
var left = (screen.width/2)-(900/2);
var top = (screen.height/2)-(600/2) + 100;

$(document).ready(function(){			
	grid_init();
	
	$(window).resize(function(){
		var width = jQuery('#main_search_box').width();
		jQuery("#jq_grid").setGridWidth(width-5);
	}); 
});

function grid_init(){
				
	$("#jq_grid").jqGrid({
		url:'/UserSetting/searchList.do',
		postData: {			
			companyId: function(){return $('#secCompanyID').val();},
			userId: function(){return $('#userId').val();},
			userName: function(){return $('#userName').val();},
			activeStatusCode: function(){return $('#activeStatusCode').val();},
			userRoleCode: function(){return $('#userRoleCode').val();},
			companyName: function(){return $('#companyName').val();},
			tokenLabel: function(){return $('#tokenLabel').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['User ID','User Name','User Role','Company ID','Company Name','Token Label','Status', 'Email','Reg.Date'],
		colModel : [
		   {name:'userId', index:'userId'}, 
		   {name:'userName', index:'userName'},
		   {name:'userRoleCode', index:'userRoleCode'},
		   {name:'companyId', index:'companyId'},
		   {name:'companyName', index:'companyName'},
		   {name:'tokenLabel', index:'tokenLabel'},
		   {name:'activeStatusCode', index:'activeStatusCode'},
		   {name:'email', index:'email'},
		   {name:'registrationDateStr', index:'registrationDate'}
		],				
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',
		sortname:'registrationDate',
		viewrecords:true,
		sortable:true,
		loadonce:false,
		sortorder:"desc",
		height:"100%",
		emptyrecords: "Empty Data!",		
		jsonReader : {
			root: "rows",
			page: "page",
			total: "total",
			records: "records",
			repeatitems:false,
			cell: "cell",
			id: "id"
		},
		multiselect:true,
		ondblClickRow:function(rowId){
			var rowData = jQuery(this).getRowData(rowId);
			var userId = rowData['userId'];
			
			doDetail(userId);
		},
		cmTemplate:{title:false}
	});
		
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function doDetail(userId){
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/UserSetting/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'userId';
	input.value= userId;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top="+top+",left="+left);
	
	form.submit();
	
	document.body.removeChild(form);
}

function doClear(){
	$("input").val("");
	$("select").val("");
}

function doSearch(){
	reloadGrid();
}


function doGenerate(){
	
	var linkhref="/UserSetting/popup/add.do";
	window.open(linkhref,"Add","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top="+top+",left="+left);
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message('Edit',$.i18n.prop('signle_row_select'));
		return;
	}
	
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	var userId = rowData['userId'];
    
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/UserSetting/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'userId';
	input.value= userId;
	form.appendChild(input);
		
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top="+top+",left="+left);

    form.submit();
	
	document.body.removeChild(form);
}

function doDelete(){
	
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length == 0){
		dialog_message('Delete',$.i18n.prop('multi_row_select'));
		return;
	}	
	
	var userIDs = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		
		if(rowData['activeStatusCode'] == 'Inactive'){
			dialog_message("Delete", $.i18n.prop('user_deleted',rowData['userName']));
			return;
		}else{
			userIDs[i] = rowData['userId'];
		}		
	}
	
	if(userIDs.length == 0) return;
		
	dialog_confirm("Confirm", $.i18n.prop('user_delete_confirm'), doDeleteCallback);	
	
}

function doDeleteCallback(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	var userIDs = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		
		userIDs[i] = rowData['userId'];	
	}
	
	doDeleteUsers(function postProcess(flag){
		if(flag){
			reloadGrid();
		}
	}, userIDs);
}

function doDeleteUsers(callback, userIDs){
	$.ajax({
		url: '/UserSetting/deleteUser.do',
		type: 'POST',
		data: {'userId':userIDs.join(',')},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				
				dialog_message("Success", response.message);			
				
				callback(true);
				
			}else{
				dialog_message("Failure", response.message);
			}
		},
		error: function(e){
			dialog_message("Error", e);
		}
		
	});
}

function doExcel(){
		
	$('#sortorder').val( $('#jq_grid').jqGrid('getGridParam', 'sortorder'));
	$('#sortname').val($('#jq_grid').jqGrid('getGridParam', 'sortname'));
	
	$('#form1').submit();
	
	//$.download('/UserSetting/excel.do',$('#form1').serialize() );
}

jQuery.download = function(url, data, method){
	//url and data options required
	if( url && data ){ 
		//data can be string of parameters or array/object
		data = typeof data == 'string' ? data : jQuery.param(data);
		//split params into form inputs
		var inputs = '';
		jQuery.each(data.split('&'), function(){ 
			var pair = this.split('=');
			inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
		});
		//send request
		jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
		.appendTo('body').submit().remove();
	};
};
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
		url:'/CompSetting/searchList.do',
		postData: {
			companyId: function(){return $('#secCompanyID').val();},
			companyName: function(){return $('#companyName').val();},
			companyStatusCode: function(){return $('#companyStatusCode').val();},
			tokenLabel: function(){return $('#tokenLabel').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}			
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company ID','Company Name','Token Label', 'Biz License No','Tel No.','Homepage URL','Status', 'Register Date'],
		colModel : [
		   {name:'companyId', index:'companyId'}, 
		   {name:'companyName', index:'companyName'}, 
		   {name:'tokenLabel', index:'tokenLabel'},
		   {name:'bizLicenseNo', index:'bizLicenseNo'},
		   {name:'officeTelNum', index:'officeTelNum'},
		   {name:'homepageUrl', index:'homepageUrl'},
		   {name:'companyStatusCode', index:'companyStatusCode'},
		   {name:'registrationDateStr', index:'registrationDate'}
		],				
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',
		sortname:'companyId',
		viewrecords:true,
		sortable:true,
		loadonce:false,
		sortorder:"asc",
		height:"100%",
		emptyrecords: "Empty Data!",		
		jsonReader : {
			root: "rows",
			page: "page",
			total: "total",
			records: "records",
			repeatitems:false,
			cell: "cell",
			id: "companyId"
		},
		multiselect:true,
		ondblClickRow:function(rowId){
			var rowData = jQuery(this).getRowData(rowId);
			var companyId = rowData['companyId'];
			
			doDetail(companyId);
		},
		cmTemplate:{title:false}
	});
		
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
	
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");	
}

function doDetail(companyId){
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/CompSetting/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'companyId';
	input.value= companyId;
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
	
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message('Edit',$.i18n.prop('single_row_select'));
		return;
	}
	
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	var companyId = rowData['companyId'];
    
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/CompSetting/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'companyId';
	input.value= companyId;
	form.appendChild(input);
		
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top="+top+",left="+left);

    form.submit();
	
	document.body.removeChild(form);
}

function doDelete(){
	
	
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length == 0){
		dialog_message('Edit',$.i18n.prop('multi_row_select'));
		return;
	}
	
	var companyIds = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		
		
			companyIds[i] = rowData['companyId'];
				
	}
	
	if(companyIds.length == 0) return;
	
	if(!confirm("Do you want to delete company?")) return;
	
	doDeleteCompany(function postProcess(flag){
		if(flag){
			reloadGrid();
		}
	}, companyIds);	
}

function doDeleteCompany(callback, companyIds){
	$.ajax({
		url: '/CompSetting/deleteCompany.do',
		type: 'POST',
		data: {'companyId':companyIds.join(',')},
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
	
}


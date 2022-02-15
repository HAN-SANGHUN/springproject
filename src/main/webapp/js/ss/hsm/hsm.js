
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
		url:'/HsmSetting/searchList.do',
		postData: {
			hsmLabel: function(){return $('#hsmLabel').val();},
			statusCode: function(){return $('#statusCode').val();},
			ipAddress: function(){return $('#ipAddress').val();},			
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['HSM No.','HSM Label','HSM Slot Count','Allocation Count', 'Start Index','End Index','IP','Port','Status', 'Description'],
		colModel : [
		   {name:'hsmNo', index:'hsmNo', hidden:true}, 
		   {name:'hsmLabel', index:'hsmLabel'}, 
		   {name:'slotCount', index:'slotCount'},
		   {name:'tokenAllocationNum', index:'tokenAllocationNum'},
		   {name:'slotStartNum', index:'slotStartNum'},
		   {name:'slotEndNum', index:'slotEndNum'},
		   {name:'ipAddress', index:'ipAddress'},
		   {name:'port', index:'port'},
		   {name:'statusCode', index:'statusCode'},
		   {name:'description', index:'description'}
		],				
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',
		sortname:'hsmNo',
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
			id: "hsmNo"
		},
		multiselect:true,
		ondblClickRow:function(rowId){
			var rowData = jQuery(this).getRowData(rowId);
			var hsmNo = rowData['hsmNo'];
			
			doDetail(hsmNo);
		},
		cmTemplate:{title:false}
	});
		
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function doDetail(hsmNo){
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/HsmSetting/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'hsmNo';
	input.value= hsmNo;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top="+top+",left="+left);
	
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
	
	var linkhref="/HsmSetting/popup/add.do";
	window.open(linkhref,"Add","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top="+top+",left="+left);
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message('Edit',$.i18n.prop('signle_row_select'));
		return false;
	}
	
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	var hsmNo = rowData['hsmNo'];
    
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/HsmSetting/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'hsmNo';
	input.value= hsmNo;
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
		dialog_message('Delete',$.i18n.prop('multi_row_select'));
		return;
	}	
	
	var hsmNos = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		
		if(rowData['statusCode'] == 'Active'){
			dialog_message("Warning", $.i18n.prop('hsm_active_cannot_be_deleted',rowData['hsmLabel']));
			return;
		}else{
			hsmNos[i] = rowData['hsmNo'];
		}		
	}
	
	if(hsmNos.length == 0) return;
		
	dialog_confirm("Confirm", $.i18n.prop('hsm_delete_confirm'), doDeleteCallback);	
	
}

function doDeleteCallback(){
	var sel = $("#jq_grid").getGridParam('selarrrow');

	var hsmNos = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		
		hsmNos[i] = rowData['hsmNo'];	
	}
	
	doDeleteHsm(function postProcess(flag){
		if(flag){
			reloadGrid();
		}
	}, hsmNos);
}

function doDeleteHsm(callback, hsmNos){
	$.ajax({
		url: '/HsmSetting/deleteHsm.do',
		type: 'POST',
		data: {'hsmNo':hsmNos.join(',')},
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
	
	//$.download('/HsmSetting/excel.do',$('#form1').serialize() );
}

function doActivateHsm(){
	
	$.ajax({
		url: '/HsmSetting/activateHsm.do',
		type: 'POST',
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				
				dialog_message("Success", response.message);
				
				reloadGrid();
			}else{
				dialog_message("Failure", response.message);
			}
		},
		error: function(e){
			dialog_message("Error", e);
		}
		
	});
}

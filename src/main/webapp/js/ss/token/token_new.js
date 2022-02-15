$(document).ready(function(){
	$('#pop-main').height('80%');
	
	validator_init();
	
	$("#searchName").focus();
	
	$("#searchName").keypress(function(event){
		if (event.keyCode == 13) {
            findCompany();
        }
	});
});

function validator_init(){
	$('#form2').validate({
		rules:{
			tokenPin:{required:true},
			companyId:{required:true}
		},
		messages:{
			companyId:"please, select a company."
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){
			doSave(function postProcess(response){										
					
				window.opener.reloadGrid();			

				dialog_message('Success', response.message, close_popup);			
			});
		}		
	});
}

function close_popup(){
	window.close();
}

function doSubmit(){	
	$('#form2').submit();
}

function doSave(callback){
	$.ajax({
		url: '/TokenSetting/popup/generateToken.do',
		type: 'POST',
		data: $('#form2').serialize(),
		dataType: 'json',
		success: function(response){
			
			if(response.status == "SUCCESS"){			
				
				callback(response);
				
			}else{
				dialog_message("Failure", response.message);
			}
		},
		error: function(e){
			dialog_message("Error", e);
		}
		
	});
}

var gridInit = false;

function findCompany(){
	
	if(gridInit){

		reloadGrid();
		
		if($('#main-con-content').css('display') == 'none'){
			$('#main-con-content').show(500);
		}
	}else{
		grid_init();
		$('#main-con-content').show(500);
		gridInit = true;
	}	
}


function reloadGrid(){
	$("#jq_grid").trigger("reloadGrid");
}

function grid_init(){
	$("#jq_grid").jqGrid({
		url:'/CompSetting/searchList.do',
		postData: {
			tokenLabelFlag: 'Y',
			companyStatusCode: 'Active',
			companyName: function(){return $('#searchName').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company ID','Company Name','Token Label','Status','Reg.Date'],
		colModel : [
		   {name:'companyId', index:'companyId'}, 
		   {name:'companyName', index:'companyName',width:250}, 
		   {name:'tokenLabel', index:'tokenLabel'},
		   {name:'companyStatusCode', index:'companyStatusCode', width:80},
		   {name:'registrationDateStr', index:'registrationDate'}
		],				
		rowNum:5,
		autowidth:true,
		shrinktofit:false,
		rowList:[5],
		pager:'#pager',
		sortname:'companyName',
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
			id: "companyName"
		},
		multiselect:false,
		ondblClickRow:function(rowId){
			var rowData = jQuery(this).getRowData(rowId);
			var companyId = rowData['companyId'];
			var companyName = rowData['companyName'];
			var tokenLabel = rowData['tokenLabel'];
			
			doProcess(companyId, companyName, tokenLabel);
		},
	});
		
    $("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    $("#jq_grid").setGridWidth($("#jq_grid").width()-5);
}

function doProcess(companyId, companyName, tokenLabel){
	
	if(tokenLabel != '' && tokenLabel != null){
		dialog_message("New", $.i18n.prop('token_exists'));
		
		return;
	}
	
	$('#searchName').val(companyName);
	$('#companyID').val(companyId);
	$('#companyName').val(companyName);
	$('#tokenLabel').val(companyId);
	
	$('#main-con-content').hide(500);
}

function cancelSearch(){
	$('#searchName').val("");
	$('#companyId').val("");
	$('#companyName').val("");
	
	$('#main-con-content').hide(500);
}

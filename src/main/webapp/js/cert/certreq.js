$(document).ready(function(){
			
	calendar_init();
	
	grid_init();
	
	$(window).resize(function(){
		var width = jQuery('#main_search_box').width();
		jQuery("#jq_grid").setGridWidth(width-5);
	}); 
});

function calendar_init(){
	$( "#startDate" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$( "#endDate" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$("#startDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});
	
	$("#endDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});
}


function grid_init(){
				
	$("#jq_grid").jqGrid({
		url:'/CertReq/searchList.do',
		postData: {
			reqSendStatusCode: '',
			SYNC_YN: '',
			companyID:function(){return $("#secCompanyID").val();},
			companyName: function(){return $('#companyName').val();},
			brandTypeCode: function(){return $('#brandTypeCode').val();},
			binNumber: function(){return $('#binNumber').val();},
			ipkIndex: function(){
				if($('#ipkIndex').val() == ''){
					return 0;
				}else{
					return $('#ipkIndex').val();
				}
									
			},
			periodType: function(){return $('#periodType').val();},
			startDate: function(){return $('#startDate').val();},
			endDate: function(){return $('#endDate').val();},
			ipkSize: function(){return $('#ipkSize').val();},
			trackingNumber: function(){return $('#trackingNumber').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company','Certificate ID','Certificate Name', 'Brand','BIN No.', 'Tracking No.', 'IPK Index','IPK Length','Status', 'Expire Date', 'Request Date', 'reqSendStatusCode', 'SYNC_YN'],
		colModel : [		  
		   {name:'companyName', index:'companyName', fixed:true}, 
		   {name:'certificateUID', index:'certificateUID', hidden:true, width:200, fixed:true},		   
		   {name:'certificateName', index:'certificateName', width:200, fixed:true}, 
		   {name:'brandTypeCode', index:'brandTypeCode', fixed:true},
		   {name:'binNumber', index:'binNumber', width:100, fixed:true},
		   {name:'trackingNumber', index:'trackingNumber', width:100, fixed:true},
		   {name:'ipkIndex', index:'ipkIndex', width:100, fixed:true},
		   {name:'ipkSize', index:'ipkSize', width:100, fixed:true},
		   {name:'binStatusCode', index:'binStatusCode', fixed:true},
		   {name:'expireDateStr', index:'expireDate', fixed:true},
		   {name:'requestDateStr', index:'requestDate', fixed:true},
		   {name:'reqSendStatusCode', index:'reqSendStatusCode', hidden:true, width:100,fixed:true},
		   {name:'SYNC_YN',index:'SYNC_YN', width:100, fixed:true, align:'center', formatter : function(cellvalue, options, rowObject){
			   if(rowObject.reqSendStatusCode == 'Y'){
		   			 return "Synchronized";
		   		 }else{
		   			 return '<a href="javascript:void(0);" onclick="sendCert(\''+rowObject.certificateUID+'\')" title="Click to synchronize with TSM." ><img src="../img/common/btn_sync.png" alt="sync"/></a>';
		   		 }
		   	 }}
		],					
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',
		sortname:'requestDate',
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
			var certiUID = rowData['certificateUID'];
			
			doDetail(certiUID);
		},
		cmTemplate:{title:false}
	});
		
    $("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    
    $("#jq_grid").jqGrid('navButtonAdd','#pager',{
	     caption: "Columns",
	     onClickButton : function (){
	         jQuery("#jq_grid").jqGrid('columnChooser', {
	        	 done : function(){
	        		 var width = jQuery('#main_search_box').width();
	        		 jQuery("#jq_grid").setGridWidth(width);
	        	 }
	         });
	     }
	});
}

function sendCert(certificateUID){
	$.ajax({
		type: "POST",
		data : { certificateUID : certificateUID},				
		url: "/CertReq/sendCert.do",
		dataType: "json",
		async: true,
		success: function(response){
			reloadGrid();
		},
		error : function(e){
			reloadGrid();
		}

	});
	
}

function reloadGrid(){
	$("#jq_grid").trigger("reloadGrid");
}

function doDetail(certiUID){
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/CertReq/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'certiUID';
	input.value= certiUID;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}

function doClear(){
	$("input").val("");
	$("select").val("");
	
	$("#ipkIndex").val("0");
	$("#ipkSize").val("0");
}

function doSearch(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function doGenerate(){
	var linkhref="/CertReq/popup/request.do";
	window.open(linkhref,"Generate","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message("Warning", $.i18n.prop('signle_row_select'));
		return;
	}
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);	
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/CertReq/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'certiUID';
	input.value= rowData.certificateUID;
	form.appendChild(input);
		
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}

function doDelete(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length == 0){
		dialog_message("Warning", $.i18n.prop('multi_row_select'));
		return;
	}
		
	dialog_confirm("Confirm", $.i18n.prop('certi_req_delete_confirm'), doDeleteCallback);		
}

function doDeleteCallback(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	var certiUIDs = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		certiUIDs[i] = rowData['certificateUID'];
	}
	
	doDeleteCerts(function postProcess(response){
		
		dialog_message("Success", response.message);	
		reloadGrid();
	
	}, certiUIDs);
}

function doDeleteCerts(callback, certiUIDs){
	$.ajax({
		url: '/CertReq/deleteCert.do',
		type: 'POST',
		data: {'certiUID':certiUIDs.join(',')},
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

function doExcel(){
	
	$('#sortorder').val( $('#jq_grid').jqGrid('getGridParam', 'sortorder'));
	$('#sortname').val($('#jq_grid').jqGrid('getGridParam', 'sortname'));
	
	$('#form1').submit();
	
}
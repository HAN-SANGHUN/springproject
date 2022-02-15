$("document").ready(function(){
	
	$("#selectFlag").hide();
	
	$("#main_search_box").hide();
	
	$("#keyProfile").hide();	
	
	calendar_init();
	
	validator_init();
	
	$("#brandTypeInt").change(function(e){
		
		var type = $("#brandTypeInt").val();
		
		if(type == 210 || type == 240 || type == 241){
			$("#trackingNumber").attr("disabled", false);
			$("#trackingNumber").attr("maxlength", 6);
			$("#trackingNumber").attr("placeholder", "number only:6 digits");
			$("#trackingNumber").rules("add", {required:true, minlength:6, maxlength:6});
			$("#amex_file_type").hide();
			$("#amexFileType").attr("disabled", true);
		}else if(type == 220 || type == 230){
			$("#trackingNumber").attr("disabled", false);
			$("#trackingNumber").attr("maxlength", 6);
			$("#trackingNumber").attr("placeholder", "CA Serial Number:6 digits");
			$("#trackingNumber").rules("add", {required:true, minlength:6, maxlength:6});
			$("#amex_file_type").hide();
			$("#amexFileType").attr("disabled", true);
		}else if(type == 250){
			$("#trackingNumber").attr("disabled", false);
			$("#trackingNumber").attr("maxlength", 6);
			$("#trackingNumber").attr("placeholder", "number only:6 digits");
			$("#trackingNumber").rules("add", {required:true, minlength:6, maxlength:6});
			$("#amex_file_type").show();
			$("#amexFileType").attr("disabled", false);
		
		}else{
			$("#trackingNumber").attr("disabled", true);			
			$("#trackingNumber").attr("placeholder", "no input");
			$("#trackingNumber").rules("remove", {required:true, minlength:6, maxlength:6});
			$("#amex_file_type").hide();
			$("#amexFileType").attr("disabled", true);
		}
	});
	
	$("expireDateStr").change(function(e){
		
		var date = $("#expireDateStr").val();
		
		if(date != null && date != ''){
			var yyyy = date.substring(0,4);
			var mm = date.substring(5,7);
			
			var lastDay = new Date(yyyy, mm, 0);
			
			$("#expireDateStr").val(yyyy+"-"+mm+"-"+lastDay);			
		}
	});
	
	$("#certificateName").focus();
	
});

function pad (str, max) {
	  return str.length < max ? pad("0" + str, max) : str;
}

function calendar_init(){
	$( "#expireDateStr" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$( "#requestDateStr" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$('#requestDateStr').val($.datepicker.formatDate('yy-mm-dd', new Date()));
	
	$("#expireDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});
	
	$("#requestDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});	
	
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

function validator_init(){
	$('#binNumber').css("ime-mode", "disabled");
	$('#binNumber').numeric();
	
	$('#trackingNumber').css("ime-mode", "disabled");
	$('#trackingNumber').numeric();
	
	$('#ipkIndex').css("ime-mode", "disabled");
	$('#ipkIndex').numeric();
	
	$('#ipkSize').css("ime-mode", "disabled");
	$('#ipkSize').numeric();
	
	$("#form2").validate({
		rules:{
			certificateName:{required:true},
			binNumber:{required:true,minlength:6, maxlength:6},
			trackingNumber:{required:true},
			ipkIndex:{required:true},
			ipkSize:{required:true},
			expireDateStr:{required:true},
			requestDateStr:{required:true}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){
			
			if(!selected){
				$("#selectFlag").show();
				return false;
			}
			
			doSave(function postProcess(response){
				
				dialog_message("Success", response.message, close_popup);	
				
				window.opener.reloadGrid();	
				
			});
		}
	});
}

function close_popup(){
	window.close();
}

function doSave(callback){
	$.ajax({
		url: '/CertReq/popup/requestCert.do',
		type: 'POST',
		data: $('#form2').serialize(),
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
								
				callback(response);
				
			}else{
				dialog_message("Error",response.message);
			}
		},
		error: function(e){
			alert('Error' + e);
		}		
	});
}


function doSubmit(){
	
	var date = $("#expireDateStr").val();
	
	if(date != null && date != ''){
		var yyyy = date.substring(0,4);
		var mm = date.substring(5,7);
		
		var lastDay = new Date(yyyy, mm, 0).getDate();
		
		$("#expireDateStr").val(yyyy+"-"+mm+"-"+lastDay);			
	}
	
	$("#form2").submit();
}

function findProfile(){
	
}

var gridInit = false;

function findProfile(){
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
	

	if($('#main_search_box').css('display') == 'none'){
		$('#main_search_box').show();
	}
	
	$("#keyProfile").hide();	
}

function grid_init(){
	$("#jq_grid").jqGrid({
		url:'/KeyProfile/searchList.do',
		postData: {
			keyValueFlag: '',
			keyUsageIndicatorValue:'',			
			activeStatusCode: 'Active',
			companyName: function(){return $('#scompanyName').val();},
			profileID: function(){return $('#sprofileID').val();},
			profileName: function(){return $('#sprofileName').val();},
			profileVersion: function(){return $('#sprofileVersion').val();},
			keyLabel: function(){return $('#skeyLabel').val();},
			keyTypeCode: function(){return $('#skeyTypeCode').val();},
			keyRoleCode: function(){return $('#skeyRoleCode').val();},
			periodType: function(){return $('#periodType').val();},
			startDate: function(){return $('#startDate').val();},
			endDate: function(){return $('#endDate').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company','Profile ID','Profile Name','Version','Key Label','Reg.Date','Expire Date', 'keySubject'],
		colModel : [
		   {name:'companyName', index:'companyName', width:100}, 	   
		   {name:'keyProfileID', index:'keyProfileID',width:100},
		   {name:'keyProfileName', index:'keyProfileName',width:140},
		   {name:'keyProfileVersion', index:'keyProfileVersion', align:'center', width:100},
		   {name:'keyLabel', index:'keyLabel',width:100},
		   {name:'registrationDateStr', index:'registrationDateStr',width:100},
		   {name:'expireDateStr', index:'expireDateStr',width:100},
		   {name:'keySubject', index:'keySubject',hidden:true}

		],				
		rowNum:5,
		autowidth:true,
		shrinktofit:false,
		rowList:[5],
		pager:'#pager',
		sortname:'keyProfileID',
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
		multiboxonly:true,
		ondblClickRow: function(rowid){
						
			var rowData = jQuery(this).getRowData(rowid);
			var keySubject = rowData['keySubject'];
						
			doDetail(keySubject);
		}	
	});
	
    $("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    $("#jq_grid").setGridWidth($("#jq_grid").width()-5);
}

function reloadGrid(){
	$("#jq_grid").trigger("reloadGrid");
}

function cancelSearch(){
	
	if(!selected){
		$("#profileID").val("");
		$("#profileVersion").val("");
		$("#keyProfile span").text("");
		$("#keyProfile input:checkbox").removeAttr('checked');
		$("#keyProfile").hide();	
	}else{
		$("#keyProfile").show();	
	}	
	
	$('#main-con-content').hide();
	$('#main_search_box').hide();
	
}

var selected = false;

function doDetail(keySubject){
	getDetail(function postProcess(response){
		$("#profileID").val(response.publicKey.keyProfileID);
		$("#profileVersion").val(response.publicKey.keyProfileVersion);
		$("#companyID").val(response.publicKey.companyID);
		$("#keySubject").val(response.publicKey.keySubject);
		
		$("#companyName").text(response.publicKey.companyName);
		$("#tokenLabel").text(response.publicKey.tokenLabel);
		$("#keyProfileID").text(response.publicKey.keyProfileID);
		$("#keyProfileName").text(response.publicKey.keyProfileName);
		$("#keyProfileVersion").text(response.publicKey.keyProfileVersion);
		$("#description").text(response.publicKey.description);
		$("#effectiveStartDate").text(response.publicKey.effectiveStartDateStr);
		$("#owner").text(response.publicKey.companyID);
		$("#effectiveEndDate").text(response.publicKey.effectiveEndDateStr);
		$("#testModeCode").text(response.publicKey.testMode);
		$("#revocationDate").text(response.publicKey.revocationDateStr);
		$("#statusCode").text(response.publicKey.activeStatusCode);
		$("#keyTypeCode").text(response.publicKey.keyTypeCode);
		$("#keySubtypeCode").text(response.publicKey.keySubtypeCode);
		
		if(response.publicKey.keyVersion != null){
			$("#keyVersion").text(response.publicKey.keyVersion);
		}
		
		if(response.publicKey.keyIdentifier != null){
			$("#keyIdentifier").text(response.publicKey.keyIdentifier+" (" + response.publicKey.keyDefinition + ")");
		}
		
		$("#keyRoleCode").text(response.publicKey.keyRoleCode);
		$("#keyIndex").text(response.publicKey.keyIndex);
		$("#keyLabel").text(response.publicKey.keyLabel);
		$("#keySize").text(response.publicKey.keySize);
		$("#keyAlgorithm").text(response.publicKey.keyAlgorithm);
		
		var attrs = response.publicKey.keyValueAttribute;
		
		
		if(attrs.importable) $("#importable").attr('checked', 'checked');
		if(attrs.exportable) $("#exportable").attr('checked', 'checked');
		if(attrs.sensitive) $("#sensitive").attr('checked', 'checked');
		if(attrs.encrypt) $("#encrypt").attr('checked', 'checked');
		if(attrs.decrypt) $("#decrypt").attr('checked', 'checked');
		if(attrs.encryptDecrypt) $("#enDecrypt").attr('checked', 'checked');
		if(attrs.wrap) $("#wraps").attr('checked', 'checked');
		if(attrs.unwrap) $("#unwrap").attr('checked', 'checked');
		if(attrs.wrapUnwrap) $("#wrapUnwrap").attr('checked', 'checked');
		if(attrs.sign) $("#sign").attr('checked', 'checked');
		if(attrs.verify) $("#verify").attr('checked', 'checked');
		if(attrs.derive) $("#derive").attr('checked', 'checked');
		
		// Private Key Profile Set
		
		$("#keyProfileID1").text(response.privateKey.keyProfileID);
		$("#keyProfileName1").text(response.privateKey.keyProfileName);
		$("#keyProfileVersion1").text(response.privateKey.keyProfileVersion);
		$("#description1").text(response.privateKey.description);
		
		$("#keyLabel1").text(response.privateKey.keyLabel);
		$("#keySize1").text(response.privateKey.keySize);
		$("#keyAlgorithm1").text(response.privateKey.keyAlgorithm);
		
		var attrs1 = response.privateKey.keyValueAttribute;
		
		
		if(attrs1.importable) $("#importable1").attr('checked', 'checked');
		if(attrs1.exportable) $("#exportable1").attr('checked', 'checked');
		if(attrs1.sensitive) $("#sensitive1").attr('checked', 'checked');
		if(attrs1.encrypt) $("#encrypt1").attr('checked', 'checked');
		if(attrs1.decrypt) $("#decrypt1").attr('checked', 'checked');
		if(attrs1.encryptDecrypt) $("#enDecrypt1").attr('checked', 'checked');
		if(attrs1.wrap) $("#wraps1").attr('checked', 'checked');
		if(attrs1.unwrap) $("#unwrap1").attr('checked', 'checked');
		if(attrs1.wrapUnwrap) $("#wrapUnwrap1").attr('checked', 'checked');
		if(attrs1.sign) $("#sign1").attr('checked', 'checked');
		if(attrs1.verify) $("#verify1").attr('checked', 'checked');
		if(attrs1.derive) $("#derive1").attr('checked', 'checked');
		
		// set IPK Index and IPK Length
		$("#ipkIndex").val(response.publicKey.keyIndex);
		$("#ipkSize").val(response.publicKey.keySize / 8);
		
		$('#main-con-content').hide();
		$('#main_search_box').hide();
		$("#keyProfile").show();		
		
	}, keySubject);
	
	selected = true;
	$("#selectFlag").hide();
}

function getDetail(callback, keySubject){
	
	$.ajax({
		url: '/KeyProfile/json/detail.do',
		type: 'POST',
		data: {'keySubject':keySubject},
		dataType: 'json',
		success: function(response){
			callback(response);
		},
		error: function(e){
			dialog_message("Error", e);
		}
		
	});
}
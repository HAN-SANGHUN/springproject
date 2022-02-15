$(document).ready(function(){			
	calendar_init();
	key_definition_init();
	key_type_init();
	key_role_init();
	key_index_init();
	validator_init();
	
	var code = $('#keyRoleCode').val();
	
	if(code == 'Application Key'){
		$('#keyVersion').prop('disabled', true);
		$('#keyIdentifier').prop('disabled', true);
		$('#keyDefinition').prop('disabled', true);
		$('#keyIndex').prop('disabled', true);	
		
		$('#keyVersion').attr('placeholder','no input').blur();
		$('#keyIdentifier').attr('placeholder','no input').blur();
		$('#keyDefinition').attr('placeholder','no input').blur();
		$('#keyIndex').attr('placeholder','no input').blur();		
		
		$("#keyIndex").rules('remove',{'required':true, 'minlength':1,'maxlength':9});

	}else if(code == 'Transport Key' || code == 'Issuer RSA Key'){
		$('#keyVersion').prop('disabled', true);
		$('#keyIdentifier').prop('disabled', true);
		$('#keyDefinition').prop('disabled', true);
		$('#keyIndex').prop('disabled', false);
		$('#keyIndex').attr('readonly', false);
		
		$('#keyVersion').attr('placeholder','no input').blur();
		$('#keyIdentifier').attr('placeholder','no input').blur();
		$('#keyDefinition').attr('placeholder','no input').blur();
		$('#keyIndex').attr('placeholder','number only').blur();
		
		$("#keyIndex").rules('add',{'required':true, 'minlength':1,'maxlength':9});		
		
	}else{
		$('#keyVersion').prop('disabled', false);
		$('#keyIdentifier').prop('disabled', false);
		$('#keyDefinition').prop('disabled', false);
		$('#keyIndex').prop('disabled', true);			
		
		$('#keyVersion').attr('placeholder','').blur();
		$('#keyIdentifier').attr('placeholder','').blur();
		$('#keyDefinition').attr('placeholder','auto input').blur();
		$('#keyIndex').attr('placeholder','no input').blur();	
		
		$("#keyIndex").rules('remove',{'required':true, 'minlength':1,'maxlength':9});
		
		$('#algorithm').text('KCV Alogrithm');
	}
});

function calendar_init(){
	
	$( "#effectiveStartDateStr" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$( "#effectiveEndDateStr" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$( "#revocationDateStr" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});	
	
		
	$('#searchName').keypress(function(e){
		code= (e.keyCode ? e.keyCode : e.which);
        if (code == 13) findCompany();
	});			
}

function key_index_init(){
	
	$("#keyIndex").focusout(function(){
		key_label_init();
	});
}

function key_definition_init(){
		
	$('#keyIdentifier').focusout(function(){
		var ver = $('#keyVersion').val();
		var idt = $('#keyIdentifier').val();
		
		key_label_init();
		
		if(ver=='75' && idt == '01'){
			$('#keyDefinition').val('Deciper Key');
			return;
		}
		
		if(ver=='73' && idt == '01'){
			$('#keyDefinition').val('DAP Verification Key');
			return;
		}
		
		if(ver=='11' && idt == '01'){
			$('#keyDefinition').val('DAP Key');
			return;
		}
		
		if(ver=='71' && idt == '01'){
			$('#keyDefinition').val('Receipt Key');
			return;
		}
		
		if(ver=='70' && idt == '01'){
			$('#keyDefinition').val('Token Key');
			return;
		}
		
		if(ver=='74' && idt == '01'){
			$('#keyDefinition').val('CASD Key');
			return;
		}
		
		if(ver=='74' && idt == '02'){
			$('#keyDefinition').val('CASD Key');
			return;
		}
		
		if(ver=='74' && idt == '03'){
			$('#keyDefinition').val('CASD Key');
			return;
		}
		
		if(20 <=ver<=47 && idt == '01'){
			$('#keyDefinition').val('SCP02 ENC Key');
			return;
		}
		
		if(20 <=ver<=47 && idt == '02'){
			$('#keyDefinition').val('SCP02 MAC Key');
			return;
		}
		
		if(20 <=ver<=47 && idt == '03'){
			$('#keyDefinition').val('SCP02 DEK Key');
			return;
		}
		
		if(10 <=ver<=15 && idt == '01'){
			$('#keyDefinition').val('SCP80 ENC Key');
			return;
		}
		
		if(10 <=ver<=15 && idt == '02'){
			$('#keyDefinition').val('SCP80 MAC Key');
			return;
		}
		
		if(10 <=ver<=15 && idt == '03'){
			$('#keyDefinition').val('SCP80 DEK Key');
			return;
		}
	});
}

function key_type_init(){
	
	$('#searchName').focus();		
	
	$('#keySubtypeCode').change(function(){
		var code = $('#keySubtypeCode').val();
		var des = {
				'64' : '64',
				'128' : '128',
				'192' : '192'
		};
		var seed = {
				'128' : '128'
		};
		var aes = {
				'128' : '128',
				'192' : '192',
				'256' : '256'
		};
		var rsa = {
				'768' : '768',
				'1024' : '1024',
				'1152' : '1152',
				'1408' : '1408',
				'2048' : '2048'
		};
		
		if(code == 'DES'){
			
			$('#keySize').empty();
			
			$.each(des, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize').val('192');
			
			$('#keySize1').empty();
			
			$.each(des, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').val('192');
			
		}else if(code == 'SEED'){
			
			$('#keySize').empty();
			
			$.each(seed, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').empty();
			
			$.each(seed, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});	
			
		}else if(code == 'AES'){
			
			$('#keySize').empty();
			
			$.each(aes, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').empty();
			
			$.each(aes, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});	
			
		}else{
			$('#keySize').empty();
			
			$.each(rsa, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').empty();
			
			$.each(rsa, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});	
		}
		
		key_label_init();
	});
}

function key_role_init(){
	$('#keyRoleCode').change(function(){
		var code = $('#keyRoleCode').val();
		
		if(code == 'Application Key'){
			$('#keyVersion').prop('disabled', true);
			$('#keyIdentifier').prop('disabled', true);
			$('#keyDefinition').prop('disabled', true);
			$('#keyIndex').prop('disabled', true);	
			
			$('#keyVersion').attr('placeholder','no input').blur();
			$('#keyIdentifier').attr('placeholder','no input').blur();
			$('#keyDefinition').attr('placeholder','no input').blur();
			$('#keyIndex').attr('placeholder','no input').blur();		
			
			$("#keyIndex").rules('remove',{'required':true, 'minlength':1,'maxlength':9});

		}else if(code == 'Transport Key' || code == 'Issuer RSA Key'){
			$('#keyVersion').val('');
			$('#keyIdentifier').val('');
			$('#keyVersion').prop('disabled', true);
			$('#keyIdentifier').prop('disabled', true);
			$('#keyDefinition').prop('disabled', true);
			$('#keyIndex').prop('disabled', false);
			$('#keyIndex').attr('readonly', false);
			
			$('#keyVersion').attr('placeholder','no input').blur();
			$('#keyIdentifier').attr('placeholder','no input').blur();
			$('#keyDefinition').attr('placeholder','no input').blur();
			$('#keyIndex').attr('placeholder','number only').blur();
			
			$("#keyIndex").rules('add',{'required':true, 'minlength':1,'maxlength':9});		
			
		}else{
			$('#keyVersion').prop('disabled', false);
			$('#keyIdentifier').prop('disabled', false);
			$('#keyDefinition').prop('disabled', false);
			$('#keyIndex').val('');
			$('#keyIndex').prop('disabled', true);			
			
			$('#keyVersion').attr('placeholder','').blur();
			$('#keyIdentifier').attr('placeholder','').blur();
			$('#keyDefinition').attr('placeholder','auto input').blur();
			$('#keyIndex').attr('placeholder','no input').blur();	
			
			$("#keyIndex").rules('remove',{'required':true, 'minlength':1,'maxlength':9});
			
			$('#algorithm').text('KCV Alogrithm');
		}
	});
}

function validator_init(){
	
	$('#keyIndex').css("ime-mode", "disabled");
	$('#keyIndex').numeric();
	
	$('#keyVersion').css("ime-mode", "disabled");
	$('#keyVersion').numeric();
	
	$('#keyIdentifier').css("ime-mode", "disabled");
	$('#keyIdentifier').numeric();
	
	$('#keyProfileVersion').css("ime-mode", "disabled");
	$('#keyProfileVersion').numeric({allow:"."});
	
	$('#form2').validate({
		rules:{
			keyVersion: {required:true},
			keyIdentifier: {required:true},
			keyLabel: {required:true},
			keyProfileName: {required:true},
			description: {required:true},
			keyProfileVersion: {required:true}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){
			doSave(function postProcess(response){				
				
				dialog_message('Success', response.message, close_popup);
				
				window.opener.reloadGrid();		
				
				
			});
		}
	});
}

function close_popup(){
	window.close();
}

function doSubmit(){	

	if($("#companyID").val() == ''){
		dialog_message('Warning', $.i18n.prop('select_compnay_first'));
		
		return false;
	}
	
	$('#form2').submit();
}

function doSave(callback){
	$.ajax({
		url: '/KeyProfile/popup/importProfile.do',
		type: 'POST',
		data: $('#form2').serialize(),
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){			
				
				callback(response);
				
			}else{
				dialog_message('Failure', response.message);
			}
		},
		error: function(e){
			dialog_message('Error', e);
		}
		
	});
}

function doCancel(){
	$.ajax({
		url: '/KeyProfile/popup/cancelImport.do',
		type: 'POST',
		data: {'tokenID':$("#tokenID").val(), 'pubTokenID':null},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){			
				
				window.close();
				
			}else{
				dialog_message('Failure', response.message);
			}
		},
		error: function(e){
			dialog_message('Error', e);
		}		
	});
}

var gridInit = false;

function findCompany(){
	$('#pop-main').height('98%');
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
	jQuery("#jq_grid").trigger("reloadGrid");
}

function grid_init(){
	$("#jq_grid").jqGrid({
		url:'/CompSetting/searchList.do',
		postData: {
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
	if(tokenLabel == '' || tokenLabel == null){
		dialog_message("Edit", $.i18n.prop('token_label_not_activated'));
		return false;
	}
	
	if($('#companyID').val() != companyId){
		profile_id_init(function postProcess(response){
			$("input[name='keyProfileID']").val(response.profileID);
			key_label_init();
		}, companyId);	
	}
	
	$('#companyID').val(companyId);
	
	$('#searchName').val(companyName);
	$('#companyName').val(companyName);
	$('#tokenLabel').val(tokenLabel);
	$('#keyOwner').val(companyId);
	
	$('#main-con-content').hide(500);
	
	pop_main_85();
}

function profile_id_init(callback, companyId){
	$.ajax({
		url: '/KeyProfile/popup/generateProfileID.do',
		type: 'POST',
		data: {'companyID':companyId},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){				
				callback(response);
				
			}else{
				dialog_message("Failure", $.i18n.prop("key_profile_id_fatal"));
				window.close();
			}
		},
		error: function(e){
			dialog_message("Error", e);
		}		
	});
}

function key_label_init(){
	var profileID = $("#keyProfileID").val();
	var keyType = $("#keyTypeCode").val();
	var keySubtype = $("#keySubtypeCode").val();
	var keyRole = $("#keyRoleCode").val();
	var postfix='';
	var keyLabel;	
	
	if(keyRole == 'SD Key'){
		keyRole = 'SD';
		postfix = $("#keyVersion").val()+"."+$("#keyIdentifier").val();
	}else if(keyRole == 'Transport Key'){
		keyRole = 'TRANS';
		postfix = $("#keyIndex").val();
	}else if(keyRole == 'Application Key'){
		keyRole = 'APP';
	}else{
		keyRole = 'ISSUERRSA';
		postfix= $("#keyIndex").val();
	}
	
	if(postfix == null || postfix == ''){
		keyLabel = profileID+"."+keyType+"."+keySubtype+'.'+keyRole;
	}else{
		keyLabel = profileID+"."+keyType+"."+keySubtype+'.'+keyRole+'.'+postfix;
	}
	
	$("#keyLabel").val(keyLabel);
}

function cancelSearch(){
	$('#searchName').val("");
	$('#companyID').val("");
	$('#companyName').val("");
	$('#tokenLabel').val("");
	$('#keyOwner').val("");
	
	$('#main-con-content').hide(500);
	
	pop_main_85();
}

function pop_main_85(){
	$('#pop-main').height('85%');
}

function pop_main_87(){
	$('#pop-main').height('87%');
}

function pop_main_90(){
	$('#pop-main').height('90%');
}

function pop_main_95(){
	$('#pop-main').height('95%');
}


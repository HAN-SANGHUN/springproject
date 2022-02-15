
$(document).ready(function(){			
	calendar_init();
	key_definition_init();
	key_type_init();
	key_role_init();
	key_index_init();
	validator_init();	
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
	
	$('#effectiveStartDateStr').val($.datepicker.formatDate('yy-mm-dd', new Date()));
	
	//add by shifei
	var now = new Date();
	var nextYear = now.getFullYear()+25;
	var nowMonth = now.getMonth();
	var nowDay = now.getDate();
	var nextYMD = new Date(nextYear, nowMonth, nowDay);

	$('#effectiveEndDateStr').val($.datepicker.formatDate('yy-mm-dd', nextYMD));
	$('#revocationDateStr').val($.datepicker.formatDate('yy-mm-dd', nextYMD));
	
	
	$('#searchName').keypress(function(e){
		code= (e.keyCode ? e.keyCode : e.which);
        if (code == 13) findCompany();
	});		
	
	$('#pair_key_profile_section').hide().find('input').prop('disabled',true);
}

function key_index_init(){
	$("#keyIndex").focusout(function(){
		key_label_init();
		
		if($("#keyTypeCode").val() == 'PAIRKEY'){
			public_key_label_init();
		}
	});
}

function key_definition_init(){
		
	$('#keyIdentifier').focusout(function(){
		var ver = $('#keyVersion').val();
		var idt = $('#keyIdentifier').val();
		
		key_label_init();
		
		if($("#keyTypeCode").val() == 'PAIRKEY'){
			public_key_label_init();
		}
		
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
		
		if($("#keyRoleCode").val() == 'SD Key'){
			$('#keyDefinition').val('auto input');
		}
		
	});
}

function key_type_init(){
	
	$('#searchName').focus();
	
	$('#publicKeySection').hide();
	
	$('#keyTypeCode').change(function(){
		
		if($("#companyID").val() == '' || $("#companyID").val() == null){
			dialog_message("Warning",  $.i18n.prop('select_compnay_first'));
			$('#keyTypeCode').val("SECRET");
			return;
		}
		
		var code = $('#keyTypeCode').val();
		var roptions = {
				'RSA' : 'RSA',
				'RSACRT' : 'RSACRT',
				'EC' : 'EC'
		};
		var poptions = {
				'DES' : 'DES',
				'SEED' : 'SEED',
				'AES' : 'AES'
		};
		var des = {
				'64' : '64',
				'128' : '128',
				'192' : '192'
		};
		var rsa = {
				'768' : '768',
				'1024' : '1024',
				'1152' : '1152',
				'1408' : '1408',
				'2048' : '2048'
		};
		
		if(code == 'PAIRKEY'){	
			
			$('#keyRoleCode').append($("<option></option>").attr('value', 'Issuer RSA Key').text('Issuer RSA Key'));
			
			public_profile_id_init();
			
			
			$('#publicKeySection').show(500);
			$('#privateKeyLabel').text("Private Key");
			$('#keyLabel1').rules("add",{required:true});
			$('#keyProfileName1').rules("add",{required:true});
			$('#description1').rules("add",{required:true});
			
			pop_main_87();
			
			$('#secret_key_profile_section').hide().find('input').prop('disabled', true);
			$('#pair_key_profile_section').show(500).find('input').prop('disabled',false);
			
			$('#keySubtypeCode').empty();			
			
			
			$.each(roptions, function(key, value){
				$('#keySubtypeCode').append($("<option></option>").attr('value', key).text(value));
			});
			
			// default rsa options setting
			
			$('#keySize').empty();
			
			$.each(rsa, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').empty();
			
			$.each(rsa, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});
			
		}else{
			
			$("#keyRoleCode option[value='Issuer RSA Key']").remove();
			
			$('#publicKeySection').hide(500);
			$('#privateKeyLabel').text("Secret Key");
			$('#keyLabel1').rules("remove","required");
			$('#keyProfileName1').rules("remove","required");
			$('#description1').rules("remove","required");
			
			pop_main_85();
			
			$('#pair_key_profile_section').hide().find('input').prop('disabled',true);
			$('#secret_key_profile_section').show(500).find('input').prop('disabled',false);			
			
			$('#keySubtypeCode').empty();			
			
			$.each(poptions, function(key, value){
				$('#keySubtypeCode').append($("<option></option>").attr('value', key).text(value));
			});
			
			// default des options setting
			
			$('#keySize').empty();
			
			$.each(des, function(key, value){
				$('#keySize').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keySize1').empty();
			
			$.each(des, function(key, value){
				$('#keySize1').append($("<option></option>").attr('value', key).text(value));
			});	
			
			$('#keyIndex').prop('disabled', true);
			$('#keyIndex').attr('placeholder','no input').blur();
			
		}		

		key_label_init();
		
		if($("#keyTypeCode").val() == 'PAIRKEY'){
			public_key_label_init();
		}
	});
	
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
		
		if($("#keyTypeCode").val() == 'PAIRKEY'){
			public_key_label_init();
		}
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
		
		key_label_init();
		
		if($("#keyTypeCode").val() == 'PAIRKEY'){
			public_key_label_init();
		}
	});
}

function validator_init(){
	
	$('#keyIndex').css("ime-mode", "disabled");
	$('#keyIndex').numeric();
	
	$('#keyVersion').css("ime-mode", "disabled");
//	$('#keyVersion').numeric();
	
	$('#keyIdentifier').css("ime-mode", "disabled");
//	$('#keyIdentifier').numeric();
	
	$('#form2').validate({
		rules:{
			keyVersion: {required:true},
			keyIdentifier: {required:true},
//			keyLabel: {required:true},
			keyProfileName: {required:true},
			description: {required:true}
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
				
				if(response.profileAttr != null){
					$('#save').remove();
					
					$("#close").text('Close');
					
					$('#id').val(response.profileAttr.profileID);
					$('#version').val(response.profileAttr.profileVersion);
					if(response.profileAttr.pairkey){
						$('#id1').val(response.profileAttr.pubProfileID);
						$('#version1').val(response.profileAttr.pubProfileVersion);
					}
					$('#xmlFrm').submit();					
				}
				
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
	
	
	//add by shifei 20140605
	if($("#keyRoleCode").val() == 'SD Key'){
		if($("#keyVersion").val().length != 0 && $("#keyVersion").val().length != 2){
			dialog_message("Warning",  $.i18n.prop('key_profile_version_check_digit'));
			
			return false;
		}
		
		if($("#keyIdentifier").val().length != 0 && $("#keyIdentifier").val().length != 2){
			dialog_message("Warning",  $.i18n.prop('key_profile_identifier_check_digit'));
			
			return false;
		}
	}
	
//	key_label_init();
//	if($("keyTypeCode").val == 'PAIRKEY'){
//		public_key_label_init();
//	}
	
	$('#form2').submit();
}

function doSave(callback){
	$.ajax({
		url: '/KeyProfile/generateProfile.do',
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
		   {name:'companyName', index:'companyName', width:250}, 
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
		dialog_message("New", $.i18n.prop('token_label_not_activated'));
		return false;
	}
	
	if($('#companyID').val() != companyId){
		profile_id_init(function postProcess(response){
			$("input[name='keyProfileID']").val(response.profileID);
			key_label_init();
		}, companyId);	
	}
	
	$('#companyID').val(companyId);
	
	if($("#keyTypeCode").val() == 'PAIRKEY'){
		public_profile_id_init();
	}
	
	$('#searchName').val(companyName);
	$('#companyName').val(companyName);
	$('#tokenLabel').val(tokenLabel);
	$('#keyOwner').val(companyId);
	
	$('#main-con-content').hide(500);	

	pop_main_85();	
}

function public_profile_id_init(){
	if($('#keyProfileID1').val() == ''){
		profile_id_init(function postProcess(response){
			$('#keyProfileID1').val(response.profileID);
			key_label_init();
			public_key_label_init();
		}, $('#companyID').val());
	}
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

function public_key_label_init(){
	
	var profileID = $("#keyProfileID1").val();
	var keyType = $("#keyTypeCode").val();
	var keySubtype = $("#keySubtypeCode").val();
	var keyRole = $("#keyRoleCode").val();
	var postfix='';
	var keyLabel;
	
	if(keyType != 'SECRET') keyType = 'PUBLIC';
	
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
	
	$("#keyLabel1").val(keyLabel);
}

function key_label_init(){
	var profileID = $("#keyProfileID").val();
	var keyType = $("#keyTypeCode").val();
	var keySubtype = $("#keySubtypeCode").val();
	var keyRole = $("#keyRoleCode").val();
	var postfix='';
	var keyLabel;
	
	if(keyType != 'SECRET') keyType = 'PRIVATE';
	
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
	
	$('#keyLabel').val("");
	$('#keyLabel1').val("");
	$("input[name='keyProfileID']").val("");
	$('#keyProfileID1').val("");
	
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

var downloadChecker;

function startDownload(){
	var token = new Date().getTime();
	$('#tokenID').val(token);
	
	$('#processModal').show();
	
	downloadChecker = window.setInterval(function(){
		var cookieValue = $.cookie('downloadToken');
		
		if(cookieValue == token){
			finishDownload();
		}
	}, 1000);	
}

function finishDownload(){
	window.clearInterval(downloadChecker);
    $.cookie('downloadToken', null);
    $('#processModal').hide();
}

function openProcessModal(){
	
}
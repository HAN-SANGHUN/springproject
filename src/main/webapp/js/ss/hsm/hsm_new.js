$(document).ready(function(){			
	validator_init();
});

function validator_init(){	
	
	$('#slotCount').css("ime-mode", "disabled");
	$('#slotCount').numeric();
	
	$('#slotStartNum').css("ime-mode", "disabled");
	$('#slotStartNum').numeric();
	
	$('#slotEndNum').css("ime-mode", "disabled");
	$('#slotEndNum').numeric();
	
	$('#port').css("ime-mode", "disabled");
	$('#port').numeric({allow:"-"});
	
	$('#ipAddress').css("ime-mode", "disabled");
	$('#ipAddress').numeric({allow:"."});
	
	$('#form2').validate({
		rules:{
			slotCount:{required:true},
			slotStartNum:{required:true},
			slotEndNum:{required:true},
			hsmLabel:{required:true},
			ipAddress:{required:true},
			port:{required:true}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){
			
			var slotCount = parseInt($("#slotCount").val());
			var startNum = parseInt($("#slotStartNum").val());
			var endNum = parseInt($("#slotEndNum").val());
			
			if(slotCount != (endNum - startNum + 1)){
				dialog_message('Warning', $.i18n.prop("slot_count_not_match"));	
				$("#slotCount").focus();
				return false;
			}
			
			if(startNum >= endNum){
				dialog_message('Warning', $.i18n.prop("slot_start_index_gt"));	
				return false;
			}
			
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
		url: '/HsmSetting/popup/addHsm.do',
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
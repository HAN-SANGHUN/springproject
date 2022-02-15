$(document).ready(function(){
	
	validator_init();
});

function validator_init(){
	$('#form2').validate({
		rules:{
			tokenPin:{required:true}
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

function doTokenPinCallback(){
	
	$('#form2').submit();
}

function close_popup(){
	window.close();
}

function doSubmit(){	
	
	if($("#tokenPin").val() != $("#oldTokenPin").val()){
		dialog_confirm("Confirm", $.i18n.prop('token_pin_edit_confirm'), doTokenPinCallback);				
	}else{
		$('#form2').submit();
	}
}

function doSave(callback){
	$.ajax({
		url: '/TokenSetting/popup/editToken.do',
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
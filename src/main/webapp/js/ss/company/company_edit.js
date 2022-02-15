
$(document).ready(function(){			
	validator_init();
});

function validator_init(){
	
	$('#fax').css("ime-mode", "disabled");
	$('#fax').numeric({allow:"-"});
	
	$('#officeTelNum').css("ime-mode", "disabled");
	$('#officeTelNum').numeric({allow:"-"});
	
	$('#bizLicenseNo').css("ime-mode", "disabled");
	$('#bizLicenseNo').numeric({allow:"-"});
	
	jQuery.validator.addMethod("urlValidator", function(value, element){
		var urlregex = new RegExp(
        "^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
		
		if(value == null || value == '') return true;
		
		return urlregex.test(value);
		
	}, "url format not suppored.");
	
	$('#form2').validate({
		rules:{
			companyName: {required:true},
			fax: {required:true},
			email: {required:true, email:true},
			officeTelNum: {required:true},
			bizLicenseNo: {required:true},
			homepageUrl:{urlValidator:true}
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
		url: '/CompSetting/popup/editCompany.do',
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
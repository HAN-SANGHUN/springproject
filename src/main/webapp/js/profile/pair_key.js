$(document).ready(function(){		
	$('#pop-main').height('80%');
	
	$('#mod').focus();
	
	validator_init();		
	
	$("input[name='pubRadio']:radio").change(function(){
		var value = $("input[name='pubRadio']:checked").val();
				
		if(value == 'visible'){
			$('#public_key_section').find('input:password').each(function() {
				   $("<input type='text' />").attr({ name: this.name, value: this.value }).insertBefore(this);
			}).remove();
		}else{
			$('#public_key_section').find('input:text').each(function() {
				
				   $("<input type='password' />").attr({ name: this.name, value: this.value }).insertBefore(this);
			}).remove();
		}
	});
	
	$("input[name='priRadio']:radio").change(function(){
		var value = $("input[name='priRadio']:checked").val();
		
		if(value == 'visible'){
			$('#private_key_section').find('input:password').each(function() {
				   $("<input type='text' />").attr({ name: this.name, value: this.value }).insertBefore(this);
			}).remove();
		}else{
			$('#private_key_section').find('input:text').each(function() {
				
				   $("<input type='password' />").attr({ name: this.name, value: this.value }).insertBefore(this);
			}).remove();
		}
	});
});

function validator_init(){
	
	var keysize = parseInt($("#keySize").val());
	var message=null;
	
	if(keysize == 768){
		message = "modulus_value_768";
	}else if(keysize == 1024){
		message = "modulus_value_1024";
	}else if(keysize == 1152){
		message = "modulus_value_1152";
	}else if(keysize == 1408){
		message = "modulus_value_1408";
	}else if(keysize == 2048){
		message = "modulus_value_2048";
	}		
	
	jQuery.validator.addMethod("modcheck", function(value, element){
		if(value.length === 0) return false;
		if(keysize == 768){
			if(value.length === 192) return true;
		}else if(keysize == 1024){
			if(value.length === 256) return true;
		}else if(keysize == 1152){
			if(value.length === 288) return true;
		}else if(keysize == 1408){
			if(value.length === 352) return true;
		}else if(keysize == 2048){
			//modify by shifei 20140603
//			if(value.length === 602) return true;
			if(value.length === 512) return true;
		}
		
		return false;
		
	}, $.i18n.prop(message));
		
	$('#form2').validate({
		rules:{
			mod:{modcheck:true},
			exp:{required:true},
			modExp:{required:true}			
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
		url: '/KeyProfile/popup/setPairKeyValue.do',
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

//add by shifei 20140603
function autoGen(){
	autoGen_t(function postProcess(response){
		if($('#keySubtypeCode').val()=="RSA"){
			$('#mod').val(response.modulus);
			$('#exp').val(response.public_exponent);
			$('#modExp').val(response.private_exponent);
		}else{
			$('#mod').val(response.modulus);
			$('#exp').val(response.public_exponent);
			$('#modExp').val(response.private_exponent);
			$('#crtp').val(response.prime_p);
			$('#crtq').val(response.prime_q);
			$('#crtdp1').val(response.prime_exponent_p);
			$('#crtdq1').val(response.prime_exponent_q);
			$('#crtpq1').val(response.crt_coefficient);	
		}
		
	}, $('#keySize').val());
}


function autoGen_t(callback, keySize){
	$.ajax({
		url: '/KeyProfile/popup/autoGen.do',
		type: 'POST',
//		data: $('#form2').serialize(),
		data: {'keySize':keySize, 'exp':$('#exp').val()},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				callback(response);
			} else{
				Alert("Error");
			}
		
		},
		error: function(e){
			dialog_message("Error", e);
		}
		
	});
}


function mod_clear(){
	$("#mod").val("");
}

function exp_clear(){	
	$("#exp").val("");
}

function crtp_clear(){	
	$("#crtp").val("");
}

function crtq_clear(){	
	$("#crtq").val("");
}

function crtdp1_clear(){	
	$("#crtdp1").val("");
}

function crtdq1_clear(){	
	$("#crtdq1").val("");
}

function crtpq1_clear(){	
	$("#crtpq1").val("");
}

function modExp_clear(){	
	$("#modExp").val("");
}
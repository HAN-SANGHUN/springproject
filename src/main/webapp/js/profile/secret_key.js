$(document).ready(function(){			
	validator_init();
	
	$('input').bind('keyup',function(){
		var value = $(this).val().replace(/[^0-9A-F]/g,'');
		$(this).val(value);
	});
	
	$("input[name='secretRadio']:radio").change(function(){
		var value = $("input[name='secretRadio']:checked").val();
						
		if(value == 'visible'){
			$('.Table-Style_pop2').find('input:password').each(function() {
				if(this.name == 'component1' || this.name == 'component2' || this.name == 'component3' || this.name == 'org_key'){
					$("<input type='text' />").attr({ name: this.name, id:this.id, value: this.value }).insertBefore(this);
					this.remove();
				}
			});
		}else{
			$('.Table-Style_pop2').find('input:text').each(function() {
				if(this.name == 'component1' || this.name == 'component2' || this.name == 'component3' || this.name == 'org_key'){
					$("<input type='password' />").attr({ name: this.name, id:this.id, value: this.value }).insertBefore(this);
					this.remove();
				}
			});
		}
	});
});

function components_size_init(size){
	$("#component1").attr('maxlength', size);
	$("#component2").attr('maxlength', size);
	$("#component3").attr('maxlength', size);
}

function validator_init(){
	
	var keysize = parseInt($("#keySize").val());
	var message=null;
	
	if(keysize == 64){
		message = "secret_key_64";
		components_size_init(16);
	}else if(keysize == 128){
		message = "secret_key_128";
		components_size_init(32);
	}else if(keysize == 192){
		message = "secret_key_192";
		components_size_init(48);
	}else if(keysize == 256){
		message = "secret_key_256";
		components_size_init(64);
	}	
	
	jQuery.validator.addMethod("hexonly", function(value, element){
		if(value.length === 0) return false;
		if(keysize == 64){
			if(value.length % 16 === 0) return true;
		}else if(keysize == 128){
			if(value.length % 32 === 0) return true;
		}else if(keysize == 192){
			if(value.length % 48 === 0 || value.length % 32 === 0) return true;
		}else if(keysize == 256){
			if(value.length % 64 === 0) return true;
		}
		
		return false;
		
	}, $.i18n.prop(message));
		
	$('#form2').validate({
		rules:{
			component1:{hexonly:true},
			component2:{hexonly:true},
			component3:{hexonly:true}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){			

			var keysize = parseInt($("#keySize").val());
			
			if(keysize == 192){
				var value1 = $("#component1").val();
				var value2 = $("#component2").val();
				var value3 = $("#component3").val();
				
				value1 = reconstruct(value1);
				$("#component1").val(value1);
				
				value2 = reconstruct(value2);
				$("#component2").val(value2);
				
				value3 = reconstruct(value3);
				$("#component3").val(value3);
			}
			
			doSave(function postProcess(response){			
					
				$("#kcv1").val(response.keyValue.kcv1);
				$("#kcv2").val(response.keyValue.kcv2);
				$("#kcv3").val(response.keyValue.kcv3);
				
				window.opener.reloadGrid();			
				
				$('#save').remove();
				
				$("#close").text('Close');

				$("#component1").attr("disabled", true);
				$("#component2").attr("disabled", true);
				$("#component3").attr("disabled", true);
				
			});
		}
		
	});		
}


function doSubmit(){		
	$('#form2').submit();
}

function reconstruct(value){
	if(value.length == 32){
		return value + value.substring(0,16);
	}else{
		return value;
	}	
}

function doSave(callback){
	
	$.ajax({
		url: '/KeyProfile/popup/setSecretKeyValue.do',
		type: 'POST',
		data: $('#form2').serialize(),
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				
				dialog_message("Success", response.message);				
				
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

function check_kcv(component, kcv){
	var value = $("#"+component).val();
	var keysize = $("#keySize").val();
			
	if(value.length === 0) return;
	
	if(keysize == 64){
		if(value.length % 16 != 0) return;
	}else if(keysize == 128){
		if(value.length % 32 != 0) return;
	}else if(keysize == 192){
		if(value.length % 48 != 0 && value.length % 32 != 0) return;
	}else if(keysize == 256){
		if(value.length % 64 != 0) return;
	}
	
	if(keysize == 192){
		value = reconstruct(value);
		$("#"+component).val(value);
	}
	
	doKCV(function postProcess(response){
		$("#"+kcv).val(response.kcv);
	}, value);
}

function doKCV(callback, component){
	
	$.ajax({
		url: '/KeyProfile/popup/getSecretKeyKCV.do',
		type: 'POST',
		data: {'keySubtype':$("#keySubtype").val(),'keySize':$("#keySize").val(), 'component':component},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				
				dialog_message("Success", response.message);				
				
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

var kcvFlag = 0;

//add by shifei 2014.6.5
function check_kcv_org(component, kcv){
	var value = $("#"+component).val();
	var keysize = $("#keySize").val();
	
	if(value.length === 0){
		dialog_message("Warning",  $.i18n.prop('secret_key_raw_key'));
		return;
	}else if(keysize == 64){
		if(value.length % 16 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_16'));
			return;
		}
	}else if(keysize == 128){
		if(value.length % 32 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_32'));
			return;
		}
	}else if(keysize == 192){
		if(value.length % 48 != 0 && value.length % 32 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_48'));
			return;
		}
		
	}else if(keysize == 256){
		if(value.length % 64 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_64'));
			return;
		}
	}
	
	if(keysize == 192){
		value = reconstruct(value);
		$("#"+component).val(value);
	}
	
	kcvFlag = 1;
	doKCV(function postProcess(response){
		$("#"+kcv).val(response.kcv);
	}, value);
}


//add by shifei 20140603
function autoGen(){
	
	var value = $("#org_key").val();
	var keysize = $("#keySize").val();
			
	if(value.length === 0){
		dialog_message("Warning",  $.i18n.prop('secret_key_raw_key'));
		return;
	}else if(keysize == 64){
		if(value.length % 16 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_16'));
			return;
		}
	}else if(keysize == 128){
		if(value.length % 32 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_32'));
			return;
		}
	}else if(keysize == 192){
		if(value.length % 48 != 0 && value.length % 32 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_48'));
			return;
		}
	}else if(keysize == 256){
		if(value.length % 64 != 0){
			dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_64'));
			return;
		}
	}
	
	if(kcvFlag == 0){
		dialog_message("Warning",  $.i18n.prop('raw_key_kcv_check'));
		return;
	}else {
		kcvFlag = 0;
	}
	
		autoGen_t(function postProcess(response){
			
			$('#component1').val(response.component1);
			$('#component2').val(response.component2);
			$('#component3').val(response.component3);
		
			$('#org_key').val(response.org_key);
			
		}, $('#keySize').val());	

	
	
//	if ($("#org_key").val() == null || $("#org_key").val() == "" || $("#org_key").val() ==" "){
//		dialog_message("Warning",  $.i18n.prop('secret_key_raw_key'));
//		return;
//	} else if ($("#org_key").val().length % 32 != 0){
//		dialog_message("Warning",  $.i18n.prop('secret_key_raw_key_32'));
//		return;
//	} else{
////		oddCheck(function postProcess(response){
////			
////			$('#org_key').val(response.org_key);
////			
////		
////		}, $('#org_key').val());
//		
//		autoGen_t(function postProcess(response){
//			
//			$('#component1').val(response.component1);
//			$('#component2').val(response.component2);
//			$('#component3').val(response.component3);
//		
//			$('#org_key').val(response.org_key);
//			
//		}, $('#keySize').val());	
//	}
	
	
	
}

function autoGen_t(callback, keySize){
	$.ajax({
		url: '/KeyProfile/popup/autoGenSecret.do',
		type: 'POST',
//		data: $('#form2').serialize(),
		data: {'keySize':keySize, 'keySubtype':$("#keySubtype").val(), 'org_key':$('#org_key').val()},
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

function component1_clear(){
	$("#component1").val("");
}

function component2_clear(){
	$("#component2").val("");
}

function component3_clear(){
	$("#component3").val("");
}

function org_key_clear(){
	$("#org_key").val("");
}
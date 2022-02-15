$(document).ready(function(){			
	validator_init();	
	
	$("input[name='importKeyType']:radio").change(function(){
		var type = $("input[name='importKeyType']:checked").val();
		
		if(type == 'PAIRKEY'){
			$("#public_key_import_section").show();
			$("#pubFile").attr('disabled', false);
			$("#pubFile").rules("add",{required:true, accept:"xml"});
			$("#pubFile").rules("add",{messages:{required:"file format 'xml' required."}});
			
			$("#secretKeyLabel").text("Import Private Key Profile");
		}else{
			$("#secretKeyLabel").text("Import Secret Key Profile");
			$("#public_key_import_section").hide();
			$("#pubFile").attr('disabled', true);
			
			$("#pubFile").rules("remove",{required:true, accept:"xml"});	
		}
	});
});

function validator_init(){
	$('#form2').validate({
		rules:{
			file:{required:true, accept:"xml"}
		},
		messages:{
			file:{required: "file format 'xml' required."}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		}
	});	
}

function doSubmit(){	
		
	$('#form2').submit();
}

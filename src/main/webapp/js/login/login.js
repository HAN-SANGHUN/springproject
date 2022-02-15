$(document).ready(function(){
	validator_init();
	
});

function validator_init(){
	$("#form").validate({
		rules:{
			j_username:{required:true},
			j_password:{required:true}
		},
		messages:{
			j_username:"ID is required",
			j_password:"Password is required",
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		},
		submitHandler:function(form){
			form.submit();
		}
	});
}

function dialog_message(title, message){
	
	$("#dialog-message p").text(message);
	
	$( "#dialog-message" ).dialog({
		modal: true,
		title: title,
		buttons: {
			Ok: function() {
				$("#dialog-message p").text("");
				$( this ).dialog( "close" );
			}
		}
    });
}
$("document").ready(function(){
	 validator_init();
	
});

function doSubmit(){
	$("#form2").submit();
}

function validator_init(){
	
	$("#form2").validate({
		rules:{
			certiFile:{required:true}
		},
		errorPlacement:function(error, element){
			error.insertAfter(element);
			error.css("color", "red");
			error.css("display", "block");
		}
	});
}
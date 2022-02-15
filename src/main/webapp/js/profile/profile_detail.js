

function download(){
	
	var token = new Date().getTime();
	$('#tokenID').val(token);
	
//	$('#id').val(profile.keyProfileID);
	
	
	$("#xmlFrm").submit();
}
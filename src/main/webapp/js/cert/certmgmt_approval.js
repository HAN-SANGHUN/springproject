$("document").ready(function(){
	 
});
 
function doApproval(){
	$.ajax({
		url: '/CertMgmt/popup/approvalCert.do',
		type: 'POST',
		data: $('#form1').serialize(),
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){ 
				alert(response.message);
				window.close();
			}else{
				alert(response.message);
			}
		},
		error: function(e){
			alert('Error' + e);
		}		
	});
}
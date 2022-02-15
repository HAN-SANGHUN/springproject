
function doSubmit(){	
	doSave(function postProcess(response){								
			
		window.opener.reloadGrid();			
	
		$('#status').text("Active");	
		
	});
}

function doSave(callback){

	$.ajax({
		url: '/TokenSetting/popup/activateToken.do',
		type: 'POST',
		data: $('#form2').serialize(),
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){			
				
				alert(response.message);				
				
				callback(response);				
				
			}else{
				alert(response.message);
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Error' +  errorThrown + ' ' + textStatus + ' ' + jqXHR);
		}
		
	});
}


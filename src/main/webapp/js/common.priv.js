comm.privilege_v = function(val){
	var value = val || '';
	var return_arr = [];
	if(value == ''){
		return;
	}
	var arr = value.split('');		//6자리 문자 --> 6개의 array
//	var _secure_domain = false;	
//	var _dap_verification = false;
//	var _delegated_management = false;
//	var _card_lock = false;
//	var _card_terminate = false;
//	var _card_reset = false;
//	var _cvm_management = false;
//	var _mandated_dap_verification = false;
//	var _trusted_path = false;
//	var _authorized_management = false;
//	var _token_management = false;	
//	var _global_delete = false;
//	var _global_lock = false;
//	var _global_registry = false;
//	var _final_application = false;
//	var _global_service = false;
//	var _receipt_generation = false;
//	var _ciphered_load_file_data_block = false;
//	var _contactless_activation = false;
//	var _contactless_self_activation = false;
	
	var byte1_1_arr = from_hexa_to_binary_arr(arr[0]);
	var byte1_2_arr = from_hexa_to_binary_arr(arr[1]);
	if(byte1_1_arr[0] == '1'){
		set_h('_secure_domain');
	}
	if(byte1_1_arr[0] == '1' && byte1_1_arr[1] == '1'){
		set_h('_dap_verification');
	}
	if(byte1_1_arr[0] == '1' && byte1_1_arr[2] == '1'){
		set_h('_delegated_management');
	}
	if(byte1_1_arr[3] == '1'){
		set_h('_card_lock');
	}
	if(byte1_2_arr[0] == '1'){
		set_h('_card_terminate');
	}
	if(byte1_2_arr[1] == '1'){
		set_h('_card_reset');
	}
	if(byte1_2_arr[2] == '1'){
		set_h('_cvm_management');
	}
	if(byte1_1_arr[0] == '1' && byte1_1_arr[1] == '1' && byte1_2_arr[3] == '1'){
		set_h('_mandated_dap_verification');
	}
	var byte_arr = from_hexa_to_binary_arr(arr[2]);
	if(byte_arr[0] == '1'){
		set_h('_trusted_path');
	}
	if(byte_arr[1] == '1'){
		set_h('_authorized_management');
	}
	if(byte_arr[2] == '1'){
		set_h('_token_management');
	}
	if(byte_arr[3] == '1'){
		set_h('_global_delete');
	}
	byte_arr = from_hexa_to_binary_arr(arr[3]);
	if(byte_arr[0] == '1'){
		set_h('_global_lock');
	}
	if(byte_arr[1] == '1'){
		set_h('_global_registry');
	}
	if(byte_arr[2] == '1'){
		set_h('_final_application');
	}
	if(byte_arr[3] == '1'){
		set_h('_global_service');
	}
	byte_arr = from_hexa_to_binary_arr(arr[4]);
	if(byte_arr[0] == '1'){
		set_h('_receipt_generation');
	}
	if(byte_arr[1] == '1'){
		set_h('_ciphered_load_file_data_block');
	}
	if(byte_arr[2] == '1'){
		set_h('_contactless_activation');
	}
	if(byte_arr[3] == '1'){
		set_h('_contactless_self_activation');
	}
	
	function from_hexa_to_binary_arr(str){
		var one = str;	//16진수 
		var two = parseInt(one, 16);	//10진수
		var three = (two).toString(2);	//2진수
		var four = (comm.LPAD(three, '0', 4)).split('');
		return four;
	}
	function set_h(_id){
		$('#' + _id).attr('style', 'padding-left:30px;');
		$('#' + _id).html('');
		$('#' + _id).html('<input type="checkbox" name="chbox" id="chbox" checked>');
	}
	
}
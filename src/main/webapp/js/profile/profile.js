$(document).ready(function(){
	
	calendar_init();
	
	$('#keyTypeCode').change(keyTypeOnChange);
	
	grid_init();	
	
	$(window).resize(function(){
		var width = jQuery('#main_search_box').width();
		jQuery("#jq_grid").setGridWidth(width-5);
	}); 
	
});

function calendar_init(){
	$( "#startDate" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$( "#endDate" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true
	});
	
	$("#startDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});
	
	$("#endDate").dblclick(function(){
		$.datepicker._clearDate(this);
		
	});	
}

function keyTypeOnChange(){
	var code = $('#keyTypeCode').val();
	var roptions = {
			'' : '-- All --',
			'RSA' : 'RSA',
			'RSACRT' : 'RSACRT',
			'EC' : 'EC'
	};
	var poptions = {
			'' : '-- All --',
			'DES' : 'DES',
			'SEED' : 'SEED',
			'AES' : 'AES'
	};
	
	if(code == 'SECRET'){
		
		$('#keySubtypeCode').empty();			
		
		$.each(poptions, function(key, value){
			$('#keySubtypeCode').append($("<option></option>").attr('value', key).text(value));
		});
		
	}else{
		
		$('#keySubtypeCode').empty();			
		
		$.each(roptions, function(key, value){
			$('#keySubtypeCode').append($("<option></option>").attr('value', key).text(value));
		});
	}
}

function grid_init(){
					
	jQuery("#jq_grid").jqGrid({
		url:'/KeyProfile/searchList.do',
		postData: {
			SYNC_YN: '',
			keyValueFlag: '',
			keyUsageIndicatorValue:'',
			companyID:function(){return $("#secCompanyID").val();},
			companyName: function(){return $('#companyName').val();},
			tokenLabel: function(){return $('#tokenLabel').val();},
//			profileID: function(){return $('#key_prof_id').val();},
//			profileName: function(){return $('#profileName').val();},
//			profileVersion: function(){return $('#profileVersion').val();},
			
			//modify by shifei
			keyProfileID: function(){return $('#keyProfileID').val();},
			keyProfileName: function(){return $('#keyProfileName').val();},
			keyProfileVersion: function(){return $('#keyProfileVersion').val();},
			
			keyLabel: function(){return $('#keyLabel').val();},
			keyTypeCode: function(){return $('#keyTypeCode').val();},
			keySubtypeCode: function(){return $('#keySubtypeCode').val();},
			activeStatusCode: function(){return $('#activeStatusCode').val();},
			periodType: function(){return $('#periodType').val();},
			startDate: function(){return $('#startDate').val();},
			endDate: function(){return $('#endDate').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company','Token Label','Profile ID','Profile Name','Version','Key Label','Key Type','Sub Type','Key Size', 'Key Usage','Key Role','Key Index','Key Version','Key Identifier','Key Definition','Start Date','End Date','Status','Registration Date','key Value Set', 'sendStatusCode', 'Sync with TSM'],
		colModel : [
		   {name:'companyName', index:'companyName', width:125,fixed:true}, 
		   {name:'tokenLabel', index:'tokenLabel', width:100,fixed:true},		   		   
		   {name:'keyProfileID', index:'keyProfileID', width:150,fixed:true},
		   {name:'keyProfileName', index:'keyProfileName',fixed:true},
		   {name:'keyProfileVersion', index:'keyProfileVersion', align:'center', width:100,fixed:true},
		   {name:'keyLabel', index:'keyLabel', width:400,fixed:true},
		   {name:'keyTypeCode', index:'keyTypeCode', align:'center', width:100, editable:true,fixed:true,
			   formatter:function(cellValue, options, rowObject){
			       if(cellValue == 'SECRET'){
			    	   if(rowObject.keyValueFlag === 'N'){
			    		   return "<img src='/img/main/icon_key_novalue2.png' style='cursor:pointer;' onClick='javascript:setSecretValue(\""+rowObject.keyProfileID+','+ rowObject.keyProfileVersion+','+ rowObject.keySubtypeCode+','+ rowObject.keySize+','+ rowObject.keyLabel+"\");' />";
			    	   }else{
			    		   return "<img src='/img/main/icon_key_symmetric.png' />";
			    	   }
					    
				   }else{
					   if(rowObject.keyValueFlag === 'N'){
						   return "<img src='/img/main/icon_key_novalue.png' alt='' style='cursor:pointer;' onClick='javascript:setRSAValue(\""+rowObject.keySubject+","+rowObject.keyTypeCode+','+rowObject.keySubtypeCode+','+ rowObject.keySize+','+ rowObject.keyLabel+"\");'/>";
					   }else{
			    		   return "<img src='/img/main/icon_key_asymmetric.png' />";

					   }
				   }
			   }
		   },
		   {name:'keySubtypeCode', index:'keySubtypeCode', align:'center', width:100,fixed:true},
		   {name:'keySize', index:'keySize',width:100,fixed:true},
		   {name:'keyUsageIndicatorValue', index:'keyUsageIndicatorValue', hidden:true, width:150,fixed:true},
		   {name:'keyRoleCode', index:'keyRoleCode', hidden:true,width:100,fixed:true},
		   {name:'keyIndex', index:'keyIndex', hidden:true,width:100, editable:true,fixed:true,
			   formatter:function(cellValue, options, rowObject){
			       if(cellValue == '0'){
			    	  return "";
					    
				   }else{
					   return cellValue;
				   }
			   }
		   },
		   {name:'keyVersion', index:'keyVersion', hidden:true,width:100,fixed:true},
		   {name:'keyIdentifier', index:'keyIdentifier', hidden:true,width:100,fixed:true},
		   {name:'keyDefinition', index:'keyDefinition', hidden:true,width:150,fixed:true},
		   {name:'effectiveStartDateStr', index:'effectiveStartDateStr', hidden:true,width:100,fixed:true},
		   {name:'effectiveEndDateStr', index:'effectiveEndDateStr', hidden:true,width:100,fixed:true},
		   {name:'activeStatusCode', index:'activeStatusCode', width:100,fixed:true},
		   {name:'registrationDateStr', index:'registrationDate', width:150,fixed:true},
		   {name:'keyValueFlag', index:'keyValueFlag', hidden:true, width:100,fixed:true},
		   {name:'sendStatusCode', index:'sendStatusCode', hidden:true, width:100,fixed:true},
		   {name:'SYNC_YN',index:'SYNC_YN', width:100, fixed:true, align:'center', formatter : function(cellvalue, options, rowObject){
			   if(rowObject.sendStatusCode == 'Y'){
		   			 return "Synchronized";
		   		 }else{
		   			 return '<a href="javascript:void(0);" onclick="sendKeyProfile(\''+rowObject.keyProfileID+'\',\''+ rowObject.keyProfileVersion+'\',\''+ rowObject.keyValueFlag+'\')" title="Click to synchronize with TSM." ><img src="../img/common/btn_sync.png" alt="sync"/></a>';
		   		 }
		   	 }}

		],				
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',
		sortname:'registrationDate',
		viewrecords:true,
		sortable:true,
		loadonce:false,
		sortorder:"desc",
		height:"100%",
		emptyrecords: "Empty Data!",		
		jsonReader : {
			root: "rows",
			page: "page",
			total: "total",
			records: "records",
			repeatitems:false,
			cell: "cell",
			id: "id"
		},
		multiselect:true,
		multiboxonly:true,
		ondblClickRow: function(rowid){
						
			var rowData = jQuery(this).getRowData(rowid);
			var profileID = rowData['keyProfileID'];
			var profileVersion = rowData['keyProfileVersion'];
			
			doDetail(profileID, profileVersion);
		},
		cmTemplate:{title:false}
	});
	
	//�섎떒 �ㅻ퉬寃뚯씠���ㅼ젙
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    
	jQuery("#jq_grid").jqGrid('navButtonAdd','#pager',{
	     caption: "Columns",
	     //title: "Reorder Columns",
	     onClickButton : function (){
	         jQuery("#jq_grid").jqGrid('columnChooser', {
	        	 done : function(){
	        		 var width = jQuery('#main_search_box').width();
	        		 jQuery("#jq_grid").setGridWidth(width);
	        	 }
	         });
	     }
	});
    
	
}

function sendKeyProfile(keyProfileID, keyProfileVersion, keyValueFlag){
	$.ajax({
		type: "POST",
		data : { keyProfileID : keyProfileID, keyProfileVersion: keyProfileVersion, keyValueFlag: keyValueFlag},				
		url: "/KeyProfile/sendKeyProfile.do",
		dataType: "json",
		async: true,
		success: function(response){
			reloadGrid();
		},
		error : function(e){
			reloadGrid();
		}

	});
	
}

function resizeGrid(width){
	$('#jq_grid').setGridWidth(width);
}

function reloadGrid(){
	$("#jq_grid").trigger("reloadGrid");
}

function setSecretValue(data){
		
	var values = data.split(",");
	
	var profileID = values[0], profileVersion=values[1], keySubtypeCode=values[2], keySize=values[3], keyLabel=values[4];
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/KeyProfile/popup/secretKeyValue.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileID';
	input.value= profileID;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileVersion';
	input.value= profileVersion;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keySubtypeCode';
	input.value= keySubtypeCode;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keySize';
	input.value= keySize;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyLabel';
	input.value= keyLabel;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}

function setRSAValue(data){
	var values = data.split(",");
	
	var keySubject = values[0],keyTypeCode=values[1], keySubtypeCode=values[2], keySize=values[3], keyLabel=values[4];
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/KeyProfile/popup/pairKeyValue.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keySubject';
	input.value= keySubject;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyTypeCode';
	input.value= keyTypeCode;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keySubtypeCode';
	input.value= keySubtypeCode;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keySize';
	input.value= keySize;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyLabel';
	input.value= keyLabel;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}


function doDetail(profileID, profileVersion){
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/KeyProfile/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileID';
	input.value= profileID;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileVersion';
	input.value= profileVersion;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}

function doClear(){
	$("input").val("");
	$("select").val("");
}

function doSearch(){
	reloadGrid();
}

function doGenerate(){	
	var linkhref="/KeyProfile/popup/generate.do";
	window.open(linkhref,"Generate","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message('Edit',$.i18n.prop('signle_row_select'));
		return;
	}
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	
	if(rowData.keyValueFlag == 'Y'){
		dialog_message('Edit',$.i18n.prop('key_value_set_not_editable'));
		return;
	}
	
	if(rowData.activeStatusCode == 'Inactive'){
		dialog_message('Export',$.i18n.prop('key_profile_not_active'));
		return;
	}
	
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/KeyProfile/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileID';
	input.value= rowData.keyProfileID;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileVersion';
	input.value= rowData.keyProfileVersion;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);
}

function doImport(){
	var linkhref="/KeyProfile/popup/import.do";
	 window.open(linkhref,"Import","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
}

function doExport(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
		
	if(sel == null || sel.length != 1){
		dialog_message('Export',$.i18n.prop('signle_row_select'));
		return;
	}
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	
	if(rowData.keyValueFlag == 'N'){
		dialog_message('Export',$.i18n.prop('key_value_not_set'));
		return;
	}
	
	if(rowData.activeStatusCode == 'Inactive'){
		dialog_message('Export',$.i18n.prop('key_profile_not_active'));
		return;
	}
	
	if(rowData.keyUsageIndicatorValue.charAt(10) != '0'){
		dialog_message('Export',$.i18n.prop('key_value_not_exportable'));
		return;
	}
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/KeyProfile/popup/export.do");
	form.setAttribute("target", "Export");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileID';
	input.value= rowData.keyProfileID;
	form.appendChild(input);
	
	input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'keyProfileVersion';
	input.value= rowData.keyProfileVersion;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";
	window.open(linkhref,"Export","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=900,top=100,left=200");
	
	form.submit();
	
	document.body.removeChild(form);	
}

function doDelete(){
	
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length == 0){
		dialog_message("Delete", $.i18n.prop('multi_row_select'));
		return;
	}
	
	for(var i=0; i < sel.length; i++){		
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		if(rowData['activeStatusCode'] == 'Inactive'){
			dialog_message("Delete", $.i18n.prop('key_profile_deleted',rowData['keyProfileID']));
			return;
		}			
	}
	
	dialog_confirm("Confirm", $.i18n.prop('key_profile_delete_confirm'), doDeleteCallback);	
}

function doDeleteCallback(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	var profileIDs = [];
	for(var i=0; i < sel.length; i++){
		var rowData = jQuery('#jq_grid').getRowData(sel[i]);
		profileIDs[i] = rowData['keyProfileID'] + '-'+ rowData['keyProfileVersion'];
	}
	
	doDeleteProfiles(function postProcess(flag){
		if(flag){
			reloadGrid();
		}
	}, profileIDs);
}


function doDeleteProfiles(callback, profileIDs){
	$.ajax({
		url: '/KeyProfile/deleteProfile.do',
		type: 'POST',
		data: {'profileID':profileIDs.join(',')},
		dataType: 'json',
		success: function(response){
			if(response.status == "SUCCESS"){
				
				dialog_message("Success", response.message);				
				
				callback(true);
				
			}else{
				dialog_message("Failure", response.message);
			}
		},
		error: function(e){
			dialog_message("Error", e);
		}		
	});
}

function doExcel(){
	$('#sortorder').val( $('#jq_grid').jqGrid('getGridParam', 'sortorder'));
	$('#sortname').val($('#jq_grid').jqGrid('getGridParam', 'sortname'));
	
	$('#form1').submit();
}


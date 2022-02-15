

$(document).ready(function(){			
	$('#tabs').tabs();
});

function doSubmit(){
	
	if($('#sensitive').is(':checked')){
		if($('#tranProfileID').val() == "" || $('#tranProfileID') == null){
			
			dialog_message("Info", $.i18n.prop("key_profile_sensitive"));
			return false;
		}
	}
	
	$('#form2').submit();
	
	
//	doSave(function postProcess(response){
//		if(response.status == "SUCCESS"){		
//			
//			
//		}
//	});
}

function doSave(callback){
	
	$.ajax({
		url: '/KeyProfile/popup/exportProfile.do',
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
		error: function(e){
			alert('Error' + e);
		}
		
	});
}

var gridInit = false;

function findTransportKey(){
	$('#pop-main').height('98%');
	if(gridInit){

		reloadGrid();
		
		if($('#main-con-content').css('display') == 'none'){
			$('#main-con-content').show(500);
		}
	}else{
		grid_init();
		$('#main-con-content').show(500);
		gridInit = true;
	}	
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function grid_init(){
	$("#jq_grid").jqGrid({
		url:'/KeyProfile/searchList.do',
		postData: {
			testMode: 'PRODUCTION',
			keyValueFlag: 'Y',
			activeStatusCode: 'Active',
			keyUsageIndicatorValue: '0',
			keyRoleCode: 'Transport Key',
			companyName: function(){return $('#searchName').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['Company','Key Label','Key Type','Sub Type','Profile ID', 'Profile Version','Profile Name','Status'],
		colModel : [
		   
		   {name:'companyName', index:'companyName'}, 
		   {name:'keyLabel', index:'keyLabel'},
		   {name:'keyTypeCode', index:'keyTypeCode', align:'center', editable:true, width:110,
			   formatter:function(cellValue, options, rowObject){
			       if(cellValue == 'SECRET'){
			    	   if(rowObject.keyValueFlag === 'N'){
			    		   return "<img src='/img/main/icon_key_novalue2.png' />";
			    	   }else{
			    		   return "<img src='/img/main/icon_key_symmetric.png' />";
			    	   }
					    
				   }else{
					   if(rowObject.keyValueFlag === 'N'){
						   return "<img src='/img/main/icon_key_novalue.png' />";
					   }else{
			    		   return "<img src='/img/main/icon_key_asymmetric.png' />";

					   }
				   }
			   }
		   },
		   {name:'keySubtypeCode', index:'keySubtypeCode', align:'center', width:110},
		   {name:'keyProfileID', index:'keyProfileID', hidden:true},
		   {name:'keyProfileVersion', index:'keyProfileVersion', hidden:true},
		   {name:'keyProfileName', index:'keyProfileName'},
		   {name:'activeStatusCode', index:'activeStatusCode', align:'center', width:100}

		],				
		rowNum:5,
		autoWidth:true,
		shrinktofit:true,
		rowList:[5],
		pager:'#pager',
		sortname:'keyProfileID',
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
		ondblClickRow: function(rowid){
						
			var rowData = jQuery(this).getRowData(rowid);
			var profileID = rowData['keyProfileID'];
			var profileVersion = rowData['keyProfileVersion'];
			var keyLabel = rowData['keyLabel'];
			
			doProcess(keyLabel, profileID, profileVersion);
		}	
	});
		
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
}

function doProcess(keyLabel, profileID, profileVersion){
//	alert(profileID +":"+profileVersion);
	$('#searchName').val(keyLabel);
	$('#tranProfileID').val(profileID);
	$('#tranProfileVersion').val(profileVersion);
	
	$('#main-con-content').hide(500);
	
	pop_main_85();
}

function cancelSearch(){
	$('#searchName').val("");
	$('#tranProfileID').val("");
	$('#tranProfileVersion').val("");
	
	$('#main-con-content').hide(500);
	
	pop_main_85();
}

function pop_main_85(){
	$('#pop-main').height('85%');
}

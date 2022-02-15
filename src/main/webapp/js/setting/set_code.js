var set_com_v = {};


$(document).ready(function(){
	
});

function set_code_list_init(){
	jQuery("#company_grid").jqGrid({
	   	url:'/Setting/SetCodeListSelect.do',
		datatype: "json",
		colNames : comm.lang.code_grid_col,
	   	colModel:[
			  {name:'CODE_DMAIN_CD',index:'CODE_DMAIN_CD', width:120},
			  {name:'CODE_DMAIN_NAME',index:'CODE_DMAIN_NAME', width:120},
			  {name:'DESCR',index:'DESCR', width:120},
			  {name:'DMAIN_TYPE_NAME',index:'DMAIN_TYPE_NAME', width:120},
			  {name:'DMAIN_TYPE_CD',index:'DMAIN_TYPE_CD', width:120},
			  {name:'CODE_TABLE_NAME',index:'CODE_TABLE_NAME', width:120},
			  {name:'CODE_VAL_MKING_TYPE_NAME',index:'CODE_VAL_MKING_TYPE_NAME', width:120},
			  {name:'CODE_VAL_MKING_TYPE_CD',index:'CODE_VAL_MKING_TYPE_CD', width:120},
			  {name:'ORG_NAME',index:'ORG_NAME', width:120},
			  {name:'PARNT_CODE_DMAIN_NAME',index:'PARNT_CODE_DMAIN_NAME', width:120},
			  {name:'PARNT_CODE_DMAIN_CD',index:'PARNT_CODE_DMAIN_CD', width:120},
			  {name:'DATA_TYPE_NAME',index:'DATA_TYPE_NAME', width:120},
			  {name:'DATA_TYPE_CD',index:'DATA_TYPE_CD', width:120},
			  {name:'EFFT_START_DT',index:'EFFT_START_DT', width:120},
			  {name:'EFFT_END_DT',index:'EFFT_END_DT', width:120}
	   	],
	   	rowNum:10,
	   	//width : 963,
	   	autoWidth:true,
	   	rowList:[10,20,30],
	   	pager: '#company_page',
	   	sortname: 'CODE_DMAIN_CD',
	    viewrecords: true,
	    sortorder: "asc",
	    //height : 318,
	    height: "100%",
	    jsonReader : { repeatitems: false },
	    multiselect:true,
	    ondblClickRow: function(rowId) {
	        var rowData = jQuery(this).getRowData(rowId); 
	        var codeDomainCode = rowData['CODE_DMAIN_CD'];
	        var codeDomainName = rowData['CODE_DMAIN_NAME'];
	        
	        doDetail(codeDomainName, codeDomainCode);
	    },

	});
	
	//하단 네비게이션 설정
	jQuery("#company_grid").jqGrid('navGrid','#company_page',{edit:false,add:false,del:false,search:false});
	
	//그리드 리사이징 설정
	comm.resizeJqGridWidth('company_grid','main_search_box', 963 );
	
	//컬럼을 동적으로 추가 삭제 할 수 있는 기능  에러 발생하니 수정하여 사용
	jQuery("#company_grid").jqGrid('navButtonAdd','#company_page',{
	     caption: "Columns",
	     //title: "Reorder Columns",
	     onClickButton : function (){
	         jQuery("#company_grid").jqGrid('columnChooser', {
	        	 done : function(){
	        		 comm.resizeJqGridWidth('company_grid','main_search_box', 963 );
	        	 }
	         });
	     }
	});
	
	$( "#txtEffectiveStart" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd'
	});
	
	$( "#txtEffectiveEnd" ).datepicker({
		showOn : 'button',
		buttonImage : '/img/common/mini_cal.gif',
		buttonImageOnly : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd'
	});
};


function road_code_detail_list(){
	jQuery("#code_detail_table").jqGrid({
	   	url:'/Setting/SetCodeDetail.do?domainCode=' + $('#domainCode').val() + '&domainName=' + $('#domainName').val(),
		datatype: "json",
		colNames : comm.lang.code_detail_grid_col,
	   	colModel:[
				  {name:'CD',index:'CD', width:100},
				  {name:'CODE_NAME',index:'CODE_NAME', width:120},
				  {name:'CODE_ETC_NAME',index:'CODE_ETC_NAME', width:120},
				  {name:'DISP_SEQ',index:'DISP_SEQ', width:120},
				  {name:'PARNT_CD',index:'PARNT_CD', width:120},
				  {name:'PARNT_CD_NAME',index:'PARNT_CD_NAME', width:120},
				  {name:'DESCR',index:'DESCR', width:120},
				  {name:'EFFT_START_DT',index:'EFFT_START_DT', width:120},
				  {name:'EFFT_END_DT',index:'EFFT_END_DT', width:120},
				  
		          {name:'REG_USER_ID',index:'REG_USER_ID', hidden: true},
		          {name:'REG_TMPST',index:'REG_TMPST', hidden: true},
		          {name:'UPD_USER_ID',index:'UPD_USER_ID', hidden: true}
	   	],
	   	rowNum:-1,
//	   	width : 800,
	   	autoWidth:true,
//	   	shrinkToFit : true,
	    viewrecords: true,
	    //height : 318,
	    height: "100%",
	    jsonReader : { repeatitems: false },
	    gridComplete : function(){
	    	if($(this).getGridParam('records') == 0){
	    		var b = '<td colspan="'+$(this).find('tr').eq(0).find('td').length
	    			+'" role="gridcell" title="" aria-describedby="" style="text-align: center;">No Data</td>';
	    		
	    		$(this).find('tr').eq(0).after(b);
	    	}
	    }
	});
	comm.resizeJqGridWidth('code_detail_table','tmppp', 800, true );	
	
}


function road_code_edit_list(rows){
	
//	var z = '';
//	for(var i=0; i<rows.length ; i++){
//		var y = rows[i];
//		if(i == (rows.length-1)){
//			z += y.CD + ':' + y.DISP_SEQ;
//		}else{
//			z += y.CD + ':' + y.DISP_SEQ + ';';
//		}
//	}
//	set_com_v['DISP_SEQ'] = z;
	
	
	jQuery("#code_edit_table").jqGrid({
		url:'/Setting/SetCodeEdit.do?domainCode=' + $('#domainCode').val() + '&domainName=' + $('#domainName').val(),
		datatype: "json",
		colNames : comm.lang.code_detail_grid_col,
		colModel:[
		          {name:'CD',index:'CD', width:100, editable: true},
		          {name:'CODE_NAME',index:'CODE_NAME', width:120, editable: true},
		          {name:'CODE_ETC_NAME',index:'CODE_ETC_NAME', width:120, editable: true},
			   	  //{name:'DISP_SEQ',index:'DISP_SEQ', width:120, editable: true, formatter:'select', edittype:"select", editoptions:{value:set_com_v['DISP_SEQ']}},
			   	  {name:'DISP_SEQ',index:'DISP_SEQ', width:120, editable: true},
		          {name:'PARNT_CD',index:'PARNT_CD', width:120, editable: true},
		          {name:'PARNT_CD_NAME',index:'PARNT_CD_NAME', width:120, editable: true},
		          {name:'DESCR',index:'DESCR', width:120, editable: true},
		          {name:'EFFT_START_DT',index:'EFFT_START_DT', width:100, editable: true, editoptions : {
			   			size : 25,
			   			dataInit : function(obj){
			   				$( obj ).datepicker({
			   					showOn : 'button',
			   					buttonImage : '/img/common/mini_cal.gif',
			   					buttonText : 'calendar',
			   					buttonImageOnly : true,
			   					changeMonth : true,
			   					changeYear : true,
			   					dateFormat : 'yy-mm-dd'
			   				});
			   			}
		          	}
			   	  },
		          {name:'EFFT_END_DT',index:'EFFT_END_DT', width:100, editable: true},
		          
		          {name:'REG_USER_ID',index:'REG_USER_ID', hidden: true},
		          {name:'REG_TMPST',index:'REG_TMPST', hidden: true},
		          {name:'UPD_USER_ID',index:'UPD_USER_ID', hidden: true}
		          
		        ],
		        rowNum:-1,
			   	autoWidth:true,
			    viewrecords: true,
			    //height : 318,
			    height: "100%",
			    jsonReader : { repeatitems: false },
			    cellEdit: true,
			    editUrl : 'editurl',
			    cellsubmit : 'clientArray',
			    //loadonce:true, 
			    multiselect:true,
//			    beforeEditCell : function(rowid){
//			    	$("#code_edit_table").editRow(rowid);
//			    },
			    gridComplete : function(){
			    	if($(this).getGridParam('records') == 0){
			    		var b = '<td colspan="'+$(this).find('tr').eq(0).find('td').length
			    			+'" role="gridcell" title="" aria-describedby="" style="text-align: center;">No Data</td>';
			    		
			    		$(this).find('tr').eq(0).after(b);
			    	}
			    }
	}
	
	);
	
	
	comm.resizeJqGridWidth('code_edit_table','tmppp', 800, true );	
	
}


function doSelect(){
	var a = $('[name=form1]').serialize();
	var _url = '/Setting/SetCodeListSelect.do' + '?' + a;
	jQuery("#company_grid").jqGrid('setGridParam',{url:_url}).trigger("reloadGrid");
}

function doDetail(codeDomainName, codeDomainCode){

	var _code_id = codeDomainCode;
	if(!_code_id){
		_code_id = $('[name=CODEDOMAINCD]').val();
	}
	var linkhref = "/Setting/SetCodeDetailOpen.do?domainCode=" + _code_id + "&domainName=" + codeDomainName;
    window.open(linkhref,"select","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
}


function doEdit(){
	var sel = $("#company_grid").getGridParam('selarrrow');
	var dCode = [];
	var dName = [];
	if(sel == null || sel.length == 0){
		alert(comm.lang.no_sel_data_msg);
		return false;
	}
	for(var i=0; i<sel.length ; i++){
		var a = $("#company_grid").getRowData(sel[i]);
		dCode[i] = a.CODE_DMAIN_CD;
		dName[i] = a.CODE_DMAIN_NAME;
	}    
	
    var codeDomainCode = dCode[0];
    var codeDomainName = dName[0];
    
	var _code_id = codeDomainCode;
	if(!_code_id){
		_code_id = $('[name=CODEDOMAINCD]').val();
	}
	var linkhref = "/Setting/SetCodeEditOpen.do?domainCode=" + _code_id + "&domainName=" + codeDomainName;
    window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
	
}

function popitup(url) {
	newwindow=window.open(url,'name','height=200,width=150');
	if (window.focus) {newwindow.focus()}
	return false;
}
	
	
function doCodeValueUpdate(){
	
	var rowIds = $("#code_edit_table").jqGrid('getDataIDs');   
	if(rowIds){   
		
		var rowCount = rowIds.length;
		if(rowCount <= 1)
		{
			return;
		}
		
		// Duplicated Code Number.
		for(var n = 0; n < rowCount ; n++) {  
			var orgRowData = $("#code_edit_table").jqGrid('getRowData', rowIds[n]);   
			
			for(var i = n + 1; i < rowCount; i++) {   
				var curRowData = $("#code_edit_table").jqGrid('getRowData', rowIds[i]);  
				
				if(orgRowData['CD'] == null || orgRowData['CD'] == "")
				{
					alert("Code can't be null.");
					return;
				}
				
				if(orgRowData['CD'] == curRowData['CD']){  
					alert("Duplicated Code.");
					return;
				}
				
				if(orgRowData['DISP_SEQ'] != "" && curRowData['DISP_SEQ'] != "")
				{					
					if(orgRowData['DISP_SEQ'] == curRowData['DISP_SEQ']){  
						alert("Duplicated Sequence Number.");
						return;					
					}   
				}
			}   
		}
		
	} 
	
	var data = {};
	
	data['rows'] = JSON.stringify($('#code_edit_table').getRowData());

	$.ajax({
		type: "POST",
		data : data,
		url: "/Setting/SetCodeValueUpdate.do?domainCode=" + $('#domainCode').val(),
		dataType: "json",
		async: true,
		success: function(jsonData){
			var action = jsonData.action;
			if(action == 'success'){
				alert(comm.lang.save_success);

			}else{
				alert(comm.lang.save_fail);
				console.error(jsonData.message);
			}
		},
		error : function(jsonData, two, three, four){
			alert(comm.lang.save_fail);
		}
	});
}



function doDelete(){

	var sel = $("#company_grid").getGridParam('selarrrow');
	var dCode = [];

	if(sel == null || sel.length == 0){
		alert(comm.lang.no_sel_data_msg);
		return false;
	}
	
	var cd = "";
	for(var i = 0; i < sel.length ; i++)
	{
		var a = $("#company_grid").getRowData(sel[i]);
		dCode[i] = a.CODE_DMAIN_CD;
		
		if(i == 0)
		{
			cd = a.IMAGEID
		}else
		{
			cd = cd + ', ' + a.IMAGEID
		}
		  
	} 
	
	 if(!confirm("Delete " + cd + " Code Information?"))
	 {
		 return;
	 }
	
	$.ajax({
		type: "POST",
		data: { CODE_DMAIN_CD : dCode.join(',') },
		url: "/Setting/SetCodeDelete.do",
		dataType: "json",
		async: true,
		success: function(jsonData){
			var action = jsonData.action;
			if(action == 'success'){
				alert(comm.lang.del_suc_msg);
				doSelect();
			}else{
				alert(comm.lang.del_fail_two_msg);
			}
		}
	});	

}

//function prepareSeqList()
//{
//	
//}
//
function getMaxValueInSeqList()
{
	var maxSeq = 0;
	var orgRowData;
	var rowIds = $("#code_edit_table").jqGrid('getDataIDs');   
	if(rowIds){   
		
		var rowCount = rowIds.length;
		if(rowCount <= 1)
		{
			if(rowCount = 1)
			{
				orgRowData = $("#code_edit_table").jqGrid('getRowData', rowIds[0]);
				maxSeq = orgRowData['DISP_SEQ'];
			}
			return maxSeq;
		}
		
		// Duplicated Code Number.
		for(var n = 0; n < rowCount ; n++) {  
			orgRowData = $("#code_edit_table").jqGrid('getRowData', rowIds[n]);   
			
			if(parseInt(orgRowData['DISP_SEQ']) > parseInt(maxSeq))
			{
				maxSeq = parseInt(orgRowData['DISP_SEQ']);
			}
		}
		
	} 
	
	if(parseInt(maxSeq) < rowCount)
	{
		maxSeq = rowCount;
	}
	
	return maxSeq;
	
}


function addEditGridRow(){
	var ids = $("#code_edit_table").getGridParam('records');
	
	var maxSeq = getMaxValueInSeqList();
	maxSeq++;
	
	jQuery("#code_edit_table").addRowData
	(
			ids + 1 + '', 
			{
				CD : '',
				CODE_ETC_NAME : '',
				//DISP_SEQ :set_com_v['DISP_SEQ'],
				DISP_SEQ :maxSeq,
				PARNT_CD : '',
				PARNT_CD_NAME : '',
				DESCR : '',
				EFFT_START_DT : '',
				EFFT_END_DT : '9999-12-31'
			}
	);
}


function delEditGridRow(){
	var sel = $('#code_edit_table').getGridParam('selarrrow');
	
	if(sel.length == 0){
		alert("Please select Data!");
	}else{
		while(sel.length >0 ){
			jQuery("#code_edit_table").delRowData(sel[0] + '');
		}
	}
	
}



$(document).ready(function(){
		
	$('#pop-main').height('80%');
	
	$('#slotStatusCode').change(function(){
		reloadGrid();
	});
	
	grid_init();
});

function grid_init(){
	
	$("#jq_grid").jqGrid({
		url:'/TokenSetting/popup/slotList.do',
		postData: {
			hsmNo:function(){return $('#hsmNo').val();},
			slotStatusCode: function(){return $('#slotStatusCode').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}			
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['HSM No.','HSM Label','Token No','Slot Index', 'Status'],
		colModel : [
		   {name:'hsmNo', index:'hsmNo'}, 
		   {name:'hsmLabel', index:'hsmLabel'}, 
		   {name:'tokenNo', index:'tokenNo'}, 
		   {name:'slotNo', index:'slotNo'},
		   {name:'slotStatusCode', index:'slotStatusCode', width:175, editable:true,
			   formatter:function(cellValue, options, rowObject){
		    	   if(rowObject.slotStatusCode === 'Active'){
		    		   return "Initialized";
		    	   }else{
		    		   return "<font color='red'>Uninitialized</font>";
		    	   }
					
			   }
		   }
		],				
		rowNum:10,
		autoWidth:true,
		shrinktofit:true,
		rowList:[10],
		pager:'#pager',
		sortname:'slotNo',
		viewrecords:true,
		sortable:true,
		loadonce:false,
		sortorder:"asc",
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
		}
	});
		
    jQuery("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
	
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");	
}
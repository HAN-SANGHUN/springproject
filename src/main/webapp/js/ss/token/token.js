
var left = (screen.width/2)-(900/2);
var top = (screen.height/2)-(600/2) + 100;

$(document).ready(function(){	
	grid_init();
	
	$(window).resize(function(){
		var width = jQuery('#main_search_box').width();
		jQuery("#jq_grid").setGridWidth(width-5);
	}); 
});

function grid_init(){
				
	$("#jq_grid").jqGrid({
		url:'/TokenSetting/searchList.do',
		postData: {
			tokenLabel: function(){return $('#tokenLabel').val();},
			companyName: function(){return $('#companyName').val();},
			sortname:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortname');},
			sortorder:function(){return $('#jq_grid').jqGrid('getGridParam', 'sortorder');},
			rowSize:function(){return $('#jq_grid').jqGrid('getGridParam', 'rowNum');}
		},
		datatype:'json',
		mtype: 'POST',
		colNames : ['HSM Label', 'Slot No.', 'Slot Index','Token Label', 'Company Name','Reg.Date'],
		colModel : [
		   {name:'hsmLabel', index:'hsmLabel'},
		   {name:'slotNo', index:'slotNo'},
		   {name:'tokenNo', index:'tokenNo', hidden:true},
		   {name:'tokenLabel', index:'tokenLabel'},
		   {name:'companyName', index:'companyName'},
		   {name:'registrationDateStr', index:'registrationDate'}
		],	
		rowNum:10,
		autowidth:true,
		shrinktofit:false,
		rowList:[10,20,30],
		pager:'#pager',		
		viewrecords:true,
		sortable:true,
		loadonce:false,
		sortname:'tokenNo',
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
		},
		multiselect:true,
		ondblClickRow:function(rowId){
			var rowData = jQuery(this).getRowData(rowId);
			var tokenId = rowData['tokenNo'];
			
			doDetail(tokenId);
		},
		cmTemplate:{title:false}
	});
		
    $("#jq_grid").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false,view:false,refresh:true});
    
}

function reloadGrid(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function doDetail(tokenNo){
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/TokenSetting/popup/detail.do");
	form.setAttribute("target", "Detail");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'tokenNo';
	input.value= tokenNo;
	form.appendChild(input);
	
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Detail","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top="+top+",left="+left);
	
	form.submit();
	
	document.body.removeChild(form);
}

function doClear(){
	$("input").val("");
}

function doSearch(){
	jQuery("#jq_grid").trigger("reloadGrid");
}

function doGenerate(){
	var linkhref="/TokenSetting/popup/generate.do";
	window.open(linkhref,"Generate","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=800,top=100,left=200");
}

function doEdit(){
	var sel = $("#jq_grid").getGridParam('selarrrow');
	
	if(sel == null || sel.length != 1){
		dialog_message('Edit',$.i18n.prop('signle_row_select'));
		return;
	}	
	
	var rowData = jQuery('#jq_grid').getRowData(sel[0]);
	var tokenNo = rowData['tokenNo'];
	    
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/TokenSetting/popup/edit.do");
	form.setAttribute("target", "Edit");
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'tokenNo';
	input.value= tokenNo;
	form.appendChild(input);
		
	document.body.appendChild(form);
	
	var linkhref="";	
    window.open(linkhref,"Edit","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top="+top+",left="+left);

    form.submit();
	
	document.body.removeChild(form);
}

function doDelete(){
	
}

function doExcel(){
	
}
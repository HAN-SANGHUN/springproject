$(document).ready(function(){
	jQuery("#image_grid").jqGrid({
	   	url:'/Setting/SetImageListSelect.do',
		datatype: "json",
		//colNames:['No','Com Type', 'Addr', 'Biz Lic No','Cntry Cd','Com Id','Com Name', 'Email', 'Fax', 'Hp Url', 'Msg Send Url', 'Ofc Tel No'],
		colNames : comm.lang.image_grid_col,
	   	colModel:[
	   		{name:'IMAGEID',index:'IMAGEID', width:120},
	   		{name:'IMGFILENAME',index:'IMGFILENAME', width:120},
	   		{name:'IMG',index:'IMG', width:120,edittype:"image",align:"center",formatter:function(cellvalue, options, rowObject, four){
	   			var IMG_ID = rowObject['IMAGEID'];
	   			return '<img src="/Setting/SetImagePic.do?IMG_ID='+IMG_ID+'" alt="sample" width="80px" height="40px">'
	   		}},
	   		{name:'CODE_NAME',index:'CODE_NAME', width:120},
	   		{name:'XSIZE',index:'XSIZE', width:120},
	   		{name:'YSIZE',index:'YSIZE', width:120}
	   	],
	   	rowNum:10,
	   	autoWidth:true,
	   	rowList:[10,20,30],
	   	pager: '#image_page',
	   	sortname: 'FILETYPECD',
	    viewrecords: true,
	    sortorder: "asc",
	    height: "100%",
	    jsonReader : { repeatitems: false },
	    multiselect:true,
	    ondblClickRow: function(rowId) {
	        var rowData = jQuery(this).getRowData(rowId); 
	        var IMG_ID = rowData['IMAGEID'];
	        doDetail('role', IMG_ID);
	    },
	});
	
	//하단 네비게이션 설정
	jQuery("#image_grid").jqGrid('navGrid','#image_page',{edit:false,add:false,del:false,search:false});
	
	//그리드 리사이징 설정
	comm.resizeJqGridWidth('image_grid','main_search_box', 963 );
	
	//컬럼을 동적으로 추가 삭제 할 수 있는 기능  에러 발생하니 수정하여 사용
	jQuery("#image_grid").jqGrid('navButtonAdd','#image_page',{
	     caption: "Columns",
	     //title: "Reorder Columns",
	     onClickButton : function (){
	         jQuery("#image_grid").jqGrid('columnChooser', {
	        	 done : function(){
	        		 comm.resizeJqGridWidth('image_grid','main_search_box', 963 );
	        	 }
	         });
	     }
	});
	comm.setSelectNode('FILE_TYPE_CD', 'FILE_TYPE_CD', 'File Type');
});


function doSelect(){
	var a = $('[name=form1]').serialize();
	var _url = '/Setting/SetImageListSelect.do' + '?' + a;
	jQuery("#image_grid").jqGrid('setGridParam',{url:_url}).trigger("reloadGrid");
}

function doDetail(typ, IMG_ID){
	var _img_id = IMG_ID;
	if(!_img_id){
		_img_id = $('[name=IMG_ID]').val();
	}
	var linkhref = "/Setting/SetImageDetail.do?IMG_ID=" + _img_id;
    window.open(linkhref,"select","toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
}
function doInsert(){
	var linkhref = "/Setting/SetImageReg.do?type=image";
	window.open(linkhref,'regedit',"toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
}

function doUpdate(){
	var sel = $("#image_grid").getGridParam('selarrrow');
	var _img_id = ($("#image_grid").getRowData(sel[0])).IMAGEID;
	if(_img_id == "" || _img_id == null){
		alert("Please Select Data!");
	}else{
		var linkhref = "/Setting/SetImageReg.do?type=updata&IMG_ID=" + _img_id;
		window.open(linkhref,'regedit',"toolbar=no,location=no,status=no,menubar=no,scrollbars=auto,resizable=no,directories=no,width=900,height=600,top=100,left=200");
	}
}

function road_reference_list(){
		jQuery("#img_ref_table").jqGrid({
		   	url:'/Setting/SetReferenceList.do?IMG_ID=' + $('#IMG_ID').val(),
			datatype: "json",
			colNames : ['Image Reference ID', 'Image Class', 'Image Class Code', 'Reference Table', 'Reference Table Code', 'Reference ID 1', 'Reference ID 2', 'Description'],
		   	colModel:[
		   		{name:'IMG_REF_ID',index:'IMG_REF_ID', width:120},
		   		{name:'IMG_CLASS_NAME',index:'IMG_CLASS_NAME', width:120},
		   		{name:'IMG_CLASS_CD',index:'IMG_CLASS_CD', width:120},
		   		{name:'REF_TABLE_NAME',index:'REF_TABLE_NAME', width:120,},
		   		{name:'REF_TABLE_CD',index:'REF_TABLE_CD', width:120,},		
		   		{name:'REF_ID1',index:'REF_ID1', width:120,},
		   		{name:'REF_ID2',index:'REF_ID2', width:120,},
		   		{name:'DESCR',index:'DESCR', width:120,}
		   	],
		   	
		   	autoWidth:true,
		   	shrinkToFit : true,
		    viewrecords: true,
		    height: "100%",
		    cellEdit: true,
		    editUrl : 'editurl',
			cellsubmit : 'clientArray',
		    rowNum: -1,
		    scrollbars:true,
		    jsonReader : { repeatitems: false },
		    gridComplete : function(){
		    	if($(this).getGridParam('records') == 0){
		    		var b = '<td colspan="'+$(this).find('tr').eq(0).find('td').length
		    			+'" role="gridcell" title="" aria-describedby="" style="text-align: center;">No Data</td>';
		    		
		    		$(this).find('tr').eq(0).after(b);
		    	}
		    }
		});
		comm.resizeJqGridWidth('img_ref_table','tmppp', 800, true );
}
function road_edit_list(){
	jQuery("#img_edit_table").jqGrid({
	   	url:'/Setting/SetReferenceList.do?IMG_ID=' + $('#IMG_ID').val(),
		datatype: "json",
		colNames : ['Image Reference ID', 'Image Class', 'Image Class Code', 'Reference Table', 'Reference Table Code', 'Reference ID 1', 'Reference ID 2', 'Description'],
	   	colModel:[
	   		{name:'IMG_REF_ID',index:'IMG_REF_ID', width:120},
	   		{name:'IMG_CLASS_NAME',index:'IMG_CLASS_NAME', width:120,editable: true},
	   		{name:'IMG_CLASS_CD',index:'IMG_CLASS_CD', width:120},
	   		{name:'REF_TABLE_NAME',index:'REF_TABLE_NAME', width:120,editable: true},
	   		{name:'REF_TABLE_CD',index:'REF_TABLE_CD', width:120},		
	   		{name:'REF_ID1',index:'REF_ID1', width:120,editable: true},
	   		{name:'REF_ID2',index:'REF_ID2', width:120,editable: true},
	   		{name:'DESCR',index:'DESCR', width:120,editable: true}
	   	],
	   	autoWidth:true,
	   	shrinkToFit : true,
	    viewrecords: true,
	    height: "100%",
	    cellEdit: true,
	    editUrl : 'editurl',
		cellsubmit : 'clientArray',
	    rowNum: -1,
	    scrollbars:true,
	    multiselect:true,
	    jsonReader : { repeatitems: false }
	});
	comm.resizeJqGridWidth('img_edit_table','tmppp', 800, true );
}

function doReg(typ){
	var linkhref = "/Setting/SetImageReg.do?type="+typ;
	window.location.href = linkhref;
}
function addGridRow(_id){
	var data = {};
	data['IMG_ID'] = $('#IMG_ID').val();
	$.ajax({
		type: "POST",
		data : data,
		url: "/Setting/SetImgRefSeq.do",
		dataType: "json",
		async: true,
		success: function(jsonData){
			var seq = jsonData.seq
			var ids = $('#' + _id).getGridParam('records');
			jQuery('#' + _id).addRowData(ids + 1 + '', {IMG_REF_ID : seq, IMG_CLASS_NAME : '',IMG_CLASS_CD:'',REF_TABLE_NAME:'',REF_TABLE_CD:'',REF_ID1:'',REF_ID2:'',DESCR:''});
		}
	});
}

function delGridRow(_id){
	var sel = $('#' + _id).getGridParam('selarrrow');
	if(sel.length == 0){
		alert("Please select Data!");
	}else{
		while(sel.length >0 ){
			jQuery("#" + _id).delRowData(sel[0] + '');
		}
	}
}

function road_refAdd_list(){
	jQuery("#image_ref_table").jqGrid({
	   	url:'/Setting/SetReferenceList.do?IMG_ID=' + $('#IMG_ID').val(),
		datatype: "json",
		colNames : ['Image Reference ID', 'Image Class', 'Image Class Code', 'Reference Table', 'Reference Table Code', 'Reference ID 1', 'Reference ID 2', 'Description'],
	   	colModel:[
	   		{name:'IMG_REF_ID',index:'IMG_REF_ID', width:120},
	   		{name:'IMG_CLASS_NAME',index:'IMG_CLASS_NAME', width:120,editable:true},
	   		{name:'IMG_CLASS_CD',index:'IMG_CLASS_CD', width:120},
	   		{name:'REF_TABLE_NAME',index:'REF_TABLE_NAME', width:120,editable:true},
	   		{name:'REF_TABLE_CD',index:'REF_TABLE_CD', width:120,},		
	   		{name:'REF_ID1',index:'REF_ID1', width:120,editable:true},
	   		{name:'REF_ID2',index:'REF_ID2', width:120,editable:true},
	   		{name:'DESCR',index:'DESCR', width:120,editable:true}
	   	],
	   	autoWidth:true,
	   	shrinkToFit : true,
	    viewrecords: true,
	    height: "100%",
	    cellEdit: true,
	    editUrl : 'editurl',
		cellsubmit : 'clientArray',
	    rowNum: -1,
	    scrollbars:true,
	    multiselect:true,
	    jsonReader : { repeatitems: false }
	});
	comm.resizeJqGridWidth('img_ref_table','tmppp', 800, true );
}
function onRoleSave(tableId){
	var data = {};
	data['rows'] = JSON.stringify($('#'+tableId).getRowData());
	data['typ'] = $('#typ').val();
	data['IMG_ID'] = $('#IMG_ID').val();
	data['IMG_FILE_NAME'] = $('#IMG_FILE_NAME').val();
	data['FILE_TYPE_CD'] = $('#FILE_TYPE_CD').val();
	data['status'] = $('#status').val();
	$.ajax({
		type: "POST",
		data : data,
		url: "/Setting/SetImgSave.do",
		dataType: "json",
		async: true,
		success: function(jsonData){
			if(jsonData.action == 'success'){
				alert(comm.lang.save_success);
				window.opener.$("[title=Search]").click();
				window.close();
			}else{
				alert(comm.lang.save_fail);
				console.error(jsonData.message);
			}
		}
	});
}
function doSave(){
	var IMG_ID = $('#IMG_ID').val();
	var IMG_FILE_NAME = $('#IMG_FILE_NAME').val();
	var X_SIZE = $('#X_SIZE').val();
	var Y_SIZE = $('#Y_SIZE').val();
	var linkhref = "/Setting/SetImgSave.do?typ=insert&status=image&IMG_ID="+IMG_ID+"&IMG_FILE_NAME="+IMG_FILE_NAME+"&X_SIZE="+X_SIZE+"&Y_SIZE="+Y_SIZE;
	window.location.href = linkhref;
}

function submitForm(){
    document.form2.action="/Setting/SetImgSave.do";  
    document.form2.method="post"; 
    document.form2.submit();
}

function trueTest(path){
  $('#img_update').val('true');
  var s = $('#IMG').val();
  $('#pic').attr("src",s);
  $("#path").attr("value",path);
}

var url = window.URL || window.webkitURL;
$("#IMG").live("change",function() {
	var opts = jQuery("#FILE_TYPE_CD option");
	  if( this.disabled ){
	        alert('Your browser does not support File upload.');
	    }else{
	        var chosen = this.files[0];
	        var image = new Image();
	        image.onload = function() {
	            //alert('Width:'+this.width +' Height:'+ this.height+' '+ Math.round(chosen.size/1024)+'KB');
	        	var typeImage = chosen.type;
	        	typeImage = typeImage.substring(6).toUpperCase();
	        	for(var i=0;i<opts.length;i++){
	        		if(opts[i].text == typeImage){
	        			$("#FILE_TYPE_CD").attr("value", opts[i].value);
	        		}
	        	}
	        	$("#X_SIZE").attr("value",this.width);
	        	$("#Y_SIZE").attr("value",this.height)
	        	$("#path").attr("value",chosen.path);
	        	var text = '<p id="FILE_TYPE_NAME">'+typeImage+'</p>';
	        	$("#FILE_TYPE_NAME").remove();
	        	$("#FILE_TYPE_NAME_TH").append(text);
	        	$("#FILE_TYPE_NAME_EDIT").attr("value", typeImage);
	        };
	        image.onerror = function() {
	            alert('Not a valid file type: '+ chosen.type);
	        };
	        image.src = url.createObjectURL(chosen);                    
	     }
});
function doDelete(){
	
	var sel = $("#image_grid").getGridParam('selarrrow');
	var imageID = [];

	if(sel == null || sel.length == 0){
		alert(comm.lang.no_sel_data_msg);
		return false;
	}
	var id = "";
	for(var i=0; i<sel.length ; i++){
		var a = $("#image_grid").getRowData(sel[i]);
		imageID[i] = a.IMAGEID;
		if(i == 0){
			id = a.IMAGEID
		}else{
			id = id + ',' + a.IMAGEID
		}
	} 
	if(confirm("Delete "+id+" Image Information?")){
		$.ajax({
			type: "POST",
			data: { IMAGEID : imageID.join(',') },
			url: "/Setting/SetImageDelete.do",
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

}

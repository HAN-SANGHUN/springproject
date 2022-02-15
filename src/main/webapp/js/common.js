$(document).ready(function(){
  $("#nav > li > a").on("click", function(e){
	  
//    if($(this).parent().has("ul")) {
//      e.preventDefault();
//    }
    
    if(!$(this).hasClass("open")) {
      // hide any open menus and remove all other classes
      $("#nav li ul").slideUp(350);
      $("#nav li a").removeClass("open");
	
      
      // open our new menu and add the open class
      $(this).next("ul").slideDown(350);
      $(this).addClass("open");
    }
    
    else if($(this).hasClass("open")) {
      $(this).removeClass("open");
      $(this).next("ul").slideUp(350);
    }
  }); 
  
  //상단 검색 조건 추가
  $("#btn_more").click(function(){
  	$("#main-con_search_list").show();
	});
	
	//상단 검색 조건 숨김
  $("#btn_turnup").click(
      function(){$("#main-con_search_list").hide();
  }); 
  
  menu_init(); 
  
  i18n_init();
  
  $("body").on({
	    // When ajaxStart is fired, add 'loading' to body class
	    ajaxStart: function() { 
	        $(this).addClass("loading"); 
	    },
	    // When ajaxStop is fired, rmeove 'loading' from body class
	    ajaxStop: function() { 
	        $(this).removeClass("loading"); 
	    }    
  });   
  
});

function menu_init(){
	var url = document.URL;	  
	  
	  if(url.indexOf("KeyProfile") != -1){
		  
		  $("#mKeyProfile").addClass("open");
		  
	  }else if(url.indexOf("CertReq") != -1){
		  $("#mcm").addClass("open");
		  $("#mCertReq").addClass("open");
		  $("#cnav").show();	
		  
	  }else if(url.indexOf("CertMgmt") != -1){
		  $("#mcm").addClass("open");
		  $("#mCertMgmt").addClass("open");
		  $("#cnav").show();	
		  
	  }else if(url.indexOf("CompSetting") != -1){
		  $("#mss").addClass("open");
		  $("#smCompany").addClass("open");
		  $("#snav").show();		  
		  
	  }else if(url.indexOf("UserSetting") != -1){
		  $("#mss").addClass("open");
		  $("#smUser").addClass("open");
		  $("#snav").show();		  
		  
	  }else if(url.indexOf("HsmSetting") != -1){
		  $("#mss").addClass("open");
		  $("#smHsm").addClass("open");
		  $("#snav").show();		  
		  
	  }else if(url.indexOf("TokenSetting") != -1){
		  $("#mss").addClass("open");
		  $("#smToken").addClass("open");
		  $("#snav").show();		  
	  }
}

function i18n_init(){
	
	var lang = $("#lan").val();
		
	jQuery.i18n.properties({
        name: 'Messages', 
        path: '/js/i18n/bundle/', 
        mode: 'both',
        language: lang
	});
}

function dialog_message(title, message){
	
	$("#dialog-message p").text(message);
	
	$( "#dialog-message" ).dialog({
		modal: true,
		title: title,
		buttons: {
			Ok: function() {
				$("#dialog-message p").text("");
				$( this ).dialog( "close" );
			}
		}
    });
}

function dialog_message(title, message, callback){
	
	$("#dialog-message p").text(message);
	
	$( "#dialog-message" ).dialog({
		modal: true,
		title: title,
		buttons: {
			Ok: function() {
				$("#dialog-message p").text("");
				$( this ).dialog( "close" );
				callback();
			}
		}
    });
}

function dialog_confirm(title, message, callback){
	
	$("#dialog-confirm p").text(message);
	
	$( "#dialog-confirm" ).dialog({
		modal: true,
		title: title,
		buttons: {
			Ok: function() {
				$("#dialog-message p").text("");
				$( this ).dialog( "close" );
				callback();
			},
			Cancel: function() {
				$("#dialog-message p").text("");
		        $( this ).dialog( "close" );
		    }
		}
    });
}






<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
	String lan = (String)session.getAttribute("lan");
	if(lan == null || "".equals(lan) || (!lan.equals("en") && !lan.equals("zh") && !lan.equals("ko"))){
		lan = "en"; // en, zh, ko
	}
%>

<!doctype html>

<html>

<head>
  <meta charset="utf-8">
  <title>:::: Key Management ::::</title>
  
    <link rel="stylesheet" type="text/css" href="/css/import.css" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />	
    <link rel="stylesheet" type="text/css" href="/css/new_font.css" />	
    <link rel="stylesheet" type="text/css" href="/css/jqgrid/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet">		
		
    <script type="text/javascript" src="/js/jquery-1.8.3.js"></script>	
    <script type="text/javascript" src="/js/jquery.form.js"></script>	
    <script type="text/javascript" src="/js/validate/jquery.validate.min.js"></script>	
    <script type="text/javascript" src="/js/validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="/js/validate/localization/messages_<%=lan%>.js"></script>
    <script type="text/javascript" src="/js/jquery.alphanumeric.js"></script>	
	<script type="text/javascript" src="/js/i18n/jquery.i18n.properties-min-1.0.9.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>		
	
	<script type="text/javascript" src="/js/jqgrid/jquery.jqGrid.src.js"></script>
	<script type="text/javascript" src="/js/jqgrid/i18n/grid.locale-en.js"></script>  		
	<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script>
	
	<sitemesh:write property='head' />
</head>

<body>
	<input type="hidden" name="lan" id="lan" value="${lan}" />
	<div class="dialog-process"></div>
	
	<div id="dialog-message">
		<p></p>
	</div>
	
	<div id="dialog-confirm">
		<p></p>
	</div>
	<!--// 900/600 팝업 //-->
	<div id="wrap">
		<!-- Main article-->
  		<div id="pop_box"> 
  			
   			<sitemesh:write property='body' /> 
    
  		</div>
  	<!-- // Main article--> 
	</div>
</body>

</html>
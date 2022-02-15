<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
	String lan = (String)session.getAttribute("lan");
	if(lan == null || "".equals(lan) || (!lan.equals("en") && !lan.equals("zh") && !lan.equals("ko"))){
		lan = "en"; // en, cn, kr
	}
%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>	
	<head>
		<meta charset="utf-8">
		<title><sitemesh:write property='title' /></title>		
		
		<link rel="stylesheet" type="text/css" href="/css/new_font.css" />
		<link rel="stylesheet" type="text/css" href="/css/import.css" />	
		<link rel="stylesheet" type="text/css" href="/css/jqgrid/redmond/jquery-ui-1.9.2.custom.css">
		<link rel="stylesheet" type="text/css" href="/css/jqgrid/ui.jqgrid.css">		
				
		<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>	
		<script type="text/javascript" src="/js/validate/jquery.validate.min.js"></script>	
    	<script type="text/javascript" src="/js/validate/additional-methods.min.js"></script>	
		<script type="text/javascript" src="/js/i18n/jquery.i18n.properties-min-1.0.9.js"></script>
		
		<script type="text/javascript" src="/js/common.js"></script>
					
		<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script>
  		<script type="text/javascript" src="/js/jqgrid/jquery.jqGrid.src.js"></script>
  		
  		<script type="text/javascript" src="/js/jqgrid/i18n/grid.locale-en.js"></script>  	  		
  		
  		
		<sitemesh:write property='head' />
	</head>
	<body>		
	    <input type="hidden" name="secCompanyID" id="secCompanyID" value="<sec:authorize access="hasRole('Key Manager')">${sessionScope.user.companyId}</sec:authorize>" />
		<input type="hidden" name="lan" id="lan" value="${lan}" />
		<div id="dialog-message">
			<p></p>
		</div>
		
		<div id="dialog-confirm">
			<p></p>
		</div>
		<!-- // 1366/768 해상도 최적화 / ie9.chrome 최신 Ver. 최적화 //-->
		<div id="wrap">
		
			<!-- Main Header -->
			<div id="top_box">
				<h1 id="top_logo"><a href="/">Konai Key Management</a></h1>
				<div id="top_login">
					<span id="top_mem">						
						<strong><sec:authentication property="principal.username" /></strong>&nbsp;&nbsp;&nbsp;welcome!
					</span>
					<a href="<c:url value='/logout' />" class="button_ss bt_red btn_logout" title="Logout">Logout</a>
				</div>
			</div>
			<!--  //Main Header -->
			
			<!-- Main Article -->
			<div id="content_box">
				
				<!-- Left Menu -->
				<div id="left_box">
					<nav>
						<ul id="nav">
							<li title="Key Profile Management"><a href="/KeyProfile/search.do" id="mKeyProfile"><img src="../img/common/nav_ico_2.png" alt="">Key Profile Management</a></li>
							<!-- 
							<li title="Request Certificate Management"><a href="/CertReq/search.do" id="mCertReq"><img src="../img/common/nav_ico_1.png" alt="">Certificate Request</a></li>
							-->
							<li title="Certificate Management"><a href="#" id="mcm"><img src="../img/common/nav_ico_4.png" alt="">Certificate Management<img class="arrow" src="../img/common/arrow.png" alt=""/></a>
								<ul id="cnav">
									<li title="Request"><a href="/CertReq/search.do" id="mCertReq">Request</a></li>
									<li title="Register"><a href="/CertMgmt/search.do" id="mCertMgmt">Register</a></li>
								</ul>
							</li>
							<li title="System Setting"><a href="#" id="mss"><img src="../img/common/nav_ico_7.png" alt="">System Setting<img class="arrow" src="../img/common/arrow.png" alt=""/></a>
								<ul id="snav">
									<li title="Company"><a href="/CompSetting/search.do" id="smCompany">Company</a></li>
									<li title="User"><a href="/UserSetting/search.do" id="smUser">User</a></li>
									<sec:authorize access="hasRole('KMS Manager')">
									<li title="HSM"><a href="/HsmSetting/search.do" id="smHsm">HSM</a></li>
									<li title="Token"><a href="/TokenSetting/search.do" id="smToken">Token</a></li>
									</sec:authorize>
								</ul>
							</li>
						</ul>
					</nav>
				</div>				
				<!-- //Left Menu -->
				
				<!-- Body Template -->
				<sitemesh:write property='body' />
				<!-- //Body Template -->
			
			</div>
			<!-- //Main Article -->
		
		</div>
		
		<!-- Main Footer -->
		<div class="footer">
			<address>Copyright Kona i Co. Ltd. All rights reserved. 6F, 16-2 Yeouido-dong, Youngdeungpo-gu, Seoul 150-740, Korea<strong>Tel</strong>02-1899-1771<strong>Fax</strong>02-769-1670 </address>
		</div>
		<!-- //Main Footer -->
	</body>
</html>
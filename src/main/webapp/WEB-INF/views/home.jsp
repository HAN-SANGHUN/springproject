<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en-US">
<head>	
	<meta charset="UTF-8">	
	<title>:::: Key Management Service ::::</title>
	<link rel="stylesheet" type="text/css" href="/css/import.css" />	
	<link rel="stylesheet" type="text/css" href="/css/jqgrid/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	
	<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/js/validate/jquery.validate.min.js"></script>	
    <script type="text/javascript" src="/js/validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/js/login/login.js"></script>
</head>
<body>
	<div id="login_wrap">
	
		<div class="logo">
			<h1><img src="/img/main/logo_konai.png" alt="Konai Trusted Service Management"/></h1>
		</div>
		<form name="f" id="form" action="<c:url value='j_spring_security_check' />" method="post">
		<div class="login_bg">
			<fieldset>
				<legend>Login</legend>
				<dl class="clearfix">
					<dt><img src="/img/main/title_Language.png" alt="Language"/></dt>
					<dd>
						<select name="lan">
							<option value="en" <c:if test="${lan eq 'en'}">selected</c:if>>English</option>
							<option value="ko" <c:if test="${lan eq 'ko'}">selected</c:if>>Korea</option>
							<option value="zh" <c:if test="${lan eq 'zh'}">selected</c:if>>China</option>
						</select>
					</dd>
					<dt><img src="/img/main/title_Id.png" alt="Id"/></dt>
					<dd><input type="text" name="j_username" value="" maxlength="20" title="ID Key"/></dd>
					<dt><img src="/img/main/title_Password.png" alt="Password"/></dt>
					<dd><input type="password" name="j_password" title="PASSWORD Key"/></dd>
				</dl>
				<span class="btn_login">
					<input type="image" src="/img/main/btn_login.png" alt="login"/>
				</span>
			</fieldset>
		</div>
		
		</form>
		<div class="footer">
			<address>Copyright Kona i Co. Ltd. All rights reserved. 6F, 16-2 Yeouido-dong, Youngdeungpo-gu, Seoul 150-740, Korea<strong>Tel</strong>02-1899-1771<strong>Fax</strong>02-769-1670 </address>
		</div>			
	</div>
	<div id="dialog-message">
		<p></p>
	</div>
	<c:if test="${not empty message}">
		<script type="text/javascript">
			dialog_message("Info", '${message}');
		</script>
	</c:if>
</body>
</html>

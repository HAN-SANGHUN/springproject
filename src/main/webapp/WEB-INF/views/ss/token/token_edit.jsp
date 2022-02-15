<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/token/token_edit.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Edit Token</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">
		<div class="floatR" style="padding-right:5px;">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		<form id="form2" name="form2" action="/TokenSetting/popup/editToken.do" method="post">	
		<input type="hidden" name="tokenNo" id="tokenNo" value="${token.tokenNo}" />
		<input type="hidden" name="oldTokenPin" id="oldTokenPin" value="${token.tokenPin}" />
		<div class="mgt10 mgb20" style="padding-top:20px;">
			<ul>
				<li>					
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
						  		<th>Company ID</th>
								<td><input type="text" name="companyID" id="companyID" value="${token.companyID}" readonly style="background-color:#F0F0F0;"/></td>
								<th>Company Name</th>
								<td><input type="text" name="companyName" id="companyName" value="${token.companyName}" readonly style="background-color:#F0F0F0;"/></td>							
						  	</tr>
						</tbody>
					</table>
					
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
						  <tr>
						  	<th>User ID</th>
							<td><input type="text" name="userId" id="userId" value="${sessionScope.user.userId}" disabled /></td>
							<th>User Name</th>
							<td><input type="text" name="userName" id="userName" value="${sessionScope.user.userName}" disabled /></td>
							
						  </tr>
						  <tr>
						  	<th>User Role</th>
							<td colspan="3"><input type="text" name="userRoleCode" id="userRoleCode" value="${sessionScope.user.userRoleCode}" disabled /></td>
						  </tr>
						  </tbody>
					</table>
					
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
						  <tr>
						  	<th>HSM Label</th>
							<td><input type="text" name="hsmLabel" id="hsmLabel" value="${hsm.hsmLabel}" readonly style="background-color:#F0F0F0;"/></td>
							<th>Token Label</th>
							<td><input type="text" name="tokenLabel" id="tokenLabel" value="${token.tokenLabel}" readonly style="background-color:#F0F0F0;"/></td>							
						  </tr>							  
						</tbody>
					</table>
					
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
						  <tr>
						  	<th>Token PIN</th>
							<td><input type="text" name="tokenPin" id="tokenPin" value="${token.tokenPin}" maxlength="128" /></td>
							<th>Description</th>
							<td><input type="text" name="description" id="description" value="${token.description}" maxlength="512"/></td>							
						  </tr>						  
						</tbody>
					</table>
				</li>
			</ul>
		</div>		
		</form>
	</div>
</body>
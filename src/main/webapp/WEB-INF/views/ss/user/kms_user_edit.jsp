<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/user/user_edit.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Edit KMS User</h1>
       	<!-- Buttons-->
       	
       	<!-- //Buttons--> 
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">				
		<div class="floatR" style="padding-right:5px;">
			<a href="javascript:doSubmit();" class="button_pop" title="Save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		<form id="form2" name="form2" action="/UserSetting/popup/editUser.do" method="post">
		<div class="mgt10 mgb20" style="padding-top:20px;">
			<ul>
				<li>
					<sec:authorize access="hasRole('KMS Manager')">
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
						  <tr>
						  	<th>Company</th>
							<td>
								<input type="text" name="searchName" id="searchName" maxlength="128" readonly/>
								
							</td>
							<td colspan="2" align="left" style="border:0;background:transparent;">
								<a href="javascript:findCompany();" class="button_pop" title="Search"><i class="icon-search"></i></a>
								<a href="javascript:cancelSearch();" class="button_pop" title="Cancel"><i class="icon-remove"></i></a>
							</td>							
						  </tr>						  
						  </tbody>
					</table>
					
					<!-- Grid Table Section -->
					<div id="main-con-content" style="display:none;margin-top:3px;margin-bottom:10px;">
						<table class="Table-Style_main" id="jq_grid"></table>
						
						<!-- Paging -->
						<div class="ft_btm mgt10 mgb20 txtC" id="pager"></div>
						<!-- //Paging -->
						<div style="color:red; margin-top:3px;font-size:11px;">
							* Please, double click to select a row.
						</div>
					</div>
					<!-- /Grid Table Section -->
					</sec:authorize>
					
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
							<td><input type="text" name="companyId" id="companyId" value="${user.companyId}" maxlength="40" placeholder="search input" readonly style="background-color:#F0F0F0;"/></td>
							<th>Company Name</th>
							<td><input type="text" name="companyName" id="companyName" value="${user.companyName}" maxlength="40" placeholder="search input" readonly style="background-color:#F0F0F0;"/></td>
							
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
							<td><input type="text" name="userId" id="userId" value="${user.userId}" maxlength="20" readonly style="background-color:#F0F0F0;"/></td>
							<th>User Password</th>
							<td><input type="password" name="userPwd" id="userPwd" value="${user.userPwd}" maxlength="40"/></td>
							
						  </tr>
						  <tr>
						  	<th>User Role</th>
							<td>
								<select name="userRoleCode" id="userRoleCode">
									<option value="KMS Manager" <c:if test="${user.userRoleCode eq 'KMS Manager'}">select</c:if>>KMS Manager</option>
									<option value="Key Manager" <c:if test="${user.userRoleCode eq 'Key Manager'}">select</c:if>>Key Manager</option>
								</select>
							</td>
							<th>Status</th>
							<td>
								<select name="activeStatusCode" id="activeStatusCode">
									<option value="Active" <c:if test="${user.activeStatusCode eq 'Active'}">select</c:if>>Active</option>
									<option value="Inactive" <c:if test="${user.activeStatusCode eq 'Inactive'}">select</c:if>>Inactive</option>
								</select>
							</td>
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
						  	<th>User Name</th>
							<td><input type="text" name="userName" id="userName" value="${user.userName}" maxlength="40"/></td>
							<th>E-Mail</th>
							<td><input type="text" name="email" id="email" value="${user.email}" maxlength="40"/></td>
							
						  </tr>
						  <tr>
						  	<th>Office Tel</th>
							<td><input type="text" name="officeTelNum" id="officeTelNum" value="${user.officeTelNum}" maxlength="20"/></td>
							<th>User Tel</th>
							<td><input type="text" name="cellphoneNum" id="cellphoneNum" value="${user.cellphoneNum}" maxlength="20"/></td>
						  </tr>
						  <tr>
						  	<th>Department</th>
							<td><input type="text" name="department" id="department" value="${user.department}" maxlength="40"/></td>
							<th>Designation</th>
							<td><input type="text" name="designation" id="designation" value="${user.designation}" maxlength="40"/></td>
						  </tr>
						  <tr>
						  	<th>Address</th>
							<td colspan="3"><input type="text" name="address" id="address" value="${user.address}" maxlength="126"/></td>
						  </tr>
						  
						  <tr>
						  	<th>Description</th>
							<td colspan="3"><input type="text" name="descritpion" id="descritpion" value="${user.description}" maxlength="100"/></td>
						  </tr>
						  
						  </tbody>
					</table>
				</li>
			</ul>
		</div>
		
		</form>
	</div>
	<form id="detailFrm" name="detailFrm" action="/UserSetting/popup/detail.do" method="post">
		<input type="hidden" name="userId" id="userId1" value="" />
	</form>
</body>
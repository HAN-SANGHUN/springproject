<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Key Management</title>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>KMS User Detail</h1>
       	<!-- Buttons-->
       	
       	<!-- //Buttons--> 
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">	
		<div class="floatR" style="padding-right:5px;">			
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Close</a>
		</div>	
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
							<td>${user.companyId}</td>
							<th>Company Name</th>
							<td>${user.companyName}</td>
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
							<td>${user.userId}</td>
							<th>User Password</th>
							<td><input type="password" name="userPwd" id="userPwd" maxlength="40" value="${user.userPwd}" readonly/></td>
							
						  </tr>
						  <tr>
						  	<th>User Role</th>
							<td>${user.userRoleCode}</td>
							<th>Status</th>
							<td>${user.activeStatusCode}</td>
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
							<td>${user.userName}</td>
							<th>E-Mail</th>
							<td>${user.email}</td>
							
						  </tr>
						  <tr>
						  	<th>Office Tel</th>
							<td>${user.officeTelNum}</td>
							<th>User Tel</th>
							<td>${user.cellphoneNum}</td>
						  </tr>
						  <tr>
						  	<th>Department</th>
							<td>${user.department}</td>
							<th>Designation</th>
							<td>${user.designation}</td>
						  </tr>
						  <tr>
						  	<th>Address</th>
							<td colspan="3">${user.address}</td>
						  </tr>
						  
						  <tr>
						  	<th>Description</th>
							<td colspan="3">${user.descriptionWeb}</td>
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
								<th>Registration User ID</th>
								<td>${user.registrationUserId}</td>
								<th>Registration Date</th>
								<td><fmt:formatDate type="both" value="${user.registrationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<th>Update User ID</th>
								<td>${user.updateUserId}</td>
								<th>Update Date</th>
								<td><fmt:formatDate type="both" value="${user.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
	</div>
</body>
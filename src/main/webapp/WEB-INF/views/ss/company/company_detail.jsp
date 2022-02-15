<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Key Management</title>
	
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Company Detail</h1>
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
							<td>${company.companyId}</td>
							<th>Token label</th>
							<td>${company.tokenLabel}</td>							
						  </tr>
						  <tr>
						  	<th>Company Name</th>
							<td>${company.companyName}</td>
							<th>Status</th>
							<td>${company.companyStatusCode}</td>
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
						  	<th>Fax</th>
							<td>${company.fax}</td>
							<th>E-Mail</th>
							<td>${company.email}</td>							
						  </tr>
						  <tr>
						  	<th>Office Tel</th>
							<td>${company.officeTelNum}</td>
						  	<th>Biz License No.</th>
							<td>${company.bizLicenseNo}</td>
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
						  	<th>HomePage URL</th>
						  	<td colspan="3">${company.homepageUrl}</td>
						  </tr>
						  <tr>
						  	<th>Address</th>
							<td colspan="3">${company.address}</td>
						  </tr>						  
						  <tr>
						  	<th>Description</th>
							<td colspan="3">${company.companyDescription}</td>
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
								<td>${company.registrationUserId}</td>
								<th>Registration Date</th>								
								<td><fmt:formatDate type="both" value="${company.registrationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<th>Update User ID</th>
								<td>${company.updateUserId}</td>
								<th>Update Date</th>								
								<td><fmt:formatDate type="both" value="${company.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
	</div>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/token/token_detail.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Token Detail</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%">
		<div class="floatR" style="padding-right:5px;margin-bottom:15px;">	
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Close</a>
		</div>	
		<div class="mgt10 mgb20">
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
								<td>${token.companyID}</td>
								<th>Company Name</th>
								<td>${token.companyName}</td>
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
								<th>Token Label</th>
								<td>${token.tokenLabel}</td>
							  	<th>Token PIN</th>
								<td>${token.tokenPin}</td>
						  	</tr>
						  	<tr>							
								<th>Description</th>
								<td colspan="3">${token.description}</td>
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
							<c:forEach var="slot" items="${token.slotInfos}">
							<tr>							
								<th>HSM Label</th>
								<td>${slot.hsmLabel}</td>
							  	<th>Slot No.</th>
								<td>${slot.slotNo}</td>
						  	</tr>
						  	</c:forEach>
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
								<td>${token.registrationUserID}</td>
								<th>Registration Date</th>
								<td><fmt:formatDate type="both" value="${token.registrationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<th>Update User ID</th>
								<td>${token.updateUserID}</td>
								<th>Update Date</th>
								<td><fmt:formatDate type="both" value="${token.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
	</div>
</body>
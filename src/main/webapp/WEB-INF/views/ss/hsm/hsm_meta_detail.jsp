<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/hsm/hsm_detail.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>HSM Info Detail</h1>
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
						  		<th>HSM Label</th>
								<td>${hsm.hsmLabel}</td>
								<th>Status</th>
								<td>${hsm.statusCode}</td>
							</tr>
							<tr>
								<th>IP Address</th>
								<td>${hsm.ipAddress}</td>
								<th>Port</th>
								<td>${hsm.port}</td>
							</tr>							
							<tr>
								<th>Slot Count</th>
								<td>${hsm.slotCount} </td>
								<th>Slot Allocation Count</th>
								<td>${hsm.tokenAllocationNum}</td>
							</tr>							
							<tr>
								<th>Slot Start Index</th>
								<td>${hsm.slotStartNum}</td>
								<th>Slot End Index</th>
								<td>${hsm.slotEndNum}</td>
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
								<th>Description</th>
								<td colspan="3">${hsm.description}</td>
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
								<td>${hsm.registrationUserId}</td>
								<th>Registration Date</th>
								<td><fmt:formatDate type="both" value="${hsm.registrationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<th>Update User ID</th>
								<td>${hsm.updateUserId}</td>							
								<th>Update Date</th>
								<td><fmt:formatDate type="both" value="${hsm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</tbody>
					</table>					
					
					<input type="hidden" name="hsmNo" id="hsmNo" value="${hsm.hsmNo}" />
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Slot Allocation Information
						
						<div class="floatR" style="padding-right:7px;">
							<select name="slotStatusCode" id="slotStatusCode">
								<option value="">-- ALL -- </option>
								<option value="Active">Initialized</option>
								<option value="Inactive">Uninitialized</option>
							</select>
						</div>
					</div>
					<!-- Grid Table Section -->
					<div id="main-con-content" style="margin-top:5px;margin-bottom:10px;">
						<table class="Table-Style_main" id="jq_grid"></table>
						
						<!-- Paging -->
						<div class="ft_btm mgt10 mgb20 txtC" id="pager"></div>
						<!-- //Paging -->
					</div>
					<!-- /Grid Table Section -->
				</li>
			</ul>
		</div>		
	</div>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/hsm/hsm_edit.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Edit HSM Info</h1>
       	<!-- Buttons-->
       
       	<!-- //Buttons--> 
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">
		<div class="floatR" style="padding-right:5px;">
			<a href="javascript:doSubmit();" class="button_pop" title="Save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		<form id="form2" name="form2" action="addHsm.do" method="post">
		<input type="hidden" name="hsmNo" id="hsmNo" value="${hsm.hsmNo}" />	
		<input type="hidden" name="statusCode" id="statusCode" value="${hsm.statusCode}" />	
		
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
								<th>Slot Count</th>
								<td><input type="text" name="slotCount" id="slotCount" value="${hsm.slotCount}" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if>placeholder="number only" maxlength="5"/></td>
						  		<th>HSM Label</th>
								<td>
									<input type="text" name="hsmLabel" id="hsmLabel" value="${hsm.hsmLabel}" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if> maxlength="20"/>
								</td>								
							</tr>	
							<tr>
								<th>Slot Start Index</th>
								<td><input type="text" name="slotStartNum" id="slotStartNum" value="${hsm.slotStartNum}" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if> maxlength="20" placeholder="number only" maxlength="5"/></td>
						  		<th>Slot End Index</th>
								<td><input type="text" name="slotEndNum" id="slotEndNum" value="${hsm.slotEndNum}" maxlength="20" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if> placeholder="number only" maxlength="5"/></td>								
							</tr>
							<tr>
								<th>IP Address</th>
								<td>
									<input type="text" name="ipAddress" id="ipAddress" maxlength="20" value="${hsm.ipAddress}" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if> placeholder="numeric and '.' only"/>
								</td>
								<th>Port</th>
								<td>
									<input type="text" name="port" id="port" maxlength="10" value="${hsm.port}" <c:if test="${hsm.statusCode eq 'Active'}">readonly style="background-color:#F0F0F0;"</c:if> placeholder="numeric only"/>
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
								<th>Description</th>
								<td colspan="3">
									<input type="text" name="description" id="description" maxlength="152" value="${hsm.description}"/>
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
		</form>
		<div style="color:red; font-size:12px;margin-top: 25px">
			* Only Description is editable when status is 'Active'.
		</div>
	</div>
	<form id="detailFrm" name="detailFrm" action="/HsmSetting/popup/detail.do" method="post">
		<input type="hidden" name="hsmNo" id="hsmNo" value="${hsm.hsmNo}" />
	</form>
</body>
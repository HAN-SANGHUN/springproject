<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/hsm/hsm_new.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Add HSM Info</h1>
       	<!-- Buttons-->
       
       	<!-- //Buttons--> 
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">
		<div class="floatR" style="padding-right:5px;">
			<a href="javascript:doSubmit();" class="button_pop" title="Save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		<form id="form2" name="form2" action="saveHsm.do" method="post">
		<c:if test="${not empty hsm}">
		<input type="hidden" name="LHsmNo" value="${hsm.LHsmNo}" />
		</c:if>
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
								<td><input type="text" name="slotCount" id="slotCount" <c:if test="${primary eq false}">value="${hsm.slotCount}" readonly style="background-color:#F0F0F0;"</c:if>placeholder="number only" maxlength="5"/></td>
						  		<th>HSM Label</th>
								<td>
									<input type="text" name="hsmLabel" id="hsmLabel" maxlength="20"/>
								</td>								
							</tr>	
							<tr>
								<th>Slot Start Index</th>
								<td><input type="text" name="slotStartNum" id="slotStartNum" maxlength="20" placeholder="number only" maxlength="5"/></td>
						  		<th>Slot End Index</th>
								<td><input type="text" name="slotEndNum" id="slotEndNum" maxlength="20" placeholder="number only" maxlength="5"/></td>								
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
								<tr>
								<th>IP Address</th>
								<td>
									<input type="text" name="ipAddress" id="ipAddress" maxlength="20" placeholder="number and '.' only" />
								</td>								
								<th>Port</th>
								<td colspan="3">
									<input type="text" name="port" id="port" maxlength="10" placeholder="number only"/>
								</td>
							</tr>
							<tr>
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
									<input type="text" name="description" id="description" maxlength="152"/>
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>		
		</form>
	</div>
	<form id="detailFrm" name="detailFrm" action="/HsmSetting/popup/detail.do" method="post">
		<input type="hidden" name="hsmNo" id="hsmNo" value="" />
	</form>
</body>
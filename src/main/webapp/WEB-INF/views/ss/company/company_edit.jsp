<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/ss/company/company_edit.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Edit Company</h1>       	
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">		
		<div class="floatR" style="padding-right:5px;">
			<a href="javascript:doSubmit();" class="button_pop" title="Save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		<form id="form2" name="form2" >
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
							<td><input type="text" name="companyId" id="companyId" value="${company.companyId}" readonly style="background-color:#F0F0F0;"/></td>
							<th>Token label</th>
							<td><input type="text" name="tokenLabel" id="tokenLabel" value="${company.tokenLabel}" readonly style="background-color:#F0F0F0;"/></td>							
						  </tr>
						  <tr>
						  	<th>Company Name</th>
							<td><input type="text" name="companyName" id="companyName" value="${company.companyName}" maxlength="128"/></td>
							<th>Status</th>
							<td>
								<select name="companyStatusCode" id="companyStatusCode">
									<option value="Active" <c:if test="${company.companyStatusCode eq 'Active'}">selected</c:if>>Active</option>
									<option value="Inactive" <c:if test="${company.companyStatusCode eq 'Inactive'}">selected</c:if>>Inactive</option>
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
						  	<th>Fax</th>
							<td><input type="text" name="fax" id="fax" value="${company.fax}" maxlength="20" placeholder="input number and '-'"/></td>
							<th>E-Mail</th>
							<td><input type="text" name="email" id="email" value="${company.email}" maxlength="100" /></td>							
						  </tr>
						  <tr>
						  	<th>Office Tel</th>
							<td><input type="text" name="officeTelNum" id="officeTelNum" value="${company.officeTelNum}" maxlength="20" placeholder="numeric and '-' only"/></td>
						  	<th>Biz License No.</th>
							<td><input type="text" name="bizLicenseNo" id="bizLicenseNo" value="${company.bizLicenseNo}" maxlength="32" placeholder="numeric and '-' only"/></td>
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
						  		<td colspan="3"><input type="text" name="homepageUrl" id="homepageUrl" value="${company.homepageUrl}" maxlength="100"/></td>
						  	</tr>
						  	<tr>
						  		<th>Address</th>
								<td colspan="3"><input type="text" name="address" id="address" value="${company.address}" maxlength="126"/></td>
						  	</tr>						  
						  	<tr>
						  		<th>Description</th>
								<td colspan="3"><input type="text" name="companyDescription" id="companyDescription" value="${company.companyDescription}" maxlength="256"/></td>
						  	</tr>	
						</tbody>
					</table>
				</li>
			</ul>
		</div>
		</form>	
	</div>
	<form id="detailFrm" name="detailFrm" action="/CompSetting/popup/detail.do" method="post">
		<input type="hidden" name="companyId" id="companyId1" value="" />
	</form>
</body>
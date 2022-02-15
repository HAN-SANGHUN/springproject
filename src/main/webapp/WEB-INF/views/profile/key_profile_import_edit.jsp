<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Edit Imported Key Profile</title>
	<script type="text/javascript" src="/js/profile/profile_import_edit.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Edit Imported Key Profile</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		<form id="form2" name="form2">
		<input type="hidden" name="companyID" id="companyID" value="${profile.companyID}" />
		<input type="hidden" name="tokenID" id="tokenID" value="${tokenID}" />
		<div class="floatR">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:doCancel();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> <span id="close">Cancel</span></a>
		</div>
		
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
						  	<th>Company Name Search</th>
							<td>
								<input type="text" name="searchName" id="searchName" maxlength="128" />
								
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
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Choose the type of key
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:2px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>					
						<caption>Choose the type of key</caption>
						<tbody>
						  <tr>
						  	<th>Company</th>
							<td><input type="text" name="companyName" id="companyName" value="${profile.companyName}" maxlength="40" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
							<th>Token Label</th>
							<td><input type="text" name="tokenLabel" id="tokenLabel" value="${profile.tokenLabel}" maxlength="40" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
						  </tr>
						  <tr>
						  	<th>Key Type</th>
							<td><input type="text" name="keyTypeCode" id="keyTypeCode" value="${profile.keyTypeCode}" readonly style="background-color:#F0F0F0;"/></td>
							<th>Key Sub Type</th>
							<td>
								<c:choose>
									<c:when test="${profile.keyTypeCode eq 'SECRET'}">
										<select name="keySubtypeCode" id="keySubtypeCode">
											<option value="DES" <c:if test="${profile.keySubtypeCode eq 'DES'}">selected</c:if>>DES</option>
											<option value="SEED" <c:if test="${profile.keySubtypeCode eq 'SEED'}">selected</c:if>>SEED</option>
											<option value="AES" <c:if test="${profile.keySubtypeCode eq 'AES'}">selected</c:if>>AES</option>
										</select>
									</c:when>
									<c:otherwise>
										<select name="keySubtypeCode" id="keySubtypeCode">
											<option value="RSA" <c:if test="${profile.keySubtypeCode eq 'RSA'}">selected</c:if>>RSA</option>
											<option value="RSACRT" <c:if test="${profile.keySubtypeCode eq 'RSACRT'}">selected</c:if>>RSACRT</option>
											<option value="EC"<c:if test="${profile.keySubtypeCode eq 'EC'}">selected</c:if>>EC</option>
										</select>
									</c:otherwise>
								</c:choose>
								
							</td>
						  </tr>
						  <tr>
						  	<th>Key Role</th>
							<td>
								<select name="keyRoleCode" id="keyRoleCode">
									<option value="SD Key" <c:if test="${profile.keyRoleCode eq 'SD Key'}">selected</c:if>>SD Key</option>
									<option value="Transport Key" <c:if test="${profile.keyRoleCode eq 'Transport Key'}">selected</c:if>>Transport Key</option>
									<option value="Application Key" <c:if test="${profile.keyRoleCode eq 'Application Key'}">selected</c:if>>Application Key</option>
									<c:if test="${profile.keyTypeCode != 'SECRET'}">
										<option value="Issuer RSA Key" <c:if test="${profile.keyRoleCode eq 'Issuer RSA Key'}">selected</c:if>>Issuer RSA Key</option>
									</c:if>
								</select>
							</td>
						  	<th>Key Version</th>
							<td><input type="text" name="keyVersion" id="keyVersion" value="${profile.keyVersion}" maxlength="40"/></td>
						  </tr>
						  <tr>
						  	<th>Key Index</th>
							<td><input type="text" name="keyIndex" id="keyIndex" value='<c:if test="${profile.keyIndex gt 0}">${profile.keyIndex}</c:if>' maxlength="40" placeholder="number only"/></td>
						  	<th>Key Identifier</th>
							<td style="border:0px;"><input type="text" name="keyIdentifier" id="keyIdentifier" value="${profile.keyIdentifier}"/></td>							
						  </tr>
						  <tr>
						  	<th></th>
							<td></td>
						  	<th>Key Definition</th>
							<td><input type="text" name="keyDefinition" id="keyDefinition" value="${profile.keyDefinition}" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>							
						  </tr>
						</tbody>
					</table>
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Input the information of key
					</div>
					<table style="border:1px solid #D8D8D8; background:transparent;">
						<colgroup>
							<col style="width:400px"/>
							<col style="width:400px"/>
						</colgroup>
						<tbody>
							<tr>
								<td>
									<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> <span id="privateKeyLabel">${profile.keyTypeCode} Key</span></div>
									<table class="Table-Style_pop2  txtL" style="width:250px;">
										<colgroup>
											<col style="width:175px"/>
											<col style="width:225px"/>
										</colgroup>
										<tbody>
											<tr>
											  	<th>Key Label</th>
												<td><textarea rows="2" name="keyLabel" id="keyLabel" maxlength="128" readonly style="background-color:#F0F0F0;width:90%;">${profile.keyLabel}</textarea></td>
											</tr>
											<tr>
											  	<th>Key Size (bits)</th>
												<td>
													<select name="keySize" id="keySize">
														<option value="64" <c:if test="${profile.keySize eq 64}">selected</c:if>>64</option>
														<option value="128" <c:if test="${profile.keySize eq 128}">selected</c:if>>128</option>
														<option value="192" <c:if test="${profile.keySize eq 192}">selected</c:if>>192</option>
													</select>
												</td>
											</tr>
											<tr>
											  	<th><span id="algorithm">KCV Algorithm</span></th>
												<td>
													<select name="keyAlgorithm" id="keyAlgorithm">
														<option value="EIGHTZEROS">EIGHTZEROS</option>
													</select>
												</td>
											</tr>
										</tbody>
									</table>
									<c:set var="attr" value="${profile.keyValueAttribute}" />
									<table class="Table-Style_pop2  txtL" style="width:250px; margin:3px 0px 3px 0px;">
										<colgroup>
											<col style="width:133px"/>
											<col style="width:133px"/>
											<col style="width:133px"/>
										</colgroup>
										<tbody>
											<tr>
												<td><input type="checkbox" name="importable" id="importable" <c:if test="${attr.importable}">checked</c:if>> Importable</td>
												<td><input type="checkbox" name="exportable" id="exportable" <c:if test="${attr.exportable}">checked</c:if>> Exportable</td>
												<td><input type="checkbox" name="sensitive" id="sensitive" <c:if test="${attr.sensitive}">checked</c:if>> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="encrypt" id="encrypt" <c:if test="${attr.encrypt}">checked</c:if>> Encrypt</td>
												<td><input type="checkbox" name="decrypt" id="decrypt" <c:if test="${attr.decrypt}">checked</c:if>> Decrypt</td>
												<td><input type="checkbox" name="encryptDecrypt" id="encryptDecrypt" <c:if test="${attr.encryptDecrypt}">checked</c:if>> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="wrap" id="wrapp" <c:if test="${attr.wrap}">checked</c:if>> Wrap</td>
												<td><input type="checkbox" name="unwrap" id="unwrap" <c:if test="${attr.unwrap}">checked</c:if>> Unwrap</td>
												<td><input type="checkbox" name="wrapUnwrap" id="wrapUnwrap" <c:if test="${attr.wrapUnwrap}">checked</c:if>> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="sign" id="sign" <c:if test="${attr.sign}">checked</c:if>> Sign</td>
												<td><input type="checkbox" name="verify" id="verify" <c:if test="${attr.verify}">checked</c:if>> Verify</td>
												<td><input type="checkbox" name="derive" id="derive" <c:if test="${attr.derive}">checked</c:if>> Derive</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Key Profile Information
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:2px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
						  <tr>
						  	<th>Profile ID</th>
							<td><input type="text" name="keyProfileID" id="keyProfileID" value="${profile.keyProfileID}" maxlength="40" readonly style="background-color:#F0F0F0;"/></td>
							<th>Profile Name</th>
							<td><input type="text" name="keyProfileName" id="keyProfileName" value="${profile.keyProfileName}" maxlength="128"/></td>
						  </tr>
						  <tr>
						  	<th>Profile Version</th>
							<td><input type="text" name="keyProfileVersion" id="keyProfileVersion" value="${profile.keyProfileVersion}" maxlength="8" /></td>
							<th>Description</th>
							<td><input type="text" name="description" id="description" value="${profile.description}" maxlength="512"/></td>
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
						  	<th>Start Date</th>
							<td><input type="text" name="effectiveStartDateStr" id="effectiveStartDateStr"  value="<fmt:formatDate value='${profile.effectiveStartDate}' pattern='yyyy-MM-dd' />" maxlength="10" readonly/></td>
							<th>Owner</th>
							<td><input type="text" name="keyOwner" id="keyOwner" value="${profile.companyID}" maxlength="128" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
						  </tr>
						  <tr>
						  	<th>End Date</th>
							<td><input type="text" name="effectiveEndDateStr" id="effectiveEndDateStr" value="<fmt:formatDate value='${profile.effectiveEndDate}' pattern='yyyy-MM-dd' />" maxlength="10" readonly/></td>
							<th>Mode</th>
							<td>
								<select name="testMode" id="testMode">
									<option value="PRODUCTION" <c:if test="${profile.testMode eq 'PRODUCTION'}">selected</c:if>>PRODUCTION</option>
									<option value="TEST" <c:if test="${profile.testMode eq 'TEST'}">selected</c:if>>TEST</option>
								</select>
							</td>
						  </tr>	
						  <tr>
						  	<th>Revocation Date</th>
							<td><input type="text" name="revocationDateStr" id="revocationDateStr" value="<fmt:formatDate value='${profile.revocationDate}' pattern='yyyy-MM-dd' />" maxlength="10" readonly/></td>
							<th>Status</th>
							<td>
								<select name="activeStatusCode" id="activeStatusCode">
									<option value="Active" <c:if test="${profile.activeStatusCode eq 'Active'}">selected</c:if>>Active</option>
									<option value="Inactive" <c:if test="${profile.activeStatusCode eq 'Inactive'}">selected</c:if>>Inactive</option>
								</select>
							</td>
						  </tr>						  
						</tbody>
					</table>										
				</li>
			</ul>
		</div>		
		</form>
	</div>
</body>
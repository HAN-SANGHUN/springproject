<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Generate Key Profile</title>
	<script type="text/javascript" src="/js/profile/profile_new.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Generate Key Profile</h1>
   	</div>
	<!-- //Header --> 
		
	<div id="pop-main" style="height:85%;">
		<form id="form2" name="form2" onsubmit="return validateForm(this);">
		<input type="hidden" name="companyID" id="companyID" value='<sec:authorize access="hasRole('Key Manager')">${sessionScope.user.companyId}</sec:authorize>' />
		<div class="floatR">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel" id="cancel"><i class="icon-remove-circle"></i> <span id="close">Cancel</span></a>
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
							<td><input type="text" name="companyName" id="companyName" value="<sec:authorize access="hasRole('Key Manager')">${sessionScope.user.companyName}</sec:authorize>" maxlength="40" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
							<th>Token Label</th>
							<td><input type="text" name="tokenLabel" id="tokenLabel" value="<sec:authorize access="hasRole('Key Manager')">${sessionScope.user.tokenLabel}</sec:authorize>" maxlength="40" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
						  </tr>
						  <tr>
						  	<th>Key Type</th>
							<td>
								<select name="keyTypeCode" id="keyTypeCode">
									<option value="SECRET">Secret Key</option>
									<option value="PAIRKEY">Pair Key</option>
								</select>
							</td>
							<th>Key Sub Type</th>
							<td>
								<select name="keySubtypeCode" id="keySubtypeCode">
									<option value="DES">DES</option>
									<option value="SEED">SEED</option>
									<option value="AES">AES</option>
								</select>
							</td>
						  </tr>
						  <tr>
						  	<th>Key Role</th>
							<td>
								<select name="keyRoleCode" id="keyRoleCode">
									<option value="SD Key">SD Key</option>
									<option value="Transport Key">Transport Key</option>
									<option value="Application Key">Application Key</option>
								</select>
							</td>
						  	<th>Key Version</th>
							<td><input type="text" name="keyVersion" id="keyVersion" maxlength="40"/></td>
						  </tr>
						  <tr>
						  	<th>Key Index</th>
							<td><input type="text" name="keyIndex" id="keyIndex" maxlength="9" placeholder="no input" disabled/></td>
						  	<th>Key Identifier</th>
							<td><input type="text" name="keyIdentifier" id="keyIdentifier" /></td>			
						  </tr>
						  <tr>
						  	<th></th>
							<td></td>
						  	<th>Key Definition</th>
							<td><input type="text" name="keyDefinition" id="keyDefinition" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>							
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
									<div id="privateKeySection">
									<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> <span id="privateKeyLabel">Secret Key</span></div>
									<table class="Table-Style_pop2  txtL" style="width:250px;">
										<colgroup>
											<col style="width:175px"/>
											<col style="width:225px"/>
										</colgroup>
										<tbody>
											<tr>
											  	<th>Key Label</th>
												<td><textarea rows="2" name="keyLabel" id="keyLabel" maxlength="128" placeholder="auto input" style="background-color:#F0F0F0;width:90%;"></textarea></td>
											</tr>
											<tr>
											  	<th>Key Size (bits)</th>
												<td>
													<select name="keySize" id="keySize">
														<option value="64">64</option>
														<option value="128">128</option>
														<option value="192" selected>192</option>
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
									<table class="Table-Style_pop2  txtL" style="width:250px; margin:3px 0px 3px 0px;">
										<colgroup>
											<col style="width:133px"/>
											<col style="width:133px"/>
											<col style="width:133px"/>
										</colgroup>
										<tbody>
											<tr>
												<td><input type="checkbox" name="importable" id="importable" checked> Importable</td>
												<td><input type="checkbox" name="exportable" id="exportable" checked> Exportable</td>
												<td><input type="checkbox" name="sensitive" id="sensitive" checked> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="encrypt" id="encrypt" checked> Encrypt</td>
												<td><input type="checkbox" name="decrypt" id="decrypt" checked> Decrypt</td>
												<td><input type="checkbox" name="encryptDecrypt" id="encryptDecrypt" checked> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="wrap" id="wrapp" checked> Wrap</td>
												<td><input type="checkbox" name="unwrap" id="unwrap" checked> Unwrap</td>
												<td><input type="checkbox" name="wrapUnwrap" id="wrapUnwrap" checked> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="sign" id="sign" checked> Sign</td>
												<td><input type="checkbox" name="verify" id="verify" checked> Verify</td>
												<td><input type="checkbox" name="derive" id="derive" checked> Derive</td>
											</tr>
										</tbody>
									</table>
									</div>
								</td>
								<td>
									<div id="publicKeySection" style="display:none;">
									<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> Public Key</div>
									<table class="Table-Style_pop2  txtL" style="width:250px;">
										<colgroup>
											<col style="width:175px"/>
											<col style="width:225px"/>
										</colgroup>
										<tbody>
											<tr>
											  	<th>Key Label</th>
												<td><textarea rows="2" name="keyLabel1" id="keyLabel1" maxlength="128" placeholder="auto input" style="background-color:#F0F0F0;width:90%;"></textarea></td>
											</tr>
											<tr>
											  	<th>Key Size (bits)</th>
												<td>
													<select name="keySize1" id="keySize1">
														<option value="64">64</option>
														<option value="128">128</option>
														<option value="192" selected>192</option>
													</select>
												</td>
											</tr>
											<tr>
											  	<th>KCV Algorithm</th>
												<td>
													<select name="keyAlgorithm1" id="keyAlgorithm1">
														<option value="EIGHTZEROS">EIGHTZEROS</option>
													</select>
												</td>
											</tr>
										</tbody>
									</table>
									<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
										<colgroup>
											<col style="width:133px"/>
											<col style="width:133px"/>
											<col style="width:133px"/>
										</colgroup>
										<tbody>
											<tr>
												<td><input type="checkbox" name="importable1" id="importable1" checked> Importable</td>
												<td><input type="checkbox" name="exportable1" id="exportable1" checked> Exportable</td>
												<td><input type="checkbox" name="sensitive1" id="sensitive1" checked> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="encrypt1" id="encrypt1" checked> Encrypt</td>
												<td><input type="checkbox" name="decrypt1" id="decrypt1" checked> Decrypt</td>
												<td><input type="checkbox" name="encryptDecrypt" id="encryptDecrypt" checked> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="wrap1" id="wrap1" checked> Wrap</td>
												<td><input type="checkbox" name="unwrap1" id="unwrap1" checked> Unwrap</td>
												<td><input type="checkbox" name="wrapUnwrap1" id="wrapUnwrap1" checked> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" name="sign1" id="sign1" checked> Sign</td>
												<td><input type="checkbox" name="verify1" id="verify1" checked> Verify</td>
												<td><input type="checkbox" name="derive1" id="derive1" checked> Derive</td>
											</tr>
										</tbody>
									</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Key Profile Information
					</div>
					<div  id="secret_key_profile_section">
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
							<td><input type="text" name="keyProfileID" id="keyProfileID" value="${profileID}" maxlength="40" placeholder="auto input" style="background-color:#F0F0F0;"/></td>
							<th>Profile Name</th>
							<td><input type="text" name="keyProfileName" id="keyProfileName" maxlength="128"/></td>
						  </tr>
						  <tr>
						  	<th>Profile Version</th>
							<td><input type="text" name="keyProfileVersion" id="keyProfileVersion" value="1.0.0" placeholder="auto input" maxlength="512" style="background-color:#F0F0F0;"/></td>
							<th>Description</th>
							<td><input type="text" name="description" id="description" maxlength="512"/></td>
						  </tr>						  
						</tbody>
					</table>
					</div>
					
					<div  id="pair_key_profile_section" style="display:none;">
					<table style="border:1px solid #D8D8D8; background:transparent;">
						<colgroup>
							<col style="width:400px"/>
							<col style="width:400px"/>
						</colgroup>
						<tbody>
							<tr>
								<td>
									<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> Private Key Profile</div>
									<table class="Table-Style_pop2  txtL" style="width:250px;">
										<colgroup>
											<col style="width:175px"/>
											<col style="width:225px"/>
										</colgroup>
										<tbody>
											<tr>
											  	<th>Profile ID</th>
												<td><input type="text" name="keyProfileID" id="keyProfileID" value="${profileID}" maxlength="40" placeholder="system input" style="background-color:#F0F0F0;"/></td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td><input type="text" name="keyProfileVersion" id="keyProfileVersion" value="1.0.0" placeholder="system input" maxlength="512" style="background-color:#F0F0F0;"/></td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td><input type="text" name="keyProfileName" id="keyProfileName" maxlength="128"/></td>
											</tr>
											<tr>
												<th>Description</th>
												<td><input type="text" name="description" id="description" maxlength="512"/></td>
											</tr>
										</tbody>
									</table>
								</td>
								<td>
									<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> Public Key Profile</div>
									<table class="Table-Style_pop2  txtL" style="width:250px;">
										<colgroup>
											<col style="width:175px"/>
											<col style="width:225px"/>
										</colgroup>
										<tbody>
											<tr>
											  	<th>Profile ID</th>
												<td><input type="text" name="keyProfileID1" id="keyProfileID1" maxlength="40" placeholder="system input" style="background-color:#F0F0F0;"/></td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td><input type="text" name="keyProfileVersion1" id="keyProfileVersion1" value="1.0.0" placeholder="system input" maxlength="512" style="background-color:#F0F0F0;"/></td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td><input type="text" name="keyProfileName1" id="keyProfileName1" maxlength="128"/></td>
											</tr>
											<tr>
												<th>Description</th>
												<td><input type="text" name="description1" id="description1" maxlength="512"/></td>
											</tr>
										</tbody>
									</table>									
								</td>
							</tr>
						</tbody>
					</table>						
					</div>
					
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
							<td><input type="text" name="effectiveStartDateStr" id="effectiveStartDateStr" maxlength="10" readonly/></td>
							<th>Owner</th>
							<td><input type="text" name="keyOwner" id="keyOwner" value="<sec:authorize access="hasRole('Key Manager')">${sessionScope.user.companyId}</sec:authorize>" maxlength="128" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
						  </tr>
						  <tr>
						  	<th>End Date</th>
							<td><input type="text" name="effectiveEndDateStr" id="effectiveEndDateStr" maxlength="10" readonly/></td>
							<th>Mode</th>
							<td>
								<select name="testMode" id="testMode">
									<option value="PRODUCTION">PRODUCTION</option>
									<option value="TEST">TEST</option>
								</select>
							</td>
						  </tr>	
						  <tr>
						  	<th>Revocation Date</th>
							<td><input type="text" name="revocationDateStr" id="revocationDateStr" maxlength="10" readonly/></td>
							<th>Status</th>
							<td>
								<select name="activeStatusCode" id="activeStatusCode">
									<option value="Active">Active</option>
									<option value="Inactive">Inactive</option>
								</select>
							</td>
						  </tr>						  
						</tbody>
					</table>
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Save as Key Profile
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Key Profile XML</th>
								<td colspan="3"><input type="checkbox" name="clientDownloadable" id="clientDownloadable"> Download Key Profile XML File</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>		
		</form>
		<!-- 
		<div class="floatR">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save1"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		-->
	</div>
	
	<form id="xmlFrm" name="xmlFrm" action="/KeyProfile/downloadProfile.do" method="get">
		<input type="hidden" name="tokenID" id="tokenID" value="" />
		<input type="hidden" name="id" id="id" value="" />
		<input type="hidden" name="version" id="version" value="" />
		<input type="hidden" name="id1" id="id1" value="" />
		<input type="hidden" name="version1" id="version1" value="" />
	</form>
	
	<form id="xmlFrm2" name="xmlFrm2" action="/KeyProfile/downloadProfile.do" method="get">
		<input type="hidden" name="id" id="id2" value="" />
		<input type="hidden" name="version" id="version2" value="" />
	</form>
</body>
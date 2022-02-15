<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Key Management</title>
	
	<script type="text/javascript" src="/js/cert/certreq_new.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Request Certificate</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		
		<div class="floatR">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel" id="cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>
					<form id="form2" name="form2" action="/CertReq/popup/reqeustCerti" method="post">
					<input type="hidden" name="keyProfileID" id="profileID" value="" />
					<input type="hidden" name="keyProfileVersion" id="profileVersion" value="" />
					<input type="hidden" name="companyID" id="companyID" value="" />	
					<input type="hidden" name="keySubject" id="keySubject" value="" />				
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Input Certificate Information
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
								<th>Certificate Name</th>
								<td><input type="text" name="certificateName" id="certificateName" /></td>
								<th>Brand</th>
								<td>
									<select name="brandTypeInt" id="brandTypeInt">
										<option value="210">VISA VSDC</option>
										<option value="220">EMV M/Chip4</option>
										<option value="230">EMV Jsmart</option>
										<option value="240">EMV Visa Local</option>
										<option value="241">EMV Master Local</option>
										<option value="250">Amex</option>
										<option value="260">Diners</option>
										<option value="270">PBOC</option>
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
								<th>BIN</th>
								<td><input type="text" name="binNumber" id="binNumber" maxlength="6" placeholder="number only"/></td>
								<th>Tracking No.</th>
								<td><input type="text" name="trackingNumber" id="trackingNumber" maxlength="6" placeholder="number only:6 digits"/></td>
							</tr>
							<tr id="amex_file_type" style="display:none;">
								<th></th>
								<td></td>
								<th>AMEX File Type</th>
								<td>
									<select name="amexFileType" id="amexFileType">
										<option value="PRODUCTION">PRODUCTION</option>
										<option value="TEST">TEST</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>IPK Index</th>
								<td><input type="text" name="ipkIndex" id="ipkIndex" maxlength="10" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
								<th>IPK Length</th>
								<td><input type="text" name="ipkSize" id="ipkSize" maxlength="9" placeholder="auto input" readonly style="background-color:#F0F0F0;"/></td>
							</tr>
							<tr>
								<th>Expiration Date</th>
								<td><input type="text" name="expireDateStr" id="expireDateStr" maxlength="10" readonly/></td>
								<th>Request Date</th>
								<td><input type="text" name="requestDateStr" id="requestDateStr" maxlength="10" readonly/></td>
							</tr>
						</tbody>
					</table>
					</form>
					
					<div id="selectFlag" style="color:red;margin:10px 0px 5px 0px;font-size:11px;">Please, select key profile.</div>
					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Select Pair Key Profile
						
						<div class="floatR" style="margin-bottom:15px;">
						<a href="javascript:findProfile();" class="button_pop" title="search" id="search"><i class="icon-search"></i></a>
						<a href="javascript:cancelSearch();" class="button_pop" title="Cancel" id="cancelSearch"><i class="icon-remove"></i></a>
						</div>
					</div>
					
					<!-- Search Criteria -->
					<div id="main_search_box" style="width:95%;min-width:650px;padding-right:20px;">
						<table id="search_box" style="width:98%">
							<colgroup>
								 <col style="width:15%"/>
						         <col style="width:18%"/>
						         <col style="width:15%"/>
						         <col style="width:18%"/>
						         <col style="width:16%"/>
						         <col style="width:18%"/>
							</colgroup>
							<tbody>
								<tr>									
									<th>Company</th>
									<td>
										<sec:authorize access="hasRole('KMS Manager')">
												<input type="text" name="scompanyName" id="scompanyName" value=""/>
										</sec:authorize>	
										<sec:authorize access="hasRole('Key Manager')">
												<input type="text" name="scompanyName" id="scompanyName" value="${sessionScope.user.companyName}" readonly style="background-color:#F0F0F0;"/>
										</sec:authorize>									
									</td>
									<th>Key Type</th>
									<td><input type="text" name="skeyTypeCode" id="skeyTypeCode" value="PUBLIC" readonly style="background-color:#F0F0F0;"/>
									</td>
									<th>Key Role</th>
									<td><input type="text" name="skeyRoleCode" id="skeyRoleCode" value="Issuer RSA Key" readonly style="background-color:#F0F0F0;"/>
									</td>
								</tr>
							</tbody>
						</table>
						<table id="search_box" style="width:98%">
							<colgroup>
								 <col style="width:15%"/>
						         <col style="width:18%"/>
						         <col style="width:15%"/>
						         <col style="width:18%"/>
						         <col style="width:16%"/>
						         <col style="width:18%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>Profile ID</th>
									<td><input type="text" name="sprofileID" id="sprofileID" /></td>
									<th>Profile Name</th>
									<td><input type="text" name="sprofileName" id="sprofileName" /></td>
									<th>Profile Version</th>
									<td><input type="text" name="sprofileVersion" id="sprofileVersion" /></td>
								</tr>
							</tbody>
						</table>
						<table id="search_box" style="width:98%">
							<colgroup>
								 <col style="width:15%"/>
						         <col style="width:18%"/>
						         <col style="width:15%"/>
						         <col style="width:18%"/>						         
								 <col style="width:15%"/>
								 <col style="width:3%"/>
								 <col style="width:15%"/>
								 <col style="width:1%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>Key Label</th>
									<td><input type="text" name="skeyLabel" id="skeyLabel" /></td>
									<!-- 
									<th>Period</th>
									<td>
										<select name="periodType" id="periodType">
											<option value="">-- All --</option>
											<option value="01">Start Date</option>
											<option value="02">End Date</option>
											<option value="03">Revocation Date</option>
										</select>	
									</td>
									<td><input type="text" name="startDate" id="startDate" readonly/></td>
									<td align="center">~</td>
									<td><input type="text" name="endDate" id="endDate" readonly/></td>
									-->
								</tr>
							</tbody>
						</table>					
						
					</div>
					<!-- // Search Criteria -->
					
					<!-- Grid Table Section -->
					<div id="main-con-content" style="display:none;margin-top:10px;margin-bottom:10px;">
						<table class="Table-Style_main" id="jq_grid"></table>
						
						<!-- Paging -->
						<div class="ft_btm mgt10 mgb20 txtC" id="pager"></div>
						<!-- //Paging -->
						<div style="color:red; margin-top:3px;font-size:11px;">
							* Please, double click to select a row.
						</div>
					</div>
					<!-- /Grid Table Section -->
					
					<div id="keyProfile">
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
								<td><span id="companyName"></span></td>
								<th>Token Label</th>
								<td><span id="tokenLabel"></span></td>
							  </tr>		
							  <tr>
							  	<th>Key Type</th>
								<td><span id="keyTypeCode"></span></td>
								<th>Key Subtype</th>
								<td><span id="keySubtypeCode"></span></td>
							  </tr>	
							  <tr>
							  	<th>Key Version</th>
								<td><span id="keyVersion"></span></td>
								<th>Key Role</th>
								<td><span id="keyRoleCode"></span></td>
							  </tr>	
							  <tr>
							  	<th>Key Identifier</th>
								<td><span id="keyIdentifier"></span></td>
								<th>Key Index</th>
								<td><span id="keyIndex"></span></td>
							  </tr>					  
							  </tbody>
						</table>
						
						<table style="border:1px solid #D8D8D8; background:transparent;">
							<colgroup>
								<col style="width:400px"/>
								<col style="width:400px"/>
							</colgroup>
							<tbody>
								<tr>
									<td>
										<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> Private Key</div>
										<table class="Table-Style_pop2  txtL" style="width:250px;">
											<colgroup>
												<col style="width:175px"/>
												<col style="width:225px"/>
											</colgroup>
											<tbody>
												<tr>
												  	<th>Key Label</th>
													<td><textarea rows="2" id="keyLabel1" style="width:100%;" readonly></textarea></td>
												</tr>
												<tr>
												  	<th>Key Size</th>
													<td><span id="keySize1"></span></td>
												</tr>
												<tr>
												  	<th>KCV Algorithm</th>
													<td><span id="keyAlgorithm1"></span></td>
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
												<td><input type="checkbox" disabled id="importable1"/> Importable</td>
												<td><input type="checkbox" disabled id="exportable1"/> Exportable</td>
												<td><input type="checkbox" disabled id="sensitive1"/> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="encrypt1"/> Encrypt</td>
												<td><input type="checkbox" disabled id="decrypt1"/> Decrypt</td>
												<td><input type="checkbox" disabled id="enDecrypt1"/> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="wraps1"/> Wrap</td>
												<td><input type="checkbox" disabled id="unwrap1"/> Unwrap</td>
												<td><input type="checkbox" disabled id="wrapUnwrap1"/> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="sign1"/> Sign</td>
												<td><input type="checkbox" disabled id="verify1"/> Verify</td>
												<td><input type="checkbox" disabled id="derive1"/> Derive</td>
											</tr>
										</tbody>
									</table>
									</td>
									<td>
										<div style="color:#24a8c1; font-size:11px; padding:3px 0px 0px 5px;"><i class="icon-circle-blank"></i> Public Key</div>
										<table class="Table-Style_pop2  txtL" style="width:250px;">
											<colgroup>
												<col style="width:175px"/>
												<col style="width:225px"/>
											</colgroup>
											<tbody>
												<tr>
												  	<th>Key Label</th>
													<td><textarea rows="2" id="keyLabel" style="width:100%;" readonly></textarea></td>
												</tr>
												<tr>
												  	<th>Key Size</th>
													<td><span id="keySize"></span></td>
												</tr>
												<tr>
												  	<th>KCV Algorithm</th>
													<td><span id="keyAlgorithm"></span></td>
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
												<td><input type="checkbox" disabled id="importable"/> Importable</td>
												<td><input type="checkbox" disabled id="exportable"/> Exportable</td>
												<td><input type="checkbox" disabled id="sensitive"/> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="encrypt"/> Encrypt</td>
												<td><input type="checkbox" disabled id="decrypt"/> Decrypt</td>
												<td><input type="checkbox" disabled id="enDecrypt"/> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="wraps"/> Wrap</td>
												<td><input type="checkbox" disabled id="unwrap"/> Unwrap</td>
												<td><input type="checkbox" disabled id="wrapUnwrap"/> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" disabled id="sign"/> Sign</td>
												<td><input type="checkbox" disabled id="verify"/> Verify</td>
												<td><input type="checkbox" disabled id="derive"/> Derive</td>
											</tr>
										</tbody>
									</table>
									</td>
								</tr>
							</tbody>
						</table>
						
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
												<td><span id="keyProfileID1"></span></td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td><span id="keyProfileVersion1"></span></td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td><span id="keyProfileName1"></span></td>
											</tr>
											<tr>
												<th>Description</th>
												<td><span id="description1"></span></td>
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
												<td><span id="keyProfileID"></span></td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td><span id="keyProfileVersion"></span></td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td><span id="keyProfileName"></span></td>
											</tr>
											<tr>
												<th>Description</th>
												<td><span id="description"></span></td>
											</tr>
										</tbody>
									</table>									
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
							  	<th>Start Date</th>
								<td><span id="effectiveStartDate"></span></td>
								<th>Owner</th>
								<td><span id="owner"></span></td>
						  </tr>
						  <tr>
							  	<th>End Date</th>
								<td><span id="effectiveEndDate"></span></td>
								<th>Mode</th>
								<td><span id="testModeCode"></span></td>
						  </tr>	
						  <tr>
						  	<th>Revocation Date</th>
							<td><span id="revocationDate"></span></td>
							<th>Status</th>
							<td><span id="statusCode"></span></td>
						  </tr>						  
						</tbody>
					</table>
					</div>
				</li>
			</ul>
		</div>
		
	</div>
	<form id="detailFrm" name="detailFrm" action="/CertReq/popup/detail.do" method="post">
		<input type="hidden" name="certiUID" id="certiUID" value="" />
	</form>
</body>
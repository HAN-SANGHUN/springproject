<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/cert/certreq_detail.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Certificate Request Detail</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		<div class="floatR">	
			<a href="javascript:download('certi');" class="button_pop" title="Input File Download" id="inputFileDownload"><i class="icon-download"></i> Input File Download</a>	
			<a href="javascript:download('hash');" class="button_pop" title="Hash Value Download" id="hashValueDownload"><i class="icon-download"></i> Hash Value Download</a>		
			<a href="javascript:window.close();" class="button_pop" title="Close" id="close"><i class="icon-remove-circle"></i> Close</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>					
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Certificate Request Information
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
								<td>${cert.certificateName}</td>
								<th>Brand</th>
								<td>${cert.brandTypeCode}</td>
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
								<td>${cert.binNumber}</td>
								<th>Tracking No.</th>
								<td>${cert.trackingNumber}</td>
							</tr>
							<tr>
								<th>IPK Index</th>
								<td>${cert.ipkIndex}</td>
								<th>IPK Length</th>
								<td>${cert.ipkSize}</td>
							</tr>
							<tr>
								<th>Expiration Date</th>
								<td>${cert.expireDateStr}</td>
								<th>Request Date</th>
								<td>${cert.requestDateStr}</td>
							</tr>
						</tbody>
					</table>
									
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Pair Key Profile Information
					</div>
					
					
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
								<td>${pubKey.companyName}</td>
								<th>Token Label</th>
								<td>${pubKey.tokenLabel}</td>
							  </tr>
							  <tr>
							  	<th>Key Type</th>
								<td>${pubKey.keyTypeCode}</td>
								<th>Key Subtype</th>
								<td>${pubKey.keySubtypeCode}</td>
							  </tr>	
							  <tr>
							  	<th>Key Version</th>
								<td>${pubKey.keyVersion}</td>
								<th>Key Role</th>
								<td>${pubKey.keyRoleCode}</td>
							  </tr>	
							  <tr>
							  	<th>Key Identifier</th>
								<td>
									${pubKey.keyIdentifier} 
									<c:if test="${not empty pubKey.keyDefinition}">
									(${pubKey.keyDefinition})
									</c:if>
								</td>
								<th>Key Index</th>
								<td>${pubKey.keyIndex}</td>
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
													<td>${priKey.keyLabel}</td>
												</tr>
												<tr>
												  	<th>Key Size</th>
													<td>${priKey.keySize}</td>
												</tr>
												<tr>
												  	<th>KCV Algorithm</th>
													<td>${priKey.keyAlgorithm}</td>
												</tr>
											</tbody>
										</table>
										
										<c:set var="attr" value="${priKey.keyValueAttribute}" />
										<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
										<colgroup>
											<col style="width:133px"/>
											<col style="width:133px"/>
											<col style="width:133px"/>
										</colgroup>
										<tbody>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.importable}">checked</c:if> disabled id="importable1"/> Importable</td>
												<td><input type="checkbox" <c:if test="${attr.exportable}">checked</c:if> disabled id="exportable1"/> Exportable</td>
												<td><input type="checkbox" <c:if test="${attr.sensitive}">checked</c:if> disabled id="sensitive1"/> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.encrypt}">checked</c:if> disabled id="encrypt1"/> Encrypt</td>
												<td><input type="checkbox" <c:if test="${attr.decrypt}">checked</c:if> disabled id="decrypt1"/> Decrypt</td>
												<td><input type="checkbox" <c:if test="${attr.encryptDecrypt}">checked</c:if> disabled id="enDecrypt1"/> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.wrap}">checked</c:if> disabled id="wraps1"/> Wrap</td>
												<td><input type="checkbox" <c:if test="${attr.unwrap}">checked</c:if> disabled id="unwrap1"/> Unwrap</td>
												<td><input type="checkbox" <c:if test="${attr.wrapUnwrap}">checked</c:if> disabled id="wrapUnwrap1"/> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.sign}">checked</c:if> disabled id="sign1"/> Sign</td>
												<td><input type="checkbox" <c:if test="${attr.verify}">checked</c:if> disabled id="verify1"/> Verify</td>
												<td><input type="checkbox" <c:if test="${attr.derive}">checked</c:if> disabled id="derive1"/> Derive</td>
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
													<td>${pubKey.keyLabel}</td>
												</tr>
												<tr>
												  	<th>Key Size</th>
													<td>${pubKey.keySize}</td>
												</tr>
												<tr>
												  	<th>KCV Algorithm</th>
													<td>${pubKey.keyAlgorithm}</td>
												</tr>
											</tbody>
										</table>
										<c:set var="attr" value="${pubKey.keyValueAttribute}" />
										<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
										<colgroup>
											<col style="width:133px"/>
											<col style="width:133px"/>
											<col style="width:133px"/>
										</colgroup>
										<tbody>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.importable}">checked</c:if> disabled id="importable"/> Importable</td>
												<td><input type="checkbox" <c:if test="${attr.exportable}">checked</c:if> disabled id="exportable"/> Exportable</td>
												<td><input type="checkbox" <c:if test="${attr.sensitive}">checked</c:if> disabled id="sensitive"/> Sensitive</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.encrypt}">checked</c:if> disabled id="encrypt"/> Encrypt</td>
												<td><input type="checkbox" <c:if test="${attr.decrypt}">checked</c:if> disabled id="decrypt"/> Decrypt</td>
												<td><input type="checkbox" <c:if test="${attr.encryptDecrypt}">checked</c:if> disabled id="enDecrypt"/> Encrypt/Decrypt</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.wrap}">checked</c:if> disabled id="wraps"/> Wrap</td>
												<td><input type="checkbox" <c:if test="${attr.unwrap}">checked</c:if> disabled id="unwrap"/> Unwrap</td>
												<td><input type="checkbox" <c:if test="${attr.wrapUnwrap}">checked</c:if> disabled id="wrapUnwrap"/> Wrap/Unwrap</td>
											</tr>
											<tr>
												<td><input type="checkbox" <c:if test="${attr.sign}">checked</c:if> disabled id="sign"/> Sign</td>
												<td><input type="checkbox" <c:if test="${attr.verify}">checked</c:if> disabled id="verify"/> Verify</td>
												<td><input type="checkbox" <c:if test="${attr.derive}">checked</c:if> disabled id="derive"/> Derive</td>
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
												<td>${priKey.keyProfileID}</td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td>${priKey.keyProfileVersion}</td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td>${priKey.keyProfileName}</td>
											</tr>
											<tr>
												<th>Description</th>
												<td>${priKey.description}</td>
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
												<td>${pubKey.keyProfileID}</td>
											</tr>
											<tr>
											  	<th>Profile Version</th>
												<td>${pubKey.keyProfileVersion}</td>
											</tr>
											<tr>
											  	<th>Profile Name</th>
												<td>${pubKey.keyProfileName}</td>
											</tr>
											<tr>
												<th>Description</th>
												<td>${pubKey.description}</td>
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
							<td>${pubKey.effectiveStartDateStr}</td>
							<th>Owner</th>
							<td>${pubKey.companyID}</td>
						  </tr>	
						  <tr>
						  	<th>End Date</th>
							<td>${pubKey.effectiveEndDateStr}</td>
							<th>Mode</th>
							<td>${pubKey.testMode}</td>
						  </tr>	
						  <tr>
						  	<th>Revocation Date</th>
							<td>${pubKey.revocationDateStr}</td>
							<th>Status</th>
							<td>${pubKey.activeStatusCode}</td>
						  </tr>							  
						</tbody>
					</table>
					</div>
				</li>
			</ul>
		</div>
	</div>
	
	<form id="fileFrm" name="fileFrm" action="/CertReq/downloadFile.do" method="post">
		<input type="hidden" name="certiUID" id="certiUID" value="${cert.certificateUID}" />
		<input type="hidden" name="fileType" id="fileType" value="" />
	</form>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
	<title>Key Profile Detail</title>
	<script type="text/javascript" src="/js/profile/profile_detail.js"></script>
	<script>
		$(function(){
			$('#tabs').tabs();
		});
	</script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Key Profile Detail</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		<div class="floatR" style="padding-right:5px;">			
			<a href="javascript:download();" class="button_pop" title="Download Key Profile XML File" id="downloadProfile"><i class="icon-download"></i> Download Key Profile XML File</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Close</a>
		</div>
		
		<div class="mgt10 mgb20" style="padding-top:30px;">
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
						  	<th>Company</th>
							<td>${profile.companyName}</td>
							<th>Token Label</th>
							<td>${profile.tokenLabel}</td>
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
						  	<th>Profile ID</th>
							<td>${profile.keyProfileID}</td>
							<th>Profile Name</th>
							<td>${profile.keyProfileName}</td>
						  </tr>	
						  <tr>
						  	<th>Profile Version</th>
							<td>${profile.keyProfileVersion}</td>
							<th>Description</th>
							<td>${profile.description}</td>
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
							<td><fmt:formatDate value="${profile.effectiveStartDate}" pattern="yyyy-MM-dd" /></td>
							<th>Owner</th>
							<td>${profile.companyID}</td>
						  </tr>	
						  <tr>
						  	<th>End Date</th>
							<td><fmt:formatDate value="${profile.effectiveEndDate}" pattern="yyyy-MM-dd" /></td>
							<th>Mode</th>
							<td>${profile.testMode}</td>
						  </tr>	
						  <tr>
						  	<th>Revocation Date</th>
							<td><fmt:formatDate value="${profile.revocationDate}" pattern="yyyy-MM-dd" /></td>
							<th>Status</th>
							<td>${profile.activeStatusCode}</td>
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
						  	<th>Key Type</th>
							<td>${profile.keyTypeCode}</td>
							<th>Key Subtype</th>
							<td>${profile.keySubtypeCode}</td>
						  </tr>	
						  <tr>
						  	<th>Key Version</th>
							<td>${profile.keyVersion}</td>
							<th>Key Role</th>
							<td>${profile.keyRoleCode}</td>
						  </tr>	
						  <tr>
						  	<th>Key Identifier</th>
							<td>${profile.keyIdentifier} <c:if test="${not empty profile.keyDefinition}">(${profile.keyDefinition})</c:if></td>
							<th>Key Index</th>
							<td><c:if test="${profile.keyIndex gt 0}">${profile.keyIndex}</c:if></td>
						  </tr>		
						  <tr>
						  	<th>KCV Algorithm</th>
							<td>${profile.keyAlgorithm}</td>
							<th>Key Size</th>
							<td>${profile.keySize}</td>
						  </tr>	
						  <tr>
						  	<th>Key Label</th>
							<td colspan="3">${profile.keyLabel}</td>
						  </tr>						  
						</tbody>
					</table>
					<c:set var="attr" value="${profile.keyValueAttribute}" />
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:133px"/>
							<col style="width:133px"/>
							<col style="width:133px"/>
							<col style="width:133px"/>
							<col style="width:133px"/>
							<col style="width:135px"/>
						</colgroup>
						<tbody>
							<tr>
								<td><input type="checkbox" <c:if test="${attr.importable}">checked</c:if> disabled /> Importable</td>
								<td><input type="checkbox" <c:if test="${attr.exportable}">checked</c:if> disabled /> Exportable</td>
								<td><input type="checkbox" <c:if test="${attr.sensitive}">checked</c:if> disabled /> Sensitive</td>
								<td><input type="checkbox" <c:if test="${attr.encrypt}">checked</c:if> disabled /> Encrypt</td>
								<td><input type="checkbox" <c:if test="${attr.decrypt}">checked</c:if> disabled /> Decrypt</td>
								<td><input type="checkbox" <c:if test="${attr.encryptDecrypt}">checked</c:if> disabled /> Encrypt/Decrypt</td>
							</tr>
							<tr>
								<td><input type="checkbox" <c:if test="${attr.wrap}">checked</c:if> disabled /> Wrap</td>
								<td><input type="checkbox" <c:if test="${attr.unwrap}">checked</c:if> disabled /> Unwrap</td>
								<td><input type="checkbox" <c:if test="${attr.wrapUnwrap}">checked</c:if> disabled /> Wrap/Unwrap</td>
								<td><input type="checkbox" <c:if test="${attr.sign}">checked</c:if> disabled /> Sign</td>
								<td><input type="checkbox" <c:if test="${attr.verify}">checked</c:if> disabled /> Verify</td>
								<td><input type="checkbox" <c:if test="${attr.derive}">checked</c:if> disabled /> Derive</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
		
		<div style="padding-top:10px; width:98%;" id="tabs">
			<ul>
				<li><a href="#profile_xml">Key Profile XML</a></li>
				<!-- <li><a href="#Revision_History">Revision History</a></li> -->
			</ul>
			<div id="profile_xml">
				<table class="Table-Style_pop2  txtL" style="width:100%;">
					<colgroup>
						<col style="width:100%;"/>
					</colgroup>
					<tbody>						
					<tr>
						<td style="padding:5px;">
							<table>									
							<c:set var="xmls" value="${profile.keyProfileXMLFileWeb}" />
							<c:set var="length" value="1" />
							<c:set var="last" value="${fn:length(xmls)}" />
							<c:forEach var="xml" items="${xmls}">
								<tr>									
								<c:choose>
									<c:when test="${length eq 1 or length eq 2 or length eq last}">
										<td style="border:0;"><c:out value="${xml}" /></td>
									</c:when>
									<c:otherwise>
										<td style="padding-left:20px;border:0;"><c:out value="${xml}" /></td>
									</c:otherwise>
								</c:choose>
								<c:set var="length" value="${length+1}" />
								</tr>
							</c:forEach>
							</table>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			
			
			<%-- <div id="Revision_History">
				<table class="Table-Style_pop2  txtL" style="width:100%;">
					<colgroup>
						<col style="width:10%;"/>
						<col style="width:15%;"/>
						<col style="width:35%;"/>
						<col style="width:20%;"/>
						<col style="width:20%;"/>
					</colgroup>
					<tbody>						
					<tr>
						<th align="center">No</th>
						<th align="center">Version</th>
						<th align="center">Description</th>
						<th align="center">Created by</th>
						<th align="center">Update Date</th>						
					</tr>
					<c:set var="sequence" value="1" />
					<c:forEach var="hist" items="${profile.revisionHistory}">
					<tr>
						<td align="center">${sequence}</td>
						<td align="center">${hist.keyProfileVersion}</td>
						<td>${hist.description}</td>
						<td>${hist.updateUserID}</td>
						<td align="center"><fmt:formatDate value="${hist.updateDate}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<c:set var="sequence" value="${sequence+1}" />
					</c:forEach>
					</tbody>
				</table>
			</div> --%>
			
			
		</div>		
	</div>
	
	
<form id="xmlFrm" name="xmlFrm" action="/KeyProfile/downloadProfile.do" method="get">
		<input type="hidden" name="tokenID" id="tokenID" value="" />
		<input type="hidden" name="id" id="id" value="${profile.keyProfileID}" />
		<input type="hidden" name="version" id="version" value="${profile.keyProfileVersion}" />
		<input type="hidden" name="id1" id="id1" value="" />
		<input type="hidden" name="version1" id="version1" value="" />
	</form>
	
	<form id="xmlFrm2" name="xmlFrm2" action="/KeyProfile/downloadProfile.do" method="get">
		<input type="hidden" name="id" id="id2" value="" />
		<input type="hidden" name="version" id="version2" value="" />
	</form>
	
	
</body>
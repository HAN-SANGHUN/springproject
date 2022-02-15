<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/cert/certmgmt_register.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Register Certificate</h1>
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
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Input Certificate Profile of Customer
					</div>
					<form:form id="form2" name="form2" action="/CertMgmt/popup/registerCert.do" method="post" enctype="multipart/form-data" modelAttribute="certiRegisterDTO">
					<input type="hidden" id="certificateUID" name="certificateUID" value="${cert.certificateUID}"/>
					
					<c:if test="${not empty message}">
						<span style="color:red; font-size:11px;">${message}</span>
					</c:if>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Certificate File</th>
								<td colspan="3"><input type="file" name="certiFile" id="certiFile" class="top"/></td>
							</tr>
						</tbody>
					</table>
					<!-- 
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Hash Value File</th>
								<td colspan="3"><input type="file" name="hashFile" id="hashFile" class="top"/></td>
							</tr>
						</tbody>
					</table>	
					-->				
					</form:form>						
					
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
								<td><span id="certificateName">${cert.certificateName}</span></td>
								<th>Brand</th>
								<td><span id="brandTypeCode">${cert.brandTypeCode}</span></td>
							</tr>
							<tr>
								<th>BIN</th>
								<td>${cert.binNumber}</td>
								<th>Company</th>
								<td>${cert.companyName}</td>
							</tr>
							<tr>
								<th>Tracking No.</th>
								<td>${cert.trackingNumber}</td>
								<th>CA Serial Number</th>
								<td>${cert.caSerialNumber}</td>
							</tr>
							<tr>
								<th>IPK Index</th>
								<td>${cert.ipkIndex}</td>
								<th>CA PK Index</th>
								<td>${cert.caPublicKeyIndex}</td>
							</tr>
							<tr>
								<th>IPK Length</th>
								<td>${cert.ipkSize}</td>
								<th>CA PK Length</th>
								<td>${cert.caPublicKeyLength}</td>
							</tr>
							<tr>
								<th>Expiration Date</th>
								<td>${cert.expireDateStr}</td>
								<th>Request Date</th>
								<td>${cert.requestDateStr}</td>
							</tr>
							<tr>
								<th>BIN Status</th>
								<td>${cert.binStatusCode}</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>					
					<table class="Table-Style_pop2  txtL" style="width:800px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Certificate Hash Value</th>
								<td colspan="3"><textarea rows="3"  style="width:614px;padding:2px 2px 2px 2px" name="hashValue" id="hashValue"  readonly>${cert.hashValue}</textarea> </td>
							</tr>							
							<tr>
								<th>Certificate Exponent Value</th>
								<td colspan="3"><textarea rows="3" style="width:614px;padding:2px 2px 2px 2px" name="exponentValue" id="exponentValue" readonly>${cert.exponentValue}</textarea> </td>
							</tr>
						</tbody>
					</table>
		
				</li>
			</ul>
		</div>
		
	</div>
	
</body>
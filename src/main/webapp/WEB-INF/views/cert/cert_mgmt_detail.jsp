<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Key Management</title>
	
	<script type="text/javascript" src="/js/cert/certmgmt_detail.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Certificate Detail</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		
		<div class="floatR">
			<a href="javascript:download();" class="button_pop" title="Download" id="download"><i class="icon-download"></i> Download Certificate</a>
			<a href="javascript:window.close();" class="button_pop" title="Close" id="close"><i class="icon-remove-circle"></i> Close</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>
					
					<input type="hidden" name="keyProfileID" id="profileID" value="" />
					<input type="hidden" name="keyProfileVersion" id="profileVersion" value="" />
					<input type="hidden" name="companyID" id="companyID" value="" />					
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
							<tr>
								<th>Certificate File</th>
								<td colspan="3"><textarea rows="15" style="width:614px;padding:2px 2px 2px 2px" name="certiResBinaryFile" id="certiResBinaryFile" readonly>${cert.certiResBinaryFileStr}</textarea> </td>
							</tr>
						</tbody>
					</table>		
				</li>
			</ul>
		</div>		
	</div>
	
	<form id="fileFrm" name="fileFrm" action="/CertMgmt/downloadFile.do" method="post">
		<input type="hidden" name="certiUID" id="certiUID" value="${cert.certificateUID}" />
	</form>
</body>
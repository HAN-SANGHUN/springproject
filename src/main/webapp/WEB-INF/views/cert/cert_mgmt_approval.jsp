<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Key Management</title>
	<script type="text/javascript" src="/js/cert/certmgmt_approval.js"></script>
	
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Approval Certificate</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:85%;">
		
		<div class="floatR">
			<a href="javascript:doApproval();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel" id="cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>
					<form  id="form1" name="form1" method="post">
					<input type="hidden" name="keyProfileID" id="profileID" value="" />
					<input type="hidden" name="keyProfileVersion" id="profileVersion" value="" />
					<input type="hidden" name="companyID" id="companyID" value="" />			
					<input type="hidden" id="certificateUID" name="certificateUID" value="${cert.certificateUID}"/>		
					<div style="color:#24a8c1; margin-top:15px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Approval Certificate 
					</div>
					
					 <div style="color:#24a8c1; margin-top:15px;margin-bottom:45px;font-size:12px;font-weight:bold;">
						<div style="float:left">Certificate Approval</div>
						<div style="float:left;margin-left:10px;width:200px">
									<select name="approvalCD" id="approvalCD" >
										<option value="approval" <c:if test="${cert.approvalCD eq 'approval'}">selected</c:if>>approval</option>
										<option value="reject" <c:if test="${cert.approvalCD eq 'reject'}">selected</c:if>>reject</option>
									</select>
						 </div>
					</div>
					</form>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Certificate ID</th>
								<td><span id="certificatUID">${cert.certificateUID}</span></td>
								<th>Certificate Name</th>
								<td><span id="certificateName">${cert.certificateName}</span></td>
							</tr>
							<tr>
								<th>Certificate Type</th>
								<td><span id="certificateTypeCode">${cert.certificateTypeCode}</span></td>
								<th>Brand</th>
								<td><span id="brandTypeCode">${cert.brandTypeCode}</span></td>
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
								<td><span id="binNumber">${cert.binNumber}</span></td>
								<th>Company</th>
								<td><span id=companyName>${cert.companyName}</span></td>
							</tr>
							<tr>
								<th>Tracking No.</th>
								<td><span id="trackingNumber">${cert.trackingNumber}</span></td>
								<th>CA Certificate Name</th>
								<td><span id="certificateName">${cert.certificateName}</span></td>
							</tr>
							<tr>
								<th>IPK Index</th>
								<td><span id="ipkIndex">${cert.ipkIndex}</span></td>
								<th>CA PK Index</th>
								<td><span id="" ></span></td>
							</tr>
							<tr>
								<th>IPK Length</th>
								<td><span id="ipkSize">${cert.ipkSize}</span></td>
								<th>CA PK Length</th>
								<td><span id=""></span></td>
							</tr>
							<tr>
								<th>Expiration Date</th>
								<td><span id="expireDate">${cert.expireDateStr}</span></td>
								<th>Request Date</th>
								<td><span id="requestDate">${cert.requestDateStr}</span></td>
							</tr>
							<tr>
								<th>Mode</th>
								<td><span id="testMode">${cert.testMode}</span></td>
								<th>Status</th>
								<td><span id="activeStatusCode">${cert.activeStatusCode}</span></td>
							</tr>
						</tbody>
					</table>
					<form id="form2" name="form2" action="/CertMgmt/popup/reqeustCerti" method="post">
					<table class="Table-Style_pop2  txtL" style="width:800px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:225px"/>
							<col style="width:175px"/>
							<col style="width:225px"/>
						</colgroup>
						<tbody>
							<tr>
								<th>Issuer Public Key Value</th>
								<td colspan="3"> ${cert.exponentValue} </td>
								 
							</tr>
							<tr>
								<th>Certificate Hash Value</th>
								<td colspan="3"> ${cert.hashValue} </td>
							</tr>
							<tr>
								<th>Module Size</th>
								<td colspan="3"> ${cert.modulesSize} </td>
								 
							</tr>
						</tbody>
					</table>
					
					</form>
		
				</li>
			</ul>
		</div>
		
	</div>
	
</body>
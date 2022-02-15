<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>:::: Key Management ::::</title>
	
	<script type="text/javascript" src="/js/cert/certmgmt.js"></script>
</head>

<body>
	<!-- Main Contents -->
	<div id="main_box">
		<!-- Header -->
		<div id="main-con_top">
			<h1 lang="code_title">Certificate Management</h1>
			<!-- Buttons -->
			<ul>
				<li>
					<a href="javascript:doClear();" class="button" title="Search Clear"><i class="icon-remove-circle"></i> Search Clear</a>
					<a href="javascript:doSearch();" class="button" title="Search"><i class="icon-search"></i> Search</a>
					<a href="javascript:doRegister();" class="button" title="Register"><i class="icon-plus-sign-alt"></i> Register</a>
			   		<a href="javascript:doExcel();" class="button" title="Excel"><i class="icon-file-alt"></i> Excel</a>
				</li>
			</ul>
			<!-- //Buttons -->
		</div>
		<!-- //Header -->
		
		<!-- Search Section -->
		<form name="form1" id="form1" action="/CertMgmt/excel.do" method="post">
		<input type="hidden" name="sortorder" id="sortorder" value=""  />
		<input type="hidden" name="sortname" id="sortname" value=""  />
		<sec:authorize access="hasRole('Key Manager')">
		<input type="hidden" name="companyId" id="comapnyId" value="${sessionScope.user.companyId}" />
		</sec:authorize>
		
		<div id="main_search_box">
			<ul>
				<!-- Search Main -->
				<li>
					<div id="main_con_search">
						<table id="search_box" style="width:90%">
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
									<td><input type="text" name="companyName" id="companyName" /></td>
									<th>IPK Index</th>
									<td><input type="text" name="ipkIndex" id="ipkIndex" value="0" /></td>
									<th>BIN</th>
									<td><input type="text" name="binNumber" id="binNumber" /></td>
								</tr>
							</tbody>
						</table>
						<table id="search_box" style="width:90%">
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
									<th>Brand</th>
									<td>
										<select name="brandTypeCode" id="brandTypeCode">
											<option value="">-- All --</option>
											<option value="VISA VSDC">VISA VSDC</option>
											<option value="EMV M/Chip4">EMV M/Chip4</option>
											<option value="EMV Jsmart">EMV Jsmart</option>
											<option value="EMV Visa Local">EMV Visa Local</option>
											<option value="EMV Master Local">EMV Master Local</option>
											<option value="Amex">Amex</option>
											<option value="Diners">Diners</option>
											<option value="PBOC">PBOC</option>
										</select>
									</td>
									<th>Tracking No</th>
									<td><input type="text" name="trackingNo" id="trackingNumber" /></td>
									<th>Certificate File</th>
									<td><input type="text" name="certiResFileName" id="certiResFileName" /></td>
								</tr>
							</tbody>
						</table>
						<table id="search_box" style="width:90%">
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
									<th>BIN Status</th>
									<td>
										<select name="binStatusCode" id="binStatusCode">
											<option value="">-- All --</option>
											<option value="DRAFT">DRAFT</option>
											<option value="USE">USE</option>
											<option value="DISPOSAL">DISPOSAL</option>
										</select>
									</td>
									<th>Period</th>
									<td><select name="periodType" id="periodType">
											<option value="">-- All --</option>
											<option value="01">Request Date</option>
											<option value="02">Expire Date</option>
											<option value="03">Registration Date</option>
										</select>
										
									</td>
									<td colspan="3">
									<input type="text" name="startDate" id="startDate" readonly style="width:70px;"/>
									 ~
									<input type="text" name="endDate" id="endDate" readonly style="width:70px;"/>
									</td>
								</tr>
							</tbody>
						</table>
						
						
						<div class="search_box_btn">
							<span id="btn_more" class="button_s bt_red csp" title="More">
								More <i class="icon-circle-arrow-down"></i>
							</span>
						</div>
					</div>
				</li>
				<!-- //Search Main -->
				
				<!-- Search More -->
				<li>
					<div id="main-con_search_list" style="display:none;">
						<table id="search_box" style="width:90%">
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
									<th>IPK Length</th>
									<td><input type="text" name="ipkSize" id="ipkSize"   value="0"/></td>
									<th>Hash Value</th>
									<td><input type="text" name="hashValue" id="hashValue" /></td>									
								</tr>
							</tbody>
						</table>						
						<div class="search_box_btn">
							<span id="btn_turnup" class="button_s bt_red csp" title="Turnup">Turnup <i class="icon-circle-arrow-up"></i></span>
						</div>
					</div>
				</li>
				<!-- //Search More -->
			</ul>
		</div>
		</form>
		<!-- //Search Section -->
		
		<!-- Grid Table Section -->
		<div id="main-con-content">
			<table class="Table-Style_main" id="jq_grid"></table>
			
			<!-- Paging -->
			<div class="ft_btm mgt10 mgb20 txtC" id="pager"></div>
			<!-- //Paging -->
		</div>
		<!-- Grid Table Section -->
	</div>
	<!-- //Main Contents -->
</body>
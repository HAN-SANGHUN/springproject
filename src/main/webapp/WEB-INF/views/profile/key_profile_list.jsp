<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>:::: Key Management ::::</title>
	
	<script type="text/javascript" src="/js/profile/profile.js"></script>
</head>

<body>
	<!-- Main Contents -->
	<div id="main_box">
		<!-- Header -->
		<div id="main-con_top">
			<h1 lang="code_title">Key Profile Management</h1>
			<!-- Buttons -->
			<ul>
				<li>
					<a href="javascript:doClear();" class="button" title="Search Clear"><i class="icon-remove-circle"></i> Search Clear</a>
					<a href="javascript:doSearch();" class="button" title="Search"><i class="icon-search"></i> Search</a>
					<a href="javascript:doGenerate();" class="button" title="Search"><i class="icon-plus-sign-alt"></i> Generate</a>
					<a href="javascript:doEdit();" class="button" title="Edit"><i class="icon-edit"></i> Edit</a>
					<a href="javascript:doImport();" class="button" title="Import"><i class="icon-chevron-sign-right"></i> Import</a>
					<a href="javascript:doExport();" class="button" title="Export"><i class="icon-chevron-sign-left"></i> Export</a>
			   		<a href="javascript:doDelete();" class="button" title="Delete"><i class="icon-trash"></i> Delete</a>
			   		<a href="javascript:doExcel();" class="button" title="Excel"><i class="icon-file-alt"></i> Excel</a>
				</li>
			</ul>
			<!-- //Buttons -->
		</div>
		<!-- //Header -->
		
		<!-- Search Section -->
		<form name="form1" id="form1" action="/KeyProfile/excel.do" method="post">
		<input type="hidden" name="sortorder" id="sortorder" value=""  />
		<input type="hidden" name="sortname" id="sortname" value=""  />
		<input type="hidden" name="keyUsageIndicatorValue" id="keyUsageIndicatorValue" value="" />
		<input type="hidden" name="keyValueFlag" id="keyValueFlag" value="" />
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
									<th>Token Label</th>
									<td><input type="text" name="tokenLabel" id="tokenLabel" /></td>									
									<th>Key Label</th>
									<td><input type="text" name="keyLabel" id="keyLabel" /></td>
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
									<!-- <th>Profile ID</th>
									<td><input type="text" name="profileID" id="profileID" /></td>
									<th>Profile Name</th>
									<td><input type="text" name="profileName" id="profileName" /></td>
									<th>Profile Version</th>
									<td><input type="text" name="profileVersion" id="profileVersion" /></td>
								</tr> -->
								
								<!-- modify by shifei -->
									<th>Profile ID</th>
									<td><input type="text" name="keyProfileID" id="keyProfileID" /></td>
									<th>Profile Name</th>
									<td><input type="text" name="keyProfileName" id="keyProfileName" /></td>
									<th>Profile Version</th>
									<td><input type="text" name="keyProfileVersion" id="keyProfileVersion" /></td>
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
									<th>Key Type</th>
									<td>
										<select name="keyTypeCode" id="keyTypeCode">
											<option value="">-- All --</option>
											<option value="SECRET">SECRET</option>
											<option value="PRIVATE">PRIVATE</option>
											<option value="PUBLIC">PUBLIC</option>
										</select>
									</td>
									<th>Key Sub Type</th>
									<td>
										<select name="keySubtypeCode" id="keySubtypeCode">
											<option value="">-- All --</option>
											<option value="DES">DES</option>
											<option value="SEED">SEED</option>
											<option value="AES">AES</option>
										</select>
									</td>									
									<th>Status</th>
									<td>
										<select name="activeStatusCode" id="activeStatusCode">
											<option value="">-- All --</option>
											<option value="Active">Active</option>
											<option value="Inactive">Inactive</option>
										</select>
									</td>
								</tr>
							</tbody>
						</table>						
						<table id="search_box" style="width:90%">
							<colgroup>
								 <col style="width:15%"/>
								 <col style="width:18%"/>
								 <col style="width:14%"/>
								 <col style="width:3%"/>
								 <col style="width:14%"/>
								 <col style="width:36%"/>
							</colgroup>
							<tbody>																
								<tr>
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
									<td><input type="text" name="endDate" id="endDate" readonly /></td>
									<td></td>
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
		
		<div style="color:white; font-size:12px;margin-top: 25px">
			<img src="<c:url value='../img/main/icon_key_asymmetric.png' />"  /> Pair Key
			&nbsp;&nbsp;
			<img src="<c:url value='../img/main/icon_key_symmetric.png'  />" /> Secret Key
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<c:url value='../img/main/icon_key_novalue.png' />" />&nbsp;<img src="<c:url value='../img/main/icon_key_novalue2.png' />" /> Not Setting Value is Gray
			&nbsp;&nbsp;
			<img src="<c:url value='../img/main/icon_key_asymmetric.png' />" />&nbsp;<img src="<c:url value='../img/main/icon_key_symmetric.png' />" /> Setting Value is Yellow
		</div>
		<!-- Grid Table Section -->
		<div id="main-con-content">
			<table class="Table-Style_main" id="jq_grid"></table>
			
			<!-- Paging -->
			<div class="ft_btm mgt10 mgb20 txtC" id="pager"></div>
			<!-- //Paging -->
		</div>
		<!-- Grid Table Section -->
		<div style="color:white; font-size:12px;">
			1. Please, click cell of "Key Type" to set key value.<br/>
			2. To see details(Key Info, XML, Revision History, etc.), please double-click on the desired line.
		</div>
	</div>
	<!-- //Main Contents -->
</body>
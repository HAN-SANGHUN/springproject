<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>:::: Key Management ::::</title>
	<script type="text/javascript" src="/js/ss/user/user.js"></script>
</head>

<body>
	<!-- Main Contents -->
	<div id="main_box">
		<!-- Header -->
		<div id="main-con_top">
			<h1 lang="code_title">User Management</h1>
			<!-- Buttons -->
			<ul>
				<li>
					<a href="javascript:doClear();" class="button" title="Search Clear"><i class="icon-remove-circle"></i> Search Clear</a>
					<a href="javascript:doSearch();" class="button" title="Search"><i class="icon-search"></i> Search</a>
					<a href="javascript:doGenerate();" class="button" title="Add"><i class="icon-plus-sign-alt"></i> Add</a>
					<a href="javascript:doEdit();" class="button" title="Edit"><i class="icon-edit"></i> Edit</a>
			   		<a href="javascript:doDelete();" class="button" title="Delete"><i class="icon-trash"></i> Delete</a>
			   		<a href="javascript:doExcel();" class="button" title="Excel"><i class="icon-file-alt"></i> Excel</a>
				</li>
			</ul>
			<!-- //Buttons -->
		</div>
		<!-- //Header -->
		
		<!-- Search Section -->
		<form name="form1" id="form1" action="/UserSetting/excel.do" method="post">
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
									<th>User ID</th>
									<td><input type="text" name="userId" id="userId" /></td>
									<th>User Name</th>
									<td><input type="text" name="userName" id="userName" /></td>
									<th></th>
									<td></td>
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
									<th>User Status</th>
									<td>
										<select name="activeStatusCode" id="activeStatusCode">
											<option value="">-- All --</option>
											<option value="Active">Active</option>
											<option value="Inactive">Inactive</option>
										</select>
									</td>
									<th><sec:authorize access="hasRole('KMS Manager')">User Role</sec:authorize></th>
									<td>
										<sec:authorize access="hasRole('KMS Manager')">
										<select name="userRoleCode" id="userRoleCode">
											<option value="">-- All --</option>
											<option value="KMS Manager">KMS MANAGER</option>
											<option value="Key Manager">KEY MAANGER</option>
										</select>
										</sec:authorize>
									</td>
									<th></th>
									<td></td>
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
									<th>Company Name</th>
									<td><input type="text" name="companyName" id="companyName" /></td>
									<th>Token Label</th>
									<td><input type="text" name="tokenLabel" id="tokenLabel" /></td>
								</tr>
							</tbody>
						</table>
						
					</div>
				</li>
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
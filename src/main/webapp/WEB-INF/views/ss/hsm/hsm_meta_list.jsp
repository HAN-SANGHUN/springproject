<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>:::: Key Management ::::</title>
	
	<script type="text/javascript" src="/js/ss/hsm/hsm.js"></script>
</head>

<body>
	<!-- Main Contents -->
	<div id="main_box">
		<!-- Header -->
		<div id="main-con_top">
			<h1 lang="code_title">HSM Management</h1>
			<!-- Buttons -->
			<ul>
				<li>
					<c:if test="${activateFlag == false}">
						<a href="javascript:doActivateHsm();" class="button" title="Activate HSM"><i class="icon-cogs"></i> Activate HSM</a>
					</c:if>
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
		<form name="form1" id="form1" action="/HsmSetting/excel.do" method="post">
		<input type="hidden" name="sortorder" id="sortorder" value=""  />
		<input type="hidden" name="sortname" id="sortname" value=""  />
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
								 <col style="width:15%"/>
								 <col style="width:18%"/>
							</colgroup>
							<tbody>																
								<tr>
									<th>HSM Label</th>
									<td><input type="text" name="hsmLabel" id="hsmLabel" /></td>
									<th>Status</th>
									<td>
										<select name="statusCode" id="statusCode">
											<option value="">-- All --</option>
											<option value="Active">Active</option>
											<option value="Inactive">Inactive</option>
										</select>
									</td>	
									<th>IP Address</th>
									<td><input type="text" name="ipAddress" id="ipAddress" /></td>	
									<th>&nbsp;</th>
									<td>&nbsp;</td>																
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
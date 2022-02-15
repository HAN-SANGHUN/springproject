<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Import Key Profile</title>
	<script type="text/javascript" src="/js/profile/profile_import.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Import Key Profile</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main" style="height:80%;">
		<form:form id="form2" name="form2" action="/KeyProfile/popup/parseProfile.do" method="post" enctype="multipart/form-data" modelAttribute="profileImportDTO">
		<div class="floatR">
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>							
					<div style="color:#24a8c1; padding-top:20px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Import Key Profile
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:625px" />
						</colgroup>
						<tbody>
							<tr>
								<th colspan="2">
									<input type="radio" name="importKeyType" value="SECRETKEY" checked> Secret Key Import
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="importKeyType" value="PAIRKEY"> Pair Key Import
								</th>
							</tr>							
						</tbody>
					</table>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:625px" />
						</colgroup>
						<tbody>
							<tr>
								<th><label id="secretKeyLabel">Import Secret Key Profile</label></th>
								<td><input type="file" name="file" id="file" class="top"/></td>
							</tr>							
						</tbody>
					</table>
					<div id="public_key_import_section" style="display:none;">
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:5px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:625px" />
						</colgroup>
						<tbody>
							<tr>
								<th><label>Import Public Key Profile</label></th>
								<td><input type="file" name="pubFile" id="pubFile" class="top" disabled/></td>
							</tr>							
						</tbody>
					</table>
					</div>
				</li>
			</ul>
		</div>
		</form:form>
		<div style="color:red; font-size:11px;">${message}</div>
	</div>
</body>
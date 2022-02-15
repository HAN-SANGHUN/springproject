<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Generate Key Profile</title>
	<script type="text/javascript" src="/js/profile/secret_key.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Set Key Value</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">
		<form id="form2" name="form2" action="/KeyProfile/popup/setSecretKeyValue.do" method="post">
		<input type="hidden" name="profileID" id="profileID" value="${profileID}" />
		<input type="hidden" name="profileVersion" id="profileVersion" value="${profileVersion}" />
		<input type="hidden" name="keySubtype" id="keySubtype" value="${keySubtypeCode}" />
		<input type="hidden" name="keySize" id="keySize" value="${keySize}" />
		<div class="floatR">
			<a href="javascript:autoGen();" class="button_pop" title="Auto-generation"><i class="icon-plus-sign-alt"></i> Auto-generation</a>
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> <span id="close">Cancel</span></a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>
					<div style="color:#24a8c1; padding-top:20px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Set Secret Key : 
						<c:choose>
							<c:when test="${keySubtypeCode eq 'DES' }">
								<c:choose>
									<c:when test="${keySize eq 64 }">DES</c:when>
									<c:when test="${keySize eq 128 }">DES2</c:when>
									<c:when test="${keySize eq 192 }">DES3</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>${keySubtypeCode}</c:otherwise>
						</c:choose>
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:3px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:275px" />
							<col style="width:175px"/>
							<col style="width:185px" />
						</colgroup>
						<tbody>
							<tr>
								<th>Key Label</th>
								<td colspan="3">${keyLabel}</td>
							</tr>
						</tbody>
							
					<!-- 	<tbody>
							<tr>
								<th>Raw Key</th>
								<td><input type="text" name="org_key" id="org_key" style="width:100%" maxlength="32" /></td>
							</tr>
						</tbody> -->
					
					</table>
					
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:3px;">
					<colgroup>
							<col style="width:175px"/>
							<col style="width:275px" />
							<col style="width:90px"/>
							<col style="width:90px"/>
							<col style="width:90px"/>
							<col style="width:90px" />
						</colgroup> 
						<tbody>
						<tr>
							<th>Raw Key</th>
							<td><input type="text" name="org_key" id="org_key" style="width:100%;" maxlength="64"/></td>
							<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:check_kcv_org('org_key', 'kcv');" class="button_pop" title="clear"><i class="icon-circle"></i> check</a></td>
							<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:org_key_clear();" class="button_pop" title="clear" id="modClear"><i class="icon-remove"></i> clear</a></td>
							<th>KCV</th>
							<td><input type="text" name="kcv" id="kcv" placeholder="auto input" disabled /></td>
						</tr>
						</tbody>
					</table>
						
					<div style="color:red; margin-top:15px;font-size:11px;">
							* Please, enter hex number [0-9],[A-F].
					</div>
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:3px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:275px" />
							<col style="width:90px"/>
							<col style="width:90px"/>
							<col style="width:90px"/>
							<col style="width:90px" />
						</colgroup> 
						<tbody>
							<tr>
								<th>Component 1</th>
								<td><input type="text" name="component1" id="component1" style="width:100%;" /></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:check_kcv('component1', 'kcv1');" class="button_pop" title="clear"><i class="icon-circle"></i> check</a></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:component1_clear();" class="button_pop" title="clear" id="modClear"><i class="icon-remove"></i> clear</a></td>
								<th>KCV</th>
								<td><input type="text" name="kcv1" id="kcv1" placeholder="auto input" disabled /></td>
							</tr>	
							<tr>
								<th>Component 2</th>
								<td><input type="text" name="component2" id="component2" style="width:100%;" /></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:check_kcv('component2', 'kcv2');" class="button_pop" title="clear"><i class="icon-circle"></i> check</a></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:component2_clear();" class="button_pop" title="clear" id="modClear"><i class="icon-remove"></i> clear</a></td>
								<th>KCV</th>
								<td><input type="text" name="kcv2" id="kcv2" placeholder="auto input" disabled /></td>
							</tr>	
							<tr>
								<th>Component 3</th>
								<td><input type="text" name="component3" id="component3" style="width:100%;" /></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:check_kcv('component3', 'kcv3');" class="button_pop" title="clear"><i class="icon-circle"></i> check</a></td>
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:component3_clear();" class="button_pop" title="clear" id="modClear"><i class="icon-remove"></i> clear</a></td>
								<th>KCV</th>
								<td><input type="text" name="kcv3" id="kcv3" placeholder="auto input" disabled/></td>
							</tr>	
							<tr>
								<td colspan="6" align="center">
									<input type="radio" name="secretRadio" value="invisible"> Key value is invisible
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="secretRadio" value="visible" checked> Key value is visiable
								</td>
							</tr>						
						</tbody>
					</table>
					
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
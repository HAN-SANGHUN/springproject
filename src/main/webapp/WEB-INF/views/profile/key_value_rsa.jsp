<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<head>
	<title>Generate Key Profile</title>
	<script type="text/javascript" src="/js/profile/pair_key.js"></script>
</head>

<body>
 	<!-- Header -->
   	<div id="pop-con_top">
       	<h1>Set Key Value</h1>
   	</div>
	<!-- //Header --> 
	
	<div id="pop-main">
		<form id="form2" name="form2" action="/KeyProfile/popup/setPairKeyValue.do" method="post">
		<input type="hidden" name="keySubject" id="keySubject" value="${keySubject}" />
		<input type="hidden" name="keyTypeCode" id="keyTypeCode" value="${keyTypeCode}" />
		<input type="hidden" name="keySubtypeCode" id="keySubtypeCode" value="${keySubtypeCode}" />
		<input type="hidden" name="keySize" id="keySize" value="${keySize}" />
		
		<div class="floatR" style="margin-bottom:20px;">
			<a href="javascript:autoGen();" class="button_pop" title="Auto-generation"><i class="icon-plus-sign-alt"></i> Auto-generation</a>
			<a href="javascript:doSubmit();" class="button_pop" title="Save" id="save"><i class="icon-save"></i> Save</a>
			<a href="javascript:window.close();" class="button_pop" title="Cancel"><i class="icon-remove-circle"></i> Cancel</a>
		</div>
		
		<div class="mgt10 mgb20">
			<ul>
				<li>					
					<table class="Table-Style_pop2  txtL" style="width:250px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:275px" />
							<col style="width:175px"/>
							<col style="width:180px" />
						</colgroup>
						<tbody>
							<tr>
								<th>Key Label</th>
								<td colspan="3">${keyLabel}</td>
							</tr>
						</tbody>						
					</table>
					<div style="color:#24a8c1; padding-top:20px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Set RSA Public Key
					</div>
					<div id="public_key_section">
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:15px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:500px" />
							<col style="width:125px" />
						</colgroup>
						<tbody>
							<tr>
								<th colspan="3">Enter Hex Component</th>
							<tr>
							<tr>
								<th>Modulus (n)</th>
								<td><input type="text" name="mod" id="mod" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:mod_clear();" class="button_pop" title="clear" id="modClear"><i class="icon-remove"></i> clear</a></td>
							</tr>	
							<tr>
								<th>Exponent (e)</th>
								<td><input type="text" name="exp" id="exp" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:exp_clear();" class="button_pop" title="clear" id="expClear"><i class="icon-remove"></i> clear</a></td>
							</tr>	
							<tr>
								<td colspan="3" align="center">
									<input type="radio" name="pubRadio" value="invisible"> Key value is invisible
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="pubRadio" value="visible" checked> Key value is visiable
								</td>
							</tr>					
						</tbody>
					</table>
					</div>
					
					<div style="color:#24a8c1; padding-top:20px;font-size:12px;font-weight:bold;">
						<i class="icon-chevron-sign-right"></i> Set RSA Private Key
					</div>
					<div id="private_key_section">
					<table class="Table-Style_pop2  txtL" style="width:250px; margin-top:15px;">
						<colgroup>
							<col style="width:175px"/>
							<col style="width:500px" />
							<col style="width:125px" />
						</colgroup>
						<tbody>
							<tr>
								<th colspan="3">Enter Hex Component</th>
							<tr>
							<tr>
								<th>CRT_P (Prime, p)</th>
								<td><input type="text" name="crtp" id="crtp" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:crtp_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>
							<tr>
								<th>CRT_Q (Prime, q)</th>
								<td><input type="text" name="crtq" id="crtq" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:crtq_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>
							<tr>
								<th>CRT_DP1 (dp)</th>
								<td><input type="text" name="crtdp1" id="crtdp1" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:crtdp1_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>
							<tr>
								<th>CRT_DQ1 (Dq)</th>
								<td><input type="text" name="crtdq1" id="crtdq1" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:crtdq1_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>
							<tr>
								<th>CRT_PQ1 (Coefficient)</th>
								<td><input type="text" name="crtpq1" id="crtpq1" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:crtpq1_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>
							<tr>
								<th>MOD_EXP (exponent, d)</th>
								<td><input type="text" name="modExp" id="modExp" style="width:100%;"/></td>								
								<td align="center" style="padding:3px 0px 3px 0px;"><a href="javascript:modExp_clear();" class="button_pop" title="clear"><i class="icon-remove"></i> clear</a></td>
							</tr>		
							<tr>
								<td colspan="3" align="center">
									<input type="radio" name="priRadio" value="invisible"> Key value is invisible
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="priRadio" value="visible" checked> Key value is visible
								</td>
							</tr>					
						</tbody>
					</table>
					</div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
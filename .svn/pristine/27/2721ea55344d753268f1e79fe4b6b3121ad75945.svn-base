<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>CPE Ordering Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">CPE Ordering Details</center>
					<br> <br> <form:form action="saveCpeOrderingDetails"
								method="post" commandName="cpeOrderingDTO">
								<table class="tableborderspacing">
									<tr>
										<td>Program Name</td>
										<td><form:select path="programName"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.programName}</option>											
										</form:select>
										</td>
									</tr>
									<tr>
										<td>Deal ID</td>
										<td><form:input path="dealId" disabled="${isDisabled}" /></td>
									</tr>
									<tr>
										<td>ICRL Required over Customer Infrastructure</td>
										<td><input type="checkbox" path="icrlRequiredOverInfra"
											disabled="${isDisabled}" /></td>
									</tr>
									<tr>
										<td>ICRL Option</td>
										<td><form:select path="icrlOption"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.icrlOption}</option>											
										</form:select></td>
									</tr>
									<tr>
										<td>Override CPE Installer/Maintainer</td>
										<td><input type="checkbox" path="overrideCPEInstaller"
											disabled="${isDisabled}" /></td>
									</tr>
									<tr>
										<td>Partner Name</td>
										<td><form:select path="partnerName"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.partnerName}</option>											
										</form:select></td>
									</tr>
									<tr>
										<td>CPE Installer</td>
										<td><form:select path="cpeInstaller"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.cpeInstaller}</option>											
										</form:select></td>
									</tr>
									<tr>
										<td>CPE Maintainer</td>
										<td><form:select path="cpeMaintainer"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.cpeMaintainer}</option>											
										</form:select></td>
									</tr>
									<tr>
										<td>Parent Bid Reference</td>
										<td><form:input path="partnerBidReference"
												disabled="${isDisabled}" /></td>
									</tr>
									<tr>
										<td>Call-Off Agent Name</td>
										<td><form:select path="callOffAgentName"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.callOffAgentName}</option>											
										</form:select></td>
									</tr>
									<tr>
										<td>Tax ID</td>
										<td><form:input path="taxId" disabled="${isDisabled}" />
										</td>
									</tr>
									<tr>
										<td>Customer Topology</td>
										<td><form:select path="customerTopology"
											 disabled="${isDisabled}">											
												<option value="">${cpeOrderingDTO.customerTopology}</option>											
										</form:select></td>
									</tr>

								</table>
							</form:form>
				</div>
				<!-- End of form -->
			</div>
			<!-- End of content_resize -->
		</div>
		<!-- End of content -->
	</div>
	<!-- End of main -->
</body>
</html>

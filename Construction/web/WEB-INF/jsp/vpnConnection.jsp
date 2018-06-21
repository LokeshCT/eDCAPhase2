<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base target="_parent" />
<title>VPN Connection</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<c:url value="/assets/css/custom/style.css" />"  rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">VPN Connection</center>
					<br />

					<form:form action="saveVpnDetails" method="post"
						commandName="vpnConnectionDTO">
						<table style="border-spacing:5px" >
							<tr>
								<td>Connection Name Name</td>
								<td><form:input path="connName" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Connection Type</td>
								<td><form:input path="connType" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Access Circuit</td>
								<td><form:input path="accessCkt"
										disabled="${isDisabled}" /></td>
							</tr>
								<tr>
								<td>Connection Bandwidth</td>
								<td><form:input path="connBandwidth"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>VPN Friendly Name</td>
								<td><form:input path="vpnFriendlyName"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Primary Connection</td>
								<td><form:input path="primaryConn"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Secure Extensions</td>
								<td><form:input path="secureExt"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Originator CIR</td>
								<td><form:input path="originatorCir"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Connectivity Type</td>
								<td><form:input path="connecType"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Multicast Services</td>
								<td><form:input path="multiServ"
										disabled="${isDisabled}" /></td>
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

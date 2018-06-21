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
<title>Service</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<c:url value="/assets/css/custom/style.css" />"  rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">Service</center>
					<br />

					<form:form action="saveVpnDetails" method="post"
						commandName="vpnServiceDTO">
						<table style="border-spacing:5px" >
							<tr>
								<td>Service Name</td>
								<td><form:input path="serviceName" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Bandwidth</td>
								<td><form:input path="bandwidth" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Burst Option</td>
								<td><form:input path="burstOption"
										disabled="${isDisabled}" /></td>
							</tr>
								<tr>
								<td>SLA Package</td>
								<td><form:input path="slaPackage"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Service Resilience</td>
								<td><form:input path="serviceResilience"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Product Name</td>
								<td><form:input path="productName"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<%
								  boolean valus = false;
								%>
								<td>Core Diversity</td>
								<c:if
									test="${vpnServiceDTO.coreDiversity=='Yes' }">
									<%
									  valus = true;
									%>
								</c:if>

								<td><input type="checkbox" path="coreDiversity"
									name="myCheckbox" <%= (valus)?"checked":"" %>
									value="<c:out value="${vpnServiceDTO.coreDiversity}" />"
									id="access" name="access" disabled="${isDisabled}" /></td>
								
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

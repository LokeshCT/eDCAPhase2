<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Configure Access Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Configure Access</center>
					<br> <form:form action="saveConfigureAccess" method="post">
							<table style="border-spacing: 5px">

								<tr>
									<td id="caption1" class="access_content">Feed Type is not received from
											Upstream</td>
								</tr>
						
								<tr>

									<td>Service Instance</td>
									<td><input value="${configureAccessDTO.serviceInstance}"
										disabled="${isDisabled}" /></td>

								</tr>
								<tr>
									<td>GPoP</td>
									<td><select id="GPoP" name="gPOP" disabled="${isDisabled}">

											<option id="dd1" value="${configureAccessDTO.gpop}">${configureAccessDTO.gpop}</option>

									</select>
									</td>


								</tr>
								<tr>
									<c:choose>
										<c:when test="${serviceInstance=='Primary'}">
											<td>Primary DVBPoP</td>
										</c:when>
										<c:otherwise>
											<td>Secondary DVBPoP</td>
										</c:otherwise>
									</c:choose>

									<td><input value="${configureAccessDTO.dvbpop}"
										disabled="${isDisabled}" /></td>

								</tr>
								<tr>

									<td>APoP</td>
									<td><select id="APoP" name="aPOP"disabled="${isDisabled}" >
											<option id="dd2" 
												value="${configureAccessDTO.apop}">${configureAccessDTO.apop}</option>
									</select></td>


								</tr>
								<tr>

									<td>Access Supplier</td>
									<td><select id="AccessSupplier" name="accessSupplier" disabled="${isDisabled}">
											<option id="dd3" 
												value="${configureAccessDTO.accessSupplier}">${configureAccessDTO.accessSupplier}</option>
									</select></td>

								</tr>
								<tr>

									<td>Access Enhanced Service Restoration</td>
									<td><select id="AccessEnhanced" name="accessSupplier" disabled="${isDisabled}">
											<option id="dd3" 
												value="${configureAccessDTO.accessEnhancedServiceRest}">${configureAccessDTO.accessEnhancedServiceRest}</option>
									</select></td>


								</tr>
								<tr>

									<td>Access Entry Point</td>
							
									<c:choose>
										<c:when test="${configureAccessDTO.accessEntryPoint=='None'}">
											<td><input value=" "
										disabled="${isDisabled}" /></td>
										</c:when>
										<c:otherwise>
											<td><input value="${configureAccessDTO.accessEntryPoint}"
										disabled="${isDisabled}" /></td>
										</c:otherwise>
									</c:choose>
									

								</tr>
								<tr>

									<td>BGP Sessions</td>
									<td><input value="${configureAccessDTO.bgpCount}"
										disabled="${isDisabled}" /></td>

								</tr>
								<tr>
									<td>GPoP Colour</td>
									<td><input value="${gpopColour}" disabled="${isDisabled}" /></td>

								</tr>
								<tr>
                                      
									<td>PORT Special Bid Reference</td>
									<c:choose>
										<c:when test="${configureAccessDTO.portSplBidId=='None'}">
											<td><input value=" "
										disabled="${isDisabled}" /></td>
										</c:when>
										<c:otherwise>
											<td><input value="${configureAccessDTO.portSplBidId}"
										disabled="${isDisabled}" /></td>
										</c:otherwise>
									</c:choose>
									

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

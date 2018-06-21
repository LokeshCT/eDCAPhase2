<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>ConfigureOtherDevices Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.hideSelectDevice {
	display: none;
}
</style>
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Configure Other Devices</center>
					<br><form:form action="configureOtherDevices" method="post"
							commandName="configureOtherDevicesDTO">

							<c:set var="size" value="${size}" scope="request" />


							<c:choose>
								<c:when test="${requestScope.size gt 0}">

									<table>
										<tr>
											<c:forEach var="cpeList" items="${configure}">
												<c:if
													test="${cpeList.serviceDemarcationOption eq 'TDM/CAS'}">
													<c:choose>
														<c:when test="${cpeList.lanSwitch eq 'No' }">
															<h3>
																<font color="blue"><label>Maximum one
																		Vegastream device should be configured as Lan Switch
																		has not been received from upstream.</label></font>
															</h3>
															</br>
														</c:when>
														<c:otherwise>
															<h3>
																<font color="blue"><label>The number of
																		Vegastream devices that can be ordered is dependent on
																		the VLAN Port</label></font>
															</h3>
															</br>
														</c:otherwise>
													</c:choose>
												</c:if>

											</c:forEach>
										</tr>
										</br>
										</br>

										<tr>
											<c:forEach var="List" items="${configureList}">

												<c:if
													test="${List.btradianzanalytics ne ' ' || List.btradianzmonitor ne ' '}">
													<c:choose>
														<c:when
															test="${List.btradianzanalytics eq 'Yes' || List.btradianzmonitor eq  'Yes'}">
															<h3>
																<font color="blue"><label>This order
																		requires atleast one Corvil device to be Configured
																		for this site </label></font>
															</h3>
														</c:when>
														<c:otherwise>
															<h3>
																<font color="blue"><label> No other
																		devices are required\available to be added in this
																		order. User can proceed to next page by clicking on
																		next button. </label></font>
															</h3>
														</c:otherwise>
													</c:choose>
												</c:if>

											</c:forEach>
										</tr>
										</br>
										</br>

										<tr>
											<th><label class="${hideSelectDevice}">Select
													Device to add</label></th>
											<td><form:select path="" id="DD1"
													class="${hideSelectDevice}" disabled="${isDisabled}">

													<c:forEach var="cpeList" items="${configure}">
														<c:if
															test="${cpeList.serviceDemarcationOption eq 'TDM/CAS'}">
															<form:option value="">Please Select</form:option>
															<form:option value="">VegaStream</form:option>
														</c:if>
													</c:forEach>
													<c:forEach var="List" items="${configureList}">
														<c:if
															test="${List.btradianzanalytics ne null || List.btradianzmonitor ne null}">
															<c:if
																test="${List.btradianzanalytics eq 'Yes' || List.btradianzmonitor eq  'Yes'}">
																<form:option value="">Please Select</form:option>
																<form:option value="Corvil" />
															</c:if>
														</c:if>
													</c:forEach>

												</form:select></td>
										</tr>

									</table>
								</c:when>
								<c:otherwise>

									<h3>
										<font color="blue"><label> No other devices are
												required\available to be added in this order. User can
												proceed to next page by clicking on next button. </label></font>
									</h3>

								</c:otherwise>
							</c:choose>

							<c:forEach var="cpeList" items="${cpeList}">
								<table border="1">
									<tr>
										<th></th>
										<th></th>
										<th>CpeUsage</th>
										<th>BaseRouter</th>
										<th>Chasis</th>
									</tr>
									<tr>
										<td><input type="button" value="Edit"></td>
										<td><input type="button" value="Delete"></td>
										<td><c:out value="${cpeList.cpeUsage}" /></td>
										<td><c:out value="${cpeList.baseRouter}" /></td>
										<td><c:out value="${cpeList.chassis}" /></td>
									</tr>
								</table>
							</c:forEach>
						</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



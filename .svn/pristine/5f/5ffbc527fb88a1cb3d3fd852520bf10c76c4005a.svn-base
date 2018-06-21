<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Site Address</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
       rel="stylesheet" type="text/css" />
<script type="text/javascript"
       src="<c:url value="/assets/js/plug-in/ajaxJquery.js" />"></script>
<link href="<c:url value="/assets/css/custom/style.css" />"
       rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/coinSlider.css" />"
       rel="stylesheet" type="text/css" />
<script type="text/javascript"
       src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js" />"></script>
<script type="text/javascript"
       src="<c:url value="/assets/js/custom/script.js" />"></script>
<script type="text/javascript"
       src="<c:url value="/assets/js/custom/coin-slider.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/siteAddress.js" />"></script>

</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Site Address</center>
					<br /> <form:form id="siteAddress" method="get"
							commandName="siteAddressDTO" onsubmit="return validate()">
							<table style="border-spacing: 5px">
								<tr>

									<td>Floor</td>
									<td><form:input id="floor" path="floor"
											disabled="${isDisabled}" onchange="validate()" /></td>
									<td><h3>
											<font color="red"> <span id="error_floor"></span></font>
										</h3></td>
								</tr>
								<tr>
									<td>Room</td>
									<td><form:input id="room" path="room"
											disabled="${isDisabled}" onchange="validate()" /></td>
									<td><h3>
											<font color="red"> <span id="error_room"></span></font>
										</h3></td>
								</tr>
								<tr>
									<td>Building Number</td>
									<td><form:input id="building" path="buildingNumber"
											disabled="true" /></td>
								</tr>
								<tr>
									<td>Address Line 1</td>
									<td><form:input id="address1" path="addressLine1"
											disabled="${isDisabled}" style="background-color:cyan"
											onchange="validate()" /></td>
									<td><h3>
											<font color="red"> <span id="error_address1"></span></font>
										</h3></td>
								</tr>
								<tr>
									<td>Address Line 2</td>
									<td><form:input id="address2" path="addressLine2"
											disabled="${isDisabled}" /></td>
								</tr>
								<tr>
									<td>City</td>
									<td><form:input id="city" path="city"
											disabled="${isDisabled}" style="background-color:cyan"
											onchange="validate()" /></td>
									<td><h3>
											<font color="red"> <span id="error_city"></span></font>
										</h3></td>
								</tr>
								<tr>
									<td>Post/ZipCode</td>
									<td><form:input id="zip" path="postcode"
											disabled="${isDisabled}" /></td>
								</tr>
								<tr>
									<c:set var="size" value="${size}" scope="request" />
									<td>State</td>
									<c:choose>
										<c:when test="${siteAddressDTO.country ne 'United States'}">
											<td><form:input path="state" /></td>
										</c:when>
										<c:otherwise>
											<td><form:select id="state" path="state"
													disabled="${isDisabled}">
													<c:forEach items="${siteCountry}" var="country">
														<form:option value="${country.state}">${country.state}</form:option>
													</c:forEach>
												</form:select></td>
										</c:otherwise>
									</c:choose>
									</tr>
							</table>
							<input type="submit" value="save"></input>
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

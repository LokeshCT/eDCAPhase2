<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/ajaxJquery.js" />"></script>
<base target="_parent" />
<title>Customer Contact Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
	src="<c:url value="/assets/js/custom/customerContactDetails.js" />"></script>
</head>
<!--Customer Contact Details JSP view for CustomerContactDetails  -->
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<center style="font-size: 30px">Customer Contact Details</center>
				<br>

					<div class="form" style="width: auto">
						<form:form class="tableform" name="CustomerContactDetails"
							method="post" commandName="customerContactDTO" id="test" 
							onsubmit="return checkTechnicalDetails()">
							<c:if
								test="${fn:length(customerContactDTO.reutersContactList)>0}">
								<h3>ReutersOrginators Contact Details</h3>

								<c:set value="${customerContactDTO.reutersContactList}"
									scope="request" var="id" />
								<c:set value="jobTitle" scope="request" var="hideJobTitle" />

								<%@ include file="customerContacts.jsp"%><br />
							</c:if>

							<c:if
								test="${fn:length(customerContactDTO.commercialContactList)>0}">
								<h3>Commercial Contact Details</h3>

								<c:set value="jobTitle" scope="request" var="hideJobTitle" />
								<c:set value="${customerContactDTO.commercialContactList}"
									scope="request" var="id" />

								<%@ include file="customerContacts.jsp"%><br />

							</c:if>

							<h3>Technical Contact Details</h3>

							<c:set value="jobTitlee" scope="request" var="hideJobTitle" />
						<%-- 	<c:set var="tech" value="${technicalContactList}" --%>
							<!-- 	scope="request"  /> -->

						<%-- 		<%@ include file="customerContacts.jsp"%> --%>
							<table>
								<thead>
									<tr>
										<th>ContactID</th>
										<th>FirstName</th>
										<th>LastName</th>
										<th>JobTitle</th>
										<th>Email</th>
										<th>Phone</th>
										<th>MobilePhone</th>
									</tr>
								</thead>
								<c:forEach var="tech"
									items="${customerContactDTO.technicalContactList}">
									<tr>
										<td><input name="contactId" type="hidden"
											value="${tech.contactId}" /></td>

										<td><input name="firstName" type="text"
											value="${tech.firstName}" /></td>
										<td><input name="lastName" type="text" s
											value="${tech.lastName}" /></td>
										<td><input name="jobTitle" type="text"
											value="${tech.jobTitle}" /></td>
										<td><input name="email" type="text" value="${tech.email}"
											id="email" onchange="checkTechnicalDetails()" /></td>
										<td><input name="phone" type="text" id="phone"
											value="${tech.phone}" onchange="checkTechnicalDetails()" /></td>
										<td><input name="mobilePager" type="text"
											id="mobilePager" value="${tech.mobilePager}"
											onchange="checkTechnicalDetails()" /></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><font color="red" /><span id="error_Email" /></td>
										<td><font color="red" /><span id="error_Phone" /></td>
										<td><font color="red" /><span id="error_MobilePager" /></td>
									</tr>
								</c:forEach>
							</table>
							<input type="submit" value="save" />
						</form:form>

					</div>
			</div>
		</div>
	</div>

</body>
</html>


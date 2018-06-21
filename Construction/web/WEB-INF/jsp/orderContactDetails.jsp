<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Order Contact Details</title>
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
	src="<c:url value="/assets/js/custom/orderContactDetails.js"/>"></script>
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">Order Contact Details</center>
					<br />


					<form:form  name="OrderContactDetails" method="post" commandName="orderContactDTO" 
						id="orderDetails" onsubmit="return checkForm()">

						<table>

							<tr>
								<td>FirstName</td>
								<td><form:input path="firstName" disabled="${isDisabled}"
										id="firstName" onchange="checkForm()" /></td>
								<td><h3>
										<font color="red"> <span id="error_fn"></span></font>
									</h3></td>

							</tr>

							<tr>
								<td>LastName</td>
								<td><form:input path="lastName" disabled="${isDisabled}"
										id="lastName" onchange="checkForm()" /></td>
								<td><h3>
										<font color="red"><span id="error_ln"></span></font>
									</h3></td>
							</tr>

							<tr>
								<td>Email</td>
								<td><form:input path="email" disabled="${isDisabled}"
										id="email" onchange="checkForm()" /></td>
								<td><h3>
										<font color="red"><span id="error_Email"></span></font>
									</h3></td>
							</tr>
							<tr>
								<td>Phone</td>
								<td><form:input path="phone" disabled="${isDisabled}"
										id="phone" onchange="checkForm()" /></td>
								<td><h3>
										<font color="red"><span id="error_Phone"></span></font>
									</h3></td>
							</tr>
							<tr>
								<td>MobilePhone</td>
								<td><form:input path="mobilePager" disabled="${isDisabled}"
										id="orderMobilePager" onchange="checkForm()" /></td>
								<td><h3>
										<font color="red"><span id="error_MobilePhone"></span></font>
									</h3></td>
							</tr>
						
						</table>


						<input type="submit" value="save" />
					</form:form>


				</div>
			</div>
		</div>
	</div>
</body>
</html>

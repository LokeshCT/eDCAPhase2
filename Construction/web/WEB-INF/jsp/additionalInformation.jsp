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
<title>Additional Information</title>
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
	src="<c:url value="/assets/js/custom/additionalInformation.js" />"></script>

</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 20px">Additional Information</center>
					<br />

					<form:form  id="additionalInformation" method="get"
						commandName="additionalInformationDTO" onsubmit=" return validate()"  >
						<table style="border-spacing:5px" >
							<tr>
								<td>Cot Notes</td>
								<td><form:textarea path="cotNotes" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Notes</td>
								<td><form:textarea path="notes" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Product Notes</td>
								<td><form:textarea path="productNotes"disabled="${isDisabled}"/></td>
							</tr>
							<tr>
								<td>Override Authorisation</td>
								<td><form:checkbox id="myCheckbox" path="overrideAuthorisation" value="Yes" disabled="${isDisabled}" onchange="validate()" /></td>
							    <td><font color="red"> <span id="error_address1"></span></font></td>
							</tr>
							<tr>
								<td>Authorisation Override Reason</td>
								<td><form:textarea id="authorisationReason" path="authorisationOverrideReason" disabled="${isDisabled}" onchange="validate()" /></td>
							<td><font color="red"> <span id="error_reason"></span></font></td>
							</tr>
							  
						</table>
						
						<input type="submit" value="save" />
						
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<base target="_parent" />
<title>Site Contact Details</title>
<base target="_parent" />
<title>Order Contact Details</title>
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
	src="<c:url value="/assets/js/custom/siteContactDetails.js" />"></script>
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<center style="font-size: 30px">Site Contact Details</center>
				<br />
				<div class="form" style="width: auto">
					<form:form method="post" name="SiteContactDetails"
						commandName="siteContactTypesDTO" id="myForm"
						onsubmit="return validate()">

						<c:if
							test="${fn:length(siteContactTypesDTO.primaryContactTypeList)>0}">
							<h3>Primary SiteContact Details</h3>

							<c:set value="primaryContactTypeList" var="primaryDetails" />

							<c:set value="${siteContactTypesDTO}" scope="request" var="id" />

							<%@ include file="siteContactDetailsHelper.jsp"%><br />
						</c:if>

						<c:if
								test="${fn:length(siteContactTypesDTO.secondaryContactTypeList)>0}">
								<h3>Secondary SiteContact Details</h3>

							<c:set value="secondaryContactTypeList" var="secondaryDetails" />

							<c:set value="${siteContactTypesDTO}" scope="request"
								var="secondaryContacts" />


							<c:set value="${siteContactTypesDTO}" scope="request"
								var="secondaryContacts" />

							<%@ include file="secondarySiteContactDetails.jsp"%><br />

						</c:if>

						<input type="submit" value="Save" />
					</form:form>
					<!-- End of form -->
				</div>
			</div>
			<!-- End of content_resize -->
		</div>
		<!-- End of content -->
	</div>
	<!-- End of main -->
</body>
</html>
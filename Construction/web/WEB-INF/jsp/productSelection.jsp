<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Product Selection</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css"/>"  rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">Product Selection</center>
					<br />

					<form:form action="saveCustomerDetails" method="post"
						commandName="productSelectionDTO">
						<table>
							<tr>
								<td>Product Name</td>
								<td><form:input path="productName" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>COS Model</td>
								<td><form:input path="cosModel" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>COS Policy</td>
								<td><form:input path="cosPolicy" disabled="${isDisabled}" />
								</td>
							</tr>

							<tr>
								<td>Customization Level</td>
								<td><form:input path="customizationLevel"
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
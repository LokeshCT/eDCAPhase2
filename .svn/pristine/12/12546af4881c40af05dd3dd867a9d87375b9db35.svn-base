<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Site Location Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
 rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width:auto">
					<center style="font-size: 30px">Site Location Details</center>
					<br/>
					<form:form action="saveCustomerDetails" method="post"
						commandName="siteLocationDetailsDTO">
					
								
							<%-- <c:if test="${fn:length(siteLocationDetailsDTO)>0}" > --%>
						
						<table>
							<tr>
								<td>Site Name</td>
								<td><form:textarea path="siteName" disabled="${isDisabled}" />
								</td>
							</tr>
							
							<tr>
								<td>Classic Site Id</td>
								<td><form:input path="classicSiteId" disabled="${isDisabled}" /></td>
							</tr>
							
							<tr>
								<td>SerClassicSiteId</td>
								<td><form:input path="serClassicSiteId" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Country</td>
								<td><form:input path="country" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td width='30%'>Is this site a Hub site for customer network performance reporting ?</td>
								<td><form:input path="hubSpoke" disabled="${isDisabled}" />
								</td>
							</tr>

						</table>
					<%-- 	</c:if> --%>
				
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

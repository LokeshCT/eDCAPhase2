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
 <script type="text/javascript" 	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>	
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width:auto">
					<center style="font-size: 30px">Site Locations</center>
					<br/>
					<form:form action="saveCustomerDetails" method="post"
						commandName="siteLocationDetailsDTO">
					<table class="bordered">
					<thead>					
							<tr>
							    <th>Edit</th>
							    <th>Site Location Id</th>
								<th>Site Name</th>
								
							</tr>
					</thead>
					<tbody>		
							<c:forEach var="site" items="${siteLocationDetailsList}">		
							<tr>
							    <td><input id="${site.siteLocationId}" type="button" onclick="setSiteLocationId(this.id);" value="Edit"/></td>
								
								<td ><c:out value="${site.siteLocationId}"/></td>
								<td><c:out value="${site.siteName}"/></td>
							</tr>
							
	                        </c:forEach>
	                  </tbody>      
						</table>
						<input type="hidden" value="${siteId}" id="siteId"></input>
					
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

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
<title>Site Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
	 <script type="text/javascript" 	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>	

<style type="text/css">
.hideServiceInstance
{
display:none;
}
</style>
	
</head>

<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Sites</center><br><br>		
				 <form:form action="saveSitePage" method="post"
							commandName="sitePage">


								<table class="bordered" border="1"  >
									<thead>
										<tr>
											<th></th>
											<th>Site ID</th>
											<th>DCA Reference</th>
											<th>Resilience</th>
											<th>Pricing</th>
											<th class="${hideServiceInstance}">Service Instance</th>
										</tr>
									</thead>

                                
                                     
                                     
									<c:forEach var="sitePage" items="${sitePageList}">
										<tr>											
											<td><input id="${sitePage.siteId}" type="button" value="Edit" onclick="setSiteId(this.id);"/></td>
											<td id="siteID"><c:out value="${sitePage.siteId}" /></td>
											<td><c:out value="${sitePage.dcaReferenceId}" /></td>
											<td><c:out value="${sitePage.resilience}" /></td>
											<td><c:out value="${sitePage.pricing}" /></td>
											<td class="${hideServiceInstance}"><c:out value="${sitePage.serviceInstance}" /></td>
										</tr>

									</c:forEach>
								</table>
								
								</form:form>
								</div>
								</div>
								</div>
								</div>
								
							
</body>
</html>
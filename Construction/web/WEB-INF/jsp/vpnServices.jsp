<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>VPN Services</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
 rel="stylesheet" type="text/css" />
 <script type="text/javascript" 	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>	
 <style type="text/css">
 .hidepreAllocatedCoreDiversity,.hideProvider,.hideServiceType
 {
  display :none;
 }
 
 
 </style>
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">VPN Services</center><br><br>	
					
					
									
				    <form action="VPNServicesDetails" method="post"> 
				    <label> Connection Name - </label><c:out value="${ConnectionName}"/><br /><br />
									
 				<table class="bordered" border="1">
 							<tr> 
 								<th></th>
 								<th>VPN Service Id</th> 
 								<th>Service Name</th> 
 								<th>Burst Option</th>
 								<th>SLA Package</th>  
 								<th>Service Resilience</th>
 								<th>Product Name</th>
 								<th>Core Diversity</th>
 								<th class="${hidepreAllocatedCoreDiversity}">Pre allocated Core Diversity</th>
 								<th class="${hideProvider}">Provider</th>
 								<th class="${hideServiceType}">Service Type</th> 
								 
 							</tr> 																			
							<c:forEach var="vpn" items="${vpnServicesDTO}">
							<tr>
							    <td><input id="${vpn.vpnServiceId}" type="button" onclick="setVpnServiceId(this.id);" value="Edit"  /></td>
								<td><c:out value="${vpn.vpnServiceId}" /></td>
								<td><c:out value="${vpn.serviceName}" /></td>
								<td><c:out value="${vpn.burstOption}" /></td>
								<td><c:out value="${vpn.slaPackage}" /></td>
								<td><c:out value="${vpn.serviceResilience}" /></td>
								<td><c:out value="${vpn.productName}" /></td>
								<td><c:out value="${vpn.coreDiversity}" /></td>
								<td class="${hidepreAllocatedCoreDiversity}"><c:out value="${vpn.preAllocatedCoreDiversity}" /></td>
								<td class="${hideProvider}"><c:out value="${vpn.providerCounterParty}" /></td>
								<td class="${hideServiceType}"><c:out value="${vpn.serviceType}" /></td>

							
							</tr>
							</c:forEach>

 						</table> 
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

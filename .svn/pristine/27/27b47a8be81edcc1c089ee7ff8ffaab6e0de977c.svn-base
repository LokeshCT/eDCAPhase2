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
<title>VPN Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
	 <script type="text/javascript" 	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>	
	
</head>

<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">VPN</center><br><br>		
				 <form:form action="#" method="post"
							commandName="vpnPage">


								<table class="bordered" border="1" style="width:90%"  >
									<thead>
										<tr>
											<th>Select</th>
											<th>VPN ID</th>
											<th>CONNECTION NAME</th>
											<th>CONNECTION TYPE</th>
											<th>CONNECTION BANDWIDTH</th>
											<th>RESILIENCE</th>
											<th>MultiCast Services</th>
										</tr>
									</thead>          
									<c:forEach var="vpnPage" items="${vpnSiteList}">
										<tr>											
											<td><input id="${vpnPage.vpnId}" type="button" onclick="setVpnId(this.id);" value="Edit"/></td>
											<td ><c:out value="${vpnPage.vpnId}" /></td>
											<td ><c:out value="${vpnPage.connectionName}" /></td>
											<td><c:out value="${vpnPage.connectionType}" /></td>
											<td><c:out value="${vpnPage.connectionBandWidth}" /></td>
											<td><c:out value="${vpnPage.resilience}" /></td>
											<td ><c:out value="${vpnPage.mcast_flag}" /></td>
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
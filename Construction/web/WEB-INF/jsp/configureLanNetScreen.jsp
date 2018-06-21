<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>CPE Ordering Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Configure LAN Switch/NET Screen</center>
					
					<br><br>
					
					<table class="bordered">
						 	<thead>
							<tr>
								<th>Edit</th>
								<th>Delete</th>
								<th>LAN USAGE</th>
								<th>LAN Switch</th>
								<th>CHASSIS</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="configureLanNetScreen" items="${configureLanNetScreenGridList}">				  
								<tr>
									<td><input type="radio" name="edit" id="edit" ></input></td>
									<td><button>Delete</button></td>
									<td><c:out value="${configureLanNetScreen.lanUsage}"/></td>
									<td><c:out value="${configureLanNetScreen.lanSwitch}"/></td>
									<td><c:out value="${configureLanNetScreen.chassis}"/></td>
								</tr>					
						</c:forEach>
						</tbody>
						</table>
					<br> <br> 
					<c:choose>
							<c:when test="${lanUsage == 'Encryption'}">
								<%@ include file="netScreenDetails.jsp"%><br/>
							</c:when>
							<c:otherwise>								
								<%@ include file="lanSwitchDetails.jsp"%><br/>
							</c:otherwise>
						</c:choose>
		
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

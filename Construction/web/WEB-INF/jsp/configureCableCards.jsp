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
<title>ConfigureCableCards</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Configure Cards/Cables</center>
					<br> <form:form action="cpeDetails" method="post"
							commandName="configureCardCableDTO">
						
							<c:set var="enablebtnRelatedDocs" value="${configureCable}"
								scope="request" />


							<c:choose>
								<c:when test="${requestScope.enablebtnRelatedDocs == 0 }">
									<font color="blue"><h3>No other devices have been added for
										this leg. User can proceed to next page by clicking on next
										button</h3></font>
								</c:when>

								<c:otherwise>
									<table border="1">

										<tr>
											<th></th>
											<th>CPE USAGE</th>
											<th>BaseRouter</th>
											<th>Chassis</th>

										</tr>

										<c:forEach var="cpeList" items="${cableList}">
											<tr>
												<td><input type="button" value="Select"></td>
												<td><c:out value="${cpeList.cpeUsage}" /></td>
												<td><c:out value="${cpeList.baseRouter}" /></td>
												<td><c:out value="${cpeList.chassis}" /></td>
											</tr>
										</c:forEach>
									</table></br>


					<h3>Configure Card\Cables</h3>
					<table border="1">

						<tr>
							<th>Element Name</th>
							<th>Sub-Element Type</th>
							<th>Element Desc</th>
							<th>Quantity</th>
						</tr>
						<c:forEach var="cards" items="${cards}">

							<tr>

								<td><c:out value="${cards.partType}" /></td>
								<td><c:out value="${cards.subElementType}" /></td>
								<td><c:out value="${cards.partName}" /></td>
								<td><form:input path=""></form:input></td>
							</tr>

						</c:forEach>
					</table>
					</c:otherwise>
					</c:choose>
					</form:form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

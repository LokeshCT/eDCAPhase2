<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>CONFIGURE LAN CARDS</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<!-- <style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style> -->
<style type="text/css">
.jagan {
	display: none;
}
</style>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">ConfigureVLan</center>
					<br />
					<c:if test="${lblError eq 'Y'}">
					<label>Ultra orders</label>
					</c:if>
					
					<form:form action="saveCustomerDetails" method="post"
						commandName="configureVlanDTOUltraList">
						<c:forEach items="${configureVlanDTOUltraList}" var="items">
							<c:out value="${items.lblError}" />
						</c:forEach>
						<table border=1>



							<tr>
								<th></th>
								<th>PAF NAME</th>
								<th>VLAN TYPE</th>
								<%-- <th class="${jagan}">Service Instance</th> --%>
							</tr>


							<c:forEach items="${configureVlanDTOUltraList }" var="items">
								<tr>
									<td><input type="button" value="view"></input></td>
									<td><input path="pafName" type="text"
										value="<c:out value="${items.pafName}"/>"
										disabled="${isDisabled}" /></td>
									<td><input path="vlanType" type="text"
										value="<c:out value="${items.vlanType}"/>"
										disabled="${isDisabled}" /></td>

								</tr>
							</c:forEach>

						</table>
						<br />
						<br />

					<%-- 	<c:forEach items="${configureVlanDTOUltraList }" var="items">
							<table style="border-spacing: 5px">
								<tr>
									<td>A- EndSite</td>
									<td><center>
											<input path="aEndSite" value="${items.aEndSite} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>Z- EndSite</td>
									<td><center>
											<input path="zEndSite" value="${items.zEndSite} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>Bandwidth</td>
									<td><center>
											<input path="bandWidth" value="${items.bandWidth} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>BroadCast</td>
									<td><center>
											<input path="broadCast" value="${items.broadCast} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>A- End Vlan Id</td>
									<td><center>
											<input path="aEndVlanId" value="${items.aEndVlanId} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>Z- End Vlan Id</td>
									<td><center>
											<input path="zEndVlanId" value="${items.zEndVlanId} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>OSILayer</td>
									<td><center>
											<input path="osiLayer" value="${items.osiLayer} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>VLAN Type</td>
									<td><center>
											<input path="vlanType" value="${items.vlanType} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>Provider Name</td>
									<td><center>
											<input path="providerName" value="${items.providerName} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>Member Name</td>
									<td><center>
											<input path="memberName" value="${items.memberName} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<tr>
									<td>PAF Name</td>
									<td><center>
											<input path="pafName" value="${items.pafName} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>
								<table border=1>
									<tr>
										<th>SAN NAME</th>
										<th>SAN VALUE</th>
									</tr>
									<tr>



										<td><c:forEach items="${sanNameList}" var="sandetails">
												<input path="sanName" type="text"
													value="<c:out value="${sandetails.sanName}"/>"
													disabled="${isDisabled}" />
											</c:forEach></td>

										<td><textarea name='textAreaName' id='textAreaId'
												disabled="${isDisabled}">
                                        <c:forEach
													items="${sanValueList}" var="sanValues">
                                                <c:out
														value="${sanValues.sanValue}" />
                                       </c:forEach></textarea></td>
									</tr>

								</table>
								
								
								<tr>
									<td>VLAN Identifier</td>
									<td><center>
											<input path="vlanIdentifier" value="${items.vlanIdentifier} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>

								<tr>
									<td>Customer Hand Off Mode</td>
									<td><center>
											<input path="customerHandOffMode"
												value="${items.customerHandOffMode} "
												disabled="${isDisabled}"></input>
										</center></td>
								</tr>


							</table>
						</c:forEach> --%>
					</form:form>
</body>
</html>

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
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">CONFIGURE LAN CARDS</center>
					<br />


					<form:form action="saveCustomerDetails" method="post"
						commandName="configureLanCardsDTO">

						<%-- <c:forEach
							items="${configureLanCardsDTO.configureLanSwitchDetailsList }"
							var="items">
							<c:if test="${empty items.lanSwitch}">
								<c:if test="${fn:length(items.lanSwitch) le 0}">

							</c:if>
						</c:forEach> --%>
						<c:choose>
							<c:when
							test="${fn:length(configureLanCardsDTO.configureLanSwitchDetailsList)>0}">
						<table>



									<tr>

										<c:if test="${empty configureLanSwitchDetailsList.lanSwitch}">
											<th></th>
											<th>Lan USAGE></th>
											<th>Lan Switch</th>
											<th>CHASSIS</th>

										</c:if>

									</tr>


									<c:forEach
										items="${configureLanCardsDTO.configureLanSwitchDetailsList }"
										var="items">
										<tr>
											<td><input type="button" value="select"></input></td>
											<td><input path="lanUsage" type="text"
												value="<c:out value="${items.lanUsage}"/>"
												disabled="${isDisabled}" /></td>
											<td><input path="lanSwitch" type="text"
												value="<c:out value="${items.lanSwitch}"/>"
												disabled="${isDisabled}" /></td>

											<td><input path="chassis" type="text"
												value="<c:out value="${items.chassis}"/>"
												disabled="${isDisabled}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:when>
							<c:otherwise>
<h4><font color="red">Site does not require Lan Switch Cables</font></h4>
</c:otherwise>
						</c:choose>
						<h3>Card Selection</h3>
						<%-- <c:if test="${not empty items.lanSwitch}"> --%>
						<table>
							<c:forEach
								items="${configureLanCardsDTO.configureLanCardsDetailsList}"
								var="cards">
								<c:if
									test="${(cards.sharedPortAdapters gt 0)  || (cards.networkModules gt 0)}">
									<tr>
										<th>CardSlot</th>
										<th>Port Type</th>
										<th>Card</th>
									</tr>
									<br />
									<c:forEach
										items="${configureLanCardsDTO.configureLanCardsDetailsList}"
										var="cards">
										<c:if test="${cards.sharedPortAdapters gt 0}">
											<tr>
												<c:set value="${cards.sharedPortAdapters}" scope="request"
													var="count" />
												<c:forEach var="sharedPortAdapterCount" begin="1"
													end="${cards.sharedPortAdapters}">
													<tr>
														<td>SPA <c:out value="${sharedPortAdapterCount}" />
															<!-- 		<br> --></td>


														<c:if test="${cards.sharedPortAdapters gt 0}">
															<td><c:forEach
																	items="${configureLanCardsDTO.cardColumnList }"
																	var="column">



																	<c:set var="spa"
																		value="SPA ${cards.sharedPortAdapters}"></c:set>
																	<%-- <c:out value="${spa }" /> <c:out value="${column.cardPosition}"/> --%>
																	<!-- <select name="option" > -->
																	<c:choose>
																		<c:when test="${spa eq column.cardPosition}">
																			<c:out value="${column.portType}" />
																			<td><c:out value="${column.cardDescription}" /></td>
																		</c:when>
																		<c:otherwise>
																			<c:out value="None" />
																		</c:otherwise>
																	</c:choose>
																	<!-- </select> -->
																</c:forEach>

																<form action="#" method="post"></form> <br /></td>
														</c:if>
												</c:forEach>
											</tr>
										</c:if>
									</c:forEach>

									<c:forEach
										items="${configureLanCardsDTO.configureLanCardsDetailsList}"
										var="cards">
										<c:if test="${cards.networkModules gt 0}">
											<tr>
												<c:set value="${cards.networkModules}" scope="request"
													var="count" />
												<c:forEach var="networkModuleCount" begin="1"
													end="${cards.networkModules}">
													<tr>
														<td>NM <c:out value="${networkModuleCount}" /> <!-- 		<br> --></td>


														<c:if test="${cards.networkModules gt 0}">
															<td><c:forEach
																	items="${configureLanCardsDTO.cardColumnList }"
																	var="column">



																	<c:set var="nm" value="NM ${cards.networkModules}"></c:set>

																	<c:choose>
																		<c:when test="${nm eq column.cardPosition}">
																			<c:out value="${column.portType}" />
																			<td><c:out value="${column.cardDescription}" /></td>
																		</c:when>
																		<c:otherwise>
																			<c:out value="None" />
																		</c:otherwise>
																	</c:choose>
																</c:forEach>

																<form action="#" method="post"></form> <br /></td>
														</c:if>
												</c:forEach>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</table>
						<br />
						<br />
						<br />
						<h3>CableSelectionList</h3>

						<c:if
							test="${fn:length(configureLanCardsDTO.cableShortNameList)>0}">

							<table>


								<tr>


									<th>LANCables</th>
									<th>Total Cables</th>

								</tr>

								<c:forEach items="${configureLanCardsDTO.cableShortNameList}"
									var="cards">
									<c:if test="${not empty cards.cableShortName}">
										<tr>


											<td><c:out value="${cards.cableShortName}"></c:out></td>
											<td><c:forEach
													items="${configureLanCardsDTO.cableSelectionTotalDTOList}"
													var="totalCables">

													<c:choose>
														<c:when
															test="${cards.cableShortName eq totalCables.cableName}">
															<c:out value="${totalCables.numberOfCables}" />
														</c:when>
													</c:choose>
												</c:forEach></td>

										</tr>
									</c:if>
								</c:forEach>

							</table>
						</c:if>

						<br />
						<br />
						<br />
						<c:if
							test="${fn:length(configureLanCardsDTO.opticalGridFirstColumnDTO)>0}">
							<table>
								<tr>

									<th>OpticalModules</th>
									<th>Total Modules</th>

								</tr>

								<c:forEach
									items="${configureLanCardsDTO.opticalGridFirstColumnDTO}"
									var="opticalCables">
									<tr>

										<td><c:out value="${opticalCables.cableShortName}"></c:out></td>

										<td><c:forEach
												items="${configureLanCardsDTO.cableSelectionTotalDTOList}"
												var="totalCables">

												<c:choose>
													<c:when
														test="${opticalCables.cableShortName eq totalCables.cableName}">
														<c:out value="${totalCables.numberOfCables}" />
													</c:when>
												</c:choose>
											</c:forEach></td>

									</tr>
								</c:forEach>
							</table>
						</c:if>
					</form:form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="title">
	<spring:message code="label.name" />
</c:set>

<%-- Math.ceil function implementation for total page count --%>
<c:set var="resultInDouble" value="${(TotalRows-1)/30 +1}" />

<c:set var="resultInDoubleZero"
	value="${resultInDouble - resultInDouble % 
1}" />

<fmt:formatNumber value="${resultInDoubleZero}" var="totalPage"
	pattern="0" />

<%-- total amount of pages --%>

<c:url var="firstUrl" value="1" />
<c:url var="lastUrl" value="${totalPage}" />
<c:url var="prevUrl" value="${CurrentIndex - 1}" />
<c:url var="nextUrl" value="${CurrentIndex + 1}" />
<c:set var="currentPage" value="${CurrentIndex}" />
<%-- current page --%>
<c:set var="links" value="10" />
<%-- amount of page links to be displayed --%>
<c:set var="range" value="5" />

<%-- minimum link range ahead/behind --%>

<c:set var="begin"
	value="${totalPage > links ? ((currentPage - range) > 1 ? (currentPage - range): 1): 1}" />
<c:set var="end"
	value="${totalPage > links ? ((currentPage + range) > totalPage ? totalPage: (currentPage + range)): totalPage}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Search Order</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/pagination.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery.1.6.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/jquery.popup.js" />"></script>
<script src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/assets/js/custom/searchOrder.js"/>"></script>


</head>
<body  onload="setHref()">

	<div class="main">
		<!--  Main Begin -->
		<div class="header">
			<!--  Header Begin -->
			<div class="header_resize">
				<!--  Header Resize Begin -->
				<!--  Include Header File -->
				<%@ include file="header.jsp"%>
				<div class="clr"></div>
				<!-- Menu  -->
				<ul id="nav">
					<li><a class="menu" href="#"> <img
							src="<c:url value="/assets/img/menu-icon.png"/>" /></a>
						<ul class="dd">
							<li><a id ="selectProfileId"><img							
									src="<c:url value="/assets/img/profile.png"/>" /> Select
									Profile</a></li>
							<li><a href="#"><img
									src="<c:url value="/assets/img/User_logout_man.png"/>" /> Sign
									Out </a></li>
						</ul></li>
				</ul>
				<!--  End of Menu -->
				<div class="clr"></div>
			</div>
			<!-- end of header_resize -->
		</div>
		<!-- end of header-->
		<div class="fbg">
			<div class="fbg_resize">
				<div class="search">
					<form action="#" method="post">
						<table>
							<tr>
								<td>Status</td>
								<td><select id="StatusType" name="StatusType"
									onchange="onchangeStatus()">

										<c:forEach var="status" items="${OrderStatusTypes}">

											<c:choose>
												<c:when test="${status==OrderType}">

													<option selected value="${status}">${status}</option>
												</c:when>
												<c:otherwise>
													<option value="${status}">${status}</option>

												</c:otherwise>
											</c:choose>


										</c:forEach>
								</select> <!-- 	  <input type="hidden" name="selectedValue" value="0"/>  -->
								</td>
								<td>Order ID</td>
								<td><input type="text" name="Order ID" /></td>
								<td>Order Reference</td>
								<td><input type="text" name="Order Reference" /></td>
							</tr>
							<tr>

								<td>Account ID</td>
								<td><input type="text" name="Account ID" /></td>
								<td><input style="float: right" type="checkbox"></td>
								<td><p style="margin-left: 18px">Search in all queue</p></td>
								<td></td>
								<td><input type="submit" value="Search" disabled/></td>

							</tr>

							<td><input type="hidden" id="urlPath" value="${title}" /></td>
							<td><input type="hidden" id="statusCode" value="${orderStatusCode}" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="clr"></div>
			</div>
			<!-- End of fbg_resize -->
		</div>
		<!-- End of fbg -->
		<div class="content">
			<div class="content_resize">
			
			
				<c:set value="${OrderType}" scope="request" var="Type" />
				<p id="content">
					<%@ include file="OrderList.jsp"%></p>
				<br> <br> <br> <form:form action="saveOrderDetails"
								method="post">
								<table class="bordered">
									<thead>
										<tr>
											<th>Order ID</th>
											<th>OrderReference</th>
											<th>Account ID</th>
											<th>Client Name</th>
											<th>Receive Date</th>
										</tr>
									</thead>



							<c:forEach var="order" items="${OrderDetailsDTO}">
								<tr>
									<c:choose>
										<c:when test="${IsDisabled == 'true'}">
											<td><c:out value="${order.orderId}" /></td>
										</c:when>
										<c:otherwise>

													<td><a href="#" id="${order.orderId}"
														data-reveal-id="myModal"><c:out
																value="${order.orderId}" /></a></td>
												</c:otherwise>
											</c:choose>
											<td><c:out value="${order.orderReference}" /></td>
											<td><c:out value="${order.clientAccountId}" /></td>
											<td><c:out value="${order.clientName}" /></td>
											<td><c:out value="${order.receivedDate}" /></td>
										</tr>



							</c:forEach>
						</table>


								<br> <br> <br>
											<div class="paginate paginate-dark wrapper">
												<ul>
													<c:choose>
														<c:when test="${CurrentIndex == 1}">
															<li class="disabled"><a href="javascript:void(0)">&lt;&lt;</a></li>
															<li class="disabled"><a href="javascript:void(0)">&lt;</a></li>
														</c:when>
														<c:otherwise>
															<c:url var="firstUrl" value="${firstUrl}" />

															<li><a href="#"
																onclick="loadPaginationUrl(${firstUrl}); return false;">&lt;&lt;</a></li>


															<c:url var="prevUrl" value="${prevUrl}" />

															<li><a href="#"
																onclick="loadPaginationUrl(${prevUrl}); return false;">&lt;</a></li>

														</c:otherwise>


													</c:choose>

													<c:forEach var="i" begin="${begin}" end="${end}">
														<c:url var="pageUrl" value="${i}" />
														<c:choose>
															<c:when test="${i == CurrentIndex}">
																<li><a class="active" href="#" onclick="loadPaginationUrl(${pageUrl}); return false;"><c:out
																			value="${i}" /></a></li>
															</c:when>
															<c:otherwise>
																<li><a href="#" onclick="loadPaginationUrl(${pageUrl}); return false;" ><c:out value="${i}" /></a></li>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													<c:choose>
														<c:when test="${CurrentIndex == totalPage}">
															<li class="disabled"><a href="javascript:void(0)">&gt;</a></li>
															<li class="disabled"><a href="javascript:void(0)">&gt;&gt;</a></li>
														</c:when>
														<c:otherwise>
															<li><a href="#" onclick="loadPaginationUrl(${nextUrl}); return false;">&gt;</a></li>
															<li><a href="#" onclick="loadPaginationUrl(${lastUrl}); return false;">&gt;&gt;</a></li>
														</c:otherwise>
													</c:choose>
												</ul>
											</div>
							</form:form>


							<div id="myModal" class="reveal-modal">
								<h1 style="font-size: 30px">
									<p id="selectedOrderId"></p>
								</h1>
								<table class="withoutbordered" border="0">
									<thead>
										<tr>
											<th colspan=2>
												<center>Action</center>
											</th>
										</tr>
									</thead>
									<tr>
										<td><img src="<c:url value="/assets/img/assign.png"/>" /></td>
										<td><a href="#">Assign Order to Myself</a></td>
									</tr>
									<tr>
										<td><img src="<c:url value="/assets/img/read.png"/>" /></td>
										<td><a id="readId" href="#">Read Order</a></td>
									</tr>
									<tr>
										<td><img src="<c:url value="/assets/img/edit.png"/>" /></td>
										<td><a href="#">Edit Order</a></td>
									</tr>
									<tr>
										<td><img src="<c:url value="/assets/img/delegate.png"/>" /></td>
										<td><a href="#">Delegate Own Order</a></td>
									</tr>
								</table>
								<a class="close-reveal-modal">X</a>
							</div>
			</div>
			<!-- End of content_resize -->

		</div>
		<!-- End of content -->
		<!--  Footer  -->
		<%@ include file="footer.jsp"%>

	</div>
	<!-- End of main -->




</body>


</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Customer Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery.1.6.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/ajaxJquery.js" />"></script>
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/coinSlider.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/script.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/coin-slider.min.js" />"></script>
	<script type="text/javascript"
	src="<c:url value="/assets/js/custom/CustomerDetails.js" />"></script>
</head>

<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Customer Details</center>

					<br> <br> <form:form action="#" method="post"
								commandName="customer"  id="CustomerForm">
								<c:set var="size" value="${size}" scope="request" />
								<table class="tableborderspacing">



									<tr>
										<td>Full Legal Company Name</td>
										<td><form:input disabled="${isDisabled}"
												value="${customer.fullLegalCompanyName}"
												path="fullLegalCompanyName" /></td>

										<td>Customer Name</td>
										<td><form:input disabled="${isDisabled}"
												value="${customer.customerName}" path="customerName" /></td>
									</tr>
									<tr>
										<td>Master Customer ID</td>
										<td><form:input path="masterCustomerId"
												disabled="${isDisabled}"
												value="${customer.masterCustomerId}" /></td>

										<td>Order Type</td>
										<td><form:select path="orderType"
												disabled="${isDisabled}">
												<form:option value="">${customer.orderType}</form:option>
											</form:select></td>
									</tr>
									<tr>

										<td>Distributor's Legal Name</td>
										<td><form:select path="distributorLegalName"
												disabled="${isDisabled}">
												<form:option value="">${customer.distributorLegalName}</form:option>
											</form:select></td>

										<td>Currency</td>

										<td><form:select path="currency" disabled="${isDisabled}">
												<form:option value="">${customer.currency}</form:option>
											</form:select></td>

									</tr>


									<tr>
										<td>External System Name</td>
										<td><form:input path="externalSystemName"
												disabled="${isDisabled}"
												value="${customer.externalSystemName}" /></td>

										<td>External System Reference</td>
										<td><form:input path="externalSystemReference"
												disabled="${isDisabled}"
												value="${customer.externalSystemReference}" /></td>
									</tr>
									<tr>
										<td>Billing ID</td>
										<td><form:input path="billingId" disabled="${isDisabled}"
												value="${customer.billingId}" /></td>

										<td>Contract ID</td>
										<td><form:input path="contractId"
												disabled="${isDisabled}" value="${customer.contractId}" /></td>
									</tr>

									<tr>
										<td>Siebel ID</td>
										<td><form:input path="opportunityId"
												disabled="${isDisabled}" style="background-color:cyan"
												value="${customer.opportunityId}" /></td>

										<td>Performance Reports Required</td>

										<td><form:select path="performanceReportRequired"
												name="User" disabled="${isDisabled}">
												<form:option value="">${customer.performanceReportRequired}</form:option>
											</form:select></td>
									</tr>
									<tr>

									</tr>


									<c:set var="poaListsize" value="${poaList}" scope="request" />
									<c:if test="${requestScope.poaListsize gt 1}">
										<tr>
											<td>Poa Reference</td>
											<c:forEach var="customer" items="${poaReference}"
												varStatus="counter">
												<c:if test="${counter.count eq 1}">
													<td><form:input path="poaReference"
															disabled="${isDisabled}" value="${customer.poaReference}" />
													</td>
												</c:if>
										</tr>
										<tr>
											<c:if test="${counter.count gt 1}">
												<tr>
													<td></td>
													<td><form:input path="poaReference"
															disabled="${isDisabled}" value="${customer.poaReference}" />
													</td>
												</tr>
											</c:if>

										</tr>
										</c:forEach>
									</c:if>
									<c:if test="${requestScope.poaListsize eq 1}">

										<c:forEach var="customer" items="${poaReference}">
											<c:choose>
												<c:when
													test="${(customer.poaReference==' ')||(customer.poaReference=='N/A')}">

												</c:when>


												<c:otherwise>
													<td>Poa Reference</td>
													<td><form:input path="poaReference"
															disabled="${isDisabled}" value="${customer.poaReference}" /></td>


												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>

									<c:if test="${requestScope.size gt 0}">

										<tr>
											<td>Sub Order Type</td>

											<c:forEach var="customer" items="${customerDetailList}"
												varStatus="counter">
												<c:if test="${counter.count eq 1}">

													<td><form:input path="subOrderType"
															disabled="${isDisabled}" value="${customer.subOrderType}" /></td>
												</c:if>
										</tr>

										<tr>
											<c:if test="${counter.count gt 1}">
												<tr>
													<td></td>
													<td><form:input path="subOrderType"
															disabled="${isDisabled}" value="${customer.subOrderType}" /></td>
												</tr>
											</c:if>
											</c:forEach>
										</tr>
									</c:if>


									<c:if test="${requestScope.size eq 0}">
										<td>Sub Order Type</td>

										<td><form:input path="subOrderType"
												disabled="${isDisabled}" /></td>


									</c:if>

									</tr>
								</table>
								<input type="submit" value="Next" ></input>
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

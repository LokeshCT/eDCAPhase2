<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cotplus-CPEM</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
	src="<c:url value="/assets/js/custom/userDetails.js" />"></script>
<!-- This jsp can be used to displying the UserProfileSelection Details and productNames -->

</head>

<!--  -->
<body>
	<div class="main indexpage">
		<div class="header">
			<div class="header_resize">
				<!--  Header Resize Begin -->
				<!--  Include Header Content -->
				<%@ include file="header.jsp"%>
				<div class="clr"></div>
				<ul id="nav">
					<li><a class="menu" href="#"><img
							src="<c:url value="/assets/img/menu-icon.png"/>"></a>
						<ul class="dd">
							<li><a href="#"><img
									src="<c:url value="/assets/img/User_logout_man.png"/>">
									Sign Out </a></li>
						</ul></li>
				</ul>
				<div class="clr"></div>

				<div class="slider">
					<div id="coin-slider">
						<a href="#"><img src="<c:url value="/assets/img/slide1.jpg"/>"
							width="500" height="323" alt="" /> </a> <a href="#"><img
							src="<c:url value="/assets/img/slide4.jpg"/>" width="500"
							height="323" alt="" /> </a> <a href="#"><img
							src="<c:url value="/assets/img/slide5.jpg"/>" width="500"
							height="323" alt="" /> </a>
					</div>
					<div class="indexform">
						<form:form action="../Continue" commandName="userDetailsDTO"
							id="mySelect" method="post">
							<br>
							<table style="border-spacing: 10px;">
								<tbody>
									<tr>
										<td>Select Profile</td>
									</tr>

									<tr>
										<td><form:select id="profile" path="profileName"
												name="User">

												<c:forEach var="User" items="${userDetailsDtoList}">
													<c:if test="${ User.profileName ne null}">
														<form:option selected value="${User.teamId}">${User.profileName}</form:option>
													</c:if>
												</c:forEach>

											</form:select></td>
									</tr>
							</table>
							<c:set var="Product" value="${Product.productCatagoryId}"
								id="Product" />
							<table>
								<tr>

									<th>Select Product</th>

									<td><div id="product">${Product.productCatagoryName}</div></td>

									<c:forEach var="Product" items="${userDetailsDtoList}">

										<c:if test="${ Product.productCatagoryName ne null}">
											<c:choose>

												<c:when test="${Product.productCatagoryName eq 'SPRING'}">
													<td id="initialLoadproducts1"><form:radiobutton
															path="productCatagoryName" id="productCatagoryName"
															value="${Product.productCatagoryName},${Product.productCatagoryId}"
															checked="checked"></form:radiobutton> KEYSTONE</td>
												</c:when>
												<c:when test="${Product.productCatagoryName eq 'MPLS'}">
													<td id="initialLoadproducts2"><form:radiobutton
															path="productCatagoryName" id="productCatagoryName"
															value="${Product.productCatagoryName},${Product.productCatagoryId}"
															checked="checked"></form:radiobutton> Ethernet
														Connect,MPLS and/or Onevoice or GTP CPE</td>
												</c:when>
												<c:otherwise>
													<td id="initialLoadproducts3"><form:radiobutton
															path="productCatagoryName" id="productCatagoryName"
															value="${Product.productCatagoryName},${Product.productCatagoryId}"
															checked="checked"></form:radiobutton>${Product.productCatagoryName}
													</td>
												</c:otherwise>

											</c:choose>
										</c:if>
									</c:forEach>


								</tr>

								<tr>
									<table>
										<tr>
											<td><input type="button" id="saveNewUsers"
												value="Makes this as Default"></td>
											<td><form:input type="submit" value="Continue"
													name="Continue" path=""></form:input></td>
										</tr>
									</table>
								</tr>
								</tbody>
							</table>
							<% String nUserId = (String) request.getAttribute("userid");%>
							<input type="hidden" name="User" id="User" value="<%=nUserId%>"></input>
							<form:input type="hidden" path="userId" id="User"
								value="<%=nUserId%>"></form:input>
						</form:form>
						<div id="result1"></div>
					</div>

					<div class="clr"></div>
				</div>
				<div class="clr"></div>
			</div>

			<!-- end of header_resize -->
		</div>

		<!-- end of header-->

		<div class="fbg">
			<div class="fbg_resize">
				<h2>Welcome to eDCA(Electronic Data Capture Application)</h2>
				<div class="col c1">
					<h2>About BT</h2>
					<p>At BT our focus is on excellent customer service. We always
						aim to be within easy reach and we want to make sure you get the
						answers you want quickly and easily. To find the contact
						information you need, just choose from the full range of options
						below.</p>
				</div>
				<div class="col c2">
					<h2>
						<span>EDCA</span> Services
					</h2>
					<p>This system facilitates creation of BT Ethernet
						Connect/MPLS/Onevoice/GTP CPE Orders and processing of Keystone
						Orders.Every user is associated with one or multiple profiles on
						the eDCA. The default profile is currently selected in the drop
						down menu above.</p>
				</div>
				<div class="col c3">
					<h2>
						<span>Contact</span> US
					</h2>
					<p>EDCA Support Contact</p>
					<p class="contact_info">
						<span>Contact:</span> Mr. Xyz ABC<br /> <span>E-mail:</span> <a
							href="#">mail@bt.com</a>
					</p>
				</div>
				<div class="clr"></div>
			</div>
			<!-- end of fbg_resize -->
		</div>
		<!-- end of fbg -->
		<div class="footer">
			<div class="footer_resize">
				<center>
					&copy; Copyright <a href="#">www.bt.com</a>
				</center>
				<div style="clear: both;"></div>
			</div>
		</div>
		<!-- end of footer -->
	</div>
	<!-- end of mainindex -->
</html>

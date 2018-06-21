<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<title>Read Order</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/sibeBarAccordian.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/sideNav.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery-1.6.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/jquery.popup.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/iframeheight.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/sideBarNavigation.js" />"></script>
	
</head>
<body>
<div class="main"> <!--  Main Begin -->
<div class="header">	<!--  Header Begin -->
	<div class="header_resize">	<!--  Header Resize Begin -->
	<!--  Include Header Content -->
		<%@ include file="header.jsp"%>	
	<div class="clr"></div>
	<!--  Menu  -->
	<ul id="nav">
			<li><a class="menu" href="../orderList"><span>Order List</span></a></li>
			<li><a class="menu" href="#" data-reveal-perform="myModal"><span>Perform
			Action</span></a></li>
			<li><a class="menu" href="#"><span>Summary Report</span></a></li>
			<li><a class="menu" href="#"><span>Alter Delivery
			Order in Cot +</span></a></li>
			<li><a class="menu" href="#"> <img
				src="<c:url value="/assets/img/menu-icon.png"/>" /></a>
			<ul class="dd">
				<li><a href="../selectProfile"><img
					src="<c:url value="/assets/img/profile.png"/>" /> Select Profile</a></li>
				<li><a href="#"><img
					src="<c:url value="/assets/img/User_logout_man.png"/>" /> Sign Out
				</a></li>
		 	</ul>
		</li>
		</ul>
		<!--  End of Menu  -->
	<div class="clr"></div>
	</div><!-- End of header_resize -->
</div><!-- End of header-->



<div class="readorder">
<div class="sidebar">
<ul class="sideBarTree transit">
	<li ><a id="orderMenu" href="#">Order - ${orderId}</a>
	<ul>
		<li><a id="customerDetailsMenu" href="<c:url value="../customerdetails/${orderId}"  />"
			target="content_page">Customer Details</a></li>
		<li><a id="infraStructureMenu" 
			href="<c:url value="../infrastructurecustomer/${orderId}"  />"
			target="content_page">Infrastructure Customer</a></li>
		<li><a id="customerContactMenu" 
			href="<c:url value="../customercontactdetails/${orderId}"  />"
			target="content_page">Customer Contact Details</a></li>
		<li><a id="orderContactMenu"
			href="<c:url value="../ordercontactdetails/${orderId}"  />"
			target="content_page">Order Contact Details</a></li>
		<li><a id="additionalInformationMenu"
			href="<c:url value="../additionalInformation/${orderId}"  />"
			target="content_page">Additional Information</a></li>
		<li><a id="productSelectionMenu" href="<c:url value="../productSelection/${orderId}"  />"
			target="content_page">Product Selection</a></li>
		<li><a id="sitesMenu" onClick='openLinkInIframe("../sitesDetailspage/${orderId}?orderStatus=${orderStatus}")' >Sites</a>
		<ul>
			<li><a id="sitePageMenu" >Site</a>
			<ul>
				<li><a id="siteLocationMenu" >Site Location</a> 
				<ul> 
					<li><a id="siteLocationDetailsMenu"  href="#">Site Location Details</a></li>
					<li><a id="siteAddressMenu" href="#" >Site Address</a></li>
					<li><a id="siteContactDetailsMenu" href="#" >Site Contact Details</a></li>
					<li><a id="cpeOrderingMenu" href="#">CPE Ordering</a></li>
					<li><a id="serviceInstanceMenu" href="#">Service Instance</a>
					<ul>
						<li><a id="configureAccessMenu">Configure Access</a></li>
						<li><a id="accessTypeMenu">Access Type</a></li>
						<li><a href="#">CPE</a>
						<ul>
							<li><a id="configureCPEMenu" href="#">Configure CPE</a></li>
							<li><a id="configureWICMenu" href="#">Configure WIC/NM/PA Cards</a></li>
							<li><a id="configureLanSwitchMenu" href="#">Configure LAN Switch/Netscreen</a></li>
							<li><a id="configureLANMenu" href="#">Configure LAN Cards</a></li>
							<li><a id="configureOtherMenu" href="#">Configure Other Devices</a></li>
							<li><a id="configureCardsMenu" href="#">Configure Cards/Cables</a></li>
							<li><a id="vpnPageMenu" href="#">VPN</a>
							<ul>
								<li><a id="vpnConnectionMenu" href="#">VPN Connection</a>
								<ul>
									<li><a id="dscpCoSMenu" href="#">DSCP CoS</a></li>
									<li><a id="vpnServicesMenu" href="#">VPN Services</a>
									<ul>
										<li><a id="serviceMenu" href="#">Service</a></li>
									</ul>
									</li>
								</ul>
								</li>
							</ul>
							</li>
							<li><a id="configureVlanMenu" href="#">Configure VLAN</a></li>
						</ul>
						</li>
					</ul>
					</li>
				</ul>
				</li>
			</ul>
			</li>
		</ul>
		</li>
		<li><a href="#">Submit</a></li>
	</ul>
	</li>
</ul>
</div>
		<input type="hidden"  value="${orderId}" id="orderId">
<!-- End of sideBarTree --> <iframe
	src="<c:url value="../customerdetails/${orderId}"  />"
	NAME="content_page" id="content_page" scrolling="no"
	onload="setIframeHeight(this.id)" width="900px"></iframe>
<div class="clr"></div>

</div>



<div id="myModal" class="reveal-modal">
	<h1 style="font-size: 30px">Order ID : ${orderId}</h1>
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
			<td><a href="#">Read Order</a></td>
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

<%@ include file="footer.jsp"%>
</div>
<!-- End of main-->
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery.velocity.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/sidebaraccordian.js" />"></script>

</body>
</html>
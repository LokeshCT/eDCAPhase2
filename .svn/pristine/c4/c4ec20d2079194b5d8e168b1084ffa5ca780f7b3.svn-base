<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Order List</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/pagination.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:set var="orderStatus" value="${Type}" />
	<c:choose>
		<c:when test="${orderStatus == 'Unassigned'}">

			<h2>View Unassigned Order</h2>
				Welcome to Unassigned orders section. This section is applicable for
				Keystone/FCE Orders. This section lists out all orders arrived from
				Decomposition Tool and not assigned to a specific team member.<br>
				Keystone/FCE users can assign these orders to themselves for further
				processing.
	 	</c:when>
		<c:when test="${orderStatus == 'AAF Pending'}">

			<h2>View AAF Pending Order</h2>
       		 	Welcome to View AAF Pending Orders section.<br>
  			 	This section lists out the orders which has been authorised by the AAF Authoriser and are in Pending Queue.       
        </c:when>
        
		<c:when test="${orderStatus == 'AAF Delegated'}">
			<h2>View AAF Delegated Order</h2>
        		Welcome to View AAF Delegated Orders section.<br> 

  				This section lists out the orders which are Delegated to AAF Authoriser.
  		</c:when>
  		
		<c:when test="${orderStatus == 'Cancelled'}">
			<h2>View Cancelled Order</h2>
         
        		Welcome to View Canceled Orders section.<br> 
  				This section lists all the orders Cancelled by the user and can be viewed in Read-Only Mode.      
         </c:when>
         
		<c:when test="${orderStatus == 'Delegated'}">
			<h2>View Delegated Order</h2>
         
         		Welcome to View Delegated Orders section.<br> 
  				This section lists all the orders delegated by the user to the team members and can be viewed in Read-Only mode.
         
         </c:when>
         
		<c:when test="${orderStatus == 'Inventory'}">
			<h2>View Inventory Order</h2>
   		  		Welcome to View Inventory Orders section.<br> 

  				This section lists out Modify orders with status of their inventory orders.
   		 
   		 </c:when>
   		 
		<c:when test="${orderStatus == 'Order by Team'}">
			<h2>View Order by Team</h2>  		  
   		 		 Welcome to View Orders section. This section lists out following types of orders.<br>
				<ul type="square">
					<li>For Sales: All orders for a specific team and created by other team members. Orders in this section will be available in Read Only Mode. </li>
					<li>For Service Delivery: All orders processed by team members. Orders in this section will be available in Read Only Mode. </li>
					<li>For RCE/Order Entry Team: All orders processed by team members. Orders in this section will be available in Read Only Mode. </li>
					<li>For Keystone/FCE: All orders assigned to team members. Orders in this section will be available in Read Only Mode.</li></ul>
				   		  
   		 </c:when>
   		 
		<c:when test="${orderStatus == 'Service Activation Summary'}">
			<h2>View Service Activation Summary</h2> 
   				 Welcome to View Service Activation Summary Report.<br> 
				This section lists out all orders which are uploaded to classic.
   		 
   		 </c:when>
   		 
		<c:when test="${orderStatus == 'Pending'}">
			<h2>View Pending Order</h2>
   				 Welcome to View Pending Orders section.<br> 
				  This section lists out following types of orders.
				  <ul type="square">
					<li>For Sales: All the orders created by order creator for a particular team. Order creator will have full rights to these orders. </li>
					<li>Service Delivery: All the orders submitted by Sales to service delivery Queue. Default Service Delivery order owner will have full rights to these orders. </li>
					<li>RCE/Order Entry Team: All orders submitted by either Sales or Service Delivery as per the TOM model for a specific country. Default RCE/Order Entry Team order owner will have full rights to these orders. </li> 
					<li>Keystone/FCE Users: All orders assigned to a specific Keystone/FCE User. Order owner will have full rights to these orders.</li></ul>
				   		 
   		 </c:when>
   		 
		<c:when test="${orderStatus == 'Classic'}">
		
			<h2>View Classic Order</h2>
			 Welcome to View Uploaded to Classic Orders.<br> 
			This section lists out all orders for which either Pass1 data or both Pass1 and Pass2 data is uploaded to classic.	 

		</c:when>
		
		<c:when test="${orderStatus == 'Classic By Team'}">
		
			<h2>View Classic By Team Order</h2>
 			
 			Welcome to View Uploaded to Classic Orders By Team.<br> 
			This section lists out all orders for which either Pass1 data or both Pass1 and Pass2 data is uploaded to classic by team.
		</c:when>

	</c:choose>

</body>
</html>
<!-- Jsp page having common attribute for primary 
     and secondary site contact details and passing this jsp 
       page to siteContactDetails.jsp page for including this jsp page. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/siteContactDetails.js" />"></script>
</head>
<body>

	<table class="bordered">

		<thead>
			<tr>
				<th>CONTACT ID</th>
				<th>FIRST NAME</th>
				<th>LAST NAME</th>
				<th>JOB TITLE</th>
				<th>EMAIL</th>
				<th>PHONE</th>
				<th>MOBILE PHONE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${id.primaryContactTypeList}" varStatus="i">
				<tr>


					<td><form:input
							path="primaryContactTypeList[${i.index}].contactId" type="text"
							readonly="true" disabled="${isDisabled}" /></td>
					<c:choose>
						<c:when test="${isDisabled eq false}">
							<td><form:input
									path="primaryContactTypeList[${i.index}].firstName" type="text"
									id="Name" name="p_FirstName" onchange="validate()"
									style="background-color: cyan" disabled="${isDisabled}" />
								<h4>
									<font color="red"><span id="error"> </span></font>
								</h4></td>
						</c:when>
						<c:otherwise>
							<td><form:input
									path="primaryContactTypeList[${i.index}].firstName" type="text"
									id="Name" name="p_FirstName" onchange="validate()"
									disabled="${isDisabled}" />
								<h4>
									<font color="red"><span id="error"> </span></font>
								</h4></td>

						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${isDisabled eq false }">
							<td><form:input
									path="primaryContactTypeList[${i.index}].lastName" type="text"
									id="LastName" name="p_lastName" onchange="validate()"
									style="background-color: cyan" disabled="${isDisabled}" />
								<h4>
									<font color="red"><span id="error_last"></span></font>
								</h4></td>
						</c:when>
						<c:otherwise>

							<td><form:input
									path="primaryContactTypeList[${i.index}].lastName" type="text"
									id="LastName" name="p_lastName" onchange="validate()"
									disabled="${isDisabled}" />
								<h4>
									<font color="red"><span id="error_last"></span></font>
								</h4></td>
						</c:otherwise>
					</c:choose>
					<td><form:input
							path="primaryContactTypeList[${i.index}].jobTitle" type="text"
							name="p_JobTitle" disabled="${isDisabled}" /></td>
					<td><form:input
							path="primaryContactTypeList[${i.index}].email" type="text"
							id="email_V" name="Email" onchange="validate()"
							disabled="${isDisabled}" />
						<h4>
							<font color="red"><span id="error_primarymail"></span></font>
						</h4></td>


					<td><form:input
							path="primaryContactTypeList[${i.index}].telephone" type="text"
							id="primaryPhone" name="Telephone" onchange="validate()"
							style="background-color: cyan" disabled="${isDisabled}" />

						<h4>
							<font color="red"><span id="error_Primaryphone"></span></font>
						</h4></td>
					<td><form:input
							path="primaryContactTypeList[${i.index}].mobilePager" type="text"
							id="mobilePager" name="Mobile" onchange="validate()"
							disabled="${isDisabled}" />

						<h4>
							<font color="red"><span id="error_mobile"></span></font>
						</h4></td>


				</tr>

</c:forEach>
</tbody>
</table>

</body>
</html>
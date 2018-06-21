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
			<c:forEach items="${secondaryContacts.secondaryContactTypeList}" varStatus="i">
				<tr>
					<td><form:input
							path="secondaryContactTypeList[${i.index}].contactId" type="text"
							name="s_ContactId" readonly="true" disabled="${isDisabled}" /></td>

					<td><form:input
							path="secondaryContactTypeList[${i.index}].firstName" type="text"
							name="Name" id="firstName" disabled="${isDisabled}" /></td>
					<td><form:input
							path="secondaryContactTypeList[${i.index}].lastName" type="text"
							name="l_Name"  disabled="${isDisabled}"/></td>
					<td><form:input
							path="secondaryContactTypeList[${i.index}].jobTitle" type="text"
							name="s_JobTile" disabled="${isDisabled}" /></td>
					<td><form:input
							path="secondaryContactTypeList[${i.index}].email" type="text"
							id="secondary_email" name="s_Email" onchange="validate()" disabled="${isDisabled}" />

						<h4>
							<font color="red"><span id="error_secondary_mail"></span></font>
						</h4></td>

					<td><form:input
							path="secondaryContactTypeList[${i.index}].telephone" type="text"
							id="secondaryPhone" name="s_Telephone" onchange="validate()" disabled="${isDisabled}" />

						<h4>
							<font color="red"><span id="error_secondary_telephone"></span></font>
						</h4></td>
					<td><form:input
							path="secondaryContactTypeList[${i.index}].mobilePager" type="text"
							id="secondaryMobile" name="s_Mobilepager" onchange="validate()" disabled="${isDisabled}" />
						<h4>
							<font color="red"><span id="error_secondary_mobiletelephone"></span></font>
						</h4></td>


				</tr>
			</c:forEach>
		</tbody>

	</table>

</body>
</html>
<!-- customerContacts.jsp has the contactdetails for all the contactTypes Reuters,Commercial,Technical contacts-->
<head>

</head>

<table class="bordered">
	<thead>
	<tr>
		<th>ContactID</th>
		<th>FirstName</th>
		<th>LastName</th>
		<th>Email</th>
		<th>Phone</th>
		<th class="${hideJobTitle}">JobTitle</th>
		<th>MobilePhone</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="orderContactDTO" items="${id}">
	
		<tr>

			<td><input name="contactId" type="text"
				value="${orderContactDTO.contactId}"disabled="${isDisabled}" /></td>
			<td><input name="firstName" type="text"
				value="${orderContactDTO.firstName}" disabled="${isDisabled}" /></td>
				
			<td><input name="lastName" type="text"
				value="${orderContactDTO.lastName}" disabled="${isDisabled}" /></td>
			<td><input name="email" type="email" name="email" value="${orderContactDTO.email}"	disabled="${isDisabled}" /></td>
			<td><input name="phone" type="text"	value="${orderContactDTO.phone}"disabled="${isDisabled}" /></td>
			<td class="${hideJobTitle}"><input name="jobTitle" type="text"	value="${orderContactDTO.jobTitle}" disabled="${isDisabled}" /></td>
			<td><input name="mobilePager" type="text" value="${orderContactDTO.mobilePager}" disabled="${isDisabled}" /></td>
		</tr>
	</c:forEach>
	</tbody>
</table>



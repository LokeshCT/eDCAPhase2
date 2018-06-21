<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDCA ERROR</title>
<style>
#outerBorder {
	background-color: "FFBEFF";
	width: 80%;
	padding: 25px;
	border: 5px solid Purple;
	margin: 25px;
	
}

h2,h2,h3,h4 {
	color: purple;
	top: 50%;  
}

td
{
align : left;
}
</style>
<script type="text/javascript">
	function toggleDetailedError() {
		var detailedErrorElement = document.getElementById("detailedError");
		var toggleButtonElement = document.getElementById("toggleButton");
		if (detailedErrorElement.style.visibility == 'visible') {
			detailedErrorElement.style.visibility = 'hidden';
			toggleButtonElement.value = "Show Error Trace";
		} else {
			detailedErrorElement.style.visibility = 'visible';
			toggleButtonElement.value = "Hide Error Trace";
		}

	}
</script>
</head>
<body>
	<div id="outerBorder">
		<table width="100%" border=0>
			<tr>
				<td width="20%" />
				<td width="15%" />
				<td width="40%" />
				<td width="5%" />
			</tr>
			<tr>
				<td />
				<td align="center" colspan=3><h2>OOPS! Something went wrong!</h2></td>

			</tr>
			<tr>

				<td rowspan="4"><img
					src="<c:url value="/assets/img/error.png"/>" height="100"
					width="100"></td>

				<td colspan=2 align="center"><h3>Error Details</h3></td>
				<td />
				
			</tr>
			
			<tr>
				<td><h5>URL Accessed:</h5></td>
				<td>${url}</td>
				<td />
			</tr>
			<tr>
				
				<td><h5>Error Type:</h5></td>
				<td>${errorType}</td>
			    <td></td>
			</tr>
			<tr>
				
				<td><h5>Error Message:</h5></td>
				<td>${message}</td>
				<td />
			</tr>
			<tr>
				<td height="20px"></td>
				<td></td>
				<td></td>
				<td></td>	
			</tr>
             <tr>
                <td></td>
				<td></td>
				<td colspan="2"><input type="button" id="toggleButton" onclick="toggleDetailedError()"
			value="Show Error Trace" ></td>
			
			</tr>


		</table>
		
		
		<div id="detailedError" style="visibility: hidden; "  width="100%">
			<h5>Error Trace:</h5>
			<textarea style="width: 100%; height: 180px;" id="aboutDescription"> ${detailedError}</textarea>
			

		</div>
	</div>



</body>
</html>
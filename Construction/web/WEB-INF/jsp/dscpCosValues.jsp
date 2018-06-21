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
<title>DSCP_COS_VALUES</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">DSCP COS</center>
					<br />


					<form:form action="saveCustomerDetails" method="post"
						commandName="dscpCosDTO">



						<center>
							<c:out value="${dscpCosDTO.lblCosModel}" />
							<c:out value="${dscpCosDTO.cosPolicy}" />
							<br> <c:out value="${dscpCosDTO.lblCustomization}" /> <br>
									<c:out value="All Speeds in kbps " />
						</center>
						<table>
							<tr>
								<td>TIR</td>
								<td><form:input path="connectionBandWidth"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>CIPR EF</td>
								<td><form:input path="ciprEf" disabled="${isDisabled}" />
								</td>
								
							</tr>
							<tr>
								<td>CIPR AF1</td>
								<td><form:input path="ciprAf1" disabled="${isDisabled}" />
								</td>
								<td>MIPR AF1</td>
								<td><form:input path="miprAf1" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>CIPR AF2</td>
								<td><form:input path="ciprAf2" disabled="${isDisabled}" />
								</td>
								<td>MIPR AF2</td>
								<td><form:input path="miprAf1" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>CIPR AF3</td>
								<td><form:input path="ciprAf3" disabled="${isDisabled}" />
								</td>
								<td>MIPR AF3</td>
								<td><form:input path="miprAf1" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>CIPR AF4</td>
								<td><form:input path="ciprAf4" disabled="${isDisabled}" />
								</td>
								<td>MIPR AF4</td>
								<td><form:input path="miprAf1" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>CIPR MGMT</td>
								<td><form:input path="ciprMgt" disabled="${isDisabled}" />
								</td>
								<td>MIPR DE</td>
								<td><form:input path="miprDe" disabled="${isDisabled}" />
								</td>
							</tr>
						</table>

						<%--    <c:forEach
								items="${dscpCosDTO}"
								var="items">
								
							<c:out value="${items.lblCosModel}"/>	
								</c:forEach> --%>

						<%-- 						
			
 				<table> 
 							<tr> 
 								<td>COSModel</td> 
								<td><form:input path="lblCosModel" disabled="${isDisabled}" /> 
 								</td> 
 							</tr> 
 							<tr> 
								<td>CUSTOMIZATION</td> 
 						<td><form:input path="lblCustomization" disabled="${isDisabled}" /> 
 								</td> 

 						</table>  --%>
					</form:form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>CPEConfigurePage</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
 rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">

				<div class="form" style="width: auto">
					<center style="font-size: 30px">CPE Configure Page</center>
					<br />
					<form:form action="cpeDetails" method="post"
						commandName="cPEConfigureDTO">

						<table class="bordered" style="width:90%" >
							
								<input style="float:left;" type="button" value="AddCascadeCpe"
										disabled="${isDisabled}" />
									
							
								
								<thead>
									<th></th>
									<th></th>
									<th>CpeUsage</th>
									<th>BaseRouter</th>
									<th>Chasis</th>
									<th>ProductType</th>
								</thead>
								
								
								<c:forEach var="User" items="${cPEConfigureDTOList}">
								<tr>
									<td><input type="button" value="Edit"  disabled="${isDisabled}"></td>
									<td><input type="button" value="Delete" disabled="${isDisabled}"></td>
									<td><c:out value="${User.cpeUsage}" /></td>
									<td><c:out value="${User.baseRouter}" /></td>
									<td><c:out value="${User.chassis}" /></td>
									<td><c:out value="${User.productType}" /></td>
									
								</tr>
								</c:forEach>
								
								
						</table>
								<br></br><input style="float:left"  type="button" value="Save" disabled="${isDisabled}">
							<br>
						<table class="tableborderspacing">
							<c:forEach var="User" items="${cPEConfigureDTOList}">
								<tr>
									<td>CPEUsageType</td>
									<td><form:select path="cpeUsage" name="User"
											disabled="${isDisabled}">
											<form:option value="">${User.cpeUsage}</form:option>
										</form:select></td>
								</tr>
								<tr>
									<td>CPEOrderingType</td>
									<td><form:select path="cpeOrderingType" name="User">
											<form:option value="">${User.cpeOrderingType}</form:option>
										</form:select></td>
								</tr>
								<tr>
									<td>BaseRouter</td>
									<td><form:select path="baseRouter" name="User">
											<form:option value="">${User.baseRouter}</form:option>
										</form:select></td>
								</tr>
								<tr>
									<td>Model</td>
									<td><form:input path="model" disabled="${isDisabled}"
											value="${User.model}" /></td>
								</tr>
								<tr>
									<td>CpeTerm</td>
									<td><form:select path="cpeTerm" name="User"
											disabled="${isDisabled}">
											<form:option value="">${User.cpeTerm}</form:option>
										</form:select></td>
								</tr>

								<tr>
									<td>Chasis</td>
									<td><form:input  path="chassis"
											disabled="${isDisabled}" value="${User.chassis}" /></td>
								</tr>

								<tr>
									<td>Rps</td>
									<td><form:input path="rps" disabled="${isDisabled}"
											value="${User.rps}" /></td>
								</tr>

								<tr>
									<td>PowerCables</td>
									<td><form:input path="powerCables"
											disabled="${isDisabled}" value="${User.powerCables}" /></td>
								</tr>

								<tr>
									<td>PowerCord</td>
									<td><form:select path="powerCord" name="User">
											<form:option value="">${User.powerCord}</form:option>
										</form:select></td>
								</tr>



								<tr>
									<td>FlashMemory</td>
									<td><form:select path="flashMemory" name="User">
											<form:option value="">${User.flashMemory}</form:option>
										</form:select></td>
								</tr>




								<tr>
									<td>DRam</td>
									<td><form:select path="dRam" name="User">
											<form:option value="">${User.dRam}</form:option>
										</form:select></td>
								</tr>




								<tr>
									<td>USB</td>
									<td><form:select path="usb" name="User">
											<form:option value="">${User.usb}</form:option>
										</form:select></td>
								</tr>




								<tr>
									<td>HardDisc</td>
									<td><form:select path="hardDisk" name="User">
											<form:option value="">${User.hardDisk}</form:option>
										</form:select></td>
								</tr>




								<tr>
									<td>IOS</td>
									<td><form:select path="ios" name="User">
											<form:option value="">${User.ios}</form:option>
										</form:select></td>
								</tr>




								<tr>
									<td>CPEFirmWare</td>
									<td><form:select path="cpe_firm_ware" name="User">
											<form:option value="">${User.cpe_firm_ware}</form:option>
										</form:select></td>
								</tr>


								<tr>
									<td>MaintananceOption</td>
									<td><form:select path="maintenanceOption" name="User">
											<form:option value="">${User.maintenanceOption}</form:option>
										</form:select></td>
								</tr>

								<tr>
									<td>LanType</td>
									<td><form:select path="lanType" name="User">
											<form:option value="">${User.lanType}</form:option>
										</form:select></td>
								</tr>

								<tr>
									<td>WicSlots</td>
									<td><form:input path="wic_slots" disabled="${isDisabled}"
											value="${User.wic_slots}" /></td>
								</tr>


								<tr>
									<td>NetWorkModules</td>
									<td><form:input path="network_Modules"
											disabled="${isDisabled}" value="${User.network_Modules}" /></td>
								</tr>

								<tr>
									<td>PortAdaptors</td>
									<td><form:input path="port_Adaptors"
											disabled="${isDisabled}" value="${User.port_Adaptors}" /></td>
								</tr>

								<tr>
									<td>SharedPortAdaptors</td>
									<td><form:input path="sharedPortAdaptors"
											disabled="${isDisabled}" value="${User.sharedPortAdaptors}" /></td>
								</tr>


								<tr>
									<td>BuiltIn</td>
									<td><form:input path="builtinslots"
											disabled="${isDisabled}" value="${User.builtinslots}" /></td>
								</tr>




								<tr>
									<td>RackMountKit</td>
									<td><form:select path="rack_Mount_Kit" name="User">
											<form:option value="">${User.rack_Mount_Kit}</form:option>
										</form:select></td>
								</tr>


								<tr>
									<td>CpeOwner</td>
									<td><form:select path="cpe_Owner" name="User">
											<form:option value="">${User.cpe_Owner}</form:option>
										</form:select></td>
								</tr>

								<tr>
									<td>EquipmentFinancing</td>
									<td><form:select path="equipment_Financing" name="User">
											<form:option value="">${User.equipment_Financing}</form:option>
										</form:select></td>
								</tr>


								<tr>
									<td>DartNumber</td>
									<td><form:input path="dartNumber"
											value="${User.dartNumber}" /></td>
								</tr>
								<tr>
									<td></td>
									<c:choose>
										<c:when test="${User.dartNumber eq null}">
											<td><font color="red">Dart Number has
													expired.Override None with new value </font></td>
										</c:when>
										<c:when test="${User.dartNumber eq 'None'}">
											<td><font color="red">Dart Number has
													expired.Override None with new value </font></td>

										</c:when>
									</c:choose>
								</tr>

								<tr>
									<td>SerialNumber</td>
									<td><form:input path="serialNumber"
											disabled="${isDisabled}" value="${User.serialNumber}" /></td>
								</tr>


								<tr>
									<td>License</td>
									<td><form:select path="license" name="User">
											<form:option value="">${User.license}</form:option>
										</form:select></td>
								</tr>

								<tr>

									<td>BaseConfig</td>
									<td><form:select path="baseConfig" name="User">
											<form:option value="">${User.baseConfig}</form:option>
										</form:select></td>
								</tr>



								<tr>
									<%
				  boolean val = false;
				%>
									<td>Extented Portfolio Needed</td>
									<c:if test="${User.portFolioNeeded=='Y' }">
										<%
					  val = true;
					%>
									</c:if>

									<td><input type="checkbox" path="portFolioNeeded"
										name="myCheckbox" <%= (val)?"checked":"" %>
										value="<c:out value="${User.portFolioNeeded=='Y' }" />"
										id="access" name="access" disabled="${isDisabled}" /></td>
								</tr>


								<tr>
									<td>Rack/Shelf</td>
									<td><form:input path="cPE_Rack_Shelf"
										value="${ConfigureAccess.cPE_Rack_Shelf}" 
										disabled="${isDisabled}" /></td>
								</tr>

								<tr>
									<th></th>
									<td><input type="button" value="save" /></td>
								</tr>


		</c:forEach>


						</table>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

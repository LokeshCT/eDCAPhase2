<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base target="_parent" />
<title>Configure Access Type</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"  rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width:auto">
					<center style="font-size: 30px">Configure Access Type</center>
					<br />

					<form:form action="saveCustomerDetails" method="post"
						commandName="configureAccessTypeDTO">
						<table>
							<tr>
								<td>Access Type</td>
								<td><form:input path="accessType" disabled="${isDisabled}" />
								</td>


							</tr>
							<tr>
								<td>Access Circuit Resilience and Diversity</td>
								<td><form:input size="80"
										path="accessCktResilenceDiversity" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Existing Circuit</td>
								<td><form:input path="existingCircuit"
										disabled="${isDisabled}" /></td>
							</tr>

							<tr>
								<td>Interface Type</td>
								<td><form:input path="interfaceType"
										disabled="${isDisabled}" /></td>
								<%
								  boolean valu = false;
								%>
								<td>Jdsu Probe On Pe</td>
								<c:if test="${configureAccessTypeDTO.jdsuProbeOnPe=='Yes' }">
									<%
									  valu = true;
									%>
								</c:if>

								<td><input type="checkbox" path="jdsuProbeOnPe"
									name="myCheckbox" <%= (valu)?"checked":"" %>
									value="<c:out value="${configureAccessTypeDTO.jdsuProbeOnPe}" />"
									id="access" name="access" disabled="${isDisabled}" /></td>
							</tr>


							<%-- <td><form:input path="jdsuProbeOnPe"
										disabled="${isDisabled}" /></td> --%>
							</tr>
							<tr>
								<td>Electrical Interface</td>
								<td><form:input path="electricalInterface"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Physical Connector</td>
								<td><form:input path="physicalConnector"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Access Bearer Circuit Framing Structure</td>
								<td><form:input path="accessBearerPhyConn"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Physical Access Speed</td>
								<td><form:input path="physicalAccessSpeed"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Additional Access Capacity Available</td>
								<td><form:input path="additionalAccess"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Ethernet Phase Type</td>
								<td><form:input path="ethPhaseType"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Port Speed</td>
								<td><form:input path="portSpeed" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Aggregated Multicast Bandwidth(In Kbps)</td>
								<td><form:input path="aggregatedMulticastBandwidth"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Number Of 64K Time Slot</td>
								<td><form:input path="numOfTimeSlot"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<%
								  boolean val = false;
								%>
								<td>Access Offer</td>
								<c:if test="${qrefAccess.accessOffer=='Y' }">
									<%
									  val = true;
									%>
								</c:if>

								<td><input type="checkbox" path="accessOffer"
									name="myCheckbox" <%= (val)?"checked":"" %>
									value="<c:out value="${qrefAccess.accessOffer}" />" id="access"
									name="access" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Qref</td>
								<td><input path="qref"
									value="<c:out value="${qrefAccess.qref}" />"
									disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Network</td>
								<td><form:input path="network" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>Framing</td>
								<td><form:input path="framing" disabled="${isDisabled}" />
								</td>
							</tr>
							<tr>
								<td>LMI</td>
								<td><form:input path="lmi" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Type</td>
								<td><form:input path="frameType" disabled="${isDisabled}" />

								</td>
							</tr>
							<tr>
								<td>FR PVC Class of Service</td>
								<td><form:input path="frPvcClassOfService"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>BT PoP Location</td>
								<td><form:input path="btPopLocation"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>PL Access Speed</td>
								<td><form:input path="plAccessSpeed"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>PL Electrical Interface</td>
								<td><form:input path="plElectricalInterface"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>PL Physical Connector Type</td>
								<td><form:input path="plPhysicalConnectoreType"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>POR Reference Number</td>
								<td><form:input path="porReferenceNumber"
										disabled="${isDisabled}" /></td>
							</tr>

							<tr>
								<td>Currency</td>
								<td><form:input path="currency" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Supplier quoted NRC</td>
								<td><form:input path="supplierQuotedNrc"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Supplier quoted MRC</td>
								<td><form:input path="supplierQuotedMrc"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Is the currecncy used a converted currency</td>
								<td><form:input path="convertedCurrency"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Has the lowest Possible bandwidth circuit been chosen
									for this order?</td>
								<td><form:input path="bandwidthCircuit"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Has the lowest cost access option been chosen for the
									current order?</td>
								<td><form:input path="cheapestSolution"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Is the optimal access technology used by this order</td>
								<td><form:input path="optimalAccessTech"
										disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td><form:button disabled="${isDisabled}">Validate Cost</form:button>
								</td>
							</tr>
							<tr>
								<td>Is Access cost within CE limit?</td>
								<td><form:input path="ceLimit" disabled="${isDisabled}" /></td>
							</tr>
							<tr>
								<td>Is Access cost within Regional limit?</td>
								<td><form:input path="regionalLimitl"
										disabled="${isDisabled}" /></td>
							</tr>



						</table>
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
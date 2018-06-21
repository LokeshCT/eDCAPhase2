<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Configure Wicnmpa</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: 600px">
					<center style="font-size: 30px">Configure WIC/NM/PA</center>
					<br />



					<form:form action="configureWicnmpaDTOList" method="post"
						commandName="configureWicnmpaDTOList">


						<table class="bordered">

							<thead>
							<tr>
								<th></th>
								<th>Cpe Usage</th>
								<th>Base Router</th>
								<th>Chassis</th>
								<th>CpeSeqId</th>
							</tr>
							</thead>
							<c:forEach var="configureWicnmpaDTO"
								items="${configureWicnmpaDTOList.configCardList}">

								<tr>
									<td><input type="button" value="Select" /></td>
									<td><input path="cpeUsage"
										value="<c:out value="${configureWicnmpaDTO.cpeUsage}" />"
										disabled="${isDisabled}" /></td>
									<td><input path="baseRouter"
										value="<c:out value="${configureWicnmpaDTO.baseRouter}" />"
										disabled="${isDisabled}" /></td>
									<td><input size="80" path="chassis"
										value="<c:out value="${configureWicnmpaDTO.chassis}" />"
										disabled="${isDisabled}" /></td>
									<td><input path="cpeSeqId"
										value="<c:out value="${configureWicnmpaDTO.cpeSeqId}" />"
										disabled="${isDisabled}" /></td>
								</tr>
							</c:forEach>
						</table>
						<%---------------! st grid----------- CARD SELECTION------------------%>
						<table class="bordered">
							<c:forEach var="configureWicnmpaDTO"
								items="${configureWicnmpaDTOList.slotList}">

                             <h3>Card Selection</h3>
                            <thead>
								<tr>
									<th>Card Slot</th>
									<th>Port Type</th>
									<th>Card</th>
								</tr>
								
							</thead>	
								<!----------------------- HWIC SLOTS------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.hwicSlots}" scope="request"
										var="count" />
									<c:forEach var="hwicSlotsCount" begin="1" end="${count}">
										<c:set value="HWIC ${hwicSlotsCount}" scope="request"
											var="cardSlotHWIC" />
										<tr>
											<td>HWIC <c:out value="${hwicSlotsCount}  "></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">

												<c:if
													test="${cardSlotHWIC == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>
								<!----------------------- WIC SLOTS------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.wicSlots}" scope="request"
										var="count" />

									<c:forEach var="wicSlotsCount" begin="1" end="${count}">
										<c:set value="WIC ${wicSlotsCount}" scope="request"
											var="cardSlot" />
										<tr>
											<td>WIC <c:out value="${wicSlotsCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">
											


													<c:if
														test="${cardSlot == configurePortList.cardPosition}">
														<td><c:out value="${configurePortList.portType}"></c:out></td>
														<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
													</c:if>
												
											</c:forEach>
										</tr>
									</c:forEach>
								</tr>
								<!----------------------- networkModules------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.networkModules}"
										scope="request" var="count" />

									<c:forEach var="networkModulesCount" begin="1" end="${count}">
										<c:set value="NM ${networkModulesCount}" scope="request"
											var="cardSlotNm" />
										<tr>
											<td>NM <c:out value="${networkModulesCount}"></c:out></td>

											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if test="${cardSlotNm == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>
								<!----------------------- portAdapters------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.portAdapters}"
										scope="request" var="count" />
									<c:forEach var="portAdaptersCount" begin="1" end="${count}">
										<c:set value="PA ${portAdaptersCount}" scope="request"
											var="cardSlotPa" />
										<tr>

											<td>PA <c:out value="${portAdaptersCount}"></c:out></td>

											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if test="${cardSlotPa == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>
								<!----------------------- shared port------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.sharedPort}"
										scope="request" var="count" />
									<c:forEach var="sharedPortCount" begin="1" end="${count}">
										<c:set value="SPA ${sharedPortCount}" scope="request"
											var="cardSlotSpa" />
										<tr>
											<td>SPA <c:out value="${sharedPortCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if
													test="${cardSlotSpa == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>
								<!----------------------- built in slots------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.builtinSlots}"
										scope="request" var="count" />
									<c:forEach var="builtinSlotsCount" begin="1" end="${count}">
										<c:set value="BUILTIN ${builtinSlotsCount}" scope="request"
											var="cardSlotBuiltin" />
										<tr>
											<td>BUILTIN <c:out value="${builtinSlotsCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if
													test="${cardSlotBuiltin == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>

								<!----------------------- carrier slots------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.carrierSlots}"
										scope="request" var="count" />
									<c:forEach var="carrierSlotsCount" begin="1" end="${count}">
										<c:set value="carrierSlots ${carrierSlotsCount}"
											scope="request" var="cardSlotCs" />
										<tr>
											<td>carrierSlots <c:out value="${carrierSlotsCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if test="${cardSlotCs == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>

								<!----------------------- processor slots------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.processorSlots}"
										scope="request" var="count" />
									<c:forEach var="processorSlotsCount" begin="1" end="${count}">
										<c:set value="processorSlots ${processorSlotsCount}"
											scope="request" var="cardSlotPs" />
										<tr>
											<td>processorSlots <c:out value="${processorSlotsCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if test="${cardSlotPs == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>

								<!----------------------- switching slots------------------------------------------------------------------------ -->
								<tr>
									<c:set value="${configureWicnmpaDTO.switchingSlots}"
										scope="request" var="count" />
									<c:forEach var="switchingSlotsCount" begin="1" end="${count}">
										<c:set value="switchingSlots ${switchingSlotsCount}"
											scope="request" var="cardSlotSs" />
										<tr>
											<td>switchingSlots <c:out value="${switchingSlotsCount}"></c:out></td>
											<c:forEach var="configurePortList"
												items="${configureWicnmpaDTOList.portList}">


												<c:if test="${cardSlotSs == configurePortList.cardPosition}">
													<td><c:out value="${configurePortList.portType}"></c:out></td>
													<td><c:out value="${configurePortList.cardDesc}"></c:out></td>
												</c:if>

											</c:forEach>
										</tr>
									</c:forEach>
								</tr>

							</c:forEach>
						</table>
						<!----------------------- END OF 2ND GRID------------------------------------------------------------------------ -->
						
						<%--------------------- CABLE SELECTION------------------%>
						<table>
							<h3>Cable Selection</h3>

						</table>

						<%--------------------------------- Getting the LAN cables------------------%>
						<table class="bordered">
							<thead>
							<tr>
								<th>LAN Cables</th>
								<th>Total Cables</th>
							</tr>
							</thead>

							<c:forEach var="Lancables" items="${cable.lanCable}">
								<tr>
									<td><input path="cableShortName"
										value="<c:out value="${Lancables.cableShortName}" />"
										disabled="${isDisabled}" /></td>

									<td><c:forEach var="noOfCable"
											items="${cable.totalLanCable}">
											
											<c:if
												test="${noOfCable.cableDesc eq Lancables.cableShortName }">
												<input path="totalCable"
													value="<c:out value="${noOfCable.totalCable}" />"
													disabled="${isDisabled}" />
											</c:if>
										</c:forEach></td>
								</tr>

							</c:forEach>


						</table>
						<%-------------------------- Getting the WAN cables ------------%>
						<table class="bordered">
							<thead>
							<tr>
								<th>WAN Cables</th>
								<th>Total Cables</th>
							</tr>
							</thead>

							<c:forEach var="wanCable" items="${cable.wanCable}">

								<tr>
									<td><input path="cableShortName"
										value="<c:out value="${wanCable.cableShortName}" />"
										disabled="${isDisabled}" /></td>
									<%-- <td><input path="totalCable" value="<c:out value="${wanCable.totalCable}" />"
						       disabled="${isDisabled}" />
						       </td> --%>
									<td><c:forEach var="noOfCable" items="${cable.wanCable}">

											<c:if
												test="${noOfCable.cableDesc eq wanCable.cableShortName }">
												<input path="totalCable"
													value="<c:out value="${noOfCable.totalCable}" />"
													disabled="${isDisabled}" />
											</c:if>
										</c:forEach></td>
								</tr>


							</c:forEach>


						</table>
						<%-------------------------Getting the Miscellaneous cables-------- --%>
						<table class="bordered">
							<thead>
							<tr>
								<th>Miscellaneous Parts</th>
								<th>Total Cables</th>
							</tr>
							</thead>
							<c:forEach var="miscellaneous"
								items="${cable.miscellaneousCable}">

								<tr>
									<td><input path="cableShortName"
										value="<c:out value="${miscellaneous.cableShortName}" />"
										disabled="${isDisabled}" /></td>
									<td><c:forEach var="noOfCable" items="${cable.wanCable}">

											<c:if
												test="${noOfCable.cableDesc eq miscellaneous.cableShortName }">
												<input path="totalCable"
													value="<c:out value="${noOfCable.totalCable}" />"
													disabled="${isDisabled}" />
											</c:if>
										</c:forEach></td>
								</tr>

							</c:forEach>


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

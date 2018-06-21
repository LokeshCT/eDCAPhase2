<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_parent" />
<title>Site Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/ajaxJquery.js" />"></script>
<link href="<c:url value="/assets/css/custom/style.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/assets/css/custom/coinSlider.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/assets/js/plug-in/jquery-1.11.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/script.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/coin-slider.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/js/custom/SitePage.js" />"></script>
</head>
<body>
	<div class="main">
		<div class="content">
			<div class="content_resize">
				<div class="form" style="width: auto">
					<center style="font-size: 30px">Site</center>

					<%
						String siteId = (String) request.getAttribute("siteId");
					%>
					<input type="hidden" id="siteId" value="<%=siteId%>"></input> <br>
						<form:form method="post" commandName="SitePageDTO" id="form">
							<table style="border-spacing: 5px">


								<tr>

									<td>DCA Reference ID</td>
									<td><form:input path="dcaReferenceId"
											value="${DcaReferenceId}" disabled="${isDisabled}" /></td>

								</tr>
								<tr>
									<td>Highest Site SLA</td>
									<td><form:input path="highestSiteSla"
											disabled="${isDisabled}" /></td>


								</tr>
								<tr>
									<c:if
										test="${(SitePageDTO.resilience eq 'Secure')||(SitePageDTO.resilience eq 'Secure +')}">
										<c:choose>
											<c:when
												test="${(SitePageDTO.fiberConnectivity eq null)||(SitePageDTO.fiberConnectivity eq ' ')}">

											</c:when>
											<c:otherwise>

												<c:if test="${SitePageDTO.fiberConnectivity eq 'Yes'}">

													<td>Optical Fibre Connectivity For ISL</td>
													<td><form:checkbox path="fiberConnectivity"
															id="fiberConnectivity"
															value="${SitePageDTO.fiberConnectivity}"
															CHECKED="checked" onclick='return checkOpticaleFiber()' /></td>
												</c:if>
												<c:if test="${SitePageDTO.fiberConnectivity eq 'No'}">

													<td>Optical Fibre Connectivity For ISL</td>
													<td><form:checkbox path="fiberConnectivity"
															id="fiberConnectivity" value="null"
															onclick='return checkOpticaleFiber()' /></td>
												</c:if>

											</c:otherwise>

										</c:choose>
									</c:if>
								</tr>


								<tr>

									<c:set var="secure" value="${overridenValue}" scope="request" />

									<c:if
										test="${(SitePageDTO.highestSiteSla eq 'Silver')||(SitePageDTO.highestSiteSla eq 'FCE Resilient Split')||(SitePageDTO.highestSiteSla eq 'FCE Resilient')||(SitePageDTO.highestSiteSla eq 'SILVER SPLIT SITE')}">

										<c:choose>
											<c:when test="${SitePageDTO.secureOverride eq null}">
												<td>Override Secure+ Option?</td>
												<td><form:checkbox path="secureOverride"
														id="secureOverride" onclick='return checkButton()'
														value="0" /></td>

											</c:when>
											<c:otherwise>
												<c:if test="${requestScope.secure eq '1'}">

													<c:if test="${SitePageDTO.secureOverride eq '0'}">
														<td>Override Secure+ Option?</td>
														<td><form:checkbox path="secureOverride"
																id="secureOverride" onclick='return checkButton()'
																value="null" /></td>

													</c:if>
													<c:if test="${SitePageDTO.secureOverride eq '1'}">
														<td>Override Secure+ Option?</td>
														<td><form:checkbox path="secureOverride"
																id="secureOverride" CHECKED="checked"
																onclick='return checkButton()'
																value="${SitePageDTO.secureOverride}" /></td>

													</c:if>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:if>
								</tr>
								<tr>
									<c:choose>
										<c:when
											test="${(SitePageDTO.highestSiteSla ne 'Silver')||(SitePageDTO.highestSiteSla ne 'FCE Resilient Split')||(SitePageDTO.highestSiteSla ne 'FCE Resilient')||(SitePageDTO.highestSiteSla ne 'SILVER SPLIT SITE')}">

											<td>Resilience</td>
											<td><form:select path="resilience">
													<form:option value="${SitePageDTO.resilience}"></form:option>
												</form:select></td>
										</c:when>
										<c:otherwise>
											<td>Resilience</td>
											<td><form:select path="resilience">
													<c:forEach var="recilience" items="${sitePageRecelience}">
														<form:option value="${recilience.resilience}"></form:option>

													</c:forEach>
												</form:select></td>
										</c:otherwise>
									</c:choose>

								</tr>
								<tr>
									<td>Pricing</td>

									<td><form:select path="pricing" disabled="disabled"
											name="pricing">
											<c:forEach var="price" items="${priceing}">

												<option value="${price.pricing}">${price.pricing}</option>
											</c:forEach>

										</form:select></td>
								</tr>
								<tr>
									<td>Customer Required by Date</td>

									<fmt:formatDate type="date" value="${SitePageDTO.crd}"
										var="dateFormat" />
									<td><form:input path="crd" value="${dateFormat}"
											name="crd" /></td>
								</tr>
								<tr>
									<td>Special Bid Reference</td>
									<td><form:select path="specialBidReference"
											multiple="multiple" name="specialBidReference"
											id="specialBidReference">
											<c:forEach var="SitePageDTO" items="${SpecialBidReference}">
												<form:option selected
													value="${SitePageDTO.specialBidReference}">${SitePageDTO.specialBidReference}</form:option>
											</c:forEach>
										</form:select></td>
									<td><input type="button" value="Clear"
										onclick='return clearDrodown()' /></td>
									<td>For Multiple Selection Press [CTRL] Key then Click</td>
								</tr>
								<tr>

									<c:choose>
										<c:when test="${SitePageDTO.structureCabling == null}">

											<td>Structure Cabling Required</td>
											<td><form:checkbox path="structureCabling"
													id="structureCabling" onclick='return checkStructure()'
													name="structureCabling" value="None" /></td>

										</c:when>
										<c:otherwise>
											<c:set var="satilite" value="${satelliteAccess}"
												scope="request" />
											<c:if test="${SitePageDTO.structureCabling != null}">
												<c:choose>
													<c:when test="${requestScope.satilite eq 'Yes'}">
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when
																test="${(SitePageDTO.resilience=='Secure+ Split')||(SitePageDTO.resilience=='Secure Split')||(SitePageDTO.resilience=='Standard')}">
																<c:if test="${SitePageDTO.lanSwitch eq 'NO'}">
																</c:if>
															</c:when>
															<c:when test="${(SitePageDTO.resilience=='Standard')}">
																<c:if test="${SitePageDTO.lanSwitch eq 'NO'}">
																</c:if>
															</c:when>
															<c:otherwise>
																<c:if test="${SitePageDTO.structureCabling  eq 'S'}">
																	<td>Structure Cabling Required</td>
																	<td><form:checkbox path="structureCabling"
																			value="${SitePageDTO.structureCabling}"
																			id="structureCabling" CHECKED="checked"
																			onclick='return checkStructure()'
																			name="structureCabling" /></td>
																</c:if>
																<c:if test="${SitePageDTO.structureCabling  eq 'D'}">
																	<td>Structure Cabling Required</td>
																	<td><form:checkbox path="structureCabling"
																			value="null" id="structureCabling"
																			onclick='return checkStructure()'
																			name="structureCabling" /></td>
																</c:if>

															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</c:if>

											<%-- <c:if
												test="${(SitePageDTO.resilience=='Secure+ Split')||(SitePageDTO.resilience=='Secure Split')||(SitePageDTO.resilience=='Standard')}">
												<td>Structure Cabling Required</td>
												<td><form:checkbox path="structureCabling"
														value="${SitePageDTO.structureCabling}"
														id="structureCabling" CHECKED
														onclick='return checkStructure()' name="structureCabling"
														disabled="${isDisabled}" /></td>

											</c:if> --%>
										</c:otherwise>
									</c:choose>

								</tr>

								<input type="hidden" id="hiddenField" name="hiddenField" />
								<tr>
									<td>Zone</td>
									<td><form:input path="zone" disabled="${isDisabled}" /></td>
								</tr>
								<tr>
									<td>Access Option</td>
									<td><form:input path="accessOption"
											disabled="${isDisabled}" /></td>
								</tr>
								<tr>
									<td>Infrastructure Site</td>
									<td><form:input path="nativeultraset"
											disabled="${isDisabled}" /></td>
								</tr>
								<tr>
									<td>One Voice Service Demarcation</td>
									<td><form:input path="onvDemarcOption"
											disabled="${isDisabled}" /></td>
								</tr>

							</table>

							<input type="submit" value="Next" id="saveNewUsers"></input>

						</form:form>
						<div id="result1"></div>
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

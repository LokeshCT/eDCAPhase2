//Ajax calling for displaying  the MakeAsDefault.
$(document)
		.ready(
				function() {
					$("#saveNewUsers")
							.click(
									function() {
										$("#result1").show();
										document.getElementById("result1").innerHTML = "";
										var profileteamid = document
												.getElementById("profile").value;
										var userid = document
												.getElementById("User").value;

										$
												.ajax({
													url : "/edca-web/edca/userDetails/"
															+ profileteamid
															+ "/" + userid,
													type : "GET",
													success : function(data) {
														document
																.getElementById("result1").innerHTML = "Your Profile is successfully defaulted";
														window.location = "/edca-web/edca/userDetails/"
																+ userid;

													},
													error : function(e) {

													}
												});

									});

					var options = document.getElementById('profile').options;
					var length = options.length;
					var userid = document.getElementById("User").value;

					for (var i = 0; i < length; i++) {

						if (options[i].value === 'T') {
							options[i].selected = true;
						} else {
							document.getElementById("profile").selectedIndex = 0;

						}
					}

					// Ajax calling for displaying the product Names.
					$("#profile")
							.change(
									function() {
										document.getElementById("product").innerHTML = "";
										$("#initialLoadproducts1").hide();
										$("#initialLoadproducts2").hide();
										$("#initialLoadproducts3").hide();
										$("#result1").hide();

										var profileteamid = document
												.getElementById("profile").value;

										$
												.ajax({
													url : "/edca-web/edca/products/"
															+ profileteamid,
													type : "GET",
													dataType : "json",
													contentType : "application/json",
													success : function(data) {
														for (var i = 0; i < data.length; i++) {

															if (data[i].productCatagoryName != null) {

																if (data[i].productCatagoryName == "SPRING") {

																	document
																			.getElementById("product").innerHTML += "<input type='radio'  name='productCatagoryName' value='"
																			+ data[i].productCatagoryName
																			+ ","
																			+ data[i].productCatagoryId
																			+ "' checked='checked'>KEYSTONE</input>";

																} else if (data[i].productCatagoryName == "MPLS") {

																	document
																			.getElementById("product").innerHTML += "<input type='radio'  name='productCatagoryName' value='"
																			+ data[i].productCatagoryName
																			+ ","
																			+ data[i].productCatagoryId
																			+ "' checked='checked'>Ethernet Connect,MPLS and/or Onevoice or GTP CPE</input>";

																}

																else {

																	document
																			.getElementById("product").innerHTML += "<input type='radio'  name='productCatagoryName' value='"
																			+ data[i].productCatagoryName
																			+ ","
																			+ data[i].productCatagoryId
																			+ "'>"
																			+ data[i].productCatagoryName
																			+ "</input>";
																}

															}
														}

													},
													error : function(e) {

													}

												});

									});
				});

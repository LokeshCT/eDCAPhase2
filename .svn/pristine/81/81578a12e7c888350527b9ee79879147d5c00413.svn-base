var int;
function checkButton() {

	if (document.getElementById('secureOverride').checked == true) {
		alert("You are selecting an option to override the Secure\\Secure + behaviour. Please confirm this is correct and click OK to continue?");
		int = "1";
		document.getElementById("secureOverride").value = int;
		return int;
	} else {
		alert("Previous Provide was raised with Secure override option. This will require to re-configure the Access\\GPoP values  the Resilience is changed. Are you sure to continue?");
		int = "0";
		document.getElementById("secureOverride").value = int;
		return int;
	}

}

var str;
function checkStructure() {

	if (document.getElementById('structureCabling').checked == true) {
		str = 'S';
		document.getElementById("structureCabling").value = str;

		return str;
	} else if (document.getElementById('structureCabling').checked == false) {
		str = 'D';
		document.getElementById("structureCabling").value = str;

		return str;
	}

}

var char;
function checkOpticaleFiber() {

	if (document.getElementById('fiberConnectivity').checked == true) {

		char = 'Yes';

		document.getElementById("fiberConnectivity").value = char;

		return char;
	} else if (document.getElementById('fiberConnectivity').checked == false) {
		char = 'No';
		document.getElementById("fiberConnectivity").value = char;

		return char;
	}

}

var lastPartUrl = window.location.href.split("/").pop();
var splitOrderId = lastPartUrl.split('?');
var orderId = splitOrderId[0];
$(document).ready(function() {
	$('#form').submit(function(event) {

		var str = $('#form').serialize();
		$.ajax({
			url : "/edca-web/edca/saveSitePage",
			type : "GET",
			data : str,
			success : function(response) {

				window.parent.$('#siteLocationMenu').trigger('click');

			},
			error : function(error) {

			}
		});
		return false;
	});
});

function clearDrodown() {
	var options = document.getElementById('specialBidReference').options;
	var length = options.length;
	for (var i = 0; i < length; i++) {
		options[i].selected = false;
	}
}

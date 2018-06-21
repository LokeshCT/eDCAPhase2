function checkTechnicalDetails() {

	if (!(document.getElementById('email').value == '' || document
			.getElementById('email').value == 'null')) {

		document.getElementById("error_Email").innerHTML = "";

		var email = document.getElementById('email');
		var filter = /[\s]*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*[\s]*/;

		if (!filter.test(email.value)) {

			document.getElementById("error_Email").innerHTML = "Mail Format Wrong, Use a@b.com";

			return true;
		}

	}
	if (!(document.getElementById('mobilePager').value == '' || document
			.getElementById('mobilePager').value == 'null')) {

		document.getElementById("error_MobilePager").innerHTML = " ";

		var phone = document.getElementById('mobilePager');

		var filter = /^[\d ]+$/;

		if (!filter.test(phone.value)) {

			document.getElementById("error_MobilePager").innerHTML = "Mobile Phone Format Wrong";

			return false;
		}

	}

	if (!(document.getElementById('phone').value == '' || document
			.getElementById('phone').value == 'null')) {

		document.getElementById("error_Phone").innerHTML = " ";

		var phone = document.getElementById('phone');

		var filter = /^[\d ]+$/;

		if (!filter.test(phone.value)) {

			document.getElementById("error_Phone").innerHTML = "Phone Format Wrong";

			return false;
		}

	}

}

var lastPartUrl = window.location.href.split("/").pop();

var splitOrderId = lastPartUrl.split('?');

var orderId = splitOrderId[0];

$(document).ready(function() {

	$('#test').submit(

	function(event) {

		var siteId = $('#siteId').val();

		var str = $('#test').serialize();

		$.ajax({

			type : "GET",

			url : "/edca-web/edca/saveTechnicalContactDetails",

			data : str,

			success : function(response) {

				window.parent.$("#orderContactMenu").trigger('click');

			},

			error : function(error) {

				alert('Error: ' + error);

			}

		});

		return false;

	});

});

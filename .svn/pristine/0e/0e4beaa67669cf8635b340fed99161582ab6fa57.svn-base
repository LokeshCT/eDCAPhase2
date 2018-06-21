function checkForm() {

	if (!(document.getElementById("firstName").value == '' || document
			.getElementById("firstName").value == 'null')) {
		var len = {
			min : 2,
			max : 200
		};
		var input = document.getElementById("firstName");
		document.getElementById("error_fn").innerHTML = "";
		if (!(input.value.length >= len.min && input.value.length <= len.max)) {
			document.getElementById("error_fn").innerHTML = "Length can't be 1 character";

			return false;

		}

	}

	if (!(document.getElementById('lastName').value == '' || document
			.getElementById('lastName').value == 'null')) {
		var len = {
			min : 2,
			max : 200
		};
		var input = document.getElementById('lastName');
		document.getElementById("error_ln").innerHTML = "";
		if (!(input.value.length >= len.min && input.value.length <= len.max)) {

			document.getElementById("error_ln").innerHTML = "Length can't be 1 character";
			return false;
		}
	}

	if (!(document.getElementById('email').value == '' || document
			.getElementById('email').value == 'null')) {

		document.getElementById("error_Email").innerHTML = "";

		var email = document.getElementById('email');
		var filter = /[\s]*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*[\s]*/;

		if (!filter.test(email.value)) {

			document.getElementById("error_Email").innerHTML = "Mail Format Wrong, Use a@b.com";

			return false;
		}
	}

	if (!(document.getElementById('phone').value == '' || document
			.getElementById('phone').value == 'null')) {

		document.getElementById("error_Phone").innerHTML = " ";

		var phone = document.getElementById('phone');
		var filter = /^[a-zA-Z0-9_ ]*$/;

		if (!filter.test(phone.value)) {

			document.getElementById("error_Phone").innerHTML = "Phone Format Wrong";

			return false;
		}

	}

	if (!(document.getElementById('orderMobilePager').value == '' || document
			.getElementById('orderMobilePager').value == 'null')) {

		document.getElementById("error_MobilePhone_Order").innerHTML = " ";

		var mphone = document.getElementById('orderMobilePager');
		var filter = /^[\d ]+$/;

		if (!filter.test(mphone.value)) {

			document.getElementById("error_MobilePhone_Order").innerHTML = "Phone Format Wrong";

			return false;
		}

	}

}

var lastPartUrl = window.location.href.split("/").pop();

var splitOrderId = lastPartUrl.split('?');

var orderId = splitOrderId[0];

$(document).ready(function() {

	$('#orderDetails').submit(

	function(event) {

		var siteId = $('#siteId').val();

		var str = $('#orderDetails').serialize();

		$.ajax({

			type : "GET",

			url : "/edca-web/edca/saveOrderContactDetails",

			data : str,

			success : function(response) {

				window.parent.$("#siteLocationMenu").trigger('click');

			},

			error : function(error) {

				alert('Error: ' + error);

			}

		});

		return false;

	});

});

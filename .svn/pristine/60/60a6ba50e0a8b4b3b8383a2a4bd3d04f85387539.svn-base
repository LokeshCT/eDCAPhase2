function validate() {

	var firstName = document.getElementById("Name").value;
	document.getElementById("error").innerHTML = "";

	if ((firstName == null) || (firstName == '')) {

		document.getElementById("error").innerHTML = "Field is Mandatory";

		return false;

	}

	var pLastName = document.getElementById("LastName").value;
	document.getElementById("error_last").innerHTML = "";

	if ((pLastName == null) || (pLastName == '')) {

		document.getElementById("error_last").innerHTML = "Field is Mandatory";

		return false;

	}

	if (!(document.getElementById('email_V').value == '' || document
			.getElementById('email_V').value == 'null')) {

		document.getElementById("error_primarymail").innerHTML = "";

		var pemail = document.getElementById('email_V');
		var filter = /[\s]*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*[\s]*/;

		if (!filter.test(pemail.value)) {

			document.getElementById("error_primarymail").innerHTML = "Mail Format Wrong, Use a@b.com";

			return true;
		}

	}

	var phone = document.getElementById("primaryPhone").value;
	document.getElementById("error_Primaryphone").innerHTML = "";

	if ((phone == null) || (phone == '')) {

		document.getElementById("error_Primaryphone").innerHTML = "Field is Mandatory";

		return false;

	}

	if (!(document.getElementById('primaryPhone').value == '' || document
			.getElementById('primaryPhone').value == 'null')) {

		document.getElementById("error_Primaryphone").innerHTML = "";

		var pphone = document.getElementById('primaryPhone');
		var filter = /^[\d ]+$/;

		if (!filter.test(pphone.value)) {

			document.getElementById("error_Primaryphone").innerHTML = "Phone Format Wrong";

			return false;
		}

	}

	if (!(document.getElementById('mobilePager').value == '' || document
			.getElementById('mobilePager').value == 'null')) {

		document.getElementById("error_mobile").innerHTML = "";

		var mobile = document.getElementById('mobilePager');
		var filter = /^[\d ]+$/;

		if (!filter.test(mobile.value)) {

			document.getElementById("error_mobile").innerHTML = "Phone Format Wrong";

			return false;
		}

	}

	if (!(document.getElementById('secondary_email').value == '' || document
			.getElementById('secondary_email').value == 'null')) {

		document.getElementById("error_secondary_mail").innerHTML = "";

		var semail = document.getElementById('secondary_email');
		var filter = /[\s]*\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*[\s]*/;

		if (!filter.test(semail.value)) {

			document.getElementById("error_secondary_mail").innerHTML = "Mail Format Wrong, Use a@b.com";

			return true;
		}

	}

	if (!(document.getElementById('secondaryPhone').value == '' || document
			.getElementById('secondaryPhone').value == 'null')) {

		document.getElementById("error_secondary_telephone").innerHTML = "";

		var sPhone = document.getElementById('secondaryPhone');
		var filter = /^[\d ]+$/;

		if (!filter.test(sPhone.value)) {

			document.getElementById("error_secondary_telephone").innerHTML = "Phone Format Wrong";

			return false;
		}

	}
	if (!(document.getElementById('secondaryMobile').value == '' || document
			.getElementById('mobilePager').value == 'null')) {

		document.getElementById("error_secondary_mobiletelephone").innerHTML = "";
	}

	var smobile = document.getElementById('secondaryMobile');
	var filter = /^[\d ]+$/;

	if (!filter.test(smobile.value)) {

		document.getElementById("error_secondary_mobiletelephone").innerHTML = "Phone Format Wrong";

		return false;
	}

	if ((smobile == null) || (smobile == '')) {

		document.getElementById("error_Primaryphone").innerHTML = "";

		return false;

	}

}

var lastPartUrl = window.location.href.split("/").pop();

var splitOrderId = lastPartUrl.split('?');

var orderId = splitOrderId[0];

$(document).ready(function() {

	$('#myForm').submit(

	function(event) {

		var siteId = $('#siteId').val();

		var str = $('#myForm').serialize();

		$.ajax({

			type : "GET",

			url : "/edca-web/edca/saveSiteContactDetails",

			data : str,

			success : function(response) {

				window.parent.$("#cpeOrderingMenu").trigger('click');

			},

			error : function(error) {

				alert('Error: ' + error);

			}

		});

		return false;

	});

});

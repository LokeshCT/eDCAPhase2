function validate() {

	var authorisationReason = document.getElementById("authorisationReason").value;
	document.getElementById("error_address1").innerHTML = "";
	document.getElementById("error_reason").innerHTML = "";
	if (document.getElementById("myCheckbox").checked) {

		if (authorisationReason.length < 1) {
			document.getElementById("error_address1").innerHTML = "Please enter Authorisation Override Reason";
			return false;

		}

	}

	else if (authorisationReason.length > 0) {
		document.getElementById("error_reason").innerHTML = "Please click on the Override Authorisation";
		return false;
	}
}
var lastPartUrl = window.location.href.split("/").pop(); 
var splitOrderId = lastPartUrl.split('?');
var orderId = splitOrderId[0];
$(document).ready(function() {
	$('#additionalInformation').submit(
		function(event) {
			var siteId = $('#siteId').val();
			
			var str = $('#additionalInformation').serialize();
				    $.ajax({	
				    url :"/edca-web/edca/saveadditional",
				    type : "GET",
				    data: str,			        
				    success : function(response) {	        
		    	    setSiteLocationMenu = window.parent.document.getElementById('siteLocationMenu');
				    window.parent.$("#siteLocationMenu").trigger('click');
				    

				},
				error : function(error) {
					
				}
			});
			return false;
		});
	});

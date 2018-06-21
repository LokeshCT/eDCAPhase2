var lastPartUrl = window.location.href.split("/").pop();
var splitOrderId = lastPartUrl.split('?');
var orderId = splitOrderId[0];
$(document).ready(function() {
	$('#InfraStructureForm').submit(function(event) {
		
		var str = $('#InfraStructureForm').serialize();
		$.ajax({
			url : "/edca-web/edca/saveCustomerDetails",
			type : "GET",
			data : str,
			success : function(response) {

				window.parent.$('#customerContactMenu').trigger('click');

			},
			error : function(error) {

			}
		});
		return false;
	});
});
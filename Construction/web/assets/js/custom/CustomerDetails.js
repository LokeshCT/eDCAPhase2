var lastPartUrl = window.location.href.split("/").pop();
var splitOrderId = lastPartUrl.split('?');
var orderId = splitOrderId[0];
$(document).ready(function() {
	$('#CustomerForm').submit(function(event) {
		
		var str = $('#CustomerForm').serialize();
		$.ajax({
			url : "/edca-web/edca/saveCustomerDetails",
			type : "GET",
			data : str,
			success : function(response) {

				window.parent.$('#infraStructureMenu').trigger('click');

			},
			error : function(error) {

			}
		});
		return false;
	});
});
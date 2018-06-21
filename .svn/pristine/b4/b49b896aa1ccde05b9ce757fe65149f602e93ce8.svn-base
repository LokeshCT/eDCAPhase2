

function validate(){
	
	var address = document.getElementById("address1").value;
	
	var city = document.getElementById("city").value;
	var spacefilter = /^\s+$/ ;
	document.getElementById("error_address1").innerHTML = ""; 
	
	if(address.length<1 || spacefilter.test(address)){
		
		document.getElementById("error_address1").innerHTML = "Field is Mandatory"; 
		
	 return false;
	
	}
	
	
	document.getElementById("error_city").innerHTML = "";
	
	if(city.length<1 || spacefilter.test(city)){
		document.getElementById("error_city").innerHTML = "Field is Mandatory";
		 return false;
	}
	
	
	var floor = document.getElementById("floor").value;
	var room = document.getElementById("room").value;
	var spacefilter2 = /^\s+$/ ;
	document.getElementById("error_floor").innerHTML = "";
	
	var filter = /[0-9,\-\,a-z,A-Z,\s,+,\\,/,(,)]/;
	if(!(filter.test(floor) || floor.length<1)){
		document.getElementById("error_floor").innerHTML = "Alphanumeric Only";	
		return false ;
	}
	document.getElementById("error_room").innerHTML = "";
	if(!(filter.test(room) || room.length<1)){
		document.getElementById("error_room").innerHTML = "Alphanumeric Only" ;
		return false ;
	
}
}

var lastPartUrl = window.location.href.split("/").pop(); 
var splitOrderId = lastPartUrl.split('?');
var orderId = splitOrderId[0];
$(document).ready(function() {
	$('#siteAddress').submit(
		function(event) {
			var siteId = $('#siteId').val();
			
			var str = $('#siteAddress').serialize();
				    $.ajax({	
				    url :"/edca-web/edca/saveSiteAddress",
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
function validate() {
	
	
	var authorisationReason = document.getElementById("authorisationReason").value;
	document.getElementById("error_address1").innerHTML = ""; 
	document.getElementById("error_reason").innerHTML = ""; 
	if (document.getElementById("myCheckbox").checked){
		
		if(authorisationReason.length<1){
			document.getElementById("error_address1").innerHTML = "Please enter Authorisation Override Reason"; 
			 return false;

		}
		
		
		
	}
	
	else if(authorisationReason.length>0){
		document.getElementById("error_reason").innerHTML = "Please click on the Override Authorisation";
		return false;
	}
}
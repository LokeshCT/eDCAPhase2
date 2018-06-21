// Joseph Added To set the select Profile Href  ...
function setHref() {
	var lastPartUrl = window.location.href.split("/").pop(); 
	setReadOrderId = document.getElementById("selectProfileId");
	if(isNaN(lastPartUrl))
		{	 
		 
			 setReadOrderId.setAttribute("href", "selectProfile");
		}
	else
		{
				setReadOrderId.setAttribute("href", "../../selectProfile");
		}

}

// onchange of status this function is invoked
function onchangeStatus() {

	var e = document.getElementById("StatusType");
	var ordType = e.options[e.selectedIndex].text;
	var base = document.getElementById("urlPath").value;

	window.location.href = base + "/searchOrder/" + ordType + "/1";

}

// loads pagination URL 

function loadPaginationUrl(pageNum) {

	var lastPartUrl = window.location.href.split("/").pop();

	if (lastPartUrl == "searchOrder")

	{
		var url = location.href + "/Unassigned/" + pageNum;
	} else {
		var url = pageNum;
	}

	window.location.href = url;
}

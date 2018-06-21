/** Side Bar Navigation   
*
*	Author : Joseph Peter
*
*	This Javascript used for Side Bar Navigation and also to append
*	the URL for menu's
*
*/

function openLinkInIframe(url) {
		document.getElementById("content_page").src= url;
	}


function loadFirstNextLink(infra,order)
{
		window.parent.$('#infraStructureMenu').trigger('click');			
}

function loadNextLink(order) {
		window.parent.$("#" + order).trigger('click');
}
	
function setSiteId(siteId) {
	var lastPartUrl = window.location.href.split("/").pop(); 
	var splitOrderId = lastPartUrl.split('?');
	var orderId = splitOrderId[0];
	setSitePageMenu = window.parent.document.getElementById('sitePageMenu');
	setSitePageMenu.setAttribute("onClick", 'openLinkInIframe("../sitepage/'+orderId +'?siteId='+siteId+'")' );		
	setSiteLocationMenu = window.parent.document.getElementById('siteLocationMenu');
	setSiteLocationMenu.setAttribute("onClick", 'openLinkInIframe("../siteLocations/'+orderId +'?siteId='+siteId+'")' );
	window.parent.$("#sitePageMenu").trigger('click');	
	
}	


	/**
	 * This method is used for setting the attribute for the side bar menu
	 * 
	 *  Set the Url for siteAddressMenu,siteContactDetailsMenu,siteLocationDetailsMenu,
	 *  serviceInstanceMenu . Appends the site Location Id to the Url	 * 
	 * 
	 * @param siteLocationId
	 */
	
	function setSiteLocationId(siteLocationId) {
		var lastPartUrl = window.location.href.split("/").pop(); 
		var splitOrderId = lastPartUrl.split('?');
		var orderId = splitOrderId[0];
		var siteId = document.getElementById('siteId').value;		
		setSiteLocationDetailsMenu = window.parent.document.getElementById('siteLocationDetailsMenu');
		setSiteLocationDetailsMenu.setAttribute("onClick", 'openLinkInIframe("../sitelocationdetails/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+ '")');
		setSiteAddressMenu = window.parent.document.getElementById('siteAddressMenu');
		setSiteAddressMenu.setAttribute("onClick", 'openLinkInIframe("../siteaddress/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+ '")' );				
		setSiteContactDetailsMenu = window.parent.document.getElementById('siteContactDetailsMenu');
		setSiteContactDetailsMenu.setAttribute("onClick", 'openLinkInIframe("../siteContactDetails/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+ '")' );
		setCpeOrderingMenu = window.parent.document.getElementById('cpeOrderingMenu');
		setCpeOrderingMenu.setAttribute("onClick", 'openLinkInIframe("../cpeOrderingDetails/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+ '")' );
		setServiceInstanceMenu = window.parent.document.getElementById('serviceInstanceMenu'); 
		setServiceInstanceMenu.setAttribute("onClick", 'openLinkInIframe("../serviceInstance/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+ '")' );
		
		/** Perform click option to navigate into next menu after clicking select option in site location Page */
		window.parent.$("#siteLocationDetailsMenu").trigger('click');
	}	
	
	
	/**
	 * This method is used for setting the attribute for the side bar menu
	 * 
	 *  Set the Url for configureAccessMenu,accessTypeMenu
	 *   Appends the site Location Id to the Url	 * 
	 * 
	 * @param serviceInstanceId
	 */
	function  setSiteServiceInstanceId(serviceInstanceId,serviceInstance)
	{
		var lastPartUrl = window.location.href.split("/").pop(); 
		var splitOrderId = lastPartUrl.split('?');
		var orderId = splitOrderId[0];
		var siteId = document.getElementById('siteId').value;
		var siteLocationId = document.getElementById('siteLocationId').value;

		setConfigureAccessMenu = window.parent.document.getElementById('configureAccessMenu');
		setConfigureAccessMenu.setAttribute("onClick", 'openLinkInIframe("../configureAccess/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setAccessTypeMenu = window.parent.document.getElementById('accessTypeMenu');
		setAccessTypeMenu.setAttribute("onClick", 'openLinkInIframe("../configureAccessType/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setConfigureCPEMenu = window.parent.document.getElementById('configureCPEMenu');
		setConfigureCPEMenu.setAttribute("onClick", 'openLinkInIframe("../cpeConfigure/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setConfigureWICMenu = window.parent.document.getElementById('configureWICMenu');
		setConfigureWICMenu.setAttribute("onClick", 'openLinkInIframe("../configureWicnmpa/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');


		setConfigureLanSwitchMenu = window.parent.document.getElementById('configureLanSwitchMenu');
		setConfigureLanSwitchMenu.setAttribute("onClick", 'openLinkInIframe("../configureLANNetScreen/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setConfigureLANMenu = window.parent.document.getElementById('configureLANMenu');
		setConfigureLANMenu.setAttribute("onClick", 'openLinkInIframe("../configurelancards/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setConfigureOtherMenu = window.parent.document.getElementById('configureOtherMenu');
		setConfigureOtherMenu.setAttribute("onClick", 'openLinkInIframe("../configureOtherDevice/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');		

		setConfigureCardsMenu = window.parent.document.getElementById('configureCardsMenu');
		setConfigureCardsMenu.setAttribute("onClick", 'openLinkInIframe("../configureCableCards/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setVpnPageMenu = window.parent.document.getElementById('vpnPageMenu');
		setVpnPageMenu.setAttribute("onClick", 'openLinkInIframe("../vpnPage/'+orderId +'?siteId='+siteId+'&siteLocationId='+siteLocationId+'&serviceInstanceId='+serviceInstanceId+'&serviceInstance='+serviceInstance+ '")');

		setConfigureVlanMenu = window.parent.document.getElementById('configureVlanMenu');
		setConfigureVlanMenu.setAttribute("onClick", 'openLinkInIframe("../configurevlan/'+orderId +'?serviceInstanceId='+serviceInstanceId+ '")');

		
		/** Perform click option to navigate into next menu after clicking select option in Configure Access Page */
		window.parent.$("#configureAccessMenu").trigger('click');		
	}
	/**
	 * This method is used for setting the attribute for the side bar menu
	 * 
	 *  Set the Url for VPn Pages
	 *   Appends the VPN Id to the Url	 * 
	 * 
	 * @param vpnId
	 */
	function  setVpnId(vpnId)
	{
		var lastPartUrl = window.location.href.split("/").pop(); 
		var splitOrderId = lastPartUrl.split('?');
		var orderId = splitOrderId[0];		

		setVpnConnectionMenu = window.parent.document.getElementById('vpnConnectionMenu');
		setVpnConnectionMenu.setAttribute("onClick", 'openLinkInIframe("../vpnConnection/'+orderId +'?vpnId='+vpnId +'")');

		setDscpCoSMenu = window.parent.document.getElementById('dscpCoSMenu');
		setDscpCoSMenu.setAttribute("onClick", 'openLinkInIframe("../dscpcosvalues/'+orderId +'?vpnId='+vpnId +'")');

		setVpnServicesMenu = window.parent.document.getElementById('vpnServicesMenu');
		setVpnServicesMenu.setAttribute("onClick", 'openLinkInIframe("../vpnServices/'+orderId +'?vpnId='+vpnId +'")');
		/** Perform click option to navigate into next menu after clicking select option in Vpn connection Page */
		window.parent.$("#vpnConnectionMenu").trigger('click');		
	}
/**
	 * This method is used for setting the attribute for the side bar menu
	 * 
	 *  Set the Url for VPn Service Page
	 *   Appends the Vpn service Id to the Url	 * 
	 * 
	 * @param Vpn service Idd
	 */

	function  setVpnServiceId(vpnServiceId)
	{		
		var lastPartUrl = window.location.href.split("/").pop(); 
		var splitOrderId = lastPartUrl.split('?');
		var orderId = splitOrderId[0];

		setServiceMenu = window.parent.document.getElementById('serviceMenu');
		setServiceMenu.setAttribute("onClick", 'openLinkInIframe("../vpnService/'+orderId +'?vpnServiceId='+vpnServiceId +'")');

		/** Perform click option to navigate into next menu after clicking select option in Service Instance Page */
		window.parent.$("#serviceMenu").trigger('click');		
	}

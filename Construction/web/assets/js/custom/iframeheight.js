

/**  This script is used for height adjustments in Iframe **/

function getDocHeight(doc) {
    doc = doc || document;
    var body = doc.body, html = doc.documentElement;
    var height = Math.max( body.scrollHeight, body.offsetHeight, 
        html.clientHeight, html.scrollHeight, html.offsetHeight );
    return height;
}

/** Get the Iframe ID and  calculate the total height of the iframe and content
 * and set the height for that id as calculated height . This will prevent page
 * from Scrolling.
 * @param id
 */

function setIframeHeight(id) {
    var ifrm = document.getElementById(id);
    var doc = ifrm.contentDocument? ifrm.contentDocument: 
        ifrm.contentWindow.document;
    ifrm.style.visibility = 'hidden';
    ifrm.style.height = "10px"; 
    ifrm.style.height = getDocHeight( doc ) + 4 + "px";
    ifrm.style.visibility = 'visible';
}

/** This document ready function is include the <div class="sideBar-demo
 * 
 * 
 */

$(document).ready(function() {
  var sideBarTree = $('ul.sideBarTree'); 
  sideBarTree.wrap('<div class=sideBarTree-demo></div>'); 
});
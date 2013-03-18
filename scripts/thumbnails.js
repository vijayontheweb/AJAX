window.onload =	initPage;

function initPage()	{	// find	the	thumbnails on the page	 
	thumbs	= 	document.getElementById("thumbnailPane").getElementsByTagName("img");
	// set the handler for each	image
	for	(var i = 0;	i <	thumbs.length; i++)	{
		image =	thumbs[i];
		// create the onclick function 
		image.onclick = function() {
			// find	the	image name 
			alert("Inside function()");
			detailURL = 'images/'+this.title+'-detail.jpg';
			document.getElementById("itemDetail").src = detailURL; 
			getDetails(this.title);
		}
	}
}

function getDetails(itemName) 
{
	alert("Inside getDetails()");
	request	= createRequest();
	if (request	== null) {
		alert("Unable to create	request");
		return;
	}
	var url= "http://localhost:1234?value="+escape(itemName);
	alert("url="+url);
	request.open("GET",	url, true);
	request.onreadystatechange = displayDetails;
	request.send(null);
}


function createRequest() {
  try {
	request	= new XMLHttpRequest();
  }	catch (tryMS) {
	try	{
	  request =	new	ActiveXObject("Msxml2.XMLHTTP");
	} catch	(otherMS) {
	  try {
		request	= new ActiveXObject("Microsoft.XMLHTTP");
	  }	catch (failed) {
		request	= null;
	  }
	}
  }
  return request;
}



function displayDetails() {
	alert("Inside displayDetails() with request.readyState = "+request.readyState);
	if (request.readyState == 4) {
		alert("request.readyState == 4. Means Server has finished sending data to the client");
		if (request.status == 200) {
			alert("request.status == 200. Means data transfer successful");
			detailDiv =	document.getElementById("description");
			alert("The Data transferred is -> request.responseText = "+request.responseText);
			detailDiv.innerHTML = request.responseText;
		}
	}
}
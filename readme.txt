This application illustrates backbone of how Ajax works. A simple webserver is available in this bundle, that returns some text based on the input passed. 

AJAX execution Steps:
1. Run the webserver. It is configured to run on port 1234
2. Open inventory.html
3. Click on an image to the left of the page
4. The Dialog Box(defined in thumbnails.js) will guide you through the steps on how the request is transmitted and returned back from the server.

Traditional execution Steps:
1. In Thumbnails.js, change the third argument value of "request.open("GET",url,true);" to false. This means that the request is now "Synchronous"
2. Repeat the previous execution steps. By setting a breakpoint on the webserver, You will notice that the webpage is frozen until the server has responded, unlike AJAX.
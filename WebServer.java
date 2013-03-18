package ajax;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class WebServer {

  /**
   * WebServer constructor.
   */
  protected void start() {
	  Map map = new HashMap();

	  map.put("itemGuitar","<p>Pete Townshend once played this guitar while his own axe was in the shop having bits of drumkit removed from it.</p>");
	  map.put("itemShades","<p>Yoko Ono's sunglasses. While perhaps not valued much by Beatles fans, this pair is rumored to have been licked by John Lennon.</p>");
	  map.put("itemCowbell","<p>Remember the famous \"more cowbell\" skit from Saturday Night Live? Well, this is the actual cowbell.</p>");
	  map.put("itemHat","<p>Michael Jackson's hat, as worn in the \"Billie Jean\" video. Not really rock memorabilia, but it smells better than Slash's tophat.</p>");	  
	  
    ServerSocket s;

    System.out.println("Webserver starting up on port 1234");
    try {
      // create the main server socket
      s = new ServerSocket(1234);
    } catch (Exception e) {
      System.out.println("Error: " + e);
      return;
    }

    System.out.println("Waiting for connection");
    for (;;) {
      try {
        // wait for a connection
        Socket remote = s.accept();
        // remote is now the connected socket
        System.out.println("Connection, sending data.");
        BufferedReader in = new BufferedReader(new InputStreamReader(
            remote.getInputStream()));
        PrintWriter out = new PrintWriter(remote.getOutputStream());
        String str = in.readLine();
        String sub = str.substring(str.indexOf("value=")+6, str.length()-9);
        out.println("HTTP/1.0 200 OK");
        out.println("Content-Type: text/html");
        out.println("Server: Bot");
        // this blank line signals the end of the headers
        out.println("");
        // Send the HTML page
        out.println("<H1>"+map.get(sub)+"</H2>");
        out.flush();        
        remote.close();
      } catch (Exception e) {
        System.out.println("Error: " + e);
      }
    }
  }

  /**
   * Start the application.
   * 
   * @param args
   *            Command line parameters are not used.
   */
  public static void main(String args[]) {
    WebServer ws = new WebServer();
    ws.start();
  }
}
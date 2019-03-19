import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcpEcho{
	private String hostname;
	private int port;
	
public ClientTcpEcho(String host,int port) {
	this.hostname = host;
	this.port = port;
}
  public static void main(String[] args) throws IOException {
	  
	  
	 new ClientTcpEcho("0.0.0.0",5544).lancerBW();
	  
  }
  
  public void lancerBW() throws IOException{
	  
	  
	  String line;
	  String message;
	  Scanner sc = new Scanner(System.in);
	  
	  
	  Socket sockClient = new Socket();
	  sockClient.connect(new InetSocketAddress(hostname,port));
	  BufferedReader br = new BufferedReader(new InputStreamReader(
			  sockClient.getInputStream()));
	  BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
			  sockClient.getOutputStream()));
	 
	  System.out.println("Connect to the server "+sockClient.getInetAddress());
	  while(true) {
		  
		  System.out.println("Message à envoyer au serveur :");
		  message = sc.nextLine();
		  if(message.equals("quit"))
			  break;
		  out.write(message);
		  out.newLine();
		  out.flush(); 
		  if((line = br.readLine()) == null)
			  break;
		  System.out.println(line);
		  
		  
	  }
	  System.out.println("Fermeture de la connection.");
	 sockClient.close();
	  
	  
  
  }
}

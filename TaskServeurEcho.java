import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TaskServeurEcho implements Runnable {
	Socket client;

	public TaskServeurEcho(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
		System.out.println("Le client "+client.getInetAddress() + ":"+ client.getPort()+ " vient de se connecter.");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
		client.getOutputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String msgrecv;
		while((msgrecv = in.readLine()) !=  null) {
			
			out.write(msgrecv.toUpperCase());
			out.newLine();
			out.flush();
			
		}
		System.out.println("Le client"+client.getInetAddress() +":"+ client.getLocalPort()  + " se déconnecte.");
		in.close();
		out.close();
		client.close();
	}catch(IOException e) {
		
		e.printStackTrace();
	}
}
	
}

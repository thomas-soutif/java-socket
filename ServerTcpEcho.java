import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTcpEcho {

	public static void main(String[] arg) throws IOException {
		
		Socket client; 
		int nbThreads = 100;
		int nbClients = 100;
		ExecutorService pool = Executors.newFixedThreadPool(nbThreads);
		ServerSocket serveur = new ServerSocket(5544);
		System.out.println("Serveur lancé sur " + serveur.getInetAddress() +':'+serveur.getLocalPort());
		
		
		for (int i = 1; i <= nbClients; i++) {
		client = serveur.accept();
		TaskServeurEcho task = new TaskServeurEcho(client);
		pool.execute(task);
		
		
		
		
		}
		serveur.close(); 
	}
}

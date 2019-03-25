import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientPop3 {

    private String serveurSmtp;
    private int port;

    public ClientPop3(String serveurSmtp, int port){

        this.serveurSmtp = serveurSmtp;
        this.port = port;


    }

    public static void main(String[] args) throws IOException {

        new ClientPop3("139.124.187.23",110).recvEmail(0,"soutif","soutif");


    }
    public void recvEmail(int number,String user, String mdp){

        Socket sockCl = new Socket();
        sockCl.connect(new InetSocketAddress(serveurSmtp,port));
        BufferedReader in = new BufferedReader(new InputStreamReader(
                sockCl.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                sockCl.getOutputStream()));

        System.out.println("Connect to the server "+sockCl.getInetAddress());




    }

}

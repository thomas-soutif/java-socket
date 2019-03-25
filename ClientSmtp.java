import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSmtp {

    private String serveurSmtp;
    private int port;
    private String hostname;
    public ClientSmtp(String serveurSmtp, int port, String hostname){

        this.serveurSmtp = serveurSmtp;
        this.port = port;
        this.hostname =  hostname;



    }

    public static void main(String[] args) throws IOException {

        new ClientSmtp("139.124.187.23",25,"D").sendMail("Dio","miceli","ceci est un spam","DÉSO (: P:S : ceci est un message envoyé automatiquement, ca sert a rien de répondre ");


    }

    public boolean sendMail(String from, String to, String subject, String body) throws IOException{

        Socket sockCl = new Socket();
        sockCl.connect(new InetSocketAddress(serveurSmtp,port));
        BufferedReader in = new BufferedReader(new InputStreamReader(
                sockCl.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                sockCl.getOutputStream()));

        System.out.println("Connect to the server "+sockCl.getInetAddress());



        readFromServer(in);
        System.out.println("EHLO "+hostname);
        writeToServer(out,"EHLO "+hostname+".");

        readFromServer(in);
        System.out.println("MAIL FROM: "+from);
        writeToServer(out,"MAIL FROM: "+from);

        readFromServer(in);

        System.out.println("RCPT TO: "+to);
        writeToServer(out,"RCPT TO: "+to);
        readFromServer(in);

        System.out.println("DATA");
        writeToServer(out,"DATA");



        readFromServer(in);

        System.out.println("FROM: "+from+"@jojo");
        System.out.println("TO: "+to);
        System.out.println("SUBJECT: "+subject);
        System.out.println(body);
        writeToServer(out,"FROM: "+from + "\n"+"TO: "+to + "\n"+"SUBJECT: "+subject +"\n"+body + "\n" + '.');

        readFromServer(in);
        writeToServer(out,"QUIT");
        readFromServer(in);
        System.out.println("Fermeture de la connection.");
        sockCl.close();
        return true;
    }

    private boolean codeSmtpIsOk(String message){

        for(int i=0; i <9; i++){

            if(message.charAt(3) == i)
                return false;
        }
        return true;
    }

    private void showError(String errorMsg){

        System.out.println("Erreur :" + errorMsg);
        System.exit(1);
    }
    private boolean codeSmtpIsEnd(String message){

        if(message.charAt(3) == ' ')
            return true;
        else
            return false;
    }

    private void readFromServer(BufferedReader in) throws IOException{

        String msg;
        while(( msg = in.readLine()) !=  null) {

            System.out.println(msg);
            if(!codeSmtpIsOk(msg))
                showError("Code error from SMTP");
            if(codeSmtpIsEnd(msg))
                break;
        }



    }
    private void writeToServer(BufferedWriter out, String msg) throws IOException{

        out.write(msg);
        out.newLine();
        out.flush();




    }
}

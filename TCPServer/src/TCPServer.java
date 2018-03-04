import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pascal Fares
 */
public class TCPServer {

    private static BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private static PrintWriter getoutput(Socket p) throws IOException {
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Le serveur:");
 
        ServerSocket l = new ServerSocket(2000);
        System.out.println(l.getLocalSocketAddress());
 
        while (true) {
            //try-with-resource
            try (Socket serviceSocket = l.accept()) {
                System.out.println(serviceSocket.getRemoteSocketAddress());
                BufferedReader ir = getInput(serviceSocket);
                PrintWriter reply = getoutput(serviceSocket);
                String line;
                //System.out.println("Avant boucle");
                while ((line = ir.readLine()) != null) {
                    //System.out.println("Dans boucle");
                    System.out.printf("Le client dit: %s\n", line);
                    System.out.printf("je répond: %s le client\n", line);
                    reply.printf("%s le client\n", line);
                    reply.flush();
                }
            }
        }
    }

}
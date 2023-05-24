import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket client) {
        this.clientSocket =client;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {

                System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

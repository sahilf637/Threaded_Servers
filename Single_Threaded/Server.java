import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    Server server = new Server();

    try {
      server.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() throws IOException {
    int PORT = 8010;
    ServerSocket socket = new ServerSocket(PORT);

    socket.setSoTimeout(10000);

    System.out.println("SERVER IS LISTENING ON PORT 8010");
    while (true) {
      try {
        Socket acceptedConnection = socket.accept();
        System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());

        PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

        toClient.println("Hello from the server");

        toClient.close();
        fromClient.close();
        acceptedConnection.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

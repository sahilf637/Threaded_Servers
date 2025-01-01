import java.io.*;
import java.net.*;

public class Client {

  public void run() throws UnknownHostException, IOException {
    int port = 8010;
    InetAddress address = InetAddress.getByName("localhost");

    Socket socket = new Socket(address, port);

    PrintWriter toServer = new PrintWriter(socket.getOutputStream());
    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    toServer.println("Hello from client");
    String line = fromServer.readLine();

    System.out.println("Server says:- " + line);

    toServer.close();
    fromServer.close();
    socket.close();
  }

  public static void main(String[] args) {
    try {
      Client client = new Client();
      client.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

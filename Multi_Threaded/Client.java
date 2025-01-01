import java.io.*;
import java.net.*;

public class Client {

  public Runnable getRunnable() {
    return new Runnable() {
      @Override
      public void run() {
        int port = 8010;
        try {
          InetAddress address = InetAddress.getByName("localhost");
          Socket socket = new Socket(address, port);

          PrintWriter toServer = new PrintWriter(socket.getOutputStream());
          BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          toServer.println("Hello from client " + socket.getLocalSocketAddress());
          String line = fromServer.readLine();
          System.out.println("Server Says :- " + line);

          toServer.close();
          fromServer.close();
          socket.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
  }

  public static void main(String[] args) {
    Client client = new Client();

    for (int i = 0; i < 100; i++) {
      try {
        Thread thread = new Thread(client.getRunnable());
        thread.start();
      } catch (Exception e) {
        return;
      }
    }
  }

}

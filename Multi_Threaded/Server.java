
import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class Server {

  public Consumer<Socket> getConsumer() {
    return (clientSocket) -> {
      try {
        PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        toClient.println("Hello From Server");
        toClient.close();
        clientSocket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
  }

  public static void main(String[] args) {
    Server server = new Server();
    int PORT = 8010;
    try {
      ServerSocket serverSocket = new ServerSocket(PORT);

      serverSocket.setSoTimeout(10000);
      System.out.println("Server started on port " + PORT);

      while (true) {
        Socket acceptedSocket = serverSocket.accept();

        System.out.println(acceptedSocket.getLocalSocketAddress());
        Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
        thread.start();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

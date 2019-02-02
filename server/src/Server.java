import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private int port;
    private ServerSocket server;
    private List<User> users;

    public Server(int port) {
        this.port = port;
        this.users = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        new Server(5000).run();
    }

    public void run() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Connnection established with port " + port);

        while (true) {
            Socket userSocket = server.accept();
            System.out.println("Connection accepted from " + userSocket);

            Scanner scanner = new Scanner(userSocket.getInputStream());
            System.out.println("Welcome in chat\nChoose nickname:");

            String nickname = scanner.nextLine();

            User user = new User(userSocket, nickname);
            users.add(user);

            Thread t = new Thread(new UserHandler(user, this));
            t.start();
        }
    }

    protected void sendToAll(String message, User sender) {

    }

    protected void sendToPrivate(String message, User sender, String Receiver) {

    }

}

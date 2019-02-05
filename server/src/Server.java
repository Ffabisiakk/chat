import java.io.IOException;
import java.io.PrintStream;
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

    private void run() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Connnection established with port " + port);

        while (true) {
            Socket userSocket = server.accept();
            System.out.println("Connection accepted from " + userSocket);

            Scanner scanner = new Scanner(userSocket.getInputStream());

            String nickname = scanner.nextLine();

            User user = new User(userSocket, nickname);
            users.add(user);

            Thread t = new Thread(new UserHandler(user, this));
            t.start();
        }
    }

    void sendToAll(String message, User sender) {
        for (User u : users) {
            if (u != sender) {
                PrintStream out = u.getOutStream();
                out.println(sender.toString() + " >> " + message);
            }
        }
        System.out.println("Log: " + sender.getNickname() + " >> ALL : " + message);
    }

    void sendToAll(String message) {
        for (User u : users) {
            PrintStream out = u.getOutStream();
            out.println(message);
            System.out.println("Log: " + message);
        }
    }


    void sendToPrivate(String message, User sender, String receiver) {

        boolean validReceiver = false;
        for (User u : users) {
            if (u.getNickname().equals(receiver)) {
                validReceiver = true;
                u.getOutStream().println(sender.toString() + " -> " + receiver + " >> " + message);
                System.out.println("Log: " + sender.getNickname() + " >> " + receiver + " : " + message);
                break;
            }
        }
        if (!validReceiver) {
            sender.getOutStream().println("There is no such user: " + receiver);
        }
    }


    public List<User> getUsers() {
        return users;
    }
}

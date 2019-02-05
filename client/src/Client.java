import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        new Client("127.0.0.1", 5000).run();
    }

    private void run() throws IOException {
        Socket clientSocket = new Socket(ip, port);
        System.out.println("Connected");

        PrintStream output = new PrintStream(clientSocket.getOutputStream());
        InputStream inputServer = clientSocket.getInputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose nickname:");
        String nickname = scanner.nextLine();
        output.println(nickname);
        System.out.println("Welcome " + nickname);

        Thread t = new Thread(new ServerHandler(inputServer));
        t.start();

        while (scanner.hasNextLine()){
            output.println(scanner.nextLine());
        }
    }
}



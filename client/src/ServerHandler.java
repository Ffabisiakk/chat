import java.io.InputStream;
import java.util.Scanner;

public class ServerHandler implements Runnable {

    private InputStream inputServer;

    public ServerHandler(InputStream inputServer) {
        this.inputServer = inputServer;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(inputServer);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}

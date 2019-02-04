import java.util.Scanner;

public class UserHandler implements Runnable {

    private User user;
    private Server server;

    public UserHandler(User user, Server server) {
        this.user = user;
        this.server = server;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(user.getInputStream());
        String message;

        while (scanner.hasNextLine()) {
            message = scanner.nextLine();

            if (message.charAt(0) == '@') {
                if (message.contains(" ")) {
                    int firstSpaceOccure = message.indexOf(" ");
                    String receiver = message.substring(1, firstSpaceOccure);
                    server.sendToPrivate(message.substring(firstSpaceOccure + 1), user, receiver);
                }
            } else {
                server.sendToAll(message, user);
            }
        }
    }
}

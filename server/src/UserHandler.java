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
                    int firstSpaceOccur = message.indexOf(" ");
                    String receiver = message.substring(1, firstSpaceOccur);
                    server.sendToPrivate(message.substring(firstSpaceOccur + 1), user, receiver);
                }
            } else if (message.equalsIgnoreCase("!quit")) {
                server.getUsers().remove(user);
                server.sendToAll(user.getNickname() + " has logout.");
            } else {
                server.sendToAll(message, user);
            }
        }
    }
}

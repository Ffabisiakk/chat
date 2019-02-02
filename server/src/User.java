import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

class User {
    private static int nbUser = 0;
    private int userId;
    private PrintStream streamOut;
    private InputStream streamIn;
    private String nickname;
    private Socket userSocket;

    public User(Socket userSocket, String name) throws IOException {
        this.streamOut = new PrintStream(userSocket.getOutputStream());
        this.streamIn = userSocket.getInputStream();
        this.userSocket = userSocket;
        this.nickname = name;
        this.userId = nbUser;
        nbUser += 1;
    }

    public PrintStream getOutStream() {
        return this.streamOut;
    }

    public InputStream getInputStream() {
        return this.streamIn;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String toString() {
        return getNickname();
    }
}
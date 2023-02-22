import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.start(8082);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
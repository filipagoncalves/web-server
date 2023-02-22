import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService service;

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.service = Executors.newCachedThreadPool();

        //System.out.printf(Messages.SERVER_STARTED, port);

    }

    public void acceptConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();
        //PlayerHandler playerHandler = new PlayerHandler(clientSocket);
        //service.submit(playerHandler);
    }


}

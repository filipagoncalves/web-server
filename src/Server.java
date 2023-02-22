import com.sun.tools.javac.Main;
import utils.HttpHeaderBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService service;

    public static void main(String[] args) {
        int port = 8080;
        if(System.getenv("PORT") != null){
            port = Integer.parseInt(System.getenv("PORT"));
        }

        try {
            new Server().start(port);
        } catch (IOException e) {
            System.err.println(Messages.SERVER_ERROR);
            e.printStackTrace();
        }
    }

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.service = Executors.newCachedThreadPool();

        acceptConnection(serverSocket, service);

        //System.out.printf(Messages.SERVER_STARTED, port);

    }

    public void acceptConnection(ServerSocket serverSocket, ExecutorService service) throws IOException {
        Socket clientSocket = serverSocket.accept();
        RequestHandler requestHandler = new RequestHandler(clientSocket);
        service.submit(requestHandler);
    }

    public static class RequestHandler implements Runnable{

        private BufferedReader in;
        private BufferedWriter out;

        public RequestHandler(Socket clientSocket) throws IOException {
            try {
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            } catch (IOException e){
                clientSocket.close();
            }
        }

        @Override
        public void run() {
            try {
                String terminalIn = in.readLine();
                String [] terminalContent = terminalIn.replace("/" , "").split(" ");
                System.out.println(terminalContent);
                if (!terminalContent[0].equals("GET")){
                    HttpHeaderBuilder.badRequest();
                }
                File directoryPath = new File("src/www");
                List<String> fileList = Arrays.asList(directoryPath.list());
                fileList.contains(terminalContent[1]);


               // if (fileList.contains(terminalContent[1]))


                //terminalContent[1].contains();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            /*GET /teste.html HTTP/1.1

            GET / HTTP/1.1
            GET /index.html HTTP/1.1
            GET /cry-me-a-river.gif HTTP/1.1*/

        }
    }
}

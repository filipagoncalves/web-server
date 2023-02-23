/*
import com.sun.tools.javac.Main;
import utils.HttpHeaderBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }

        try {

            Server server = new Server();
            server.start(port);
        } catch (IOException e) {
            System.err.println(Messages.SERVER_ERROR);
            e.printStackTrace();
        }
    }

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.service = Executors.newCachedThreadPool();

        while (true) {
            acceptConnection(serverSocket, service);
        }

        //System.out.printf(Messages.SERVER_STARTED, port);

    }

    public void acceptConnection(ServerSocket serverSocket, ExecutorService service) throws IOException {
        Socket clientSocket = serverSocket.accept();
        RequestHandler requestHandler = new RequestHandler(clientSocket);
        service.submit(requestHandler);
    }

    public static class RequestHandler implements Runnable {

        private BufferedReader in;
        private OutputStream out;

        public RequestHandler(Socket clientSocket) throws IOException {
            try {
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.out = clientSocket.getOutputStream();
            } catch (IOException e) {
                clientSocket.close();
            }
        }

        public void send(String message) throws IOException {
            out.write(message.getBytes());
            //out.newLine();
            out.flush();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("step0");
                    String terminalIn = in.readLine();
                    String[] terminalContent = terminalIn.replace("/", "").split(" ");
                    System.out.println("step1");
                    System.out.println(Arrays.toString(terminalContent));
                    if (!terminalContent[0].equals("GET")) {
                        send(HttpHeaderBuilder.badRequest());
                        System.out.println("step2");
                    }
                    File directoryPath = new File("src/www");
                    List<String> fileList = Arrays.asList(directoryPath.list());
                    System.out.println("step3");

                    //PATH NOT FOUND -> 404
                    if (!fileList.contains(terminalContent[1]) || !terminalContent[2].equals("HTTP1.1")) {
                        //SEND 404 PAGE
                        System.out.println("3.1");
                        Path filePath = Paths.get("src/www/404.html");
                        File selectedFile = new File(filePath.toUri());
                        String fileContent = Files.readString(filePath);
                        send(fileContent);

                        //HEADER
                        send(HttpHeaderBuilder.notFound(terminalContent[1], selectedFile.length()));

                        System.out.println("step4");

                        //PATH FOUND -> FILE
                    } else {
                        Path filePath = Paths.get("src/www/" + terminalContent[1]);
                        File selectedFile = new File(filePath.toUri());

                        if (terminalContent[1].endsWith(".html")) {
                            // send HTML page
                            String fileContent = Files.readString(filePath);
                            send(HttpHeaderBuilder.ok(terminalContent[1], selectedFile.length()));
                            send(fileContent);

                            // send image
                            Path imagePath = Paths.get("src/www/welcome.gif");
                            File imageFile = new File(imagePath.toUri());
                            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imageFile));
                            byte[] buffer = new byte[1024];
                            int count;
                            while ((count = bis.read(buffer)) != -1) {
                                out.write(buffer, 0, count);
                            }
                            out.flush();
                        } else {
                                // send other files
                                send(HttpHeaderBuilder.ok(terminalContent[1], selectedFile.length()));
                                byte[] fileBytes = Files.readAllBytes(filePath);
                                out.write(fileBytes);
                                out.flush();
                            }
                    }

                    System.out.println("step7");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}


 */

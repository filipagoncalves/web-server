package utils;

public class HttpHeaderBuilder {

    public static String notFound(String fileName, long length) {
        return "HTTP/1.0 404 Not Found\r\n" +
                contentType(fileName) +
                "Content-Length: " + length + "\r\n\r\n";
    }

    public static String ok(String fileName, long length) {
        return "HTTP/1.0 200 Document Follows\r\n" +
                contentType(fileName) +
                "Content-Length: " + length + "\r\n\r\n";
    }

    public static String contentType(String fileName){


        return "a";
    }
}

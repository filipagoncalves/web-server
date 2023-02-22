package utils;

public class HttpHeaderBuilder {

    public static String notFound (String fileName, long length)
    {
        return "HTTP/1.0 404 Not Found\r\n" +
                contentType(fileName) +
                "Content-Length: " + length + "\r\n\r\n";
    }

    public static String ok (String fileName, long length)
    {
        return "HTTP/1.0 200 Document Follows\r\n" +
                contentType(fileName) +
                "Content-Length: " + length + "\r\n\r\n";
    }

    public static String contentType(String fileName)
    {
        return "a";
    }

    public static String badRequest (String fileName, long length){
        return "HTTP/1.0 400 Bad Request\r\n" +
                contentType(fileName) + length + "Bad Request: " + length + "\r\n\r\n";
    }

    public static String internalError (String fileName, long length){
        return "HTTP/1.0 500 Internal Server Error\r\n" + "Internal Server Error: " + length + "\r\n\r\n";
    }

    public static String temporaryRedirect (String fileName, long length){
        return "HTTP/1.0 307 Temporary Redirect \r\n" + "Temporary Redirect: " + length + "\r\n\r\n";
    }
    public static String movedPermanently (String fileName, long length){
        return "HTTP/1.0 301 Moved Permanently\r\n" + "Moved Permanently: " + length + "\r\n\r\n";
    }
}

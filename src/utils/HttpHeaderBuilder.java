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

    public static String contentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        switch (extension) {
            case ".html":
                return "Content-Type: text/html\r\n";
            case ".mp3":
                return "Content-Type: audio/mpeg\r\n";
            case ".gif":
                return "Content-Type: image/gif\r\n";
            case ".jpeg":
            case "jpg":
                return "Content-Type: image/jpeg\r\n";
            case  ".txt":
                return "Content-Type: text/plain\r\n";
            default:
                return "Content-Type: application/octet-stream\r\n";
        }
    }
}

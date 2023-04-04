import java.io.* ;
import java.net.* ;
import java.util.* ;
public final class WebServer
{
    private static ServerSocket socket;

    public static void main(String argv[]) throws Exception
    {
        // Set the port number.
        int port = 26950;
        
        // Establish the listen socket.
        socket = new ServerSocket(port);

        // Process HTTP service requests in an infinite loop.
        while (true) 
        {
            // Listen for a TCP connection request.
            Socket client = socket.accept();

            // Construct an object to process the HTTP request message.
            HttpRequest request = new HttpRequest(client);
            // Create a new thread to process the request.
            Thread thread = new Thread(request);
            // Start the thread.
            thread.start();
        }
    }
}

final class HttpRequest implements Runnable
{
    final static String CRLF = "\r\n";
    Socket socket;
    // Constructor
    public HttpRequest(Socket socket) throws Exception
    {
        this.socket = socket;
    }
    // Implement the run() method of the Runnable interface.
    public void run()
    {
        try 
        {
            processRequest();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    private void processRequest() throws Exception
    {
        // Get a reference to the socket's input and output streams.

    }
}
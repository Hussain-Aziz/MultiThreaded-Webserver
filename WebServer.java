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
        InputStream is = socket.getInputStream();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        // Set up input stream filters.
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // Get the request line of the HTTP request message.
        String requestLine = br.readLine();
        // Display the request line.
        System.out.println();
        System.out.println(requestLine);

        // Get and display the header lines.
        String headerLine = null;
        while ((headerLine = br.readLine()).length() != 0) {
        System.out.println(headerLine);
        }

        // Close streams and socket.
        os.close();
        br.close();
        socket.close();
    }
}
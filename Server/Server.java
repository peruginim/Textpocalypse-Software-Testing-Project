import java.net.*; 
import java.io.*; 

public class Server extends Thread
{ 
	public static List[] userList;
	public static index;
 	protected Socket clientSocket;

 	public static void main(String[] args) throws IOException 
  	{ 
	index = 0;
	userList = new List[100];
    	ServerSocket serverSocket = null; 

   		try { 
        	serverSocket = new ServerSocket(4444); 
       		System.out.println ("Connection Socket Created");
         	try { 
            	while (true)
                {
                	System.out.println ("Waiting for Connection");
                  	new Server (serverSocket.accept()); 
                }
             } 
        	 catch (IOException e) 
        	 { 
        	 	System.err.println("Accept failed."); 
            	System.exit(1); 
        	} 
        } 
    	catch (IOException e) 
        { 
        	System.err.println("Could not listen on port: 4444."); 
         	System.exit(1); 
        } 
    	finally
        {
        	try {
         	   serverSocket.close(); 
            }
         	catch (IOException e)
            { 
            	System.err.println("Could not close port: 4444."); 
              	System.exit(1); 
            } 
        }
	}

 	private Server (Socket clientSoc)
   	{
    	clientSocket = clientSoc;
    	start();
   	}

 	public void run()
   	{
    	System.out.println ("New Communication Thread Started");

    	try { 
       	 	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
        	BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

        	String inputLine; 

        	while ((inputLine = in.readLine()) != null) 
        	{ 
 	       		System.out.println ("Server: " + inputLine); 
           		out.println(inputLine); 

           		if (inputLine.equals("Bye.")) break; 
        	} 

        	out.close(); 
        	in.close(); 
        	clientSocket.close(); 
        } 
   	 	catch (IOException e) 
        { 
         	System.err.println("Problem with Communication Server");
         	System.exit(1); 
        } 
    }
} 

class List
{
	String user;
	String password;
	String location;
	//Stats
	boolean loggedIn;
}



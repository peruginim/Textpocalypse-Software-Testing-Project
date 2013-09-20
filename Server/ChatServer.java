import java.net.*; 
import java.io.*;
import java.util.*;

public class ChatServer extends Thread
{ 
 	protected Socket clientSocket;
	public static Socket[] clientArray;
	public static int clientIndex;
	public static String buffer;


	public void sendToAll() throws Exception
	{
		for(int i = 0; i < clientIndex; i++)
		{
			PrintWriter out = new PrintWriter(clientArray[i].getOutputStream(), true);
			out.println(buffer + Character.toString((char)0));
		}
	}

	public void removeFromList(Socket client)
	{
		for(int i = 0; i < clientIndex; i++)
		{
			if(clientArray[i] == client)
			{
				for(int b = i; b < 99; b++)
				{
					clientArray[b] = clientArray[b+1];
				}
			}
		}
		clientIndex--;
		System.out.println("Client Socket removed from list");
	}
 	public static void main(String[] args) throws IOException 
  	{ 
	clientArray = new Socket[100];
	clientIndex = 0;
	buffer = "";
    	ServerSocket serverSocket = null; 

   		try { 
        	serverSocket = new ServerSocket(4445); 
       		System.out.println ("Connection ChatSocket Created");
         	try { 
            	while (true)
                {
                	System.out.println ("Waiting for Connection to Chat Server");
			Socket clientAccept = serverSocket.accept();
			clientArray[clientIndex++] = clientAccept;
                  	new ChatServer (clientAccept); 
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
        	System.err.println("Could not listen on port: 4445."); 
         	System.exit(1); 
        } 
    	finally
        {
        	try {
         	   serverSocket.close(); 
            }
         	catch (IOException e)
            { 
            	System.err.println("Could not close port: 4445."); 
              	System.exit(1); 
            } 
        }
	}

 	private ChatServer (Socket clientSoc)
   	{
    	clientSocket = clientSoc;
    	start();
   	}


 	public void run()
   	{
    	System.out.println ("New Communication Thread Started for Chat");
	try{
		//set when first go to Game frame;
		sendToAll();
	}catch(Exception send){

	}
    	try { 
       	 	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
        	BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

        	String inputLine; 

        	while ((inputLine = in.readLine()) != null) 
        	{ 
 	       		//System.out.println ("Server: " + inputLine);
			buffer = buffer.concat(inputLine + "\n");
			System.out.println("This is the whole buffer: " + buffer);
			try{
				sendToAll();
			}catch(Exception excep){
				System.out.println("Unable to send to all clients");
			}
           		//out.println(inputLine); 

           		if (inputLine.equals("Bye.")) break; 
        	} 
		System.out.println("Closing Client Socket");
		removeFromList(clientSocket);
        	out.close(); 
        	in.close(); 
        	clientSocket.close(); 
        } 
   	 	catch (IOException e) 
        { 
         	System.err.println("Problem with Communication ChatServer");
         	System.exit(1); 
        } 
    }
} 

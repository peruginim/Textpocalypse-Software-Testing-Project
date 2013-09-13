import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable 
{

	public static void main(String argv[])
	{
		Server sv = new Server();
		sv.run();
		
	}
	
	public void run()
	{
		try 
		{
			String clientSentence;
			ServerSocket welcomeSocket = new ServerSocket(4444);

			while(true)
			{
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientSentence = inFromClient.readLine();
				System.out.println("FROM CLIENT: " + clientSentence);
				outToClient.writeBytes("Connection with server established!\n");
			}
		}
		catch(IOException e){
		
		}
			
	}

}

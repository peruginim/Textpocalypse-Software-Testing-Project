import java.net.*; 
import java.io.*; 

public class Server extends Thread
{ 
	public static List[] userList;
	public static int index;
 	protected Socket clientSocket;

	public boolean handleRequest(String user, String pass, int message)
	{
		switch(message)
		{
			//Login
			case 2:
				//Search
				for(int i = 0; i < index; i++){
					if(userList[i].user.equals(user))
					{
						if(userList[i].password.equals(pass))
						{
							if(userList[i].loggedIn == false)
							{
								userList[i].loggedIn = true;
								return true;
							}
						}
					}
				}
				return false;
			//NewUser
			case 3:
				boolean userExists = false;
				for(int i = 0; i < index; i++)
				{
					if(userList[i].user.equals(user))
					{
						userExists = true;
					}
				}
				if(userExists == false)
				{
					userList[index] = new List();
					userList[index].user = user;
					userList[index].password = pass;
					userList[index].loggedIn = true;
					index++;
					return true;
				}else
				{
					return false;
				}
			//Logout
			case 4:
				for(int i = 0; i < index; i++)
				{
					if(userList[i].user.equals(user))
					{
						userList[i].loggedIn = false;
						return true;
					}
				}
		}
		return false;
	}
	
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
			String[] parsedCommand  = inputLine.split(",");
			boolean temp = handleRequest(parsedCommand[0], parsedCommand[1], Integer.parseInt(parsedCommand[2]));
			if(temp)
			{
				out.println("Success");
			}else
			{
				out.println("Failure");
			}
           		//out.println(inputLine); 

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



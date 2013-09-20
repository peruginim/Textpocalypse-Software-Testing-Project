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
					serialize();
					deserialize();
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
	
	
	
/*
 *
 * Serialize saves the array of List objects to disk
 * allowing data to be stored when the server is offline
 *
 */
	public void serialize(){
	
		System.out.println("SERIALIZE!");
		
		for (int x=0; x<index; x++)
		{
		
			System.out.println("List to serialize...");
			System.out.println("User: " + userList[x].user);
			System.out.println("Password: " + userList[x].password);
			System.out.println("Location: " + userList[x].location);
			System.out.println("Logged in: " + userList[x].loggedIn);
		}
		
		try
  		{
  			FileOutputStream fileOut = new FileOutputStream("playerdata.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
 	 		out.writeObject(userList);
 			out.close();
 			fileOut.close();
			System.out.println("Serialized data is saved in playerdata.ser");
 		}
 		catch(IOException e)
	 	{
	  		System.out.println("Error while serializing");
	  		e.printStackTrace();
	 	}
	 	 		 	
  	}
  	
/*
 *
 * Deserialize loads from disk the saved instance of the player database
 *
 */
	public void deserialize(){
	
		System.out.println("DESERIALIZE!");
		
    	userList = null;
     	try
      	{
        	FileInputStream fileIn = new FileInputStream("playerdata.ser");
       		ObjectInputStream in = new ObjectInputStream(fileIn);
         	userList = (List[]) in.readObject();
         	in.close();
		    fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
		    return;
		}catch(ClassNotFoundException c)
		{
			System.out.println("List class not found");
		    c.printStackTrace();
		    return;
		}
		for (int x=0; x<index; x++)
		{
		
			System.out.println("Deserialized List...");
			System.out.println("User: " + userList[x].user);
			System.out.println("Password: " + userList[x].password);
			System.out.println("Location: " + userList[x].location);
			System.out.println("Logged in: " + userList[x].loggedIn);
		}
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



/*
 *
 * @List class stores all pertinent user data
 *
 */
class List implements Serializable
{
	String user;
	String password;
	String location;
	
	int health;
	int strength;
	int agility;
	int intelligence;
	int armor;
	int critChance;
	int critModifier;
	int damage;
	int hitChance;
	boolean loggedIn;
	int [] inventory = new int[102];
}



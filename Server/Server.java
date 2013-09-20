import java.net.*; 
import java.io.*; 

public class Server extends Thread
{ 
	public static List[] userList;
	public static Integer index;
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
								System.out.println("case 2: ");
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
					serializeToFile();
					deserializeFromFile();
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
						serializeToFile();
						return true;
					}
				}
				
		}
		return false;
	}
	
	
	// handle receiving list class
	public List handleList(String user){
		for(int i = 0; i < index; i++)
		{
			if(userList[i].user.equals(user))
			{
				return userList[i];
			
			}
		}
		return null;
	
	
	
	}
	
	
	
/*
 *
 * serializeToFile saves the array of List objects to disk
 * allowing data to be stored when the server is offline
 *
 */
	public void serializeToFile()
	{
	
		System.out.println("SERIALIZE!");
		
		for (int x=0; x<index; x++)
		{
			System.out.println("List to serialize to disk...");
			System.out.println("User: " + userList[x].user);
			System.out.println("Password: " + userList[x].password);
			System.out.println("Location: " + userList[x].location);
			System.out.println("Logged in: " + userList[x].loggedIn);
			System.out.println("Index is: " + index);
		}
		
		try
  		{
  			// save list array
  			FileOutputStream fileOut = new FileOutputStream("playerdata.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
 	 		out.writeObject(userList);
 			out.close();
 			fileOut.close();
			System.out.println("Serialized data is saved in playerdata.ser");
			
			// save index
			FileOutputStream fileOut2 = new FileOutputStream("index.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
			out2.writeObject(index);
 			out2.close();
 			fileOut2.close();
			System.out.println("Serialized data is saved in index.ser");
 		}
 		catch(IOException e)
	 	{
	  		System.out.println("Error while serializing");
	  		e.printStackTrace();
	 	}
	 	 		 	
  	}
  	
/*
 *
 * deserializeFromFile loads from disk the saved instance of the player database
 *
 */
	public static void deserializeFromFile()
	{
	
		System.out.println("DESERIALIZE from disk!");
		
     	try
      	{
      		// open list array
      		System.out.println(">>> opening playerdata.ser");
        	FileInputStream fileIn = new FileInputStream("playerdata.ser");
       		ObjectInputStream in = new ObjectInputStream(fileIn);
         	userList = (List[]) in.readObject();
         	in.close();
		    fileIn.close();
		    
		    //open index
		    System.out.println(">>> opening index.ser");
        	FileInputStream fileInn = new FileInputStream("index.ser");
       		ObjectInputStream inn = new ObjectInputStream(fileInn);
       		index = (Integer) inn.readObject();
         	inn.close();
		    fileInn.close();
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
			try 
			{
				System.out.println(">>> INDEX");
				System.out.println("Deserialized List...");
				System.out.println("User: " + userList[x].user);
				System.out.println("Password: " + userList[x].password);
				System.out.println("Location: " + userList[x].location);
				System.out.println("Logged in: " + userList[x].loggedIn);
				System.out.println("Index is: " + index);
			}catch(Exception e)
			{
				System.out.println("empty list");
			}
				
		}
	}
	
	
 	public static void main(String[] args) throws IOException 
  	{ 
	index = 0;
	userList = new List[100];
    	ServerSocket serverSocket = null;
    	
    	File ind = new File("index.ser");
    	File pd = new File("playerdata.ser");
    	if( pd.createNewFile() ){
		ind.createNewFile();
    		System.out.println("creating player data files");
    	}else
    	{
			System.out.println("Blah");
			deserializeFromFile();
		}
			
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
		OutputStream os = clientSocket.getOutputStream();
       	 	PrintWriter out = new PrintWriter(os, 
                                      true); 
        	BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 
		
		ObjectOutputStream outStream = new ObjectOutputStream(os);
        	String inputLine; 

        	while ((inputLine = in.readLine()) != null) 
        	{ 
 	       		System.out.println ("Server: " + inputLine);
			String[] parsedCommand  = inputLine.split(",");
			
			if(Integer.parseInt(parsedCommand[2]) == 5)
			{
				List returnList;
				returnList = handleList(parsedCommand[0]);
				//send object back to client
				try{
					outStream.writeObject(returnList);
				}catch(Exception eblah){
					System.out.println(eblah);
				}
				
			}else if(Integer.parseInt(parsedCommand[2]) == 6)
			{
				String[] invent = parsedCommand[1].split("/");
				List returnList = handleList(parsedCommand[0]);
				for(int i = 0; i < invent.length; i++)
				{
					returnList.inventory[i] = Integer.parseInt(invent[i]);
					System.out.print(returnList.inventory[i]);
				}

			}else if(Integer.parseInt(parsedCommand[2]) == 7)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.health = Integer.parseInt(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 8)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.strength = Integer.parseInt(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 9)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.armor = Integer.parseInt(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 10)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.critChance = Double.parseDouble(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 11)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.critModifier = Integer.parseInt(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 12)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.damage = Integer.parseInt(parsedCommand[1]);

			}else if(Integer.parseInt(parsedCommand[2]) == 13)
			{
				List returnList = handleList(parsedCommand[0]);
				returnList.hitChance = Double.parseDouble(parsedCommand[1]);

			}else{
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
	
	int health = 10;
	int strength = 1;
	int agility = 1;
	int intelligence = 1;
	int armor = 0;
	double critChance = 0.05;
	int critModifier = 2;
	int damage = 1 + strength;
	double hitChance = 0.9;
	boolean loggedIn;
	int [] inventory = new int[20];
}




import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/*
 *
 *	@Client is an abstract class with four stub methods which must
 *	be implemented in any child classes.  Child classes of Client 
 *	should include any GUI screens in Textpocalypse (Login, Create
 *	User, Menu, Instructions, the actual game).  These screens will 
 *	create the necessary frames, panels, buttons and labels for its
 *	screen.  Additionally, these child classes inherit access to the 
 *	connectToServer function.
 *
 */


public abstract class Client extends Thread implements Runnable
{

	public static String currentUser;
	public static boolean connected = false;
	public static JTextArea chatTextField;
	public static JTextField chatTextBox;
	public static PrintWriter out2;
	public static String outToClient;
	public static BufferedReader in2;
	public static String[] stats;
	public static Location[] location;
	public static List userStats;
	public static int inventIndex;
	//Creates the login screen
	public static void main(String[] args)
	{
		/*
		location = new Location[10];
		location[0].locName = "P.U.S.H.";
		location[0].picName = "PUSH.jpg";
		location[0].north = "Ross-Ade";
		location[0].south = null;
		location[0].east = null;
		location[0].west = null;
		location[0].northeast = "Armstrong";
		location[0].northwest = null;
		location[0].southeast = null;
		location[0].southwest = "Lawson";
		location[0].description = "push.txt";
		location[0].thresh1 = "push_thresh1.txt";
		location[0].thresh2 = "push_thresh2.txt";
		location[0].thresh3 = "push_thresh3.txt";


		location[1].locName = "Armstrong";
		location[1].picName = "Armstrong.jpg";
		location[1].north = null;
		location[1].south = "Elliot";
		location[1].east = null;
		location[1].west = null;
		location[1].northeast = null;
		location[1].northwest = null;
		location[1].southeast = "Engineering Mall";
		location[1].southwest = "P.U.S.H.";
		location[1].description = "armstrong.txt";
		location[1].thresh1 = "armstrong_thresh1.txt";
		location[1].thresh2 = "armstrong_thresh2.txt";
		location[1].thresh3 = "armstrong_thresh3.txt";

		location[2].locName = "Lawson";
		location[2].picName = "Lawson.jpg";
		location[2].north = null;
		location[2].south = "Lily";
		location[2].east = "Elliot";
		location[2].west = "Co-Rec";
		location[2].northeast = "P.U.S.H.";
		location[2].northwest = null;
		location[2].southeast = null;
		location[2].southwest = "Discovery Park";
		location[2].description = "lawson.txt";
		location[2].thresh1 = "lawson_thresh1.txt";
		location[2].thresh2 = "lawson_thresh2.txt";
		location[2].thresh3 = "lawson_thresh3.txt";

		location[3].locName = "Elliot";
		location[3].picName = "Elliot.jpg";
		location[3].north = "Armstrong";
		location[3].south = null;
		location[3].east = "Union";
		location[3].west = "Lawson";
		location[3].northeast = "Engineering Mall";
		location[3].northwest = null;
		location[3].southeast = null;
		location[3].southwest = "Lily";
		location[3].description = "elliot.txt";
		location[3].thresh1 = "elliot_thresh1.txt";
		location[3].thresh2 = "elliot_thresh2.txt";
		location[3].thresh3 = "elliot_thresh3.txt";

		location[4].locName = "Engineering Mall";
		location[4].picName = "engineeringMall.jpg";
		location[4].north = null;
		location[4].south = null;
		location[4].east = null;
		location[4].west = null;
		location[4].northeast = null;
		location[4].northwest = "Armstrong";
		location[4].southeast = "Union";
		location[4].southwest = "Elliot";
		location[4].description = "engmall.txt";
		location[4].thresh1 = "engmall_thresh1.txt";
		location[4].thresh2 = "engmall_thresh2.txt";
		location[4].thresh3 = "engmall_thresh3.txt";

		location[5].locName = "Union";
		location[5].picName = "stunion.jpg";
		location[5].north = null;
		location[5].south = null;
		location[5].east = null;
		location[5].west = "Elliot";
		location[5].northeast = null;
		location[5].northwest = "Engineering Mall";
		location[5].southeast = null;
		location[5].southwest = null;
		location[5].description = "union.txt";
		location[5].thresh1 = "union_thresh1.txt";
		location[5].thresh2 = "union_thresh2.txt";
		location[5].thresh3 = "union_thresh3.txt";

		location[6].locName = "Lily";
		location[6].picName = "Lily.jpg";
		location[6].north = "Lawson";
		location[6].south = null;
		location[6].east = null;
		location[6].west = "Discovery Park";
		location[6].northeast = "Elliot";
		location[6].northwest = null;
		location[6].southeast = null;
		location[6].southwest = "Lawson";
		location[6].description = "lily.txt";
		location[6].thresh1 = "lily_thresh1.txt";
		location[6].thresh2 = "lily_thresh2.txt";
		location[6].thresh3 = "lily_thresh3.txt";

		location[7].locName = "Discovery Park";
		location[7].picName = "discoveryPark.jpg";
		location[7].north = null;
		location[7].south = null;
		location[7].east = "Lily";
		location[7].west = null;
		location[7].northeast = "Lawson";
		location[7].northwest = null;
		location[7].southeast = null;
		location[7].southwest = null;
		location[7].description = "park.txt";
		location[7].thresh1 = "park_thresh1.txt";
		location[7].thresh2 = "park_thresh2.txt";
		location[7].thresh3 = "park_thresh3.txt";

		location[8].locName = "Co-Rec";
		location[8].picName = "dova.jpg";
		location[8].north = null;
		location[8].south = "Discovery Park";
		location[8].east = "Lawson";
		location[8].west = null;
		location[8].northeast = null;
		location[8].northwest = null;
		location[8].southeast = null;
		location[8].southwest = null;
		location[8].description = "corec.txt";
		location[8].thresh1 = "corec_thresh1.txt";
		location[8].thresh2 = "corec_thresh2.txt";
		location[8].thresh3 = "corec_thresh3.txt";

		location[9].locName = "Ross-Ade";
		location[9].picName = "rossAide.jpg";
		location[9].north = null;
		location[9].south = "P.U.S.H.";
		location[9].east = null;
		location[9].west = null;
		location[9].northeast = null;
		location[9].northwest = null;
		location[9].southeast = "Armstrong";
		location[9].southwest = null;
		location[9].description = "rossade.txt";
		location[9].thresh1 = "rossade_thresh1.txt";
		location[9].thresh2 = "rossade_thresh2.txt";
		location[9].thresh3 = "rossade_thresh3.txt";
		*/
		inventIndex = 0;
		new Login();
	}

	//These methods must be implemented in child classes
	public abstract void createFrame();

	public abstract void createPanels();

	public abstract void createButtons();

	public abstract void createLabels();

	//Child classes can call connectToServer
	public boolean connectToServer(String user, String pass, int message) throws Exception
	{
        //String serverHostname = new String ("borg21.cs.purdue.edu");
        String serverHostname = new String ("localhost");
        System.out.println ("Attemping to connect to host " +
		serverHostname + " on port 4444.");

        Socket echoSocket = null;
        PrintWriter out = null;
	InputStream inS = null;
	BufferedReader in = null;
	ObjectInputStream ois = null;

        try {
            echoSocket = new Socket(serverHostname, 4444);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
	    inS = echoSocket.getInputStream();
	    in = new BufferedReader(new InputStreamReader(inS));
	    ois = new ObjectInputStream(inS);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            //System.exit(1);
        }
        
        switch(message)
        {
		//Send Location
		case 0:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
        	//Startup
       		case 1:
       			//out.println("starting new client");
			break;
       		//Login
 		case 2:
 			out.println(user + "," + pass + ","  + message);
			String tempOut = in.readLine();
			if(tempOut.equals("Success"))
			{
				//Login Succesful
				out.close();
				echoSocket.close();
				return true;
			}else
			{
				//Login Unsuccessful
				out.close();
				echoSocket.close();
				return false;
			}
		//NewUser
		case 3:
			out.println(user + "," + pass + "," + message);
			if(in.readLine().equals("Success"))
			{
				out.close();
				echoSocket.close();
				return true;
			}else
			{
				//Username is taken
				out.close();
				echoSocket.close();
				return false;
			}
       		//Logout
		case 4:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//Get stats
		case 5:
			out.println(user + "," + pass + "," + message);
			userStats = (List)ois.readObject();
			return true;
		//send inventory
		case 6:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//sendHealth
		case 7:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send strength
		case 8:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send armor
		case 9:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send critchance
		case 10:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send crit mod
		case 11:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send damage
		case 12:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
		//send hitChance
		case 13:
			out.println(user + "," + pass + "," + message);
			out.close();
			echoSocket.close();
			return true;
        }
        
		out.close();
		echoSocket.close();
		return true;
    
	}

	public void run() 
	{
	System.out.println("Started Thread");
        String serverHostname = new String ("borg21.cs.purdue.edu");
        //String serverHostname = new String ("localhost");
        System.out.println ("Attemping to connect to host " +
		serverHostname + " on port 4445.");

        Socket echoSocket = null;
        //PrintWriter out2 = null;
	//BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostname, 4445);
            out2 = new PrintWriter(echoSocket.getOutputStream(), true);
	    in2 = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            //System.exit(1);
        }

	//out.println(chatToServer);
	String temp = "";
	int tempInt = 0;
	char tempChar = 'a';
	while(true){
		try{
			//out.println(chatToServer);
			while(true)
			{
				tempInt = in2.read();
				//System.out.println(tempInt);
				tempChar = (char)tempInt;
				if(tempInt == 0)
					break;
				temp = temp + tempChar;
				//System.out.println(temp);
			}
			System.out.println("This is what is printed to the Textbox " + temp);
			if(temp.charAt(0) == '\n')
			{
				temp = temp.substring(1);
			}
			chatTextField.setText(temp);
			temp = "";
			//outToClient = temp;
			//System.out.println(outToClient);
			if(temp == null)
			{
				System.out.println("broke loop");
				break;
			}
		}catch(Exception blah){

		}	
	}        
	//out2.close();
	/*try{
		echoSocket.close();
	}catch(IOException excep){
		System.out.println("a;sjfdkasdhf;hasd;jfhalsdhf;lasdfasdf");
	}*/
  
	}
}


class Login extends Client implements ActionListener
{

	//Client Frame
	JFrame clientFrame;
	JPanel loginPanel, serverPanel, emptyPanel;

	//Login Panel
	JPanel loginCredPanel;
	JButton newUserButton, loginButton;
	JLabel loginIdText, passwordText;
	JTextField usernameTextBox;
	JPasswordField passwordTextBox;
	JButton exitButton;

	//Server Panel
	JLabel serverStatus;

	public static void main(String[] args)
	{
		//(new Thread(new Login())).start();
	}

	public Login()
	{
		createButtons();
		createLabels();
		createPanels();
		createFrame();
		try
		{ 
			if(!(connected)){
				if ( connectToServer("", "",  1) ){ 
					serverStatus.setText("Server is running, better catch it!");
					connected = true;
				}
			}
		}
		catch (Exception e)
		{
			serverStatus.setText("Exception! Error with server...");
		}
	}

	public void createFrame()
	{
		clientFrame = new JFrame("Textpocalypse: Purdue -- LOGIN");
		clientFrame.setLayout(new BorderLayout());
		clientFrame.setSize(500, 200);

		clientFrame.add(loginPanel, BorderLayout.CENTER);
		clientFrame.add(serverPanel, BorderLayout.EAST);

		clientFrame.setResizable(false);
		clientFrame.setLocationRelativeTo(null);
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setVisible(true);
	}

	public void createPanels()
	{
		//LoginCreditialsPanel
		loginCredPanel = new JPanel(new GridLayout(2,2));
		loginCredPanel.add(loginIdText);
		loginCredPanel.add(usernameTextBox);
		loginCredPanel.add(passwordText);
		loginCredPanel.add(passwordTextBox);

		//EmtpyPanel
		emptyPanel = new JPanel(new GridLayout(1,1));

		//LoginPanel
		loginPanel = new JPanel(new GridLayout(5, 1));
		loginPanel.add(loginCredPanel);
		loginPanel.add(loginButton);
		loginPanel.add(newUserButton);
		loginPanel.add(emptyPanel);
		loginPanel.add(exitButton);

		//ServerPanel
		serverPanel = new JPanel(new BorderLayout());
		serverPanel.add(serverStatus, BorderLayout.CENTER);

	}

	public void createButtons()
	{
		newUserButton = new JButton("Create New User");
		newUserButton.addActionListener(this);

		loginButton = new JButton("Enter");
		loginButton.addActionListener(this);

		exitButton = new JButton("Exit Game");
		exitButton.addActionListener(this);
	}

	public void createLabels()
	{
		loginIdText = new JLabel("Login ID:");
		passwordText = new JLabel("Password:");
		serverStatus = new JLabel("Server stuff will go here");
		usernameTextBox = new JTextField();
		passwordTextBox = new JPasswordField();
	}

	public void actionPerformed(ActionEvent e)
	{
		Object pressed = e.getSource();

		if(pressed.equals(newUserButton))
		{
			//Go to new user screen. Hide current frame
			new CreateNewUser();
			clientFrame.dispose();
		}else if(pressed.equals(loginButton))
		{
			try{
				String userTemp = usernameTextBox.getText();
				char[] passTemp = passwordTextBox.getPassword();
				String passTemp2 = new String(passTemp);
				boolean accepted = connectToServer(userTemp, passTemp2, 2);
				if(accepted)
				{
					//User accepted
					currentUser = userTemp;
					clientFrame.dispose();
					boolean stats = connectToServer(currentUser, "", 5);
					if(stats)
					{
						System.out.println("stats uploaded");
						System.out.println(userStats.health);
						System.out.println(userStats.inventory[0]);
					}
					new MainMenu();
				}else
				{
					serverStatus.setText("The Username or Password you entered does not exist or is in use");
				}
			}catch(Exception excep){
				System.out.println("Well fuck");
			}
			//Communicate with server to login
			//Change currentUser to logged in User
		}else if(pressed.equals(exitButton))
		{
			try{
				boolean temp = connectToServer(currentUser, "", 4);
			}catch(Exception excep){
				
			}
			System.exit(1);
		}
	}

}



/*
 *
 * @CreateNewUser creates a new form allowing the user to 
 *  enter a desired username and password for character
 *  creation. This class will send user data to the server
 *  which will verify the uniqueness of the username.
 *  
 */

class CreateNewUser extends Client implements ActionListener
{


	JFrame createUserFrame;

	//createUserPanel
	JPanel createUserPanel; 
	JButton createUserButton, backButton;
	JLabel usernameText;
	JLabel passwordText;
	JLabel password2Text;
	JTextField usernameTextBox;
	JPasswordField passwordTextBox;
	JPasswordField password2TextBox;

	//textPanel
	JPanel textPanel;
	JTextField verificationText;

	public static void main(String[] args)
	{
		//new CreateNewUser();
	}

	public CreateNewUser()
	{
		//stops extra boxs
		//client.clientFrame.dispose();
		createButtons();
		createLabels();
		createPanels();
		createFrame();
	}

	public void createFrame()
	{
		createUserFrame = new JFrame("Textpocalypse: Purdue -- CREATE NEW USER");
		createUserFrame.setLayout(new BorderLayout());
		createUserFrame.setSize(500, 200);

		createUserFrame.add(createUserPanel, BorderLayout.CENTER);
		createUserFrame.add(textPanel, BorderLayout.SOUTH);

		createUserFrame.setResizable(false);
		createUserFrame.setLocationRelativeTo(null);
		createUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUserFrame.setVisible(true);
	}

	public void createPanels()
	{
		createUserPanel = new JPanel(new GridLayout(9, 1));
		
		createUserPanel.add(usernameText);
		createUserPanel.add(usernameTextBox);
		createUserPanel.add(passwordText);
		createUserPanel.add(passwordTextBox);
		createUserPanel.add(password2Text);
		createUserPanel.add(password2TextBox);
		createUserPanel.add(createUserButton);
		createUserPanel.add(backButton);
		createUserPanel.add(verificationText);

		//textPanel
		textPanel = new JPanel(new GridLayout(1,1));
		textPanel.add(verificationText);

	}

	public void createButtons()
	{
		createUserButton = new JButton("Create New User and Login");
		createUserButton.addActionListener(this);

		backButton = new JButton("Back");
		backButton.addActionListener(this);
	}

	public void createLabels()
	{
		usernameText = new JLabel("Desired Username:");
		passwordText = new JLabel("Password:");
		password2Text = new JLabel("Re-enter password:");
		usernameTextBox = new JTextField();
		passwordTextBox = new JPasswordField();
		password2TextBox = new JPasswordField();
		verificationText = new JTextField();
		verificationText.setText("");
		verificationText.setEditable(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object pressed = e.getSource();

		if(pressed.equals(createUserButton))
		{
			//Create user and login

			//Check if passwords match
			char[] temp = passwordTextBox.getPassword();
			char[] temp2 = password2TextBox.getPassword();
			String user = usernameTextBox.getText();
			String pass = new String(temp);
			String pass2 = new String(temp2);
			if(!(pass.equals(pass2)))
			{
				verificationText.setText("The passwords you entered do not match!");
			}else
			{
				try{
					if(connectToServer(user, pass2, 3))
					{
						//Check if user is available with the server
						//if yes go into main menu and make currentUser the created User
						currentUser = user;
						boolean stats = connectToServer(currentUser, "", 5);
						if(stats)
						{
							System.out.println("stats uploaded");
							System.out.println(userStats.health);
							System.out.println(userStats.inventory[0]);
						}
						createUserFrame.dispose();
						new MainMenu();
					}else
					{
						//Username was taken
						verificationText.setText("This username has been taken!");
					}
				}catch(Exception excep)
				{
					System.out.print("fasdfasdl;kfj");	
				}
				//if no update verificationText telling that the name is taken
			}

		}else if(pressed.equals(backButton))
		{
			createUserFrame.dispose();
			new Login();
		}
	}
}


/*
 *
 * @MainMenu creates the main menu dialog box that gives the options to move into the game or
 * look at instructions or logout
 *
 */

class MainMenu extends Client implements ActionListener, WindowListener
{

    JFrame mainMenuFrame;

    //MainMenuFrame
    JButton instructButton, enterButton, logoutButton;
    JLabel currentUserLabel;
    JPanel buttonPanel;

    public static void main(String[] args)
    {
    }

    public MainMenu()
    {
        createButtons();
        createLabels();
        createPanels();
        createFrame();
    }
    
    public void createFrame()
    {		
		mainMenuFrame = new JFrame("Textpocalypse: Purdue -- MAIN MENU");
        mainMenuFrame.setLayout(new GridLayout(1,2));
        mainMenuFrame.setSize(500, 200);

        mainMenuFrame.add(buttonPanel);
        mainMenuFrame.add(currentUserLabel);

        mainMenuFrame.setResizable(false);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainMenuFrame.addWindowListener(this);
        mainMenuFrame.setVisible(true);
    }

    public void createPanels()
    {
        buttonPanel = new JPanel(new GridLayout(3,1));
        buttonPanel.add(instructButton);
        buttonPanel.add(enterButton);
        buttonPanel.add(logoutButton);
    }

    public void createLabels()
    {
        currentUserLabel = new JLabel("Current User:  " + currentUser);
    }

    public void createButtons()
    {
        instructButton = new JButton("Instructions");
        instructButton.addActionListener(this);

        enterButton = new JButton("Enter World");
        enterButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object pressed = e.getSource();
        if(pressed.equals(instructButton))
        {
            //Go to instructions dialog
            new Instructions();
        }else if(pressed.equals(enterButton))
        {
            //Go to World Dialog Box
            mainMenuFrame.dispose();
            (new Thread(new Game())).start();
        }else if(pressed.equals(logoutButton))
        {
	    try{	
	    	boolean temp = connectToServer(currentUser, "", 4);
            	currentUser = null;
            	mainMenuFrame.dispose();
            	new Login();
	    }catch(Exception excep){

	    }
        }
    }
    
    
	//window listener
    public void windowClosing(WindowEvent e) {
    	Object closed = e.getSource();
		if(closed.equals(mainMenuFrame))
		{
			try{
				boolean temp = connectToServer(currentUser, "", 4);
			}catch(Exception excep){
				
			}
			System.exit(1);
		}
    }

    public void windowClosed(WindowEvent e) {        
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowGainedFocus(WindowEvent e) {
    }

    public void windowLostFocus(WindowEvent e) {

    }

    public void windowStateChanged(WindowEvent e) {
    }


}



/*
 * 
 * @The instructions dialog box, to be opened when pressed. Will not close original box it was opened from
 * 
 */


class Instructions extends Client implements ActionListener
{
	JFrame instructFrame;

	//Instructions Frame
	JButton exitButton;
	JTextArea howtoPlayText;
	JScrollPane scroll;

	public static void main(String[] args)
	{
		//new Instructions();
	}

	Instructions()
	{	
		createButtons();
		createLabels();
		createFrame();
	}

	public void createFrame()
	{
		instructFrame = new JFrame("Textpocalypse: Purdue -- INSTRUCTIONS");
		instructFrame.setLayout(new BorderLayout());
		instructFrame.setSize(500, 400);

		instructFrame.add(scroll, BorderLayout.CENTER);
		instructFrame.add(exitButton, BorderLayout.PAGE_END);

		instructFrame.setResizable(false);
		instructFrame.setLocationRelativeTo(null);
		instructFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instructFrame.setVisible(true);
	}
	public void createPanels(){}

	public void createLabels()
	{
	    try {
    	  BufferedReader br = new BufferedReader(new FileReader("./TextFiles/intro.txt"));
      	  StringBuilder sb = new StringBuilder();
      	  String line = br.readLine();

       	  while (line != null) {
          	sb.append(line);
            sb.append('\n');
            line = br.readLine();
          }
          String everything = sb.toString();
          howtoPlayText = new JTextArea(everything);
          br.close();
          howtoPlayText.setLineWrap(true);
          howtoPlayText.setEditable(false);
          scroll = new JScrollPane(howtoPlayText);
		  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        } catch (Exception e) {
        	howtoPlayText = new JTextArea("Error reading instructions file");
        	System.out.println("Error reading instructions file");
        }
	}

	public void createButtons()
	{
		exitButton = new JButton("Close Instructions");
		exitButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object pressed = e.getSource();
		if(pressed.equals(exitButton))
		{
			instructFrame.dispose();
		}
	}
}



/*
 * @The actual game menu
 * 
 */

class Game extends Client implements ActionListener, WindowListener
{
    String equipThis;
    JFrame gameFrame;

    //GameFrame
    JPanel commandPanel, mapPanel, chatPanel, buttonPanel;

    //CommandPanel
    //?
    JTextArea commandResponse;
    JTextField commandField;
    JScrollPane scroll;
    //MapPanel
    JLabel picLabel;
    BufferedImage myPicture;

    //chatPanel
    JScrollPane scroll2;

    //ButtonPanel
    JLabel currentUserLabel;
    JButton instructButton, logoutButton, exitButton;

    public static void main(String[] args)
    {
    }
    
    public Game()
    {
	createTextFields();
        createButtons();
        createLabels();
        createPanels();
        createFrame();
    }

    public void createFrame()
    {
        gameFrame = new JFrame("Textpocalypse: Purdue -- GAME");
        gameFrame.setLayout(new GridLayout(2,2));
        gameFrame.setSize(750, 750);

        gameFrame.add(commandPanel);
        gameFrame.add(mapPanel);
        gameFrame.add(chatPanel);
        gameFrame.add(buttonPanel);

        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameFrame.addWindowListener(this);
        gameFrame.setVisible(true);
    }

    public void createPanels()
    {
        //This will be decided Later
        commandPanel = new JPanel(new BorderLayout());
	commandPanel.add(scroll, BorderLayout.CENTER);
	commandPanel.add(commandField, BorderLayout.PAGE_END);

        //This will be decided Later
        mapPanel = new JPanel(new BorderLayout());
	try{
		myPicture = ImageIO.read(new File("PUSH.jpg"));
	}catch(Exception e){
		System.out.println("wellfuck2");
	}
	picLabel = new JLabel(new ImageIcon(myPicture));
	mapPanel.add(picLabel);

        //This will be decided Later
        chatPanel = new JPanel(new BorderLayout());
	chatPanel.add(scroll2, BorderLayout.CENTER);
	chatPanel.add(chatTextBox, BorderLayout.PAGE_END);

        buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(currentUserLabel);
        buttonPanel.add(instructButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);
    }

    public void createLabels()
    {
	currentUserLabel = new JLabel("Current User:  " + currentUser);
	commandResponse = new JTextArea("Enter Command");
	commandResponse.setLineWrap(true);
	commandResponse.setEditable(false);
	scroll = new JScrollPane(commandResponse);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



	chatTextField = new JTextArea("");
	chatTextField.setLineWrap(true);
	chatTextField.setEditable(false);
	//Need scrollbar to work may not be working because one solid string is being passed
	scroll2 = new JScrollPane(chatTextField);
	scroll2.setVerticalScrollBarPolicy(JScrollPane.	VERTICAL_SCROLLBAR_ALWAYS);
	scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	DefaultCaret caret = (DefaultCaret)chatTextField.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void createTextFields()
    {
    	commandField = new JTextField();
	commandField.addActionListener(this);

	chatTextBox = new JTextField();
	chatTextBox.addActionListener(this);
    }
    public void createButtons()
    {
        instructButton = new JButton("Instructions");
        instructButton.addActionListener(this);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        exitButton = new JButton("Exit Game");
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object pressed = e.getSource();
        if(pressed.equals(instructButton))
        {
            new Instructions();
        }else if(pressed.equals(logoutButton))
        {   
	    try{
	    	boolean temp = connectToServer(currentUser, "", 4);
            	currentUser = null;
            	gameFrame.dispose();
            	new Login();
	    }catch(Exception excep){
		
	    }
        }else if(pressed.equals(exitButton))
        {
	    try{
		boolean temp = connectToServer(currentUser, "", 4);
	    }catch(Exception excep){
		
	    }
            System.exit(1);
        }else if(pressed.equals(commandField))
	{
		String text = commandField.getText();
		try{
			String [] commandArray = text.split(" ", 2);
			//equipThis = commandArray[1];
			String response = gameLogic(commandArray[0], commandArray[1]);
			commandResponse.setText(response);
		}catch(Exception except){
			commandResponse.setText("Invalid use. Please enter a command followed by parameters");
		}
		commandField.setText("");
			
	}
	else if(pressed.equals(chatTextBox))
	{
		String temp = chatTextBox.getText();
		out2.println(currentUser + ": " + temp + " ");
		chatTextBox.setText("");
	}
    }
    
	//window listener
    public void windowClosing(WindowEvent e) {
    	Object closed = e.getSource();
		if(closed.equals(gameFrame))
		{
			try{
				boolean temp = connectToServer(currentUser, "", 4);
			}catch(Exception excep){
				
			}
			System.exit(1);
		}
    }

    public void windowClosed(WindowEvent e) {        
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowGainedFocus(WindowEvent e) {
    }

    public void windowLostFocus(WindowEvent e) {

    }

    public void windowStateChanged(WindowEvent e) {
    }
    
	public void sendLocation()
	{
		String temp = userStats.location;
		try{
			boolean sendLocation = connectToServer(currentUser, temp, 0);
		}catch(Exception e){

		}
	}
    
	public void sendInvent()
	{
		String temp = "";
		for(int i = 0; i < inventIndex; i++)
		{
			temp = temp.concat(Integer.toString(userStats.inventory[i]) + "/");
		}
		try{
			boolean sendInvent = connectToServer(currentUser, temp, 6);
		}catch(Exception e){

		}
	}
    
	public void sendHealth()
	{
		String temp = Integer.toString(userStats.health);
		try{
			boolean sendHealth = connectToServer(currentUser, temp, 7);
		}catch(Exception e){
		
		}
	}

	public void sendStr()
	{
		String temp = Integer.toString(userStats.strength);
		try{
			boolean sendStr = connectToServer(currentUser, temp, 8);
		}catch(Exception e){
	
		}
	}

	public void sendArmor()
	{
		String temp = Integer.toString(userStats.armor);
		try{
			boolean sendArmor = connectToServer(currentUser, temp, 9);
		}catch(Exception e){
	
		}
	}

	public void sendCritChance()
	{
		String temp = String.valueOf(userStats.critChance);
		try{
			boolean sendCritChance = connectToServer(currentUser, temp, 10);
		}catch(Exception e){
	
		}
	}
	
	public void sendCritMod()
	{
		String temp = String.valueOf(userStats.critModifier);
		try{
			boolean sendCritMod = connectToServer(currentUser, temp , 11);
		}catch(Exception e){

		}
	}
	
	public void sendDamage()
	{
		String temp = Integer.toString(userStats.damage);
		try{
			boolean sendDamage = connectToServer(currentUser, temp, 12);
		}catch(Exception e){

		}
	}

	public void sendHitChance()
	{
		String temp = String.valueOf(userStats.hitChance);
		try{
			boolean sendHitChance = connectToServer(currentUser, temp, 13);
		}catch(Exception e){

		}
	}
	public String normalize(String input)
	{
		char temp[] = input.toCharArray();
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] < 97)
			{
				temp[i] += 32;
			}
		}
		String output = new String(temp);
		return output;
	}
	/*
	 * Reads in command line text for commands.....
	 */
	public String gameLogic(String command, String parameters)
	{
		if(command.equals("give"))
		{
			userStats.inventory[inventIndex++] = Integer.parseInt(parameters);
			String good = "woot";
			sendInvent();
			return good;
		}
		parameters = normalize(parameters);
		System.out.println(parameters);
		String response = "";
		switch(command)
		{
			case "move":
				// Should print out what valid moves a player can make.
				// Ex: Lawson is to the South. The Union is to the SouthEast
				try{
					String newLoc = parameters+".jpg";
					System.out.println(newLoc);
					BufferedImage newLocation = ImageIO.read(new File(newLoc));
					picLabel.setIcon(new ImageIcon(newLocation));
					picLabel.repaint();
				}
				catch(Exception eee){
					System.out.println("well fucker");
				}
				break;
			case "inventory":
				response = "Items should appear here";
				break;
			case "stats":
				response = "Stats should appear here";
				break;
			case "attack":
				// Should be a instance field that keeps track of the player being in a fight or not
				// if fighting
				// {
				// 		dice roll to see if a player hits or misses (they have hit chance
				// 		dmgDealt = player dmg (Damage + strength)
				// 		dice roll to see if player crits (player has %crit stat)
				// 		if crit
				// 			x2 multiplier
				//		dmgDealt = dmgDealt - (.5 * armor)
				//		deal dmg
				//		response = "You dealt " + dmgDealt + " damage.";
				//	}
				//	else
				//		response = "You are not in combat!";
				break;
			case "flee":
				// if not in combat
				// 		response = "You are not in combat!";
				// else if in Ross Ade
				// 		response = "There is no escape!";
				// else
				// {
				//		if monster level = 1
				//		{
				//			response = "Successfully Escaped";
				//			in combat = false;
				//		}
				//		else if monster level = 2
				//		{
				//			int x = random(1 - 4);
				//			if x < 4
				//			{
				//				response = "Successfully Escaped";
				//				in combat = false;
				//			}
				//			else
				//				response = "Cannot flee!";
				//		}
				//		else if monster level = 3
				//		{
				//			x = random(1-2);
				//			if x = 1
				//			{
				//				response = "Successfully Escaped";
				//				in combat = false;
				//			}
				//			else
				//				response = "Cannot flee!";
				//		}
				//		else if monster level > 4
				//			response = "Cannot flee!";
				// }
				break;
			case "where":
				// retrieval of location name (Haas, LAwson, Ross-Ade)
				// response = location;
				break;
			case "search":
				// retreives player int
				// response = location.description;
				// if int > threshold 1
				// 		response += location.thresh1
				// else if int > thresh 2
				// 		response += location.thresh2
				// else if int > thresh 3
				// 		response += location.thresh3
				break;
			case "equip":
				response = "rm - rf";
				break;
			default:
				response = "Not a valid command. Please see instructions for a list of commands.";
				break;

		}
		return response;
	}

}

/*
 * @Location class stores location data
 */
class Location
{
	String locName;
	String north;
	String south;
	String east;
	String west;
	String northeast;
	String northwest;
	String southeast;
	String southwest;

	String description;
	String picName;
	// thresh = int threshold a player must meet in order to gain further information.
	String thresh1;
	String thresh2;
	String thresh3;
}

/*
 * @List class stores all perminent user data
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
	double critChance;
	int critModifier;
	int damage;
	double hitChance;
	boolean loggedIn;
	int[] inventory = new int[20];
}

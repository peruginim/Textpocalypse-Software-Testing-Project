
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.io.*;
import java.net.*;


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


public abstract class Client
{

	public static String currentUser;
	public static boolean connected = false;
	//Creates the login screen
	public static void main(String[] args)
	{
		new Login();
	}

	//These methods must be implemented in child classes
	public abstract void createFrame();

	public abstract void createPanels();

	public abstract void createButtons();

	public abstract void createLabels();

	//Child classes can call connectToServer
	public boolean connectToServer(String user, int message) throws Exception
	{
		
		//Intialize connection
		String receivedSentence;
		Socket clientSocket = new Socket("borg21.cs.purdue.edu", 4444);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		switch(message)
		{
		//Startup
		case 1:
			outToServer.writeBytes("Hello, I am the client!\n");
			receivedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + receivedSentence);
			return true;
		//Login
		case 2: outToServer.writeBytes(user + ", " + message);
			receivedSentence = inFromServer.readLine();
			System.out.println("Blah");
			//return true for now, it will depend on if the user is accepted from the server
			return true;

		//NewUser
		case 3: return false;
		default: return false;
		}
		
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
				if ( connectToServer("", 1) ){ serverStatus.setText("Server is running, better catch it!");
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
				boolean accepted = connectToServer(userTemp, 2);
			}catch(Exception excep){
				System.out.println("Well fuck");
			}
			//Communicate with server to login
			//Change currentUser to logged in User
		}else if(pressed.equals(exitButton))
		{
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
		//createUser Panel
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
		verificationText.setText("Confirm or deny user creation in this textbox");
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
				verificationText.setText("The passwords you entered do not match");
			}else
			{
				//Check if user is available with the server
				//if yes go into main menu and make currentUser the created User
				currentUser = user;
				createUserFrame.dispose();
				new MainMenu();
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

class MainMenu extends Client implements ActionListener
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
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            new Game();
        }else if(pressed.equals(logoutButton))
        {
            currentUser = null;
            mainMenuFrame.dispose();
            new Login();
        }

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
	JLabel howtoPlayText;

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
		instructFrame.setLayout(new GridLayout(2,1));
		instructFrame.setSize(500, 200);

		instructFrame.add(howtoPlayText);
		instructFrame.add(exitButton);

		instructFrame.setResizable(false);
		instructFrame.setLocationRelativeTo(null);
		instructFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructFrame.setVisible(true);
	}
	public void createPanels(){}

	public void createLabels()
	{
		howtoPlayText = new JLabel("A bunch of shit");
	}

	public void createButtons()
	{
		exitButton = new JButton("Back");
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

class Game extends Client implements ActionListener
{

    JFrame gameFrame;

    //GameFrame
    JPanel commandPanel, mapPanel, chatPanel, buttonPanel;

    //CommandPanel
    //?
    JLabel commandResponse;
    JTextField commandField;
    //MapPanel
    //?

    //chatPanel
    //?

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
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    public void createPanels()
    {
        //This will be decided Later
        commandPanel = new JPanel(new GridLayout(2,1));
	commandPanel.add(commandResponse);
	commandPanel.add(commandField);

        //This will be decided Later
        mapPanel = new JPanel(new BorderLayout());

        //This will be decided Later
        chatPanel = new JPanel(new BorderLayout());

        buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(currentUserLabel);
        buttonPanel.add(instructButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);
    }

    public void createLabels()
    {
    	currentUserLabel = new JLabel("Current User:  " + currentUser);
        commandResponse = new JLabel("Enter Command");
    }

    public void createTextFields()
    {
    	commandField = new JTextField();
	commandField.setMaximumSize( new Dimension( 200, 24 ) );
	commandField.addActionListener(this);
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
            currentUser = null;
            gameFrame.dispose();
            new Login();
        }else if(pressed.equals(exitButton))
        {
            System.exit(1);
        }else if(pressed.equals(commandField))
	{
	String text = commandField.getText();
	commandResponse.setText(text);
	commandField.setText("");
	}
    }



}


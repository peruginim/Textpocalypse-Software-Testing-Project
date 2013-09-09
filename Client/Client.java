
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Client implements ActionListener
{
	
	String currentUser;
	

	//Client Frame
	JFrame clientFrame;
	JPanel loginPanel, serverPanel, exitPanel;
	
	//Exit Panel
	JButton exitButton;
	
	//Login Panel
	JPanel loginCredPanel;
	JButton newUserButton, loginButton;
	JLabel loginIdText, passwordText;
	JTextField usernameTextBox;
	JPasswordField passwordTextBox;
	
	//Server Panel
	JLabel serverStatus;
	
	
	public static void main(String[] args)
	{
		new Client();
	}
	
	public Client()
	{
		createButtons();
		createLabels();
		createPanels();
		createFrame();
	}
	
	public void createFrame()
	{
		clientFrame = new JFrame("Blah");
		clientFrame.setLayout(new BorderLayout());
		clientFrame.setSize(750, 750);
		
		clientFrame.add(loginPanel, BorderLayout.CENTER);
		clientFrame.add(serverPanel, BorderLayout.EAST);
		clientFrame.add(exitPanel, BorderLayout.PAGE_END);
		
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
		
		//LoginPanel
		loginPanel = new JPanel(new GridLayout(3, 1));
		loginPanel.add(newUserButton);
		loginPanel.add(loginCredPanel);
		loginPanel.add(loginButton);
		
		//ServerPanel
		serverPanel = new JPanel(new GridLayout(2,1));
		serverPanel.add(serverStatus);
		
		//ExitPanel
		exitPanel = new JPanel(new GridLayout(0, 2));
		exitPanel.add(exitButton);
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
			//Take to new user screen
		}else if(pressed.equals(loginButton))
		{
			//Communicate with server to login
		}else if(pressed.equals(exitButton))
		{
			System.exit(1);
		}
	}
}


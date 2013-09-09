
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
		clientFrame = new JFrame("Textpocalypse: Purdue");
		clientFrame.setLayout(new BorderLayout());
		clientFrame.setSize(500, 200);
		
		clientFrame.add(loginPanel, BorderLayout.CENTER);
		clientFrame.add(serverPanel, BorderLayout.EAST);
		
		clientFrame.setResizable(false);
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
		loginPanel.add(newUserButton);
		loginPanel.add(loginButton);
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


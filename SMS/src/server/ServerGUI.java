package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import server.Server;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;


import javax.swing.border.MatteBorder;

public class ServerGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea serverConsole;
	private JLabel lblDbName;
	private JTextField textFieldDBName;
	private JLabel lblPort;
	private JTextField textFieldPort;
	private JButton btnConnect;
	private JTextField textFieldUser;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField textFieldPass;
    public Server server;
	public static JLabel lblNewLabel;
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setTitle("Server - Side");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,Color.WHITE));
		scrollPane.setBounds(10, 116, 414, 284);
		contentPane.add(scrollPane);
		
		lblDbName = new JLabel("DB Name:");
		lblDbName.setFont(new Font("Calibri",Font.PLAIN, 16));
		lblDbName.setBounds(10, 11, 80, 14);
		contentPane.add(lblDbName);
		
		textFieldDBName = new JTextField();
		textFieldDBName.setForeground(Color.BLACK);
		textFieldDBName.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		textFieldDBName.setBounds(85, 8, 125, 20);
		contentPane.add(textFieldDBName);
		textFieldDBName.setColumns(10);
		
		lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPort.setBounds(10, 38, 64, 14);
		contentPane.add(lblPort);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("5555");
		textFieldPort.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		textFieldPort.setForeground(Color.BLACK);
		textFieldPort.setBounds(85, 36, 125, 20);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		btnConnect = new JButton("Connect to DB");
		btnConnect.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnConnect.setBounds(85, 67, 125, 23);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbname, portid , usrname , pass;
					try{
					
							//Getting Current Date time
						  	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						  	LocalDateTime now = LocalDateTime.now();
				            dbname = textFieldDBName.getText();
				            portid = textFieldPort.getText();
				            usrname = textFieldUser.getText();
				            pass = new String (textFieldPass.getPassword());
				            if(!(dbname.isEmpty() || portid.isEmpty() || usrname.isEmpty() || pass.isEmpty()))
				            {
				            	if(server.initDBConnection(dbname,usrname, pass))
				            	{
				            		server.setPort(Integer.valueOf(textFieldPort.getText()));
				            		display("["+dtf.format(now)+"] SQL connection succeed");	
				            		try{
				            			server.listen(); //Start listening for connections
				            			display("["+dtf.format(now)+"] Server is listening on port " + textFieldPort.getText());
				            			textFieldDBName.setForeground(Color.GREEN);
				            			textFieldDBName.setEnabled(false);
				            			textFieldPort.setForeground(Color.GREEN);
				            			textFieldPort.setEnabled(false);
				            			textFieldUser.setForeground(Color.GREEN);
				            			textFieldUser.setEnabled(false);
				            			textFieldPass.setForeground(Color.GREEN);
				            			textFieldPass.setEnabled(false);
				            			btnConnect.setEnabled(false);
				            			}catch(Exception ex){display("["+dtf.format(now)+"] ERROR - Could not listen for clients!");}
				            	}
				        		else 
				        			display("["+dtf.format(now)+"] SQL connection failed.");
				            }
				            else
				            	display("You must Fill all the fields");
					}catch(Exception ex1){e.toString();};
			}
		});
		contentPane.add(btnConnect);
		
		textFieldUser = new JTextField();
		textFieldUser.setText("root");
		textFieldUser.setForeground(Color.BLACK);
		textFieldUser.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		textFieldUser.setBounds(294, 8, 130, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(220, 11, 80, 14);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(220, 38, 66, 14);
		contentPane.add(lblPassword);
		
		textFieldPass = new JPasswordField();
		textFieldPass.setForeground(Color.BLACK);
		textFieldPass.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		textFieldPass.setBounds(294, 35, 130, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setBounds(26, 101, 385, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIp.setBounds(220, 69, 24, 14);
		contentPane.add(lblIp);
		
		InetAddress ip;
		  try {

			ip = InetAddress.getLocalHost();
			JLabel lblnumber = new JLabel(ip.getHostAddress());
			lblnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblnumber.setBounds(264, 69, 120, 14);
			contentPane.add(lblnumber);
			
			serverConsole = new JTextArea();
			serverConsole.setBounds(10, 116, 414, 284);
			///contentPane.add(serverConsole);
			scrollPane.setViewportView(serverConsole);
			serverConsole.setEditable(false);

		  } catch (UnknownHostException e) {

			e.printStackTrace();

		  }
		
		
	}
	
	public  void display(String s)
	{
		serverConsole.append(s + "\n");
	}
	
	
	public Server getserver()
	{return this.server;}
	public static void main(String[] args) {
	    
	    ServerGUI servergui = new ServerGUI();
	    Server server = new Server(servergui,5555);
	    servergui.server = server;
	}
}

package Student;

import javax.swing.JPanel;



import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Entities.*; 

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class BlockingParentGui extends JPanel {
    private String pid; 
    private JTextField contenttextfield;
    private JTextField monthtxt;
    private JTextField daytxt;
    private JTextField yeartxt;
private int day;
private int year;
private int month;
   public JLabel lblMsg;
   public static int count=0; 
    /**
	 * Create the panel.
	 */
	public BlockingParentGui() {
		setBounds(135, 0, 289, 300);
		setLayout(null); 
		JPanel newpan=new JPanel(); // Creating an Empty Jpanel for the Cancel Button .
		newpan.setBounds(135, 0, 289, 300);
		JLabel lblParentsBlocking = new JLabel("Parents Blocking : ");
		lblParentsBlocking.setBounds(10, 11, 155, 31);
		add(lblParentsBlocking);
	      
		//System.out.printf("test date",date);
		 lblMsg = new JLabel("New label");
		lblMsg.setEnabled(false);
		lblMsg.setVisible(false);
		lblMsg.setBounds(27, 206, 252, 28);
		add(lblMsg);
	
		JButton btnBlockParent = new JButton("Block parent"); // i should do request
		
		btnBlockParent.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				 day=Integer.parseInt(daytxt.getText());
			      month=Integer.parseInt(monthtxt.getText());
			      year=Integer.parseInt(yeartxt.getText());
			      count++; 
				Date date=new Date(day,month,year);
				Message msg=new Message("INSERT INTO messages values("+count+","+Client.client.user.getID()+",blockingParent,blockmyparents,"+date+","+2+");",QTypes.blockparent); 
				 Client.client.handleMessageFromClientUI(msg);
		           
			}
		});
		btnBlockParent.setBounds(10, 245, 107, 31);
		add(btnBlockParent);
		
	
		
		contenttextfield = new JTextField();
		contenttextfield.setBounds(27, 96, 193, 110);
		add(contenttextfield);
		contenttextfield.setColumns(10);
		
		JLabel lblMessageContent = new JLabel("Message content:");
		lblMessageContent.setBounds(10, 53, 103, 14);
		add(lblMessageContent);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel);
				((HomeUI)Client.clientGUI).resizeHome();
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=newpan;  
			}
		});
		
		btnCancel.setBounds(177, 247, 102, 27);
		add(btnCancel);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(192, 19, 46, 14);
		add(lblDate);
		
		monthtxt = new JTextField();
		monthtxt.setBounds(192, 44, 28, 23);
		add(monthtxt);
		monthtxt.setColumns(10);
		
		daytxt = new JTextField();
		daytxt.setBounds(154, 44, 28, 23);
		add(daytxt);
		daytxt.setColumns(10);
		
		yeartxt = new JTextField();
		yeartxt.setBounds(230, 44, 36, 23);
		add(yeartxt);
		yeartxt.setColumns(10);

	}
	public void setlabel(String str)
	{
		this.lblMsg.setText(str);
		this.lblMsg.setVisible(true);
	}
}


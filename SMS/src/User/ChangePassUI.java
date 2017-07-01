package User;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassUI extends JPanel {
	
	private JPasswordField OldPassUser;
	private JPasswordField newPasswordUser;
	private JPasswordField RetryNewPass;
	private JLabel lblNewLabel_1; 
	private JLabel lblNewLabe2;
	private JLabel lblNewLabel_2;
	//JPanel panel = new JPanel();

	/**
	 * Create the panel.
	 */
	public ChangePassUI() {
		setLayout(null);
		setBounds(10, 59, 477, 300);

		JLabel label = new JLabel("Old Password:");
		label.setBounds(10, 51, 90, 14);
		add(label);
		

		OldPassUser = new JPasswordField();
		OldPassUser.setBounds(166, 48, 157, 20);
		add(OldPassUser);
		
		
		JLabel label_1 = new JLabel("New Password:");
		label_1.setBounds(10, 92, 106, 14);
		add(label_1);
		
		
		newPasswordUser = new JPasswordField();
		newPasswordUser.setBounds(166, 89, 157, 20);
		add(newPasswordUser);
		
		JLabel label_2 = new JLabel("Retry New Password:");
		label_2.setBounds(10, 135, 120, 14);
		add(label_2);
		
		lblNewLabel_2 = new JLabel("Password Changed Succesfully");
		lblNewLabel_2.setBounds(161, 172, 162, 14);
		add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		
		
		RetryNewPass = new JPasswordField();
		RetryNewPass.setBounds(166, 132, 157, 20);
		add(RetryNewPass);
		
		
		 lblNewLabel_1 = new JLabel("Password Doesnt Match");
		lblNewLabel_1.setBounds(328, 135, 146, 14);
		lblNewLabel_1.setVisible(false);
		add(lblNewLabel_1);
		
		
		 lblNewLabe2 = new JLabel("Check Again");
		lblNewLabe2.setBounds(344, 51, 70, 14);
		lblNewLabe2.setVisible(false);
		add(lblNewLabe2);
		JButton button = new JButton("Change Password");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 boolean flag=true;
					String oldpass = Client.user.getPassword();
					String newpass = new String(OldPassUser.getPassword());
					if(!(oldpass.equals(newpass)))
					{

						lblNewLabe2.setVisible(true);
						flag=false;
					}
					newpass = new String (newPasswordUser.getPassword());
					String newpassret = new String (RetryNewPass.getPassword());
					if(!(newpass.equals(newpassret)))
					{

						lblNewLabel_1.setVisible(true);
						flag=false;
						
					}
					
					if(flag){
						//update data base//
						String Query = new String("UPDATE users SET password="+newpass+" WHERE id="+Client.client.user.getID()+";");
						Client.user.setPassword(newpass);
						lblNewLabel_2.setVisible(true);
						Message msg= new Message(Query,QTypes.update);
						Client.client.handleMessageFromClientUI(msg);
			}
			}
		});
		button.setBounds(196, 213, 127, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		button_1.setBounds(325, 213, 89, 23);
		add(button_1);

	}
	
}
		

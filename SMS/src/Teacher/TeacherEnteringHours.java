package Teacher;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TeacherEnteringHours extends JPanel {
   public JLabel lblTeacherId;
   public JLabel lblWorkingHours;
   public JTextField textHours;
   public JLabel lblchours;
   public int maxhours=0;
	/**
	 * Create the panel.
	 */
	public TeacherEnteringHours() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
	    lblTeacherId = new JLabel("Teacher Name :");
		lblTeacherId.setBounds(26, 45, 99, 14);
		add(lblTeacherId);
		
	    lblWorkingHours = new JLabel("Working Hours:");
		lblWorkingHours.setBounds(26, 88, 99, 14);
		add(lblWorkingHours);
		
		textHours = new JTextField();
		textHours.setBounds(145, 85, 86, 20);
		add(textHours);
		textHours.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent evt) {
		    	  char char_input = evt.getKeyChar();
		    	  if(evt.getKeyCode() != KeyEvent.VK_ENTER)
		    	  {
		    		  /*
		    	    if (((char_input < '0') || (char_input > '9')) && (char_input != '\b'))
		    	    {
                       JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    	textHours.setText("");
		    	    }*/
		    	  }
		      }});
		textHours.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textHours.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(null, "You have to fill Working Hours","Empty inputs",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					float res = 0;
					res = Float.parseFloat(lblchours.getText());
					res+= Float.parseFloat(textHours.getText());
					if(res > maxhours)
					{
						JOptionPane.showMessageDialog(null, "Can't Update Hours more than max hours"," error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Message Msg=new Message("update teacher set currenthours="+res+" where user_id="+Client.client.user.getID() +";",QTypes.EnteringHours);
						Client.client.handleMessageFromClientUI(Msg);
					}
				}
			}
		});
		
		btnSave.setBounds(104, 222, 89, 23);
		add(btnSave);
		
		JLabel lbluser = new JLabel("");
		lbluser.setText(Client.user.getName());
		lbluser.setBounds(153, 45, 98, 14);
		add(lbluser);
		
		JLabel lblCurrentHours = new JLabel("Current Hours:");
		lblCurrentHours.setBounds(26, 126, 89, 14);
		
		add(lblCurrentHours);
		
		lblchours = new JLabel("");
		lblchours.setBounds(125, 120, 36, 24);
		add(lblchours);

	}
	
	public static void successadd(String string) {
		JOptionPane.showMessageDialog(null, string,"Success",JOptionPane.PLAIN_MESSAGE);
	}
}

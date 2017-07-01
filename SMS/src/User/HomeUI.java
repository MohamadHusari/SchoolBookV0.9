package User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;
import sysAdmin.sysAdminHomeUI;
import Teacher.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

public class HomeUI extends JFrame {

	public JPanel innerpanel = new JPanel();
	public JPanel contentPane;
	private final JButton btnLogout = new JButton(new ImageIcon("img\\homeui\\rsz_btnlogout.png"));
	private final JButton btnchangepass = new JButton(new ImageIcon("img\\homeui\\resetpassword.png"));
	private final JButton btngetinfo = new JButton(new ImageIcon("img\\homeui\\information.png"));
	private final JLabel lblWelcome = new JLabel("Welcome:");
	private final JLabel lblid = new JLabel("ID: ");

	public int getbtnX(){
		return btnLogout.getX();
	}
	public void setNewBounds(int diff){
		btnLogout.setBounds(btnLogout.getX()+diff,btnLogout.getY(),btnLogout.getWidth(),btnLogout.getHeight());
		btnchangepass.setBounds(btnchangepass.getX()+diff,btnchangepass.getY(),btnchangepass.getWidth(),btnchangepass.getHeight());
		btngetinfo.setBounds(btngetinfo.getX()+diff,btngetinfo.getY(),btngetinfo.getWidth(),btngetinfo.getHeight());

	}
	/**
	 * Create the frame.
	 */
	public HomeUI() {
		setBackground(java.awt.Color.WHITE);
		
		//setResizable(false);
		setTitle("School Mangement System");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 388);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBounds(0, 40, 300, height);
		contentPane.setBackground(java.awt.Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Client.client.sendToServer(new Message("UPDATE users SET Status = 1 WHERE ID="+Client.user.getID()+"/User: "+Client.user.getID(),QTypes.update));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(391, 11, 30, 30);
		
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		contentPane.add(btnLogout);
		lblWelcome.setBounds(10, 15, 222, 14);
		lblWelcome.setText(lblWelcome.getText()+" "+Client.user.getName());
		contentPane.add(lblWelcome);
		btnchangepass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(((HomeUI)Client.clientGUI).innerpanel.getClass().getSimpleName().equals("sysAdminHomeUI"))
				{
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new ChangePassUI();
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizesysAdminHome();
				}
				else if(((HomeUI)Client.clientGUI).innerpanel.getClass().getSimpleName().equals("TeacherHomeUI"))
				{
					((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new ChangePassUI();
            		((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
            		((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizeteacherhome();
				}
				else
				{
					((HomeUI)Client.clientGUI).contentPane.remove(((HomeUI)Client.clientGUI).innerpanel);
					((HomeUI)Client.clientGUI).innerpanel=new ChangePassUI();
					((HomeUI)Client.clientGUI).contentPane.add(((HomeUI)Client.clientGUI).innerpanel);
					//(Client.clientGUI).setVisible(true);
					((HomeUI)Client.clientGUI).resizeHome();
				}
			}
		});
		
		
		btnchangepass.setOpaque(false);
		btnchangepass.setContentAreaFilled(false);
		btnchangepass.setBorderPainted(false);
		btnchangepass.setBounds(358, 11, 30, 30);
		contentPane.add(btnchangepass);
		
		btngetinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client.client.sendToServer(new Message("Select date FROM login_history WHERE userid="+Client.user.getID()+";",QTypes.select));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btngetinfo.setOpaque(false);
		btngetinfo.setContentAreaFilled(false);
		btngetinfo.setBorderPainted(false);
		btngetinfo.setBounds(318, 11, 30, 30);
		contentPane.add(btngetinfo);
		
		
		lblid.setBounds(10, 34, 165, 14);
		lblid.setText(lblid.getText()+" "+Client.user.getID());
		contentPane.add(lblid);
		
		
		//innerpanel.setBounds(0, 60, 444, 211);
		
	}
	public void logout(){
		this.setVisible(false);
		Client.clientGUI.dispose();
		Client.clientGUI=new LoginUI();
		try {
			Client.client.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client.user=null;
		Client.clientGUI.setVisible(true);
	}
	public void ChangeJPanel(JPanel panel){
		((HomeUI)Client.clientGUI).contentPane.remove(((HomeUI)Client.clientGUI).innerpanel);
		((HomeUI)Client.clientGUI).innerpanel=panel;
		((HomeUI)Client.clientGUI).contentPane.add(((HomeUI)Client.clientGUI).innerpanel);
		((HomeUI)Client.clientGUI).resizeHome();
	}
	public void resizeHome(){
		int myY=0;
		int myX=0;
		int myplus = 0;
		//System.out.print( ((HomeUI)Client.clientGUI).getWidth() + " " + ((HomeUI)Client.clientGUI).innerpanel.getWidth()+ " 1\n");
		if(((HomeUI)Client.clientGUI).getWidth() >= ((HomeUI)Client.clientGUI).innerpanel.getWidth())
		{
		
			myY = ((HomeUI)Client.clientGUI).getWidth();
			myplus =(myY - ((HomeUI)Client.clientGUI).innerpanel.getWidth())-5;
			btnLogout.setBounds(btnLogout.getX() - myplus, 11, 30, 30);
			btnchangepass.setBounds(btnchangepass.getX() - myplus, 11, 30, 30);
			btngetinfo.setBounds(btngetinfo.getX() - myplus, 11, 30, 30);
			((HomeUI)Client.clientGUI).setBounds(((HomeUI)Client.clientGUI).getX()
	        		,((HomeUI)Client.clientGUI).getY()
	        		,(((HomeUI)Client.clientGUI).innerpanel.getWidth()+5)
	        		,(((HomeUI)Client.clientGUI).innerpanel.getHeight()
	        		+((HomeUI)Client.clientGUI).innerpanel.getY()+27));
		}
		else
		{
			myY = ((HomeUI)Client.clientGUI).innerpanel.getWidth();
			myplus = ((HomeUI)Client.clientGUI).innerpanel.getWidth() - ((HomeUI)Client.clientGUI).getWidth() + 5;
			btnLogout.setBounds(btnLogout.getX() + myplus, 11, 30, 30);
			btnchangepass.setBounds(btnchangepass.getX() + myplus, 11, 30, 30);
			btngetinfo.setBounds(btngetinfo.getX() + myplus, 11, 30, 30);
			((HomeUI)Client.clientGUI).setBounds(((HomeUI)Client.clientGUI).getX()
	        		,((HomeUI)Client.clientGUI).getY()
	        		,(myY +5)
	        		,(((HomeUI)Client.clientGUI).innerpanel.getHeight()
	        		+((HomeUI)Client.clientGUI).innerpanel.getY()+27));
		}
		/*if(((HomeUI)Client.clientGUI).innerpanel.getWidth() < ((HomeUI)Client.clientGUI).getWidth())
		{
			myX = ((HomeUI)Client.clientGUI).getWidth() - ((HomeUI)Client.clientGUI).innerpanel.getWidth();
		}
	/*	if(((HomeUI)Client.clientGUI).getHeight() >= ((HomeUI)Client.clientGUI).innerpanel.getHeight())
			myX = ((HomeUI)Client.clientGUI).getHeight();
		else
			myX = ((HomeUI)Client.clientGUI).innerpanel.getHeight();
		
		btnLogout.setBounds(btnLogout.getX() + myplus, 11, 30, 30);
		btnchangepass.setBounds(btnchangepass.getX() + myplus, 11, 30, 30);
		btngetinfo.setBounds(btngetinfo.getX() + myplus, 11, 30, 30);
		
		((HomeUI)Client.clientGUI).setBounds(((HomeUI)Client.clientGUI).getX()
        		,((HomeUI)Client.clientGUI).getY()
        		,(myY+5)
        		,(((HomeUI)Client.clientGUI).innerpanel.getHeight()
        		+((HomeUI)Client.clientGUI).innerpanel.getY()+27));*/
		//((HomeUI)Client.clientGUI).btnLogout.setBounds(btnLogout.getBounds().x + , 11, width, height);
        ((HomeUI)Client.clientGUI).setResizable(false);
    ((HomeUI)Client.clientGUI).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    Client.clientGUI.setVisible(true);
	}
}

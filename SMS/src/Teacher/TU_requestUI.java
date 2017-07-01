package Teacher;

import java.awt.Color;

import javax.swing.JPanel;

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

public class TU_requestUI extends JPanel {
	public JTable tblTrequest;
    public JLabel lblRequest = new JLabel("Request");
    public JLabel lblTeachUnit = new JLabel("Teach Unit");
    public JLabel lblAprove = new JLabel("Aprove");
    public JLabel lblType = new JLabel("Type");
	/**
	 * Create the panel.
	 */
	public TU_requestUI() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		//edit table
		tblTrequest = new JTable(){  
		      public boolean isCellEditable(int row,int column){  
		      Object o = getValueAt(row,column);  
		      if(o!=null) return false;  
		           return true;  
		         }  
		       }; 
		 tblTrequest.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		 tblTrequest.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 tblTrequest.setBounds(10, 31, 467, 178);
		 add(tblTrequest);
		JLabel lblNewLabel = new JLabel("Send request to SysAdmin to register to teach unit");
		lblNewLabel.setBounds(10, 258, 292, 14);
		add(lblNewLabel);
		
		JButton btnnewreq = new JButton("New Request");
		btnnewreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((TU_requestUI)((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new TU_newRequest();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunits1));
				//((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).index = 0;
				//Client.client.handleMessageFromClientUI(new Message("SELECT * FROM courses",QTypes.ShowinTXTallCourses));
				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizeteacherhome();
			}
		});
		btnnewreq.setBounds(314, 254, 135, 23);
		add(btnnewreq);
		lblRequest.setBounds(10, 6, 64, 14);
		add(lblRequest);
		lblTeachUnit.setBounds(103, 6, 70, 14);
		add(lblTeachUnit);
		lblAprove.setBounds(203, 6, 46, 14);
		add(lblAprove);
		lblType.setBounds(288, 6, 46, 14);
		add(lblType);
		
		JLabel lblSemster = new JLabel("Semster");
		lblSemster.setBounds(382, 6, 58, 14);
		add(lblSemster);
		
	
		
	}
}

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

public class TU_requestUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public TU_requestUI() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Send request to SysAdmin to register to teach unit");
		lblNewLabel.setBounds(10, 28, 292, 14);
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
		btnnewreq.setBounds(311, 24, 135, 23);
		add(btnnewreq);
	}
}

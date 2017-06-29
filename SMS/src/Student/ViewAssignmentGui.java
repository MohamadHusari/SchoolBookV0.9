package Student;

import javax.swing.JPanel;

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewAssignmentGui extends JPanel {

	JPanel newpan=new JPanel();
	JComboBox coursecombo;
	/**
	 * Create the panel.
	 */
	public ViewAssignmentGui() {
		 coursecombo = new JComboBox();
		setBounds(135, 0, 289, 300);
		setLayout(null);
		newpan.setBounds(135, 0, 289, 300);
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setBounds(10, 50, 101, 22);
		add(lblCourseName);
		
		JLabel lblAssignment = new JLabel("Assignment :");
		lblAssignment.setBounds(10, 102, 101, 22);
		add(lblAssignment);
		
		JButton btnView = new JButton("View");
		btnView.setBounds(10, 203, 89, 28);
		add(btnView);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel);
				((HomeUI)Client.clientGUI).resizeHome();
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=newpan;  
			}
		});
		btnCancel.setBounds(150, 203, 89, 28);
		add(btnCancel);
		
		
		
		coursecombo.addMouseListener(new MouseAdapter() {
			@Override // for course name:
			public void mouseClicked(MouseEvent arg0) {
			         Message msg=new Message("select distinct CourseId from assignemntcourse;",QTypes.courseassignment); 
			         Client.client.handleMessageFromClientUI(msg);
				
			}
		});
		coursecombo.setBounds(123, 50, 128, 22);
		add(coursecombo);
		
		JComboBox comboassignment = new JComboBox();
		comboassignment.setBounds(123, 102, 128, 22);
		add(comboassignment);
		
		JLabel lblViewAssignment = new JLabel("View Assignment :");
		lblViewAssignment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewAssignment.setBounds(10, 11, 182, 28);
		add(lblViewAssignment);
	}

}

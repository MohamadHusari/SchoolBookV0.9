package SchoolAdmin;

import javax.swing.JPanel;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReadOnly extends JPanel  {
	
	public JPanel Read_panel;
	
	private final JButton StudentCourse = new JButton("Student in Course");
	private final JButton TeacherInCourse = new JButton("Teacher in Course");
	private final JButton CoursInTU = new JButton("Courses and Teaching Units");
	private final JButton PreCourses = new JButton("Pre Courses");
	private final JButton ShowRequest = new JButton("Show Requests");
	
	
	public ReadOnly() {
		setLayout(null);
		
		StudentCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Q="SELECT * FROM class_students ;";
				Message Msg= new Message(Q,QTypes.readOnly);
				Client.client.handleMessageFromClientUI(Msg);
				Data Window=new Data();
				
			}
		});
		StudentCourse.setBounds(233, 80, 185, 23);
				add(StudentCourse);
				
				
				
		TeacherInCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Q="SELECT * FROM class_schedule ;";
				Message Msg= new Message(Q,QTypes.readOnly);
				Client.client.handleMessageFromClientUI(Msg);
				Data Window=new Data();

			}
		});
		TeacherInCourse.setBounds(233, 46, 185, 23);
		
		add(TeacherInCourse);
		CoursInTU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Q="SELECT * FROM courses ;";
				Message Msg= new Message(Q,QTypes.readOnly);
				Client.client.handleMessageFromClientUI(Msg);
				Data Window=new Data();

				
			}
		});
		CoursInTU.setBounds(233, 123, 178, 23);
		
		add(CoursInTU);
		
		
		
		PreCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Q="SELECT * FROM pre_courses ;";
				Message Msg= new Message(Q,QTypes.readOnly);
				Client.client.handleMessageFromClientUI(Msg);
				Data Window=new Data();
				
			
			}
		});
		
		
		PreCourses.setBounds(232, 167, 179, 23);
		
		add(PreCourses);
		ShowRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Q="SELECT * FROM requests ;";
				Message Msg= new Message(Q,QTypes.readOnly);
				Client.client.handleMessageFromClientUI(Msg);
				Data Window=new Data();
			}
		});
		ShowRequest.setBounds(233, 201, 178, 23);
		
		add(ShowRequest);
		
		
		
		
		
		
	}
}

package Teacher;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.jidesoft.swing.CheckBoxList;

import Entities.Course;
import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import com.jidesoft.swing.StyledLabel;

public class TeacherShowCFields extends JPanel {
	private JTextField txtcourseid;
	private JTextField txtcoursename;
	private JTextField txtstudyhours;
	private JTextField txtteachunit;
	public JScrollPane scrollPane;
	public JLabel lblstatus;
	public JButton btnback1;
	public JButton btnback2;
	public JButton btnnext1;
	public JButton btnnext2;
	
	public DefaultListModel<String> model ;
	public JList<String> list;
	public ArrayList<Course> allcourses =new ArrayList<Course>();
	public int index = 0;

	/**
	 * Create the panel.
	 */
	public TeacherShowCFields() {
		
		setBackground(Color.WHITE);
		setBounds(10, 59, 600, 350);
		setLayout(null);
		
		JLabel label_1 = new JLabel("Course id :");
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_1.setBounds(124, 38, 75, 20);
		add(label_1);
		
		txtcourseid = new JTextField();
		txtcourseid.setEditable(false);
		txtcourseid.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtcourseid.setBackground(Color.LIGHT_GRAY);
		txtcourseid.setBounds(198, 41, 118, 20);
		add(txtcourseid);
		
		txtcoursename = new JTextField();
		txtcoursename.setEditable(false);
		txtcoursename.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtcoursename.setBackground(Color.LIGHT_GRAY);
		txtcoursename.setBounds(198, 72, 118, 20);
		add(txtcoursename);
		
		JLabel label_2 = new JLabel("Name :");
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_2.setBounds(145, 71, 51, 18);
		add(label_2);
		
		JLabel label_4 = new JLabel("Teaching Unit :");
		label_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_4.setBounds(95, 104, 103, 20);
		add(label_4);
		
		JLabel label_6 = new JLabel("Learn Hours :");
		label_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_6.setBounds(103, 137, 93, 20);
		add(label_6);
		
		JLabel label_8 = new JLabel("Pre-Courses :");
		label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label_8.setBounds(94, 170, 93, 20);
		add(label_8);
		
		txtstudyhours = new JTextField();
		txtstudyhours.setEditable(false);
		txtstudyhours.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtstudyhours.setBackground(Color.LIGHT_GRAY);
		txtstudyhours.setBounds(198, 137, 63, 20);
		add(txtstudyhours);
		
		txtteachunit = new JTextField();
		txtteachunit.setEditable(false);
		txtteachunit.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtteachunit.setBackground(Color.LIGHT_GRAY);
		txtteachunit.setBounds(198, 105, 118, 20);
		add(txtteachunit);
		
		btnback2 = new JButton("");
		btnback2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnback1.setEnabled(false);
				btnback2.setEnabled(false);
				btnnext1.setEnabled(true);
				btnnext2.setEnabled(true);
				index= 0;
				txtcourseid.setText(allcourses.get(index).getCourse_ID());
				txtcoursename.setText(allcourses.get(index).getCourse_Name());
				txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
				txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
				String[] m = allcourses.get(index).getPreCourses();
				model.removeAllElements();
				if(m == null)
				{
					model.addElement("*empty* pre Courses");
				}
				else {
					for (String mm : m)
					{
						model.addElement(mm);
					}
				}
				list.updateUI();
			}
		});
		btnback2.setBorder(BorderFactory.createEmptyBorder());
		btnback2.setBackground(Color.WHITE);
		btnback2.setIcon(new ImageIcon("img\\sysAdmin\\back2.png"));
		btnback2.setFocusable(false);
		btnback2.setBounds(175, 299, 32, 26);
		add(btnback2);
		
		btnback1 = new JButton("");
		btnback1.setBorder(BorderFactory.createEmptyBorder());
		btnback1.setBackground(Color.WHITE);
		btnback1.setIcon(new ImageIcon("img\\sysAdmin\\back1.png"));
		btnback1.setFocusable(false);
		btnback1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnnext2.setEnabled(true);
				btnnext1.setEnabled(true);
				index--;
				if(index == 0)
				{
					btnback1.setEnabled(false);
					btnback2.setEnabled(false);
					txtcourseid.setText(allcourses.get(index).getCourse_ID());
					txtcoursename.setText(allcourses.get(index).getCourse_Name());
					txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
					txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
					String[] m = allcourses.get(index).getPreCourses();
					model.removeAllElements();
					if(m == null)
					{
						model.addElement("*empty* pre Courses");
					}
					else {
						for (String mm : m)
						{
							model.addElement(mm);
						}
					}
					list.updateUI();
				}
				else
				{
					txtcourseid.setText(allcourses.get(index).getCourse_ID());
					txtcoursename.setText(allcourses.get(index).getCourse_Name());
					txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
					txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
					String[] m = allcourses.get(index).getPreCourses();
					model.removeAllElements();
					if(m == null)
					{
						model.addElement("*empty* pre Courses");
					}
					else {
						for (String mm : m)
						{
							model.addElement(mm);
						}
					}
					list.updateUI();
				}

			}
		});
		btnback1.setBounds(217, 299, 32, 26);
		add(btnback1);
		
		btnnext1 = new JButton("");
		btnnext1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnback1.setEnabled(true);
				btnback2.setEnabled(true);
				index++;
				if(index == (allcourses.size()-1))
				{
					btnnext2.setEnabled(false);
					btnnext1.setEnabled(false);
					txtcourseid.setText(allcourses.get(index).getCourse_ID());
					txtcoursename.setText(allcourses.get(index).getCourse_Name());
					txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
					txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
					String[] m = allcourses.get(index).getPreCourses();
					model.removeAllElements();
					if(m == null)
					{
						model.addElement("*empty* pre Courses");
					}
					else {
						for (String mm : m)
						{
							model.addElement(mm);
						}
					}
					list.updateUI();
				}
				else
				{
					txtcourseid.setText(allcourses.get(index).getCourse_ID());
					txtcoursename.setText(allcourses.get(index).getCourse_Name());
					txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
					txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
					String[] m = allcourses.get(index).getPreCourses();
					model.removeAllElements();
					if(m == null)
					{
						model.addElement("*empty* pre Courses");
					}
					else {
						for (String mm : m)
						{
							model.addElement(mm);
						}
					}
					list.updateUI();
				}
			}
		});
		btnnext1.setBorder(BorderFactory.createEmptyBorder());
		btnnext1.setBackground(Color.WHITE);
		btnnext1.setIcon(new ImageIcon("img\\sysAdmin\\next1.png"));
		btnnext1.setFocusable(false);
		btnnext1.setBounds(261, 299, 32, 26);
		add(btnnext1);
		
		btnnext2 = new JButton("");
		btnnext2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnnext2.setEnabled(false);
				btnnext1.setEnabled(false);
				btnback1.setEnabled(true);
				btnback2.setEnabled(true);
				index= allcourses.size()-1;
				txtcourseid.setText(allcourses.get(index).getCourse_ID());
				txtcoursename.setText(allcourses.get(index).getCourse_Name());
				txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
				txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
				String[] m = allcourses.get(index).getPreCourses();
				model.removeAllElements();
				if(m == null)
				{
					model.addElement("*empty* pre Courses");
				}
				else {
					for (String mm : m)
					{
						model.addElement(mm);
					}
				}
				list.updateUI();
			}
		});
		btnnext2.setBorder(BorderFactory.createEmptyBorder());
		btnnext2.setBackground(Color.WHITE);
		btnnext2.setIcon(new ImageIcon("img\\sysAdmin\\next2.png"));
		btnnext2.setFocusable(false);
		btnnext2.setBounds(305, 299, 32, 26);
		add(btnnext2);
		
		JLabel lblSelectYourView = new JLabel("Select your view :");
		lblSelectYourView.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblSelectYourView.setBounds(390, 12, 124, 20);
		add(lblSelectYourView);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((TeacherShowCFields)((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new TeacherShowCoursesUI();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((TeacherShowCoursesUI)((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).StartmyUI(allcourses);
				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizeteacherhome();
			}
		});
		button_4.setBorder(BorderFactory.createEmptyBorder());
		button_4.setBackground(Color.WHITE);
		button_4.setFocusable(false);
		//button_4.setFocusPainted(false);
		//button_4.setContentAreaFilled(false);
		button_4.setIcon(new ImageIcon("img\\sysAdmin\\icons8-Grid.png"));
		button_4.setBounds(516, 10, 32, 34);
		add(button_4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 169, 137, 98);
		add(scrollPane);
		
		model = new DefaultListModel<String>();
	    list = new JList<String>(model);
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);
		
		lblstatus = new JLabel("* There no courses in database");
		lblstatus.setVisible(false);
		lblstatus.setVerticalAlignment(SwingConstants.TOP);
		lblstatus.setForeground(Color.RED);
		lblstatus.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblstatus.setBounds(95, 3, 275, 26);
		add(lblstatus);
		
		StyledLabel styledLabel = new StyledLabel();
		styledLabel.setBounds(435, 244, 1, 1);
		add(styledLabel);

	}
	
	public void StartmyUI (ArrayList<Course> allcourses)
	{
		if(index == 0)
		{
			btnback1.setEnabled(false);
			btnback2.setEnabled(false);
			txtcourseid.setText(allcourses.get(index).getCourse_ID());
			txtcoursename.setText(allcourses.get(index).getCourse_Name());
			txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
			txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
			String[] m = allcourses.get(index).getPreCourses();
			if(m == null)
			{
				model.addElement("*empty* pre Courses");
			}
			else {
				for (String mm : m)
				{
					model.addElement(mm);
				}
			}
			//list.updateUI();
		}
		else if (index == (allcourses.size()-1))
		{
			btnnext1.setEnabled(false);
			btnnext2.setEnabled(false);
			txtcourseid.setText(allcourses.get(index).getCourse_ID());
			txtcoursename.setText(allcourses.get(index).getCourse_Name());
			txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
			txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
			String[] m = allcourses.get(index).getPreCourses();
			if(m == null)
			{
				model.addElement("*empty* pre Courses");
			}
			else {
				for (String mm : m)
				{
					model.addElement(mm);
				}
			}
			//list.updateUI();
		}
		else if(allcourses.isEmpty())
		{
			lblstatus.setVisible(true);
		}
		else
		{
			txtcourseid.setText(allcourses.get(index).getCourse_ID());
			txtcoursename.setText(allcourses.get(index).getCourse_Name());
			txtteachunit.setText(allcourses.get(index).getTeachUnit_ID());
			txtstudyhours.setText(allcourses.get(index).getCourseStudyHours() +"");
			String[] m = allcourses.get(index).getPreCourses();
			if(m == null)
			{
				model.addElement("*empty* pre Courses");
			}
			else {
				for (String mm : m)
				{
					model.addElement(mm);
				}
			}
			//list.updateUI();
		}
	}
}

package sysAdmin;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jidesoft.swing.CheckBoxList;

import Entities.Course;
import Entities.TeachUnit;
import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

public class EditCourseNextUI extends JPanel {

	public JTextField txtcourseid;
	public JTextField txtcoursename;
	public JLabel lblLearnHours;
	public JTextField txtlearnhours1;
	public CheckBoxList list1;
	public JLabel lblPrecourses;
	public Choice teacunit_choice;
	public JTextField txtfirstid;
	public JScrollPane scrollPane;
	public JTextField txtlearnhours2;
	
	public ArrayList<Course> coursesedit = new ArrayList<Course>();
	public ArrayList<TeachUnit> TU = new ArrayList<TeachUnit>();
	public int index = 0;
	public int size = 0;
	public JButton btnsave;
	public JButton btmnext;
	public JButton btnmback;
	public JLabel lblstatus;
	private JButton btncancel;
	private JButton btncloseall;
	
	/**
	 * Create the panel.
	 */
	public EditCourseNextUI() {
		
		setBackground(Color.WHITE);
		setBounds(10, 59, 515, 350);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course id :");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(149, 22, 75, 20);
		add(lblNewLabel);
		
		txtcourseid = new JTextField();
		txtcourseid.setEditable(false);
		txtcourseid.setBackground(Color.LIGHT_GRAY);
		txtcourseid.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtcourseid.setBounds(285, 23, 93, 20);
		add(txtcourseid);
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblName.setBounds(170, 55, 51, 18);
		add(lblName);
		
		txtcoursename = new JTextField();
		txtcoursename.setBackground(Color.LIGHT_GRAY);
		txtcoursename.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtcoursename.setBounds(223, 56, 118, 20);
		add(txtcoursename);
		
		JLabel lblTeachingUnit = new JLabel("Teaching Unit :");
		lblTeachingUnit.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTeachingUnit.setBounds(120, 88, 103, 20);
		add(lblTeachingUnit);
		
		lblLearnHours = new JLabel("Learn Hours :");
		lblLearnHours.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblLearnHours.setBounds(128, 121, 93, 20);
		add(lblLearnHours);
		
		txtlearnhours1 = new JTextField();
		txtlearnhours1.setBackground(Color.LIGHT_GRAY);
		txtlearnhours1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtlearnhours1.setBounds(223, 121, 56, 20);
		add(txtlearnhours1);
		txtlearnhours1.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent evt) {
		    	  char char_input = evt.getKeyChar();
		    	  if(evt.getKeyCode() != KeyEvent.VK_ENTER)
		    	    if (((char_input < '0') || (char_input > '9')) && (char_input != '\b'))
		    	    {
		    	    	JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    	txtlearnhours1.setText("");
		    	  
		    	    }
		      }});
		
		lblPrecourses = new JLabel("Pre-Courses :");
		lblPrecourses.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblPrecourses.setBounds(119, 154, 93, 20);
		add(lblPrecourses);
		
		
		teacunit_choice = new Choice();
		teacunit_choice.setEnabled(false);
		teacunit_choice.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		teacunit_choice.setBounds(223, 86, 128, 20);
		add(teacunit_choice);
		
		teacunit_choice.addItemListener(new ItemListener(){
			@Override
	        public void itemStateChanged(ItemEvent ie)
	        {
				String s;
				s = teacunit_choice.getSelectedItem().substring(teacunit_choice.getSelectedItem().length()-3, teacunit_choice.getSelectedItem().length());
				//System.out.print(s);
				s = s.substring(0,s.length()-1);
				txtfirstid.setText(s);
				
	        }
	    });
		
		txtfirstid = new JTextField();
		txtfirstid.setEditable(false);
		txtfirstid.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtfirstid.setHorizontalAlignment(SwingConstants.CENTER);
		txtfirstid.setBackground(Color.LIGHT_GRAY);
		txtfirstid.setBounds(234, 23, 41, 20);
		add(txtfirstid);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 158, 150, 114);
		add(scrollPane);
		

		/*list1 = new CheckBoxList();
		scrollPane.setViewportView(list1);
		list1.setBackground(Color.LIGHT_GRAY);
		*/
		
		
		btnsave = new JButton("");
		btnsave.setBorder(BorderFactory.createEmptyBorder());
		btnsave.setIcon(new ImageIcon("img\\sysAdmin\\save.png"));
		btnsave.setBackground(Color.WHITE);
		btnsave.setFocusable(false);
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtcoursename.getText().isEmpty() || txtlearnhours1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "You need To fill all important (*) fields","Empty inputs",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
						Course course = new Course("", "", "", 0, null);
						course.setCourse_ID(txtfirstid.getText() + txtcourseid.getText());
						course.setCourse_Name(txtcoursename.getText());
						course.setCourseStudyHours(Float.parseFloat(txtlearnhours1.getText() +"." + txtlearnhours2.getText()));
						course.setTeachUnit_ID(txtfirstid.getText());
						Client.client.handleMessageFromClientUI(new Message("UPDATE courses SET course_name = '" + course.getCourse_Name()+"', course_studyhours= "+course.getCourseStudyHours() +" WHERE course_id ="+course.getCourse_ID(),QTypes.updateCourse));
						Client.client.handleMessageFromClientUI(new Message("DELETE FROM pre_courses WHERE course_id="+course.getCourse_ID(),QTypes.deletepreCourses));
						if(list1.getCheckBoxListSelectedIndex() != -1)
						{
							String[] prec = Arrays.copyOf(list1.getCheckBoxListSelectedValues(), list1.getCheckBoxListSelectedValues().length, String[].class);
							int i=0;
							for(String x : prec)
							{
								x = x.substring(0, 5);
								prec[i] = x;
								i++;
								Client.client.handleMessageFromClientUI(new Message("INSERT INTO pre_courses (course_id,pre_course)"
										+ "VALUES ('" + course.getCourse_ID() +"', '"+ x +"' )",QTypes.AddnewPreCourse));
							}
							course.setPreCourses(prec);
						}
				}
			}
		});
		btnsave.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnsave.setBounds(186, 284, 87, 34);
		add(btnsave);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_1.setBounds(159, 57, 10, 14);
		add(label_1);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_3.setBounds(116, 126, 10, 14);
		add(label_3);
		
		JLabel label_4 = new JLabel(".");
		label_4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_4.setBounds(285, 124, 10, 14);
		add(label_4);
		
		txtlearnhours2 = new JTextField();
		txtlearnhours2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtlearnhours2.setBackground(Color.LIGHT_GRAY);
		txtlearnhours2.setBounds(295, 121, 56, 20);
		add(txtlearnhours2);
		
		btmnext = new JButton("");
		btmnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				if(index == (coursesedit.size()-1))
				{
					btmnext.setEnabled(false);
					StartmyUI();
					btnmback.setEnabled(true);
				}
				else
				{
					btnmback.setEnabled(true);
					StartmyUI();
				}
			}
		});
		btmnext.setIcon(new ImageIcon("img\\sysAdmin\\mnext.jpg"));
		btmnext.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btmnext.setFocusable(false);
		btmnext.setBorder(BorderFactory.createEmptyBorder());
		btmnext.setBackground(Color.WHITE);
		btmnext.setBounds(458, 125, 34, 34);
		add(btmnext);
		
		btnmback = new JButton("");
		btnmback.setEnabled(false);
		btnmback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				if(index == 0)
				{
					btnmback.setEnabled(false);
					StartmyUI();
					btmnext.setEnabled(true);
				}
				else
				{
					btmnext.setEnabled(true);
					StartmyUI();
				}
			}
		});
		btnmback.setIcon(new ImageIcon("img\\sysAdmin\\mback.png"));
		btnmback.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnmback.setFocusable(false);
		btnmback.setBorder(BorderFactory.createEmptyBorder());
		btnmback.setBackground(Color.WHITE);
		btnmback.setBounds(46, 125, 34, 34);
		add(btnmback);
		
		lblstatus = new JLabel("*There no courses to edit");
		lblstatus.setVisible(false);
		lblstatus.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblstatus.setForeground(Color.RED);
		lblstatus.setBounds(13, 0, 183, 20);
		add(lblstatus);
		
		btncancel = new JButton("");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Removeonefromcoursesedit();
			}
		});
		btncancel.setIcon(new ImageIcon("img\\sysAdmin\\Cancel.png"));
		btncancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btncancel.setFocusable(false);
		btncancel.setBorder(BorderFactory.createEmptyBorder());
		btncancel.setBackground(Color.WHITE);
		btncancel.setBounds(285, 284, 80, 34);
		add(btncancel);
		
		btncloseall = new JButton("");
		btncloseall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are You Sure you wanna colse edit Panel", "Verify your option", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
		        	((EditCourseNextUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new WelcomeUI();
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).collapseAll(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).mytree.tree);
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).savemenu="";
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).pressed=false;
					//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizesysAdminHome();
		        }
			}
		});
		btncloseall.setIcon(new ImageIcon("img\\sysAdmin\\close-icon-31.png"));
		btncloseall.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btncloseall.setFocusable(false);
		btncloseall.setBorder(BorderFactory.createEmptyBorder());
		btncloseall.setBackground(Color.WHITE);
		btncloseall.setBounds(426, 0, 87, 69);
		add(btncloseall);
		txtlearnhours2.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent evt) {
		    	  char char_input = evt.getKeyChar();
		    	  if(evt.getKeyCode() != KeyEvent.VK_ENTER)
		    	    if (((char_input < '0') || (char_input > '9')) && (char_input != '\b'))
		    	    {
		    	    	JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    	txtlearnhours2.setText("");
		    	  
		    	    }
		      }});

	}
	
	public void StartmyUI ()
	{
		if(coursesedit.size() == 1)
		{
			btmnext.setEnabled(false);
			btnmback.setEnabled(false);
		}
		txtfirstid.setText(coursesedit.get(index).getTeachUnit_ID());
		txtcourseid.setText(coursesedit.get(index).getCourse_ID().substring(2, 5));
		txtcoursename.setText(coursesedit.get(index).getCourse_Name());
		for(int i = 0 ; i< TU.size() ;i++)
		{
			if(coursesedit.get(index).getTeachUnit_ID().equals(TU.get(i).getTeachUnit_ID()))
				teacunit_choice.select(TU.get(i).getTeachUnit_Name() + " '" + TU.get(i).getTeachUnit_ID() +"'");
		}
		//txtlearnhours1.setText(coursesedit.get(index).getCourseStudyHours()+"");
		String str = ""+coursesedit.get(index).getCourseStudyHours();
		if (str.contains(".")) {
			String[] parts = str.split(Pattern.quote("."));
			txtlearnhours1.setText(parts[0]);
			txtlearnhours2.setText(parts[1]);
		} else {
			txtlearnhours1.setText(str);
		}
		updatemylist(coursesedit.get(index).getCourse_ID());
		list1.selectNone();
		String[] m = coursesedit.get(index).getPreCourses();
		if(m != null)
		{
			for (String mm : m)
			{
       			Client.client.handleMessageFromClientUI(new Message("SELECT course_name FROM courses WHERE course_id=" + mm,QTypes.Getspaceficcoursenameforedit));
			}
		}
		//String str = coursesedit.get(index).getCourseStudyHours();
		
	}
	
	public void updatemylist(String delc)
	{
		list1 = new CheckBoxList();
		scrollPane.setViewportView(list1);
		list1.setBackground(Color.LIGHT_GRAY);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(list1, popupMenu);
		popupMenu.setBounds(0, 0, 137, 28);
        // New File menu item
		JMenuItem menuItem = new JMenuItem("Select by ID...",
                new ImageIcon("img/sysAdmin/Search-menu.png"));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	//JOptionPane pane = new JOptionPane("Enter valid Course id : ",JOptionPane.QUESTION_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
            	String hold = "";
                int number = -1;

                while (number == -1) {
                    hold = JOptionPane.showInputDialog("Enter valid Course id ( XXXXX - Size 5 ) :");
                    if(hold != null)
                    {
                    	if(hold.length() == 5)
                    	{
                    		try {
                   			Integer.parseInt(hold);
                   			number = 1;
                   			Client.client.handleMessageFromClientUI(new Message("SELECT course_name FROM courses WHERE course_id=" + hold,QTypes.Getspaceficcoursenameforedit));
                   			} catch (NumberFormatException ex){
                   				number = -1;
                   				JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
                   			}
                    	}
                    	else
                    		JOptionPane.showMessageDialog(null, "Course id Length must be 5 !","Invalid Input",JOptionPane.ERROR_MESSAGE);	
                    }
                    else
                	break;
                }
            }
        });
        popupMenu.add(menuItem);
		Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses WHERE course_id <>" +delc,QTypes.GetAllCoursestoButinChechboxlist));
	}
	
	public void Removeonefromcoursesedit()
	{
		coursesedit.remove(index);
		if(coursesedit.size() == 0)
		{
			btncancel.setEnabled(false);
			btnsave.setEnabled(false);
			btmnext.setEnabled(false);
			btnmback.setEnabled(false);
			lblstatus.setVisible(true);
			/*empty all*/
			txtfirstid.setText("");
			txtfirstid.setEditable(false);
			txtcourseid.setText("");
			txtcourseid.setEditable(false);
			txtcoursename.setText("");
			txtcoursename.setEditable(false);
			teacunit_choice.removeAll();
			teacunit_choice.setEnabled(false);
			txtlearnhours1.setText("");
			txtlearnhours1.setEditable(false);
			txtlearnhours2.setText("");
			txtlearnhours2.setEditable(false);
			scrollPane.setEnabled(false);
			list1.setEnabled(false);
			scrollPane.getViewport().removeAll();
		}
		else
		{
			if(btmnext.isEnabled())
			{
				if(index == (coursesedit.size()-1))
				{
					btmnext.setEnabled(false);
					StartmyUI();
					if(index !=0)
						btnmback.setEnabled(true);
				}
				else
				{
					if(index !=0)
						btnmback.setEnabled(true);
					StartmyUI();
				}
			}
			else
			{
				if(btnmback.isEnabled())
				{
					index--;
					if(index == 0)
					{
						btnmback.setEnabled(false);
						StartmyUI();
						if(coursesedit.size() > 1 && index !=( coursesedit.size()-1))
							btmnext.setEnabled(true);
					}
					else
					{
						if(coursesedit.size() > 1 && index !=( coursesedit.size()-1))
							btmnext.setEnabled(true);
						StartmyUI();
					}
				}
			}
		}
	}
	
	public static void showerrdilog(String string) {
			JOptionPane.showMessageDialog(null, string,"Invalid Input",JOptionPane.ERROR_MESSAGE);
	} 
	
	public static void successadd(String string) {
		JOptionPane.showMessageDialog(null, string,"Success",JOptionPane.PLAIN_MESSAGE);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

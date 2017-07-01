package sysAdmin;

import com.jidesoft.swing.CheckBoxList;

import Entities.Course;
import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;



public class AddCourseUI extends JPanel {
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

	/**
	 * Create the panel.
	 */
	public AddCourseUI() {
		
		
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course id :");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(100, 22, 75, 20);
		add(lblNewLabel);
		
		txtcourseid = new JTextField();
		txtcourseid.setBackground(Color.LIGHT_GRAY);
		txtcourseid.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtcourseid.setBounds(236, 23, 93, 20);
		add(txtcourseid);
		txtcourseid.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent evt) {
		    	  char char_input = evt.getKeyChar();
		    	  if(evt.getKeyCode() != KeyEvent.VK_ENTER)
		    	  {
		    	    if (((char_input < '0') || (char_input > '9')) && (char_input != '\b'))
		    	    {
		    	    	//txtcourseid.setEnabled(false);
		    	    	/*Object[] buttons = {"OK"};
		    	    	int res = JOptionPane.showOptionDialog(null,
		    	    	                   "Number only!","Invalid Input",
		    	    	                   JOptionPane.OK_OPTION,
		    	    	                   JOptionPane.ERROR_MESSAGE, null, buttons , buttons[0]);*/
		    	    	JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    	txtcourseid.setText("");
		    	        /*txtcourseid.setEnabled(true);
		    	        txtcourseid.requestFocusInWindow();*/
		    	    	//System.out.print(res);
		    	        //int res = JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    }
		    	    if(txtcourseid.getText().length() > 3 )
		    	    {
		    	    	//txtcourseid.setEnabled(false);
		    	    	JOptionPane.showMessageDialog(null, "there is limit for the length - >  ###!","Invalid Input",JOptionPane.ERROR_MESSAGE);
		    	    	txtcourseid.setText(txtcourseid.getText().substring(0, 3));
		    	    	/*txtcourseid.setEnabled(true);
		    	    	txtcourseid.requestFocusInWindow();*/
		    	    }
		    	    
		    	  }
		      }});
		
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblName.setBounds(121, 55, 51, 18);
		add(lblName);
		
		txtcoursename = new JTextField();
		txtcoursename.setBackground(Color.LIGHT_GRAY);
		txtcoursename.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtcoursename.setBounds(174, 56, 118, 20);
		add(txtcoursename);
		
		JLabel lblTeachingUnit = new JLabel("Teaching Unit :");
		lblTeachingUnit.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTeachingUnit.setBounds(71, 88, 103, 20);
		add(lblTeachingUnit);
		
		lblLearnHours = new JLabel("Learn Hours :");
		lblLearnHours.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblLearnHours.setBounds(79, 121, 93, 20);
		add(lblLearnHours);
		
		txtlearnhours1 = new JTextField();
		txtlearnhours1.setBackground(Color.LIGHT_GRAY);
		txtlearnhours1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtlearnhours1.setBounds(174, 121, 56, 20);
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
		lblPrecourses.setBounds(70, 154, 93, 20);
		add(lblPrecourses);
		
		
		teacunit_choice = new Choice();
		teacunit_choice.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		teacunit_choice.setBounds(174, 86, 128, 20);
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
		txtfirstid.setBounds(185, 23, 41, 20);
		add(txtfirstid);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 158, 150, 114);
		add(scrollPane);
		

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
                   			Client.client.handleMessageFromClientUI(new Message("SELECT course_name FROM courses WHERE course_id=" + hold,QTypes.Getspaceficcoursename));
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
		
		JButton submitbtn = new JButton("");
		submitbtn.setBorder(BorderFactory.createEmptyBorder());
		submitbtn.setIcon(new ImageIcon("img\\sysAdmin\\submit2.png"));
		submitbtn.setBackground(Color.WHITE);
		submitbtn.setFocusable(false);
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtcourseid.getText().isEmpty() || txtcoursename.getText().isEmpty() || txtlearnhours1.getText().isEmpty())
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
						Client.client.handleMessageFromClientUI(new Message("INSERT INTO courses (course_id,course_name,teachunit_id,course_studyhours)"
							+ "VALUES ('" + course.getCourse_ID() +"'"
							 + ", '" + txtcoursename.getText() +"'"
							+", '" +course.getTeachUnit_ID() + "', " +course.getCourseStudyHours() + " )"
							,QTypes.AddnewCourse));
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
						//Client.client.handleMessageFromClientUI(new Message(course,QTypes.AddnewCourse));
						//course.setPreCourses(preCourses);
						//Client.client.handleMessageFromClientUI(new Message("SELECT course_name FROM courses WHERE course_id=" + hold,QTypes.Getspaceficcoursename));
					/*else
					{
						JOptionPane.showMessageDialog(null, "The course id is already exists in DataBase !","Invalid Input",JOptionPane.ERROR_MESSAGE);
						txtcourseid.setText("");
						txtcourseid.requestFocusInWindow();
		    	    	/*txtcourseid.setEnabled(true);
		    	    	txtcourseid.requestFocusInWindow();
					}*/
			}
		});
		submitbtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		submitbtn.setBounds(382, 214, 87, 34);
		add(submitbtn);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label.setBounds(87, 27, 10, 14);
		add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_1.setBounds(110, 59, 10, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_2.setBounds(58, 93, 10, 14);
		add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_3.setBounds(67, 126, 10, 14);
		add(label_3);
		
		JLabel label_4 = new JLabel(".");
		label_4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		label_4.setBounds(236, 124, 10, 14);
		add(label_4);
		
		txtlearnhours2 = new JTextField();
		txtlearnhours2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtlearnhours2.setBackground(Color.LIGHT_GRAY);
		txtlearnhours2.setBounds(246, 121, 56, 20);
		add(txtlearnhours2);
		
		JButton btnclose = new JButton("");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are You Sure you wanna colse this Panel", "Verify your option", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
		        	((AddCourseUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
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
		btnclose.setIcon(new ImageIcon("img\\sysAdmin\\close-icon-31.png"));
		btnclose.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnclose.setFocusable(false);
		btnclose.setBorder(BorderFactory.createEmptyBorder());
		btnclose.setBackground(Color.WHITE);
		btnclose.setBounds(411, 0, 87, 69);
		add(btnclose);
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
	private static int stringToInt(String string) {
		if(string.length() > 5)
		{
			JOptionPane.showMessageDialog(null, "The size need to be 5!","Invalid Input",JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	    try {
	        return Integer.parseInt(string);
	    }
	    catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(null, "Number only!","Invalid Input",JOptionPane.ERROR_MESSAGE);
	        return -1;
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

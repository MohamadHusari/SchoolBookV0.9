package sysAdmin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.jidesoft.swing.SimpleScrollPane;

import Entities.Course;
import OurMessage.Message;
import OurMessage.QTypes;
import OurMessage.Request;
import User.HomeUI;
import chat.Client;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.jidesoft.swing.JideScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class ShowAllCoursesUI extends JPanel {
	public JTable table;
	public DefaultTableModel model;
	public SimpleScrollPane jideScrollPane;

	/**
	 * Create the panel.
	 */
    
	public ShowAllCoursesUI() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 600, 350);
		setLayout(null);
		
		JLabel label = new JLabel("Select your view :");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		label.setBounds(390, 12, 124, 20);
		add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((ShowAllCoursesUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
				((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new ShowAllCoursesFieldsUI();
				((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).index = 0;
				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM courses",QTypes.ShowinTXTallCourses));
				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
				((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizesysAdminHome();
			}
		});
		button.setIcon(new ImageIcon("img\\sysAdmin\\icons8-List-48.png"));
		button.setFocusable(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBackground(Color.WHITE);
		button.setBounds(516, 10, 32, 34);
		add(button);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(27, 55, 55, 16);
		add(lblCourseId);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(144, 55, 77, 16);
		add(lblCourseName);
		
		JLabel lblPreCourses = new JLabel("Pre-Courses");
		lblPreCourses.setBounds(468, 55, 86, 16);
		add(lblPreCourses);
		
		JLabel lblTeachUnit = new JLabel("Teach Unit");
		lblTeachUnit.setBounds(274, 55, 68, 16);
		add(lblTeachUnit);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(360, 55, 44, 16);
		add(lblHours);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("img\\sysAdmin\\Untitled-2.png"));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(12, 52, 576, 25);
		add(label_1);
		
		
	}
	public void StartmyUI (ArrayList<Course> allcourses)
	{
		model = new DefaultTableModel() {
			public Class<?> getColumnClass (int column)
			{
				switch(column)
				{
				case 0 : return String.class;
				case 1 : return String.class;
				case 2 : return String.class;
				case 3 : return Float.class;
				case 4 : return String.class;
				case 5: return Boolean.class;
				default :
					return Boolean.class;
				}
			}
			@Override
			public boolean isCellEditable(int row, int col) {
			     switch (col) {
			         case 0 : return false;
			         case 1 : return false;
			         case 2 : return false;
			         case 3 : return false;
			         case 4 : return false;
			         case 5 : return true;
			         default:
			             return true;
			      }
			}
		};
		table = new JTable();
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setBackground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		model.addColumn("Course Id");
		model.addColumn("Course Name");
		model.addColumn("Teach unit");
		model.addColumn("Study Hours");
		model.addColumn("Pre-Courses");
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(175);
		
		jideScrollPane = new SimpleScrollPane();
		jideScrollPane.setBackground(Color.LIGHT_GRAY);
		jideScrollPane.setHorizontalScrollBarPolicy(SimpleScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jideScrollPane.setVerticalScrollBarPolicy(SimpleScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jideScrollPane.setBounds(12, 75, 576, 98);
		add(jideScrollPane);
		jideScrollPane.setViewportView(table);
		jideScrollPane.getViewport().setBackground(Color.white);
		
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		popupMenu.setBounds(0, 0, 137, 28);
        // New File menu item
		JMenuItem menuItem = new JMenuItem("See more details..",
                new ImageIcon("img/sysAdmin/view-details.png"));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	int row = table.getSelectedRow();
            	if(row == -1)
            	{
            		JOptionPane.showMessageDialog(null, "There is No Selected row!","Invalid !!",JOptionPane.ERROR_MESSAGE);
            	}
            	else
            	{
            		//JOptionPane pane = new JOptionPane("Enter valid Course id : ",JOptionPane.QUESTION_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		((ShowAllCoursesUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new ShowAllCoursesFieldsUI();
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
            		((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).index = row;
            		Client.client.handleMessageFromClientUI(new Message("SELECT * FROM courses",QTypes.ShowinTXTallCourses));
            		//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
            		((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizesysAdminHome();
            	}
				
            }
        });
        popupMenu.add(menuItem);
		
		
		String s = "";
		for(int i=0; i<allcourses.size();i++)
		{
			model.addRow(new Object[0]);
			model.setValueAt(allcourses.get(i).getCourse_ID(), i, 0);
			model.setValueAt(allcourses.get(i).getCourse_Name(), i, 1);
			model.setValueAt(allcourses.get(i).getTeachUnit_ID(), i, 2);
			model.setValueAt(allcourses.get(i).getCourseStudyHours(), i, 3);
			if(allcourses.get(i).getPreCourses() == null)
				model.setValueAt("*empty*", i, 4);
			else
			{
				s = "";
				String [] m1 = allcourses.get(i).getPreCourses();
				s = m1[0];
				for(int j= 1; j < m1.length; j++)
				{
					s += ", " + m1[j];
					if (s.length() > 15)
					{
						s+= "....";
						break;
					}
				}
				model.setValueAt(s, i, 4);
			}
		}
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

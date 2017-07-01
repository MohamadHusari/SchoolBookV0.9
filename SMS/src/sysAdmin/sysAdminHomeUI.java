package sysAdmin;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.CompoundBorder;

public class sysAdminHomeUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public sysAdminMenuUI mytree = new sysAdminMenuUI();
	public JPanel innerpanel = new JPanel();
	public JPanel contentPane;
	public Boolean pressed = false;
	public String savemenu = "";
	
	
	public sysAdminHomeUI() {
		setBackground(Color.WHITE);
		setBounds(2, 59, 600, 350);
		setLayout(null);
		
		JPanel panel = new JPanel();
		//panel.setBorder(null);
		panel.setBounds(0, 0, 173, 350);
		add(panel);
		panel.add(mytree.makeUI());
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		contentPane = new JPanel();
		contentPane.setBounds(172, 0, 428, 350);
		contentPane.setBackground(java.awt.Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		//panel.addInputMethodListener(l);
		
		mytree.tree.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		    		    TreePath tp = mytree.tree.getPathForLocation(me.getX(), me.getY());
		    		    if(tp != null)
		    		    {
		    		    	String s = tp.getLastPathComponent().toString();
		    		    	if(!(s.equals("Courses") || s.equals("SubCategory 2")))
		    		    	{
		    		    		if(pressed == true && !savemenu.equals(s))
		    		    		{
		    		    		    int option=JOptionPane.showConfirmDialog(null, "Are you sure you wanna switch to '"+ s + "' ?", "Switch MENU", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		    		    		        if(option==JOptionPane.YES_OPTION){
		    		    		            pressed = false;
		    		    		        }else{
		    		    		        	pressed=true;
		    		    		        }
		    		    		}
		    		    		if(pressed == false)
		    		    		{
		    		    			if(s.equals("Add New Course"))
		    		    			{
		    		    				savemenu = new String(s);
		    		    				pressed = true;
		    		    				contentPane.remove(innerpanel);
		    		    				innerpanel.removeAll();
		    		    				innerpanel = new AddCourseUI();
		    		    				contentPane.add(innerpanel);
		    		    				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunits));
		    		    				Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
		    		    				resizesysAdminHome();
		    		    				//contentPane.revalidate();
		    		    				//contentPane.repaint();
		    		    				//lblNewLabel.setText(tp.getLastPathComponent().toString());
		    		    			}
		    		    			if(s.equals("Show All Courses"))
		    		    			{
		    		    				savemenu = new String(s);
		    		    				pressed = true;
		    		    				contentPane.remove(innerpanel);
		    		    				innerpanel.removeAll();
		    		    				innerpanel = new ShowAllCoursesFieldsUI();
		    		    				contentPane.add(innerpanel);
		    		    				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM courses",QTypes.ShowinTXTallCourses));
		    		    				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
		    		    				resizesysAdminHome();
		    		    				//contentPane.revalidate();
		    		    				//contentPane.repaint();
		    		    				//lblNewLabel.setText(tp.getLastPathComponent().toString());
		    		    			}
		    		    			if(s.equals("Edit Course"))
		    		    			{
		    		    				savemenu = new String(s);
		    		    				pressed = true;
		    		    				contentPane.remove(innerpanel);
		    		    				innerpanel.removeAll();
		    		    				innerpanel = new EditCourseUI();
		    		    				contentPane.add(innerpanel);
		    		    				Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids1));
		    		    				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
		    		    				resizesysAdminHome();
		    		    				//contentPane.revalidate();
		    		    				//contentPane.repaint();
		    		    				//lblNewLabel.setText(tp.getLastPathComponent().toString());
		    		    			}
		    		    			if(s.equals("Show Teach Units"))
		    		    			{
		    		    				savemenu = new String(s);
		    		    				pressed = true;
		    		    				contentPane.remove(innerpanel);
		    		    				innerpanel.removeAll();
		    		    				innerpanel = new ShowTeachUnitUI();
		    		    				contentPane.add(innerpanel);
		    		    				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunitsUI));
		    		    				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
		    		    				resizesysAdminHome();
		    		    				//contentPane.revalidate();
		    		    				//contentPane.repaint();
		    		    				//lblNewLabel.setText(tp.getLastPathComponent().toString());
		    		    			}
		    		    			if(s.equals("Show Requests"))
		    		    			{
		    		    				savemenu = new String(s);
		    		    				pressed = true;
		    		    				contentPane.remove(innerpanel);
		    		    				innerpanel.removeAll();
		    		    				innerpanel = new ShowRequestsUI();
		    		    				contentPane.add(innerpanel);
		    		    				//Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunitsUI));
		    		    				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
		    		    				resizesysAdminHome();
		    		    				//contentPane.revalidate();
		    		    				//contentPane.repaint();
		    		    				//lblNewLabel.setText(tp.getLastPathComponent().toString());
		    		    			}
		    		    			
		    		    			
		    		    		}
		    		    	}
		    		    }
		        }
		      });

	}
	
	public void resizesysAdminHome(){
		int mypanelX = 0;
		int mypanelY = 0;
		/*if ( innerpanel.getWidth() >= contentPane.getWidth())
			mypanelX = innerpanel.getWidth();
		else 
			mypanelX = contentPane.getWidth();
		if(innerpanel.getHeight() >= contentPane.getHeight() )
			mypanelY = innerpanel.getHeight();
		else
			mypanelY = contentPane.getHeight();*/
		
		mypanelX = innerpanel.getWidth();
		mypanelY = innerpanel.getHeight();
		//System.out.print(mypanelY +"  se\n");
			//myorgpanel = 172 + mypanelY;
		contentPane.setBounds(172, 0, mypanelX, mypanelY);
		((HomeUI)Client.clientGUI).innerpanel.setBounds(((HomeUI)Client.clientGUI).innerpanel.getX(), 
				((HomeUI)Client.clientGUI).innerpanel.getY() ,
				(mypanelX +  173), mypanelY);
		((HomeUI)Client.clientGUI).resizeHome();
		contentPane.revalidate();
		contentPane.repaint();
		//((HomeUI)Client.clientGUI).innerpanel.setBounds(((HomeUI)Client.clientGUI).innerpanel.getBounds().x, ((HomeUI)Client.clientGUI).innerpanel.getBounds().y, ((HomeUI)Client.clientGUI).innerpanel.getWidth() + myplus, ((HomeUI)Client.clientGUI).innerpanel.getHeight());
		//((HomeUI)Client.clientGUI).resizeHome();
		/*
		if(innerpanel.getWidth() + mytree.tree.getWidth() >= ((HomeUI)Client.clientGUI).innerpanel.getWidth() )
		{
			myY = innerpanel.getWidth() + mytree.tree.getWidth();
		}
		else
			myY = ((HomeUI)Client.clientGUI).innerpanel.getWidth();
		
		if(((HomeUI)Client.clientGUI).getWidth() >= ((HomeUI)Client.clientGUI).innerpanel.getWidth())
			myY = ((HomeUI)Client.clientGUI).getWidth();
		else
		{
			myY = ((HomeUI)Client.clientGUI).innerpanel.getWidth();
			myplus = ((HomeUI)Client.clientGUI).innerpanel.getWidth() - ((HomeUI)Client.clientGUI).getWidth();
		}
		btnLogout.setBounds(391 + myplus, 11, 30, 30);
		btnchangepass.setBounds(358 + myplus, 11, 30, 30);
		btngetinfo.setBounds(318 + myplus, 11, 30, 30);
		((HomeUI)Client.clientGUI).setBounds(((HomeUI)Client.clientGUI).getX()
        		,((HomeUI)Client.clientGUI).getY()
        		,myY
        		,((HomeUI)Client.clientGUI).innerpanel.getHeight()
        		+((HomeUI)Client.clientGUI).innerpanel.getY()+40);
		//((HomeUI)Client.clientGUI).btnLogout.setBounds(btnLogout.getBounds().x + , 11, width, height);
        ((HomeUI)Client.clientGUI).setResizable(false);
    ((HomeUI)Client.clientGUI).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    Client.clientGUI.setVisible(true);
    */
	}
	  public void expandAll(JTree tree) {
		    int row = 0;
		    while (row < tree.getRowCount()) {
		      tree.expandRow(row);
		      row++;
		      }
	   }


	  public void expandToLast(JTree tree) {
		    // expand to the last leaf from the root
		    DefaultMutableTreeNode  root;
		    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		    tree.scrollPathToVisible(new TreePath(root.getLastLeaf().getPath()));
	   }
	  public void collapseAll(JTree tree) {
		    int row = tree.getRowCount() - 1;
		    while (row >= 0) {
		      tree.collapseRow(row);
		      row--;
		      }
	   }
	
}

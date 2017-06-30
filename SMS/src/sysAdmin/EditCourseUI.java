package sysAdmin;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import com.jidesoft.swing.CheckBoxList;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class EditCourseUI extends JPanel {

	public JScrollPane scrollPane;
	public CheckBoxList list1;
	public JButton btnnext;
	public JLabel lblstatus;
	private JLabel lblselectc;
	
	/**
	 * Create the panel.
	 */
	public EditCourseUI() {
		
		setBackground(Color.WHITE);
		setBounds(10, 59, 442, 350);
		setLayout(null);
		
		lblselectc = new JLabel("");
        lblselectc.setIcon(new ImageIcon("D:\\SMS\\SMS\\img\\sysAdmin\\select_c4.png"));
        lblselectc.setBounds(28, 12, 205, 37);
        add(lblselectc);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 61, 218, 196);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		add(scrollPane);
		list1 = new CheckBoxList();
		list1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		scrollPane.setViewportView(list1);
		list1.setBackground(Color.WHITE);
		
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
                   			Client.client.handleMessageFromClientUI(new Message("SELECT course_name FROM courses WHERE course_id=" + hold,QTypes.Getspaceficcoursename1));
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
        
        btnnext = new JButton("");
        btnnext.setBorder(BorderFactory.createEmptyBorder());
        btnnext.setIcon(new ImageIcon("img\\sysAdmin\\Next.png"));
        btnnext.setBackground(Color.WHITE);
        btnnext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        
        btnnext.setBounds(308, 140, 73, 32);
        add(btnnext);
        
        lblstatus = new JLabel("* There no courses in database");
        lblstatus.setVisible(false);
        lblstatus.setForeground(Color.RED);
        lblstatus.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lblstatus.setBounds(12, 293, 240, 26);
        add(lblstatus);
	}
	
	
	public static void showerrdilog(String string) {
		JOptionPane.showMessageDialog(null, string,"Invalid Input",JOptionPane.ERROR_MESSAGE);
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

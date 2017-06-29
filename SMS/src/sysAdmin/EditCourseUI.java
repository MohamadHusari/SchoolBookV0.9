package sysAdmin;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EditCourseUI extends JPanel {

	public JScrollPane scrollPane;
	public DefaultListModel<String> model ;
	public JList<String> list;
	
	/**
	 * Create the panel.
	 */
	public EditCourseUI() {
		
		setBackground(Color.GRAY);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 85, 199, 155);
		add(scrollPane);
		model = new DefaultListModel<String>();
	    list = new JList<String>(model);
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);
	}

}

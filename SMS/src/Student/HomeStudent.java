package Student;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.List;
import javax.swing.event.ListSelectionListener;

import User.HomeUI;
import chat.Client;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class HomeStudent extends JPanel {
	/**
	 * Create the panel.
	 */
	 public JPanel panel;
	public HomeStudent() {
			setBounds(10, 59, 424, 300);
		setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
	      int value=0; 
		DefaultListModel <String>model = new DefaultListModel<String>();
		model.addElement("View Report");
		model.addElement("Download Assignment");
		model.addElement("Blocking parent");
		model.addElement("View Assignment");
		model.addElement("View Materials");
		model.addElement("Download Materials");
		JList list = new JList(model);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				  Object Res=list.getSelectedValue();
					switch((String)Res)
					{
					case "View Report": 
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new ViewReportGui(); 
							break;
					case "Download Assignment":
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new DownloadAssignment(); 
					 
						break; 
					case "Blocking parent":
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new BlockingParentGui(); 
						//System.out.print("Blockparentpanel");
						break; 
					case "View Assignment":
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new ViewAssignmentGui(); 
						break;
					case "View Materials":
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new ViewMaterialsGui(); 
						break;
					case "Download Materials":
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(panel);
						((HomeUI)Client.clientGUI).resizeHome();
						((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=new DownloadMaterialsGui();
						break;
						default:
							break;
						}
					add(panel);
			}
		});
	
		list.setBounds(0, 0, 134, 300);
		add(list);
		panel.setBounds(135, 0, 289, 300);
		
	}
}

package Student;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import User.HomeUI;
import chat.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReportGui extends JPanel {
	private JTextField textField;
	//JLabel newpanlbl=new JLabel();
     JPanel newpan=new JPanel();
	/**
	 * Create the panel.
	 */
	public ViewReportGui() {
		setBounds(135, 0, 289, 300);
		setLayout(null);
		newpan.setBounds(135, 0, 289, 300);
			
			
	
		JLabel lblReport = new JLabel("Report :");
		lblReport.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblReport.setForeground(Color.BLACK);
		lblReport.setBounds(10, 11, 90, 18);
		add(lblReport);
		
		JComboBox Reportcombobox = new JComboBox();
		Reportcombobox.setBounds(66, 11, 92, 20);
		add(Reportcombobox);
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(10, 59, 117, 29);
		add(btnViewReport);
		
		textField = new JTextField();
		textField.setBounds(10, 104, 247, 130);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel);
				((HomeUI)Client.clientGUI).resizeHome();
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=newpan;
			}
		});
		btnBack.setBounds(10, 245, 89, 29);
		add(btnBack);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel);
				((HomeUI)Client.clientGUI).resizeHome();
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=newpan;
			}
		});
		btnCancel.setBounds(132, 245, 89, 29);
		add(btnCancel);

	}
}
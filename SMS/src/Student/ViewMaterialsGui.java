package Student;

import javax.swing.JPanel;

import User.HomeUI;
import chat.Client;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMaterialsGui extends JPanel {

	JPanel newpan=new JPanel(); 
	/**
	 * Create the panel.
	 */
	public ViewMaterialsGui() {
		setBounds(135, 0, 289, 300);
		setLayout(null);
		newpan.setBounds(135, 0, 289, 300);
		JLabel lblMaterialsPerCourse = new JLabel(" Materials per Course :");
		lblMaterialsPerCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterialsPerCourse.setBounds(10, 11, 138, 33);
		add(lblMaterialsPerCourse);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(66, 91, 138, 27);
		add(comboBox);
		
		JLabel lblCourseName = new JLabel("Course Name :");
		lblCourseName.setBounds(10, 55, 138, 29);
		add(lblCourseName);
		
		JLabel lblMaterials = new JLabel("Materials:");
		lblMaterials.setBounds(10, 147, 83, 27);
		add(lblMaterials);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(66, 172, 138, 27);
		add(comboBox_1);
		
		JButton btnView = new JButton("View");
		btnView.setBounds(10, 246, 89, 23);
		add(btnView);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).remove(((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel);
				((HomeUI)Client.clientGUI).resizeHome();
				((HomeStudent)((HomeUI)Client.clientGUI).innerpanel).panel=newpan;
			}
		});
		btnCancel.setBounds(130, 246, 89, 23);
		add(btnCancel);

	}
}

package Teacher;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JButton;

public class TeacherEstimatingFile extends JPanel {
	   public JFilePicker filePicker;
	   public Choice choice3 = new Choice();
	/**
	 * Create the panel.
	 */
	public TeacherEstimatingFile() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		JLabel lblEstimatingFile = new JLabel("Estimating File");
		lblEstimatingFile.setBounds(193, 11, 101, 14);
		add(lblEstimatingFile);
		filePicker = new JFilePicker("Choose a file", "Browse...");
		filePicker.setBounds(-91, 102, 591, 52);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".pdf", "PDF");
		filePicker.addFileTypeFilter(".jpg", "JPG Image");
		JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
		
		add(filePicker);
		JLabel lblStudent = new JLabel("Student :");
		lblStudent.setBounds(22, 45, 46, 14);
		add(lblStudent);
		
	
		choice3.setBounds(74, 45, 107, 20);
		add(choice3);
		
		JButton btnSendFile = new JButton("Send File");
		btnSendFile.setBounds(157, 214, 89, 23);
		add(btnSendFile);
	}

}

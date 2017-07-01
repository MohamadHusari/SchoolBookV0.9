package Teacher;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JButton;

public class TeacherUplaodAss extends JPanel {
    public Choice choice = new Choice();
    public JLabel lblClass = new JLabel("Course:");
    public JLabel lblUploadAssigment = new JLabel("Upload Assigment ");
    public JFilePicker filePicker;
    /**
	 * Create the panel.
	 */
	public TeacherUplaodAss() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		filePicker = new JFilePicker("Choose a file", "Browse...");
		filePicker.setBounds(-91, 102, 591, 52);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".pdf", "PDF");
		filePicker.addFileTypeFilter(".jpg", "JPG Image");
		JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
		
		add(filePicker);
		
		lblUploadAssigment.setBounds(190, 11, 134, 14);
		add(lblUploadAssigment);
		lblClass.setBounds(26, 50, 46, 14);
		add(lblClass);
		choice.setBounds(89, 50, 235, 20);
		add(choice);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(163, 225, 89, 23);
		add(btnUpload);
	}
}

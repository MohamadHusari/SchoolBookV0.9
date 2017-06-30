package Teacher;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JButton;

public class TeacherUploadMat extends JPanel {
   public Choice choice2 = new Choice();
   public JFilePicker filePicker;
	/**
	 * Create the panel.
	 */
	public TeacherUploadMat() {
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
		
		JLabel lblUploadMatrial = new JLabel("Upload Materials");
		lblUploadMatrial.setBounds(189, 24, 138, 14);
		add(lblUploadMatrial);
		
		JLabel lblClass = new JLabel("Course:");
		lblClass.setBounds(10, 58, 46, 14);
		add(lblClass);
		
		
		choice2.setBounds(62, 58, 138, 20);
		add(choice2);
		
		JButton btnUpload2 = new JButton("Upload");
		btnUpload2.setBounds(189, 226, 89, 23);
		add(btnUpload2);
	}

}

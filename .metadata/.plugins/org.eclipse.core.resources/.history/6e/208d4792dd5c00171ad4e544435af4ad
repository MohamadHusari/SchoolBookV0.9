package Teacher;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.jidesoft.swing.FolderChooser;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TU_newRequest extends JPanel {

	public Choice teacunit_choice;
	public JFilePicker filePicker;
	/**
	 * Create the panel.
	 */
	public TU_newRequest() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 500, 350);
		setLayout(null);
		
		teacunit_choice = new Choice();
		teacunit_choice.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		teacunit_choice.setBounds(171, 39, 128, 24);
		add(teacunit_choice);
		
		JLabel lbltechunit = new JLabel("Teaching Unit :");
		lbltechunit.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lbltechunit.setBounds(68, 41, 103, 20);
		add(lbltechunit);
		
		
		filePicker = new JFilePicker("Choose a file", "Browse...");
		filePicker.setBounds(0, 83, 522, 135);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".pdf", "PDF");
		filePicker.addFileTypeFilter(".jpg", "JPG Image");
		JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
		
		add(filePicker);
		
	}
	
	
	  
	
	
	
	
	
	
	
	
}

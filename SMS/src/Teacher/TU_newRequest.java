package Teacher;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.jidesoft.swing.FolderChooser;

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
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
import javax.swing.JTextArea;

public class TU_newRequest extends JPanel {

	public Choice teacunit_choice;
	public JFilePicker filePicker;
	private JLabel lblDetails;
	private JButton btnSendReq;
	private JButton btnBack;
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
		lbltechunit.setFont(new Font("Arial", Font.PLAIN, 14));
		lbltechunit.setBounds(68, 41, 103, 20);
		add(lbltechunit);
		
		
		filePicker = new JFilePicker("Choose a file", "Browse...");
		filePicker.setBounds(0, 83, 522, 52);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".pdf", "PDF");
		filePicker.addFileTypeFilter(".jpg", "JPG Image");
		JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));
		
		add(filePicker);
		
		lblDetails = new JLabel("Details:");
		lblDetails.setBounds(10, 178, 46, 14);
		add(lblDetails);
		
		JTextArea txtdetails = new JTextArea();
		txtdetails.setBackground(Color.WHITE);
		txtdetails.setBounds(68, 178, 356, 63);
		txtdetails.setBorder(BorderFactory.createLineBorder(SystemColor.BLACK));
		add(txtdetails);
		
		btnSendReq = new JButton("Send Request");
		btnSendReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				
			}
		});
		btnSendReq.setBounds(68, 276, 117, 23);
		add(btnSendReq);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				((TU_newRequest)((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new TU_requestUI();
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
				//((TeacherShowCFields)((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).index = 0;
				//Client.client.handleMessageFromClientUI(new Message("SELECT * FROM class_schedule WHERE teacher_id="+Client.client.user.getID()+" AND sem_id="+ Client.client.opnedsem,QTypes.showCoursesT));
				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM tu_req WHERE sem_id="+Client.client.opnedsem,QTypes.tu_request));
				//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
				((TeacherHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizeteacherhome();
			}
		});
		btnBack.setBounds(271, 276, 89, 23);
		add(btnBack);
		
	}
}

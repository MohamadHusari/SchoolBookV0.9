package sysAdmin;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

import Entities.TeachUnit;
import User.HomeUI;
import chat.Client;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ShowTeachUnitUI extends JPanel {
	public JTextField txtteachid;
	public JTextField txtteachname;
	
	public ArrayList<TeachUnit> TU = new ArrayList<TeachUnit>();
	public int index = 0;
	public JButton btnback2;
	public JButton btnback1;
	public JButton btnnext1;
	public JButton btnnext2;
	public JLabel lblstatus;
	private JButton btnclose;
	/**
	 * Create the panel.
	 */
	public ShowTeachUnitUI() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 428, 350);
		setLayout(null);
		
		JLabel lblTeachUnitId = new JLabel("Teach Unit ID :");
		lblTeachUnitId.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTeachUnitId.setBounds(75, 72, 110, 20);
		add(lblTeachUnitId);
		
		txtteachid = new JTextField();
		txtteachid.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtteachid.setEditable(false);
		txtteachid.setBackground(Color.LIGHT_GRAY);
		txtteachid.setBounds(184, 75, 118, 20);
		add(txtteachid);
		
		JLabel lblTeachUnitName = new JLabel("Teach Unit Name :");
		lblTeachUnitName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblTeachUnitName.setBounds(53, 105, 129, 18);
		add(lblTeachUnitName);
		
		txtteachname = new JTextField();
		txtteachname.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtteachname.setEditable(false);
		txtteachname.setBackground(Color.LIGHT_GRAY);
		txtteachname.setBounds(184, 106, 118, 20);
		add(txtteachname);
		
		btnback2 = new JButton("");
		btnback2.setIcon(new ImageIcon("img\\sysAdmin\\back2.png"));
		btnback2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnback1.setEnabled(false);
				btnback2.setEnabled(false);
				btnnext1.setEnabled(true);
				btnnext2.setEnabled(true);
				index= 0;
				txtteachid.setText(TU.get(index).getTeachUnit_ID());
				txtteachname.setText(TU.get(index).getTeachUnit_Name());
			}
		});
		btnback2.setFocusable(false);
		btnback2.setBorder(BorderFactory.createEmptyBorder());
		btnback2.setBackground(Color.WHITE);
		btnback2.setBounds(123, 196, 32, 26);
		add(btnback2);
		
		btnback1 = new JButton("");
		btnback1.setIcon(new ImageIcon("img\\sysAdmin\\back1.png"));
		btnback1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnnext2.setEnabled(true);
				btnnext1.setEnabled(true);
				index--;
				if(index == 0)
				{
					btnback1.setEnabled(false);
					btnback2.setEnabled(false);
					txtteachid.setText(TU.get(index).getTeachUnit_ID());
					txtteachname.setText(TU.get(index).getTeachUnit_Name());;
				}
				else
				{
					txtteachid.setText(TU.get(index).getTeachUnit_ID());
					txtteachname.setText(TU.get(index).getTeachUnit_Name());
				}
			}
		});
		btnback1.setFocusable(false);
		btnback1.setBorder(BorderFactory.createEmptyBorder());
		btnback1.setBackground(Color.WHITE);
		btnback1.setBounds(165, 196, 32, 26);
		add(btnback1);
		
		btnnext1 = new JButton("");
		btnnext1.setIcon(new ImageIcon("img\\sysAdmin\\next1.png"));
		btnnext1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnback1.setEnabled(true);
				btnback2.setEnabled(true);
				index++;
				if(index == (TU.size()-1))
				{
					btnnext2.setEnabled(false);
					btnnext1.setEnabled(false);
					txtteachid.setText(TU.get(index).getTeachUnit_ID());
					txtteachname.setText(TU.get(index).getTeachUnit_Name());
				}
				else
				{
					txtteachid.setText(TU.get(index).getTeachUnit_ID());
					txtteachname.setText(TU.get(index).getTeachUnit_Name());
				}
			}
		});
		btnnext1.setFocusable(false);
		btnnext1.setBorder(BorderFactory.createEmptyBorder());
		btnnext1.setBackground(Color.WHITE);
		btnnext1.setBounds(209, 196, 32, 26);
		add(btnnext1);
		
		btnnext2 = new JButton("");
		btnnext2.setIcon(new ImageIcon("img\\sysAdmin\\next2.png"));
		btnnext2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnnext2.setEnabled(false);
				btnnext1.setEnabled(false);
				btnback1.setEnabled(true);
				btnback2.setEnabled(true);
				index= TU.size()-1;
				txtteachid.setText(TU.get(index).getTeachUnit_ID());
				txtteachname.setText(TU.get(index).getTeachUnit_Name());
			}
		});
		btnnext2.setFocusable(false);
		btnnext2.setBorder(BorderFactory.createEmptyBorder());
		btnnext2.setBackground(Color.WHITE);
		btnnext2.setBounds(253, 196, 32, 26);
		add(btnnext2);
		
		lblstatus = new JLabel("*There no teaching units in DB");
		lblstatus.setVisible(false);
		lblstatus.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblstatus.setForeground(Color.RED);
		lblstatus.setBounds(107, 23, 220, 20);
		add(lblstatus);
		
		btnclose = new JButton("");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are You Sure you wanna colse this Panel", "Verify your option", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
		        	((ShowTeachUnitUI)((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel).removeAll();
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel = new WelcomeUI();
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).innerpanel);
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).collapseAll(((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).mytree.tree);
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).savemenu="";
		        	((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).pressed=false;
					//Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
					((sysAdminHomeUI)((HomeUI)Client.client.clientGUI).innerpanel).resizesysAdminHome();
		        }
			}
		});
		btnclose.setIcon(new ImageIcon("img\\sysAdmin\\close-icon-31.png"));
		btnclose.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnclose.setFocusable(false);
		btnclose.setBorder(BorderFactory.createEmptyBorder());
		btnclose.setBackground(Color.WHITE);
		btnclose.setBounds(339, 0, 87, 69);
		add(btnclose);
		
		
	}
	
	public void startmyUI ()
	{
		if(index == 0)
		{
			btnback1.setEnabled(false);
			btnback2.setEnabled(false);
			txtteachid.setText(TU.get(index).getTeachUnit_ID());
			txtteachname.setText(TU.get(index).getTeachUnit_Name());
			//list.updateUI();
		}
		else if (index == (TU.size()-1))
		{
			btnnext1.setEnabled(false);
			btnnext2.setEnabled(false);
			txtteachid.setText(TU.get(index).getTeachUnit_ID());
			txtteachname.setText(TU.get(index).getTeachUnit_Name());
			//list.updateUI();
		}
		else if(TU.isEmpty())
		{
			lblstatus.setVisible(true);
		}
		else
		{
			txtteachid.setText(TU.get(index).getTeachUnit_ID());
			txtteachname.setText(TU.get(index).getTeachUnit_Name());
	    }
	}

}

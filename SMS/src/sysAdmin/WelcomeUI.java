package sysAdmin;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WelcomeUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomeUI() {
		setBackground(Color.WHITE);
		setBounds(10, 59, 540, 350);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel Welcomeimg = new JLabel("");
		Welcomeimg.setIcon(new ImageIcon("img\\sysAdmin\\Welcome.gif"));
		add(Welcomeimg);

	}

}

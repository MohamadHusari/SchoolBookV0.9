package Secretary;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class HomeSelectUI extends JPanel {

	/**
	 * Create the panel.
	 */
	private final JLabel menuimg = new JLabel(new ImageIcon("img\\Secretary\\HomeMenuUI\\Folder-icon.png"));
	public HomeSelectUI() {
		setBackground(Color.WHITE);
		setBounds(177, 0, 415, 400);
		setLayout(null);
		menuimg.setBounds(57,44,300,300);
		add(menuimg);
	}

}

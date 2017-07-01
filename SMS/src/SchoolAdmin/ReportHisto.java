package SchoolAdmin;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ReportHisto extends JPanel {


	private final JButton Different_Classes = new JButton("Different Classes");
	private int [] ArrayCounter = new int [100];
	private HashMap<Integer , Integer> StudentsGrades = new HashMap<Integer, Integer>();
	public boolean done =false;
	public ReportHisto(){
		
		setLayout(null);
		Different_Classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.opnedsem="m7";
				String Q=new String ("SELECT * FROM studentgrades WHERE sem_id ='"+Client.opnedsem+"';");	
				Message msg = new Message(Q,QTypes.getgrades);
				Client.client.handleMessageFromClientUI(msg);
				while(!done);
				System.out.println("start");
				LineChart_AWT chart = new LineChart_AWT("","",ArrayCounter);
				chart.pack( );
				RefineryUtilities.centerFrameOnScreen( chart );
				chart.setVisible( true );
				System.out.println("Done");
		}
		});
		
Different_Classes.setBounds(241, 91, 156, 23);
add(Different_Classes);
		
	}
	
	@SuppressWarnings("unchecked")
	public void fillArray(HashMap<Integer, Integer> msg){
		this.StudentsGrades = (HashMap<Integer, Integer>) msg.clone();
		for(Integer key : msg.keySet()){
			ArrayCounter[msg.get(key)]++;
		}
	}
	public void setflag(boolean flag){
		this.done = flag;
	}
	
		}


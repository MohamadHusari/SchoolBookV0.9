package chat;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSet;
import Teacher.*;
import ocsf.client.AbstractClient;
import Entities.ClassRoomStudent;
import Entities.Course;
import Entities.Semester;
import Entities.StudentName;
import Entities.TeachUnit;
import Entities.User;
import OurMessage.*;
import User.HomeUI;
import sysAdmin.*;
import User.LoginUI;
import Secretary.*;
import Student.*;
//import common.Sys;
/**
 * Client class represents as the user who sends the message to the server and
 * gets the message from the server.
 * 
 * @author 
 *
 */
public class Client extends AbstractClient {

	//Sys clientUI;

	public static JFrame clientGUI;
	public static Client client;
    public static User user;
    public static String opnedsem = "";
    //Get more entities
    public static JPanel userMenu;
    public static JPanel selectedMenu;
	/**
	 * Constructor for creating the client.
	 * 
	 * @param host
	 *            is the host of the server used to connecting to the server.
	 * @param port
	 *            is the port used
	 */
    public Client (String host, int port){
    	super(host, port);
    }
	public Client (String host, int port, JFrame m) throws IOException  {
        super(host, port); //Call the superclass constructor
        //this.id = ID;
        //this.clientUI = clientUI;
        this.clientGUI = m;
       /*try {
            openConnection();
        } catch (IOException e) {
            System.out.println("Error: Can't setup connection! Awaiting command");
            return;
        }
       /*
        try {
            sendToServer("#login " + "id");
        } catch (IOException e) {
            clientUI.display("An error occurred.  Terminating client.");
            quit();
        }
        */
		
	}
	
	public void quit() {
        try {
            closeConnection();
        } catch (IOException e) {
        }
        System.exit(0);
    }
	
	public void SetUser(User n) {
		this.user = n;
    }

	/**
	 * Handles all the different type of messages received from the server and
	 * displays on its ui.
	 * 
	 * @param msg
	 *            is the message received from the server.
	 */
	@Override
	public void handleMessageFromServer(Object msg) {
		System.out.println("I am here HMFS");
		int op = ((Request)msg).getRtype();
		System.out.println("Code: "+op);
		switch(op){
		case 4:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				((HomeUI)clientGUI).logout();
			}
			else{
				//System Error
			}
			break;
		case 5://Login Case
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				((LoginUI)clientGUI).lblerr.setForeground(Color.RED);
				Boolean flag = (Boolean)((Request)msg).getRequest();
				if(flag == true)
					((LoginUI)clientGUI).setstatus("Username or password is incorrect. !");
				else
					((LoginUI)clientGUI).setstatus("Your account is not Active !");
			}
			if(((Request)msg).getRequest() instanceof User)
			{ 
				User us=(User)(((Request)msg).getRequest());
				this.user=us;
				((LoginUI)clientGUI).loginsuccess(us.getAccess().getAccess());//Treat Login Case in loginsuccess function
				//clientGUI.setVisible(false);
				System.out.println("I am here Request User");
				
				
			}
			
		case 7://Login Case
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest()))
					System.out.print("There is no Opned semester");

			}
			if(((Request)msg).getRequest() instanceof Integer)
			{ 
				this.opnedsem = ((Integer)((Request)msg).getRequest()).toString();	
			}
			
			break;
		case 101:
			((SecretaryHomeUI)Client.userMenu).ChangeJPanel(new CreatingSemesterUI());
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if((Boolean)(((Request)msg).getRequest())){
					//DB is Empty (NO SEMESTER CREATED YET)
					((CreatingSemesterUI)Client.selectedMenu).setSemester();
				}else{
					//Semester is still on
					((CreatingSemesterUI)Client.selectedMenu).setErrSemester();
				}
			}
			if(((Request)msg).getRequest() instanceof Semester){
				//semester expired
				Semester sm=(Semester)(((Request)msg).getRequest());
				((CreatingSemesterUI)Client.selectedMenu).setSemester(sm.getSemester_id(),sm.getSemesterLetter());
			}
			break;
			
		case 102:
				((CreatingSemesterUI)Client.selectedMenu).added((Boolean)(((Request)msg).getRequest()));
			break;
		case 103:
			if(((Request)msg).getRequest() instanceof TableModel)
			{
				((SecretaryHomeUI)Client.userMenu).ChangeJPanel(new ExceptionStudentsUI());
			((ExceptionStudentsUI)Client.selectedMenu).tblRequest.setModel((TableModel)(((Request)msg).getRequest()));
			
			}
			break;
			
		case 104:break;
		case 105:
			if(((Request)msg).getRequest() instanceof TableModel[])
			{
				ArrayList<ClassRoomStudent> classes=new ArrayList<ClassRoomStudent>();
				ArrayList<StudentName> studentlist=new ArrayList<StudentName>();
				TableModel rs1=((TableModel[])(((Request)msg).getRequest()))[1];
				TableModel rs2=((TableModel[])(((Request)msg).getRequest()))[0];
				for(int i=0;i<rs1.getRowCount();i++)
				{//Setting Student List
						studentlist.add(new StudentName(Integer.parseInt(rs1.getValueAt(i, 0).toString()),rs1.getValueAt(i,1).toString()));
				}
				for(int i=0;i<rs2.getRowCount();i++){//setting classes list
						classes.add(new ClassRoomStudent(rs2.getValueAt(i,0).toString(),Integer.parseInt(rs2.getValueAt(i, 1).toString())));
				}
				((SecretaryHomeUI)Client.userMenu).ChangeJPanel(new AssocciateClassUI());
				((AssocciateClassUI)Client.selectedMenu).classes=classes;
				((AssocciateClassUI)Client.selectedMenu).StudentsList=studentlist;
				((AssocciateClassUI)Client.selectedMenu).getStudentsforClass();
				}else{
				
			}
			break;
		case 106:
			if((Boolean)(((Request)msg).getRequest()))
			{
				((AssocciateClassUI)Client.selectedMenu).insertionsuccess();
			}else{
				((AssocciateClassUI)Client.selectedMenu).insertionfailed();
			}
			break;

		case 300:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(((Request)msg).getRequest().equals(true))
				{
					 ((BlockingParentGui)((HomeStudent)((HomeUI)clientGUI).innerpanel).panel).lblMsg.setText("your request has been sent successfully. ");	
				}
				else 
				{
					((BlockingParentGui)((HomeStudent)((HomeUI)clientGUI).innerpanel).panel).lblMsg.setText("your request has failed,check errors ");
				}
				
			}
			break; 
			
		case 301:// filling the course comboBox :
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest())){
					//DB is Empty
					System.out.print("empty combo");
				}
			}
			if(((Request)msg).getRequest() instanceof ArrayList<?>)
			{
				ArrayList<Integer> m = ((ArrayList<Integer>)((Request)msg).getRequest());
				for (int i = 0; i < m.size(); i++) {
		            //((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).teacunit_choice.add(m.get(i));
		            
		        }
			}
			break; 
			
			
			/* Sys Admin */
			
		case 200:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest())){
					//DB is Empty
					System.out.print("empty");
				}
			}
			if(((Request)msg).getRequest() instanceof ArrayList<?>)
			{
				if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof TeachUnit)
				{
					ArrayList<TeachUnit> m = ((ArrayList<TeachUnit>)((Request)msg).getRequest());
					for (int i = 0; i < m.size(); i++) {
						((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).teacunit_choice.add(m.get(i).getTeachUnit_Name() + " '" + m.get(i).getTeachUnit_ID() +"'");
					}
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).txtfirstid.setText(m.get(0).getTeachUnit_ID());
				}
			}
			break;
			
		case 201:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest())){
					//DB is Empty
					System.out.print("empty");
				}
			}
			if(((Request)msg).getRequest() instanceof ArrayList<?>)
			{
				if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof String)
				{
					ArrayList<String> m = ((ArrayList<String>)((Request)msg).getRequest());
					DefaultListModel lmdlEjemplo=new DefaultListModel();
					for (int i = 0; i < m.size(); i++)
						lmdlEjemplo.addElement(m.get(i));
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).list1.setModel(lmdlEjemplo);
					
					//JCheckBox[] myList;
					
					/*DefaultListModel model = ((DefaultListModel)((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).list1.getModel());
					
					for (int i = 0; i < m.size(); i++)
					{
						model.addElement(new CheckBoxListEntry(m.get(i),false));
					}
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).list1.setModel(model);
					//((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).txtfirstid.setText(m.get(0).getTeachUnit_ID());
					 * 
					 */
				}
			}
			break;
			
		case 202:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest())){
					//DB is Empty
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).showerrdilog("The Course id is not available");
				}
			}
			if(((Request)msg).getRequest() instanceof String)
			{
				
				((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).list1.addCheckBoxListSelectedValue(((Request)msg).getRequest(), false);
				((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).list1.updateUI();
					
			}
			break;
			
		case 203:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest()))
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).showerrdilog("The course id is already exists in DataBase !");
				else
				{
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).successadd("The Course has been added Successfully");
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel);
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).removeAll();
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel = new AddCourseUI();
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel);
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel);
					Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunits));
	    			Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
	    			((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).resizesysAdminHome();
				}
			}
			break;
			
		case 204:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest()))
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).showerrdilog("There is something wrong happened with Database");
				/*else
				{
					((AddCourseUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).successadd("The Course has been added Successfully");
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).contentPane.remove(((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel);
					((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).contentPane.add(((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel);
					Client.client.handleMessageFromClientUI(new Message("SELECT * FROM teaching_unit",QTypes.GetTeachunits));
	    			Client.client.handleMessageFromClientUI(new Message("SELECT course_id, course_name FROM courses",QTypes.GetAllCoursesids));
	    			((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).resizesysAdminHome();
				}*/
			}
			break;
			
		case 206:
			if(((Request)msg).getRequest() instanceof Boolean)
			{
				if(!(Boolean)(((Request)msg).getRequest()))
					System.out.print("medo");
			}
			if(((Request)msg).getRequest() instanceof ArrayList<?>)
			{
				if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof Course)
				{
					((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).allcourses = ((ArrayList<Course>)((Request)msg).getRequest()) ;
					//((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).index = 0;
					((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).StartmyUI( ((ShowAllCoursesFieldsUI)((sysAdminHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).allcourses);
				}
			}
			
			break;
			
			/* Sys Admin */
			/// teacher
			
					case 700:
						if(((Request)msg).getRequest() instanceof Boolean)
						{
							if(!(Boolean)(((Request)msg).getRequest())){
								//DB is Empty
								System.out.print("empty");
							}
						}
						if(((Request)msg).getRequest() instanceof ArrayList<?>)
						{
							if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof Integer)
							{
								ArrayList<Integer> m = ((ArrayList<Integer>)((Request)msg).getRequest());
								((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).lblchours.setText(""+m.get(1));
								((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).maxhours = m.get(0);
							}
						}
						break;
						
					case 701:
						if(((Request)msg).getRequest() instanceof Boolean)
						{
							if(!(Boolean)(((Request)msg).getRequest())){
								//DB is Empty
								System.out.print("empty");
							}
							else
							{
								((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).successadd("The hours has been added successfully");
								float res = Float.parseFloat(((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).textHours.getText());
								res += Integer.parseInt(((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).lblchours.getText());
								((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).lblchours.setText(""+res);
								((TeacherEnteringHours)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).textHours.setText("");
							}
						}
						
						break;
						
					case 702:
						if(((Request)msg).getRequest() instanceof Boolean)
						{
							if(!(Boolean)(((Request)msg).getRequest())){
								//DB is Empty
								System.out.print("empty");
							}
						}
						if(((Request)msg).getRequest() instanceof ArrayList<?>)
						{
							if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof TeachUnit)
							{
								ArrayList<TeachUnit> m = ((ArrayList<TeachUnit>)((Request)msg).getRequest());
								for (int i = 0; i < m.size(); i++) {
									((TU_newRequest)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).teacunit_choice.add(m.get(i).getTeachUnit_Name() + " '" + m.get(i).getTeachUnit_ID() +"'");
								}
							}
						}
						break;
						
					case 703:
						if(((Request)msg).getRequest() instanceof Boolean)
						{
							if(!(Boolean)(((Request)msg).getRequest()))
								System.out.print("asd");
						}
						if(((Request)msg).getRequest() instanceof ArrayList<?>)
						{
							if(((ArrayList<?>)((Request)msg).getRequest()).get(0) instanceof Course)
							{
								((TeacherShowCFields)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).allcourses = ((ArrayList<Course>)((Request)msg).getRequest()) ;
								//((TeacherShowCFields)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).index = 0;
								((TeacherShowCFields)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).StartmyUI(((TeacherShowCFields)((TeacherHomeUI)((HomeUI)clientGUI).innerpanel).innerpanel).allcourses);
							}
						}
						
						break;
						
						//teacher
						
						
						
			
		}
	}
	
    /**
     * This method handles all data coming from the UI
     *
     * @param message The message from the UI.
     */
    public void handleMessageFromClientUI(Message msg) {
    	if (!isConnected()) {
            //((LoginUI) clientGUI.setst("Could not send message to server. Terminating client.");
        } else {
        	try {
				sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    }


    public void handleCommand(String command) throws IOException {
    	if(command.equals("#Checklogin"))
    	{
    		try {
                openConnection();
                sendToServer(command);
                System.out.println("Connected to server!");
            } catch (IOException e) {
            }
    	}
        /*if (command.equals("#quit")) {
            quit();
        } else if (command.equals("#logoff")) {
            try {
                closeConnection();
            } catch (IOException e) {
            }
        } else if (command.contains("#sethost")) {
            if (!isConnected()) {
                String newHost = command.substring(9);
                setHost(newHost);
                System.out.println("Changed host to " + newHost);
            } else {
                System.out.println("Already connected!");
            }
        } else if (command.contains("#setport")) {
            if (!isConnected()) {
                int newPort = Integer.parseInt(command.substring(9));
                setPort(newPort);
                System.out.println("Changed port to " + newPort);
            } else {
                System.out.println("Already connected!");
            }
        } else if (command.contains("#login")) {
            if (!isConnected()) {
                try {
                    openConnection();
                    sendToServer(command);
                    System.out.println("Connected to server!");
                } catch (IOException e) {
                }
            } else {
                System.out.println("Already connected!");
            }
        } else if (command.equals("#gethost")) {
            System.out.println("Host: " + getHost());
        } else if (command.equals("#getport")) {
            System.out.println("Port: " + getPort());
        } else if (command.equals("#1")) {
            sendToServer("#1");
        } else if (command.equals("#2")) {
            sendToServer("#2");
        } else if (command.equals("#3")) {
            sendToServer("#3");
        } else if (command.equals("#4")) {
            sendToServer("#4");
        } else if (command.equals("#5")) {
            sendToServer("#5");
        } else if (command.equals("#6")) {
            sendToServer("#6");
        } else if (command.equals("#7")) {
            sendToServer("#7");
        } else if (command.equals("#8")) {
            sendToServer("#8");
        } else if (command.equals("#9")) {
            sendToServer("#9");
        } else if (command.equals("#createGame")) {
            sendToServer("#createGame");
        } else if (command.equals("#getGameList")) {
            sendToServer("#getGameList");
        } else {
            System.out.println("Command not recognized!");
        }
        */
    }
	
}

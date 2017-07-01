package server;

import java.awt.Color;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import javax.swing.table.TableModel;

import ocsf.server.*;

import OurMessage.*;
import User.LoginUI;
import net.proteanit.sql.DbUtils;
import Entities.AccessProfiles;
import Entities.ClassRoomStudent;
import Entities.Course;
import Entities.Semester;
import Entities.TeachUnit;
import Entities.User;
import Teacher.*;
public class Server extends AbstractServer {
	//Class variables ***************************************************
	  
	  /**
	   * The default port to listen on.
	   */
	  final public static int DEFAULT_PORT = 5555;
	  private Connection conn;
	  private ServerGUI serv;
	  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	  //Constructors ****************************************************
	  
	  /**
	   * Constructs an instance of the echo server.
	   *
	   * @param port The port number to connect on.
	   */
	  
	  public Server(ServerGUI srv,int port)
	  {
		  super(port);
		  this.serv = srv;
		  srv.setVisible(true);
	  }
	  
	  //Instance methods ************************************************
	  
	  /**
	   * This method create the connection between the server and the DB
	   *
	   * @param dbName The database name.
	   * @param user mysql username.
	   * @param pass mysql password.
	   */
	  
	  public boolean initDBConnection(String dbName,String user, String pass) throws Exception
	  {
		
		  try 
			{
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName+"?verifyServerCertificate=false"+ "&useSSL=false"+ "&requireSSL=false", user, pass);
	            System.out.println("SQL connection succeed");
	            return true;
	        } catch (SQLException ex) {return false;}
	  }
	 
	  /**
	   * This method handles any messages received from the client.
	   *
	   * @param msg The message received from the client , it's the query that the server should do.
	   * @param client The connection from which the message originated.
	 * @throws SQLException 
	   */
	  
	  @Override
		protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		  //Getting Current Date time
		  LocalDateTime now = LocalDateTime.now();
		  
		  //Server Check
		  Statement stmt=null;//INSERT & SELECT Query statement;
		  PreparedStatement pstmt;//UPDATE prepared statement
		  ResultSet rs=null,rs2=null;
		  try 
		  {	 
			  stmt = conn.createStatement();
			  int op = ((Message)msg).GetQType();
			  
			  switch(op){
			  case 4:
				  String[] parts = (((Message)msg).GetQuery()).split("/");
				  stmt.executeUpdate(parts[0]);
				  serv.display("["+dtf.format(now)+"] "+parts[1]+" has been disconnected!" );
				  try {
					client.sendToClient(new Request(true,QTypes.update));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  break;
			  case 5://Login User 
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if (rs.next()) {
					  int res=rs.getInt(5);//get status
					  if(res==1){
						  User us=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),res,new AccessProfiles(rs.getInt(6)));
						  Request req=new Request(us,QTypes.getuser);
						  serv.display("["+dtf.format(now)+"] User: "+us.getID()+" has been connected!" );
						  try{
							  client.sendToClient(req);
							  stmt.executeUpdate("UPDATE users SET Status = 0 WHERE ID="+ Integer.parseInt(rs.getString(1)));
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back User Details!");
						  }
					  }else{
						  Request req=new Request(false,QTypes.getuser);
						  try{
							  client.sendToClient(req);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					  }
				  }
				  else
				  {
					  Request req=new Request(true,QTypes.getuser);
					  try{
						  client.sendToClient(req);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
			  case 6:
				  stmt.executeUpdate(((Message)msg).GetQuery());
				  serv.display("["+dtf.format(now)+"] User: "+client.getInfo("name")+" has been disconnected!" );
				  break;
				  
			  case 7://Login Case
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if (rs.next()) {
					      int res=rs.getInt(1);//get sem id
						  Request req7=new Request(res,QTypes.GetOpenedSem);
						  try{
							  client.sendToClient(req7);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
				  }
				  else
				  {
					  Request req7=new Request(false,QTypes.GetOpenedSem);
					  try{
						  client.sendToClient(req7);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  
				  break;
				  
				  // Student Blocking Parent .
			  case 300:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) // if it was successfully executed  
				  {
					  Request req300=new Request(true,QTypes.blockparent);
					    try{
							  client.sendToClient(req300);
						  }catch(IOException ex){
							  serv.display("["+dtf.format(now)+"] Error Sending true statemnet block parent");
						  }
					}
				  else 
				  {
					  Request req300=new Request(false,QTypes.blockparent);
					  try{
						  client.sendToClient(req300);
					  }catch(IOException ex){
					
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment block parent!");
					  }
				  }
				  break;
				  
			  case 301:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<Integer> alltu=new ArrayList<Integer>();
					  int i =0;
					    do { 
					    	i++; // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	alltu.add(Integer.parseInt((rs.getString(i))));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req301=new Request(alltu,QTypes.courseassignment);
					    try{
							  client.sendToClient(req301);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back coursename statment!");
						  }
					}
				  else 
				  {
					  Request req301=new Request(false,QTypes.courseassignment);
					  try{
						  client.sendToClient(req301);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			 //SECRETARY CASES
			  case 101:
				  rs=stmt.executeQuery(((Message)msg).GetQuery());
				  
				  if (rs.next()) {
					  System.out.println("Semester: ID="+rs.getInt(1));
					  serv.display("["+dtf.format(now)+"] Checking Semester_ID="+rs.getInt(1)+" duration!");
					  Date dt=rs.getDate(3);
					  Date today=new Date();
					  if(dt.before(today)){
						  try {
							  Semester sm=new Semester(rs.getDate(2),dt,rs.getString(4),rs.getInt(1));
							client.sendToClient(new Request(sm,QTypes.checksemester));
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");
						}
					  }else{
						  try {
							client.sendToClient(new Request(false,QTypes.checksemester));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");
						}
					  }
				  }else{//didn't get any Semesters
					  try {
							client.sendToClient(new Request(true,QTypes.checksemester));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");
						}
				  }
				  break;
			  case 102://new semester
				  int result=stmt.executeUpdate(((Message)msg).GetQuery());
				  serv.display(((Message)msg).GetQuery());
				  if(result==1){
					  try {
						client.sendToClient(new Request(true,QTypes.addnewsemester));
						serv.display("["+dtf.format(now)+"] Successfull Insertion to table!");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						serv.display("["+dtf.format(now)+"] Error Sending back to Client!");					
					}
				  }else{
					  try {
							client.sendToClient(new Request(false,QTypes.addnewsemester));
							serv.display("["+dtf.format(now)+"] Unsuccessfull Insertion to table!");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");						
						}
				  }
					  
				  break;
			  case 103:
				  rs=stmt.executeQuery(((Message)msg).GetQuery());
				  TableModel tm=DbUtils.resultSetToTableModel(rs);
					  try {
							client.sendToClient(new Request(tm,QTypes.getrequests));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");	
						}
				  break;
			  case 104:
				  break;
			  case 105:
				  String[] TwoQ=(((Message)msg).GetQuery()).split("/");
				  rs=stmt.executeQuery(TwoQ[0]);
				  stmt = conn.createStatement();
				  rs2=stmt.executeQuery(TwoQ[1]);
				  TableModel[] lists={DbUtils.resultSetToTableModel(rs),DbUtils.resultSetToTableModel(rs2)};
				  try {
					client.sendToClient(new Request(lists,QTypes.getassocciateclass));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  break;
			  case 106:
				  int results=1;
				  Request res;
				  ArrayList<String> Qlist=(ArrayList<String>)(((Request)msg).getRequest());
				  for(String query:Qlist){
					  results&=stmt.executeUpdate(query);
				  }
				  if(results==1){
					  res=new Request(true,QTypes.insertnewstudents);
				  }else{
					  res=new Request(false,QTypes.insertnewstudents);
				  }
				  try {
					client.sendToClient(res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  break;
				  
				  
				//System-Administrator	Cases
			  case 200:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<TeachUnit> alltu=new ArrayList<TeachUnit>();
					  int i =0;
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	alltu.add(new TeachUnit (rs.getString(1), rs.getString(2)));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req200=new Request(alltu,QTypes.GetTeachunits);
					    try{
							  client.sendToClient(req200);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req200=new Request(false,QTypes.GetTeachunits);
					  try{
						  client.sendToClient(req200);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
			  case 201:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<String> allcourses=new ArrayList<String>();
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	allcourses.add(rs.getString(1) +" - " + rs.getString(2));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req201=new Request(allcourses,QTypes.GetAllCoursesids);
					    try{
							  client.sendToClient(req201);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req201=new Request(false,QTypes.GetAllCoursesids);
					  try{
						  client.sendToClient(req201);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 202:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  String retsos= ((Message) msg).GetQuery().substring(((Message) msg).GetQuery().length() - 5, ((Message) msg).GetQuery().length());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
						  retsos = retsos + " - "+rs.getString(1);
					    Request req202=new Request(retsos,QTypes.Getspaceficcoursename);
					    try{
							  client.sendToClient(req202);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
				 }
				  else 
				  {
					  Request req202=new Request(false,QTypes.Getspaceficcoursename);
					  try{
						  client.sendToClient(req202);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 203:
				  try {
					  stmt.executeUpdate(((Message) msg).GetQuery());
					  Request req203=new Request(true,QTypes.AddnewCourse);
					  try{
						  client.sendToClient(req203);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
						//System.out.println("Record is inserted into DBUSER table!");
					} catch (SQLException e) {
						serv.display("["+dtf.format(now)+"] Error Insert new Course!");
						Request req203=new Request(false,QTypes.AddnewCourse);
						try{
							  client.sendToClient(req203);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					}
				  break;
			  case 204:
				  try {
					  stmt.executeUpdate(((Message) msg).GetQuery());
					  Request req204=new Request(true,QTypes.AddnewPreCourse);
					  try{
						  client.sendToClient(req204);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
						//System.out.println("Record is inserted into DBUSER table!");
					} catch (SQLException e) {
						serv.display("["+dtf.format(now)+"] Error Insert pre-Course for spacifc Course!");
						Request req204=new Request(false,QTypes.AddnewPreCourse);
						try{
							  client.sendToClient(req204);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					}
				  break;
				  
			  case 206:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  ArrayList<Course> allcourses1=new ArrayList<Course>();
				  ArrayList<String> prec1=new ArrayList<String>();
				  Course course1;
				  String precc1 [] =null;
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	course1 = new Course (rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), null);
					    	allcourses1.add(course1);
					    } while (rs.next());
				  for(int i=0 ; i<allcourses1.size() ; i++)
				  {
					  prec1.clear();
					  rs = stmt.executeQuery("SELECT * FROM pre_courses WHERE course_id=" + allcourses1.get(i).getCourse_ID());
					  if(rs.next()) {
			    		  do {
			    		  	  prec1.add(rs.getString(2));
			    		  }while (rs.next());
			    		  precc1 = prec1.toArray(new String[prec1.size()]);
			    		  allcourses1.get(i).setPreCourses(precc1);
			    	   }
				  }
					    Request req206=new Request(allcourses1,QTypes.ShowinTXTallCourses);
					    try{
							  client.sendToClient(req206);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back The Courses details statment!");
						  }
				  }
				  else 
				  {
					  Request req206=new Request(false,QTypes.ShowinTXTallCourses);
					  try{
						  client.sendToClient(req206);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;  
				  
				  
				  
			  case 207:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<String> allcourses=new ArrayList<String>();
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	allcourses.add(rs.getString(1) +" - " + rs.getString(2));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req207=new Request(allcourses,QTypes.GetAllCoursesids1);
					    try{
							  client.sendToClient(req207);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req207=new Request(false,QTypes.GetAllCoursesids1);
					  try{
						  client.sendToClient(req207);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 208:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  String retsos1= ((Message) msg).GetQuery().substring(((Message) msg).GetQuery().length() - 5, ((Message) msg).GetQuery().length());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
						  retsos1 = retsos1 + " - "+rs.getString(1);
					    Request req208=new Request(retsos1,QTypes.Getspaceficcoursename1);
					    try{
							  client.sendToClient(req208);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
				 }
				  else 
				  {
					  Request req208=new Request(false,QTypes.Getspaceficcoursename1);
					  try{
						  client.sendToClient(req208);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
				  
			  case 209:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  ArrayList<Course> allcourses111=new ArrayList<Course>();
				  ArrayList<String> prec111=new ArrayList<String>();
				  Course course111;
				  String precc111 [] =null;
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	course111 = new Course (rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), null);
					    	allcourses111.add(course111);
					    } while (rs.next());
				  for(int i=0 ; i<allcourses111.size() ; i++)
				  {
					  prec111.clear();
					  rs = stmt.executeQuery("SELECT * FROM pre_courses WHERE course_id=" + allcourses111.get(i).getCourse_ID());
					  if(rs.next()) {
			    		  do {
			    			  prec111.add(rs.getString(2));
			    		  }while (rs.next());
			    		  precc111 = prec111.toArray(new String[prec111.size()]);
			    		  course111.setPreCourses(precc111);
			    	   }
				  }
					    Request req209=new Request(course111,QTypes.GetspaceficcourseDetails);
					    try{
							  client.sendToClient(req209);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back The Courses details statment!");
						  }
				  }
				  else 
				  {
					  Request req209=new Request(false,QTypes.GetspaceficcourseDetails);
					  try{
						  client.sendToClient(req209);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 210:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<TeachUnit> alltu=new ArrayList<TeachUnit>();
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	alltu.add(new TeachUnit (rs.getString(1), rs.getString(2)));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req210=new Request(alltu,QTypes.GetTeachunits2);
					    try{
							  client.sendToClient(req210);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req210=new Request(false,QTypes.GetTeachunits2);
					  try{
						  client.sendToClient(req210);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 211:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<String> allcourses=new ArrayList<String>();
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	allcourses.add(rs.getString(1) +" - " + rs.getString(2));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req211=new Request(allcourses,QTypes.GetAllCoursestoButinChechboxlist);
					    try{
							  client.sendToClient(req211);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req211=new Request(false,QTypes.GetAllCoursestoButinChechboxlist);
					  try{
						  client.sendToClient(req211);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 212:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  String rretmsa= ((Message) msg).GetQuery().substring(((Message) msg).GetQuery().length() - 5, ((Message) msg).GetQuery().length());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
						  rretmsa = rretmsa + " - "+rs.getString(1);
					    Request req212=new Request(rretmsa,QTypes.Getspaceficcoursenameforedit);
					    try{
							  client.sendToClient(req212);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
				 }
				  else 
				  {
					  Request req212=new Request(false,QTypes.Getspaceficcoursenameforedit);
					  try{
						  client.sendToClient(req212);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 213:
				  try {
					  stmt.executeUpdate(((Message) msg).GetQuery());
					  Request req213=new Request(true,QTypes.updateCourse);
					  try{
						  client.sendToClient(req213);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
						//System.out.println("Record is inserted into DBUSER table!");
					} catch (SQLException e) {
						serv.display("["+dtf.format(now)+"] Error Insert new Course!");
						Request req213=new Request(false,QTypes.updateCourse);
						try{
							  client.sendToClient(req213);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					}
				  break;
				  
			  case 214:
				  try {
					  stmt.execute(((Message) msg).GetQuery());
					  Request req214=new Request(true,QTypes.deletepreCourses);
					  try{
						  client.sendToClient(req214);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
						//System.out.println("Record is inserted into DBUSER table!");
					} catch (SQLException e) {
						serv.display("["+dtf.format(now)+"] Error Insert new Course!");
						Request req214=new Request(false,QTypes.deletepreCourses);
						try{
							  client.sendToClient(req214);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					}
				  break;
				  
			  case 215:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<TeachUnit> alltu=new ArrayList<TeachUnit>();
					  int i =0;
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	alltu.add(new TeachUnit (rs.getString(1), rs.getString(2)));
					    } while (rs.next());
					    Request req215=new Request(alltu,QTypes.GetTeachunitsUI);
					    try{
							  client.sendToClient(req215);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req215=new Request(false,QTypes.GetTeachunitsUI);
					  try{
						  client.sendToClient(req215);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
				//System-Administrator	Cases
				//Teacher
			  case 700:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  ArrayList<Integer> res1=new ArrayList<Integer>();
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					      res1.add(rs.getInt(3));
					      res1.add(rs.getInt(4));
					    Request req700=new Request(res1,QTypes.getcurrenthours);
					    try{
							  client.sendToClient(req700);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
				 }
				  else 
				  {
					  Request req700=new Request(false,QTypes.getcurrenthours);
					  try{
						  client.sendToClient(req700);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 701:
				  try {
					  stmt.executeUpdate(((Message) msg).GetQuery());
					  Request req701=new Request(true,QTypes.EnteringHours);
					  try{
						  client.sendToClient(req701);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
						//System.out.println("Record is inserted into DBUSER table!");
					} catch (SQLException e) {
						serv.display("["+dtf.format(now)+"] Error Insert new Course!");
						Request req701=new Request(false,QTypes.EnteringHours);
						try{
							  client.sendToClient(req701);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
						  }
					}
				  break;
				  
			  case 702:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  if(rs.next()) { // Checks for any results and moves cursor to first row,
					  ArrayList<TeachUnit> alltu=new ArrayList<TeachUnit>();
					  int i =0;
					    do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	alltu.add(new TeachUnit (rs.getString(1), rs.getString(2)));
					    	//System.out.print(alltu[i].getTeachUnit_ID() + " " +  alltu[i].getTeachUnit_Name() +"\n and hereeee");
					    } while (rs.next());
					    Request req702=new Request(alltu,QTypes.GetTeachunits1);
					    try{
							  client.sendToClient(req702);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back Teaching units statment!");
						  }
					}
				  else 
				  {
					  Request req702=new Request(false,QTypes.GetTeachunits1);
					  try{
						  client.sendToClient(req702);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
			  case 703:
				  rs = stmt.executeQuery(((Message) msg).GetQuery());
				  ArrayList<String> cou=new ArrayList<String>();
				  ArrayList<Course> allcourses11=new ArrayList<Course>();
				  ArrayList<String> prec11=new ArrayList<String>();
				  Course course11;
				  String precc11 [] =null;
				  if(rs.next())
				  {
					  do { // Use 'do...while' to process the first row, while continuing to process remaining rows
					    	cou.add(rs.getString(2));
					    } while (rs.next());
					  for (int i =0;i<cou.size();i++)
					  {
						  rs = stmt.executeQuery("SELECT * FROM courses WHERE course_id="+cou.get(i));
						  if(rs.next()) { // Checks for any results and moves cursor to first row,
							  do { // Use 'do...while' to process the first row, while continuing to process remaining rows
								  course11 = new Course (rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), null);
								  allcourses11.add(course11);
							  } while (rs.next());
						  }
				  
						  for(int i1=0 ; i1<allcourses11.size() ; i1++)
						  {
							  prec11.clear();
							  rs = stmt.executeQuery("SELECT * FROM pre_courses WHERE course_id=" + allcourses11.get(i1).getCourse_ID());
							  if(rs.next()) {
								  do {
									  prec11.add(rs.getString(2));
								  }while (rs.next());
								  precc11 = prec11.toArray(new String[prec11.size()]);
								  allcourses11.get(i1).setPreCourses(precc11);
							  }
						  }
					  }
					  Request req703=new Request(allcourses11,QTypes.showCoursesT);
					    try{
							  client.sendToClient(req703);
						  }catch(IOException ex){
							 //Do Somthing
							  serv.display("["+dtf.format(now)+"] Error Sending back The Courses details statment!");
						  }
				  }
				  else 
				  {
					  Request req703=new Request(false,QTypes.showCoursesT);
					  try{
						  client.sendToClient(req703);
					  }catch(IOException ex){
						 //Do Somthing
						  serv.display("["+dtf.format(now)+"] Error Sending back false statment!");
					  }
				  }
				  break;
				  
				  
			  case 704:
				  rs=stmt.executeQuery(((Message)msg).GetQuery());
				  TableModel tu=DbUtils.resultSetToTableModel(rs);
					  try {
							client.sendToClient(new Request(tu,QTypes.tu_request));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serv.display("["+dtf.format(now)+"] Error Sending back to Client!");	
						}
				  break;
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  //teacher
				  
			  }
			  
	      } catch (SQLException e2) 
		  		{
	    	   		serv.display("["+dtf.format(now)+"] Error with SQL Connection");
	      		}
	  	}
	 	    
	  /**
	   * This method overrides the one in the superclass.  Called
	   * when the server starts listening for connections.
	   */
	  protected void serverStarted()
	  {
		//Getting Current Date time
		  	LocalDateTime now = LocalDateTime.now();
		  System.out.println
		    ("["+dtf.format(now)+"] Server listening for connections on port " + getPort());
	  }
	  
	  /**
	   * This method overrides the one in the superclass.  Called
	   * when the server stops listening for connections.
	   */
	  protected void serverStopped()
	  {
		  System.out.println
	       ("Server has stopped listening for connections.");
	  }
	  
	  
}

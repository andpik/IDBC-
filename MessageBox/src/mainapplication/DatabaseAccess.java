package mainapplication;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




public class DatabaseAccess {
        private Scanner keyboard = new Scanner(System.in);
	private Connection conn = null;
	private static final String DBURL = "jdbc:mysql://andpik.ddns.net:36701/project1?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "andpik1";
	private static final String PASS = "2109239806";
	
	public void ConnectToDatabase() {
			try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) 
				{
					System.out.println("Failure to Connect to Database 1");
					e.printStackTrace();
				}
			conn = DriverManager.getConnection(DBURL, USER, PASS);
				} catch (SQLException ex)
					{
						System.out.println("Failure to Connect to Database 2");
						ex.printStackTrace();
			
					}
		
	}
	
	//****************************************************************************	
	public ResultSet login(String userName, String passWord)  {
		
			ResultSet rs = null;
			
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT username, password, userstatus, id FROM  users WHERE username = ?  AND passWord = ?" );
				pstmt.setString(1, userName);
				pstmt.setString(2, passWord);
				rs = pstmt.executeQuery();
				
				
				} catch (SQLException ex)
					{ System.out.println("Loggin' Problem"); 
			
					}
//								
//						
				return rs;
	
	
	}

	public void DisplayMessages(){
        ResultSet rs;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM messagetbl");
            //pstmt.setInt(1, userID);
            System.out.println("______________________________________________________");
            rs = pstmt.executeQuery();
            while (rs.next()) {
            int msgid=rs.getInt("msgid");
            String DateOfSub=rs.getString("datesent");
            String Sender = rs.getString("username");
            String Receiver=rs.getString("rusername");
            String MessageData=rs.getString("messagetext");    
                
             System.out.println(msgid);
             System.out.println(DateOfSub);
             System.out.println(Sender);
             System.out.println(Receiver);   
             System.out.println(MessageData);    
             System.out.println("_________________________________________________________");   

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }       
    } 
    
    public void DisplayUsers(){
        ResultSet rs;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users");
            //pstmt.setInt(1, userID);
            System.out.println("****************************************************");
            rs = pstmt.executeQuery();
            while (rs.next()) {
            int id=rs.getInt("id");
            String username=rs.getString("username");
            String userstatus=rs.getString("userstatus");
              
                
             System.out.println(id);
             System.out.println(username);
             //System.out.println(password);
             System.out.println(userstatus);   
                
             System.out.println("****************************************************");   

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }     
        
        
     
        
        
    public void SendMessages(String username) throws SQLException{
        String Sender=username;
        String datesent="";
        System.out.print(username);
        System.out.print("Who is the Receiver of the notice? ");
	String Receiver = keyboard.nextLine();
        System.out.print("What is the your message ");
	String MessageData = keyboard.nextLine();
        
        Statement stmt = (Statement)conn.createStatement();
        String sql="INSERT INTO messagetbl(username,rusername,messagetext)  VALUES ('"+Sender+"','"+Receiver+"','"+MessageData+"')";
        stmt.executeUpdate(sql);

        ResultSet rs;            
        PreparedStatement pstmt1 = conn.prepareStatement("SELECT datesent FROM messagetbl ORDER BY datesent DESC LIMIT 1");
        rs = pstmt1.executeQuery();
            while (rs.next()) {
            datesent=rs.getString("datesent");
            System.out.println(datesent);
            }
        
        
        new AppendToFile(Sender,Receiver,MessageData,datesent);
    
    
    }

     public void EditMessages() throws SQLException{
        DisplayMessages();
        System.out.print("What msgid of notice want to edit ");
        String msgid1=keyboard.nextLine();
        int msgid = Integer.parseInt(msgid1);
        
        ResultSet rs;
       // try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT messagetext FROM messagetbl WHERE msgid= ?");
            pstmt.setInt(1, msgid);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            String messagetext = rs.getString("messagetext"); 
            System.out.println(messagetext);
            }
            
            
            System.out.println("Write your new message. When ENTER pressed the new message will be saved in the database ");
            String MessageData = keyboard.nextLine();
        
        PreparedStatement pstmt1 = conn.prepareStatement("UPDATE messagetbl SET messagetext= ? WHERE msgid= ? ");
	pstmt1.setString(1, MessageData);
	pstmt1.setInt(2, msgid);
	pstmt1.executeUpdate();
        

    }    
        
        
     public void DeleteMessages() throws SQLException{
        DisplayMessages();
        System.out.print("What msgid of notice want to delete ");
        String msgid1=keyboard.nextLine();
        int msgid = Integer.parseInt(msgid1);
        ResultSet rs;
       // try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM messagetbl WHERE msgid= ?");
            pstmt.setInt(1, msgid);
            pstmt.executeUpdate();
    }       
        
    public void CreateUser() throws SQLException{
        
        System.out.print("What is the username of the new user? ");
	String username = keyboard.nextLine();
        System.out.print("What is the password of the new user? ");
	String password = keyboard.nextLine();
        System.out.print("What is the userstatus? ");
        System.out.print("Input 2 for Admin, 4 for Moderator, 5 for Moderator1, 6 for Simple User");
        String userstatus1=keyboard.nextLine();
        int userstatus = Integer.parseInt(userstatus1);
        
        Statement stmt = (Statement)conn.createStatement();
        String sql="INSERT INTO users(username,password,userstatus)  VALUES ('"+username+"','"+password+"','"+userstatus+"')";
        stmt.executeUpdate(sql);
    }
     
     
     public void UpdateUser() throws SQLException{
        DisplayUsers();
        int sqluserstatus=0;
        System.out.print("Who is the user you want to update?  Input his id");
	String user1=keyboard.nextLine();
        int user = Integer.parseInt(user1);
        
        System.out.println("****************************************************");
        System.out.println("			1: Update to Admin");
        System.out.println("			2: Update to Moderator");
        System.out.println("			3: Update to Moderator1");
        System.out.println("			4: Update to Simple User");
        System.out.println("****************************************************");
        
        String userstatus1 = keyboard.nextLine();
        int userstatus = Integer.parseInt(userstatus1);
        if(userstatus==1)
            sqluserstatus=2;
        if(userstatus==2)
            sqluserstatus=4;
        if(userstatus==3)
            sqluserstatus=5;
        if(userstatus==4)
            sqluserstatus=6;
        
        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET userstatus= ? WHERE id= ? ");
	pstmt.setInt(1, sqluserstatus);
	pstmt.setInt(2, user);
	pstmt.executeUpdate();
        
        

    }
     
     
     
     
     public void DeleteUser() throws SQLException{
        DisplayUsers();
        System.out.print("What User you want to delete? Input his id ");
        String id1=keyboard.nextLine();
        int id = Integer.parseInt(id1);
        //ResultSet rs;
       // try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE id= ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
    }       
     
     
     
     
        
        public void closeResources(){
	        try {
	            if(conn!=null)
	                conn.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }




}



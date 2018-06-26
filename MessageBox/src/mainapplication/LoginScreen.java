package mainapplication;

import java.io.Console;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreen {

	private Scanner keyboard = new Scanner(System.in);
	private Console console = System.console();
	private String userName;
	private String passWord;
	private int status;
        private int id;
	//private byte tries = 3;
	private ResultSet ID_userName = null;
	private DatabaseAccess DB;
        //private boolean HaveIRun=false;
        
	public LoginScreen(DatabaseAccess DB) throws SQLException {

		this.DB = DB;
	
        

	
                try {
		// while(tries > 0){
		System.out.print("Please give your username: ");
		// this.userName = keyboard.nextLine();
		userName = keyboard.nextLine();

		System.out.print("Please give your Password: ");
		// this.passWord = keyboard.nextLine();
		passWord = keyboard.nextLine();
		//console.readPassword("Please give your Password: ");
		// this.passWord = String.valueOf(pw);

		ID_userName = DB.login(userName, passWord);

		
			if (!ID_userName.isBeforeFirst()) {
	                 System.out.println("Wrong Credentials");
			 System.out.println("Application Exits");
                         System.out.println("Try Later");
                         DB.closeResources(); 
                            
                          System.exit(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
                //for debugging 
                while (ID_userName.next()) {
				userName = ID_userName.getString(1);
				passWord = ID_userName.getString(2);
				status = ID_userName.getInt(3);
                                id = ID_userName.getInt(4);
				//System.out.println(userName + passWord + status + id);
                                
			}
		
	//return true;	
	
        new ApplicationMenus(userName, status, id, DB);
        
        }

}
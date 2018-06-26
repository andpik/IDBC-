/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapplication;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public class ApplicationMenus {
    
    private Scanner input = new Scanner(System.in);
    private String userName;
    private int userstatus;
    private DatabaseAccess DB;
    
    
    public ApplicationMenus(String username, int userstatus, int id, DatabaseAccess DB) throws SQLException{
        

            this.DB = DB;
//            System.out.println(username);
//            System.out.println(userstatus);
//            System.out.println(id); 
            DisplayMenu(username, userstatus, id);

    } 
    
    public void DisplayMenu(String username, int userstatus, int id) throws SQLException{
        switch(userstatus) {
        case 2: {SuperAdminAccount(username, id);
                break;}
        case 4: {ModeratorAccount(username, id);
                 break;}
        
        case 5: {ModeratorAccount1(username, id);
                 break;} 
        case 6: {SimpleMemberAccount(username, id);
                 break;}
        }
        
        }
    	
    
    
    private void SuperAdminAccount(String username, int id) throws SQLException {
        
        System.out.println("Live long and prosper "+ username);
        System.out.println("****************************************************");
        System.out.println("1 : Read Messages ");
        System.out.println("2 : Send Messages ");
        System.out.println("3 : Edit Messages ");
        System.out.println("4 : Delete Messages ");
        System.out.println("***********USER ADMINISTRATION**********************");
        System.out.println("5 : Create User ");
        System.out.println("6 : Delete User ");
        System.out.println("7 : Update User ");
        System.out.println("8 : Exit application "); 
        System.out.println("****************************************************");
        
        
        
        
        Operations operations = new Operations(id, username, DB);
        int option;        
        do{
            option = CheckInput(2); 
            operations.SuperAdminActions(option);
        } while(option <= 8 ); 
        
    
    }
    
    private void ModeratorAccount(String username, int id) throws SQLException{
        System.out.println("Welcome "+username);
        System.out.println("****************************************************");
        System.out.println("1: Read Messages ");
        System.out.println("2: Send Messages ");
        System.out.println("3: Edit Messages ");
        System.out.println("4: Exit application"); 
        System.out.println("****************************************************");
        
        
        
        
        Operations operations = new Operations(id, username, DB);
        int option;        
        do{
            option = CheckInput(4); 
            operations.Moderators(option);
        } while(option <= 4 );   
    }
    
    private void ModeratorAccount1(String username, int id) throws SQLException{
        System.out.println("Welcome "+username);
        System.out.println("****************************************************");
        System.out.println("1: Read Messages ");
        System.out.println("2: Send Messages ");
        System.out.println("3: Edit Messages ");
        System.out.println("4: Delete Messages ");
        System.out.println("5: Exit application"); 
        System.out.println("****************************************************");
        
        
        
        
        Operations operations = new Operations(id, username, DB);
        int option;        
        do{
            option = CheckInput(5); 
            operations.Moderators1(option);
        } while(option <= 5 );   
    }
    
    
    
    private void SimpleMemberAccount(String username, int id) throws SQLException{
        System.out.println("Welcome "+username);
        System.out.println("****************************************************");
        System.out.println("1: Read Messages");
        System.out.println("2: Send Messages ");
        System.out.println("3: Exit application "); 
        System.out.println("****************************************************");
        
        Operations operations = new Operations(id, username, DB);
        int option;   
        do{
            option = CheckInput(6); 
            operations.SimpleMemberActions(option);
        } while(option <= 3 );  
    }
    
    private int CheckInput(int user){
        int userInput = 0;
        
        int MaxMenuNumber=0;        
        if (user==2)
            MaxMenuNumber=8;
        if (user==4)
            MaxMenuNumber=4;
        if (user==5)
            MaxMenuNumber=5;
        if (user==6)
            MaxMenuNumber=3;
         
        do{
            System.out.print("Please press a number between 1 and "+MaxMenuNumber+ " to choose an operation: ");
            try{
                userInput = Integer.parseInt(input.nextLine());
                
            } catch(NumberFormatException e){
               // System.out.println("Throws exception "+e);
            }   
        }while(userInput <= 0 || userInput > MaxMenuNumber );
             
        return userInput;
    
    }
    


}
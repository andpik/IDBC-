package mainapplication;

import java.sql.SQLException;


/**
 *
 * @author Andreas
 */
public class Operations {
    
    private DatabaseAccess DB;
    private StringBuilder transactions = new StringBuilder();
    private final int user_id;
    private final String userName;
    
    public Operations(int user_id, String userName, DatabaseAccess DB){
            this.user_id = user_id;
            this.userName = userName;
            this.DB = DB;
    }
    
    private void DisplayMessages(){
        DB.DisplayMessages();

    }
//    
    private void SendMessages() throws SQLException{
        DB.SendMessages(userName);
    }
//    
    private void DeleteMessages() throws SQLException{
        DB.DeleteMessages();
    }
//    
    private void DeleteUser() throws SQLException{
        DB.DeleteUser();
    }    
//    
    private void CreateUser() throws SQLException{
        DB.CreateUser();
    }  
//    
    private void UpdateUser() throws SQLException{
        DB.UpdateUser();
    }
    
      
    private void EditMessages() throws SQLException{
        DB.EditMessages();
    }
    
    
    
    
    
    public void SimpleMemberActions(int action) throws SQLException{
        switch (action) {
            case 1:
                DisplayMessages();
                
                break;
            case 2:
                SendMessages();
                break;
            case 3:
                ExitApp();
                break;
            default:
                break;
        }
    }
 
   public void Moderators(int action) throws SQLException{
       switch (action) {
            case 1:
                 DisplayMessages();
                break;
            case 2:
                 SendMessages();
                break;
            case 3:
                EditMessages();
                break;
            case 4:
                ExitApp();
                break;

            default:
                break;              
      }
   } 
    
  public void Moderators1(int action) throws SQLException{
       switch (action) {
            case 1:
                 DisplayMessages();
                break;
            case 2:
                 SendMessages();
                break;
            case 3:
                EditMessages();
                break;
            case 4:
                DeleteMessages();
                break;
            case 5:
                ExitApp();
                break;   
            default:
                break;              
      }
   }   
    
    
    
    
    public void SuperAdminActions(int action) throws SQLException{
        switch (action) {
             case 1:
                 DisplayMessages();                 
                break;
            case 2:
                 SendMessages();
                break;
            case 3:
                EditMessages();
                break;
            case 4:
                DeleteMessages();
                break;
            case 5:
                CreateUser();
                break;    
            case 6:
                DeleteUser();
                break;    
            case 7:
                UpdateUser();
                break;
            case 8:
                ExitApp();
                break;   
            default:
                break;          
        }
    }
    
 public void ExitApp(){
     DB.closeResources(); 
     System.out.println("Application Exits");
     
     System.exit(0);
 }


}
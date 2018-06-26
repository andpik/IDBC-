package mainapplication;

import java.io.IOException;
import java.sql.SQLException;



/**
 *
 * @author Andreas
 */
public class MainApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DatabaseAccess DB = new DatabaseAccess();
        DB.ConnectToDatabase(); 
        LoginScreen login = new LoginScreen(DB);      
 
    }
    
     
}
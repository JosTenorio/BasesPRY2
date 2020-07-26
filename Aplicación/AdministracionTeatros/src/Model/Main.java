
package Model;

import Controller.MainMenuController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args){
        ConnectionManager.connect();
    }      
}
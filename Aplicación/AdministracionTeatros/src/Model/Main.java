
package Model;

import Controller.MainMenuController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args){
        
        try {
            //Para hacer log in a la base de datos se debe especificar la direccion ip
            
            //ConnectionManager.logIn("192.168.0.5", "sa", "2019064588");
            ConnectionManager.InitialLogIn("25.79.234.220");
            //ConnectionManager.executePubLoginAdmTe("nangulo","ped7Kie");
            try {
                int x = System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConnectionManager.executeAdmTeCreateObra ("Pepe", "Danza");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

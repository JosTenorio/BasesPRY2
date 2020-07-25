
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
            ConnectionManager.LogIn("25.79.234.220");
            /*
            if (ConnectionManager.executePubLoginAdmTe("nangulo","ped7Kie") == 1) {
                System.out.println("Logueado como administrador");
            }
            else {
                System.out.println("Login fallido");
            }
            */
            while (true) {
                try {
                    int x = System.in.read();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                ConnectionManager.executeAdmTeReadTipos();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
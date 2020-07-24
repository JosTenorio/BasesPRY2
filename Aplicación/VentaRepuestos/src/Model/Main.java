
package Model;

import Controller.MainMenuController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args){
        
        //Para hacer log in a la base de datos se debe especificar la direccion ip, el nombre del administrador y la contrasena de esta
        
        //ConnectionManager.logIn("192.168.0.5", "sa", "2019064588");
        ConnectionManager.logIn("25.12.222.208", "ApplicationLogin1", "ElGalloDeDatos25");
        try {
            ConnectionManager.connect();
            System.out.println ("Conectado");
            try {
                int x = System.in.read();
                ConnectionManager.connection.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

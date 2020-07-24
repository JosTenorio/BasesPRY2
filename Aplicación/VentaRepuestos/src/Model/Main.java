
package Model;

import Controller.MainMenuController;

public class Main {
    
    public static void main(String[] args){
        
        //Para hacer log in a la base de datos se debe especificar la direccion ip, el nombre del administrador y la contrasena de esta
        
        //ConnectionManager.logIn("192.168.0.5", "sa", "2019064588");
        ConnectionManager.logIn("192.168.100.45", "sa", "12345");
        MainMenuController.getInstance().makeVisible(true);
    } 
}

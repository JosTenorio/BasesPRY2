
package Model;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ErrorManager {
    
    public static void errorPubLoginAdmSis  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("[CustomError]")) {
            infoBox (message,"Login fallido");
        }
        else if (message.contains ("EXECUTE")) {
            infoBox ("Usted ya esta logueado como un administrador del sistema, cierre la aplicación"
                    + "y espere unos segundos si desea ingresar como otro usuario","Login fallido");
        } 
        else {
            infoBox (message,"Operación no realizada");
        }
    }
    
    public static void errorPubLoginAdmTe  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("[CustomError]")) {
            infoBox (message,"Login fallido");
        }
        else if (message.contains ("EXECUTE")) {
            infoBox ("Usted ya esta logueado como un administrador de teatro, cierre la aplicación"
                    + "y espere unos segundos si desea ingresar como otro usuario","Login fallido");
        } 
        else {
            infoBox (message,"Operación no realizada");
        }
    }
    
    public static void errorPubLoginAgnTe  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("[CustomError]")) {
            infoBox (message,"Login fallido");
        }
        else if (message.contains ("EXECUTE")) {
            infoBox ("Usted ya esta logueado como un agente de teatro, cierre la aplicación"
                    + "y espere unos segundos si desea ingresar como otro usuario","Login fallido");
        } 
        else {
            infoBox (message,"Operación no realizada");
        }
    }
    
    public static void errorPubReadProducciones  (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorPubReadPresentaciones (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorPubReadBloquesProducciones (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorPubReadAsientosPresentaciones (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }

    public static void errorPubReadCompraResumen(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorPubCreateCompraTarjeta(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeReadProducciones (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeReadPresentaciones(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeReadBloquesProducciones(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}

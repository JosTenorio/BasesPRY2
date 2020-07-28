
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
    
    public static void errorAgnTeReadAsientosPresentaciones(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeReadCompraResumen(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeCreateCompraTarjeta(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAgnTeCreateCompraEfectivo(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeReadTipos(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreateTipo(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeReadObras(SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreateObra (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreateProduccion (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
   
    public static void errorAdmTeReadProducciones  (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeReadPresentaciones  (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreatePresentacion (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeReadBloques (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreateBloqueProduccion (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeReadEstados (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeUpdateProduccionEstado (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmTeCreateEmpleadoAgnTe (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisCreateTeatro (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisReadTeatros  (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisReadBloques  (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisCreateBloque (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisReadAsientos (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisCreateAsiento (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisReadRegistroPagos (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisReadEmpleados (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    public static void errorAdmSisCreateEmpleadoAdmTe (SQLException exception) {
        String message = exception.getMessage();
        infoBox ("message", "Operacion no realizada");
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}

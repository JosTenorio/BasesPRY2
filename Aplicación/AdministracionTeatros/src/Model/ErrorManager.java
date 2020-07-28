
package Model;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ErrorManager {
    
    public static void errorPubLoginAdmSis  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("[CustomError]")) {
            infoBox (message.replace("[CustomError]", ""), "Login fallido");
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
            infoBox (message.replace("[CustomError]", ""), "Login fallido");
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
            infoBox (message.replace("[CustomError]", ""), "Login fallido");
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
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorPubReadPresentaciones (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorPubReadBloquesProducciones (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorPubReadAsientosPresentaciones (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }

    public static void errorPubReadCompraResumen(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorPubCreateCompraTarjeta(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeReadProducciones (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeReadPresentaciones(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeReadBloquesProducciones(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeReadAsientosPresentaciones(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeReadCompraResumen(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeCreateCompraTarjeta(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAgnTeCreateCompraEfectivo(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeReadTipos(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreateTipo(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnTipos_Nombre")){
            infoBox ("El tipo de obra dado ya existe", "Operacion no realizada");        
        }
        else if (message.contains("CkTipos_Nombre")){
            infoBox ("El nombre del tipo de obra no puede ser vacío", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeReadObras(SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreateObra (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("CkObras_Nombre")){
            infoBox ("El nombre de la obra no puede estar vacío", "Operacion no realizada");        
        }
        else if (message.contains("UnObras_NombreYIdTipo")){
            infoBox ("Ya existe una obra del mismo tipo y con el mismo nombre, por lo que"
                    + "no se puede crear una obra con esos datos", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreateProduccion (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnProducciones_IdTeatroYIdObra")){
            infoBox ("La produccion dada ya existe", "Operacion no realizada");        
        }
        else if (message.contains("UnProducciones_IdTeatroYIdObra")){
            infoBox ("La produccion dada ya existe", "Operacion no realizada");        
        }
        else if (message.contains("CkProducciones_FechaFin")){
            infoBox ("La fecha final de una produccion debe darse a partir de su fecha de inicio", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
   
    public static void errorAdmTeReadProducciones  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeReadPresentaciones  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreatePresentacion (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnPresentaciones_FechaHoraInicioYIdProduccion")){
            infoBox ("Ya hay una presentacion para la produccion dada a esa hora", "Operacion no realizada");        
        }
        else if (message.contains("FkPresentaciones_IdProduccion")){
            infoBox ("El identificador de producción dado no corresponde a ninguna producción registrada", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeReadBloques (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreateBloqueProduccion (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnBloquesProducciones_IdBloqueYIdProduccion")){
            infoBox ("El bloque indicado ya tiene un precio registrado para la producción indicada", "Operacion no realizada");        
        }
        else if (message.contains("CkBloquesProducciones_Precio")){
            infoBox ("El precio a registrar debe ser mayor que cero", "Operacion no realizada");        
        }
        else if (message.contains("FkBloquesProducciones_IdBloque")){
            infoBox ("El identificador de bloque dado no corresponde a ningún bloque en la base de datos", "Operacion no realizada");        
        }
        else if (message.contains("FkBloquesProducciones_IdProduccion")){
            infoBox ("El identificador de producción dado no corresponde a ninguna producción registrada", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeReadEstados (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeUpdateProduccionEstado (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("FkProducciones_IdEstado")){
            infoBox ("El identificador de estado indicado no esta registrado en la base de datos", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmTeCreateEmpleadoAgnTe (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnEmpleados_Cedula")){
            infoBox ("La cédula indicada ya esta registrada en la base de datos", "Operacion no realizada");        
        }
        else if (message.contains("UnEmpleados_TelCelular")){
            infoBox ("El teléfono celular indicado ya se encuentra registrado a nombre de otro usuario", "Operacion no realizada");        
        }
        else if (message.contains("UnEmpleados_Usuario")){
            infoBox ("El usuario provisto para el nuevo agente ya esta en uso en el sistema", "Operacion no realizada");        
        }
        else if (message.contains("FkEmpleados_IdTeatro")){
            infoBox ("El identificador de teatro indicado no corresponde a ningún teatro registrado", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Cedula")){
            infoBox ("La cédula indicada no es válida (9 dígitos)", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Sexo")){
            infoBox ("El sexo del nuevo agente se debe indicar como masculino (M) o femenino (F)", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Correo")){
            infoBox ("El correo indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelCasa")){
            infoBox ("El teléfono de casa indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelCelular")){
            infoBox ("El teléfono celular indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelOtro")){
            infoBox ("El teléfono alternativo indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Nombre")){
            infoBox ("El nombre del nuevo usuario del sistema no puede estar vacío", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Tipo")){
            infoBox ("El nuevo usuario debe ser asignado el rol de agente o administrador de teatro", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisCreateTeatro (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnTeatros_Nombre")){
            infoBox ("El nombre de teatro indicado ya se encuentra registrado en el sistema", "Operacion no realizada");        
        }
        else if (message.contains("UnTeatros_Correo")){
            infoBox ("El correo electrónico indicado ya se encuentra a nombre de otro teatro registrado", "Operacion no realizada");        
        }
        else if (message.contains("UnTeatros_Link")){
            infoBox ("El sitio web indicado ya se encuentra a nombre de otro teatro registrado", "Operacion no realizada");        
        }
        else if (message.contains("UnTeatros_TelAdmin")){
            infoBox ("El teléfono de la administración indicado ya se encuentra a nombre de otro teatro registrado", "Operacion no realizada");        
        }
        else if (message.contains("CkTeatros_Nombre")){
            infoBox ("El nombre del teatro a registrar no puede estar vacío", "Operacion no realizada");        
        }
        else if (message.contains("CkTeatros_TelBoleteria")){
            infoBox ("El teléfono de boletería indicado es inválido", "Operacion no realizada");        
        }
        else if (message.contains("CkTeatros_TelAdmin")){
            infoBox ("El teléfono de administración indicado es inválido", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisReadTeatros  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisReadBloques  (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisCreateBloque (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("CkBloques_Nombre")){
            infoBox ("El nombre del bloque a agregar no puede estar vacío", "Operacion no realizada");        
        }
        else if (message.contains("UnBloques_NombreYIdTeatro")){
            infoBox ("El nombre de bloque indicado ya existe en el teatro dado", "Operacion no realizada");        
        }
        else if (message.contains("FkBloques_IdTeatro")){
            infoBox ("El identificador de teatro dado no corresponde a ningún teatro registrado", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisReadAsientos (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisCreateAsiento (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnAsientos_FilaYColumnaYIdBloque")){
            infoBox ("El asiento indicado ya existe", "Operacion no realizada");        
        }
        else if (message.contains("FkAsientos_IdBloque")){
            infoBox ("El identificador de bloque indicado no corresponde a ningún bloque registrado", "Operacion no realizada");        
        }
        else if (message.contains("CkAsientos_Columna")){
            infoBox ("El número de asiento debe ser mayor que cero", "Operacion no realizada");        
        }
        else if (message.contains("CkAsientos_Fila")){
            infoBox ("El nombre de fila indicado es inválido o no corresponde a ninguna fila registrada", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisReadRegistroPagos (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisReadEmpleados (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    public static void errorAdmSisCreateEmpleadoAdmTe (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("[CustomError]")){
            infoBox (message.replace("[CustomError]", ""), "Operacion no realizada");
        }
        else if (message.contains("UnEmpleados_Cedula")){
            infoBox ("La cédula indicada ya esta registrada en la base de datos", "Operacion no realizada");        
        }
        else if (message.contains("UnEmpleados_TelCelular")){
            infoBox ("El teléfono celular indicado ya se encuentra registrado a nombre de otro usuario", "Operacion no realizada");        
        }
        else if (message.contains("UnEmpleados_Usuario")){
            infoBox ("El usuario provisto para el nuevo administrador ya esta en uso en el sistema", "Operacion no realizada");        
        }
        else if (message.contains("FkEmpleados_IdTeatro")){
            infoBox ("El identificador de teatro indicado no corresponde a ningún teatro registrado", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Cedula")){
            infoBox ("La cédula indicada no es válida (9 dígitos)", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Sexo")){
            infoBox ("El sexo del nuevo administrador se debe indicar como masculino (M) o femenino (F)", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Correo")){
            infoBox ("El correo indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelCasa")){
            infoBox ("El teléfono de casa indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelCelular")){
            infoBox ("El teléfono celular indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_TelOtro")){
            infoBox ("El teléfono alternativo indicado no es válido", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Nombre")){
            infoBox ("El nombre del nuevo usuario del sistema no puede estar vacío", "Operacion no realizada");        
        }
        else if (message.contains("CkEmpleados_Tipo")){
            infoBox ("El nuevo usuario debe ser asignado el rol de agente o administrador de teatro", "Operacion no realizada");        
        }
        else{
            infoBox (message, "Operacion no realizada");        
        }
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}

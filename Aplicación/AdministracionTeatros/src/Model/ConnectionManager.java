
package Model;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.math.BigDecimal;
import java.sql.Types;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ConnectionManager {
    public static Connection connection = null;
    private static String Ip = "";
    private static String Username = "";
    private static String Password = "";
    
    public static void LogIn(String ip){
        Ip = ip;
        Password = "ElGalloDeDatos25";
        Username = "ApplicationLogin";
        for (int i = 1; i <= 5; i++){
            Username += Integer.toString (i);
            try {
                connect();
                return;
            } catch (SQLException ex) {
                System.out.println (ex.getMessage());
                Username = Username.substring(0, Username.length() - 1);
            }  
        }
        infoBox ("Hay muchos usuarios conectados en este momento, intente de nuevo más tarde", "Ingreso denegado");
    }
    
    public static void connect() throws SQLException {
        String url = "jdbc:sqlserver://" + Ip  + ";databaseName=BasesPRY2;user=" + Username + ";password=" + Password;
        connection = DriverManager.getConnection(url);
    }
    
    public static int executePubLoginAdmSis (String username, String password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAdmSis(?, ?)}");
       cstmt.registerOutParameter(1, Types.INTEGER);
       cstmt.setString(2, username);
       cstmt.setString(3, password);
       cstmt.executeQuery();
       int result = cstmt.getInt(1);
       cstmt.close();
       return result;
    } 
    
    public static int executePubLoginAdmTe (String username, String password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAdmTe(?, ?)}");
       cstmt.registerOutParameter(1, Types.INTEGER);
       cstmt.setString(2, username);
       cstmt.setString(3, password);
       cstmt.execute();
       int result = cstmt.getInt(1);
       cstmt.close();
       return result;
    }
    
    public static int executePubLoginAgnTe (String username, String password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAgnTe(?, ?)}");
       cstmt.registerOutParameter(1, Types.INTEGER);
       cstmt.setString(2, username);
       cstmt.setString(3, password);
       cstmt.executeQuery();
       int result = cstmt.getInt(1);
       cstmt.close();
       return result;
    } 
    
    public static ResultSet executeAdmSisReadTeatros () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadTeatros()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static void executeAdmSisCreateAsiento (int IdBloque, int Cantidad) throws SQLException {
        CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateAsiento(?,?)}");
        cstmt.setInt(1, IdBloque);
        cstmt.setInt(2, Cantidad);
        cstmt.executeQuery();
        cstmt.close();
    }
    
    public static void executeAdmSisCreateBloque (int IdTeatro,String Nombre ) throws SQLException {
        CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateBloque(?,?)}");
        cstmt.setInt(1, IdTeatro);
        cstmt.setString(2, Nombre);
        cstmt.executeQuery();
        cstmt.close();
    }
    
    public static void executeAdmSisCreateEmpleadoAdmTe (String Cedula, String Nombre, String FechaNacimiento,
    String Direccion, String Sexo, String Correo, String Usuario, String Contrasena, String TelCelular,
    String TelCasa, String TelOtro, int IdTeatro) throws SQLException {
        CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateEmpleadoAdmTe(?,?,?,?,?,?,?,?,?,?,?,?)}");
        cstmt.setString(1, Cedula);
        cstmt.setString(2, Nombre);
        cstmt.setDate(3, java.sql.Date.valueOf(FechaNacimiento));
        cstmt.setString(4, Direccion);
        cstmt.setString(5, Sexo);
        cstmt.setString(6, Correo);
        cstmt.setString(7, Usuario);
        cstmt.setString(8, Contrasena);
        cstmt.setString(9, TelCelular);
        cstmt.setString(10, TelCasa);
        cstmt.setString(11, TelOtro);
        cstmt.setInt(12, IdTeatro);
        cstmt.executeQuery();
        cstmt.close();
    }
    
    public static void executeAdmSisCreateTeatro (String Nombre, String Direccion, String Correo, String Link,
            String TelBoleteria, String TelAdmin) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateTeatro (?,?,?,?,?,?)}");
       cstmt.setString (1, Nombre);
       cstmt.setString (2, Direccion);
       cstmt.setString (3, Correo);
       cstmt.setString (4, Link);
       cstmt.setString (5, TelBoleteria);
       cstmt.setString (6, TelAdmin);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static ResultSet executeAdmSisReadAsientos (int IdBloque) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadAsientos (?)}");
       cstmt.setInt (1, IdBloque );
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmSisReadBloques (int IdTeatro) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadBloques (?)}");
       cstmt.setInt (1, IdTeatro);
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmSisReadEmpleados (int Tipo) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadEmpleados (?)}");
       cstmt.setInt (1, Tipo);
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmSisReadRegistroPagos () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadRegistroPagos ()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static void executeAdmTeCreateBloqueProduccion(int IdProduccion, int IdBloque, float Precio,
    String User, String Password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateBloqueProduccion (?,?,?,?,?)}");
       cstmt.setInt(1, IdProduccion);
       cstmt.setInt(2, IdBloque);
       BigDecimal PrecioBigDecimal = new BigDecimal(Float.toString(Precio));
       cstmt.setBigDecimal(3,PrecioBigDecimal);
       cstmt.setString (4, User);
       cstmt.setString (5, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAdmTeCreateEmpleadoAgnTe (String Cedula, String Nombre, String FechaNacimiento,
    String Direccion, String Sexo, String Correo, String Usuario, String Contrasena, String TelCelular,
    String TelCasa, String TelOtro, String User, String Password) throws SQLException {
        CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateEmpleadoAgnTe(?,?,?,?,?,?,?,?,?,?,?,?, ?)}");
        cstmt.setString(1, Cedula);
        cstmt.setString(2, Nombre);
        cstmt.setDate(3, java.sql.Date.valueOf(FechaNacimiento));
        cstmt.setString(4, Direccion);
        cstmt.setString(5, Sexo);
        cstmt.setString(6, Correo);
        cstmt.setString(7, Usuario);
        cstmt.setString(8, Contrasena);
        cstmt.setString(9, TelCelular);
        cstmt.setString(10, TelCasa);
        cstmt.setString(11, TelOtro);
        cstmt.setString(12, User);
        cstmt.setString(13, Password);
        cstmt.executeQuery();
        cstmt.close();
    }
    
    public static void executeAdmTeCreateObra (String Nombre, String Descripcion, int IdTipo) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?, ?)}");
       cstmt.setString(1, Nombre);
       cstmt.setString(2, Descripcion);
       cstmt.setInt (3, IdTipo);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAdmTeCreatePresentacion (int IdProduccion , String FechaHoraInicio, String User,
            String Password ) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreatePresentacion(?, ?, ?, ?)}");
       cstmt.setInt(1, IdProduccion);
       cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraInicio));
       cstmt.setString(3, User);
       cstmt.setString(4, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAdmTeCreateProduccion (int IdObra, String User, String Password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateProduccion(?, ?, ?)}");
       cstmt.setInt(1, IdObra);
       cstmt.setString(2, User);
       cstmt.setString(3, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAdmTeCreateTipo (String Nombre) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateTipo(?)}");
       cstmt.setString(1, Nombre);
       cstmt.executeQuery();
       cstmt.close();  
    }
    
    public static ResultSet executeAdmTeReadBloques (String User, String Password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadBloques (?,?)}");
       cstmt.setString(1, User);
       cstmt.setString(2, Password);
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmTeReadEstados () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadEstados ()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmTeReadObras () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadObras ()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static ResultSet executeAdmTeReadProducciones (String User, String Password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadProducciones (?,?)}");
       cstmt.setString(1, User);
       cstmt.setString(2, Password);
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
     public static ResultSet executeAdmTeReadTipos () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadTipos ()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
     
    public static void executeAdmTeUpdateProduccionEstado (int IdProduccion, int IdEstado, 
       String User, String Password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeUpdateProduccionEstado (?,?,?,?)}");
       cstmt.setInt(1, IdProduccion);
       cstmt.setInt(2, IdEstado);
       cstmt.setString(3, User);
       cstmt.setString(4, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAgnTeCreateCompraEfectivo (ArrayList<Integer> Ids, String Nombre, String Telefono, String Correo,
       String User, String Password) throws SQLException {
       SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call AgnTeCreateCompraEfectivo (?,?,?,?,?,?)}");
       SQLServerDataTable ListaAsientos = new SQLServerDataTable();
       ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
       for (int Id: Ids) {
           ListaAsientos.addRow(Id);
       }
       cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
       cstmt.setString(2, Nombre);
       cstmt.setString(3, Telefono);
       cstmt.setString(4, Correo);
       cstmt.setString(5, User);
       cstmt.setString(6, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static void executeAgnTeCreateCompraTarjeta (ArrayList<Integer> Ids, String Nombre, String Telefono, String Correo,
       String Tarjeta, String Expira, String CVV, String User, String Password) throws SQLException {
       SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call AgnTeCreateCompraTarjeta (?,?,?,?,?,?,?,?,?)}");
       SQLServerDataTable ListaAsientos = new SQLServerDataTable();
       ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
       for (int Id: Ids) {
           ListaAsientos.addRow(Id);
       }
       cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
       cstmt.setString(2, Nombre);
       cstmt.setString(3, Telefono);
       cstmt.setString(4, Correo);
       cstmt.setString(5, Tarjeta);
       cstmt.setDate(6, java.sql.Date.valueOf(Expira));
       cstmt.setString(5, CVV);
       cstmt.setString(8, User);
       cstmt.setString(9, Password);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    
    
    public static ResultSet executePubReadAsientosPresentaciones (int IdPresentacion , int IdBloque) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?)}");
       cstmt.setInt(1, IdPresentacion);
       cstmt.setInt(1, IdBloque);
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
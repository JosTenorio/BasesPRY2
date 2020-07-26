
package Model;

import java.math.BigDecimal;
import java.sql.Types;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
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
       cstmt.setBigDecimal(3, java.sql.Types.DECIMAL);
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
    
     public static ResultSet executeAdmTeReadTipos () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeReadTipos}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
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
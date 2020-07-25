
package Model;

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

public class ConnectionManager {
    public static Connection connection = null;
    private static String Ip = "";
    private static String Username = "";
    private static String Password = "";
    
    public static void InitialLogIn(String ip){
        Ip = ip;
        Password = "ElGalloDeDatos25";
        Username = "ApplicationLogin";
        for (int i = 1; i <= 5; i++){
            Username += Integer.toString (i);
            try {
                connect();
                System.out.println ("Conectado con: " + Username + ", " + Password);
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
    
    public static ResultSet executeAdmSisReadTeatros () throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmSisReadTeatros()}");
       ResultSet rs = cstmt.executeQuery();
       cstmt.close();
       return rs;
    }
    
    public static int executePubLoginAdmSis (String username, String password) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAdmSis(?, ?)}");
       cstmt.setString(1, username);
       cstmt.setString(2, password);
       cstmt.registerOutParameter(1, Types.INTEGER);
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
       cstmt.setString(1, username);
       cstmt.setString(2, password);
       cstmt.registerOutParameter(1, Types.INTEGER);
       cstmt.executeQuery();
       int result = cstmt.getInt(1);
       cstmt.close();
       return result;
    } 
    
    public static void executeAdmTeCreateObra (String Nombre, String Descripcion) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?)}");
       cstmt.setString(1, Nombre);
       cstmt.setString(2, Descripcion);
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
       return cstmt.executeQuery();
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
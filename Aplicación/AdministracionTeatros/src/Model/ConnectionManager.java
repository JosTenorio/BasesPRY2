
package Model;

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
       cstmt.close();
       return cstmt.executeQuery();
    } 
    
    public static void executeAdmTeCreateObra (String Nombre, String Descripcion) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?)}");
       cstmt.setString(1, Nombre);
       cstmt.setString(1, Descripcion);
       cstmt.executeQuery();
       cstmt.close();
    }
    
    public static ResultSet executePubReadAsientosPresentaciones (int IdPresentacion , int IdBloque) throws SQLException {
       CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?)}");
       cstmt.setInt(1, IdPresentacion);
       cstmt.setInt(1, IdBloque);
       cstmt.close();
       return cstmt.executeQuery();
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
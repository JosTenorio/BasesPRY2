
package Model;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Types;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import javax.sql.rowset.*; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


public class ConnectionManager {
    private static Connection connection = null;
    public static String Ip = "";
    public static String Username = "";
    public static String Password = "";
    
    public static boolean connect(){
        Ip = "25.12.222.208";
        Username = "ApplicationLogin";
        Password = "ElGalloDeDatos25";
//        if (!loadLoginData ()){
//            return false;
//        }
        for (int i = 1; i <= 5; i++){
            Username += Integer.toString (i);
            try {
                obtainConnection();
                return true;
            } catch (SQLException ex) {
                Username = Username.substring(0, Username.length() - 1);
            }  
        }
        Utilities.infoBox ("Hay muchos usuarios conectados en este momento o los datos de login son incorrectos, intente de nuevo más tarde", "Ingreso denegado");
        return false;
    }
    
    private static boolean loadLoginData () {
        JFileChooser chooser= new JFileChooser();
        Frame frame = new Frame("Favor seleccionar el archivo de datos de login");
        int width = 10;
        int height = 10;
        frame.setSize(width, height);
        frame.setVisible(true);
        int choice = chooser.showOpenDialog(frame);
        if (choice != JFileChooser.APPROVE_OPTION){
            Utilities.infoBox ("El archivo indicado no corresponde a un archivo de datos de login", "Error");
            frame.dispose();
            return false;
        }
        File LoginFile = chooser.getSelectedFile();
        if (!"txt".equals(getExtension (LoginFile))){
            Utilities.infoBox ("El archivo indicado no corresponde a un archivo de datos de login", "Error");
            frame.dispose();
            return false;
        }
        BufferedReader br;  
        try {
            br = new BufferedReader(new FileReader(LoginFile));
        } catch (FileNotFoundException ex) {
            Utilities.infoBox ("El archivo indicado no corresponde a un archivo de datos de login", "Error");
            frame.dispose();
            return false;
        }
        try {
            Ip = br.readLine().substring(7);
            Username = br.readLine().substring(13);
            Password = br.readLine().substring (13);
        } catch (IOException ex) {
            Utilities.infoBox ("El archivo indicado no corresponde a un archivo de datos de login", "Error");
            frame.dispose();
            return false;
        }
        frame.dispose();
        return true;
    }
    
    private static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    private static void obtainConnection() throws SQLException {
        String url = "jdbc:sqlserver://" + Ip  + ";databaseName=BasesPRY2;user=" + Username + ";password=" + Password;
        connection = DriverManager.getConnection(url);
    }
    
    public static boolean execPubLoginAdmSis (String username, String password) {
        try (CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAdmSis(?, ?)}")) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.setString(3, password);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 1){
                cstmt.close();
                return true;
            }
        } catch (SQLException ex) {
            ErrorManager.errorPubLoginAdmSis(ex);
        }
        return false;
    } 
    
    public static boolean execPubLoginAdmTe (String username, String password) {
        try (CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAdmTe(?, ?)}")) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.setString(3, password);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 1){
                cstmt.close();
                return true;
            }
        } catch (SQLException ex) {
            ErrorManager.errorPubLoginAdmTe(ex);
        }
        return false;
    }
    
    public static boolean execPubLoginAgnTe (String username, String password) {
        try (CallableStatement cstmt = connection.prepareCall("{? = call PubLoginAgnTe(?, ?)}")) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, username);
            cstmt.setString(3, password);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 1){
                cstmt.close();
                return true;
            }
        } catch (SQLException ex) {
            ErrorManager.errorPubLoginAgnTe(ex);
        }
        return false;
    } 
    
    public static void executeAdmSisCreateEmpleadoAdmTe (String Cedula, String Nombre, String FechaNacimiento, String Direccion, String Sexo, String Correo, String Usuario, String Contrasena, String TelCelular, String TelCasa, String TelOtro, int IdTeatro) throws SQLException {
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
    
    public static ArrayList<String[]> executeAdmSisReadEmpleados (int Tipo) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisReadEmpleados (?)}")) {
            if (Tipo == 0)
                cstmt.setNull(1, Types.INTEGER);
            else
                cstmt.setInt (1, Tipo);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> executeAdmSisReadRegistroPagos () {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisReadRegistroPagos ()}")) {
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmSisCreateAsiento (int IdBloque, int Cantidad) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateAsiento(?,?)}")) {
            cstmt.setInt(1, IdBloque);
            cstmt.setInt(2, Cantidad);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<String[]> execAdmSisReadAsientos (int IdBloque) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisReadAsientos (?)}")) {
            cstmt.setInt (1, IdBloque );
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmSisCreateBloque (int IdTeatro, String Nombre ) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateBloque(?,?)}")) {
            cstmt.setInt(1, IdTeatro);
            cstmt.setString(2, Nombre);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<String[]> execAdmSisReadBloques (int IdTeatro) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisReadBloques (?)}")) {
            cstmt.setInt (1, IdTeatro);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execAdmSisReadTeatros () {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisReadTeatros()}")) {
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmSisCreateTeatro (String Nombre, String Direccion, String Correo, String Link, String TelBoleteria, String TelAdmin) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmSisCreateTeatro (?,?,?,?,?,?)}")) {
            cstmt.setString (1, Nombre);
            cstmt.setString (2, Direccion);
            cstmt.setString (3, Correo);
            cstmt.setString (4, Link);
            cstmt.setString (5, TelBoleteria);
            cstmt.setString (6, TelAdmin);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void execAdmTeCreateEmpleadoAgnTe (String Cedula, String Nombre, String FechaNacimiento, String Direccion, String Sexo, String Correo, String Usuario, String Contrasena, String TelCelular, String TelCasa, String TelOtro, String User, String Password) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateEmpleadoAgnTe(?,?,?,?,?,?,?,?,?,?,?,?, ?)}")) {
            cstmt.setString(1, Cedula);
            cstmt.setString(2, Nombre);
            cstmt.setDate(3, java.sql.Date.valueOf(FechaNacimiento));
            cstmt.setString(4, Direccion);
            cstmt.setString(5, Sexo);
            cstmt.setString(6, Correo);
            cstmt.setString(7, Usuario);
            cstmt.setString(8, Contrasena);
            cstmt.setString(9, TelCelular);
            if ("".equals(TelCasa))
                cstmt.setNull(10, Types.NCHAR);
            else
                cstmt.setString(10, TelCasa); 
            if ("".equals(TelOtro))
                cstmt.setNull(11, Types.NCHAR);
            else
                cstmt.setString(11, TelOtro);
            cstmt.setString(12, User);
            cstmt.setString(13, Password);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreateEmpleadoAgnTe(ex);
        }
    }
    
     public static void execAdmTeUpdateProduccionEstado (int IdProduccion, int IdEstado, String User, String Password) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeUpdateProduccionEstado (?,?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            cstmt.setInt(2, IdEstado);
            cstmt.setString(3, User);
            cstmt.setString(4, Password);
            cstmt.execute();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeUpdateProduccionEstado(ex);
        }
    }
     
    public static ArrayList<String[]> execAdmTeReadEstados () {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadEstados ()}")) {
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeReadEstados(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmTeCreateBloqueProduccion(int IdProduccion, int IdBloque, float Precio, String User, String Password) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateBloqueProduccion (?,?,?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            cstmt.setInt(2, IdBloque);
            BigDecimal PrecioBigDecimal = new BigDecimal(Float.toString(Precio));
            cstmt.setBigDecimal(3,PrecioBigDecimal);
            cstmt.setString(4, User);
            cstmt.setString(5, Password);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreateBloqueProduccion(ex);
        }
    }
    
    public static ArrayList<String[]> execAdmTeReadBloques (String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadBloques (?,?)}")) {
            cstmt.setString(1, User);
            cstmt.setString(2, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeReadBloques(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmTeCreatePresentacion (int IdProduccion, String FechaHoraInicio, String User, String Password ) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreatePresentacion(?,?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraInicio));
            cstmt.setString(3, User);
            cstmt.setString(4, Password);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreatePresentacion(ex);
        }
    }
    
    public static ArrayList<String[]> execAdmTeReadPresentaciones (int IdProduccion, String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadPresentaciones (?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            cstmt.setString(2, User);
            cstmt.setString(3, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeReadPresentaciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execAdmTeReadProducciones (String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadProducciones (?,?)}")) {
            cstmt.setString(1, User);
            cstmt.setString(2, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeReadProducciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmTeCreateProduccion (int IdObra, String User, String Password) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateProduccion(?, ?, ?)}")) {
            cstmt.setInt(1, IdObra);
            cstmt.setString(2, User);
            cstmt.setString(3, Password);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreateProduccion(ex);
        }
    }
    
    public static void execAdmTeCreateObra (String Nombre, String Descripcion, int IdTipo) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateObra(?, ?, ?)}")) {
            cstmt.setString(1, Nombre);
            cstmt.setString(2, Descripcion);
            cstmt.setInt (3, IdTipo);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreateObra(ex);
        }
    }
    
    public static ArrayList<String[]> execAdmTeReadObras () {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadObras ()}")) {
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
           ErrorManager.errorAdmTeReadObras(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static void execAdmTeCreateTipo (String Nombre) {
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeCreateTipo(?)}")) {
            cstmt.setString(1, Nombre);
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeCreateTipo(ex);
        }  
    }
    
     public static ArrayList<String[]> execAdmTeReadTipos () {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AdmTeReadTipos ()}")) {
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAdmTeReadTipos(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static String[] execAgnTeCreateCompraEfectivo (ArrayList<Integer> Ids, String Nombre, String Telefono, String Correo, String User, String Password) {
        CachedRowSet crs = null;
        try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call AgnTeCreateCompraEfectivo (?,?,?,?,?,?)}")) {
            SQLServerDataTable ListaAsientos = new SQLServerDataTable();
            ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
            for (int Id: Ids) {
                ListaAsientos.addRow(Id);
            }
            cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
            cstmt.setString(2, Nombre);
            cstmt.setString(3, Telefono);
            if ("".equals(Correo))
                cstmt.setNull(4, Types.NVARCHAR);
            else
                cstmt.setString(4, Correo);
            cstmt.setString(5, User);
            cstmt.setString(6, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeCreateCompraEfectivo(ex);
        }
        return Utilities.convertToRow(crs);
    }
    
    public static String[] execAgnTeCreateCompraTarjeta (ArrayList<Integer> Ids, String Nombre, String Telefono, String Correo, String Tarjeta, String Expira, String CVV, String User, String Password) {
        CachedRowSet crs = null;
        try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call AgnTeCreateCompraTarjeta (?,?,?,?,?,?,?,?,?)}")) {
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
            cstmt.setString(7, CVV);
            cstmt.setString(8, User);
            cstmt.setString(9, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeCreateCompraTarjeta(ex);
        }
        return Utilities.convertToRow(crs);
    }
    
    public static String[] execAgnTeReadCompraResumen (ArrayList<Integer> Ids, String User, String Password) {
        CachedRowSet crs = null;
        try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call AgnTeReadCompraResumen (?,?,?)}")) {
            SQLServerDataTable ListaAsientos = new SQLServerDataTable();
            ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
            for (int Id: Ids) {
                ListaAsientos.addRow(Id);
            }    cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
            cstmt.setString(2, User);
            cstmt.setString(3, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeReadCompraResumen(ex);
        }
       return Utilities.convertToRow(crs);
    }
    
    public static ArrayList<String[]> execAgnTeReadAsientosPresentaciones (int IdPresentacion , int IdBloque, String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AgnTeReadAsientosPresentaciones(?,?,?,?)}")) {
            cstmt.setInt(1, IdPresentacion);
            cstmt.setInt(2, IdBloque);
            cstmt.setString(3, User);
            cstmt.setString(4, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeReadAsientosPresentaciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execAgnTeReadBloquesProducciones (int IdProduccion, String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AgnTeReadBloquesProducciones(?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            cstmt.setString(2, User);
            cstmt.setString(3, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeReadBloquesProducciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execAgnTeReadPresentaciones (int IdProduccion, String FechaHoraInicio, String FechaHoraFin, String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AgnTeReadPresentaciones(?,?,?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            if (FechaHoraInicio == null)
                cstmt.setNull(2, Types.DATE);
            else
                cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraInicio));
            if (FechaHoraFin == null)
                cstmt.setNull(3, Types.DATE);
            else
                cstmt.setTimestamp(3, Timestamp.valueOf(FechaHoraFin));
            cstmt.setString(4, User);
            cstmt.setString(5, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeReadPresentaciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execAgnTeReadProducciones (String FechaHoraInicio, String FechaHoraFin, String User, String Password) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call AgnTeReadProducciones(?,?,?,?)}")) {
            if (FechaHoraInicio == null)
                cstmt.setNull(1, Types.DATE);
            else
                cstmt.setTimestamp(1, Timestamp.valueOf(FechaHoraInicio));
            if (FechaHoraFin == null)
                cstmt.setNull(2, Types.DATE);
            else
                cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraFin));
            cstmt.setString(3, User);
            cstmt.setString(4, Password);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorAgnTeReadProducciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static String[] execPubCreateCompraTarjeta (ArrayList<Integer> Ids, String Nombre, String Telefono, String Correo, String Tarjeta, String Expira, String CVV) {
        CachedRowSet crs = null;
        try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call PubCreateCompraTarjeta(?,?,?,?,?,?,?)}")) {
            SQLServerDataTable ListaAsientos = new SQLServerDataTable();
            ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
            for (int Id: Ids) 
                ListaAsientos.addRow(Id);
            cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
            cstmt.setString(2, Nombre);
            cstmt.setString(3, Telefono);
            cstmt.setString(4, Correo);
            cstmt.setString(5, Tarjeta);
            cstmt.setDate(6, java.sql.Date.valueOf(Expira));
            cstmt.setString(7, CVV);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorPubCreateCompraTarjeta(ex);
        }
        return Utilities.convertToRow(crs);
    }
    
    public static String[] execPubReadCompraResumen (ArrayList<Integer> Ids) {
        CachedRowSet crs = null;
        try (SQLServerCallableStatement cstmt = (SQLServerCallableStatement) connection.prepareCall("{call PubReadCompraResumen(?)}")) {
            SQLServerDataTable ListaAsientos = new SQLServerDataTable();
            ListaAsientos.addColumnMetadata ("IdAsientoPresentacion", java.sql.Types.INTEGER);
            for (int Id: Ids)
                ListaAsientos.addRow(Id);    
            cstmt.setStructured (1,"dbo.ListaAsientos",ListaAsientos);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorPubReadCompraResumen(ex);
        }
       return Utilities.convertToRow(crs);
    }
    
    public static ArrayList<String[]> execPubReadAsientosPresentaciones (int IdPresentacion , int IdBloque) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call PubReadAsientosPresentaciones(?,?)}")) {
            cstmt.setInt(1, IdPresentacion);
            cstmt.setInt(2, IdBloque);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorPubReadAsientosPresentaciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execPubReadBloquesProducciones (int IdProduccion) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call PubReadBloquesProducciones(?)}")) {
            cstmt.setInt(1, IdProduccion);
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execPubReadPresentaciones (int IdProduccion, String FechaHoraInicio, String FechaHoraFin) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call PubReadPresentaciones(?,?,?)}")) {
            cstmt.setInt(1, IdProduccion);
            if (FechaHoraInicio == null)
                cstmt.setNull(2, Types.DATE);
            else
                cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraInicio));
            if (FechaHoraFin == null)
                cstmt.setNull(3, Types.DATE);
            else
                cstmt.setTimestamp(3, Timestamp.valueOf(FechaHoraFin));
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorPubReadPresentaciones(ex);
        }
       return Utilities.convertToTable(crs);
    }
    
    public static ArrayList<String[]> execPubReadProducciones (String FechaHoraInicio, String FechaHoraFin) {
        CachedRowSet crs = null;
        try (CallableStatement cstmt = connection.prepareCall("{call PubReadProducciones(?,?)}")) {
            if (FechaHoraInicio == null)
                cstmt.setNull(1, Types.DATE);
            else
                cstmt.setTimestamp(1, Timestamp.valueOf(FechaHoraInicio));
            if (FechaHoraFin == null)
                cstmt.setNull(2, Types.DATE);
            else
                cstmt.setTimestamp(2, Timestamp.valueOf(FechaHoraFin));
            ResultSet rs = cstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            cstmt.close();
        } catch (SQLException ex) {
            ErrorManager.errorPubReadProducciones(ex);
        }
        return Utilities.convertToTable(crs);
    }
}
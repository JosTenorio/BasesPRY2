
package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class PartQuery {
    
    public static void insertPart(String name, String brand, String fabricator){
        try {
            ArrayList<String> columns = new ArrayList<String>() {
                {
                    add("NOMBRE");
                    add("ID_MARCA");
                    add("ID_FABRICANTE");
                }
            };
            ArrayList<String> values = new ArrayList<String>() {
                {
                    add("'" + name + "'");
                    add("(SELECT ID FROM MARCA WHERE NOMBRE = '" + brand + "')");
                    add("(SELECT ID FROM FAB_PARTES WHERE NOMBRE = '" + fabricator + "')");
                }
            };
            if (name.equals(""))
                throw new SQLException ("Empty part name");
            ConnectionManager.insert("PARTE", columns, values);
        } catch (SQLException ex) {
            ErrorManager.partInsertError(ex);
        }
    }                                                                            
    
    public static void deletePart(String partId){
        try {
            int rowsAffected = ConnectionManager.delete("PARTE", "ID = " + partId);
            if (rowsAffected == 0)
                throw new SQLException("No rows affected");
        } catch (SQLException ex) {
            ErrorManager.deletePartError(ex);
        }
    }
    
    public static void asociatePartProvider(String partId, String providerId, String providerPrice, String gainPercent){
        try {
            ArrayList<String> columns = new ArrayList<String>() {
                {
                    add("ID_PARTE");
                    add("ID_PROVEEDOR");
                    add("PRECIO_PROVEEDOR");
                    add("POR_GANANCIA");
                    add("PRECIO_PUBLICO");
                }
            };
            ArrayList<String> values = new ArrayList<String>() {
                {
                    add(partId);
                    add(providerId);
                    add(providerPrice);
                    add(gainPercent);
                    add(gainPercent  + "/100.0 * " + providerPrice + " + " + providerPrice);
                }
            };
            ConnectionManager.insert("PROVISION", columns, values);
        } catch (SQLException ex) {
            ErrorManager.provitionInsertError(ex);
        }
    }
    
    public static void asociatePartCar(String partId, String autoId){
        try {
            ArrayList<String> columns = new ArrayList<String>() {
                {
                    add("ID_PARTE");
                    add("ID_AUTOMOVIL");
                }
            };
            ArrayList<String> values = new ArrayList<String>() {
                {
                    add(partId);
                    add(autoId);
                }
            };
            ConnectionManager.insert("CORRESPONDENCIA", columns, values);
        } catch (SQLException ex) {
            ErrorManager.insertCorrespondenceError(ex);
        }
    }
    
    public static void modifyPartProvider(String partId, String providerId, String providerPrice, String gainPercent){
        try {
            ArrayList<String> columns = new ArrayList<String>() {
                {
                    add("PRECIO_PROVEEDOR");
                    add("POR_GANANCIA");
                    add("PRECIO_PUBLICO");
                }
            };
            ArrayList<String> values = new ArrayList<String>() {
                {
                    add(providerPrice);
                    add(gainPercent);
                    add(gainPercent  + "/100.0 * " + providerPrice + " + " + providerPrice);
                }
            };
            int rowsAffected = ConnectionManager.update("PROVISION", columns, values, "ID_PARTE = " + partId + " AND ID_PROVEEDOR = " + providerId);
            if (rowsAffected == 0)
                throw new SQLException("No rows affected");
        } catch (SQLException ex) {
            ErrorManager.provitionUpdateError(ex);
        }
    }
}

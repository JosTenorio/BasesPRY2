
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class ClientQuery {
    
    public static void insertClient(String status, ArrayList<String> information, ArrayList<String> telephones, boolean organization){
        try {
            ConnectionManager.insert("CLIENTE", "ID_ESTADO", "(SELECT ID FROM ESTADO WHERE TIPO = '" + status + "')");
            insertClientAux(information, organization);
            insertPhone(information.get(0), telephones, organization);
        } catch (SQLException ex) {
            ErrorManager.clientInsertError(ex);
        } catch (PreviousSQLException ex) {
            try {
                ConnectionManager.delete("CLIENTE"," ID = (SELECT MAX(ID) FROM CLIENTE)");
            } catch (SQLException ex1) {
                Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }     
    }
    
    private static void insertClientAux(ArrayList<String> information, boolean organization) throws PreviousSQLException {           
            ArrayList<String> columns = new ArrayList<>(){
                {
                    if (organization){
                        add("CEDULA_JUR");
                        add("NOMBRE");
                        add("DIRECCION");
                        add("CIUDAD");
                        add("NOMBRE_CONTACTO");
                        add("CARGO_CONTACTO");
                    }
                    else{
                        add("CEDULA");
                        add("NOMBRE");
                        add("DIRECCION");
                        add("CIUDAD");
                    }
                    add("ID_CLIENTE");
                }
            };
            for (int i = 0; i < information.size(); i++)
                information.set(i, "'" + information.get(i) + "'");
            information.add("(SELECT MAX(ID) FROM CLIENTE)");
            if (organization) {
                try {
                    if (information.get(0).equals("")){
                       throw new SQLException ("Empty client name");
                    }
                    ConnectionManager.insert("ORGANIZACION", columns, information);
                }catch (SQLException ex) {
                    ErrorManager.organizationInsertError(ex);
                    throw new PreviousSQLException ("Error de insercion de datos de organizacion");
                }
            }
            else {
                try {
                    if (information.get(0).equals("")){
                       throw new SQLException ("Empty client name");
                    }
                    ConnectionManager.insert("PERSONA", columns, information);
                } catch (SQLException ex) {
                    ErrorManager.personInsertError(ex);
                    throw new PreviousSQLException ("Error de insercion de datos de perosona");
                }
            }
    }
    
    private static void insertPhone(String clientCed, ArrayList<String> telephones, boolean organization) throws PreviousSQLException{
            ArrayList<String> columns = new ArrayList<>(){
                {
                    if (organization)
                        add("CEDULA_ORGANIZACION");
                    else
                        add("CEDULA_PERSONA");
                    add("TELEFONO");  
                }
            };
            ArrayList<String> values = new ArrayList<>() { 
                { 
                    add(clientCed); 
                    add("");
                } 
            };
            if (organization)
                for (String telephone : telephones){ 
                    values.set(1,"'" + telephone + "'");
                    try {
                        if (!telephone.matches("[0-9]+")) {
                            throw new SQLException ("Non-numerical telephone");
                        }
                        ConnectionManager.insert("TELEFONOS_ORGANIZACION", columns, values);
                    } catch (SQLException ex) {
                        ErrorManager.telephoneInsertError(ex, telephone);
                    }
                }
            else
                for (String telephone : telephones){
                    values.set(1, "'" + telephone + "'" );
                    try {
                        if (!telephone.matches("[0-9]+")) {
                            throw new SQLException ("Non-numerical telephone");
                        }
                        ConnectionManager.insert("TELEFONOS_PERSONA", columns, values);
                    } catch (SQLException ex) {
                        ErrorManager.telephoneInsertError(ex, telephone);
                    }
                }
    }
    
    public static void modifyClient(String clientCed, String status, ArrayList<String> information, ArrayList<String> telephones, boolean organization){
        try {
            ResultSet rs;
            if (organization)
                rs = ConnectionManager.select("ID_CLIENTE", "ORGANIZACION", "CEDULA_JUR = '" + clientCed + "'");
            else
                rs = ConnectionManager.select("ID_CLIENTE", "PERSONA", "CEDULA = '" + clientCed + "'");
            rs.next();
            String clientId = String.valueOf(rs.getInt("ID_CLIENTE"));
            ConnectionManager.update("CLIENTE", "ID_ESTADO", "(SELECT ID FROM ESTADO WHERE TIPO = '" + status + "')", "ID = " + clientId);
            modifyClientAux(clientId, information, organization);
            if (organization)
                ConnectionManager.delete("TELEFONOS_ORGANIZACION", "CEDULA_ORGANIZACION = " + clientCed);
            else
                ConnectionManager.delete("TELEFONOS_PERSONA", "CEDULA_PERSONA = " + clientCed);
            insertPhone(clientCed, telephones, organization);
        } catch (SQLException ex) {
            ErrorManager.clientUpdateError(ex,organization);
        } catch (PreviousSQLException ex) {
            
        }
    }
    
    public static void modifyClientAux(String clientId, ArrayList<String> information, boolean organization) throws SQLException{
            if (information.get(0).equals("")){
                throw new SQLException ("Empty client name");
            }    
            ArrayList<String> columns = new ArrayList<>(){
                {
                    if (organization){
                        add("NOMBRE");
                        add("DIRECCION");
                        add("CIUDAD");
                        add("NOMBRE_CONTACTO");
                        add("CARGO_CONTACTO");
                    }
                    else{
                        add("NOMBRE");
                        add("DIRECCION");
                        add("CIUDAD");
                    }
                }
            };
            for (int i = 0; i < information.size(); i++)
                information.set(i, "'" + information.get(i) + "'");
            if (organization)
                ConnectionManager.update("ORGANIZACION", columns, information, "ID_CLIENTE = " + clientId);
            else
                ConnectionManager.update("PERSONA", columns, information, "ID_CLIENTE = " + clientId);
    }
}

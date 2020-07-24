
package Model;

import static Controller.OrderMenuController.IVA;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultQuery {  
    
   public static ArrayList<String[]> listClientsTable(){
       ArrayList<String[]> clientList = new ArrayList<>();
       try {
           ArrayList<String> columnsPerson = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA");
               }
           };
           ResultSet rsPerson = ConnectionManager.select(columnsPerson, "PERSONA");
           while(rsPerson.next()){
               String[] person = new String[5];
               for (int i = 1; i <= columnsPerson.size(); i++)
                   person[i-1] = String.valueOf(rsPerson.getObject(i));
               person[4] = "FALSE";
               clientList.add(person);
           }
           ArrayList<String> columnsOrg = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA_JUR");
               }
           };
           ResultSet rsOrg = ConnectionManager.select(columnsOrg, "ORGANIZACION");
           while(rsOrg.next()){
               String[] org = new String[5];
               for (int i = 1; i <= columnsOrg.size(); i++)
                   org[i-1] = String.valueOf(rsOrg.getObject(i));
               org[4] = "TRUE";
               clientList.add(org);
           }
           for (String[] people : clientList){
               ResultSet rsClient = ConnectionManager.select("TIPO", "ESTADO", "ID = (SELECT ID_ESTADO FROM CLIENTE WHERE ID = " + people[0] + ")");
               rsClient.next();
               people[3] = rsClient.getString("TIPO");
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return clientList;
   }
   
   public static ArrayList<String> listStatusDropdown(){
       ArrayList<String> statusList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("TIPO", "ESTADO");
           while(rs.next()){
               statusList.add(rs.getString("TIPO"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return statusList;
   }
   
   public static String[] listClientForm(String[] clientInfo){
       String[] client = new String[10];
       client[0] = clientInfo[1];
       client[1] = clientInfo[2];
       client[2] = clientInfo[3];
       client[9] = clientInfo[4];
       for (int i = 3; i < 9; i++)
           client[i] = "";
       String clientCed = clientInfo[2];
       boolean organization = ("TRUE".equals(clientInfo[4]));
       try{
           ArrayList<String> columns = new ArrayList<>(){
               {
                   add("DIRECCION");
                   add("CIUDAD");
                   if (organization){
                       add("NOMBRE_CONTACTO");
                       add("CARGO_CONTACTO");
                   }
               }
           };
           if (organization){
               ResultSet rs = ConnectionManager.select(columns, "ORGANIZACION", "CEDULA_JUR = " + clientCed);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               client[5] = rs.getString("NOMBRE_CONTACTO");
               client[6] = rs.getString("CARGO_CONTACTO");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_ORGANIZACION", "CEDULA_ORGANIZACION = " + clientCed);
               int i = 7;
               while(rs.next() && i < 9){
                   client[i] = rs.getString("TELEFONO");
                   i++;
               }
           }
           else{
               ResultSet rs = ConnectionManager.select(columns, "PERSONA", "CEDULA = " + clientCed);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_PERSONA", "CEDULA_PERSONA = " + clientCed);
               int i = 7;
               while(rs.next() && i < 9){
                   client[i] = rs.getString("TELEFONO");
                   i++;
               }
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return client;
   }
   
   public static ArrayList<String[]> listPartsTable(){
       ArrayList<String[]> partList = new ArrayList<>();
       try {
           ArrayList<String> columnsPart = new ArrayList<>(){
               {
                   add("ID");
                   add("NOMBRE");
                   add("ID_FABRICANTE");
                   add("ID_MARCA");
               }
           };
           ResultSet rsPart = ConnectionManager.select(columnsPart, "PARTE");
           while(rsPart.next()){
               String[] part = new String[4];
               for (int i = 1; i <= columnsPart.size(); i++)
                   part[i-1] = String.valueOf(rsPart.getObject(i));
               partList.add(part);
           }
           for (String[] part : partList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "FAB_PARTES", "ID = " + part[2]);
               rs.next();
               part[2] = rs.getString("NOMBRE");
               rs.close();
               rs = ConnectionManager.select("NOMBRE", "MARCA", "ID = " + part[3]);
               rs.next();
               part[3] = rs.getString("NOMBRE");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partList;
   }
   
   public static ArrayList<String> listFabPartsDropdown(){
       ArrayList<String> fabPartsList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("NOMBRE", "FAB_PARTES");
           while(rs.next()){
               fabPartsList.add(rs.getString("NOMBRE"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return fabPartsList;
   }
   
   public static ArrayList<String> listBrandsDropdown(){
       ArrayList<String> brandsList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("NOMBRE", "MARCA");
           while(rs.next()){
               brandsList.add(rs.getString("NOMBRE"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return brandsList;
   }
   
   public static ArrayList<String[]> listPartProvidersTable(){
       ArrayList<String[]> partProvList = new ArrayList<>();
       try {
           ArrayList<String> columnsPartProv = new ArrayList<>(){
               {
                   add("ID_PARTE");
                   add("ID_PROVEEDOR");
                   add("PRECIO_PROVEEDOR");
                   add("POR_GANANCIA");
                   add("PRECIO_PUBLICO");
               }
           };
           ResultSet rsPart = ConnectionManager.select(columnsPartProv, "PROVISION");
           while(rsPart.next()){
               String[] partProv = new String[7];
               for (int i = 1; i <= columnsPartProv.size(); i++)
                   partProv[i-1] = String.valueOf(rsPart.getObject(i));
               partProvList.add(partProv);
           }
           for (String[] partProv : partProvList){
               partProv[6] = partProv[4];
               partProv[5] = partProv[3];
               partProv[4] = partProv[2];
               ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + partProv[0]);
               rs.next();
               partProv[2] = rs.getString("NOMBRE");
               rs.close();
               rs = ConnectionManager.select("NOMBRE", "PROVEEDOR", "ID = " + partProv[1]);
               rs.next();
               partProv[3] = rs.getString("NOMBRE");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partProvList;
   }
   
   public static ArrayList<String[]> listPartsDropdown(){
       ArrayList<String[]> partsList = new ArrayList<>();
       try {
           ArrayList<String> columnsPart = new ArrayList<>(){
               {
                   add("ID");
                   add("NOMBRE");
               }
           };
           ResultSet rs = ConnectionManager.select(columnsPart, "PARTE");
           while(rs.next()){
               String[] part = new String[2];
               for (int i = 1; i <= columnsPart.size(); i++)
                   part[i-1] = String.valueOf(rs.getObject(i));
               partsList.add(part);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partsList;
   }
   
   public static ArrayList<String[]> listProvidersDropdown(){
       ArrayList<String[]> provsList = new ArrayList<>();
       try {
           ArrayList<String> columnsProv = new ArrayList<>(){
               {
                   add("ID");
                   add("NOMBRE");
               }
           };
           ResultSet rs = ConnectionManager.select(columnsProv, "PROVEEDOR");
           while(rs.next()){
               String[] prov = new String[2];
               for (int i = 1; i <= columnsProv.size(); i++)
                   prov[i-1] = String.valueOf(rs.getObject(i));
               provsList.add(prov);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return provsList;
   }
   
   public static ArrayList<String[]> listPartAutosTable(){
       ArrayList<String[]> partAutoList = new ArrayList<>();
       try {
           ArrayList<String> columnsPartAuto = new ArrayList<>(){
               {
                   add("ID_PARTE");
                   add("ID_AUTOMOVIL");
               }
           };
           ResultSet rsPart = ConnectionManager.select(columnsPartAuto, "CORRESPONDENCIA");
           while(rsPart.next()){
               String[] partProv = new String[5];
               for (int i = 1; i <= columnsPartAuto.size(); i++)
                   partProv[i-1] = String.valueOf(rsPart.getObject(i));
               partAutoList.add(partProv);
           }
           for (String[] partAuto : partAutoList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + partAuto[0]);
               rs.next();
               partAuto[2] = rs.getString("NOMBRE");
               rs.close();
               ArrayList<String> columnsAuto = new ArrayList<>(){{add("MODELO");add("ANO");}};
               rs = ConnectionManager.select(columnsAuto, "AUTOMOVIL", "ID = " + partAuto[1]);
               rs.next();
               partAuto[3] = rs.getString("MODELO");
               partAuto[4] = rs.getString("ANO");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partAutoList;
   }
   
   public static ArrayList<String[]> listAutosDropdown(){
       ArrayList<String[]> autosList = new ArrayList<>();
       try {
           ArrayList<String> columnsAutos = new ArrayList<>(){
               {
                   add("ID");
                   add("MODELO");
                   add("ANO");
               }
           };
           ResultSet rs = ConnectionManager.select(columnsAutos, "AUTOMOVIL");
           while(rs.next()){
               String[] auto = new String[3];
               for (int i = 1; i <= columnsAutos.size(); i++)
                   auto[i-1] = String.valueOf(rs.getObject(i));
               autosList.add(auto);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return autosList;
   }
   
   public static ArrayList<String[]> listClientsDropdown(){
       ArrayList<String[]> clientsList = new ArrayList<>();
       try {
           ArrayList<String> columnsClients = new ArrayList<>(){
               {
                   add("NOMBRE");
                   add("CEDULA");
               }
           };
           ResultSet rs = ConnectionManager.select(columnsClients, "PERSONA");
           while(rs.next()){
               String[] client = new String[3];
               for (int i = 1; i <= columnsClients.size(); i++)
                   client[i-1] = String.valueOf(rs.getObject(i));
               client[2] = "FALSE";
               clientsList.add(client);
           }
           rs.close();
           columnsClients.set(1, "CEDULA_JUR");
           rs = ConnectionManager.select(columnsClients, "ORGANIZACION");
           while(rs.next()){
               String[] client = new String[3];
               for (int i = 1; i <= columnsClients.size(); i++)
                   client[i-1] = String.valueOf(rs.getObject(i));
               client[2] = "TRUE";
               clientsList.add(client);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return clientsList;
   }
   
   public static ArrayList<String[]> listOrdersTable(){
       ArrayList<String[]> orderList = new ArrayList<>();
       try {
           ArrayList<String> columnsOrder = new ArrayList<>(){
               {
                   add("ID");
                   add("ID_CLIENTE");
                   add("FECHA");
                   add("MONTO_BASE");
               }
           };
           ResultSet rsOrder = ConnectionManager.select(columnsOrder, "ORDEN");
           while(rsOrder.next()){
               String[] order = new String[6];
               for (int i = 1; i <= columnsOrder.size(); i++)
                   order[i-1] = String.valueOf(rsOrder.getObject(i));
               int basePrice = rsOrder.getInt("MONTO_BASE");
               order[4] = String.valueOf(basePrice * IVA);
               order[5] = String.valueOf((basePrice * IVA) + basePrice);
               orderList.add(order);
           }
           for (String[] order : orderList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "PERSONA", "ID_CLIENTE = " + order[1]);
               if (rs.next() == false){
                   rs = ConnectionManager.select("NOMBRE", "ORGANIZACION", "ID_CLIENTE = " + order[1]);
                   rs.next();
               }
               order[1] = rs.getString("NOMBRE");
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return orderList;
   }
   
   public static ArrayList<String[]> listDetailsForm(String orderId){
       ArrayList<String[]> detailsList = new ArrayList<>();
       try {
           ArrayList<String> columnsDetails = new ArrayList<>(){
               {
                   add("ID_PARTE");
                   add("ID_PROVEEDOR");
                   add("CANTIDAD");
                   add("PRECIO");
               }
           };
           ResultSet rsDetail = ConnectionManager.select(columnsDetails, "DETALLE", "ID_ORDEN = " + orderId);
           while(rsDetail.next()){
               String[] detail = new String[4];
               for (int i = 1; i <= columnsDetails.size(); i++)
                   detail[i-1] = String.valueOf(rsDetail.getObject(i));
               detailsList.add(detail);
           }
           for (String[] detail : detailsList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + detail[0]);
               rs.next();
               detail[0] = rs.getString("NOMBRE");
               rs.close();
               rs = ConnectionManager.select("NOMBRE", "PROVEEDOR", "ID = " + detail[1]);
               rs.next();
               detail[1] = rs.getString("NOMBRE");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return detailsList;
   }
   
   public static ArrayList<String[]> listPartProvidersDropdown(){
       ArrayList<String[]> partProvList = new ArrayList<>();
       try {
           ArrayList<String> columnsPartProv = new ArrayList<>(){
               {
                   add("ID_PARTE");
                   add("ID_PROVEEDOR");
               }
           };
           ResultSet rsPart = ConnectionManager.select(columnsPartProv, "PROVISION");
           while(rsPart.next()){
               String[] partProv = new String[4];
               for (int i = 1; i <= columnsPartProv.size(); i++)
                   partProv[i-1] = String.valueOf(rsPart.getObject(i));
               partProvList.add(partProv);
           }
           for (String[] partProv : partProvList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + partProv[0]);
               rs.next();
               partProv[2] = rs.getString("NOMBRE");
               rs.close();
               rs = ConnectionManager.select("NOMBRE", "PROVEEDOR", "ID = " + partProv[1]);
               rs.next();
               partProv[3] = rs.getString("NOMBRE");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partProvList;
   }

   public static ArrayList<String[]> listPartProvidersTable(String partName){
       ArrayList<String[]> partProvList = new ArrayList<>();
       try{
            String partId = null;
            try {
                if (partName.equals("")){
                    throw new SQLException ("Empty part name"); 
                }
                ResultSet rs = ConnectionManager.select("ID", "PARTE", "NOMBRE = '" + partName + "'");
                if (rs.next()!=false){
                    partId = String.valueOf(rs.getInt("ID"));
                }else {
                    throw new SQLException ("No rows affected");
                } 
            } catch (SQLException ex) {
                ErrorManager.listProvidersTableError(ex);
                throw new PreviousSQLException(ex.getMessage());
            }
            try {
                ArrayList<String> columnsPartProv = new ArrayList<>(){
                    {
                        add("ID_PARTE");
                        add("ID_PROVEEDOR");
                        add("PRECIO_PROVEEDOR");
                        add("POR_GANANCIA");
                        add("PRECIO_PUBLICO");
                    }
                };
                ResultSet rsPart = ConnectionManager.select(columnsPartProv, "PROVISION", "ID_PARTE = " + partId);
                while(rsPart.next()){
                    String[] partProv = new String[7];
                    for (int i = 1; i <= columnsPartProv.size(); i++)
                        partProv[i-1] = String.valueOf(rsPart.getObject(i));
                    partProvList.add(partProv);
                }
                for (String[] partProv : partProvList){
                    partProv[6] = partProv[4];
                    partProv[5] = partProv[3];
                    partProv[4] = partProv[2];
                    ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + partProv[0]);
                    rs.next();
                    partProv[2] = rs.getString("NOMBRE");
                    rs.close();
                    rs = ConnectionManager.select("NOMBRE", "PROVEEDOR", "ID = " + partProv[1]);
                    rs.next();
                    partProv[3] = rs.getString("NOMBRE");
                    rs.close();
                }
            } catch (SQLException ex) {
                ErrorManager.listProvidersTableError(ex);
                throw new PreviousSQLException(ex.getMessage());
            }
        }catch (PreviousSQLException ex){}
        finally{
           return partProvList;
        }
   }
   
   public static ArrayList<String[]> listPartAutosTable(String model, String year){
        ArrayList<String[]> partAutoList = new ArrayList<>();
        try{
            String autoId = null;
            try {
                if (model.equals("")){
                    throw new SQLException ("Empty model");
                }
                if (year.equals("")){
                    throw new SQLException ("Empty year");
                }
                ResultSet rs = ConnectionManager.select("ID", "AUTOMOVIL", "MODELO = '" + model + "' AND ANO = '" + year + "'");
                if (rs.next()!=false){
                    autoId = String.valueOf(rs.getInt("ID"));
                }else{
                    throw new SQLException ("No rows affected");
                }
            } catch (SQLException ex) {
                ErrorManager.listPartAutosTableError(ex);
                throw new PreviousSQLException (ex.getMessage());
            }
            try {
                ArrayList<String> columnsPartAuto = new ArrayList<>(){
                    {
                        add("ID_PARTE");
                        add("ID_AUTOMOVIL");
                    }
                };
                ResultSet rsPart = ConnectionManager.select(columnsPartAuto, "CORRESPONDENCIA", "ID_AUTOMOVIL = " + autoId);
                while(rsPart.next()){
                    String[] partProv = new String[5];
                    for (int i = 1; i <= columnsPartAuto.size(); i++)
                        partProv[i-1] = String.valueOf(rsPart.getObject(i));
                    partAutoList.add(partProv);
                }
                for (String[] partAuto : partAutoList){
                    ResultSet rs = ConnectionManager.select("NOMBRE", "PARTE", "ID = " + partAuto[0]);
                    rs.next();
                    partAuto[2] = rs.getString("NOMBRE");
                    rs.close();
                    ArrayList<String> columnsAuto = new ArrayList<>(){{add("MODELO");add("ANO");}};
                    rs = ConnectionManager.select(columnsAuto, "AUTOMOVIL", "ID = " + partAuto[1]);
                    rs.next();
                    partAuto[3] = rs.getString("MODELO");
                    partAuto[4] = rs.getString("ANO");
                    rs.close();
                }
            } catch (SQLException ex) {
                ErrorManager.listPartAutosTableError(ex);
                throw new PreviousSQLException (ex.getMessage());
            }
        }catch(PreviousSQLException ex){}
        finally{
            return partAutoList;
        }
   }   
}





package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ConnectionManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static String IP;
    private static String USERNAME;
    private static String PASSWORD;
    
    public static void logIn(String ip, String username, String password){
        IP = ip;
        USERNAME = username;
        PASSWORD = password;
    }
    
    public static void connect() throws SQLException {
        String url = "jdbc:sqlserver://" + IP  + ":1433;databaseName=BasesPRY1;user=" + USERNAME + ";password=" + PASSWORD;
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement ();
    }
    
    private static void executeActionQuery(String query) throws SQLException {
        if (connection == null)
            connect();
        System.out.println(query);
        statement.execute(query);
    }
    
    private static ResultSet executeConsultQuery(String query) throws SQLException {
        if (connection == null)
            connect();
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
    
    private static int executeUpdateQuery(String query) throws SQLException {
        if (connection == null)
            connect();
        System.out.println(query);
        return statement.executeUpdate(query);
    }
    
    public static void insert(String table, String column, String value) throws SQLException{
        insert(table, new ArrayList<>(Arrays.asList(column)), new ArrayList<>(Arrays.asList(value)));
    }
    
    public static void insert(String table, ArrayList<String> columns, ArrayList<String> values) throws SQLException{
        String query = "INSERT INTO " + table + "(";
        Iterator i = columns.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + ") VALUES(";
        i = values.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + ");";
        executeActionQuery(query);
    }
    
    public static void update(String table, String column, String value, String conditions) throws SQLException{
        update(table, new ArrayList<>(Arrays.asList(column)), new ArrayList<>(Arrays.asList(value)), conditions);
    }
    
    public static int update(String table, ArrayList<String> columns, ArrayList<String> values, String conditions) throws SQLException{
        String query = "UPDATE " + table + " SET ";
        Iterator i1 = columns.iterator();
        Iterator i2 = values.iterator();
        while (i1.hasNext()) 
        {
            query += i1.next() + " = " + i2.next() + ",";
        }
        query = query.substring(0,query.length()-1);
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        return executeUpdateQuery(query);
    }
    
    public static ResultSet select(ArrayList<String> columns, String table) throws SQLException{
        return select(columns, new ArrayList<>(Arrays.asList(table)), "");
    }
    
    public static ResultSet select(String column, String table) throws SQLException{
        return select(new ArrayList<>(Arrays.asList(column)), new ArrayList<>(Arrays.asList(table)), "");
    }
    
    public static ResultSet select(String column, String table, String conditions) throws SQLException{
        return select(new ArrayList<>(Arrays.asList(column)), new ArrayList<>(Arrays.asList(table)), conditions);
    }
    
    public static ResultSet select(ArrayList<String> columns, String table, String conditions) throws SQLException{
        return select(columns, new ArrayList<>(Arrays.asList(table)), conditions);
    }
    
    public static ResultSet select(ArrayList<String> columns, ArrayList <String> tables, String conditions) throws SQLException{
        String query = "SELECT ";
        Iterator i = columns.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + " FROM ";
        i = tables.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1);
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        ResultSet rs = executeConsultQuery(query);
        return (rs);
        
    }
    
    public static int delete(String table, String conditions) throws SQLException{
        String query = "DELETE FROM " + table;
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        return executeUpdateQuery(query);
    }
    
}

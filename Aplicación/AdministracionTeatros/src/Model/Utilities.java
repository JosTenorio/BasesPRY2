
package Model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class Utilities {
    
    public static String USERNAME;
    public static String PASSWORD;
    public static int LOGINTYPE = 0;
    
    public static void setCredentials(String user, String password, int loginType){
        USERNAME = user;
        PASSWORD = password;
        LOGINTYPE = loginType;
    }
    
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static ArrayList<String[]> convertToTable(ResultSet rs) {
        ArrayList<String[]> table = new ArrayList<>();
        try {
            while (rs != null && rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                String[] row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++)
                    row[i-1] = String.valueOf(rs.getObject(i));
                table.add(row);
            }
        } catch (SQLException ex) {
            System.out.println("Error in conversion. Returning empty");
        }
        return table;
    }
    
    public static String[] convertToRow(ResultSet rs) {
        String[] row = new String[0];
        try {
            if (rs != null && rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++)
                    row[i-1] = String.valueOf(rs.getObject(i));
            }
        } catch (SQLException ex) {
            System.out.println("Error in conversion. Returning empty");
        }
        return row;
    }
}

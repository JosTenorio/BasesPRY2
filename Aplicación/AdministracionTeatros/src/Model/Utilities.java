
package Model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilities {
    
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static ArrayList<String[]> convertToTable(ResultSet rs) {
        ArrayList<String[]> table = new ArrayList<>();
        try {
            while (rs.next()){
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                String[] row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++)
                    row[i-1] = String.valueOf(rs.getObject(i));
                table.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }
    
    public static String[] convertToRow(ResultSet rs) {
        String[] row = null;
        try {
            rs.next();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            row = new String[columnCount];
            for (int i = 1; i <= columnCount; i++)
                row[i-1] = String.valueOf(rs.getObject(i));
        } catch (SQLException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}

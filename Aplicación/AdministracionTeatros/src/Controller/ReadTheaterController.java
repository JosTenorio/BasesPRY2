
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.TheaterMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadTheaterController implements ActionListener{
    
    private static final TheaterMenuDisplay display = new TheaterMenuDisplay();
    private static ReadTheaterController firstInstance = null;
    private ArrayList<String[]> theaterList;
    
    private ReadTheaterController(){
        init();
    }
    
    public static ReadTheaterController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadTheaterController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        theaterList = ConnectionManager.execAdmSisReadTeatros();
        for (String[] row : theaterList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Theaters.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmSisMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            try{
                int selectedIndex = display.jTable_Theaters.getSelectedRow();
                int theaterId = Integer.valueOf(theaterList.get(selectedIndex)[0]);
                CreateAdmTeController.getInstance().makeVisible(true, theaterId);
                display.setVisible(false);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

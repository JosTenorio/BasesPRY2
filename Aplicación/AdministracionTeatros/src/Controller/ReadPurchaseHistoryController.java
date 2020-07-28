
package Controller;

import Model.ConnectionManager;
import View.PurchaseHistoryDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadPurchaseHistoryController implements ActionListener{
    
    private static final PurchaseHistoryDisplay display = new PurchaseHistoryDisplay();
    private static ReadPurchaseHistoryController firstInstance = null;
    
    private ReadPurchaseHistoryController(){
        init();
    }
    
    public static ReadPurchaseHistoryController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadPurchaseHistoryController();
        return firstInstance;
    }
    
    private void init(){
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
        ArrayList<String[]> purchList = ConnectionManager.execAdmSisReadRegistroPagos();
        for (String[] row : purchList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 0, row.length));
        display.jTable_Payments.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmSisMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
    }
}

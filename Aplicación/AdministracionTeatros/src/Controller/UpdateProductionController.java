
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.ProductionUpdateDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdateProductionController implements ActionListener{
    
    private static final ProductionUpdateDisplay display = new ProductionUpdateDisplay();
    private static UpdateProductionController firstInstance = null;
    private ArrayList<String[]> prodList;
    private ArrayList<String[]> stateList;
    
    private UpdateProductionController(){
        init();
    }
    
    public static UpdateProductionController getInstance(){
        if (firstInstance == null)
            firstInstance = new UpdateProductionController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_Update.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true){
            updateTableData1();
            updateTableData2();
        }
    }
    
    public void updateTableData1(){
        display.tableModel1.setRowCount(0);
        prodList = ConnectionManager.execAdmTeReadProducciones(Utilities.USERNAME, Utilities.PASSWORD);
        for (String[] row : prodList)
            display.tableModel1.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Productions.setModel(display.tableModel1);
    }    
    
    public void updateTableData2(){
        display.tableModel2.setRowCount(0);
        stateList = ConnectionManager.execAdmTeReadEstados();
        for (String[] row : stateList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_States.setModel(display.tableModel2);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmTeMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Update)){
            try{
                int selectedIndex = display.jTable_Productions.getSelectedRow();
                int prodId = Integer.valueOf(prodList.get(selectedIndex)[0]);
                selectedIndex = display.jTable_States.getSelectedRow();
                int stateId = Integer.valueOf(stateList.get(selectedIndex)[0]);
                ConnectionManager.execAdmTeUpdateProduccionEstado(prodId, stateId, Utilities.USERNAME, Utilities.PASSWORD);
                updateTableData1();
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

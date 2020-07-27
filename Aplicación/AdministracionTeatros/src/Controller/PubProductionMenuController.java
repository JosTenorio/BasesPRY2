
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.ProductionMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PubProductionMenuController implements ActionListener{
    
    private static final ProductionMenuDisplay display = new ProductionMenuDisplay();
    private static PubProductionMenuController firstInstance = null;
    private ArrayList<String[]> productionList;
    private String startTime = null;
    private String endTime = null;
    
    private PubProductionMenuController(){
        init();
    }
    
    public static PubProductionMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubProductionMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.jButton_SearchDate.addActionListener(this);
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
        if (Utilities.LOGINTYPE == 0)
            productionList = ConnectionManager.execPubReadProducciones(startTime, endTime);
        if (Utilities.LOGINTYPE == 1)
            productionList = ConnectionManager.execAgnTeReadProducciones(startTime, endTime, Utilities.USERNAME, Utilities.PASSWORD);
        for (String[] row : productionList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Productions.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            try{
                int selectedIndex = display.jTable_Productions.getSelectedRow();
                int productionId = Integer.valueOf(productionList.get(selectedIndex)[0]);
                PubPresentationMenuController.getInstance().makeVisible(true, productionId);
                display.setVisible(false);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_SearchDate)){
            if (!"".equals(display.jTextField_DateStart.getText()))
                startTime = display.jTextField_DateStart.getText() + ":00";
            else
                startTime = null;
            if (!"".equals(display.jTextField_DateEnd.getText()))
                endTime = display.jTextField_DateEnd.getText() + ":00";
            else
                endTime = null;
            updateTableData();
        }
    }
}

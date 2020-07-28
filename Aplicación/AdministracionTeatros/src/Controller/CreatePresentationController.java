
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.PresentationCreationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CreatePresentationController implements ActionListener{
    
    private static final PresentationCreationDisplay display = new PresentationCreationDisplay();
    private static CreatePresentationController firstInstance = null;
    private ArrayList<String[]> prodList;
    
    private CreatePresentationController(){
        init();
    }
    
    public static CreatePresentationController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreatePresentationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_AddPres.addActionListener(this);
        display.jButton_ViewProd.addActionListener(this);
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
    
    public void updateTableData2(int prodId){
        display.tableModel2.setRowCount(0);
        ArrayList<String[]> presList = ConnectionManager.execAdmTeReadPresentaciones(prodId, Utilities.USERNAME, Utilities.PASSWORD);
        for (String[] row : presList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Presentations.setModel(display.tableModel2);
    } 
    
    private void updateTableData2(){
        display.tableModel2.setRowCount(0);
        ArrayList<String[]> presList = new ArrayList<>();
        for (String[] row : presList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Presentations.setModel(display.tableModel2);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmTeMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_ViewProd)){
            try{
                int selectedIndex = display.jTable_Productions.getSelectedRow();
                int prodId = Integer.valueOf(prodList.get(selectedIndex)[0]);
                updateTableData2(prodId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_AddPres)){
            try{
                int selectedIndex = display.jTable_Productions.getSelectedRow();
                int prodId = Integer.valueOf(prodList.get(selectedIndex)[0]);
                String date = display.jTextField_Date.getText() + ":00";
                ConnectionManager.execAdmTeCreatePresentacion(prodId, date, Utilities.USERNAME, Utilities.PASSWORD);
                updateTableData2(prodId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

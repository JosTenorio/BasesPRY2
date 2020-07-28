
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.ProductionCreationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateProductionController implements ActionListener{
    
    private static final ProductionCreationDisplay display = new ProductionCreationDisplay();
    private static CreateProductionController firstInstance = null;
    private ArrayList<String[]> typeList;
    private ArrayList<String[]> showList;
    
    private CreateProductionController(){
        init();
    }
    
    public static CreateProductionController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreateProductionController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_AddProd.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.jButton_AddShow.addActionListener(this);
        display.jButton_AddType.addActionListener(this);
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
        typeList = ConnectionManager.execAdmTeReadTipos();
        for (String[] row : typeList)
            display.tableModel1.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Types.setModel(display.tableModel1);
    }    
    
    public void updateTableData2(){
        display.tableModel2.setRowCount(0);
        showList = ConnectionManager.execAdmTeReadObras();
        for (String[] row : showList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Shows.setModel(display.tableModel2);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmTeMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_AddProd)){
            try{
                int selectedIndex = display.jTable_Shows.getSelectedRow();
                int showId = Integer.valueOf(showList.get(selectedIndex)[0]);
                ConnectionManager.execAdmTeCreateProduccion(showId, Utilities.USERNAME, Utilities.PASSWORD);
                Utilities.infoBox("Produccion agregada exitosamente", "Exito");
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_AddType)){
            String name = display.jTextField_Type.getText();
            ConnectionManager.execAdmTeCreateTipo(name);
            updateTableData1();
        }
        if (e.getSource().equals(display.jButton_AddShow)){
            try{
                int selectedIndex = display.jTable_Types.getSelectedRow();
                int typeId = Integer.valueOf(typeList.get(selectedIndex)[0]);
                String name = display.jTextField_Name.getText();
                String desc = display.jTextArea_Desc.getText();
                ConnectionManager.execAdmTeCreateObra(name, desc, typeId);
                updateTableData2();
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

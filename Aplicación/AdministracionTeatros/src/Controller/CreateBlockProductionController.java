
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.BlockProductionCreationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateBlockProductionController implements ActionListener{
    
    private static final BlockProductionCreationDisplay display = new BlockProductionCreationDisplay();
    private static CreateBlockProductionController firstInstance = null;
    private ArrayList<String[]> prodList;
    private ArrayList<String[]> blockList;
    
    private CreateBlockProductionController(){
        init();
    }
    
    public static CreateBlockProductionController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreateBlockProductionController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_AddPrice.addActionListener(this);
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
        blockList = ConnectionManager.execAdmTeReadBloques(Utilities.USERNAME, Utilities.PASSWORD);
        for (String[] row : blockList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Blocks.setModel(display.tableModel2);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmTeMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_AddPrice)){
            try{
                int selectedIndex = display.jTable_Productions.getSelectedRow();
                int prodId = Integer.valueOf(prodList.get(selectedIndex)[0]);
                selectedIndex = display.jTable_Blocks.getSelectedRow();
                int blockId = Integer.valueOf(blockList.get(selectedIndex)[0]);
                float price = Float.valueOf(display.jTextField_Price.getText());
                ConnectionManager.execAdmTeCreateBloqueProduccion(prodId,blockId, price, Utilities.USERNAME, Utilities.PASSWORD);
                Utilities.infoBox("Precio agregado exitosamente", "Exito");
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

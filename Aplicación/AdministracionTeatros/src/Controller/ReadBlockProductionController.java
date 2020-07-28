
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.BlockProductionMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadBlockProductionController implements ActionListener{
    
    private static final BlockProductionMenuDisplay display = new BlockProductionMenuDisplay();
    private static ReadBlockProductionController firstInstance = null;
    private ArrayList<String[]> blockList;
    private int productionId;
    private int presentationId;
    
    private ReadBlockProductionController(){
        init();
    }
    
    public static ReadBlockProductionController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadBlockProductionController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int productionId, int presentationId){
        this.productionId = productionId;
        this.presentationId = presentationId;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        if (Utilities.LOGINTYPE == 0)
            blockList = ConnectionManager.execPubReadBloquesProducciones(productionId);
        if (Utilities.LOGINTYPE == 1)
            blockList = ConnectionManager.execAgnTeReadBloquesProducciones(productionId, Utilities.USERNAME, Utilities.PASSWORD);
        for (String[] row : blockList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Blocks.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            ReadPresentationController.getInstance().makeVisible(true, productionId);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            try{
                int selectedIndex = display.jTable_Blocks.getSelectedRow();
                int blockId = Integer.valueOf(blockList.get(selectedIndex)[0]);
                ReadSeatPresentationController.getInstance().makeVisible(true, blockId, presentationId, productionId);
                display.setVisible(false);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

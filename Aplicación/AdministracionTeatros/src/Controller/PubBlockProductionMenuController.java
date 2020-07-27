
package Controller;

import Model.ConnectionManager;
import View.BlockProductionMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PubBlockProductionMenuController implements ActionListener{
    
    private static final BlockProductionMenuDisplay display = new BlockProductionMenuDisplay();
    private static PubBlockProductionMenuController firstInstance = null;
    private ArrayList<String[]> blockList;
    private int productionId;
    private int presentationId;
    
    private PubBlockProductionMenuController(){
        init();
    }
    
    public static PubBlockProductionMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubBlockProductionMenuController();
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
        blockList = ConnectionManager.execPubReadBloquesProducciones(productionId);
        for (String[] row : blockList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Blocks.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            PubPresentationMenuController.getInstance().makeVisible(true, productionId);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            int selectedIndex = display.jTable_Blocks.getSelectedRow();
            int blockId = Integer.valueOf(blockList.get(selectedIndex)[0]);
            PubSeatPresentationMenuController.getInstance().makeVisible(true, blockId, presentationId, productionId);
        }
    }
}

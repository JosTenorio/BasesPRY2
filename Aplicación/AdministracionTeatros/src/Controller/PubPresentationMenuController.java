
package Controller;

import Model.ConnectionManager;
import View.PresentationMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PubPresentationMenuController implements ActionListener{
    
    private static final PresentationMenuDisplay display = new PresentationMenuDisplay();
    private static PubPresentationMenuController firstInstance = null;
    private ArrayList<String[]> presentationList;
    private String startTime = null;
    private String endTime = null;
    private int productionId;
    
    private PubPresentationMenuController(){
        init();
    }
    
    public static PubPresentationMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubPresentationMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int productionId){
        this.productionId = productionId;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        presentationList = ConnectionManager.execPubReadPresentaciones(productionId, startTime, endTime);
        for (String[] row : presentationList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Presentations.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            PubProductionMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            int selectedIndex = display.jTable_Presentations.getSelectedRow();
            int presentationId = Integer.valueOf(presentationList.get(selectedIndex)[0]);
            PubBlockProductionMenuController.getInstance().makeVisible(true, productionId, presentationId);
        }
    }
}

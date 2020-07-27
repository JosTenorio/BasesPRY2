
package Controller;

import Model.ConnectionManager;
import View.SeatPresentationMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PubSeatPresentationMenuController implements ActionListener{
    
    private static final SeatPresentationMenuDisplay display = new SeatPresentationMenuDisplay();
    private static PubSeatPresentationMenuController firstInstance = null;
    private ArrayList<String[]> seatList;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private PubSeatPresentationMenuController(){
        init();
    }
    
    public static PubSeatPresentationMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubSeatPresentationMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int blockId, int presentationId, int productionId){
        this.blockId = blockId;
        this.presentationId = presentationId;
        this.productionId = productionId;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        seatList = ConnectionManager.execPubReadAsientosPresentaciones(presentationId, blockId);
        for (String[] row : seatList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Seats.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            PubBlockProductionMenuController.getInstance().makeVisible(true, productionId, presentationId);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            //ClientInformationController.getInstance().makeVisible(true, true);
        }
    }
}

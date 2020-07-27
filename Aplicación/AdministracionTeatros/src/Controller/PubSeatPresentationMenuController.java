
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
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
    private ArrayList<Integer> selectedSeatIds;
    
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
        display.jButton_Buy.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int blockId, int presentationId, int productionId){
        this.blockId = blockId;
        this.presentationId = presentationId;
        this.productionId = productionId;
        this.selectedSeatIds = new ArrayList<>();
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.jLabel_Amount.setText("");
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
            if (!selectedSeatIds.isEmpty()){
                PubPurchasePreviewController.getInstance().makeVisible(true, blockId, presentationId, productionId, selectedSeatIds);
                display.setVisible(false);
            }
            else
                Utilities.infoBox("No se selecciono ningun item", "Error");
        }
        if (e.getSource().equals(display.jButton_Buy)){
            if (selectedSeatIds.size() <= 8){
                try{
                    int selectedIndex = display.jTable_Seats.getSelectedRow();
                    int seatId = Integer.valueOf(seatList.get(selectedIndex)[0]);
                    if (!selectedSeatIds.contains(seatId)){
                        selectedSeatIds.add(seatId);
                        display.jLabel_Amount.setText(String.valueOf(selectedSeatIds.size()));
                    }
                }
                catch(Exception ex){
                    Utilities.infoBox("No se selecciono ningun item", "Error");
                }
            }
        }
    }
}

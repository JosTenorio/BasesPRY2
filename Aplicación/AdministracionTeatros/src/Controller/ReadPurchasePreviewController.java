
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.PurchasePreviewDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadPurchasePreviewController implements ActionListener{
    
    private static final PurchasePreviewDisplay display = new PurchasePreviewDisplay();
    private static ReadPurchasePreviewController firstInstance = null;
    private ArrayList<Integer> selectedSeatIds;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private ReadPurchasePreviewController(){
        init();
    }
    
    public static ReadPurchasePreviewController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadPurchasePreviewController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_PayCard.addActionListener(this);
        display.jButton_PayCash.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int blockId, int presentationId, int productionId, ArrayList<Integer> selectedSeatIds){
        this.productionId = productionId;
        this.presentationId = presentationId;
        this.blockId = blockId;
        this.selectedSeatIds = selectedSeatIds;
        if (Utilities.LOGINTYPE == 0)
            display.jButton_PayCash.setEnabled(false);
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        String[] purchasePreview = new String[6];
        if (Utilities.LOGINTYPE == 0)
            purchasePreview = ConnectionManager.execPubReadCompraResumen(selectedSeatIds);
        if (Utilities.LOGINTYPE == 1)
            purchasePreview = ConnectionManager.execAgnTeReadCompraResumen(selectedSeatIds, Utilities.USERNAME, Utilities.PASSWORD);        
        display.jLabel_Theater.setText(purchasePreview[0]);
        display.jLabel_Show.setText(purchasePreview[1]);
        display.jLabel_Time.setText(purchasePreview[2]);
        display.jLabel_Cost.setText(purchasePreview[3]);
        display.jLabel_Block.setText(purchasePreview[4]);
        display.jLabel_Seats.setText(purchasePreview[5]);
    }      

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            ReadSeatPresentationMenuController.getInstance().makeVisible(true, blockId, presentationId, productionId);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_PayCard)){
            CreatePurchaseCardController.getInstance().makeVisible(true, blockId, presentationId, productionId, selectedSeatIds);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_PayCash)){
            CreatePurchaseCashController.getInstance().makeVisible(true, blockId, presentationId, productionId, selectedSeatIds);
            display.setVisible(false);
        }
    }
}


package Controller;

import Model.ConnectionManager;
import View.PurchasePreviewDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PubPurchasePreviewController implements ActionListener{
    
    private static final PurchasePreviewDisplay display = new PurchasePreviewDisplay();
    private static PubPurchasePreviewController firstInstance = null;
    private ArrayList<Integer> selectedSeatIds;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private PubPurchasePreviewController(){
        init();
    }
    
    public static PubPurchasePreviewController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubPurchasePreviewController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int blockId, int presentationId, int productionId, ArrayList<Integer> selectedSeatIds){
        this.productionId = productionId;
        this.presentationId = presentationId;
        this.blockId = blockId;
        this.selectedSeatIds = selectedSeatIds;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        String[] purchasePreview = ConnectionManager.execPubReadCompraResumen(selectedSeatIds);
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
            PubSeatPresentationMenuController.getInstance().makeVisible(true, blockId, presentationId, productionId);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            display.setVisible(false);
        }
    }
}


package Controller;

import View.PurchaseConfirmDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PubPurchaseConfirmController implements ActionListener{
    
    private static final PurchaseConfirmDisplay display = new PurchaseConfirmDisplay();
    private static PubPurchaseConfirmController firstInstance = null;
    private String[] purchaseResume;
    
    private PubPurchaseConfirmController(){
        init();
    }
    
    public static PubPurchaseConfirmController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubPurchaseConfirmController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, String[] purchaseResume){
        this.purchaseResume = purchaseResume;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.jLabel_Theater.setText(purchaseResume[0]);
        display.jLabel_Show.setText(purchaseResume[1]);
        display.jLabel_Time.setText(purchaseResume[2]);
        display.jLabel_Cost.setText(purchaseResume[3]);
        display.jLabel_Block.setText(purchaseResume[4]);
        display.jLabel_Seats.setText(purchaseResume[5]);
        display.jLabel_PurchaseTime.setText(purchaseResume[6]);
    }      

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Confirm)){
            PubProductionMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
    }
}

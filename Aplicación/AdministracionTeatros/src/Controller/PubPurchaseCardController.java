
package Controller;

import Model.ConnectionManager;
import View.PurchaseCardDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PubPurchaseCardController implements ActionListener{
    
    private static final PurchaseCardDisplay display = new PurchaseCardDisplay();
    private static PubPurchaseCardController firstInstance = null;
    private ArrayList<Integer> selectedSeatIds;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private PubPurchaseCardController(){
        init();
    }
    
    public static PubPurchaseCardController getInstance(){
        if (firstInstance == null)
            firstInstance = new PubPurchaseCardController();
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
        if (visible == true){
            display.jTextField_Name.setText("");
            display.jTextField_Phone.setText("");
            display.jTextField_Mail.setText("");
            display.jTextField_Card.setText("");
            display.jTextField_Expiration.setText("");
            display.jTextField_Security.setText("");
        }
        display.setVisible(visible);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            PubPurchasePreviewController.getInstance().makeVisible(true, blockId, presentationId, productionId, selectedSeatIds);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            String name = display.jTextField_Name.getText();
            String phone = display.jTextField_Phone.getText();
            String mail = display.jTextField_Mail.getText();
            String card = display.jTextField_Card.getText();
            String expiration = display.jTextField_Expiration.getText();
            String security = display.jTextField_Security.getText();
            String[] purchaseConfirm = ConnectionManager.execPubCreateCompraTarjeta(selectedSeatIds, name, phone, mail, card, expiration, security);
            if (purchaseConfirm.length > 0){
                PubPurchaseConfirmController.getInstance().makeVisible(true, purchaseConfirm);
                display.setVisible(false);
            }
        }
    }
}

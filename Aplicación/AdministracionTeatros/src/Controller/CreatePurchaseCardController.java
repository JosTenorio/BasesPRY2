
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.PurchaseCardDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatePurchaseCardController implements ActionListener{
    
    private static final PurchaseCardDisplay display = new PurchaseCardDisplay();
    private static CreatePurchaseCardController firstInstance = null;
    private ArrayList<Integer> selectedSeatIds;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private CreatePurchaseCardController(){
        init();
    }
    
    public static CreatePurchaseCardController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreatePurchaseCardController();
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
            ReadPurchasePreviewController.getInstance().makeVisible(true, blockId, presentationId, productionId, selectedSeatIds);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            String name = display.jTextField_Name.getText();
            String phone = display.jTextField_Phone.getText();
            String mail = display.jTextField_Mail.getText();
            String card = display.jTextField_Card.getText();
            String expiration = display.jTextField_Expiration.getText();
            String security = display.jTextField_Security.getText();
            String[] purchaseConfirm = new String[0];
            if (Utilities.LOGINTYPE == 0)
                purchaseConfirm = ConnectionManager.execPubCreateCompraTarjeta(selectedSeatIds, name, phone, mail, card, expiration, security);
            if (Utilities.LOGINTYPE == 1)
                purchaseConfirm = ConnectionManager.execAgnTeCreateCompraTarjeta(selectedSeatIds, name, phone, mail, card, expiration, security, Utilities.USERNAME, Utilities.PASSWORD);            
            if (purchaseConfirm.length > 0){
                ReadPurchaseConfirmController.getInstance().makeVisible(true, purchaseConfirm);
                display.setVisible(false);
            }
        }
    }
}

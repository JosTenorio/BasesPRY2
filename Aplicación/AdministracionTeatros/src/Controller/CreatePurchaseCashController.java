
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.PurchaseCashDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatePurchaseCashController implements ActionListener{
    
    private static final PurchaseCashDisplay display = new PurchaseCashDisplay();
    private static CreatePurchaseCashController firstInstance = null;
    private ArrayList<Integer> selectedSeatIds;
    private int blockId;
    private int presentationId;
    private int productionId;
    
    private CreatePurchaseCashController(){
        init();
    }
    
    public static CreatePurchaseCashController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreatePurchaseCashController();
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
            String[] purchaseConfirm = new String[0];
            if (Utilities.LOGINTYPE == 1)
                purchaseConfirm = ConnectionManager.execAgnTeCreateCompraEfectivo(selectedSeatIds, name, phone, mail, Utilities.USERNAME, Utilities.PASSWORD);            
            if (purchaseConfirm.length > 0){
                ReadPurchaseConfirmController.getInstance().makeVisible(true, purchaseConfirm);
                display.setVisible(false);
            }
        }
    }
}

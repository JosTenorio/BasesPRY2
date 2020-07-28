
package Controller;

import View.AdmTeMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmTeMenuController implements ActionListener{
    
    private static final AdmTeMenuDisplay display = new AdmTeMenuDisplay();
    private static AdmTeMenuController firstInstance = null;
    
    private AdmTeMenuController(){
        init();
    }
    
    public static AdmTeMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new AdmTeMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_NewProd.addActionListener(this);
        display.jButton_NewPres.addActionListener(this);
        display.jButton_UpdProd.addActionListener(this);
        display.jButton_NewPrice.addActionListener(this);
        display.jButton_NewAgent.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back))
            MainMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewProd))
            CreateProductionController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewPres))
            CreatePresentationController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_UpdProd))
            UpdateProductionController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewPrice))
            CreateBlockProductionController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewAgent))
            CreateAgnTeController.getInstance().makeVisible(true);
        display.setVisible(false);
    }
    
}

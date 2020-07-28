
package Controller;

import View.MainMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener{
    
    private static final MainMenuDisplay display = new MainMenuDisplay();
    private static MainMenuController firstInstance = null;
    
    private MainMenuController(){
        init();
    }
    
    public static MainMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new MainMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Public.addActionListener(this);
        display.jButton_AdmTe.addActionListener(this);
        display.jButton_AdmSis.addActionListener(this);
        display.jButton_AgnTe.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Public))
            ReadProductionMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_AgnTe))
            LoginMenuController.getInstance().makeVisible(true, 1);
        if (e.getSource().equals(display.jButton_AdmTe))
            LoginMenuController.getInstance().makeVisible(true, 2);
        if (e.getSource().equals(display.jButton_AdmSis))
            LoginMenuController.getInstance().makeVisible(true, 3);
        display.setVisible(false);
    }
    
}

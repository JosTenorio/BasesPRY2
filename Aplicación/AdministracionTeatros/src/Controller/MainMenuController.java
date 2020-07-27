
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
            PubProductionMenuController.getInstance().makeVisible(true);
        //if (e.getSource().equals(display.jButton_AdmTe))

        //if (e.getSource().equals(display.jButton_AdmSis))

        //if (e.getSource().equals(display.jButton_AgnTe))

        display.setVisible(false);
    }
    
}

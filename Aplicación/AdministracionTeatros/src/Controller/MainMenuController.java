
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
        display.jButton_Client.addActionListener(this);
        display.jButton_Order.addActionListener(this);
        display.jButton_Part.addActionListener(this);
        display.jButton_Provider.addActionListener(this);
        display.jButton_Auto.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Client))
            ClientMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_Part))
            PartMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_Provider))
            ProviderMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_Auto))
            AutoMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_Order))
            OrderMenuController.getInstance().makeVisible(true);
        display.setVisible(false);
    }
    
}

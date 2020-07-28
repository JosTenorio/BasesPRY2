
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.TheaterCreationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTheaterController implements ActionListener{
    
    private static final TheaterCreationDisplay display = new TheaterCreationDisplay();
    private static CreateTheaterController firstInstance = null;
    
    private CreateTheaterController(){
        init();
    }
    
    public static CreateTheaterController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreateTheaterController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        if (visible == true){
            display.jTextField_Name.setText("");
            display.jTextField_Dir.setText("");
            display.jTextField_Mail.setText("");
            display.jTextField_Link.setText("");
            display.jTextField_PhoneHome.setText("");
            display.jTextField_PhoneAdmin.setText("");
        }
        display.setVisible(visible);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmSisMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            String name = display.jTextField_Name.getText();
            String dir = display.jTextField_Dir.getText();
            String mail = display.jTextField_Mail.getText();
            String link = display.jTextField_Link.getText();
            String home = display.jTextField_PhoneHome.getText();
            String admin = display.jTextField_PhoneAdmin.getText();
            ConnectionManager.execAdmSisCreateTeatro(name, dir, mail, link, home, admin);
            Utilities.infoBox("Teatro creado exitosamente", "Exito");
        }
    }
}

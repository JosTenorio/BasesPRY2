
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.LoginMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenuController implements ActionListener{
    
    private static final LoginMenuDisplay display = new LoginMenuDisplay();
    private static LoginMenuController firstInstance = null;
    private int loginType;
    
    private LoginMenuController(){
        init();
    }
    
    public static LoginMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new LoginMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Login.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int loginType){
        this.loginType = loginType;
        if (visible == true){
            display.jTextField_Password.setText("");
            display.jTextField_Username.setText("");
        }
        display.setVisible(visible);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Login)){
            String user = display.jTextField_Username.getText();
            String pass = display.jTextField_Password.getText();
            boolean connection = false;
            switch(loginType){
                case 1:
                    connection = ConnectionManager.execPubLoginAgnTe(user, pass);
                    break;
                case 2:
                    connection = ConnectionManager.execPubLoginAdmTe(user, pass);
                    break;
                case 3:  
                    connection = ConnectionManager.execPubLoginAdmSis(user, pass);
                    break;
            }
            if (connection){
                Utilities.setCredentials(user, pass, loginType);
                switch(loginType){
                    case 1:
                        ReadProductionController.getInstance().makeVisible(true);
                        break;
                    case 2:
                        AdmTeMenuController.getInstance().makeVisible(true);
                        break;
                    case 3:  
                        AdmSisMenuController.getInstance().makeVisible(true);
                        break;
                }
                display.setVisible(false);
            }
            else
                Utilities.infoBox("Credenciales incorrectas", "Conneccion Fallida");
        }
    }
    
}

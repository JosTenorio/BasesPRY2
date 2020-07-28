
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.AddAgnTeDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAgnTeController implements ActionListener{
    
    private static final AddAgnTeDisplay display = new AddAgnTeDisplay();
    private static CreateAgnTeController firstInstance = null;
    
    private CreateAgnTeController(){
        init();
    }
    
    public static CreateAgnTeController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreateAgnTeController();
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
            display.jTextField_Ced.setText("");
            display.jTextField_Birth.setText("");
            display.jTextField_Dir.setText("");
            display.jTextField_Sex.setText("");
            display.jTextField_Mail.setText("");
            display.jTextField_PhoneCel.setText("");
            display.jTextField_PhoneHome.setText("");
            display.jTextField_PhoneOther.setText("");
            display.jTextField_User.setText("");
            display.jTextField_Pass.setText("");
        }
        display.setVisible(visible);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmTeMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            String name = display.jTextField_Name.getText();
            String ced = display.jTextField_Ced.getText();
            String birth = display.jTextField_Birth.getText();
            String dir = display.jTextField_Dir.getText();
            String sex = display.jTextField_Sex.getText();
            String mail = display.jTextField_Mail.getText();
            String cel = display.jTextField_PhoneCel.getText();
            String home = display.jTextField_PhoneHome.getText();
            String other = display.jTextField_PhoneOther.getText();
            String user = display.jTextField_User.getText();
            String pass = display.jTextField_Pass.getText();
            ConnectionManager.execAdmTeCreateEmpleadoAgnTe(ced, name, birth, dir, sex, mail, user, pass, cel, home, other, Utilities.USERNAME, Utilities.PASSWORD);
            Utilities.infoBox("Agente de teatro agregado exitosamente", "Exito");
        }
    }
}

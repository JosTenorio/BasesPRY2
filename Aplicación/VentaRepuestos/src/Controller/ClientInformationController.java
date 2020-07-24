
package Controller;

import Model.ClientQuery;
import Model.ConsultQuery;
import View.ClientInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ClientInformationController implements ActionListener{
    
    private static final ClientInformationDisplay display = new ClientInformationDisplay();
    private static ClientInformationController firstInstance = null;
    private boolean newClient;
    
    private ClientInformationController(){
        init();
    }
    
    public static ClientInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new ClientInformationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Accept.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void makeVisible(boolean visible, boolean newClient){
        display.setVisible(visible);
        this.newClient = newClient;
        clearInfo();
        if (visible == true)
            updateComboBoxData();
    }      

    public void updateComboBoxData(){
        display.comboBoxModel.removeAllElements();
        ArrayList<String> statusList = ConsultQuery.listStatusDropdown();
        for (String status : statusList)
            display.comboBoxModel.addElement(status);
        display.jComboBox_Status.setModel(display.comboBoxModel);
    }
    
    public void setInfo(String[] client){
        display.jTextField_Name.setText(client[0]);
        display.jTextField_Id.setText(client[1]);
        display.jTextField_Addres.setText(client[3]);
        display.jTextField_City.setText(client[4]);
        display.jTextField_ContactName.setText(client[5]);
        display.jTextField_ContactCharge.setText(client[6]);
        display.jTextField_Phone1.setText(client[7]);
        display.jTextField_Phone2.setText(client[8]);
        display.jComboBox_Status.setSelectedItem(client[2]);
        if ("TRUE".equals(client[9]))
            display.jCheckBox_Org.setSelected(true);
        display.updateTextFields();
        display.jCheckBox_Org.setEnabled(false);
        display.jTextField_Id.setEditable(false);
    }
    
    public void clearInfo(){
        display.jTextField_Name.setText("");
        display.jTextField_Id.setText("");
        display.jTextField_Addres.setText("");
        display.jTextField_City.setText("");
        display.jTextField_ContactName.setText("");
        display.jTextField_ContactCharge.setText("");
        display.jTextField_Phone1.setText("");
        display.jTextField_Phone2.setText("");
        display.jCheckBox_Org.setSelected(false);
        display.updateTextFields();
        display.jCheckBox_Org.setEnabled(true);
        display.jTextField_Id.setEditable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            if (newClient)
                addNewClient();
            else
                modifyClient();
            ClientMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewClient(){
        boolean organization = display.jCheckBox_Org.isSelected();
        String status = String.valueOf(display.jComboBox_Status.getSelectedItem());
        ArrayList<String> info = new ArrayList<>(){
            {
                add(display.jTextField_Id.getText());
                add(display.jTextField_Name.getText());
                add(display.jTextField_Addres.getText());
                add(display.jTextField_City.getText());
                if (organization){
                    add(display.jTextField_ContactName.getText());
                    add(display.jTextField_ContactCharge.getText());
                }
            }
        };
        ArrayList<String> telephones = new ArrayList<>(){
            {
                if (!display.jTextField_Phone1.getText().equals(""))
                    add(display.jTextField_Phone1.getText());
                if (!display.jTextField_Phone2.getText().equals(""))
                    add(display.jTextField_Phone2.getText());
            }
        };
        ClientQuery.insertClient(status, info, telephones, organization);
    }
    
    private void modifyClient(){
        boolean organization = display.jCheckBox_Org.isSelected();
        String status = String.valueOf(display.jComboBox_Status.getSelectedItem());
        String clientCed = display.jTextField_Id.getText();
        ArrayList<String> info = new ArrayList<>(){
            {
                add(display.jTextField_Name.getText());
                add(display.jTextField_Addres.getText());
                add(display.jTextField_City.getText());
                if (organization){
                    add(display.jTextField_ContactName.getText());
                    add(display.jTextField_ContactCharge.getText());
                }
            }
        };
        ArrayList<String> telephones = new ArrayList<>(){
            {
                if (!display.jTextField_Phone1.getText().equals(""))
                    add(display.jTextField_Phone1.getText());
                if (!display.jTextField_Phone2.getText().equals(""))
                    add(display.jTextField_Phone2.getText());
            }
        };
        ClientQuery.modifyClient(clientCed, status, info, telephones, organization);
    }
}

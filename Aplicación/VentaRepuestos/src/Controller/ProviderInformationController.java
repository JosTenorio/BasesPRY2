
package Controller;

import Model.ConsultQuery;
import Model.PartQuery;
import View.ProviderInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ProviderInformationController implements ActionListener{
    
    private static final ProviderInformationDisplay display = new ProviderInformationDisplay();
    private static ProviderInformationController firstInstance = null;
    private ArrayList<String[]> partList;
    private ArrayList<String[]> provList;
    private boolean newPartProv;
    
    
    private ProviderInformationController(){
        init();
    }
    
    public static ProviderInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new ProviderInformationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Accept.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void makeVisible(boolean visible, boolean newPartProv){
        display.setVisible(visible);
        this.newPartProv = newPartProv;
        clearInfo();
        if (visible == true)
            updateComboBoxData();
    }      

    public void updateComboBoxData(){
        display.comboBoxModelPart.removeAllElements();
        display.comboBoxModelProv.removeAllElements();
        partList = ConsultQuery.listPartsDropdown();
        for (String[] part : partList)
            display.comboBoxModelPart.addElement(part[1]);
        provList = ConsultQuery.listProvidersDropdown();
        for (String[] prov : provList)
            display.comboBoxModelProv.addElement(prov[1]);
        display.jComboBox_Part.setModel(display.comboBoxModelPart);
        display.jComboBox_Provider.setModel(display.comboBoxModelProv);
    }
    
    public void clearInfo(){
        display.jTextField_PriceProv.setText("");
        display.jTextField_Gain.setText("");
    }
    
    public void setInfo(String[] partProv){
        display.jTextField_PriceProv.setText(partProv[4]);
        display.jTextField_Gain.setText(partProv[5]);
        display.jComboBox_Part.setSelectedItem(partProv[2]);
        display.jComboBox_Provider.setSelectedItem(partProv[3]);
        display.jComboBox_Part.setEnabled(false);
        display.jComboBox_Provider.setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            if (newPartProv)
                addNewPartProvider();
            else
                modifyPartProvider();
            ProviderMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewPartProvider(){
        String partId = partList.get(display.jComboBox_Part.getSelectedIndex())[0];
        String providerId = provList.get(display.jComboBox_Provider.getSelectedIndex())[0];
        String providerPrice = display.jTextField_PriceProv.getText();
        String gain = display.jTextField_Gain.getText();
        PartQuery.asociatePartProvider(partId, providerId, providerPrice, gain);
    }
    
    private void modifyPartProvider(){
        String partId = partList.get(display.jComboBox_Part.getSelectedIndex())[0];
        String providerId = provList.get(display.jComboBox_Provider.getSelectedIndex())[0];
        String providerPrice = display.jTextField_PriceProv.getText();
        String gain = display.jTextField_Gain.getText();
        PartQuery.modifyPartProvider(partId, providerId, providerPrice, gain);
    }
}

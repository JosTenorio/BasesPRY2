
package Controller;

import Model.ConsultQuery;
import Model.PartQuery;
import View.PartInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class PartInformationController implements ActionListener{
    
    private static final PartInformationDisplay display = new PartInformationDisplay();
    private static PartInformationController firstInstance = null;
    
    private PartInformationController(){
        init();
    }
    
    public static PartInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new PartInformationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Accept.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        clearInfo();
        if (visible == true)
            updateComboBoxData();
    }      

    public void updateComboBoxData(){
        display.comboBoxModelFab.removeAllElements();
        display.comboBoxModelBrand.removeAllElements();
        ArrayList<String> fabPartsList = ConsultQuery.listFabPartsDropdown();
        for (String fabParts : fabPartsList)
            display.comboBoxModelFab.addElement(fabParts);
        ArrayList<String> brandsList = ConsultQuery.listBrandsDropdown();
        for (String brand : brandsList)
            display.comboBoxModelBrand.addElement(brand);
        display.jComboBox_Fab.setModel(display.comboBoxModelFab);
        display.jComboBox_Brand.setModel(display.comboBoxModelBrand);
    }
    
    public void clearInfo(){
        display.jTextField_Name.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            addNewClient();
            PartMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewClient(){
        String name = display.jTextField_Name.getText();
        String fab = String.valueOf(display.jComboBox_Fab.getSelectedItem());
        String brand = String.valueOf(display.jComboBox_Brand.getSelectedItem());
        PartQuery.insertPart(name, brand, fab);
    }
    
}

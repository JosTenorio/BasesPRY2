
package Controller;

import Model.ConsultQuery;
import Model.PartQuery;
import View.AutoInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class AutoInformationController implements ActionListener{
    
    private static final AutoInformationDisplay display = new AutoInformationDisplay();
    private static AutoInformationController firstInstance = null;
    private ArrayList<String[]> partList;
    private ArrayList<String[]> autoList;
    
    private AutoInformationController(){
        init();
    }
    
    public static AutoInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new AutoInformationController();
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
        display.comboBoxModelPart.removeAllElements();
        display.comboBoxModelAuto.removeAllElements();
        partList = ConsultQuery.listPartsDropdown();
        for (String[] part : partList)
            display.comboBoxModelPart.addElement(part[1]);
        autoList = ConsultQuery.listAutosDropdown();
        for (String[] auto : autoList)
            display.comboBoxModelAuto.addElement(auto[1] + " (" + auto[2] + ")");
        display.jComboBox_Part.setModel(display.comboBoxModelPart);
        display.jComboBox_Auto.setModel(display.comboBoxModelAuto);
    }
    
    public void clearInfo(){
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            addNewPartAuto();
            AutoMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewPartAuto(){
        String partId = partList.get(display.jComboBox_Part.getSelectedIndex())[0];
        String autoId = autoList.get(display.jComboBox_Auto.getSelectedIndex())[0];
        PartQuery.asociatePartCar(partId, autoId);
    }
}

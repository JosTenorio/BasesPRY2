
package Controller;

import Model.ConsultQuery;
import Model.OrderQuery;
import View.DetailInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class DetailInformationController implements ActionListener{
    
    private static final DetailInformationDisplay display = new DetailInformationDisplay();
    private static DetailInformationController firstInstance = null;
    private ArrayList<String[]> detailList;
    private ArrayList<String[]> partProvList;
    private String orderId;
    
    private DetailInformationController(){
        init();
    }
    
    public static DetailInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new DetailInformationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Accept.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void makeVisible(boolean visible, String orderId){
        display.setVisible(visible);
        this.orderId = orderId;
        clearInfo();
        if (visible == true){
            updateComboBoxData();
            updateTableData();
        }
    }      

    public void updateComboBoxData(){
        display.comboBoxModelPart.removeAllElements();
        partProvList = ConsultQuery.listPartProvidersDropdown();
        for (String[] partProv : partProvList)
            display.comboBoxModelPart.addElement(partProv[2] + " (" + partProv[3] + ")");
        display.jComboBox_Part.setModel(display.comboBoxModelPart);
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        detailList = ConsultQuery.listDetailsForm(orderId);
        for (String[] detail : detailList)
            display.tableModel.addRow(detail);
        display.jTable_Detail.setModel(display.tableModel);
    }
    
    public void clearInfo(){
        display.jTextField_Amount.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            addNewDetail();
            updateTableData();
            OrderMenuController.getInstance().updateTableData();
        }
    }
    
    private void addNewDetail(){
        String partId = partProvList.get(display.jComboBox_Part.getSelectedIndex())[0];
        String provId = partProvList.get(display.jComboBox_Part.getSelectedIndex())[1];
        String amount = display.jTextField_Amount.getText();
        OrderQuery.addDetail(orderId, partId, provId, amount);
    }
}

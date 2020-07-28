
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.PresentationMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadPresentationController implements ActionListener{
    
    private static final PresentationMenuDisplay display = new PresentationMenuDisplay();
    private static ReadPresentationController firstInstance = null;
    private ArrayList<String[]> presentationList;
    private String startTime = null;
    private String endTime = null;
    private int productionId;
    
    private ReadPresentationController(){
        init();
    }
    
    public static ReadPresentationController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadPresentationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Confirm.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.jButton_SearchDate.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible, int productionId){
        this.productionId = productionId;
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        if (Utilities.LOGINTYPE == 0)
            presentationList = ConnectionManager.execPubReadPresentaciones(productionId, startTime, endTime);
        if (Utilities.LOGINTYPE == 1)
            presentationList = ConnectionManager.execAgnTeReadPresentaciones(productionId, startTime, endTime, Utilities.USERNAME, Utilities.PASSWORD);  
        for (String[] row : presentationList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Presentations.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            ReadProductionController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_Confirm)){
            try{
                int selectedIndex = display.jTable_Presentations.getSelectedRow();
                int presentationId = Integer.valueOf(presentationList.get(selectedIndex)[0]);
                ReadBlockProductionController.getInstance().makeVisible(true, productionId, presentationId);
                display.setVisible(false);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_SearchDate)){
            if (!"".equals(display.jTextField_DateStart.getText()))
                startTime = display.jTextField_DateStart.getText() + ":00";
            else
                startTime = null;
            if (!"".equals(display.jTextField_DateEnd.getText()))
                endTime = display.jTextField_DateEnd.getText() + ":00";
            else
                endTime = null;
            updateTableData();
        }
    }
}

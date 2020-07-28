
package Controller;

import Model.ConnectionManager;
import View.EmployeeDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadEmployeesController implements ActionListener{
    
    private static final EmployeeDisplay display = new EmployeeDisplay();
    private static ReadEmployeesController firstInstance = null;
    
    private ReadEmployeesController(){
        init();
    }
    
    public static ReadEmployeesController getInstance(){
        if (firstInstance == null)
            firstInstance = new ReadEmployeesController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        ArrayList<String[]> employeeList = ConnectionManager.execAdmSisReadEmpleados(0);
        for (String[] row : employeeList)
            display.tableModel.addRow(Arrays.copyOfRange(row, 0, row.length));
        display.jTable_Employees.setModel(display.tableModel);
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmSisMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
    }
}


package Controller;

import View.AdmSisMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmSisMenuController implements ActionListener{
    
    private static final AdmSisMenuDisplay display = new AdmSisMenuDisplay();
    private static AdmSisMenuController firstInstance = null;
    
    private AdmSisMenuController(){
        init();
    }
    
    public static AdmSisMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new AdmSisMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_NewTheater.addActionListener(this);
        display.jButton_NewSeats.addActionListener(this);
        display.jButton_PaymentHistory.addActionListener(this);
        display.jButton_Employees.addActionListener(this);
        display.jButton_NewAdmin.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back))
            MainMenuController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewTheater))
            CreateTheaterController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewSeats))
            CreateSeatsController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_PaymentHistory))
            ReadPurchaseHistoryController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_Employees))
            ReadEmployeesController.getInstance().makeVisible(true);
        if (e.getSource().equals(display.jButton_NewAdmin))
            ReadTheaterController.getInstance().makeVisible(true);
        display.setVisible(false);
    }
    
}

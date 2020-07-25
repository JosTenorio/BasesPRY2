
package Controller;

import Model.ConsultQuery;
import View.OrderMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class OrderMenuController implements ActionListener{
    
    private static final OrderMenuDisplay display = new OrderMenuDisplay();
    private static OrderMenuController firstInstance = null;
    private ArrayList<String[]> orderList;
    public static final double IVA = 13.0/100;
    
    private OrderMenuController(){
        init();
    }
    
    public static OrderMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new OrderMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewOrder.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        initPopUpMenu();
        initTable();
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    private void initPopUpMenu(){
        JMenuItem modify = new JMenuItem("Modificar");
        modify.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int index = display.jTable_Orders.getSelectedRow();
                if (index != -1){
                    DetailInformationController.getInstance().makeVisible(true, orderList.get(index)[0]);
                }
            }
        });
        display.popUpMenu.add(modify);
    }
    
    private void initTable(){
        display.jTable_Orders.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (SwingUtilities.isRightMouseButton(e))
                    display.popUpMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        orderList = ConsultQuery.listOrdersTable();
        for (String[] order : orderList)
            display.tableModel.addRow(Arrays.copyOfRange(order, 1, 6));
        display.jTable_Orders.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewOrder)){
            OrderInformationController.getInstance().makeVisible(true);
        }
    }
}

package Controller;

import Model.ConsultQuery;
import View.AutoMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class AutoMenuController implements ActionListener{
    
    private static final AutoMenuDisplay display = new AutoMenuDisplay();
    private static AutoMenuController firstInstance = null;
    private ArrayList<String[]> partAutoList;
    
    private AutoMenuController(){
        init();
    }
    
    public static AutoMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new AutoMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewPartAuto.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.jButton_Search.addActionListener(this);
        display.jButton_Refresh.addActionListener(this);
        initPopUpMenu();
        initTable();
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    private void initPopUpMenu(){
        JMenuItem empty = new JMenuItem("");
        display.popUpMenu.add(empty);
    }
    
    private void initTable(){
        display.jTable_Auto.addMouseListener(new MouseAdapter(){
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
        display.jTextField_SearchModel.setText("");
        display.jTextField_SearchYear.setText("");
        display.tableModel.setRowCount(0);
        partAutoList = ConsultQuery.listPartAutosTable();
        for (String[] partAuto : partAutoList)
            display.tableModel.addRow(Arrays.copyOfRange(partAuto, 2, 5));
        display.jTable_Auto.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewPartAuto)){
            AutoInformationController.getInstance().makeVisible(true);
        }
        if (e.getSource().equals(display.jButton_Search)){
            display.tableModel.setRowCount(0);
            partAutoList = ConsultQuery.listPartAutosTable(display.jTextField_SearchModel.getText(), display.jTextField_SearchYear.getText());
            for (String[] partAuto : partAutoList)
                display.tableModel.addRow(Arrays.copyOfRange(partAuto, 2, 5));
            display.jTable_Auto.setModel(display.tableModel);
        }
        if (e.getSource().equals(display.jButton_Refresh))
            updateTableData();
    }
}
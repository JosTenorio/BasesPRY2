
package Controller;

import Model.ConsultQuery;
import View.ProviderMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class ProviderMenuController implements ActionListener{
    
    private static final ProviderMenuDisplay display = new ProviderMenuDisplay();
    private static ProviderMenuController firstInstance = null;
    private ArrayList<String[]> partProvList;
    
    private ProviderMenuController(){
        init();
    }
    
    public static ProviderMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new ProviderMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewPartProvider.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        display.jButton_Search.addActionListener(this);
        display.jButton_Refresh.addActionListener(this);
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
                int index = display.jTable_Provider.getSelectedRow();
                if (index != -1){
                    String[] partProv = partProvList.get(index);
                    ProviderInformationController.getInstance().makeVisible(true, false);
                    ProviderInformationController.getInstance().setInfo(partProv);
                }
            }
        });
        display.popUpMenu.add(modify);
    }
    
    private void initTable(){
        display.jTable_Provider.addMouseListener(new MouseAdapter(){
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
        display.jTextField_SearchPart.setText("");
        display.tableModel.setRowCount(0);
        partProvList = ConsultQuery.listPartProvidersTable();
        for (String[] partProv : partProvList)
            display.tableModel.addRow(Arrays.copyOfRange(partProv, 2, 7));
        display.jTable_Provider.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewPartProvider)){
            ProviderInformationController.getInstance().makeVisible(true, true);
        }
        if (e.getSource().equals(display.jButton_Search)){
            display.tableModel.setRowCount(0);
            partProvList = ConsultQuery.listPartProvidersTable(display.jTextField_SearchPart.getText());
            for (String[] partProv : partProvList)
                display.tableModel.addRow(Arrays.copyOfRange(partProv, 2, 7));
            display.jTable_Provider.setModel(display.tableModel);
        }
        if (e.getSource().equals(display.jButton_Refresh))
            updateTableData();
    }
}
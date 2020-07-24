
package Controller;

import Model.ConsultQuery;
import View.ClientMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class ClientMenuController implements ActionListener{
    
    private static final ClientMenuDisplay display = new ClientMenuDisplay();
    private static ClientMenuController firstInstance = null;
    private ArrayList<String[]> clientList;
    
    private ClientMenuController(){
        init();
    }
    
    public static ClientMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new ClientMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewClient.addActionListener(this);
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
                int index = display.jTable_Clients.getSelectedRow();
                if (index != -1){
                    String[] client = ConsultQuery.listClientForm(clientList.get(index));
                    ClientInformationController.getInstance().makeVisible(true, false);
                    ClientInformationController.getInstance().setInfo(client);
                }
            }
        });
        display.popUpMenu.add(modify);
    }
    
    private void initTable(){
        display.jTable_Clients.addMouseListener(new MouseAdapter(){
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
        clientList = ConsultQuery.listClientsTable();
        for (String[] client : clientList)
            display.tableModel.addRow(Arrays.copyOfRange(client, 1, 4));
        display.jTable_Clients.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewClient)){
            ClientInformationController.getInstance().makeVisible(true, true);
        }
    }
}

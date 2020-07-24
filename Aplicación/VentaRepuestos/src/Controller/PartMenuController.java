
package Controller;

import Model.ConsultQuery;
import Model.PartQuery;
import View.PartMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class PartMenuController implements ActionListener{
    
    private static final PartMenuDisplay display = new PartMenuDisplay();
    private static PartMenuController firstInstance = null;
    private ArrayList<String[]> partList;
    
    private PartMenuController(){
        init();
    }
    
    public static PartMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new PartMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewPart.addActionListener(this);
        display.jButton_Back.addActionListener(this);
        initPopUpMenu();
        initTable();
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    private void initPopUpMenu(){
        JMenuItem delete = new JMenuItem("Eliminar");
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int index = display.jTable_Parts.getSelectedRow();
                if (index != -1){
                    PartQuery.deletePart(partList.get(index)[0]);
                    updateTableData();
                }
            }
        });
        display.popUpMenu.add(delete);
    }
    
    private void initTable(){
        display.jTable_Parts.addMouseListener(new MouseAdapter(){
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
        partList = ConsultQuery.listPartsTable();
        for (String[] part : partList)
            display.tableModel.addRow(Arrays.copyOfRange(part, 1, 4));
        display.jTable_Parts.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewPart)){
            PartInformationController.getInstance().makeVisible(true);
        }
    }
}
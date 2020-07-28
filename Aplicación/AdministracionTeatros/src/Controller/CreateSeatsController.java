
package Controller;

import Model.ConnectionManager;
import Model.Utilities;
import View.SeatCreationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateSeatsController implements ActionListener{
    
    private static final SeatCreationDisplay display = new SeatCreationDisplay();
    private static CreateSeatsController firstInstance = null;
    private ArrayList<String[]> theaterList;
    private ArrayList<String[]> blockList;
    
    private CreateSeatsController(){
        init();
    }
    
    public static CreateSeatsController getInstance(){
        if (firstInstance == null)
            firstInstance = new CreateSeatsController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Back.addActionListener(this);
        display.jButton_ViewBlock.addActionListener(this);
        display.jButton_ViewTheater.addActionListener(this);
        display.jButton_AddBlock.addActionListener(this);
        display.jButton_AddSeats.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true){
            updateTableData1();
            updateTableData2();
            updateTableData3();
        }
    }
    
    public void updateTableData1(){
        display.tableModel1.setRowCount(0);
        theaterList = ConnectionManager.execAdmSisReadTeatros();
        for (String[] row : theaterList)
            display.tableModel1.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Theaters.setModel(display.tableModel1);
    }    
    
    public void updateTableData2(int theaterId){
        display.tableModel2.setRowCount(0);
        blockList = ConnectionManager.execAdmSisReadBloques(theaterId);
        for (String[] row : blockList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Blocks.setModel(display.tableModel2);
    } 
    
    private void updateTableData2(){
        display.tableModel2.setRowCount(0);
        blockList = new ArrayList<>();
        for (String[] row : blockList)
            display.tableModel2.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Blocks.setModel(display.tableModel2);
    }  
    
    public void updateTableData3(int blockId){
        display.tableModel3.setRowCount(0);
        ArrayList<String[]> seatList = ConnectionManager.execAdmSisReadAsientos(blockId);
        for (String[] row : seatList)
            display.tableModel3.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Seats.setModel(display.tableModel3);
    } 
    
    private void updateTableData3(){
        display.tableModel3.setRowCount(0);
        ArrayList<String[]> seatList = new ArrayList<>();
        for (String[] row : seatList)
            display.tableModel3.addRow(Arrays.copyOfRange(row, 1, row.length));
        display.jTable_Seats.setModel(display.tableModel3);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            AdmSisMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_ViewTheater)){
            try{
                int selectedIndex = display.jTable_Theaters.getSelectedRow();
                int theaterId = Integer.valueOf(theaterList.get(selectedIndex)[0]);
                updateTableData2(theaterId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_ViewBlock)){
            try{
                int selectedIndex = display.jTable_Blocks.getSelectedRow();
                int blockId = Integer.valueOf(blockList.get(selectedIndex)[0]);
                updateTableData3(blockId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_AddBlock)){
            try{
                int selectedIndex = display.jTable_Theaters.getSelectedRow();
                int theaterId = Integer.valueOf(theaterList.get(selectedIndex)[0]);
                String name = display.jTextField_Block.getText();
                ConnectionManager.execAdmSisCreateBloque(theaterId, name);
                updateTableData2(theaterId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
        if (e.getSource().equals(display.jButton_AddSeats)){
            try{
                int selectedIndex = display.jTable_Blocks.getSelectedRow();
                int blockId = Integer.valueOf(blockList.get(selectedIndex)[0]);
                int amount = Integer.valueOf(display.jTextField_SeatAmount.getText());
                ConnectionManager.execAdmSisCreateAsiento(blockId, amount);
                updateTableData3(blockId);
            }
            catch(Exception ex){
                Utilities.infoBox("No se selecciono ningun item", "Error");
            }
        }
    }
}

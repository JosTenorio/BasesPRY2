
package View;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ProductionCreationDisplay extends javax.swing.JFrame {
    
    public DefaultTableModel tableModel1;
    public DefaultTableModel tableModel2;
    private final int ColumnAmount = 1;

    public ProductionCreationDisplay() {
        initComponents();
        this.tableModel1 = (DefaultTableModel) jTable_Types.getModel();
        initTable1();
        this.tableModel2 = (DefaultTableModel) jTable_Shows.getModel();
        initTable2();
        jTextArea_Desc.setLineWrap(true);
    }
    
    private void initTable1(){
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < ColumnAmount; i++)
            jTable_Types.getColumnModel().getColumn(i).setCellRenderer(renderer);
    }
    
    private void initTable2(){
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < ColumnAmount; i++)
            jTable_Shows.getColumnModel().getColumn(i).setCellRenderer(renderer);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_BG = new javax.swing.JPanel();
        jPanel_Header = new javax.swing.JPanel();
        jLabel_Title = new javax.swing.JLabel();
        jButton_Confirm = new javax.swing.JButton();
        jButton_Back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Types = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Shows = new javax.swing.JTable();
        jLabel_Desc = new javax.swing.JLabel();
        jLabel_Name = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_Desc = new javax.swing.JTextArea();
        jLabel_Type = new javax.swing.JLabel();
        jTextField_Type = new javax.swing.JTextField();
        jButton_AddType = new javax.swing.JButton();
        jButton_AddShow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_BG.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_Header.setBackground(new java.awt.Color(0, 51, 204));

        jLabel_Title.setFont(new java.awt.Font("Gill Sans MT", 1, 42)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Title.setText("CREAR PRODUCCION");

        javax.swing.GroupLayout jPanel_HeaderLayout = new javax.swing.GroupLayout(jPanel_Header);
        jPanel_Header.setLayout(jPanel_HeaderLayout);
        jPanel_HeaderLayout.setHorizontalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addComponent(jLabel_Title)
                .addContainerGap(299, Short.MAX_VALUE))
        );
        jPanel_HeaderLayout.setVerticalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jButton_Confirm.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Confirm.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton_Confirm.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Confirm.setText("CONFIRMAR");
        jButton_Confirm.setToolTipText("");

        jButton_Back.setBackground(new java.awt.Color(0, 51, 204));
        jButton_Back.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton_Back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Back.setText("REGRESAR");

        jTable_Types.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jTable_Types.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Types.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable_Types);

        jTable_Shows.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jTable_Shows.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Obra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Shows.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable_Shows);

        jLabel_Desc.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_Desc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Desc.setText("DESCRIPCION:");

        jLabel_Name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Name.setText("NOMBRE:");

        jTextField_Name.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextField_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NameActionPerformed(evt);
            }
        });

        jTextArea_Desc.setColumns(20);
        jTextArea_Desc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextArea_Desc.setRows(3);
        jScrollPane4.setViewportView(jTextArea_Desc);

        jLabel_Type.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_Type.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Type.setText("NOMBRE:");

        jTextField_Type.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextField_Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_TypeActionPerformed(evt);
            }
        });

        jButton_AddType.setBackground(new java.awt.Color(0, 51, 204));
        jButton_AddType.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton_AddType.setForeground(new java.awt.Color(255, 255, 255));
        jButton_AddType.setText("AGREGAR");
        jButton_AddType.setToolTipText("");

        jButton_AddShow.setBackground(new java.awt.Color(0, 51, 204));
        jButton_AddShow.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton_AddShow.setForeground(new java.awt.Color(255, 255, 255));
        jButton_AddShow.setText("AGREGAR");
        jButton_AddShow.setToolTipText("");

        javax.swing.GroupLayout jPanel_BGLayout = new javax.swing.GroupLayout(jPanel_BG);
        jPanel_BG.setLayout(jPanel_BGLayout);
        jPanel_BGLayout.setHorizontalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                        .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel_Type)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jButton_AddType))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                                        .addComponent(jLabel_Desc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane4))
                                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                                        .addComponent(jLabel_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_AddShow)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                        .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Confirm)))
                .addContainerGap())
        );
        jPanel_BGLayout.setVerticalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addComponent(jPanel_Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Confirm, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Type)
                    .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Name)
                        .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_Desc)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jButton_AddShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_AddType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NameActionPerformed

    private void jTextField_TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_TypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_TypeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_AddShow;
    public javax.swing.JButton jButton_AddType;
    public javax.swing.JButton jButton_Back;
    public javax.swing.JButton jButton_Confirm;
    private javax.swing.JLabel jLabel_Desc;
    private javax.swing.JLabel jLabel_Name;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_Type;
    private javax.swing.JPanel jPanel_BG;
    private javax.swing.JPanel jPanel_Header;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTable jTable_Shows;
    public javax.swing.JTable jTable_Types;
    public javax.swing.JTextArea jTextArea_Desc;
    public javax.swing.JTextField jTextField_Name;
    public javax.swing.JTextField jTextField_Type;
    // End of variables declaration//GEN-END:variables
}

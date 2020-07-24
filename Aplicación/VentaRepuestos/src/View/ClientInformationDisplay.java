
package View;

import javax.swing.DefaultComboBoxModel;

public class ClientInformationDisplay extends javax.swing.JFrame {
    
    public DefaultComboBoxModel comboBoxModel;

    public ClientInformationDisplay() {
        initComponents();
        updateTextFields();
        this.comboBoxModel = (DefaultComboBoxModel) jComboBox_Status.getModel();
    }
    
    public void updateTextFields(){
        if (jCheckBox_Org.isSelected()){
            jLabel_ContactName.setEnabled(true);
            jLabel_ContactCharge.setEnabled(true);
            jTextField_ContactName.setEnabled(true);
            jTextField_ContactCharge.setEnabled(true);
        }
        else{
            jLabel_ContactName.setEnabled(false);
            jLabel_ContactCharge.setEnabled(false);
            jTextField_ContactName.setEnabled(false);
            jTextField_ContactCharge.setEnabled(false);
        }
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
        jLabel_Name = new javax.swing.JLabel();
        jLabel_Id = new javax.swing.JLabel();
        jCheckBox_Org = new javax.swing.JCheckBox();
        jLabel_Addres = new javax.swing.JLabel();
        jLabel_City = new javax.swing.JLabel();
        jLabel_Phone1 = new javax.swing.JLabel();
        jLabel_Phone2 = new javax.swing.JLabel();
        jLabel_Status = new javax.swing.JLabel();
        jLabel_ContactName = new javax.swing.JLabel();
        jLabel_ContactCharge = new javax.swing.JLabel();
        jComboBox_Status = new javax.swing.JComboBox<>();
        jButton_Accept = new javax.swing.JButton();
        jTextField_Id = new javax.swing.JTextField();
        jTextField_City = new javax.swing.JTextField();
        jTextField_Name = new javax.swing.JTextField();
        jTextField_Addres = new javax.swing.JTextField();
        jTextField_ContactName = new javax.swing.JTextField();
        jTextField_ContactCharge = new javax.swing.JTextField();
        jTextField_Phone2 = new javax.swing.JTextField();
        jTextField_Phone1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_BG.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_Header.setBackground(new java.awt.Color(153, 0, 0));

        jLabel_Title.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Title.setText("INFORMACION DE CLIENTE");

        javax.swing.GroupLayout jPanel_HeaderLayout = new javax.swing.GroupLayout(jPanel_Header);
        jPanel_Header.setLayout(jPanel_HeaderLayout);
        jPanel_HeaderLayout.setHorizontalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addComponent(jLabel_Title)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        jPanel_HeaderLayout.setVerticalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel_Title)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel_Name.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Name.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Name.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Name.setText("NOMBRE:");

        jLabel_Id.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Id.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Id.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Id.setText("CEDULA:");

        jCheckBox_Org.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Org.setFont(new java.awt.Font("Gill Sans MT", 1, 10)); // NOI18N
        jCheckBox_Org.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox_Org.setText("ORGANIZACION");
        jCheckBox_Org.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox_OrgMouseClicked(evt);
            }
        });

        jLabel_Addres.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Addres.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Addres.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Addres.setText("DIRECCION:");

        jLabel_City.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_City.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_City.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_City.setText("CIUDAD:");

        jLabel_Phone1.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Phone1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Phone1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Phone1.setText("TELEFONO:");

        jLabel_Phone2.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Phone2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Phone2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Phone2.setText("TELEFONO:");

        jLabel_Status.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Status.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Status.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Status.setText("ESTADO:");

        jLabel_ContactName.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_ContactName.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ContactName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_ContactName.setText("CONTACTO:");

        jLabel_ContactCharge.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_ContactCharge.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ContactCharge.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_ContactCharge.setText("CARGO:");

        jComboBox_Status.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jComboBox_Status.setForeground(new java.awt.Color(0, 0, 0));

        jButton_Accept.setBackground(new java.awt.Color(153, 0, 0));
        jButton_Accept.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        jButton_Accept.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Accept.setText("ACEPTAR");

        jTextField_Id.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_City.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Name.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Addres.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_ContactName.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_ContactCharge.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Phone2.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Phone1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel_BGLayout = new javax.swing.GroupLayout(jPanel_BG);
        jPanel_BG.setLayout(jPanel_BGLayout);
        jPanel_BGLayout.setHorizontalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel_Addres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_City, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jLabel_ContactName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_ContactCharge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Addres)
                    .addComponent(jTextField_City)
                    .addComponent(jTextField_ContactName)
                    .addComponent(jTextField_ContactCharge)
                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                        .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addComponent(jTextField_Id)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addComponent(jTextField_Name)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_BGLayout.createSequentialGroup()
                                .addComponent(jCheckBox_Org)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Phone2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_Status, 0, 120, Short.MAX_VALUE)
                            .addComponent(jTextField_Phone1)
                            .addComponent(jTextField_Phone2))))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_BGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Accept, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_BGLayout.setVerticalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addComponent(jPanel_Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Name)
                    .addComponent(jLabel_Phone1)
                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_Org)
                    .addComponent(jLabel_Phone2)
                    .addComponent(jTextField_Phone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Id)
                    .addComponent(jLabel_Status)
                    .addComponent(jComboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_City)
                    .addComponent(jTextField_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Addres)
                    .addComponent(jTextField_Addres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ContactName)
                    .addComponent(jTextField_ContactName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ContactCharge)
                    .addComponent(jTextField_ContactCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_Accept, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void jCheckBox_OrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox_OrgMouseClicked
        updateTextFields();
    }//GEN-LAST:event_jCheckBox_OrgMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Accept;
    public javax.swing.JCheckBox jCheckBox_Org;
    public javax.swing.JComboBox<String> jComboBox_Status;
    private javax.swing.JLabel jLabel_Addres;
    private javax.swing.JLabel jLabel_City;
    private javax.swing.JLabel jLabel_ContactCharge;
    private javax.swing.JLabel jLabel_ContactName;
    private javax.swing.JLabel jLabel_Id;
    private javax.swing.JLabel jLabel_Name;
    private javax.swing.JLabel jLabel_Phone1;
    private javax.swing.JLabel jLabel_Phone2;
    private javax.swing.JLabel jLabel_Status;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel_BG;
    private javax.swing.JPanel jPanel_Header;
    public javax.swing.JTextField jTextField_Addres;
    public javax.swing.JTextField jTextField_City;
    public javax.swing.JTextField jTextField_ContactCharge;
    public javax.swing.JTextField jTextField_ContactName;
    public javax.swing.JTextField jTextField_Id;
    public javax.swing.JTextField jTextField_Name;
    public javax.swing.JTextField jTextField_Phone1;
    public javax.swing.JTextField jTextField_Phone2;
    // End of variables declaration//GEN-END:variables
}

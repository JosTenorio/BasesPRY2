
package View;

import javax.swing.DefaultComboBoxModel;

public class OrderInformationDisplay extends javax.swing.JFrame {
    
    public DefaultComboBoxModel comboBoxModelClient;

    public OrderInformationDisplay() {
        initComponents();
        this.comboBoxModelClient = (DefaultComboBoxModel) jComboBox_Client.getModel();
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
        jLabel_Client = new javax.swing.JLabel();
        jLabel_Day = new javax.swing.JLabel();
        jButton_Accept = new javax.swing.JButton();
        jComboBox_Client = new javax.swing.JComboBox<>();
        jTextField_Day = new javax.swing.JTextField();
        jTextField_Month = new javax.swing.JTextField();
        jTextField_Year = new javax.swing.JTextField();
        jLabel_Month = new javax.swing.JLabel();
        jLabel_Year = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_BG.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_Header.setBackground(new java.awt.Color(153, 0, 0));

        jLabel_Title.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Title.setText("RELACION PARTE AUTOMOVIL");

        javax.swing.GroupLayout jPanel_HeaderLayout = new javax.swing.GroupLayout(jPanel_Header);
        jPanel_Header.setLayout(jPanel_HeaderLayout);
        jPanel_HeaderLayout.setHorizontalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel_Title)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel_HeaderLayout.setVerticalGroup(
            jPanel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel_Title)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel_Client.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Client.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Client.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Client.setText("CLIENTE:");

        jLabel_Day.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Day.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Day.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Day.setText("DIA:");

        jButton_Accept.setBackground(new java.awt.Color(153, 0, 0));
        jButton_Accept.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        jButton_Accept.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Accept.setText("ACEPTAR");

        jComboBox_Client.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jComboBox_Client.setForeground(new java.awt.Color(0, 0, 0));

        jTextField_Day.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Month.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jTextField_Year.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jLabel_Month.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Month.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Month.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Month.setText("MES:");

        jLabel_Year.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel_Year.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Year.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel_Year.setText("AÑO:");

        javax.swing.GroupLayout jPanel_BGLayout = new javax.swing.GroupLayout(jPanel_BG);
        jPanel_BG.setLayout(jPanel_BGLayout);
        jPanel_BGLayout.setHorizontalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_BGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Accept, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                        .addComponent(jLabel_Client)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Client, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_BGLayout.createSequentialGroup()
                        .addComponent(jLabel_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Month, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Month, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_BGLayout.setVerticalGroup(
            jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BGLayout.createSequentialGroup()
                .addComponent(jPanel_Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Client)
                    .addComponent(jComboBox_Client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Day)
                    .addGroup(jPanel_BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Month)
                        .addComponent(jLabel_Year)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jButton_Accept, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Accept;
    public javax.swing.JComboBox<String> jComboBox_Client;
    private javax.swing.JLabel jLabel_Client;
    private javax.swing.JLabel jLabel_Day;
    private javax.swing.JLabel jLabel_Month;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_Year;
    private javax.swing.JPanel jPanel_BG;
    private javax.swing.JPanel jPanel_Header;
    public javax.swing.JTextField jTextField_Day;
    public javax.swing.JTextField jTextField_Month;
    public javax.swing.JTextField jTextField_Year;
    // End of variables declaration//GEN-END:variables
}

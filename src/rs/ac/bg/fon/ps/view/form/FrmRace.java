/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Matija
 */
public class FrmRace extends javax.swing.JDialog {

    /**
     * Creates new form FrmRace
     */
    public FrmRace(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTrack = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtRace = new javax.swing.JTextField();
        txtTotalLaps = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRaceItem = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbRider = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtStartingPosition = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbTeam = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtRacingNumber = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnSaveRace = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblCU = new javax.swing.JLabel();
        btnEnableChanges = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");

        jLabel2.setText("Track:");

        jLabel3.setText("Date:");

        jLabel4.setText("Race name:");

        txtID.setEditable(false);

        jLabel5.setText("Total laps:");

        tblRaceItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblRaceItem);

        btnRemove.setText("Remove");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rider"));

        jLabel6.setText("Rider:");

        cbRider.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Starting position:");

        jLabel8.setText("Racing team:");

        cbTeam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdd.setText("Add");

        jLabel10.setText("Racing number:");

        txtRacingNumber.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbRider, 0, 194, Short.MAX_VALUE)
                    .addComponent(txtStartingPosition)
                    .addComponent(cbTeam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRacingNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbRider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtStartingPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtRacingNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnDelete.setText("Delete");

        btnSaveRace.setText("Save");
        btnSaveRace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRaceActionPerformed(evt);
            }
        });

        jLabel9.setText("Current user:");

        btnEnableChanges.setText("Enable changes");

        btnCancel.setText("Cancel");

        btnEdit.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalLaps, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(txtRace)
                            .addComponent(txtDate)
                            .addComponent(txtTrack)
                            .addComponent(txtID))
                        .addGap(124, 124, 124)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lblCU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(91, 91, 91))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(177, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel)
                                .addGap(18, 18, 18)
                                .addComponent(btnEnableChanges)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemove)
                            .addComponent(btnSaveRace, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(lblCU))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTrack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTotalLaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnSaveRace)
                            .addComponent(btnEnableChanges)
                            .addComponent(btnCancel)
                            .addComponent(btnEdit))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveRaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveRaceActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEnableChanges;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSaveRace;
    private javax.swing.JComboBox<String> cbRider;
    private javax.swing.JComboBox<String> cbTeam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCU;
    private javax.swing.JTable tblRaceItem;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtRace;
    private javax.swing.JTextField txtRacingNumber;
    private javax.swing.JTextField txtStartingPosition;
    private javax.swing.JTextField txtTotalLaps;
    private javax.swing.JTextField txtTrack;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(javax.swing.JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public javax.swing.JButton getBtnRemove() {
        return btnRemove;
    }

    public JButton getBtnEnableChanges() {
        return btnEnableChanges;
    }

    public JButton getBtnSaveRace() {
        return btnSaveRace;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnRemove(javax.swing.JButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    public javax.swing.JComboBox<String> getCbRider() {
        return cbRider;
    }

    public void setCbRider(javax.swing.JComboBox<String> cbRider) {
        this.cbRider = cbRider;
    }

    public javax.swing.JComboBox<String> getCbTeam() {
        return cbTeam;
    }

    public void setCbTeam(javax.swing.JComboBox<String> cbTeam) {
        this.cbTeam = cbTeam;
    }

    public javax.swing.JButton getjButton3() {
        return btnDelete;
    }

    public void setjButton3(javax.swing.JButton jButton3) {
        this.btnDelete = jButton3;
    }

    public javax.swing.JButton getjButton4() {
        return btnSaveRace;
    }

    public void setjButton4(javax.swing.JButton jButton4) {
        this.btnSaveRace = jButton4;
    }

    public javax.swing.JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(javax.swing.JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public javax.swing.JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(javax.swing.JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public javax.swing.JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(javax.swing.JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public javax.swing.JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(javax.swing.JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public javax.swing.JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(javax.swing.JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public javax.swing.JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(javax.swing.JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    public javax.swing.JLabel getjLabel7() {
        return jLabel7;
    }

    public void setjLabel7(javax.swing.JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }

    public javax.swing.JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(javax.swing.JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    public javax.swing.JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(javax.swing.JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }

    public javax.swing.JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(javax.swing.JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public javax.swing.JLabel getLblCU() {
        return lblCU;
    }

    public void setLblCU(javax.swing.JLabel lblCU) {
        this.lblCU = lblCU;
    }

    public javax.swing.JTable getTblRaceItem() {
        return tblRaceItem;
    }

    public void setTblRaceItem(javax.swing.JTable tblRaceItem) {
        this.tblRaceItem = tblRaceItem;
    }

    public javax.swing.JTextField getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(javax.swing.JTextField txtDate) {
        this.txtDate = txtDate;
    }

    public javax.swing.JTextField getTxtID() {
        return txtID;
    }

    public void setTxtID(javax.swing.JTextField txtID) {
        this.txtID = txtID;
    }

    public javax.swing.JTextField getTxtRace() {
        return txtRace;
    }

    public void setTxtRace(javax.swing.JTextField txtRace) {
        this.txtRace = txtRace;
    }

    public javax.swing.JTextField getTxtStartingPosition() {
        return txtStartingPosition;
    }

    public void setTxtStartingPosition(javax.swing.JTextField txtStartingPosition) {
        this.txtStartingPosition = txtStartingPosition;
    }

    public javax.swing.JTextField getTxtTotalLaps() {
        return txtTotalLaps;
    }

    public void setTxtTotalLaps(javax.swing.JTextField txtTotalLaps) {
        this.txtTotalLaps = txtTotalLaps;
    }

    public javax.swing.JTextField getTxtTrack() {
        return txtTrack;
    }

    public void setTxtTrack(javax.swing.JTextField txtTrack) {
        this.txtTrack = txtTrack;
    }

    public JTextField getTxtRacingNumber() {
        return txtRacingNumber;
    }

    public void addBtnAddRiderActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
    }

    public void addBtnRemoveRiderActionListener(ActionListener actionListener) {
        btnRemove.addActionListener(actionListener);
    }

    public void addBtnSaveRaceActionListener(ActionListener actionListener) {
        btnSaveRace.addActionListener(actionListener);
    }
    public void addEditBtnActionListener(ActionListener actionListener) {
        btnEdit.addActionListener(actionListener);
    }
    public void addEnableChangesBtnActionListener(ActionListener actionListener) {
         btnEnableChanges.addActionListener(actionListener);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.JLabel;

/**
 *
 * @author Matija
 */
public class FrmTeam extends javax.swing.JDialog {

    /**
     * Creates new form FrmTeam
     */
    public FrmTeam(java.awt.Frame parent, boolean modal) {
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEnableChanges = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtSponsor = new javax.swing.JTextField();
        txtBudget = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtManager = new javax.swing.JTextField();
        txtHQ = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblCU = new javax.swing.JLabel();
        lblIDError = new javax.swing.JLabel();
        lblSponsorError = new javax.swing.JLabel();
        lblBudgetError = new javax.swing.JLabel();
        lblNameError = new javax.swing.JLabel();
        lblManagerError = new javax.swing.JLabel();
        lblHeadquartersError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TrackData v1 - New Team");

        jLabel1.setText("ID:");

        jLabel2.setText("Sponsor:");

        jLabel3.setText("Budget:");

        jLabel4.setText("Team name:");

        jLabel5.setText("General manager:");

        jLabel6.setText("Headquarters:");

        btnSave.setText("Save");

        btnDelete.setText("Delete");

        btnEnableChanges.setText("Enable changes");

        btnCancel.setText("Cancel");

        btnEdit.setText("Edit");

        txtBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBudgetActionPerformed(evt);
            }
        });

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Current user:");

        lblCU.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCU.setForeground(new java.awt.Color(51, 51, 255));

        lblSponsorError.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblSponsorError.setForeground(new java.awt.Color(255, 0, 0));

        lblBudgetError.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblBudgetError.setForeground(new java.awt.Color(255, 0, 0));

        lblNameError.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblNameError.setForeground(new java.awt.Color(255, 0, 0));

        lblManagerError.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblManagerError.setForeground(new java.awt.Color(255, 0, 0));

        lblHeadquartersError.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblHeadquartersError.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBudgetError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSponsorError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIDError, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtSponsor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtBudget, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblManagerError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtManager)
                            .addComponent(txtHQ, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblHeadquartersError, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblCU, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(571, 571, 571))))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnEdit)
                .addGap(40, 40, 40)
                .addComponent(btnCancel)
                .addGap(40, 40, 40)
                .addComponent(btnEnableChanges)
                .addGap(40, 40, 40)
                .addComponent(btnDelete)
                .addGap(40, 40, 40)
                .addComponent(btnSave)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(txtManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSponsor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSponsorError, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblManagerError, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHeadquartersError, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
                    .addComponent(lblBudgetError, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnEnableChanges)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBudgetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBudgetActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEnableChanges;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblBudgetError;
    private javax.swing.JLabel lblCU;
    private javax.swing.JLabel lblHeadquartersError;
    private javax.swing.JLabel lblIDError;
    private javax.swing.JLabel lblManagerError;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblSponsorError;
    private javax.swing.JTextField txtBudget;
    private javax.swing.JTextField txtHQ;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSponsor;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnCancel() {
        return btnCancel;
    }

    public javax.swing.JButton getBtnDelete() {
        return btnDelete;
    }

    public javax.swing.JButton getBtnEdit() {
        return btnEdit;
    }

    public javax.swing.JButton getBtnEnableChanges() {
        return btnEnableChanges;
    }

    public javax.swing.JButton getBtnSave() {
        return btnSave;
    }

    public javax.swing.JLabel getjLabel1() {
        return jLabel1;
    }

    public javax.swing.JLabel getjLabel2() {
        return jLabel2;
    }

    public javax.swing.JLabel getjLabel3() {
        return jLabel3;
    }

    public javax.swing.JLabel getjLabel4() {
        return jLabel4;
    }

    public javax.swing.JLabel getjLabel5() {
        return jLabel5;
    }

    public javax.swing.JLabel getjLabel6() {
        return jLabel6;
    }

    public javax.swing.JTextField getTxtBudget() {
        return txtBudget;
    }

    public javax.swing.JTextField getTxtHQ() {
        return txtHQ;
    }

    public javax.swing.JTextField getTxtID() {
        return txtID;
    }

    public javax.swing.JTextField getTxtManager() {
        return txtManager;
    }

    public javax.swing.JTextField getTxtName() {
        return txtName;
    }

    public void setLblCU(JLabel lblCU) {
        this.lblCU = lblCU;
    }

    public JLabel getLblCU() {
        return lblCU;
    }

    public javax.swing.JTextField getTxtSponsor() {
        return txtSponsor;
    }

    public void addSaveBtnActionListener(ActionListener actionListener) {
         btnSave.addActionListener(actionListener);
    }

    public void addEnableChangesBtnActionListener(ActionListener actionListener) {
         btnEnableChanges.addActionListener(actionListener);
    }

    public void addCancelBtnActionListener(ActionListener actionListener) {
        btnCancel.addActionListener(actionListener);
    }

    public void addDeleteBtnActionListener(ActionListener actionListener) {
        btnDelete.addActionListener(actionListener);
    }

    public void addEditBtnActionListener(ActionListener actionListener) {
        btnEdit.addActionListener(actionListener);
    }

    public JLabel getLblSponsorError() {
        return lblSponsorError;
    }

    public JLabel getLblBudgetError() {
        return lblBudgetError;
    }

    public JLabel getLblIDError() {
        return lblIDError;
    }

    public JLabel getLblNameError() {
        return lblNameError;
    }

    public JLabel getLblHeadquartersError() {
        return lblHeadquartersError;
    }

    public JLabel getLblManagerError() {
        return lblManagerError;
    }
    public void getTxtBudgetAddFocus(FocusListener focusListener) {
        txtBudget.addFocusListener(focusListener);
    }
}

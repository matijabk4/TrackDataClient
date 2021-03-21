/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import rs.ac.bg.fon.ps.domain.MotorcycleMake;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.view.form.component.table.RiderTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Matija
 */
public class FrmViewRiders extends javax.swing.JDialog {

    /**
     * Creates new form FrmViewRiders
     */
    public FrmViewRiders(java.awt.Frame parent, boolean modal) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRiders = new javax.swing.JTable();
        btnDetails = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblCurrentUser = new javax.swing.JLabel();
        lblCU = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TrackData v1 - View All Riders");

        tblRiders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Firstname", "Surname", "Nationality", "Motorcycle make", "Racing No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRiders);

        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblCurrentUser.setText("Current user:");

        lblCU.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCU.setForeground(new java.awt.Color(51, 51, 255));

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCurrentUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCU, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCurrentUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCU))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDetails)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        /* int row = tblRiders.getSelectedRow();
        int empty = tblRiders.getRowCount();
        if (empty == 0) {
            JOptionPane.showMessageDialog(this, "No riders to display!", "TrackData 1.0", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (row >= 0) {
            Rider r = ((RiderTableModel) tblRiders.getModel()).getRiderAt(row);
            new FrmRider(null, true, FormMode.FORM_VIEW, r).setVisible(true);
            btnRefresh.setVisible(true);
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
            btnDetails.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a rider.", "TrackData 1.0", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //prepareView();
        /*RiderTableModel rtm = (RiderTableModel) tblRiders.getModel();
        rtm.addRider(new Rider());
        btnAdd.setEnabled(false);
        btnDetails.setEnabled(false);
        btnDelete.setEnabled(false);
        btnValidate.setVisible(true);
        btnValidate.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Please fill out all the fields. Cick 'Validate' once you are finished. ", "TrackData 1.0", JOptionPane.INFORMATION_MESSAGE);*/
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        RiderTableModel table = (RiderTableModel) tblRiders.getModel();
        String search = txtSearch.getText().trim();

        //      String pretrazi2 = txtPretrazi.getText().toUpperCase(); // za registraciju
        TableRowSorter<RiderTableModel> tr = new TableRowSorter<>(table);
        tblRiders.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(search));
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));

        //       tr.setRowFilter(RowFilter.regexFilter(pretrazi2));

    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
    }//GEN-LAST:event_txtSearchFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCU;
    private javax.swing.JLabel lblCurrentUser;
    private javax.swing.JTable tblRiders;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    public void getBtnDetailsAddActionListener(ActionListener actionListener) {
        btnDetails.addActionListener(actionListener);
    }

    public javax.swing.JButton getBtnAdd() {
        return btnAdd;
    }

    public javax.swing.JButton getBtnDetails() {
        return btnDetails;
    }

    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public javax.swing.JLabel getLblCU() {
        return lblCU;
    }

    public javax.swing.JLabel getLblCurrentUser() {
        return lblCurrentUser;
    }

    public javax.swing.JTable getTblRiders() {
        return tblRiders;
    }

    public void setTblRiders(JTable tblRiders) {
        this.tblRiders = tblRiders;
    }

    public void getBtnAddAddActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
    }

    public void getTxtSearchAddFocus(FocusListener focusListener) {
        txtSearch.addFocusListener(focusListener);
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }
}

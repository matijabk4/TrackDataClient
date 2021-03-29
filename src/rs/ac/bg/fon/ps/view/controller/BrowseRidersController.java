/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmBrowseRiders;
import rs.ac.bg.fon.ps.view.form.FrmViewRiders;
import rs.ac.bg.fon.ps.view.form.component.table.RiderTableModel;

/**
 *
 * @author laptop-02
 */
public class BrowseRidersController {
    
    private final FrmBrowseRiders frmViewRiders;
    
    public BrowseRidersController(FrmBrowseRiders frmViewRiders) {
        this.frmViewRiders = frmViewRiders;
        addActionListener();
        addFocusListener();
    }
    
    private void addFocusListener() {
        frmViewRiders.getTxtSearchAddFocus(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (frmViewRiders.getTxtSearch().getText().equals("Search...")) {
                    frmViewRiders.getTxtSearch().setForeground(Color.black);
                    frmViewRiders.getTxtSearch().setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (frmViewRiders.getTxtSearch().getText().isEmpty()) {
                    //frmViewRiders.getTxtSearch().setText("Search...");
                    frmViewRiders.getTxtSearch().setForeground(Color.gray);
                    
                } else {
                    frmViewRiders.getTxtSearch().setForeground(Color.black);
                }
            }
        });
    }
    
    private void addActionListener() {
        
        frmViewRiders.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblRiders();
                centerTableValues();
                searchFieldSettings();
            }
            
        });
        
    }
    
    public void openForm() {
        frmViewRiders.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView();
        frmViewRiders.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmViewRiders.setVisible(true);
        
    }
    
    private void prepareView() {
        frmViewRiders.getTblRiders().getTableHeader().setResizingAllowed(false);
        frmViewRiders.getTblRiders().getTableHeader().setReorderingAllowed(false);
        frmViewRiders.setResizable(false);
        //fillTblRiders();
    }
    
    private void fillTblRiders() {
        List<Rider> riders;
        try {
            riders = Communication.getInstance().getAllRiders();
            RiderTableModel ptm = new RiderTableModel(riders);
            frmViewRiders.getTblRiders().setModel(ptm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewRiders, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RiderViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void centerTableValues() {

        // center column names
        ((DefaultTableCellRenderer) frmViewRiders.getTblRiders().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // center column values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int col = 0; col < frmViewRiders.getTblRiders().getColumnCount(); col++) {
            frmViewRiders.getTblRiders().getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // frmViewRiders.getTblRiders().setAutoCreateRowSorter(true);
    }
    
    private void searchFieldSettings() {
        
        if (!frmViewRiders.getTxtSearch().getText().equals("Search...")) {
            frmViewRiders.getTxtSearch().setText("Search...");
            frmViewRiders.getTxtSearch().setForeground(Color.gray);
            
        }
        
    }
    
    public void refresh() {
        fillTblRiders();
    }
    
}

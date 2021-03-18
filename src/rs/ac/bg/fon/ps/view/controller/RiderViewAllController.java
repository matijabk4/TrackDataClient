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
import rs.ac.bg.fon.ps.view.form.FrmViewRiders;
import rs.ac.bg.fon.ps.view.form.component.table.RiderTableModel;

/**
 *
 * @author laptop-02
 */
public class RiderViewAllController {

    private final FrmViewRiders frmViewRiders;

    public RiderViewAllController(FrmViewRiders frmViewRiders) {
        this.frmViewRiders = frmViewRiders;
        addActionListener();
        addFocusListener();
    }
private void addFocusListener(){
    frmViewRiders.getTxtPretraziAddFocus(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (frmViewRiders.getTxtPretrazi().getText().equals("Pretrazi po bilo kom kriterijumu...")) {
            frmViewRiders.getTxtPretrazi().setForeground(Color.black);
            frmViewRiders.getTxtPretrazi().setText("");
        }
        }

        @Override
        public void focusLost(FocusEvent e) {
             if (frmViewRiders.getTxtPretrazi().getText().isEmpty()) {
            frmViewRiders.getTxtPretrazi().setText("Pretrazi po bilo kom kriterijumu...");
            frmViewRiders.getTxtPretrazi().setForeground(Color.gray);

        } else {
            frmViewRiders.getTxtPretrazi().setForeground(Color.black);
        }
        }
    });
}
    private void addActionListener() {
        frmViewRiders.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewRiders.getTblRiders().getSelectedRow();
                if (row >= 0) {
                    Rider r = ((RiderTableModel) frmViewRiders.getTblRiders().getModel()).getRiderAt(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_RIDER, r);
                    MainCordinator.getInstance().openRiderDetailsRiderForm();

                } else {
                    JOptionPane.showMessageDialog(frmViewRiders, "You must select a rider", "RIDER DETAILS", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmViewRiders.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblRiders();
                centrirajSveVrednostiUTabeli();
                obradiPoljeKojePretrazuje();
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
            }

        });
        frmViewRiders.getBtnAddAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              MainCordinator.getInstance().openRiderAddRiderForm();
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
        frmViewRiders.setTitle("View riders");
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
private void centrirajSveVrednostiUTabeli() {

        // centriraj nazive kolona
        ((DefaultTableCellRenderer) frmViewRiders.getTblRiders().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // centriraj vrednosti u tabeli
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int col = 0; col < frmViewRiders.getTblRiders().getColumnCount(); col++) {
            frmViewRiders.getTblRiders().getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // omoguci sortiranje za sve kolone (default sortiranje u koje ne mesam svoje prste)
        //tblVozila.setAutoCreateRowSorter(true);
    }
private void obradiPoljeKojePretrazuje() {

        if (!frmViewRiders.getTxtPretrazi().getText().equals("Pretrazi po bilo kom kriterijumu...")) {
            frmViewRiders.getTxtPretrazi().setText("Pretrazi po bilo kom kriterijumu...");
            frmViewRiders.getTxtPretrazi().setForeground(Color.gray);

        }

    }
    public void refresh() {
        fillTblRiders();
    }

}

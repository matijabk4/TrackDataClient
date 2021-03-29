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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmBrowseTeams;
import rs.ac.bg.fon.ps.view.form.FrmViewTeams;
import rs.ac.bg.fon.ps.view.form.component.table.TeamTableModel;

/**
 *
 * @author laptop-02
 */
public class BrowseTeamsController {

    private final FrmBrowseTeams frmViewTeams;

    public BrowseTeamsController(FrmBrowseTeams frmViewTeams) {
        this.frmViewTeams = frmViewTeams;
        addActionListener();
        addFocusListener();
    }

    private void addFocusListener() {
        frmViewTeams.getTxtSearchAddFocus(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (frmViewTeams.getTxtSearch().getText().equals("Search...")) {
                    frmViewTeams.getTxtSearch().setForeground(Color.black);
                    frmViewTeams.getTxtSearch().setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (frmViewTeams.getTxtSearch().getText().isEmpty()) {
                    frmViewTeams.getTxtSearch().setText("Search...");
                    frmViewTeams.getTxtSearch().setForeground(Color.gray);

                } else {
                    frmViewTeams.getTxtSearch().setForeground(Color.black);
                }
            }
        });
    }

    private void addActionListener() {
       
        frmViewTeams.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblTeams();
                centerTableValues();
                searchFieldSettings();
            }

        });
        /*frmViewTeams.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblTeams();
            }

        });*/
       
    }

    public void openForm() {
        frmViewTeams.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView();
        frmViewTeams.getLblCu().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmViewTeams.setVisible(true);

    }

    private void prepareView() {
        frmViewTeams.getTblTeams().getTableHeader().setResizingAllowed(false);
        frmViewTeams.getTblTeams().getTableHeader().setReorderingAllowed(false);
        frmViewTeams.setResizable(false);
        //fillTblRiders();
    }

    private void fillTblTeams() {
        List<RacingTeam> teams;
        try {
            teams = Communication.getInstance().getAllTeams();
            TeamTableModel ptm = new TeamTableModel(teams);
            frmViewTeams.getTblTeams().setModel(ptm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewTeams, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RiderViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        fillTblTeams();
    }

    private void centerTableValues() {
        // center column names
        ((DefaultTableCellRenderer) frmViewTeams.getTblTeams().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // center column values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int col = 0; col < frmViewTeams.getTblTeams().getColumnCount(); col++) {
            frmViewTeams.getTblTeams().getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        //frmViewTeams.getTblTeams().setAutoCreateRowSorter(true);
    }

    private void searchFieldSettings() {

        if (!frmViewTeams.getTxtSearch().getText().equals("Search...")) {
            frmViewTeams.getTxtSearch().setText("Search...");
            frmViewTeams.getTxtSearch().setForeground(Color.gray);

        }

    }
}

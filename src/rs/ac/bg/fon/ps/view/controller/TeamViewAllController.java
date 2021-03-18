/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmViewTeams;
import rs.ac.bg.fon.ps.view.form.component.table.TeamTableModel;

/**
 *
 * @author laptop-02
 */
public class TeamViewAllController {

    private final FrmViewTeams frmViewTeams;

    public TeamViewAllController(FrmViewTeams frmViewTeams) {
        this.frmViewTeams = frmViewTeams;
        addActionListener();
    }

    private void addActionListener() {
        frmViewTeams.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewTeams.getTblTeams().getSelectedRow();
                if (row >= 0) {
                    RacingTeam t = ((TeamTableModel) frmViewTeams.getTblTeams().getModel()).getTeamAt(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_TEAM, t);
                    MainCordinator.getInstance().openTeamDetailsTeamForm();

                } else {
                    JOptionPane.showMessageDialog(frmViewTeams, "You must select a team", "TEAM DETAILS", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmViewTeams.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblTeams();
            }

        });
        frmViewTeams.getBtnAddAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openTeamAddTeamForm();
            }
        });
    }

    public void openForm() {
        frmViewTeams.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView();
        frmViewTeams.getLblCu().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmViewTeams.setVisible(true);
    }

    private void prepareView() {
        frmViewTeams.setTitle("View riders");
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

}

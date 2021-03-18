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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author laptop-02
 */
public class MainController {

    private final FrmMain frmMain;
    static MainController instance;

    public MainController() {
        this.frmMain = null;
    }
    
    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public static MainController getInstance() {
        if(instance==null){
            instance=new MainController();
        }
        return instance;
    }

    public void openForm() {

        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        frmMain.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiRiderNewAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRiderNewActionPerformed(evt);
            }

            private void jmiRiderNewActionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openAddNewRiderForm();
            }
        });
        frmMain.jmiRiderShowAllActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRiderShowAllActionPerformed(evt);
            }

            private void jmiRiderShowAllActionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openViewAllRidersForm();
            }
        });
        frmMain.jmiTeamNewAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTeamNewActionPerformed(evt);
            }

            private void jmiTeamNewActionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openAddNewTeamForm();
            }
        });
        frmMain.jmiTeamShowAllActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTeamShowAllActionPerformed(evt);
            }

            private void jmiTeamShowAllActionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openViewAllTeamsForm();
            }
        });
        frmMain.getjMenuItemNewRace().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openAddNewRaceForm();
            }
        });
        frmMain.getjMenuItemRaceShowAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllRacesForm();
            }
        });

        frmMain.getjMenuItemLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "TrackData logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        Communication.getInstance().logout((User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER));
                        frmMain.dispose();
                        MainCordinator.getInstance().openLoginForm();

                    }

                } catch (Exception ex) {
                    Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmMain.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                try {
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "TrackData exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        Communication.getInstance().logout((User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER));
                        System.exit(0);

                    }
                } catch (Exception ex) {
                    Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    public void shutdown() {
        JOptionPane.showMessageDialog(null, "Server shut down. Goodbye!");
        System.exit(0);
    }
}

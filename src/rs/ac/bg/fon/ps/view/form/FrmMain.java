/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Matija
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        setLocationRelativeTo(null);
        jMenuBarMain.add(Box.createHorizontalGlue());
        jMenuBarMain.add(jMenuLogout);
        jMenuItemLogout.setIcon(new ImageIcon("images/Logout.jpg"));
        lblPicture.setIcon(new ImageIcon("images/main background.jpg"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCurrentUser = new javax.swing.JLabel();
        lblCU = new javax.swing.JLabel();
        lblPicture = new javax.swing.JLabel();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuRider = new javax.swing.JMenu();
        jMenuItemNewRider = new javax.swing.JMenuItem();
        jMenuItemRiderShowAll = new javax.swing.JMenuItem();
        jMenuTeam = new javax.swing.JMenu();
        jMenuItemNewTeam = new javax.swing.JMenuItem();
        jMenuItemTeamShowAll = new javax.swing.JMenuItem();
        jMenuRace = new javax.swing.JMenu();
        jMenuItemNewRace = new javax.swing.JMenuItem();
        jMenuItemRaceShowAll = new javax.swing.JMenuItem();
        jMenuLogout = new javax.swing.JMenu();
        jMenuItemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TrackData v1");
        setName(""); // NOI18N

        jPanel1.setLayout(null);

        lblCurrentUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCurrentUser.setForeground(new java.awt.Color(51, 51, 255));
        lblCurrentUser.setText("Current user:");
        jPanel1.add(lblCurrentUser);
        lblCurrentUser.setBounds(10, 410, 120, 20);

        lblCU.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCU.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(lblCU);
        lblCU.setBounds(140, 400, 150, 40);
        jPanel1.add(lblPicture);
        lblPicture.setBounds(0, 0, 850, 460);

        jMenuRider.setText("Rider");

        jMenuItemNewRider.setText("Add new rider");
        jMenuItemNewRider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewRiderActionPerformed(evt);
            }
        });
        jMenuRider.add(jMenuItemNewRider);

        jMenuItemRiderShowAll.setText("Show all riders");
        jMenuItemRiderShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRiderShowAllActionPerformed(evt);
            }
        });
        jMenuRider.add(jMenuItemRiderShowAll);

        jMenuBarMain.add(jMenuRider);

        jMenuTeam.setText("Team");

        jMenuItemNewTeam.setText("Add new team");
        jMenuItemNewTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewTeamActionPerformed(evt);
            }
        });
        jMenuTeam.add(jMenuItemNewTeam);

        jMenuItemTeamShowAll.setText("Show all teams");
        jMenuItemTeamShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTeamShowAllActionPerformed(evt);
            }
        });
        jMenuTeam.add(jMenuItemTeamShowAll);

        jMenuBarMain.add(jMenuTeam);

        jMenuRace.setText("Race");

        jMenuItemNewRace.setText("Add new race");
        jMenuRace.add(jMenuItemNewRace);

        jMenuItemRaceShowAll.setText("Show all races");
        jMenuItemRaceShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRaceShowAllActionPerformed(evt);
            }
        });
        jMenuRace.add(jMenuItemRaceShowAll);

        jMenuBarMain.add(jMenuRace);

        jMenuLogout.setText("Logout");

        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenuLogout.add(jMenuItemLogout);

        jMenuBarMain.add(jMenuLogout);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNewRiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewRiderActionPerformed
        // new FrmRider(this, true, FormMode.FORM_ADD).setVisible(true);
    }//GEN-LAST:event_jMenuItemNewRiderActionPerformed

    private void jMenuItemRiderShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRiderShowAllActionPerformed
        //new FrmViewRiders(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemRiderShowAllActionPerformed

    private void jMenuItemNewTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewTeamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemNewTeamActionPerformed

    private void jMenuItemTeamShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTeamShowAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemTeamShowAllActionPerformed

    private void jMenuItemRaceShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRaceShowAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemRaceShowAllActionPerformed

    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed
        /*try {
            if(JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?","TrackData logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            
            Communication.getInstance().logout((User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER));
            this.dispose();
            MainCordinator.getInstance().openLoginForm();
            
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jMenuItemLogoutActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenuItem jMenuItemLogout;
    private javax.swing.JMenuItem jMenuItemNewRace;
    private javax.swing.JMenuItem jMenuItemNewRider;
    private javax.swing.JMenuItem jMenuItemNewTeam;
    private javax.swing.JMenuItem jMenuItemRaceShowAll;
    private javax.swing.JMenuItem jMenuItemRiderShowAll;
    private javax.swing.JMenuItem jMenuItemTeamShowAll;
    private javax.swing.JMenu jMenuLogout;
    private javax.swing.JMenu jMenuRace;
    private javax.swing.JMenu jMenuRider;
    private javax.swing.JMenu jMenuTeam;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCU;
    private javax.swing.JLabel lblCurrentUser;
    private javax.swing.JLabel lblPicture;
    // End of variables declaration//GEN-END:variables

    public void jmiRiderNewAddActionListener(ActionListener actionListener) {
        jMenuItemNewRider.addActionListener(actionListener);
    }

    public void jmiRiderShowAllActionListener(ActionListener actionListener) {
        jMenuItemRiderShowAll.addActionListener(actionListener);
    }

    public javax.swing.JMenu getjMenu2() {
        return jMenuTeam;
    }

    public javax.swing.JMenuBar getjMenuBarMain() {
        return jMenuBarMain;
    }

    public javax.swing.JMenuItem getjMenuItemNewRider() {
        return jMenuItemNewRider;
    }

    public javax.swing.JMenuItem getjMenuItemRiderShowAll() {
        return jMenuItemRiderShowAll;
    }

    public javax.swing.JMenu getjMenuRider() {
        return jMenuRider;
    }

    public javax.swing.JLabel getLblCU() {
        return lblCU;
    }

    public javax.swing.JLabel getLblCurrentUser() {
        return lblCurrentUser;
    }

    public JMenuItem getjMenuItemNewRace() {
        return jMenuItemNewRace;
    }

    public JMenuItem getjMenuItemTeamShowAll() {
        return jMenuItemTeamShowAll;
    }

    public JMenuItem getjMenuItemNewTeam() {
        return jMenuItemNewTeam;
    }

    public JMenuItem getjMenuItemRaceShowAll() {
        return jMenuItemRaceShowAll;
    }

    public void jmiTeamNewAddActionListener(ActionListener actionListener) {
        jMenuItemNewTeam.addActionListener(actionListener);
    }

    public void jmiTeamShowAllActionListener(ActionListener actionListener) {
        jMenuItemTeamShowAll.addActionListener(actionListener);
    }

    public void jmiRaceNewActionListener(ActionListener actionListener) {
        jMenuItemNewRace.addActionListener(actionListener);
    }

    public void jmiRaceShowAllActionListener(ActionListener actionListener) {
        jMenuItemRaceShowAll.addActionListener(actionListener);
    }

    public JMenu getjMenuLogout() {
        return jMenuLogout;
    }

    public JMenuItem getjMenuItemLogout() {
        return jMenuItemLogout;
    }
    
    public void jmiLogoutActionListener(ActionListener actionListener) {
        jMenuItemLogout.addActionListener(actionListener);
    }
    public void frmMainWindowListener(WindowListener windowListener){
        this.addWindowListener(windowListener);
    }
}

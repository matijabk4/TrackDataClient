/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogin;

/**
 *
 * @author laptop-02
 */
public class LoginController {

    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
        frmLogin.setResizable(false);
        frmLogin.setTitle("TrackData v1 - Login");
    }

    private void addActionListener() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginUser(actionEvent);
            }

        });
        frmLogin.getTxtPasswordAddKeyPressed(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginUser(e);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
        frmLogin.getTxtUsernameAddKeyPressed(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginUser(e);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

    private void resetForm() {
        frmLogin.getLblUsernameError().setText("");
        frmLogin.getLblPasswordError().setText("");
    }

    private void validateForm(String username, String password) throws Exception {
        String errorMessage = "";
        if (username.isEmpty()) {
            frmLogin.getLblUsernameError().setText("Username cannot be empty!");
            errorMessage += "Username cannot be empty!\n";
        }
        if (password.isEmpty()) {
            frmLogin.getLblPasswordError().setText("Password cannot be empty!");
            errorMessage += "Password cannot be empty!\n";
        }
        if (!errorMessage.isEmpty()) {
            throw new Exception(errorMessage);
        }
    }

    private void loginUser(ActionEvent actionEvent) {
        resetForm();
        try {
            String username = frmLogin.getTxtUsername().getText().trim();
            String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

            validateForm(username, password);

            //User user = Controller.getInstance().login(username, password);
            User user = Communication.getInstance().login(username, password);
            JOptionPane.showMessageDialog(
                    frmLogin,
                    "Welcome " + user.getFirstname() + " " + user.getLastname(),
                    "TrackData v1 - Login Success", JOptionPane.INFORMATION_MESSAGE
            );
            frmLogin.dispose();
            MainCordinator.getInstance().addParam(Constants.CURRENT_USER, user);
            MainCordinator.getInstance().openMainForm();
        } catch (Exception e) {
            if(e.getMessage().equals("Unknown user!") || e.getMessage().contains("refused")){
                JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "TrackData v1 - Login Error", JOptionPane.ERROR_MESSAGE);
        
            }
            //JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginUser(KeyEvent actionEvent) {
        resetForm();
        try {
            String username = frmLogin.getTxtUsername().getText().trim();
            String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

            validateForm(username, password);

            //User user = Controller.getInstance().login(username, password);
            User user = Communication.getInstance().login(username, password);
            JOptionPane.showMessageDialog(
                    frmLogin,
                    "Welcome " + user.getFirstname() + " " + user.getLastname(),
                    "TrackData v1 - Login Success", JOptionPane.INFORMATION_MESSAGE
            );
            frmLogin.dispose();
            MainCordinator.getInstance().addParam(Constants.CURRENT_USER, user);
            MainCordinator.getInstance().openMainForm();
        } catch (Exception e) {
            if(e.getMessage().equals("Unknown user!")){
            JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "TrackData v1 - Login Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
}

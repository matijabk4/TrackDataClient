/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.MotorcycleMake;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmRider;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author laptop-02
 */
public class RiderController {

    private final FrmRider frmRider;

    public RiderController(FrmRider frmRider) {
        this.frmRider = frmRider;
        addActionListeners();
    }

    private void addActionListeners() {
        frmRider.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    validateForm();
                    Rider rider = new Rider();
                    //rider.setID(Long.parseLong(frmRider.getTxtID().getText().trim()));
                    rider.setFirstname(frmRider.getTxtFName().getText().trim());
                    rider.setSurname(frmRider.getTxtSName().getText().trim());
                    rider.setNationality(frmRider.getTxtNationality().getText().trim());
                    rider.setMotorcycle((MotorcycleMake) frmRider.getCbMotorcycleMake().getSelectedItem());
                    rider.setRacingNum(Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()));
                    if (JOptionPane.showConfirmDialog(frmRider, "Are you sure you want to save this rider?", "TrackData v1 - Save Rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().addRider(rider);
                        System.out.println(rider.getID().toString());

                        frmRider.getTxtID().setText(rider.getID().toString());
                        JOptionPane.showMessageDialog(frmRider, "Rider successfully saved!", "TrackData v1 - Save Rider", JOptionPane.INFORMATION_MESSAGE);
                        frmRider.getTxtID().setText("");
                        frmRider.getTxtFName().setText("");
                        frmRider.getTxtSName().setText("");
                        frmRider.getTxtNationality().setText("");
                        frmRider.getCbMotorcycleMake().setSelectedItem(MotorcycleMake.Honda);
                        frmRider.getTxtRacingNum().setText("");
                    }

                } catch (Exception ex) {
                    //Logger.getLogger(FrmRider.class.getName()).log(Level.SEVERE, null, ex);
                    // JOptionPane.showMessageDialog(frmRider, ex.getMessage(), "Add new rider", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frmRider.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);

            }
        });

        frmRider.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                if (!frmRider.getBtnEnableChanges().isEnabled()) {
                    if (JOptionPane.showConfirmDialog(frmRider, "Are you sure? Any unsaved data will be lost.", "TrackData v1 - Save Rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        frmRider.dispose();
                    }
                }else{
                        frmRider.dispose();
                }
            }
        });

        frmRider.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                Rider rider = makeRiderFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmRider, "Are you sure you want to remove this rider?", "TrackData v1 - Remove Rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().deleteRider(rider);
                        JOptionPane.showMessageDialog(frmRider, "Rider successfully removed!\n", "TrackData v1 - Remove Rider", JOptionPane.INFORMATION_MESSAGE);
                        frmRider.dispose();
                    }
                    /*Controller.getInstance().deleteRider(rider);
                    JOptionPane.showMessageDialog(frmRider, "Rider deleted successfully!\n", "Delete rider", JOptionPane.INFORMATION_MESSAGE);
                     */

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRider, "Error deleting rider!\n" + ex.getMessage(), "TrackData v1 - Remove Rider", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmRider.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {

                try {
                    Rider rider = makeRiderFromForm();
                    validateFormEdit();
                    if (JOptionPane.showConfirmDialog(frmRider, "Are you sure you want to update this rider?", "TrackData v1 - Update Rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        Communication.getInstance().editRider(rider);
                        JOptionPane.showMessageDialog(frmRider, "Rider information successfully updated!\n", "TrackData v1 - Update Rider", JOptionPane.INFORMATION_MESSAGE);
                        frmRider.dispose();
                    }

                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(frmRider, "Error updating rider!\n" + ex.getMessage(), "TrackData v1 - Update Rider", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmRider.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView(formMode);
        frmRider.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmRider.setVisible(true);

    }

    private void prepareView(FormMode formMode) {
        fillCbMeasurementUnit();
        setupComponents(formMode);
    }

    private void fillCbMeasurementUnit() {
        frmRider.getCbMotorcycleMake().removeAllItems();
        for (MotorcycleMake m : MotorcycleMake.values()) {
            frmRider.getCbMotorcycleMake().addItem(m);
        }
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmRider.setResizable(false);
                frmRider.getBtnCancel().setEnabled(true);
                frmRider.getBtnDelete().setEnabled(false);
                frmRider.getBtnEdit().setEnabled(false);
                frmRider.getBtnEnableChanges().setEnabled(false);
                frmRider.getBtnSave().setEnabled(true);

                frmRider.getLblID().setEnabled(false);
                frmRider.getTxtID().setEnabled(false);
                frmRider.getTxtFName().setEnabled(true);
                frmRider.getTxtSName().setEnabled(true);
                frmRider.getTxtNationality().setEnabled(true);
                frmRider.getTxtRacingNum().setEnabled(true);
                frmRider.getCbMotorcycleMake().setEnabled(true);
                break;
            case FORM_VIEW:
                frmRider.setResizable(false);
                frmRider.getBtnCancel().setEnabled(true);
                frmRider.getBtnDelete().setEnabled(true);
                frmRider.getBtnEdit().setEnabled(false);
                frmRider.getBtnEnableChanges().setEnabled(true);
                frmRider.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmRider.getLblID().setEnabled(false);
                frmRider.getTxtID().setEnabled(false);
                frmRider.getTxtFName().setEnabled(false);
                frmRider.getTxtSName().setEnabled(false);
                frmRider.getTxtRacingNum().setEnabled(false);
                frmRider.getTxtNationality().setEnabled(false);
                frmRider.getCbMotorcycleMake().setEnabled(false);

                //get rider
                Rider r = (Rider) MainCordinator.getInstance().getParam(Constants.PARAM_RIDER);
                frmRider.getTxtID().setText(r.getID() + "");
                frmRider.getTxtFName().setText(r.getFirstname());
                frmRider.getTxtSName().setText(r.getSurname());
                frmRider.getTxtNationality().setText(r.getNationality());
                frmRider.getCbMotorcycleMake().setSelectedItem(MotorcycleMake.valueOf(r.getMotorcycle().toString()));
                frmRider.getTxtRacingNum().setText(String.valueOf(r.getRacingNum()));
                break;
            case FORM_EDIT:
                frmRider.setResizable(false);
                frmRider.getBtnCancel().setEnabled(true);
                frmRider.getBtnDelete().setEnabled(false);
                frmRider.getBtnEdit().setEnabled(true);
                frmRider.getBtnEnableChanges().setEnabled(false);
                frmRider.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmRider.getLblID().setEnabled(false);
                frmRider.getTxtID().setEnabled(false);
                frmRider.getTxtFName().setEnabled(true);
                frmRider.getTxtSName().setEnabled(true);
                frmRider.getTxtRacingNum().setEnabled(true);
                frmRider.getTxtNationality().setEnabled(true);
                frmRider.getCbMotorcycleMake().setEnabled(true);
                break;
        }
    }

    private Rider makeRiderFromForm() {
        Rider r = new Rider();
        r.setID(Long.parseLong(frmRider.getTxtID().getText().trim()));
        r.setFirstname(frmRider.getTxtFName().getText().trim());
        r.setSurname(frmRider.getTxtSName().getText().trim());
        r.setNationality(frmRider.getTxtNationality().getText().trim());
        r.setMotorcycle((MotorcycleMake) frmRider.getCbMotorcycleMake().getSelectedItem());
        try {
            r.setRacingNum(Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()));

        } catch (Exception e) {
        }
        return r;
    }

    private void validateForm() throws Exception {
        frmRider.getLblIDError().setText("");
        frmRider.getLblFirstnameError().setText("");
        frmRider.getLblSurnameError().setText("");
        frmRider.getLblNationalityError().setText("");
        frmRider.getLblRacingNumberError().setText("");
        String msg = "";

        if (frmRider.getTxtFName().getText().isEmpty()) {
            frmRider.getLblFirstnameError().setText("Please insert firstname");
            msg += "Firstname cannot be empty!\n";
        }
        if (frmRider.getTxtSName().getText().isEmpty()) {
            frmRider.getLblSurnameError().setText("Please insert surname");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRider.getTxtNationality().getText().isEmpty()) {
            frmRider.getLblNationalityError().setText("Please insert nationality");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRider.getTxtRacingNum().getText().isEmpty()) {
            frmRider.getLblRacingNumberError().setText("Please insert racing number");
            msg += "Racing number cannot be empty!\n";
        } else {
            try {
                Integer.parseInt(frmRider.getTxtRacingNum().getText().trim());
            } catch (Exception e) {
                frmRider.getLblRacingNumberError().setText("Please insert integer value");
                msg += "Racing number must be an integer value!\n";
            }

        }

        List<Rider> riders = Communication.getInstance().getAllRiders();
        boolean exists = false;
        boolean racingNumber = false;
        for (Rider r : riders) {
            if (r.getFirstname().toUpperCase().equals(frmRider.getTxtFName().getText().toUpperCase()) && r.getSurname().toUpperCase().equals(frmRider.getTxtSName().getText().toUpperCase())) {
                exists = true;
                frmRider.getLblFirstnameError().setText("Rider already exists");
                frmRider.getLblSurnameError().setText("Rider already exists");
            }
            try {
                if (r.getRacingNum().equals(Integer.valueOf(frmRider.getTxtRacingNum().getText()))) {
                    racingNumber = true;

                }
            } catch (Exception e) {

            }

        }
        if (racingNumber) {
            frmRider.getLblRacingNumberError().setText("Racing number already taken");
            msg += "Rider with racing number " + frmRider.getTxtRacingNum().getText() + " already exists!\n";
        }
        if (exists) {
            msg += "Rider " + frmRider.getTxtFName().getText() + " " + frmRider.getTxtSName().getText() + " already exists!\n";
        }
        boolean firstnameAllLetters = frmRider.getTxtFName().getText().chars().allMatch(Character::isLetter);
        boolean surnameAllLetters = frmRider.getTxtSName().getText().chars().allMatch(Character::isLetter);
        if (!firstnameAllLetters) {
            frmRider.getLblFirstnameError().setText("Please insert a new firstname");
            msg += "Firstname contains letters only!\n";
        }
        if (frmRider.getTxtFName().getText().trim().length() < 2) {
            frmRider.getLblFirstnameError().setText("Please insert a new firstname");
            msg += "Firstname is too short!\n";
        }
        if (!surnameAllLetters) {
            frmRider.getLblSurnameError().setText("Please insert a new surname");
            msg += "Surname contains letters only!\n";
        }
        try {
            char ch = frmRider.getTxtFName().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblFirstnameError().setText("Firstname starts with capital letter");
                msg += "Firstname starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRider.getTxtSName().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblSurnameError().setText("Surname starts with capital letter");
                msg += "Surname starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRider.getTxtNationality().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblNationalityError().setText("Nationality starts with capital letter");
                msg += "Nationality starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        if (frmRider.getTxtSName().getText().trim().length() < 2) {
            frmRider.getLblSurnameError().setText("Please insert a new surname");
            msg += "Surname is too short!\n";
        }
        try {
            if (Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()) < 0 || Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()) > 99) {
                frmRider.getLblRacingNumberError().setText("Please insert other racing number");
                msg += "Racing number must be in range [0-99]!\n";
            }
        } catch (Exception e) {
        }

        boolean nationalityAllLetters = frmRider.getTxtNationality().getText().chars().allMatch(Character::isLetter);
        if (!nationalityAllLetters) {
            frmRider.getLblNationalityError().setText("Please insert a valid nationality");
            msg += "Nationality contains letters only!\n";
        }
        if (frmRider.getTxtNationality().getText().trim().length() < 4) {
            frmRider.getLblNationalityError().setText("Please insert a valid nationality");
            msg += "Please insert a valid nationality!\n";
        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }

    private void validateFormEdit() throws Exception {
        frmRider.getLblIDError().setText("");
        frmRider.getLblFirstnameError().setText("");
        frmRider.getLblSurnameError().setText("");
        frmRider.getLblNationalityError().setText("");
        frmRider.getLblRacingNumberError().setText("");
        String msg = "";

        if (frmRider.getTxtFName().getText().isEmpty()) {
            frmRider.getLblFirstnameError().setText("Please insert firstname.");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmRider.getTxtSName().getText().isEmpty()) {
            frmRider.getLblSurnameError().setText("Please insert surname.");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRider.getTxtNationality().getText().isEmpty()) {
            frmRider.getLblNationalityError().setText("Please insert nationality.");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRider.getTxtRacingNum().getText().isEmpty()) {
            frmRider.getLblRacingNumberError().setText("Please insert racing number.");
            msg += "Racing number cannot be empty!\n";
        } else {
            try {
                Integer.parseInt(frmRider.getTxtRacingNum().getText().trim());
            } catch (Exception e) {
                frmRider.getLblRacingNumberError().setText("Please insert integer value.");
                msg += "Racing number must be an integer value!\n";
            }

        }
        try {
            char ch = frmRider.getTxtFName().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblFirstnameError().setText("Firstname starts with capital letter");
                msg += "Firstname starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRider.getTxtSName().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblSurnameError().setText("Surname starts with capital letter");
                msg += "Surname starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRider.getTxtNationality().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRider.getLblNationalityError().setText("Nationality starts with capital letter");
                msg += "Nationality starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        List<Rider> riders = Communication.getInstance().getAllRiders();
        int riderExists = 0;
        int racingNumberExists = 0;
        for (Rider r : riders) {
            if (r.getFirstname().toUpperCase().equals(frmRider.getTxtFName().getText().toUpperCase()) && r.getSurname().toUpperCase().equals(frmRider.getTxtSName().getText().toUpperCase()) && r.getID() != Integer.parseInt(frmRider.getTxtID().getText().trim())) {
                riderExists++;
                if (riderExists > 0) {
                    frmRider.getLblFirstnameError().setText("Rider already exists");
                    frmRider.getLblSurnameError().setText("Rider already exists");
                }
            }
            try {
                if (r.getRacingNum() == Integer.valueOf(frmRider.getTxtRacingNum().getText()) && r.getID() != Integer.parseInt(frmRider.getTxtID().getText().trim())) {
                    racingNumberExists++;
                    if (racingNumberExists > 0) {
                        frmRider.getLblRacingNumberError().setText("Please insert other racing number.");
                    }
                }
            } catch (Exception e) {
            }

        }
        if (racingNumberExists > 0) {
            msg += "Rider with racing number " + frmRider.getTxtRacingNum().getText() + " already exists!\n";
        }
        if (riderExists > 0) {
            msg += "Rider " + frmRider.getTxtFName().getText() + " " + frmRider.getTxtSName().getText() + " already exists!\n";
        }
        boolean firstnameAllLetters = frmRider.getTxtFName().getText().chars().allMatch(Character::isLetter);
        boolean surnameAllLetters = frmRider.getTxtSName().getText().chars().allMatch(Character::isLetter);
        if (!firstnameAllLetters) {
            frmRider.getLblFirstnameError().setText("Please insert a new firstname.");
            msg += "Firstname contains letters only!\n";
        }
        if (frmRider.getTxtFName().getText().trim().length() < 2) {
            frmRider.getLblFirstnameError().setText("Please insert a new firstname.");
            msg += "Firstname is too short!\n";
        }
        if (!surnameAllLetters) {
            frmRider.getLblSurnameError().setText("Please insert a new surname.");
            msg += "Surname contains letters only!\n";
        }
        if (frmRider.getTxtSName().getText().trim().length() < 2) {
            frmRider.getLblSurnameError().setText("Please insert a new surname.");
            msg += "Surname is too short!\n";
        }
        try {
            if (Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()) < 0 || Integer.parseInt(frmRider.getTxtRacingNum().getText().trim()) > 99) {
                frmRider.getLblRacingNumberError().setText("Please insert other racing number.");
                msg += "Racing number must be in range [0-99]";
            }
        } catch (Exception e) {
        }

        boolean nationalityAllLetters = frmRider.getTxtNationality().getText().chars().allMatch(Character::isLetter);
        if (!nationalityAllLetters) {
            frmRider.getLblNationalityError().setText("Please insert a valid nationality.");
            msg += "Nationality contains letters only!\n";
        }
        if (frmRider.getTxtNationality().getText().trim().length() < 4) {
            frmRider.getLblNationalityError().setText("Please insert a valid nationality.");
            msg += "Please insert a valid nationality!\n";
        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }
}

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
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmRider;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.form.FrmTeam;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author laptop-02
 */
public class TeamController {

    private final FrmTeam frmTeam;

    public TeamController(FrmTeam frmTeam) {
        this.frmTeam = frmTeam;
        addActionListeners();
    }

    private void addActionListeners() {
        frmTeam.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    validateForm();
                    RacingTeam team = new RacingTeam();
                    try {
                        team.setId(Integer.parseInt(frmTeam.getTxtID().getText().trim()));
                    } catch (Exception e) {
                    }

                    team.setBudget(Double.parseDouble(frmTeam.getTxtBudget().getText().trim()));
                    team.setGeneralManager(frmTeam.getTxtManager().getText().trim());
                    team.setHeadquarters(frmTeam.getTxtHQ().getText().trim());
                    team.setName(frmTeam.getTxtName().getText().trim());
                    team.setSponsor(frmTeam.getTxtSponsor().getText().trim());
                    if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure you want to save this team?", "TrackData v1 - Save Team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().addTeam(team);
                        frmTeam.getTxtID().setText("ID successfully generated!");
                        System.out.println(team.getId());
                        //JOptionPane.showMessageDialog(frmTeam, "Team successfully saved!", "TrackData v1 - Save Team", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(frmTeam, "Team could not be saved!", "TrackData v1 - Save Team", JOptionPane.ERROR_MESSAGE);
                        
                        frmTeam.getTxtID().setText("");
                        frmTeam.getTxtBudget().setText("");
                        frmTeam.getTxtHQ().setText("");
                        frmTeam.getTxtManager().setText("");
                        frmTeam.getTxtName().setText("");
                        frmTeam.getTxtSponsor().setText("");
                    }
                } catch (Exception ex) {
                    //Logger.getLogger(FrmRider.class.getName()).log(Level.SEVERE, null, ex);
                    //JOptionPane.showMessageDialog(frmTeam, ex.getMessage());
                }
            }
        });

        frmTeam.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });

        frmTeam.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                 if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure? Any unsaved data will be lost.", "TrackData v1 - Save Team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frmTeam.dispose();
                }
            }
        });

        frmTeam.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                RacingTeam team = makeTeamFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure you want to delete this team?", "TrackData v1 - Delete team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().deleteTeam(team);
                        JOptionPane.showMessageDialog(frmTeam, "Team deleted successfully!\n", "TrackData v1 - Delete team", JOptionPane.INFORMATION_MESSAGE);
                        frmTeam.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmTeam, "Error deleting team!\n" + ex.getMessage(), "TrackData v1 - Delete team", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmTeam.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                try {
                    RacingTeam team = makeTeamFromForm();
                    validateFormEdit();

                    if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure you want to update this team?", "TrackData v1 - Edit team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        Communication.getInstance().editTeam(team);
                        JOptionPane.showMessageDialog(frmTeam, "Team information updated successfully!\n", "TrackData v1 - Edit team", JOptionPane.INFORMATION_MESSAGE);
                        frmTeam.dispose();
                    }

                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(frmTeam, "Error editting team!\n" + ex.getMessage(), "Edit team", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmTeam.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView(formMode);
        frmTeam.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmTeam.setVisible(true);
        frmTeam.setResizable(false);
        
    }

    private void prepareView(FormMode formMode) {
        //fillCbMeasurementUnit();
        setupComponents(formMode);
        frmTeam.setResizable(false);
        frmTeam.setSize(680, 300);
    }

    /*private void fillCbMeasurementUnit() {
        frmTeam.getCbMotorcycleMake().removeAllItems();
        for (MotorcycleMake m : MotorcycleMake.values()) {
            frmTeam.getCbMotorcycleMake().addItem(m);
        }
    }*/
    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmTeam.getBtnCancel().setEnabled(true);
                frmTeam.getBtnDelete().setEnabled(false);
                frmTeam.getBtnEdit().setEnabled(false);
                frmTeam.getBtnEnableChanges().setEnabled(false);
                frmTeam.getBtnSave().setEnabled(true);

                frmTeam.getTxtID().setEnabled(false);
                frmTeam.getTxtBudget().setEnabled(true);
                frmTeam.getTxtHQ().setEnabled(true);
                frmTeam.getTxtManager().setEnabled(true);
                frmTeam.getTxtName().setEnabled(true);
                frmTeam.getTxtSponsor().setEnabled(true);
                break;
            case FORM_VIEW:
                frmTeam.getBtnCancel().setEnabled(true);
                frmTeam.getBtnDelete().setEnabled(true);
                frmTeam.getBtnEdit().setEnabled(false);
                frmTeam.getBtnEnableChanges().setEnabled(true);
                frmTeam.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmTeam.getTxtID().setEnabled(false);
                frmTeam.getTxtBudget().setEnabled(false);
                frmTeam.getTxtHQ().setEnabled(false);
                frmTeam.getTxtManager().setEnabled(false);
                frmTeam.getTxtName().setEnabled(false);
                frmTeam.getTxtSponsor().setEnabled(false);

                //get product
                RacingTeam r = (RacingTeam) MainCordinator.getInstance().getParam(Constants.PARAM_TEAM);
                frmTeam.getTxtID().setText(r.getId() + "");
                frmTeam.getTxtBudget().setText(String.valueOf(r.getBudget()));
                frmTeam.getTxtHQ().setText(r.getHeadquarters());
                frmTeam.getTxtManager().setText(r.getGeneralManager());
                frmTeam.getTxtName().setText(r.getName());
                frmTeam.getTxtSponsor().setText(r.getSponsor());
                break;
            case FORM_EDIT:
                frmTeam.getBtnCancel().setEnabled(true);
                frmTeam.getBtnDelete().setEnabled(false);
                frmTeam.getBtnEdit().setEnabled(true);
                frmTeam.getBtnEnableChanges().setEnabled(false);
                frmTeam.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmTeam.getTxtID().setEnabled(false);
                frmTeam.getTxtBudget().setEnabled(true);
                frmTeam.getTxtHQ().setEnabled(true);
                frmTeam.getTxtManager().setEnabled(true);
                frmTeam.getTxtName().setEnabled(true);
                frmTeam.getTxtSponsor().setEnabled(true);
                break;
        }
    }

    private RacingTeam makeTeamFromForm() {
        RacingTeam r = new RacingTeam();
        r.setId(Integer.parseInt(frmTeam.getTxtID().getText().trim()));
        r.setGeneralManager(frmTeam.getTxtManager().getText().trim());
        r.setHeadquarters(frmTeam.getTxtHQ().getText().trim());
        r.setName(frmTeam.getTxtName().getText().trim());
        r.setSponsor(frmTeam.getTxtSponsor().getText().trim());
        try {
            r.setBudget(Double.parseDouble(frmTeam.getTxtBudget().getText().trim()));

        } catch (Exception e) {
        }
        return r;
    }

    private void validateForm() throws Exception {
        frmTeam.getLblIDError().setText("");
        frmTeam.getLblHeadquartersError().setText("");
        frmTeam.getLblSponsorError().setText("");
        frmTeam.getLblManagerError().setText("");
        frmTeam.getLblNameError().setText("");
        frmTeam.getLblBudgetError().setText("");
        String msg = "";

        if (frmTeam.getTxtHQ().getText().isEmpty()) {
            frmTeam.getLblHeadquartersError().setText("Please insert headquarters");
            msg += "Headquarters cannot be empty!\n";

        }
        if (frmTeam.getTxtSponsor().getText().isEmpty()) {
            frmTeam.getLblSponsorError().setText("Please insert sponsor");
            msg += "Sponsor cannot be empty!\n";
        }
        if (frmTeam.getTxtManager().getText().isEmpty()) {
            frmTeam.getLblManagerError().setText("Please insert manager");
            msg += "Manager cannot be empty!\n";
        }
        if (frmTeam.getTxtName().getText().isEmpty()) {
            frmTeam.getLblNameError().setText("Please insert team name");
            msg += "Team name cannot be empty!\n";
        }
        if (frmTeam.getTxtBudget().getText().isEmpty()) {
            frmTeam.getLblBudgetError().setText("Please insert budget");
            msg += "Budget cannot be empty!\n";
        }
        

        boolean HQAllLetters = frmTeam.getTxtHQ().getText().chars().anyMatch(Character::isDigit);
        if (HQAllLetters) {
            frmTeam.getLblHeadquartersError().setText("Please insert a valid headquarter");
            msg += "Headquarter contains letters only!\n";
        }
        try {
            char ch = frmTeam.getTxtHQ().getText().charAt(0);
        if (!Character.isUpperCase(ch)) {
            frmTeam.getLblHeadquartersError().setText("Headquarter name starts with capital letter");
            msg += "Headquarter starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        

        if (frmTeam.getTxtHQ().getText().trim().length() < 4 && !frmTeam.getTxtHQ().getText().isEmpty()) {
            frmTeam.getLblHeadquartersError().setText("Headquarter name too short");
            msg += "Please insert a valid headquarter.!\n";
        }
        boolean SponsorAllLetters = frmTeam.getTxtSponsor().getText().chars().anyMatch(Character::isDigit);
        if (SponsorAllLetters) {
            frmTeam.getLblSponsorError().setText("Please insert a valid sponsor");
            msg += "Sponsor contains letters only!\n";
        }
        try {
            char ch1 = frmTeam.getTxtSponsor().getText().charAt(0);
        if (!Character.isUpperCase(ch1)) {
            frmTeam.getLblSponsorError().setText("Sponsor name starts with capital letter");
            msg += "Sponsor name starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        
        if (frmTeam.getTxtSponsor().getText().trim().length() < 2 && !frmTeam.getTxtSponsor().getText().isEmpty()) {
            frmTeam.getLblSponsorError().setText("Sponsor name too short");
            msg += "Please insert a valid sponsor.!\n";
        }
        boolean NameAllLetters = frmTeam.getTxtName().getText().chars().anyMatch(Character::isDigit);
        if (NameAllLetters) {
            frmTeam.getLblNameError().setText("Please insert a valid team name");
            msg += "Team name contains letters only!\n";
        }
        try {
            char ch2 = frmTeam.getTxtName().getText().charAt(0);
        if (!Character.isUpperCase(ch2)) {
            frmTeam.getLblNameError().setText("Team name starts with capital letter");
            msg += "Team name starts with capital letter!\n";
        }

        } catch (Exception e) {
        }
                if (frmTeam.getTxtName().getText().trim().length() < 2 && !frmTeam.getTxtName().getText().isEmpty()) {
            frmTeam.getLblNameError().setText("Team name too short");
            msg += "Please insert a valid team name.!\n";
        }
        boolean ManagerAllLetters = frmTeam.getTxtManager().getText().chars().anyMatch(Character::isDigit);
        if (ManagerAllLetters) {
            frmTeam.getLblManagerError().setText("Please insert a valid manager name");
            msg += "Manager name contains letters only!\n";
        }
        try {
            char ch3 = frmTeam.getTxtManager().getText().charAt(0);
        if (!Character.isUpperCase(ch3)) {
            frmTeam.getLblManagerError().setText("Manager name starts with capital letter");
            msg += "Manager name starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        
        if (frmTeam.getTxtManager().getText().trim().length() < 3 && !frmTeam.getTxtManager().getText().isEmpty()) {
            frmTeam.getLblManagerError().setText("Manager name too short");
            msg += "Please insert a valid manager name.!\n";
        }
        if (frmTeam.getTxtBudget().getText().length() > 8) {
            frmTeam.getLblBudgetError().setText("Budget cannot exceed 8 digits");
            msg += "Budget cannot exceed 8 digits!\n";
        }
        try {
            Double.parseDouble(frmTeam.getTxtBudget().getText().trim());
        } catch (Exception e) {
            frmTeam.getLblBudgetError().setText("Please insert numeric value");
            msg += "Racing number must be numeric value!\n";
        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }

    private void validateFormEdit() throws Exception {
        frmTeam.getLblIDError().setText("");
        frmTeam.getLblHeadquartersError().setText("");
        frmTeam.getLblSponsorError().setText("");
        frmTeam.getLblManagerError().setText("");
        frmTeam.getLblNameError().setText("");
        frmTeam.getLblBudgetError().setText("");
        String msg = "";

        if (frmTeam.getTxtHQ().getText().isEmpty()) {
            frmTeam.getLblHeadquartersError().setText("Please insert headquarters");
            msg += "Headquarters cannot be empty!\n";

        }
        if (frmTeam.getTxtSponsor().getText().isEmpty()) {
            frmTeam.getLblSponsorError().setText("Please insert sponsor");
            msg += "Sponsor cannot be empty!\n";
        }
        if (frmTeam.getTxtManager().getText().isEmpty()) {
            frmTeam.getLblManagerError().setText("Please insert manager");
            msg += "Manager cannot be empty!\n";
        }
        if (frmTeam.getTxtName().getText().isEmpty()) {
            frmTeam.getLblNameError().setText("Please insert team name");
            msg += "Team name cannot be empty!\n";
        }
        if (frmTeam.getTxtBudget().getText().isEmpty() || frmTeam.getTxtBudget().getText() == "") {
            frmTeam.getLblBudgetError().setText("Please insert budget");
            msg += "Budget cannot be empty!\n";
        } 
           

        
        List<RacingTeam> teams = Communication.getInstance().getAllTeams();
        for (RacingTeam team : teams) {
            if (team.getName().toUpperCase().equals(frmTeam.getTxtName().getText().toUpperCase()) && team.getId() != Integer.parseInt(frmTeam.getTxtID().getText().trim())) {
                frmTeam.getLblNameError().setText("Team " + frmTeam.getTxtName().getText() + " already exists");
                msg += "Team already exists!\n";
            }
        }
        boolean HQAllLetters = frmTeam.getTxtHQ().getText().chars().anyMatch(Character::isDigit);
        if (HQAllLetters) {
            frmTeam.getLblHeadquartersError().setText("Please insert a valid headquarter");
            msg += "Headquarter contains letters only!\n";
        }
        try {
            char ch = frmTeam.getTxtHQ().getText().charAt(0);
        if (!Character.isUpperCase(ch)) {
            frmTeam.getLblHeadquartersError().setText("Headquarter name starts with capital letter");
            msg += "Headquarter starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        

        if (frmTeam.getTxtHQ().getText().trim().length() < 2 && !frmTeam.getTxtHQ().getText().isEmpty()) {
            frmTeam.getLblHeadquartersError().setText("Headquarter name too short");
            msg += "Please insert a valid headquarter.!\n";
        }
        boolean SponsorAllLetters = frmTeam.getTxtSponsor().getText().chars().anyMatch(Character::isDigit);
        if (SponsorAllLetters) {
            frmTeam.getLblSponsorError().setText("Please insert a valid sponsor");
            msg += "Sponsor contains letters only!\n";
        }
        try {
            char ch1 = frmTeam.getTxtSponsor().getText().charAt(0);
        if (!Character.isUpperCase(ch1)) {
            frmTeam.getLblSponsorError().setText("Sponsor name starts with capital letter");
            msg += "Sponsor name starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        
        if (frmTeam.getTxtSponsor().getText().trim().length() < 2 && !frmTeam.getTxtSponsor().getText().isEmpty()) {
            frmTeam.getLblSponsorError().setText("Sponsor name too short");
            msg += "Please insert a valid sponsor.!\n";
        }
        boolean NameAllLetters = frmTeam.getTxtName().getText().chars().anyMatch(Character::isDigit);
        if (NameAllLetters) {
            frmTeam.getLblNameError().setText("Please insert a valid team name");
            msg += "Team name contains letters only!\n";
        }
        try {
            char ch2 = frmTeam.getTxtName().getText().charAt(0);
        if (!Character.isUpperCase(ch2)) {
            frmTeam.getLblNameError().setText("Team name starts with capital letter");
            msg += "Team name starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        
        if (frmTeam.getTxtName().getText().trim().length() < 4 && !frmTeam.getTxtName().getText().isEmpty()) {
            frmTeam.getLblNameError().setText("Team name too short");
            msg += "Please insert a valid team name.!\n";
        }
        boolean ManagerAllLetters = frmTeam.getTxtManager().getText().chars().anyMatch(Character::isDigit);
        if (ManagerAllLetters) {
            frmTeam.getLblManagerError().setText("Please insert a valid manager name");
            msg += "Manager name contains letters only!\n";
        }
        try {
            char ch3 = frmTeam.getTxtManager().getText().charAt(0);
        if (!Character.isUpperCase(ch3)) {
            frmTeam.getLblManagerError().setText("Manager name starts with capital letter");
            msg += "Manager name starts with capital letter!\n";
        }
        } catch (Exception e) {
        }
        
        if (frmTeam.getTxtManager().getText().trim().length() < 4 && !frmTeam.getTxtManager().getText().isEmpty()) {
            frmTeam.getLblManagerError().setText("Manager name too short");
            msg += "Please insert a valid manager name.!\n";
        }
        if (frmTeam.getTxtBudget().getText().length() > 8) {
            frmTeam.getLblBudgetError().setText("Budget cannot exceed 8 digits");
            msg += "Budget cannot exceed 8 digits!\n";
        }
         try {
                Double.parseDouble(frmTeam.getTxtBudget().getText().trim());
            } catch (Exception e) {
                frmTeam.getLblBudgetError().setText("Please insert numeric value");
                msg += "Racing number must be numeric value!\n";
            }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }
}

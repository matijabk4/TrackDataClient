/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                    //validateForm();
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

                    Communication.getInstance().addTeam(team);
                    frmTeam.getTxtID().setText("ID successfully generated!");
                    System.out.println(team.getId());
                    JOptionPane.showMessageDialog(frmTeam, "Team successfully saved!", "Add team", JOptionPane.INFORMATION_MESSAGE);
                    frmTeam.getTxtID().setText("");
                    frmTeam.getTxtBudget().setText("");
                    frmTeam.getTxtHQ().setText("");
                    frmTeam.getTxtManager().setText("");
                    frmTeam.getTxtName().setText("");
                    frmTeam.getTxtSponsor().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(FrmRider.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmTeam, ex.getMessage());
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
                frmTeam.dispose();
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
                    if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure you want to delete this team?", "Delete team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().deleteTeam(team);
                        JOptionPane.showMessageDialog(frmTeam, "Team deleted successfully!\n", "Delete team", JOptionPane.INFORMATION_MESSAGE);
                        frmTeam.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmTeam, "Error deleting team!\n" + ex.getMessage(), "Delete team", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmTeam.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                RacingTeam team = makeTeamFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmTeam, "Are you sure you want to update this team?", "Edit team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        Communication.getInstance().editTeam(team);
                        JOptionPane.showMessageDialog(frmTeam, "Team information updated successfully!\n", "Edit team", JOptionPane.INFORMATION_MESSAGE);
                        frmTeam.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmTeam, "Error editting team!\n" + ex.getMessage(), "Edit team", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    private void prepareView(FormMode formMode) {
        //fillCbMeasurementUnit();
        setupComponents(formMode);
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
        r.setBudget(Double.parseDouble(frmTeam.getTxtBudget().getText().trim()));
        r.setGeneralManager(frmTeam.getTxtManager().getText().trim());
        r.setHeadquarters(frmTeam.getTxtHQ().getText().trim());
        r.setName(frmTeam.getTxtName().getText().trim());
        r.setSponsor(frmTeam.getTxtSponsor().getText().trim());
        return r;
    }

    /*private void validateForm() throws Exception {
        frmTeam.getLblIDError().setText("");
        frmTeam.getLblFirstnameError().setText("");
        frmTeam.getLblSurnameError().setText("");
        frmTeam.getLblNationalityError().setText("");
        frmTeam.getLblRacingNumberError().setText("");
        String msg = "";
        if (frmTeam.getTxtID().getText().isEmpty()) {
            frmTeam.getLblIDError().setText("Please insert ID");
            msg += "ID cannot be empty!\n";

        } else {
            try {
                Long.parseLong(frmTeam.getTxtID().getText().trim());
            } catch (Exception e) {
                frmTeam.getLblIDError().setText("Please insert long value");
                msg += "ID must be a long value!\n";
            }
        }
        if (frmTeam.getTxtFName().getText().isEmpty()) {
            frmTeam.getLblFirstnameError().setText("Please insert firstname");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmTeam.getTxtSName().getText().isEmpty()) {
            frmTeam.getLblSurnameError().setText("Please insert surname");
            msg += "Surname cannot be empty!\n";
        }
        if (frmTeam.getTxtNationality().getText().isEmpty()) {
            frmTeam.getLblNationalityError().setText("Please insert nationality");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmTeam.getTxtRacingNum().getText().isEmpty()) {
            frmTeam.getLblRacingNumberError().setText("Please insert racing number");
            msg += "Racing number cannot be empty!\n";
        } else {
            try {
                Integer.parseInt(frmTeam.getTxtRacingNum().getText().trim());
            } catch (Exception e) {
                frmTeam.getLblRacingNumberError().setText("Please insert integer value");
                msg += "Racing number must be an integer value!\n";
            }

        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }*/
}

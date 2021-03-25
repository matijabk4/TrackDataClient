/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Race;
import rs.ac.bg.fon.ps.domain.RaceItem;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmRace;
import rs.ac.bg.fon.ps.view.form.FrmRider;
import rs.ac.bg.fon.ps.view.form.component.table.RaceItemTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author laptop-02
 */
public class RaceController {

    private final FrmRace frmRace;

    public RaceController(FrmRace frmRace) {
        this.frmRace = frmRace;
        //addActionListeners();
    }

    /*private void addActionListeners() {
        frmRace.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    validateForm();
                    Rider rider = new Rider();
                    rider.setID(Long.parseLong(frmRace.getTxtID().getText().trim()));
                    rider.setFirstname(frmRace.getTxtFName().getText().trim());
                    rider.setSurname(frmRace.getTxtSName().getText().trim());
                    rider.setNationality(frmRace.getTxtNationality().getText().trim());
                    rider.setMotorcycle((MotorcycleMake) frmRace.getCbMotorcycleMake().getSelectedItem());
                    rider.setRacingNum(Integer.parseInt(frmRace.getTxtRacingNum().getText().trim()));

                    Controller.getInstance().addRider(rider);

                    JOptionPane.showMessageDialog(frmRace, "Rider successfully saved!", "Add rider", JOptionPane.INFORMATION_MESSAGE);
                    frmRace.getTxtID().setText("");
                    frmRace.getTxtFName().setText("");
                    frmRace.getTxtSName().setText("");
                    frmRace.getTxtNationality().setText("");
                    frmRace.getCbMotorcycleMake().setSelectedItem(MotorcycleMake.Honda);
                    frmRace.getTxtRacingNum().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(FrmRider.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmRace, ex.getMessage());
                }
            }
        });

        frmRace.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });

        frmRace.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                frmRace.dispose();
            }
        });

        frmRace.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                Rider rider = makeRiderFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to delete this rider?", "Delete rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Controller.getInstance().deleteRider(rider);
                        JOptionPane.showMessageDialog(frmRace, "Rider deleted successfully!\n", "Delete rider", JOptionPane.INFORMATION_MESSAGE);
                        frmRace.dispose();
                    }
                    Controller.getInstance().deleteRider(rider);
                    JOptionPane.showMessageDialog(frmRace, "Rider deleted successfully!\n", "Delete rider", JOptionPane.INFORMATION_MESSAGE);
                     

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Error deleting rider!\n" + ex.getMessage(), "Delete rider", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmRace.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                Rider rider = makeRiderFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to update this rider?", "Update rider", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                        Controller.getInstance().editRider(rider);
                        JOptionPane.showMessageDialog(frmRace, "Rider information updated successfully!\n", "Edit rider", JOptionPane.INFORMATION_MESSAGE);
                        frmRace.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Error editting rider!\n" + ex.getMessage(), "Edit rider", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/

 /**/
    public void openForm(FormMode formMode) {
        frmRace.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView(formMode);
        frmRace.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmRace.setVisible(true);
        frmRace.setResizable(false);
    }

    private void prepareView(FormMode formMode) {
        fillForm();
        fillDefaultValues();
        fillTblRace();
        addActionListeners();
        setupComponents(formMode);
        frmRace.setResizable(false);
    }/*

    private void fillCbMeasurementUnit() {
        frmRace.getCbMotorcycleMake().removeAllItems();
        for (MotorcycleMake m : MotorcycleMake.values()) {
            frmRace.getCbMotorcycleMake().addItem(m);
        }
    }

     */
    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmRace.getBtnCancel().setEnabled(true);
                frmRace.getBtnDelete().setEnabled(false);
                frmRace.getBtnEdit().setEnabled(false);
                frmRace.getBtnEnableChanges().setEnabled(false);
                frmRace.getBtnSaveRace().setEnabled(true);

                frmRace.getTxtID().setEnabled(false);
                frmRace.getTxtTrack().setEnabled(true);
                frmRace.getTxtDate().setEnabled(true);
                frmRace.getTxtRace().setEnabled(true);
                frmRace.getTxtTotalLaps().setEnabled(true);
                break;
            case FORM_VIEW:
                frmRace.getBtnCancel().setEnabled(true);
                frmRace.getBtnDelete().setEnabled(true);
                frmRace.getBtnEdit().setEnabled(false);
                frmRace.getBtnEnableChanges().setEnabled(true);
                frmRace.getBtnSaveRace().setEnabled(false);
                frmRace.getBtnRemove().setEnabled(false);
                frmRace.getjPanel1().setEnabled(false);
                frmRace.getCbRider().setEnabled(false);
                frmRace.getCbTeam().setEnabled(false);
                frmRace.getTxtStartingPosition().setEnabled(false);
                frmRace.getBtnAdd().setEnabled(false);
                //zabrani izmenu vrednosti
                frmRace.getTxtID().setEnabled(false);
                frmRace.getTxtTrack().setEnabled(false);
                frmRace.getTxtDate().setEnabled(false);
                frmRace.getTxtRace().setEnabled(false);
                frmRace.getTxtTotalLaps().setEnabled(false);
                frmRace.getjPanel1().setVisible(false);

                //get race
                Race r = (Race) MainCordinator.getInstance().getParam(Constants.PARAM_RACE);
                frmRace.getTxtID().setText(r.getId() + "");
                frmRace.getTxtTrack().setText(r.getTrack());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
                 {
                    try {
                        Date d = sdf.parse(r.getDate().toString());
                        sdf.applyPattern("dd.MM.yyyy.");
                        String date = sdf.format(d);
                        frmRace.getTxtDate().setText(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                frmRace.getTxtRace().setText(r.getName());
                frmRace.getTxtTotalLaps().setText(String.valueOf(r.getTotalLaps()));
                RaceItemTableModel ritm = new RaceItemTableModel(r);
                frmRace.getTblRaceItem().setModel(ritm);
                break;
            case FORM_EDIT:
                frmRace.getBtnCancel().setEnabled(true);
                frmRace.getBtnDelete().setEnabled(false);
                frmRace.getBtnEdit().setEnabled(true);
                frmRace.getBtnEnableChanges().setEnabled(false);
                frmRace.getBtnSaveRace().setEnabled(false);
                frmRace.getBtnRemove().setEnabled(true);
                frmRace.getjPanel1().setEnabled(true);
                frmRace.getCbRider().setEnabled(true);
                frmRace.getCbTeam().setEnabled(true);
                frmRace.getTxtStartingPosition().setEnabled(true);
                frmRace.getBtnAdd().setEnabled(true);
                frmRace.getjPanel1().setVisible(true);
                //zabrani izmenu vrednosti
                frmRace.getTxtID().setEnabled(false);
                frmRace.getTxtTrack().setEnabled(true);
                frmRace.getTxtDate().setEnabled(true);
                frmRace.getTxtRace().setEnabled(true);
                frmRace.getTxtTotalLaps().setEnabled(true);
                break;
        }
    }

    /*private Rider makeRiderFromForm() {
        Rider r = new Rider();
        r.setID(Long.parseLong(frmRace.getTxtID().getText().trim()));
        r.setFirstname(frmRace.getTxtFName().getText().trim());
        r.setSurname(frmRace.getTxtSName().getText().trim());
        r.setNationality(frmRace.getTxtNationality().getText().trim());
        r.setMotorcycle((MotorcycleMake) frmRace.getCbMotorcycleMake().getSelectedItem());
        r.setRacingNum(Integer.parseInt(frmRace.getTxtRacingNum().getText().trim()));
        return r;
    }

    private void validateForm() throws Exception {
        frmRace.getLblIDError().setText("");
        frmRace.getLblFirstnameError().setText("");
        frmRace.getLblSurnameError().setText("");
        frmRace.getLblNationalityError().setText("");
        frmRace.getLblRacingNumberError().setText("");
        String msg = "";
        if (frmRace.getTxtID().getText().isEmpty()) {
            frmRace.getLblIDError().setText("Please insert ID");
            msg += "ID cannot be empty!\n";

        } else {
            try {
                Long.parseLong(frmRace.getTxtID().getText().trim());
            } catch (Exception e) {
                frmRace.getLblIDError().setText("Please insert long value");
                msg += "ID must be a long value!\n";
            }
        }
        if (frmRace.getTxtFName().getText().isEmpty()) {
            frmRace.getLblFirstnameError().setText("Please insert firstname");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmRace.getTxtSName().getText().isEmpty()) {
            frmRace.getLblSurnameError().setText("Please insert surname");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRace.getTxtNationality().getText().isEmpty()) {
            frmRace.getLblNationalityError().setText("Please insert nationality");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRace.getTxtRacingNum().getText().isEmpty()) {
            frmRace.getLblRacingNumberError().setText("Please insert racing number");
            msg += "Racing number cannot be empty!\n";
        } else {
            try {
                Integer.parseInt(frmRace.getTxtRacingNum().getText().trim());
            } catch (Exception e) {
                frmRace.getLblRacingNumberError().setText("Please insert integer value");
                msg += "Racing number must be an integer value!\n";
            }

        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }*/
    private void fillForm() {
        try {
            frmRace.getCbTeam().setModel(new DefaultComboBoxModel(Communication.getInstance().getAllTeams().toArray()));

            frmRace.getCbRider().setModel(new DefaultComboBoxModel(Communication.getInstance().getAllRiders().toArray()));
            frmRace.getCbRider().setSelectedIndex(-1);
            frmRace.getCbTeam().setSelectedIndex(-1);
            frmRace.getCbRider().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Rider r = (Rider) e.getItem();
                        frmRace.getTxtRacingNumber().setText(String.valueOf(r.getRacingNum()));
                        frmRace.getTxtStartingPosition().grabFocus();
                        frmRace.getTxtStartingPosition().setSelectionStart(0);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillDefaultValues() {

    }

    private void fillTblRace() {
        RaceItemTableModel rtm = new RaceItemTableModel(new Race());
        frmRace.getTblRaceItem().setModel(rtm);
    }

    private void addActionListeners() {
        frmRace.addBtnAddRiderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRaceItem();
            }

            private void addRaceItem() {
                int row = 0;
                try {
                    row++;
                    Rider rider = (Rider) frmRace.getCbRider().getSelectedItem();
                    System.out.println("OK1");
                    row++;
                    int startingPos = Integer.parseInt(frmRace.getTxtStartingPosition().getText().trim());
                    System.out.println("OK2");
                    row++;
                    int racingNum = Integer.parseInt(frmRace.getTxtRacingNumber().getText().trim());
                    System.out.println("OK3");
                    row++;
                    RacingTeam team = (RacingTeam) frmRace.getCbTeam().getSelectedItem();
                    System.out.println("TIM" + team.getName());
                    System.out.println("OK4");
                    row++;
                    RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();
                    System.out.println("OK5");
                    row++;
                    List<RaceItem> raceItems = rtm.getRace().getItems();

                    row++;
                    RaceItem r = new RaceItem();
                    System.out.println("OK6");
                    r.setRaceNumber(racingNum);
                    System.out.println("OK7");
                    r.setRider(rider);
                    System.out.println("OK8");
                    r.setStartPosition(startingPos);
                    System.out.println("OK9");
                    r.setTeam(team);
                    System.out.println("OK10");
                    boolean existingRider = false;
                    int count = 1;
                    boolean position = false;
                    for (RaceItem i : raceItems) {
                        if (i.getRider() == null) {
                            System.out.println("RACE ITEM RIDER JE NULL");

                        }
                        if (i.getRider().toString().equals(rider.toString())) {
                            System.out.println("OK11");
                            existingRider = true;
                            System.out.println("OK12");
                        }
                        if (i.getTeam().getName() == null) {
                            System.out.println("TIM JE NAL");
                        }
                        if (i.getTeam().getId() == (team.getId())) {
                            System.out.println("OK13");
                            count++;
                        }
                        if (i.getStartPosition() == startingPos) {
                            System.out.println("OK14");
                            position = true;
                        }
                    }
                    System.out.println("ok 14,5");
                    if (!existingRider && count <= 2 && !position) {
                        System.out.println("OK15");
                        rtm.addRaceItem(racingNum, rider, team, startingPos);
                    } else {
                        if (existingRider) {
                            JOptionPane.showMessageDialog(frmRace, "Rider already exists!");
                        }
                        if (count > 2) {
                            JOptionPane.showMessageDialog(frmRace, "One racing team consists of only 2 riders!");
                        }
                        if (position) {
                            JOptionPane.showMessageDialog(frmRace, "Staring position number " + startingPos + " is taken!");
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Invalid rider data!" + row, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmRace.addBtnRemoveRiderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRaceItem();
            }

            private void removeRaceItem() {
                int row = frmRace.getTblRaceItem().getSelectedRow();
                if (row >= 0) {
                    RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();
                    rtm.removeRaceItem(row);
                } else {
                    JOptionPane.showMessageDialog(frmRace, "Please select a row to delete.", "Error!", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });
        frmRace.addBtnSaveRaceActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRace();
            }

            private void saveRace() {
                try {
                    validateForm();
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
                    RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();
                    Race race = new Race();
                    race.setTrack(frmRace.getTxtTrack().getText().trim());
                    race.setDate(df.parse(frmRace.getTxtDate().getText().trim()));
                    race.setName(frmRace.getTxtRace().getText().trim());
                    race.setTotalLaps(Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()));
                    race.setItems(rtm.getRace().getItems());
                    race.setCreatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                    List<RaceItem> is = race.getItems();
                    for (RaceItem i : is) {
                        System.out.println(i.getRaceNumber());
                    }

                    Communication.getInstance().addRace(race);
                    frmRace.getTxtID().setText(String.valueOf(race.getId()));
                    JOptionPane.showMessageDialog(frmRace, "Race successfully saved!");
                } catch (Exception ex) {
                    Logger.getLogger(FrmRace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        frmRace.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });
        frmRace.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                Race race = makeRaceFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to update this race?", "Edit race", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        List<RaceItem> items = race.getItems();

                        Communication.getInstance().editRace(race);

                        Communication.getInstance().editRaceItems(race, items);
                        JOptionPane.showMessageDialog(frmRace, "Race information updated successfully!\n", "Edit race", JOptionPane.INFORMATION_MESSAGE);
                        //frmRace.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Error editting race!\n" + ex.getMessage(), "Edit race", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    private void validateForm() throws Exception {
        frmRace.getLblDateError().setText("");
        frmRace.getLblRaceNameError().setText("");
        frmRace.getLblTotalLapsError().setText("");
        frmRace.getLblTrackError().setText("");
        String msg = "";
        if (frmRace.getTxtTrack().getText().isEmpty()) {
            frmRace.getLblTrackError().setText("Please insert track name");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmRace.getTxtTotalLaps().getText().isEmpty()) {
            frmRace.getLblTotalLapsError().setText("Please insert number of laps");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRace.getTxtRace().getText().isEmpty()) {
            frmRace.getLblRaceNameError().setText("Please insert race name");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRace.getTxtDate().getText().isEmpty()) {
            frmRace.getLblDateError().setText("Please insert race date");
            msg += "Racing number cannot be empty!\n";
        }
        try {
            if (Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) < 20 || Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) > 23) {
                frmRace.getLblTotalLapsError().setText("Number of laps must be in range [20-23]");
                msg += "Race cannot have over 23 laps!\n";
            }
        } catch (Exception e) {
        }
        if(!frmRace.getTxtDate().getText().isEmpty()){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
        try {
            df.parse(frmRace.getTxtDate().getText().trim());
        } catch (Exception e) {
            frmRace.getLblDateError().setText("Bad date format");
            msg += "Date must be in format dd.MM.yyyy.";
        }
        }
         boolean TrackAllLetters = frmRace.getTxtTrack().getText().chars().anyMatch(Character::isDigit);
        if (TrackAllLetters) {
            frmRace.getLblTrackError().setText("Please insert a valid track name");
            msg += "Track name contains letters only!\n";
        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }

    private Race makeRaceFromForm() {
        Race r = new Race();
        r.setId(Integer.parseInt(frmRace.getTxtID().getText().trim()));
        r.setTrack(frmRace.getTxtTrack().getText().trim());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
        try {
            r.setDate(df.parse(frmRace.getTxtDate().getText().trim()));
        } catch (ParseException ex) {
            Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        r.setUpdatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
        r.setName(frmRace.getTxtRace().getText().trim());
        r.setTotalLaps(Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()));
        RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();

        List<RaceItem> items = rtm.getRace().getItems();
        if (items.isEmpty()) {
            System.out.println("empty list");
        }
        r.setItems(items);

        return r;
    }
}

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
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
        addActionListeners();
    }

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
        setupComponents(formMode);
        frmRace.getTblRaceItem().getTableHeader().setResizingAllowed(false);
        frmRace.getTblRaceItem().getTableHeader().setReorderingAllowed(false);
        frmRace.setResizable(false);
    }

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
                frmRace.setSize(675, 610);
                //get race
                Race r = (Race) MainCordinator.getInstance().getParam(Constants.PARAM_RACE);
                frmRace.getTxtID().setText(r.getId() + "");
                frmRace.getTxtTrack().setText(r.getTrack());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 {
                    try {
                        Date d = sdf.parse(r.getDate().toString());
                        sdf.applyPattern("dd.MM.yyyy.");
                        String date = sdf.format(d);
                        frmRace.getTxtDate().setText(date);
                    } catch (ParseException ex) {
                        //Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                frmRace.getTxtRace().setText(r.getName());
                frmRace.getTxtTotalLaps().setText(String.valueOf(r.getTotalLaps()));
                RaceItemTableModel ritm = new RaceItemTableModel(r);
                frmRace.getTblRaceItem().setModel(ritm);
                frmRace.setLocationRelativeTo(null);
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
                frmRace.setSize(675, 900);
                frmRace.setLocationRelativeTo(null);
                break;
        }
    }

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
            //Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
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

                try {
                    RaceItem r = new RaceItem();
                    Rider rider = null;
                    RacingTeam team = null;
                    if(frmRace.getCbRider().getSelectedIndex()!=-1){
                    rider = (Rider) frmRace.getCbRider().getSelectedItem();
                    }
                    int startingPos = Integer.parseInt(frmRace.getTxtStartingPosition().getText().trim());
                    int racingNum = Integer.parseInt(frmRace.getTxtRacingNumber().getText().trim());
                    if(frmRace.getCbTeam().getSelectedIndex()!=-1){
                        team = (RacingTeam) frmRace.getCbTeam().getSelectedItem();
                    }
                    
                    RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();
                    List<RaceItem> raceItems = rtm.getRace().getItems();

                    
                    r.setRaceNumber(racingNum);
                    r.setRider(rider);
                    r.setStartPosition(startingPos);
                    r.setTeam(team);
                    boolean existingRider = false;
                    int count = 1;
                    boolean position = false;
                    for (RaceItem i : raceItems) {
                        if (i.getRider() == null) {

                        }
                        if (i.getRider().toString().equals(rider.toString())) {
                            existingRider = true;
                        }
                        if (i.getTeam().getName() == null) {
                        }
                        if (i.getTeam().getId() == (team.getId())) {
                            count++;
                        }
                        if (i.getStartPosition() == startingPos) {
                            position = true;
                        }
                    }
                    String message = "";
                    if (!existingRider && count <= 2 && !position) {
                        rtm.addRaceItem(racingNum, rider, team, startingPos);
                    } else {
                        if (existingRider) {
                            message += "Rider already exists!\n";
                        }
                        if (count > 2) {
                            message += "One racing team consists of maximum of 2 riders!\n";
                        }
                        if (position) {
                            message += "Staring position number " + startingPos + " is taken!\n";
                        }
                        JOptionPane.showMessageDialog(frmRace, message, "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Invalid rider data!", "TrackData v1 - Save Race", JOptionPane.ERROR_MESSAGE);
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
                RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();
                List<RaceItem> items = rtm.getRace().getItems();
                boolean empty = false;
                if (items.isEmpty()) {
                    JOptionPane.showMessageDialog(frmRace, "Riders list is empty", "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);
                    empty = true;
                }
                if (!empty) {
                    if (row >= 0) {
                        rtm.removeRaceItem(row);
                    } else {
                        JOptionPane.showMessageDialog(frmRace, "Please select a row to delete.", "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);

                    }
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

                    List<RaceItem> items = race.getItems();
                    int size = items.size();
                    boolean error = false;
                    for (RaceItem item : items) {
                        if (item.getStartPosition() > size) {
                            error = true;
                            JOptionPane.showMessageDialog(frmRace, "Starting positions must be in range [1," + size + "]\n", "TrackData v1 - Edit race", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!error) {
                        if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to save this race?", "TrackData v1 - Save Race", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            Communication.getInstance().addRace(race);
                            frmRace.getTxtID().setText(String.valueOf(race.getId()));
                            JOptionPane.showMessageDialog(frmRace, "Race successfully saved!", "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);
                            frmRace.getTxtID().setText("");
                            frmRace.getTxtDate().setText("");
                            frmRace.getTxtRace().setText("");
                            frmRace.getTxtTotalLaps().setText("");
                            frmRace.getTxtTrack().setText("");
                            frmRace.getTxtRacingNumber().setText("");
                            frmRace.getTxtStartingPosition().setText("");
                            frmRace.getCbTeam().setSelectedIndex(-1);
                            frmRace.getCbRider().setSelectedIndex(-1);
                            clearTableRaceItems(rtm);
                        }
                    }

                } catch (Exception ex) {
                    //Logger.getLogger(FrmRace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void clearTableRaceItems(RaceItemTableModel rtm) {
                rtm.removeAllRaceItems();
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
                try {
                    Race race = makeRaceFromForm();
                    validateFormEdit();

                    List<RaceItem> items = race.getItems();
                    int size = items.size();
                    boolean error = false;
                    for (RaceItem item : items) {
                        if (item.getStartPosition() > size) {
                            error = true;
                            JOptionPane.showMessageDialog(frmRace, "Starting positions must be in range [1," + size + "]\n", "TrackData v1 - Edit race", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }   
                    }
                    if (!error) {
                        if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to update this race?", "TrackData v1 - Edit race", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            Communication.getInstance().editRace(race);

                            Communication.getInstance().editRaceItems(race, items);
                            JOptionPane.showMessageDialog(frmRace, "Race information updated successfully!\n", "TrackData v1 - Edit race", JOptionPane.INFORMATION_MESSAGE);
                            frmRace.dispose();
                        }
                    }

                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(frmRace, "Error editting race!\n" + ex.getMessage(), "TrackData v1 - Edit race", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        frmRace.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                Race race = makeRaceFromForm();
                try {
                    if (JOptionPane.showConfirmDialog(frmRace, "Are you sure you want to delete this race?", "TrackData v1 - Delete race", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Communication.getInstance().deleteRace(race);
                        JOptionPane.showMessageDialog(frmRace, "Race " + race.getName().toUpperCase() + " deleted successfully!\n", "TrackData v1 - Delete race", JOptionPane.INFORMATION_MESSAGE);
                        frmRace.dispose();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRace, "Error deleting race!\n" + ex.getMessage(), "TrackData v1 - Delete race", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(RiderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmRace.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                if (!frmRace.getBtnEnableChanges().isEnabled()) {
                    if (JOptionPane.showConfirmDialog(frmRace, "Are you sure? Any unsaved data will be lost.", "TrackData v1 - Save Race", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        frmRace.dispose();
                    }
                } else {
                    frmRace.dispose();
                }
            }
        });
         frmRace.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                centerTableValues();
            }

            private void centerTableValues() {
                 // center column names
        ((DefaultTableCellRenderer) frmRace.getTblRaceItem().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // center column values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int col = 0; col < frmRace.getTblRaceItem().getColumnCount(); col++) {
            frmRace.getTblRaceItem().getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // frmViewRiders.getTblRiders().setAutoCreateRowSorter(true);
            }
            
        });
    }

    private void validateForm() throws Exception {
        frmRace.getLblDateError().setText("");
        frmRace.getLblRaceNameError().setText("");
        frmRace.getLblTotalLapsError().setText("");
        frmRace.getLblTrackError().setText("");
        RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();

        String msg = "";

        if (frmRace.getTxtTrack().getText().isEmpty()) {
            frmRace.getLblTrackError().setText("Please insert track name");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmRace.getTxtTotalLaps().getText().chars().anyMatch(Character::isAlphabetic)) {
            frmRace.getLblTotalLapsError().setText("Please insert numeric value");
        }
        if (frmRace.getTxtTotalLaps().getText().isEmpty()) {
            frmRace.getLblTotalLapsError().setText("Please insert number of laps");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRace.getTxtRace().getText().isEmpty()) {
            frmRace.getLblRaceNameError().setText("Please insert race name");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRace.getTxtDate().getText().chars().anyMatch(Character::isAlphabetic)) {
            frmRace.getLblDateError().setText("Bad date format");
            msg += "Date must be in format dd.MM.yyyy.";
        }
        if (frmRace.getTxtDate().getText().isEmpty()) {
            frmRace.getLblDateError().setText("Please insert race date");
            msg += "Racing number cannot be empty!\n";
        }
        try {
            char ch = frmRace.getTxtRace().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRace.getLblRaceNameError().setText("Race name starts with capital letter");
                msg += "Race name starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRace.getTxtTrack().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRace.getLblTrackError().setText("Track name starts with capital letter");
                msg += "Track name starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            String date = frmRace.getTxtDate().getText();
            String[] dateArray = date.split("\\.");
            String s1 = dateArray[0];
            String s2 = dateArray[1];
            String s3 = dateArray[2];
            int day = Integer.parseInt(s1);
            int month = Integer.parseInt(s2);
            int year = Integer.parseInt(s3);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int nextYear = currentYear + 1;
            if (day > 31 || month > 12) {
                frmRace.getLblDateError().setText("Bad date");
                msg += "Date must be in format dd.MM.yyyy.";
            }
            if (year > nextYear) {
                frmRace.getLblDateError().setText("Year is limited to " + nextYear);
                msg += "Date must be in format dd.MM.yyyy.";
            }
        } catch (Exception e) {
        }

        try {
            if (Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) < 20 || Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) > 23) {
                frmRace.getLblTotalLapsError().setText("Number of laps must be in range [20-23]");
                msg += "Race cannot have over 23 laps!\n";
            }
        } catch (Exception e) {
        }
        if (!frmRace.getTxtDate().getText().isEmpty()) {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
            try {
                df.parse(frmRace.getTxtDate().getText().trim());
            } catch (Exception e) {
                frmRace.getLblDateError().setText("Bad date format");
                msg += "Date must be in format dd.MM.yyyy.";
            }
        }
        long count = frmRace.getTxtDate().getText().chars().filter(ch -> ch == '.').count();
        if (count > 3) {
            frmRace.getLblDateError().setText("Bad date format");
            msg += "Date must be in format dd.MM.yyyy.";
        }
        boolean TrackAllLetters = frmRace.getTxtTrack().getText().chars().anyMatch(Character::isDigit);
        if (TrackAllLetters) {
            frmRace.getLblTrackError().setText("Please insert a valid track name");
            msg += "Track name contains letters only!\n";
        }

        List<RaceItem> raceItems = rtm.getRace().getItems();
        if (raceItems.size() < 6) {
            if (frmRace.getLblTrackError().getText().isEmpty() && frmRace.getLblDateError().getText().isEmpty()
                    && frmRace.getLblRaceNameError().getText().isEmpty()
                    && frmRace.getLblTotalLapsError().getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmRace, "Race must have at least 6 contestants", "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);
                msg += "Race must have at least 6 contestants!\n";
            }
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
            //Logger.getLogger(RaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        r.setUpdatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
        r.setName(frmRace.getTxtRace().getText().trim());
        r.setTotalLaps(Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()));
        RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();

        List<RaceItem> items = rtm.getRace().getItems();

        r.setItems(items);

        return r;
    }

    private void validateFormEdit() throws Exception {
        frmRace.getLblDateError().setText("");
        frmRace.getLblRaceNameError().setText("");
        frmRace.getLblTotalLapsError().setText("");
        frmRace.getLblTrackError().setText("");
        RaceItemTableModel rtm = (RaceItemTableModel) frmRace.getTblRaceItem().getModel();

        String msg = "";

        if (frmRace.getTxtTrack().getText().isEmpty()) {
            frmRace.getLblTrackError().setText("Please insert track name");
            msg += "Firstname cannot be empty!\n";

        }
        if (frmRace.getTxtTotalLaps().getText().chars().anyMatch(Character::isAlphabetic)) {
            frmRace.getLblTotalLapsError().setText("Please insert numeric value");
        }
        if (frmRace.getTxtTotalLaps().getText().isEmpty()) {
            frmRace.getLblTotalLapsError().setText("Please insert number of laps");
            msg += "Surname cannot be empty!\n";
        }
        if (frmRace.getTxtRace().getText().isEmpty()) {
            frmRace.getLblRaceNameError().setText("Please insert race name");
            msg += "Nationality cannot be empty!\n";
        }
        if (frmRace.getTxtDate().getText().chars().anyMatch(Character::isAlphabetic)) {
            frmRace.getLblDateError().setText("Bad date format");
            msg += "Date must be in format dd.MM.yyyy.";
        }
        if (frmRace.getTxtDate().getText().isEmpty()) {
            frmRace.getLblDateError().setText("Please insert race date");
            msg += "Racing number cannot be empty!\n";
        }

        try {
            String date = frmRace.getTxtDate().getText();
            String[] dateArray = date.split("\\.");
            String s1 = dateArray[0];
            String s2 = dateArray[1];
            String s3 = dateArray[2];
            int day = Integer.parseInt(s1);
            int month = Integer.parseInt(s2);
            int year = Integer.parseInt(s3);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int nextYear = currentYear + 1;
            if (day > 31 || month > 12) {
                frmRace.getLblDateError().setText("Bad date");
                msg += "Date must be in format dd.MM.yyyy.";
            }
            if (year > nextYear) {
                frmRace.getLblDateError().setText("Year is limited to " + nextYear);
                msg += "Date must be in format dd.MM.yyyy.";
            }

        } catch (Exception e) {
        }
        try {
            char ch = frmRace.getTxtRace().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRace.getLblRaceNameError().setText("Race name starts with capital letter");
                msg += "Race name starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            char ch = frmRace.getTxtTrack().getText().charAt(0);
            if (!Character.isUpperCase(ch)) {
                frmRace.getLblTrackError().setText("Track name starts with capital letter");
                msg += "Track name starts with capital letter!\n";
            }
        } catch (Exception e) {
        }
        try {
            if (Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) < 20 || Integer.parseInt(frmRace.getTxtTotalLaps().getText().trim()) > 23) {
                frmRace.getLblTotalLapsError().setText("Number of laps must be in range [20-23]");
                msg += "Race cannot have over 23 laps!\n";
            }
        } catch (Exception e) {
        }
        if (!frmRace.getTxtDate().getText().isEmpty()) {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
            try {
                df.parse(frmRace.getTxtDate().getText().trim());
            } catch (Exception e) {
                frmRace.getLblDateError().setText("Bad date format");
                msg += "Date must be in format dd.MM.yyyy.";
            }
        }
        long count = frmRace.getTxtDate().getText().chars().filter(ch -> ch == '.').count();
        if (count > 3) {
            frmRace.getLblDateError().setText("Bad date format");
            msg += "Date must be in format dd.MM.yyyy.";
        }
        boolean TrackAllLetters = frmRace.getTxtTrack().getText().chars().anyMatch(Character::isDigit);
        if (TrackAllLetters) {
            frmRace.getLblTrackError().setText("Please insert a valid track name");
            msg += "Track name contains letters only!\n";
        }
        List<RaceItem> raceItems = rtm.getRace().getItems();
        if (raceItems.size() < 6) {
            if (frmRace.getLblTrackError().getText().isEmpty() && frmRace.getLblDateError().getText().isEmpty()
                    && frmRace.getLblRaceNameError().getText().isEmpty()
                    && frmRace.getLblTotalLapsError().getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmRace, "Race must have at least 6 contestants", "TrackData v1 - Save Race", JOptionPane.INFORMATION_MESSAGE);
                msg += "Race must have at least 6 contestants!\n";
            }
        }
        if (!msg.isEmpty()) {
            throw new Exception(msg);
        }
    }
}

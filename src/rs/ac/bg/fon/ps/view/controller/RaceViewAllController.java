/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Race;
import rs.ac.bg.fon.ps.domain.RaceItem;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmViewRaces;
import rs.ac.bg.fon.ps.view.form.component.table.RaceItemTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.RaceTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.ResultTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.RiderTableModel;

/**
 *
 * @author laptop-02
 */
public class RaceViewAllController {

    private final FrmViewRaces frmViewRaces;

    public RaceViewAllController(FrmViewRaces frmViewRaces) {
        this.frmViewRaces = frmViewRaces;
        addActionListener();
        addFocusListener();
    }

    private void addFocusListener() {
        frmViewRaces.getTxtSearchAddFocus(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (frmViewRaces.getTxtSearch().getText().equals("Search...")) {
                    frmViewRaces.getTxtSearch().setForeground(Color.black);
                    frmViewRaces.getTxtSearch().setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (frmViewRaces.getTxtSearch().getText().isEmpty()) {
                    frmViewRaces.getTxtSearch().setText("Search...");
                    frmViewRaces.getTxtSearch().setForeground(Color.gray);

                } else {
                    frmViewRaces.getTxtSearch().setForeground(Color.black);
                }
            }
        });
    }

    private void addActionListener() {

        frmViewRaces.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblRaces();
                frmViewRaces.setLocationRelativeTo(null);
                frmViewRaces.getLblRaceName().setVisible(true);
                frmViewRaces.getLblRaceStatus().setVisible(true);
            }

        });
         frmViewRaces.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                frmViewRaces.getLblRaceName().setVisible(false);
                frmViewRaces.getLblRaceStatus().setVisible(false);
            }
            

        });
        frmViewRaces.getBtnSetRaceResultAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewRaces.getTblRaces().getSelectedRow();
                if (row >= 0) {
                    Race race = ((RaceTableModel) frmViewRaces.getTblRaces().getModel()).getRaceAt(row);
                    fillTblResults(race.getId());
                    frmViewRaces.setSize(1300, 900);
                    frmViewRaces.setResizable(false);
                    frmViewRaces.setLocationRelativeTo(null);
                    frmViewRaces.getjPanel1().setVisible(true);
                    frmViewRaces.getLblRaceName().setVisible(true);
                    frmViewRaces.getLblRaceStatus().setVisible(true);
                    frmViewRaces.getLblRName().setVisible(true);
                    frmViewRaces.getLblStat().setVisible(true);
                    frmViewRaces.getLblRName().setText(race.getName().toUpperCase());
                    frmViewRaces.getLblStat().setText("P E N D I N G");
                    frmViewRaces.getLblStat().setForeground(Color.yellow);
                    /*Rider r = ((RiderTableModel) frmViewRiders.getTblRiders().getModel()).getRiderAt(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_RIDER, r);
                    MainCordinator.getInstance().openRiderDetailsRiderForm();*/

                } else {
                    JOptionPane.showMessageDialog(frmViewRaces, "You must select a race", "RACE RESULTS", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        frmViewRaces.getBtnSaveRaceResultAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultTableModel rtm = (ResultTableModel) frmViewRaces.getTblResults().getModel();
                List<RaceItem> raceItemsForSave = rtm.getRaceItems();
                boolean errorSameFinishingPosition = false;
                for (int i = 0; i < raceItemsForSave.size() - 1; i++) {
                    RaceItem r1 = raceItemsForSave.get(i);
                    for (int j = i + 1; j < raceItemsForSave.size(); j++) {
                        RaceItem r2 = raceItemsForSave.get(j);
                        if (r1.getFinishPosition() == r2.getFinishPosition()) {
                            errorSameFinishingPosition = true;
                            JOptionPane.showMessageDialog(frmViewRaces, "Two riders cannot have the same finishing position!", "TrackData v1 - Save Race Results", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    break;
                }
                boolean errorFinishingPositionOutOfBounds = false;
                int bound = raceItemsForSave.size();
                for (RaceItem raceItem : raceItemsForSave) {
                    if (raceItem.getFinishPosition() > bound || raceItem.getFinishPosition() <= 0) {
                        errorFinishingPositionOutOfBounds = true;
                        JOptionPane.showMessageDialog(frmViewRaces, "Finishing position must be in range [1-" + bound + "]!", "TrackData v1 - Save Race Results", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                if (!errorSameFinishingPosition && !errorFinishingPositionOutOfBounds) {
                    saveRaceResult(raceItemsForSave);
                    frmViewRaces.setSize(860, 400);
                    frmViewRaces.getjPanel1().setVisible(false);
                    frmViewRaces.setLocationRelativeTo(null);
                     /*frmViewRaces.getLblRaceName().setVisible(false);
                    frmViewRaces.getLblRaceStatus().setVisible(false);
                    frmViewRaces.getLblStat().setVisible(false);
                    frmViewRaces.getLblRName().setVisible(false);*/
                }
            }
        });
        frmViewRaces.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewRaces.getTblRaces().getSelectedRow();
                if (row >= 0) {
                    Race r = ((RaceTableModel) frmViewRaces.getTblRaces().getModel()).getRaceAt(row);
                    /*RaceItemTableModel rtm = (RaceItemTableModel) frmViewRaces.getTblResults().getModel();
                    List<RaceItem> items = rtm.getRace().getItems();
                    for (RaceItem item : items) {
                        System.out.println(item.getFinishPosition());
                    }*/
                    List<RaceItem> items = getRaceItemsForRace(r);
                    r.setItems(items);

                    MainCordinator.getInstance().addParam(Constants.PARAM_RACE, r);
                    MainCordinator.getInstance().openRaceDetailsRiderForm();

                } else {
                    JOptionPane.showMessageDialog(frmViewRaces, "You must select a race", "RACE DETAILS", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        frmViewRaces.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblRaces();
                centerTableValues();
                searchFieldSettings();
                
               
            }

        });
    }

    public void openForm() {
        frmViewRaces.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView();

        frmViewRaces.setSize(860, 400);
        frmViewRaces.getjPanel1().setVisible(false);
        frmViewRaces.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmViewRaces.setVisible(true);
        frmViewRaces.setResizable(false);
    }

    private void prepareView() {
        frmViewRaces.getTblRaces().getTableHeader().setResizingAllowed(false);
        frmViewRaces.getTblRaces().getTableHeader().setReorderingAllowed(false);
        frmViewRaces.getTblResults().getTableHeader().setResizingAllowed(false);
        frmViewRaces.getTblResults().getTableHeader().setReorderingAllowed(false);
        //fillTblRiders();
    }

    private void fillTblRaces() {
        List<Race> races;
        try {
            races = Communication.getInstance().getAllRaces();
            RaceTableModel rtm = new RaceTableModel(races);
            frmViewRaces.getTblRaces().setModel(rtm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewRaces, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RiderViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveRaceResult(List<RaceItem> raceItemsForSave) {
        try {
            Communication.getInstance().saveRaceResult(raceItemsForSave);
            Communication.getInstance().updateRaceInfo(raceItemsForSave.get(0).getRace().getId());
            int row = frmViewRaces.getTblRaces().getSelectedRow();
            if (row >= 0) {
                Race r = ((RaceTableModel) frmViewRaces.getTblRaces().getModel()).getRaceAt(row);
                 frmViewRaces.getLblStat().setText("S A V E D!");
               frmViewRaces.getLblStat().setForeground(Color.green);
                JOptionPane.showMessageDialog(frmViewRaces, "Racing statistics for race " + r.getName().toUpperCase() + " successfully saved!", "TrackData v1 - Save Race Results", JOptionPane.INFORMATION_MESSAGE);
              
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewRaces, "GRESKAAA");
            Logger.getLogger(RaceViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fillTblResults(int id) {
        List<RaceItem> raceItems;
        try {
            raceItems = Communication.getInstance().getRaceItems(id);
            ResultTableModel rtm = new ResultTableModel(raceItems);
            frmViewRaces.getTblResults().setModel(rtm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewRaces, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RiderViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<RaceItem> getRaceItemsForRace(Race r) {
        List<RaceItem> items = new ArrayList<>();
        try {
            items = Communication.getInstance().getRaceItems(r.getId());
        } catch (Exception ex) {
            Logger.getLogger(RaceViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public void refresh() {
        fillTblRaces();
    }

    private void centerTableValues() {
        // center column names
        ((DefaultTableCellRenderer) frmViewRaces.getTblRaces().getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // center column values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int col = 0; col < frmViewRaces.getTblRaces().getColumnCount(); col++) {
            frmViewRaces.getTblRaces().getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        //frmViewTeams.getTblTeams().setAutoCreateRowSorter(true);
    }

    private void searchFieldSettings() {

        if (!frmViewRaces.getTxtSearch().getText().equals("Search...")) {
            frmViewRaces.getTxtSearch().setText("Search...");
            frmViewRaces.getTxtSearch().setForeground(Color.gray);

        }

    }
}

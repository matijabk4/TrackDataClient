/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    }

    private void addActionListener() {

        frmViewRaces.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblRaces();
                frmViewRaces.setLocationRelativeTo(null);
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
                saveRaceResult(raceItemsForSave);
                frmViewRaces.setSize(860, 360);
                frmViewRaces.getjPanel1().setVisible(false);
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
    }

    public void openForm() {
        frmViewRaces.setLocationRelativeTo(MainCordinator.getInstance().getMainContoller().getFrmMain());
        User currentUser = (User) MainCordinator.getInstance().getParam(Constants.CURRENT_USER);
        prepareView();
        frmViewRaces.setSize(860, 360);
        frmViewRaces.getjPanel1().setVisible(false);
        frmViewRaces.getLblCU().setText(currentUser.getFirstname() + " " + currentUser.getLastname());
        frmViewRaces.setVisible(true);
    }

    private void prepareView() {
        frmViewRaces.setTitle("View races");
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
            JOptionPane.showMessageDialog(frmViewRaces, "Uspeh");
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
            items=Communication.getInstance().getRaceItems(r.getId());
        } catch (Exception ex) {
            Logger.getLogger(RaceViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public void refresh() {
        fillTblRaces();
    }

}
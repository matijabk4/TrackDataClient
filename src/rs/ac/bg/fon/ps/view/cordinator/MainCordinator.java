/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.communication.ProcessServerResponse;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.RaceController;
import rs.ac.bg.fon.ps.view.controller.RaceViewAllController;
import rs.ac.bg.fon.ps.view.controller.RiderController;
import rs.ac.bg.fon.ps.view.controller.RiderViewAllController;
import rs.ac.bg.fon.ps.view.controller.TeamController;
import rs.ac.bg.fon.ps.view.controller.TeamViewAllController;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmRace;
import rs.ac.bg.fon.ps.view.form.FrmRider;
import rs.ac.bg.fon.ps.view.form.FrmTeam;
import rs.ac.bg.fon.ps.view.form.FrmViewRaces;
import rs.ac.bg.fon.ps.view.form.FrmViewRiders;
import rs.ac.bg.fon.ps.view.form.FrmViewTeams;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author laptop-02
 */
public class MainCordinator {

    private static MainCordinator instance;
    private final MainController mainContoller;

    //this can be in some other Singleton class
    private final Map<String, Object> params;

    private MainCordinator() {
        mainContoller = new MainController(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());

        loginContoller.openForm();
    }

    public void openMainForm() {
        mainContoller.openForm();
    }

    public void openAddNewRiderForm() {
        RiderController riderController = new RiderController(new FrmRider(mainContoller.getFrmMain(), true));
        riderController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllRidersForm() {
        FrmViewRiders form = new FrmViewRiders(mainContoller.getFrmMain(), true);

        RiderViewAllController riderViewAllController = new RiderViewAllController(form);
        riderViewAllController.openForm();
    }

    public void openRiderDetailsRiderForm() {
        FrmRider riderDetails = new FrmRider(mainContoller.getFrmMain(), true);
        RiderController riderController = new RiderController(riderDetails);
        riderController.openForm(FormMode.FORM_VIEW);
    }

    public void openRiderAddRiderForm() {
        FrmRider riderAdd = new FrmRider(mainContoller.getFrmMain(), true);
        RiderController riderController = new RiderController(riderAdd);
        riderController.openForm(FormMode.FORM_ADD);
    }

    public void openAddNewTeamForm() {
        FrmTeam teamAdd = new FrmTeam(mainContoller.getFrmMain(), true);
        TeamController teamController = new TeamController(teamAdd);
        teamController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllTeamsForm() {
        FrmViewTeams form = new FrmViewTeams(mainContoller.getFrmMain(), true);

        TeamViewAllController teamViewAllController = new TeamViewAllController(form);
        teamViewAllController.openForm();
    }

    public void openTeamAddTeamForm() {
        FrmTeam teamAdd = new FrmTeam(mainContoller.getFrmMain(), true);
        TeamController teamController = new TeamController(teamAdd);
        teamController.openForm(FormMode.FORM_ADD);
    }

    public void openTeamDetailsTeamForm() {
        FrmTeam teamDetails = new FrmTeam(mainContoller.getFrmMain(), true);
        TeamController teamController = new TeamController(teamDetails);
        teamController.openForm(FormMode.FORM_VIEW);
    }

    public void openAddNewRaceForm() {
        FrmRace raceAdd = new FrmRace(mainContoller.getFrmMain(), true);
        RaceController raceController = new RaceController(raceAdd);
        raceController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllRacesForm() {
        FrmViewRaces form = new FrmViewRaces(mainContoller.getFrmMain(), true);

        RaceViewAllController raceViewAllController = new RaceViewAllController(form);
        raceViewAllController.openForm();
    }

    public MainController getMainContoller() {
        return mainContoller;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public void openRaceDetailsRiderForm() {
        FrmRace raceDetails = new FrmRace(mainContoller.getFrmMain(), true);
        RaceController raceController = new RaceController(raceDetails);
        raceController.openForm(FormMode.FORM_VIEW);
    }


}

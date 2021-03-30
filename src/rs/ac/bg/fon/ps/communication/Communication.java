/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.domain.Race;
import rs.ac.bg.fon.ps.domain.RaceItem;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.Rider;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.controller.MainController;

/**
 *
 * @author Matija
 */
public class Communication {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);

    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(Operation.LOGIN, user, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getResult() == Operation.LOGOUT_ALL){
            socket.close();
            MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addRider(Rider rider) throws Exception {
        Request request = new Request(Operation.ADD_RIDER, rider, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            Rider responseRider = (Rider) response.getResult();
            rider.setID(responseRider.getID());
        } else {
            throw response.getException();
        }
    }

    public List<Rider> getAllRiders() throws Exception {
        Request request = new Request(Operation.GET_ALL_RIDERS, null, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
            MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            return (List<Rider>) response.getResult();

        } else {
            throw response.getException();
        }
    }

    public void deleteRider(Rider r) throws Exception {
        Request request = new Request(Operation.DELETE_RIDER, r, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }

    }

    public void editRider(Rider rider) throws Exception {
        Request request = new Request(Operation.EDIT_RIDER, rider, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void addTeam(RacingTeam team) throws Exception {
        Request request = new Request(Operation.ADD_TEAM, team, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<RacingTeam> getAllTeams() throws Exception {
        Request request = new Request(Operation.GET_ALL_TEAMS, null, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            return (List<RacingTeam>) response.getResult();

        } else {
            throw response.getException();
        }
    }

    public void deleteTeam(RacingTeam team) throws Exception {
        Request request = new Request(Operation.DELETE_TEAM, team, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void editTeam(RacingTeam team) throws Exception {
        Request request = new Request(Operation.EDIT_TEAM, team, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void addRace(Race race) throws Exception {
        Request request = new Request(Operation.ADD_RACE, race, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            Race newRace = (Race) response.getResult();
            race.setId(newRace.getId());
        } else {
            throw response.getException();
        }
    }

    public List<Race> getAllRaces() throws Exception {
        Request request = new Request(Operation.GET_ALL_RACES, null, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            return (List<Race>) response.getResult();

        } else {
            throw response.getException();
        }
    }

    public List<RaceItem> getRaceItems(int raceId) throws Exception {
        Request request = new Request(Operation.GET_RACE_ITEMS, raceId, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {
            return (List<RaceItem>) response.getResult();

        } else {
            throw response.getException();
        }
    }

    public void saveRaceResult(List<RaceItem> raceItemsForSave) throws Exception {

        Request request = new Request(Operation.SAVE_RACE_RESULT, raceItemsForSave, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void updateRaceInfo(int id) throws Exception {
        Request request = new Request(Operation.UPDATE_RACE_INFO, id, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void editRace(Race race) throws Exception {
        Request request = new Request(Operation.EDIT_RACE, race, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void editRaceItems(Race race, List<RaceItem> items) throws Exception {
        Request request = new Request(Operation.EDIT_RACE_ITEMS, race, items);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void logout(User user) throws Exception {
        Request request = new Request(Operation.LOGOUT,user,null);
        sender.send(request);
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
            MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
             
        }
        if (response.getException() == null) {
            
        } else {
            throw response.getException();
        }
        
    }

    public void deleteRace(Race race) throws Exception{
        Request request = new Request(Operation.DELETE_RACE, race, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
         if(response.getResult()==Operation.LOGOUT_ALL){
            socket.close();
             MainController.getInstance().shutdown();
            throw new Exception("Server shut down. Goodbye!");
        }
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void deleteRaceItems(int raceID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

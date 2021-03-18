/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.view.controller.MainController;

/**
 *
 * @author Matija
 */
public class ProcessServerResponse extends Thread{
boolean kraj = false;
Socket socket;
 Receiver receiver;

    public ProcessServerResponse(Socket socket) {
        this.socket = socket;
        receiver = new Receiver(socket);
    }
 
    @Override
    public void run() {
        while(!kraj){
            try {
                Response response = (Response) receiver.receive();
                if(response.getResult()==Operation.LOGOUT_ALL){
                   // MainController.getInstance().shutdown();
                }
            } catch (Exception ex) {
                Logger.getLogger(ProcessServerResponse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

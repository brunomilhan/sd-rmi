package model;

import gui_controller.Controller;
import rmi_interfaces.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 05/10/16.
 */
public class Client extends UnicastRemoteObject implements ClientInterface {

    public Client() throws RemoteException {
        super();
    }

    public void notifyBookAvailable(String bookName) throws RemoteException {
        Controller controller = new Controller();
        controller.notifyReserveAvailable(bookName);
    }
}

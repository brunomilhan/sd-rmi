package model;

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
        System.out.println("reservou"+bookName);
    }
}

package rmi_class;

import rmi_interfaces.ClientInterface;

import java.rmi.RemoteException;

/**
 * Created by Bruno on 07/10/2016.
 */
public class Client implements ClientInterface {
    private String name;

    public void notifyBookAvailable(Book book) throws RemoteException {

    }
}

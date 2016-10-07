package model;

import rmi_class.Book;
import rmi_interfaces.ClientInterface;

import java.rmi.RemoteException;

/**
 * Created by bruno on 05/10/16.
 */
public class Client implements ClientInterface {

    public void notifyBookAvailable(Book book) throws RemoteException {

    }
}

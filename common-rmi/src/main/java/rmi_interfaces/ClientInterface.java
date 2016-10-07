package rmi_interfaces;

import rmi_class.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by bruno on 05/10/16.
 */
public interface ClientInterface extends Remote {
    void notifyBookAvailable(Book book) throws RemoteException;
}

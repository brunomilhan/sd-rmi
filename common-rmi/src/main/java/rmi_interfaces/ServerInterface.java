package rmi_interfaces;

import rmi_class.BookInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public interface ServerInterface extends Remote {
    List<BookInterface> listBooks() throws RemoteException;
}

package rmi_interfaces;

import rmi_class.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public interface ServerInterface extends Remote {
    List<String> listBooks() throws RemoteException;
    boolean lend(String clientName, String bookName) throws RemoteException;
    boolean renew(String clientName, String bookName) throws RemoteException;
}

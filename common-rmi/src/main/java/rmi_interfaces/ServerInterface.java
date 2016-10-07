package rmi_interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public interface ServerInterface extends Remote {
    List<String> listBooks() throws RemoteException;
    boolean lend(String clientName, String bookName) throws RemoteException;
    boolean renew(String clientName, String bookName) throws RemoteException;
    boolean returnBook(String clientName, String bookName) throws RemoteException;
    boolean reserve(String clientName, String bookName, ClientInterface clientInterface,
                    Date date2Expire) throws RemoteException;
}

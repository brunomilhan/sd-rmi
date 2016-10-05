package hello_word;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by bruno on 28/09/16.
 */
public interface InterfaceCliente extends Remote {
    public void echo(String string) throws RemoteException;
}

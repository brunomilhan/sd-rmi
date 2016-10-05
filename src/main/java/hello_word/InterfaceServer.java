package hello_word;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by bruno on 28/09/16.
 */
public interface InterfaceServer extends Remote {
    public void chamar(String nomeCliente, InterfaceCliente cliente) throws RemoteException;
}

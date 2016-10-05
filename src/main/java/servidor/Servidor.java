package servidor;

import hello_word.InterfaceCliente;
import hello_word.InterfaceServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 28/09/16.
 */
public class Servidor extends UnicastRemoteObject implements InterfaceServer {
    public Servidor() throws RemoteException {
    }

    public void chamar(String nomeCliente, InterfaceCliente cliente) {
        try {
            cliente.echo(nomeCliente);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

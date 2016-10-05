package cliente;

import hello_word.InterfaceCliente;
import hello_word.InterfaceServer;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 28/09/16.
 */
public class Cliente extends UnicastRemoteObject implements InterfaceCliente {
    private String name;
    InterfaceServer interfaceServer;
    Registry registry;

    public Cliente(String name) throws RemoteException {
        super();
        this.name = name;
        getRegistry();

    }

    private void getRegistry() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 8080);
            interfaceServer = (InterfaceServer) registry.lookup("InterfaceServer");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void callChamar() {
        try {
            interfaceServer.chamar(name, this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void echo(String string) {
        System.out.println(string);
    }
}

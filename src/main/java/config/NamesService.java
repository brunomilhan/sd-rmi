package config;

import hello_word.InterfaceServer;
import servidor.Servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by bruno on 28/09/16.
 */
public class NamesService {
    private static final int port = 8080;
    private Registry registry;
    private Servidor servidor;

    public NamesService(){
        try {
            servidor = new Servidor();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setRegistry(){
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindReference(){
        try {
            registry.bind("InterfaceServer", servidor);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}

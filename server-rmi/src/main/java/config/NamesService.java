package config;

import model.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Utilizado para registrar a interface do servidor...
 * Created by bruno on 28/09/16.
 */
public class NamesService {
    private static final int port = 8080;
    private Registry registry;

    public void setRegistry(){
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindReference(Server server){
        try {
            registry.bind("ServerInterface", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}

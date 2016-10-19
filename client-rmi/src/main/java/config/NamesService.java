package config;

import rmi_interfaces.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by bruno on 05/10/16.
 * Usado apenas para retornar a instancia do servidor
 */
public class NamesService {
    private Registry registry;

    public ServerInterface getServerRegistry() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 8080);
            return (ServerInterface) registry.lookup("ServerInterface");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

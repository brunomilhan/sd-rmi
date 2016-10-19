package main_test;

import config.NamesService;
import model.Server;

import java.rmi.RemoteException;

/**
 * Sobe o servidor
 * Created by bruno on 05/10/16.
 */
public class MainTest {
    public static void main(String []args){
        Server server = null;
        try {
             server = new Server();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        NamesService namesService = new NamesService();
        namesService.setRegistry();
        namesService.bindReference(server);

        while (true){

        }
    }

}

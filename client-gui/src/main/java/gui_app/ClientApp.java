package gui_app;

import config.NamesService;
import gui_controller.Controller;
import model.Client;
import rmi_interfaces.ClientInterface;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;

/**
 * Created by Bruno on 08/10/2016.
 */
public class ClientApp {
    private ClientInterface clientInterface;
    private Client client;
    private ServerInterface serverInterface;
    private NamesService namesService;
    private Controller controller;

    public ClientApp() {
        getInterface();
        initClientInterface();
        controller = new Controller(serverInterface, clientInterface);
    }

    private void initClientInterface() {
        try {
            client = new Client();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        clientInterface = client;
    }

    public void initClientGUI() {
        controller.initGUI();
    }

    private void getInterface() {
        namesService = new NamesService();
        serverInterface = namesService.getServerRegistry();
    }
}

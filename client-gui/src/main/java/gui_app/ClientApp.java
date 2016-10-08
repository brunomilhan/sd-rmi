package gui_app;

import config.NamesService;
import gui_controller.Controller;
import rmi_interfaces.ServerInterface;

/**
 * Created by Bruno on 08/10/2016.
 */
public class ClientApp {
    private ServerInterface serverInterface;
    private NamesService namesService;
    private Controller controller;

    public ClientApp() {
        getInterface();
        controller = new Controller(serverInterface);
    }

    public void initClientGUI() {
        controller.initGUI();
    }

    private void getInterface() {
        namesService = new NamesService();
        serverInterface = namesService.getServerRegistry();
    }
}

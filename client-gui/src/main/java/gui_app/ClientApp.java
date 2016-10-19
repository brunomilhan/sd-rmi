package gui_app;

import config.NamesService;
import gui_controller.Controller;
import model.Client;
import rmi_interfaces.ClientInterface;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;

/**
 * Encapsula a lógica que será executada no método Main.
 * Created by Bruno on 08/10/2016.
 */
public class ClientApp {
    private ClientInterface clientInterface;
    private Client client;
    private ServerInterface serverInterface;
    private NamesService namesService;
    private Controller controller;

    /**
     * O construtor primeiro irá pegar a instancia do servidor.
     * Logo em seguida vai implementar a interface do cliente.
     * E por ultimo vai enviar para o controller essas duas instancias.
     * O Controller é o responsavel por invocar os métodos disponiveis na view (swing gui).
     * @see Controller
     */
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

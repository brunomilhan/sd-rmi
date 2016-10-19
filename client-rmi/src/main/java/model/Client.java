package model;

import gui_controller.Controller;
import rmi_interfaces.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 05/10/16.
 * Implementação da interface cliente
 */
public class Client extends UnicastRemoteObject implements ClientInterface {

    public Client() throws RemoteException {
        super();
    }

    /**
     * Notifica o cliente se o livro que ele registrou interesse esta disponivel.
     * O controller está no módulo client-gui
     */
    public void notifyBookAvailable(String bookName) throws RemoteException {
        Controller controller = new Controller();
        controller.notifyReserveAvailable(bookName);
    }
}

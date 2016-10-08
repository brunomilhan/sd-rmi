package gui_controller;

import rmi_interfaces.ServerInterface;
import gui_view_swing.MainFrame;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 * Created by Bruno on 08/10/2016.
 */
public class Controller {
    private JFrame jFrame = new JFrame("Java RMI");
    private ServerInterface serverInterface;
    private MainFrame mainFrame;

    public Controller(ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
        mainFrame = new MainFrame();
        mainFrame.setController(this);
    }

    public void initGUI() {
        jFrame.setContentPane(mainFrame.getMainPanel());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void list() {
        try {
            serverInterface.listBooks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}

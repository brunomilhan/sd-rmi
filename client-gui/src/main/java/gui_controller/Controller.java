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

    private DefaultListModel availableListModel;
    private DefaultListModel loansListModel;

    private String clientName;
    private boolean isBooksListed;

    public Controller(ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
        mainFrame = new MainFrame();
        mainFrame.setController(this);

        availableListModel = new DefaultListModel();
        loansListModel = new DefaultListModel();
        mainFrame.setAvailableListModel(availableListModel);
        mainFrame.setLoansListModel(loansListModel);
    }

    public void initGUI() {
        initName();
        jFrame.setContentPane(mainFrame.getMainPanel());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void initName() {
        clientName = JOptionPane.showInputDialog("Digite seu nome");
    }

    public void list() {
        try {
            serverInterface.listBooks();
            mainFrame.enableLoansBtns();
            isBooksListed = true;
            for (String s : serverInterface.listBooks()) {
                availableListModel.addElement(s);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void lend(String bookSelected) {
        boolean ok = false;
        if (isBooksListed)
            try {
                ok = serverInterface.lend(clientName, bookSelected);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        if (ok)
            loansListModel.addElement(bookSelected);
    }

}

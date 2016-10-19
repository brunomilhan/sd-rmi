package gui_controller;

import rmi_interfaces.ClientInterface;
import rmi_interfaces.ServerInterface;
import gui_view_swing.MainFrame;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bruno on 08/10/2016.
 */
public class Controller {
    private JFrame jFrame = new JFrame("Java RMI");
    private ServerInterface serverInterface;
    private ClientInterface clientInterface;
    private MainFrame mainFrame;

    private DefaultListModel availableListModel;
    private DefaultListModel loansListModel;
    private DefaultListModel unavailableListModel;

    private String clientName;
    private boolean isBooksListed;
    private boolean isBooksLoans;
    private boolean isUnavailableListed;

    public Controller(ServerInterface serverInterface, ClientInterface clientInterface) {
        this.clientInterface = clientInterface;
        this.serverInterface = serverInterface;
        mainFrame = new MainFrame();
        mainFrame.setController(this);

        availableListModel = new DefaultListModel();
        loansListModel = new DefaultListModel();
        unavailableListModel = new DefaultListModel();

        mainFrame.setUnavailableListModel(unavailableListModel);
        mainFrame.setAvailableListModel(availableListModel);
        mainFrame.setLoansListModel(loansListModel);
    }

    public Controller() {

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
            availableListModel.clear();
            for (String s : serverInterface.listBooks()) {
                availableListModel.addElement(s);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void lend(String bookSelected) {
        boolean ok = false;
        if (checkBookIsSelected(bookSelected))
            if (isBooksListed)
                try {
                    ok = serverInterface.lend(clientName, bookSelected);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        if (ok) {
            availableListModel.removeElement(bookSelected);
            loansListModel.addElement(bookSelected);
            isBooksLoans = true;
            mainFrame.enableRenewReturnBtns();
            confirmDialog("Emprestado com sucesso");
        } else
            confirmDialog("Faça uma nova consulta, talvez o livro não esteja mais disponivel.");
    }

    public void renew(String bookSelected) {
        boolean ok = false;
        if (checkBookIsSelected(bookSelected))
            if (isBooksLoans)
                try {
                    ok = serverInterface.renew(clientName, bookSelected);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        if (ok) {
            confirmDialog("Renovado com sucesso");
        } else
            confirmDialog("Não pode, tem livro atrasado.");
    }

    public void returnBook(String bookSelected) {
        boolean ok = false;
        if (checkBookIsSelected(bookSelected))
            if (isBooksLoans)
                try {
                    ok = serverInterface.returnBook(clientName, bookSelected);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        if (ok) {
            loansListModel.removeElement(bookSelected);
            confirmDialog("Devolvido com sucesso");
        }
    }

    public void listUnavailableBooks() {
        try {
            serverInterface.listUnavailableBooks();
            mainFrame.enableReserveBtn();
            isUnavailableListed = true;
            unavailableListModel.clear();
            for (String s : serverInterface.listUnavailableBooks()) {
                unavailableListModel.addElement(s);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void reserve(String bookSelected, String date) {
        // just for tests
        Date date2Expire;
        if (date != null) {
            long inMillis = TimeUnit.SECONDS.toMillis(Long.parseLong(date));
            date2Expire = new Date(System.currentTimeMillis() + inMillis);
        } else
            date2Expire = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));

        boolean ok = false;
        if (checkBookIsSelected(bookSelected))
            if (isUnavailableListed)
                try {
                    ok = serverInterface.reserve(clientName, bookSelected, clientInterface, date2Expire);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        if (ok) {
            unavailableListModel.removeElement(bookSelected);
            confirmDialog("Reservado com sucesso");
        }
    }

    public void notifyReserveAvailable(String bookName) {
        confirmDialog("O livro: " + bookName + " que você reservou já esta disponivel");
    }

    private boolean checkBookIsSelected(String bookSelected) {
        if (bookSelected == null) {
            confirmDialog("Você precisa selecionar o livro antes");
            return false;
        }
        return true;
    }

    private void confirmDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}

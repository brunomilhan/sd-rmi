package gui_view_swing;

import gui_controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bruno on 08/10/2016.
 */
public class MainFrame {
    private JPanel panelBtns;
    private JPanel panelInfos;
    private JButton btnList;
    private JButton btnBorrow;
    private JButton btnRenew;
    private JButton btnReturn;
    private JButton btnReserve;
    private JList listLoans;
    private JPanel mainPanel;
    private JLabel labelMethods;
    private JLabel labelAvailable;
    private JList listAvailable;
    private JLabel labelLoans;

    private Controller controller;

    private String bookSelected;

    public MainFrame() {
        btnBorrow.setEnabled(false);
        btnReserve.setEnabled(false);

        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.list();
            }
        });

        btnBorrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.lend(bookSelected);
            }
        });

        btnRenew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnReserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        listAvailable.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                bookSelected = listAvailable.getSelectedValue().toString();
                System.out.println("teste " + bookSelected);
            }
        });
    }

    public void enableLoansBtns(){
        btnBorrow.setEnabled(true);
        btnReserve.setEnabled(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setAvailableListModel(DefaultListModel model) {
        listAvailable.setModel(model);
    }

    public void setLoansListModel(DefaultListModel model) {
        listLoans.setModel(model);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

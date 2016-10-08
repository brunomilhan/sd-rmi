package swing_gui;

import javax.swing.*;
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
    private JList list1;
    private JPanel mainPanel;
    private JLabel labelMethods;
    private JLabel labelAvailable;
    private JList list2;
    private JLabel labelLoans;

    public MainFrame() {
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnBorrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

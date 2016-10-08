package swing_gui;

import javax.swing.*;

/**
 * Created by Bruno on 07/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Java RMI");
        jFrame.setContentPane(new MainFrame().getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}

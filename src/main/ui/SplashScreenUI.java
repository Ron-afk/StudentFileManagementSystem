package ui;

import javax.swing.*;
import java.awt.*;

/** This class creates a splash window for welcome
 *
 */
public class SplashScreenUI {
    private ImageIcon initIcon = new ImageIcon(
            new ImageIcon("./img/initIcon.JPG")
                    .getImage()
                    .getScaledInstance(500,500, Image.SCALE_DEFAULT));

    private JWindow window;

    // EFFECTS: Create a splash window with welcome information and an image icon
    public SplashScreenUI() {
        window = new JWindow();
        JLabel label = new JLabel("Welcome!");
        label.setIcon(initIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("MV Boli",Font.BOLD,20));

        window.getContentPane().add(label,BorderLayout.CENTER);
        window.setSize(600,600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.setVisible(false);
        window.dispose();
    }
}

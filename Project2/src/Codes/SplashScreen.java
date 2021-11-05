package Codes;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    JFrame splashScreen;
    ImageIcon logo;
    protected void createSplashScreen(){
        //Image
        logo = new ImageIcon("images/logo1.png");
        JLabel welcomeLogo = new JLabel(logo);

        //Frame
        splashScreen = new JFrame();
        splashScreen.add(welcomeLogo);
        splashScreen.setUndecorated(true);
        splashScreen.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
        splashScreen.setSize(1300,500);
        splashScreen.setResizable(false);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        splashScreen.setLayout(new CardLayout());
        splashScreen.setVisible(true);

        //Delay using Thread
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.setVisible(false);
    }
}

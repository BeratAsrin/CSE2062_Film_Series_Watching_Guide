package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntranceScreen implements ActionListener {
    static JFrame entranceFrame = new JFrame("FilmBOX");
    JButton signInButton, signUpButton;
    static JTextField userName,userPassword;
    public void entranceScreenCreater(){

        //UIManager
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //Login Logo
        ImageIcon logo = new ImageIcon("images/logo1.png");
        Image logoReSized = logo.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
        logo = new ImageIcon(logoReSized);
        JLabel logoLabel = new JLabel(logo);
        entranceFrame.add(logoLabel);

        //Username Panel
        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(new FlowLayout());
        entranceFrame.add(userNamePanel);

        //Username Label
        JLabel userNameLabel = new JLabel("Username:");
        userNamePanel.add(userNameLabel);

        //Username Textfield
        userName = new JTextField();
        userName.setPreferredSize(new Dimension(250,20));
        userNamePanel.add(userName);

        //Password Panel
        JPanel userPasswordPanel = new JPanel();
        userPasswordPanel.setLayout(new FlowLayout());
        entranceFrame.add(userPasswordPanel);

        //Password Label
        JLabel userPasswordLabel = new JLabel("Password:");
        userPasswordPanel.add(userPasswordLabel);

        //Password Textfield
        userPassword = new JPasswordField();
        userPassword.setPreferredSize(new Dimension(250,20));
        userPasswordPanel.add(userPassword);

        //Button Panel
        JPanel entrancePanel = new JPanel();
        entrancePanel.setLayout(new FlowLayout());
        entranceFrame.add(entrancePanel);

        //Sign up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);
        entrancePanel.add(signUpButton);

        //Sign in Button
        signInButton = new JButton("Sign In");
        signInButton.addActionListener(this);
        entrancePanel.add(signInButton);

        //Frame
        entranceFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        entranceFrame.setLayout(new GridLayout(4,1));
        entranceFrame.setSize(300,250);
        entranceFrame.setLocationRelativeTo(null);
        entranceFrame.setResizable(false);
        entranceFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==  signInButton){
            boolean checkFile;
            checkFile = SignInUserFile.searchUser(userName.getText(),userPassword.getText());
            if(checkFile){
                entranceFrame.setVisible(false);
                ApplicationScreen applicationScreen = new ApplicationScreen();
                applicationScreen.applicationScreenCreater(userName.getText());
            }

        }
        else if(e.getSource() == signUpButton){
            entranceFrame.setVisible(false);
            SignUpScreen signUpScreen = new SignUpScreen();
            signUpScreen.signUpScreenCreater();
        }
    }

}

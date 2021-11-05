package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class SignUpScreen extends WindowAdapter implements ActionListener {
    JFrame signUpFrame = new JFrame("Sign Up");
    JButton signUpButton, backToEntrenceButton;
    JPasswordField passwordPasswordField, passwordConfirmPasswordField;
    JTextField nameTextField, surnameTextField, usernameTextField, mailTextField;
    ButtonGroup genderGroup;
    JRadioButton maleRadioButton,femaleRadioButton;
    JComboBox <String> ageCombobox;

    public void signUpScreenCreater(){
        //SignUp Logo Label
        ImageIcon logo = new ImageIcon("images/logo1.png");
        Image logoReSized = logo.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
        logo = new ImageIcon(logoReSized);
        JLabel logoLabel = new JLabel(logo);
        signUpFrame.add(logoLabel);

        //Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        //Name TextField
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(250,20));

        //Name Panel
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        signUpFrame.add(namePanel);

        //Surname Label
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setVerticalAlignment(JLabel.CENTER);
        surnameLabel.setHorizontalAlignment(JLabel.CENTER);

        //Surname TextField
        surnameTextField = new JTextField();
        surnameTextField.setPreferredSize(new Dimension(250,20));

        //Surname Panel
        JPanel surnamePanel = new JPanel();
        surnamePanel.setLayout(new FlowLayout());
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameTextField);
        signUpFrame.add(surnamePanel);

        //Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);

        //Username textfield
        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(250,20));

        //Username input panel
        JPanel usernameInput = new JPanel();
        usernameInput.add(usernameLabel);
        usernameInput.add(usernameTextField);
        usernameInput.setLayout(new FlowLayout());
        signUpFrame.add(usernameInput);

        //Gender RadioButton
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        //GenderPanel
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout());
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        signUpFrame.add(genderPanel);

        //Age Panel
        String[] ages = new String[99];
        for (int i=1;i<100;i++){
            ages[i-1] = Integer.toString(i);
        }
        ageCombobox = new JComboBox<>(ages);
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setVerticalAlignment(JLabel.CENTER);
        ageLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel agePanel = new JPanel();
        agePanel.setLayout(new FlowLayout());
        agePanel.add(ageLabel);
        agePanel.add(ageCombobox);
        signUpFrame.add(agePanel);

        //Mail Label
        JLabel mailLabel = new JLabel("Mail:");
        mailLabel.setVerticalAlignment(JLabel.CENTER);
        mailLabel.setHorizontalAlignment(JLabel.CENTER);

        //Mail TextField
        mailTextField = new JTextField();
        mailTextField.setPreferredSize(new Dimension(250,20));

        //Mail Panel
        JPanel mailPanel = new JPanel();
        mailPanel.setLayout(new FlowLayout());
        mailPanel.add(mailLabel);
        mailPanel.add(mailTextField);
        signUpFrame.add(mailPanel);

        //Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        //Password TextField
        passwordPasswordField = new JPasswordField();
        passwordPasswordField.setPreferredSize(new Dimension(250,20));

        //Password Confirm Label
        JLabel passwordConfirmLabel = new JLabel("Password Confirm:");
        passwordConfirmLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmLabel.setVerticalAlignment(JLabel.CENTER);

        //Password Confirm TextField
        passwordConfirmPasswordField = new JPasswordField();
        passwordConfirmPasswordField.setPreferredSize(new Dimension(250,20));

        //Password Panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordPasswordField);
        signUpFrame.add(passwordPanel);

        //Password Confirm Panel
        JPanel passwordConfirmPanel = new JPanel();
        passwordConfirmPanel.add(passwordConfirmLabel);
        passwordConfirmPanel.add(passwordConfirmPasswordField);
        signUpFrame.add(passwordConfirmPanel);

        //Buttons
        signUpButton = new JButton("Sign Up");
        backToEntrenceButton = new JButton("Back to Login");
        signUpButton.addActionListener(this);
        backToEntrenceButton.addActionListener(this);

        //Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backToEntrenceButton);
        buttonPanel.add(signUpButton);
        signUpFrame.add(buttonPanel);

        //Frame
        signUpFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        signUpFrame.addWindowListener(this);
        signUpFrame.setLayout(new GridLayout(10,1));
        signUpFrame.setSize(275,600);
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setResizable(false);
        signUpFrame.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int selection = JOptionPane.showConfirmDialog(signUpFrame,"Are you sure?");
        if(selection == JOptionPane.YES_OPTION){
            signUpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        else if(selection == JOptionPane.NO_OPTION){
            signUpFrame.setVisible(false);
            EntranceScreen.entranceFrame.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToEntrenceButton){
            signUpFrame.setVisible(false);
            EntranceScreen.userName.setText("");
            EntranceScreen.userPassword.setText("");
            EntranceScreen.entranceFrame.setVisible(true);
        }
        else if(e.getSource() == signUpButton){
            if(new String(passwordConfirmPasswordField.getPassword()).equals(new String(passwordPasswordField.getPassword())) && checkString(usernameTextField.getText())
            && checkString(nameTextField.getText()) && checkString(surnameTextField.getText()) && checkString(mailTextField.getText())
            && checkString(new String(passwordPasswordField.getPassword())) && checkString(new String(passwordConfirmPasswordField.getPassword())) && checkRadioButton()){
                if(SearchUser.searchUser(usernameTextField.getText())){
                    JOptionPane.showMessageDialog(signUpFrame,"This username is already taken.","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                writeToUserInformations();
                signUpFrame.setVisible(false);
                EntranceScreen.userName.setText("");
                EntranceScreen.userPassword.setText("");
                EntranceScreen.entranceFrame.setVisible(true);

            }
            else{
                if(!checkString(new String(passwordPasswordField.getPassword())) || !checkString(new String(passwordConfirmPasswordField.getPassword()))){
                    JOptionPane.showMessageDialog(signUpFrame,"Fill the blank areas.","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else if(!(new String(passwordConfirmPasswordField.getPassword()).equals(new String(passwordPasswordField.getPassword())))){
                    JOptionPane.showMessageDialog(signUpFrame,"Passwords are not matched.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(signUpFrame,"Fill the blank areas.","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }

        }
    }

    private boolean checkString(String toCheck){
        return !toCheck.equals("");
    }

    private boolean checkRadioButton(){
        return maleRadioButton.isSelected() || femaleRadioButton.isSelected();
    }

    private void writeToUserInformations(){
        try {
            String dataToWrite = "\n" + nameTextField.getText() + ":" + surnameTextField.getText() + ":" + usernameTextField.getText() +":"
                    + (maleRadioButton.isSelected() ? "Male" : "Female") + ":" + ageCombobox.getSelectedItem() + ":" + mailTextField.getText() + ":" +
                    new String(passwordPasswordField.getPassword());
            FileWriter fileToWrite = new FileWriter("userfiles/userinformations.txt",true);
            fileToWrite.append(dataToWrite);
            fileToWrite.close();
            System.out.println(usernameTextField.getText()+" is added to userinformations.");
        }catch (Exception error) {
            error.printStackTrace();
        }
    }
}
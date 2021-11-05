package Codes;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignInUserFile {
    static public boolean searchUser(String usernameInput, String passwordInput){
        try{
            File searchFile = new File("userfiles/userinformations.txt");
            Scanner readFile = new Scanner(searchFile);
            while(readFile.hasNextLine() && !usernameInput.equals("") && !passwordInput.equals("")){
                String data = readFile.nextLine();
                String[] splittedString = data.split(":");
                if(splittedString[2].equals(usernameInput) && splittedString[6].equals(passwordInput)){
                    return true;
                }
            }
            if(usernameInput.equals("") || passwordInput.equals("")){
                JOptionPane.showMessageDialog(EntranceScreen.entranceFrame,"Enter your username and password!!!","Warning",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
        JOptionPane.showMessageDialog(EntranceScreen.entranceFrame,"Could not find the user!!!","Error",JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

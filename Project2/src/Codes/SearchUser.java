package Codes;

import java.io.File;
import java.util.Scanner;

public class SearchUser {
    static public boolean searchUser(String username){
        try{
            File file = new File("userfiles/userinformations.txt");
            Scanner scanner = new Scanner(file);
            String[] information;
            String line;
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                information = line.split(":");
                if(username.equals(information[2])){
                    return true;
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }
}

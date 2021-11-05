package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Inside of application after logining in
public class ApplicationScreen extends Container implements ActionListener {
    JFrame applicationScreen = new JFrame("FilmBOX Application");
    JButton signOutButton = new JButton("Sign Out");
    JButton accountDetailsButton = new JButton("Account Details");
    JButton searchButton;
    String loginedUser = "";
    JTextField searchTextField;
    ArrayList <JButton> categories = new ArrayList<>();
    static ArrayList <JPanel> categorycontentPanels = new ArrayList<>();

    // Frame creater method of application
    public void applicationScreenCreater(String loginedUser){
        this.loginedUser = loginedUser;
        //Search Panel
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(800,23));
        ImageIcon searchImageIcon = new ImageIcon("images/search.png");
        Image searchImage = searchImageIcon.getImage().getScaledInstance(13,13, Image.SCALE_SMOOTH);
        searchImageIcon = new ImageIcon(searchImage);

        searchButton = new JButton("Search",searchImageIcon);
        searchButton.addActionListener(this);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchPanel.setLayout(new FlowLayout());

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(searchPanel,BorderLayout.NORTH);
        applicationScreen.add(centerPanel,BorderLayout.CENTER);

        //Categories
        categories.add(new JButton("Home Page"));
        categories.add(new JButton("Action"));
        categories.add(new JButton("Science Fiction"));
        categories.add(new JButton("Romantic"));
        categories.add(new JButton("Comedy"));
        categories.add(new JButton("Documentary"));
        categories.add(new JButton("Horror"));
        categories.add(new JButton("Thriller"));
        categories.add(new JButton("Animation"));
        categories.add(new JButton("Fantastic"));
        categories.add(new JButton("Adventure"));
        categories.add(accountDetailsButton);
        categories.add(signOutButton);

        //Logo Label
        ImageIcon logo = new ImageIcon("images/logo1.png");
        Image logoReSized = logo.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
        logo = new ImageIcon(logoReSized);
        JLabel logoLabel = new JLabel(logo);

        //Category Panel
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(14,1));
        categoryPanel.add(logoLabel);
        for (JButton temp: categories) {
            categoryPanel.add(temp);
            temp.addActionListener(this);
        }

        applicationScreen.add(categoryPanel,BorderLayout.WEST);

        //Category Panels
        int i;
        for (i=0;i<11;i++){
            categorycontentPanels.add(new JPanel());
        }

        //Category Layout Setups
        for (JPanel temp: categorycontentPanels){
            temp.removeAll();
        }

        homePageCreator();
        showContent("action",1);
        showContent("sciencefiction",2);
        showContent("romantic",3);
        showContent("comedy",4);
        showContent("documentary",5);
        showContent("horror",6);
        showContent("thriller",7);
        showContent("animation",8);
        showContent("fantastic",9);
        showContent("adventure",10);
        JPanel contentShowPanel = new JPanel();
        contentShowPanel.setLayout(new CardLayout());

        contentShowPanel.add(categorycontentPanels.get(0));
        contentShowPanel.add(categorycontentPanels.get(1));
        contentShowPanel.add(categorycontentPanels.get(2));
        contentShowPanel.add(categorycontentPanels.get(3));
        contentShowPanel.add(categorycontentPanels.get(4));
        contentShowPanel.add(categorycontentPanels.get(5));
        contentShowPanel.add(categorycontentPanels.get(6));
        contentShowPanel.add(categorycontentPanels.get(7));
        contentShowPanel.add(categorycontentPanels.get(8));
        contentShowPanel.add(categorycontentPanels.get(9));
        contentShowPanel.add(categorycontentPanels.get(10));
        centerPanel.add(contentShowPanel,BorderLayout.CENTER);

        //Frame
        applicationScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        applicationScreen.setSize(1300,800);
        applicationScreen.setMinimumSize(new Dimension(1300,800));
        applicationScreen.setLocationRelativeTo(null);
        applicationScreen.setVisible(true);
    }

    // The panel that is used to get random content
    private void homePageCreator() { // Home Page designation
        categorycontentPanels.get(0).setLayout(new BorderLayout());

        //North Panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        //North Panel of North Panel
        JPanel northOfNorthPanel = new JPanel();
        northOfNorthPanel.setLayout(new FlowLayout());

        //RadioButtons
        ArrayList <JRadioButton> categoryRadios = new ArrayList<>();
        ButtonGroup radioButtonGroup = new ButtonGroup();
        for(int i=0;i<10;i++){
            switch (i){
                case 0 -> categoryRadios.add(new JRadioButton("Action"));
                case 1 -> categoryRadios.add(new JRadioButton("Science Fiction"));
                case 2 -> categoryRadios.add(new JRadioButton("Romantic"));
                case 3 -> categoryRadios.add(new JRadioButton("Comedy"));
                case 4 -> categoryRadios.add(new JRadioButton("Documentary"));
                case 5 -> categoryRadios.add(new JRadioButton("Horror"));
                case 6 -> categoryRadios.add(new JRadioButton("Thriller"));
                case 7 -> categoryRadios.add(new JRadioButton("Animation"));
                case 8 -> categoryRadios.add(new JRadioButton("Fantastic"));
                case 9 -> categoryRadios.add(new JRadioButton("Adventure"));
            }
            radioButtonGroup.add(categoryRadios.get(i));
            northOfNorthPanel.add(categoryRadios.get(i));
        }
        JLabel categoryLabel = new JLabel("Select a Category to Get Suggestion.");
        categoryLabel.setFont(new Font("Serif", Font.BOLD, 14));
        categoryLabel.setVerticalAlignment(JLabel.CENTER);
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(categoryLabel, BorderLayout.NORTH);
        northPanel.add(northOfNorthPanel,BorderLayout.CENTER);
        JButton luckyButton = new JButton("I Feel Lucky.");
        luckyButton.addActionListener(e -> {
            int flag = 0;
            String categoryInfo="";
            for(JRadioButton temp: categoryRadios){
                if(temp.isSelected()){
                    categoryInfo = temp.getText().replaceAll(" ","").toLowerCase();
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                try {
                    String line;
                    String[] getName;
                    ArrayList <String> relatedContent = new ArrayList<>();
                    File allFile = new File("database/all/allcontent.txt");
                    Scanner scanAllFile = new Scanner(allFile);
                    while (scanAllFile.hasNextLine()){
                        line = scanAllFile.nextLine();
                        if(line.contains(categoryInfo)){
                            getName = line.split(" ");
                            relatedContent.add(getName[0]);
                        }
                    }
                    try {
                        Random randNumberGenerator = new Random();
                        int randomNumber = randNumberGenerator.nextInt(relatedContent.size());
                        ImageIcon randomContent = new ImageIcon("database/"+categoryInfo+"/"+relatedContent.get(randomNumber)+".jpg");
                        Image reSized = randomContent.getImage().getScaledInstance(300,400,Image.SCALE_SMOOTH);
                        randomContent = new ImageIcon(reSized);
                        categorycontentPanels.get(0).removeAll();
                        homePageCreator();
                        JButton contentButton = new JButton(randomContent);
                        String finalCategoryInfo = categoryInfo;
                        contentButton.addActionListener(e1 -> {
                            try {
                                String[] data;
                                data = insideofContentFile(finalCategoryInfo,relatedContent.get(randomNumber));
                                contentInformationScreen(data);
                            }catch (Exception error){
                                error.printStackTrace();
                            }

                        });
                        categorycontentPanels.get(0).add(contentButton,BorderLayout.CENTER);
                        categorycontentPanels.get(0).setVisible(false);
                        categorycontentPanels.get(0).setVisible(true);
                    }catch (Exception insideError){
                        insideError.printStackTrace();
                    }
                }catch (Exception error){
                    error.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(applicationScreen,"Please select a category.","Warning",JOptionPane.WARNING_MESSAGE);
            }
        });
        northPanel.add(luckyButton,BorderLayout.SOUTH);
        categorycontentPanels.get(0).add(northPanel, BorderLayout.NORTH);
    }

    // Action listeners of components
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton){
            EntranceScreen.userName.setText("");
            EntranceScreen.userPassword.setText("");
            applicationScreen.setVisible(false);
            EntranceScreen.entranceFrame.setVisible(true);
        }
        else if (e.getSource() == accountDetailsButton){
            showUserInformationInApplication();
        }
        else if(e.getSource() == searchButton){
            if(searchTextField.getText().equals("")){
               JOptionPane.showMessageDialog(applicationScreen,"Some input should be entered.","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else{
                JFrame searchFrame = new JFrame("Search");
                searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                try {
                    int flag=0;
                    File searchDatabase = new File("database/all/allcontent.txt");
                    Scanner scanDatabase = new Scanner(searchDatabase);
                    String[] words;
                    String[] infoOfContent = new String[2];
                    words = searchTextField.getText().split(" ");
                    loop:
                    while (scanDatabase.hasNextLine()){
                        infoOfContent = scanDatabase.nextLine().split(" ");
                        for(String temp: words){
                            if(infoOfContent[0].contains(temp)){
                                flag = 1;
                                break loop;
                            }
                        }
                    }
                    scanDatabase.close();

                    if(flag == 1){
                        try {
                            ImageIcon searchedContent = new ImageIcon("database/"+ infoOfContent[1] +"/"+ infoOfContent[0] +".jpg");
                            Image reSized = searchedContent.getImage().getScaledInstance(300,400,Image.SCALE_SMOOTH);
                            searchedContent = new ImageIcon(reSized);
                            JButton searchedContentButton = new JButton(searchedContent);
                            String[] finalInfoOfContent = infoOfContent;
                            searchedContentButton.addActionListener(e1 -> contentInformationScreen(insideofContentFile(finalInfoOfContent[1], finalInfoOfContent[0])));
                            //Frame
                            JFrame searchedContentFrame = new JFrame("Searched Content");
                            searchedContentFrame.setLayout(new BorderLayout());
                            searchedContentFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            searchedContentFrame.add(searchedContentButton,BorderLayout.CENTER);
                            JLabel infoLabel = new JLabel("Click on the image to see information.");
                            infoLabel.setFont(new Font("Serif", Font.BOLD, 14));
                            infoLabel.setHorizontalAlignment(JLabel.CENTER);
                            infoLabel.setVerticalAlignment(JLabel.CENTER);
                            searchedContentFrame.add(infoLabel,BorderLayout.NORTH);
                            searchedContentFrame.setSize(500,500);
                            searchedContentFrame.setLocationRelativeTo(null);
                            searchedContentFrame.setResizable(false);
                            searchedContentFrame.setVisible(true);


                        }catch (Exception error){
                            error.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(applicationScreen,"Could not find any content.","Info",JOptionPane.INFORMATION_MESSAGE);
                    }

                }catch (Exception error){
                    error.printStackTrace();
                }
            }
        }
        else if (e.getSource() == categories.get(0)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 0);
                i++;
            }
        }
        else if (e.getSource() == categories.get(1)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 1);
                i++;
            }
        }
        else if (e.getSource() == categories.get(2)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 2);
                i++;
            }
        }
        else if (e.getSource() == categories.get(3)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 3);
                i++;
            }
        }
        else if (e.getSource() == categories.get(4)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 4);
                i++;
            }
        }
        else if (e.getSource() == categories.get(5)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 5);
                i++;
            }
        }
        else if (e.getSource() == categories.get(6)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 6);
                i++;
            }
        }
        else if (e.getSource() == categories.get(7)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 7);
                i++;
            }
        }
        else if (e.getSource() == categories.get(8)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 8);
                i++;
            }
        }
        else if (e.getSource() == categories.get(9)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 9);
                i++;
            }
        }
        else if (e.getSource() == categories.get(10)){
            int i=0;
            while(i!=11){
                categorycontentPanels.get(i).setVisible(i == 10);
                i++;
            }
        }
    }

    // Method that is used to find entered user
    public void showUserInformationInApplication(){
        try {
            File informationFile = new File("userfiles/userinformations.txt");
            Scanner scanInformationFile = new Scanner(informationFile);
            String data;
            String[] userInformation = new String[7];
            while (scanInformationFile.hasNextLine()){
                data = scanInformationFile.nextLine();
                userInformation = data.split(":");
                if(userInformation[2].equals(loginedUser)){
                    break;
                }
            }
            userInformationFrame(userInformation);
        }catch (FileNotFoundException error) {
            error.printStackTrace();
        }
    }

    // Method that is used to create frame to show entered user's informations
    public void userInformationFrame(String[] userInformation){
        JFrame informationFrame = new JFrame("User Informations");

        //Information Labels
        ArrayList <JLabel> labels = new ArrayList<>();
        JLabel name = new JLabel("Name: " + userInformation[0]);
        JLabel surname = new JLabel("Surname: " + userInformation[1]);
        JLabel username = new JLabel("Username: " + userInformation[2]);
        JLabel gender = new JLabel("Gender: " + userInformation[3]);
        JLabel age = new JLabel("Age: " + userInformation[4]);
        JLabel mail = new JLabel("Mail: " + userInformation[5]);

        labels.add(name);
        labels.add(surname);
        labels.add(username);
        labels.add(gender);
        labels.add(age);
        labels.add(mail);

        for (JLabel temp: labels) {
            temp.setVerticalAlignment(JLabel.CENTER);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
            temp.setForeground(Color.white);
        }

        informationFrame.add(name);
        informationFrame.add(surname);
        informationFrame.add(username);
        informationFrame.add(gender);
        informationFrame.add(age);
        informationFrame.add(mail);

        //Ok Button
        ImageIcon tickImageIcon = new ImageIcon("images/tickicon.png");
        Image tickImage = tickImageIcon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        tickImageIcon = new ImageIcon(tickImage);
        JButton okButton = new JButton("OK", tickImageIcon);

        okButton.addActionListener(e -> informationFrame.setVisible(false));
        informationFrame.add(okButton);

        //Frame
        informationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        informationFrame.setSize(400,400);
        informationFrame.setLocationRelativeTo(null);
        informationFrame.setLayout(new GridLayout(7,1));
        informationFrame.getContentPane().setBackground(Color.GRAY);
        informationFrame.setResizable(false);
        informationFrame.setVisible(true);
    }

    // Method that counts the contents in related category
    private int contentCounter(String contentcategory){
        int numberOfLines=0;
        try {
            File contentIncludedInCategoryFile = new File("database/"+contentcategory+"/"+contentcategory+"content.txt");
            Scanner contentIncludedCategory = new Scanner(contentIncludedInCategoryFile);
            while (contentIncludedCategory.hasNextLine()){
                contentIncludedCategory.nextLine();
                numberOfLines++;
            }
            contentIncludedCategory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfLines;
    }

    // Method that shows the image of content on buttons
    public void showContent(String contentCategory, int index){ // all in lower
        int numberOfContent = contentCounter(contentCategory);
        categorycontentPanels.get(index).setLayout(new GridLayout((int) Math.ceil(numberOfContent/2),2));
        try{
            File contentIncludedInCategoryFile = new File("database/"+ contentCategory +"/"+ contentCategory +"content.txt");
            Scanner contentIncludedCategory = new Scanner(contentIncludedInCategoryFile);
            while(contentIncludedCategory.hasNextLine()){
                String nameOfContent = contentIncludedCategory.nextLine();
                ImageIcon contentImage = new ImageIcon("database/"+ contentCategory +"/"+nameOfContent+".jpg");
                Image resizedImage = contentImage.getImage().getScaledInstance(175,225,Image.SCALE_SMOOTH);
                contentImage = new ImageIcon(resizedImage);
                JButton newContent = new JButton(contentImage);
                newContent.addActionListener(e -> {
                    String[] contentDetails = insideofContentFile(contentCategory, nameOfContent);
                    contentInformationScreen(contentDetails);
                });
                categorycontentPanels.get(index).add(newContent);
            }
        }catch (Exception error) {
            error.printStackTrace();
        }
    }

    // Method that shows information of content when clicked on content's button
    private void contentInformationScreen(String[] contentDetails){
        ArrayList <JComponent> labels = new ArrayList<>();
        for (int i=0;i<8;i++){
            switch (i) {
                case 0 -> {
                    labels.add(new JLabel("Category: " + contentDetails[0]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 1 -> {
                    labels.add(new JLabel("Name: " + contentDetails[1]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 2 -> {
                    labels.add(new JLabel("Year: " + contentDetails[2]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 3 -> {
                    labels.add(new JLabel("Duration (Minutes): " + contentDetails[3]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 4 -> {
                    labels.add(new JLabel("IMDB Rating: " + contentDetails[4]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 5 -> {
                    labels.add(new JLabel("Director: " + contentDetails[5]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 6 -> {
                    labels.add(new JLabel("Actors/Actresses: " + contentDetails[6]));
                    ((JLabel) labels.get(i)).setHorizontalAlignment(JLabel.CENTER);
                    ((JLabel) labels.get(i)).setVerticalAlignment(JLabel.CENTER);
                }
                case 7 -> {
                    JButton trailerButton = new JButton("Watch Trailer");
                    trailerButton.addActionListener(e -> {
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v="+ contentDetails[7]));
                        }catch (Exception error){
                            error.printStackTrace();
                        }
                    });
                    labels.add(trailerButton);
                }
            }
            labels.get(i).setFont(new Font("Serif", Font.BOLD, 14));
        }

        JFrame frame = new JFrame(contentDetails[1]);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(500,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        for (JComponent temp: labels){
            frame.add(temp);
        }
        frame.setLayout(new GridLayout(8,1));
        frame.setVisible(true);
    }

    // Method that returns the information exist in related content's file.
    public String[] insideofContentFile(String contentCategory, String nameOfContent){
        String[] returnString = new String[8];
        try {
            String line;
            int i=0;
            File content = new File("database/"+ contentCategory +"/"+ nameOfContent +".txt");
            Scanner scanContent = new Scanner(content);
            while (scanContent.hasNextLine()){
                line = scanContent.nextLine();
                switch (i) {
                    case 0 -> returnString[0] = line;
                    case 2 -> returnString[1] = line;
                    case 3 -> returnString[2] = line;
                    case 4 -> returnString[3] = line;
                    case 5 -> returnString[4] = line;
                    case 6 -> returnString[5] = line;
                    case 7 -> returnString[6] = line;
                    case 8 -> returnString[7] = line;
                }
                i++;
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }
            return returnString;
    }
}

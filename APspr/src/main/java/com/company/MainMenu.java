package com.company;

public class MainMenu {



    static void mainMenu(User user){
        PersonalPage personalPage = new PersonalPage(user);
        TimeLine timeLine = new TimeLine(user);
        Explorer explorer = new Explorer(user);
        Messaging messaging = new Messaging(user);
        Settings settings = new Settings(user);
        while (true) {
            user.updateLastTime();
            System.out.println("1.personal page\n" +
                    "2.TimeLine\n" +
                    "3.Explorer\n" +
                    "4.Messaging\n" +
                    "5.Settings\n"+
                    "6.back");
            Logic.output("enter a valid number: ");
            String s = Logic.input();
            if (s.equals("1")){
                personalPage.showOptions();
            }
            else if (s.equals("2")){
                timeLine.timeLineOptions();
            }
            else if (s.equals("3")){
              explorer.explorerOptions();
            }
            else if (s.equals("4")){
                messaging.messageOptions();
            }
            else if (s.equals("5")){
                settings.settingsOption();
            }
            else if (s.equals("6")){
                return;
            }
            else
                Logic.output("enter a valid number:");

        }

    }

}

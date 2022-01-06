package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class Settings {
    private static final Logger logger = LogManager.getLogger(Settings.class);



    User user;
    public Settings(User user) {
        this.user = user;
    }

    public void settingsOption() {
        user.updateLastSeen();
            Logic.output("1.logout\n" +
                    "2.delete your account\n" +
                    "3.more\n" +
                    "4.back\n");
        while (true) {
            String s = Logic.input();
            if (s.equals("1")) {
                Logic.output("are you sure?(yes/no)");
                String ans = Logic.input();
                if (ans.equals("yes"))
                logOut();
                else
                    return;
            }
            else if (s.equals("2")){
                Logic.output("are you sure?(yes/no)");
                String ans = Logic.input();
                if (ans.equals("yes"))
                   deleteYourAccount();
                else
                    return;
            }
            else if (s.equals("3")) {
                while (1 == 1) {
                    Logic.output("1.lastSeen\n" +
                            "2.public/private\n" +
                            "3.activate/inactivate\n" +
                            "4.changes\n"+
                            "5.back");
                    String q = Logic.input();
                    if (q.equals("1")){
                        lastSeen(user.getUsername());
                        user.updateLastTime();
                    }
                    else if (q.equals("2")){
                        privacy();
                    }
                    else if (q.equals("3")){
                        Logic.output("are you sure?(yes/no)");
                        String ans = Logic.input();
                        if (ans.equals("yes"))
                            inactivate();
                        else
                            return;
                    }
                    else if (q.equals("4")){
                        while (2==2){
                            Logic.output("1.password\n"+
                                    "2.fullName\n"+
                                    "3.phoneNumber\n"+
                                    "4.email\n"+
                                    "5.lastSeen\n" +
                                    "6.permission\n"+
                                    "7.add birthdate\n"+
                                    "8.add bio\n"+
                                    "9.back");
                            String t = Logic.input();
                             if (t.equals("1")){
                                changePassword();
                            }
                            else if (t.equals("2")){
                                changeFullName();
                            }
                            else if(t.equals("3")){
                                changePhoneNumber();
                            }
                            else if (t.equals("4")){
                                changeEmail();
                            }
                            else if (t.equals("5")){
                                changeLastSeen();
                             }
                            else if (t.equals("6")){
                                changePermission();
                             }
                            else if(t.equals("7")){
                                addBirthdate();
                            }
                            else if (t.equals("8")){
                                addBio();
                            }
                            else if (t.equals("9")){
                                return;
                            }
                            else
                                Logic.output("enter a valid number:");
                        }
                    }
                    else if (q.equals("5")){
                        return;
                    }
                    else
                        Logic.output("enter a valid number:");

                }
            }
            else if (s.equals("4")){
                return;
            }
            else
                Logic.output("enter a valid number");

        }
    }

    public void logOut(){
        Logic.output("are you sure that you want to log out?(yes/no)");
        String ans = Logic.input();
        if (ans.equals("yes")){
            user.updateLastTime();
            Logic.output("you are logged out");
            user.setOnline(false);
            user.saveUser();
            logger.info(user.getUsername() + "logged out");
            Start.start();

        }
        else
            return;
    }

    public void deleteYourAccount(){
        Logic.output("are you sure that you wat to delete your account?(yes/no)");
        String s = Logic.input();
        if (s.equals("yes")){
            try {
            String path = "resources/saveUser";
                path += "/" + user.getId();
                path += ".txt";
                File file = new File(path);
                if (file.exists()){
                    Logic.output("1");
                }
                if (file.delete()) {
                    System.out.println(file.getName() + "deleted");
                    logger.info(user.getUsername() + " deleted file ");
                    System.exit(0);

                } else {
                    System.out.println("failed");
                    logger.error(" could not delete file");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            return;

    }

    public void lastSeen(String username){
        User user43 = Load.loadUser(username);
       if (user43.showLastSeen == 2){
          Logic.output("everybody");
          user43.updateLastTime();
          user43.saveUser();
       }
       else if (user43.showLastSeen == 1){
             Logic.output("followings only");
             user43.updateLastTime();
             user43.saveUser();
        }
       else {
           Logic.output("last seen recently");
       }

    }

    public void changeLastSeen(){
        Logic.output("1.everybody\n"+
                "2.followings\n" +
                "3.no one\n"+
                "4.back\n");
        while (true){
            String s = Logic.input();
            if (s.equals("1")){
                user.setShowLastSeen(2);
                user.saveUser();
                logger.info(user.getUsername() + "last seen changed");
            }
            else if (s.equals("2")){
                user.setShowLastSeen(1);
                user.saveUser();
                logger.info(user.getUsername() + "last seen changed");

            }
            else if (s.equals("3")){
                user.setShowLastSeen(0);
                user.saveUser();
                logger.info(user.getUsername() + "last seen changed");

            }
            else if (s.equals("4")){
                return;
            }
            else
                Logic.output("enter a valid answer");
        }

    }

    public void privacy(){
        user.updateLastTime();
      Logic.output("here you can switch your account to public or private\n" +
              "your account is public by default");
      Logic.output("1.switch to private\n" +
              "2. switch to public\n" +
              "3.back\n");
        while (true) {
      String ans = Logic.input();
          if (ans.equals("1")) {
              if (user.Public == true) {
                  user.setPublic(false);
                  user.setShowLastSeen(1);
                  user.saveUser();
                  logger.info(user.getUsername() + "privacy changed");
                  break;
              }
          }
          else if (ans.equals("2")) {
              if (user.Public == false) {
                  user.setPublic(true);
                  user.saveUser();
                  logger.info(user.getUsername() + "privacy changed");
                  break;
              }
          }
          else if (ans.equals("3")) {
              return;
          }
          else
              Logic.output("enter a valid answer:");
      }

    }

    public void inactivate(){
        user.updateLastTime();
       Logic.output("are you sure that you want to inactivate your account?(yes/no)");
       String ans = Logic.input();
       if (ans.equals("yes")){
           if (user.isActive == true){
               user.setActive(false);
               user.saveUser();
               logger.info(user.getUsername() + "deactivated");
               Start.start();
           }
           else
               return;
       }
    }

    public void changePermission(){
        Logic.output("1.switch to false(others can only see your username and fullName)\n"+
                "2.switch to true(others can see your info\n" +
                "3.back\n");
        while (true){
            String s = Logic.input();
            if (s.equals("1")){
                user.setPermit(false);
                user.saveUser();
                logger.info(user.getUsername() + "permission changed");
            }
            else if (s.equals("2")){
                user.setPermit(true);
                user.saveUser();
                logger.info(user.getUsername() + "permission changed");
            }
            else if (s.equals("3")){
                return;
            }
            else
                Logic.output("enter a valid answer");
        }

    }

    public void changeFullName(){
        while (true) {
            Logic.output("enter your new fullName:");
            String f2 = Logic.input();
            if (SignUp.validName(f2)) {
                user.fullName = f2;
                user.saveUser();
                logger.info(user.getUsername() + "changed fullName");
                break;
            }
            else Logic.output("enter a valid fullName!");
        }
    }

    public void changePassword(){
        while (true) {
            Logic.output("enter a new password:");
            String p2 =Logic.input();
            if (SignUp.isValidPassword(p2)) {
                user.setPassword(p2);
                user.saveUser();
                logger.info(user.getUsername() + "changed password");
                break;
            }
            else
                Logic.output("enter a valid password");
        }
    }

    public void changePhoneNumber() {
        while (true) {
            Logic.output("enter a new phoneNumber:");
            String pn2 = Logic.input();
                if (SignUp.isValidPhoneNumber(pn2)) {
                    user.setPhoneNumber(pn2);
                     user.saveUser();
                     logger.info(user.getUsername() + "phoneNumber changed");
                        break;

                }
                else
                    Logic.output("enter a valid phone number");
            }

        }


    public void changeEmail() {
            while (true) {
                Logic.output("enter a new email:");
                String e2 = Logic.input();
                for (int i = 0; i < Load.userList.size(); i++) {
                    user.setEmail( e2);
                    if (SignUp.isValidEmail(e2)) {
                        if (!Load.userList.contains(user.getEmail())) {
                            user.saveUser();
                            logger.info(user.getUsername() + "email changed");
                            break;
                        } else
                            Logic.output("enter a valid email");
                    }
                }
            }
        }


    public void addBirthdate(){
        Logic.output("enter the day:");
        String day = Logic.input();
        System.out.println("enter the month:");
        String month = Logic.input();
        System.out.println("enter the year:");
        String year = Logic.input();
        String date = year + "/" + month + "/" + day;
        user.setBirthDay(date);
        Logic.output(date);
        user.saveUser();
    }

    public void addBio(){
        Logic.output("add bio to your profile:");
        String bio = Logic.input();
        user.setBiography(bio);
        user.saveUser();
    }

}

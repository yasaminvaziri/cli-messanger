package com.company;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {
    private static final Logger logger = LogManager.getLogger(Start.class);
    public static void start() {
        Logic.output("\033[4;36m" + "**********Already have an account?(yes/no)************");

        while (1 == 1) {

            String ans = Logic.input();


            if (ans.equals("no") || ans.equals("No") || ans.equals("NO")) {
                Logic.output("\033[1;35m" + "*******Create an account (signup)*******");
                Logic.output("\033[1;33m" + "************Enter your fullName:************");

                String fullName = Logic.input();
                while (!SignUp.validName(fullName)) {
                    Logic.output("\\033[0;31m" + "**********Invalid name! Enter your fullName:**********");
                    String a = Logic.input();
                    fullName = a;
                }

                Logic.output("\033[1;33m" + "************Enter your username:************");
                String username;
                while (true){
                    username = Logic.input();
                    User user30 = Load.loadUser(username);
                        if (SignUp.validUsername(username)) {
                            if (user30 == null)
                                break;
                            }
                        else
                           Logic.output("************* invalid user! try again***************");
                    }

               Logic.output(
                        "\033[1;91m" + "******1. A password must have at least eight characters.\n******" +
                                "\033[1;91m" + "******2. A password consists of only letters and digits.\n******" +
                                "\033[1;91m" + "******3. A password must contain at least two digits \n******" +
                                "\033[1;33m" + "****** Enter a password (You are agreeing to the above Terms and Conditions.):******");
                String password = Logic.input();
                while (!SignUp.isValidPassword(password)) {
                    Logic.output("\033[0;35m" + "**********Not a valid password:********** " + password);
                    String p = Logic.input();
                    password = p;
                }
                System.out.println("\033[1;33m" + "************Enter your email:**************");
                String email = Logic.input();
                while (!SignUp.isValidEmail(email)) {
                    Logic.output("\\033[0;31m" + "**********Invalid email! Enter your email**********");
                    String e = Logic.input();
                    email = e;
                }
                Logic.output(
                        "\033[1;91m" + "******1. A number must have at least 13 digits.\n******" +
                                "\033[1;91m" + "******2. Start your number with '+' then, your country code and the rest of your number.\n******" +
                                "\033[1;33m" + "************Enter your phone number:************");
                String phoneNumber = Logic.input();
                while (!SignUp.isValidPhoneNumber(phoneNumber)) {
                    System.out.println("\033[0;35m" + "**********Invalid phone number! Enter your phone number**********");
                    String p = Logic.input();
                    phoneNumber = p;
                }
                Logic.output("\033[4;36m" + "**************your account has been successfully created!************");
                User user = new User(username, email, password, fullName, null, phoneNumber, null);
                user.setOnline(true);
                user.setActive(true);
                user.saveUser();
                logger.info("file created for " + user.getUsername());
                MainMenu.mainMenu(user);

                break;
            }
            if (ans.equals("yes") || ans.equals("Yes") || ans.equals("YES")) {
                while (true) {
                    Logic.output("please enter your username:");
                    String username = Logic.input();
                    Logic.output("please enter your password:");
                    String pass = Logic.input();
                    User user = Load.loadUser(username);
                    if (SignUp.validUsername(username) && SignUp.isValidPassword(pass)) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(pass)) {
                            Logic.output("login successful");
                            logger.info(user.getUsername() + " logged in successfully ");
                            user.setActive(true);
                            user.setOnline(true);
                            user.saveUser();
                            MainMenu.mainMenu(user);
                        }
                    }
                    else
                        Logic.output("invalid! answer again!");

                    break;
                }
                Logic.output("\\033[0;31m" + "**********Enter a valid answer!**********");

            }

        }
    }

        }








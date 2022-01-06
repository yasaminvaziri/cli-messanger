package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;


      public class PersonalPage {
          private static final Logger logger = LogManager.getLogger(PersonalPage.class);

      User user;

      public PersonalPage(User user){
            this.user = user;
        }


      public void showOptions() {
              user.updateLastSeen();
              while (true) {
                  Logic.output("1.Add tweet\n" +
                          "2.Show your tweets\n" +
                          "3.Info\n" +
                          "4.Followers\n" +
                          "5.Followings\n" +
                          "6.BlockList\n" +
                          "7.Notifications\n" +
                          "8.back\n");
                  Logic.output("enter a valid number:");
                  String q = Logic.input();
                  if (q.equals("1")) {
                      addTweet(user.getUsername());
                  }
                  else if (q.equals("2")) {
                      showYourTweets(user.getUsername());
                  }
                  else if (q.equals("3")) {
                     user.profile();

                  }
                  else if (q.equals("4")) {
                      showFollowers(user.getUsername());

                  }
                  else if (q.equals("5")) {
                      showFollowings(user.getUsername());
                  }

                  else if (q.equals("6")) {
                      showBlockList(user.getUsername());

                  }
                  else if (q.equals("7")) {
                      notifications(user.getUsername());

                  }
                  else if (q.equals("8")) {
                      return;

                  }
                  else
                      Logic.output("Invalid number! try again.");
              }
          }


          public void addTweet(String username55){
            User user55 = Load.loadUser(username55);
              Logic.output("new tweet:");
              String t = Logic.input();
              Tweet tweet = new Tweet(user55,t);
              user55.tweetList.add(tweet);
              tweet.saveTweet();
              user55.saveUser();
              logger.info(user55.getUsername() + "added a tweet");
          }

    public void showYourTweets(String username44){
            User user44 = Load.loadUser(username44);
        for(int i = 0; i < user44.tweetList.size(); i++) {

            Logic.output(user.tweetList.get(i).getT());
        }
    }

    public void showFollowers(String username33){
            User user33 = Load.loadUser(username33);
            if (user33.followers.size() > 0) {
                for (int i = 0; i < user33.followers.size(); i++) {
                    Logic.output(user33.followers.get(i));
                }
            }
            else
                Logic.output("no followers yet!");
    }

    public void showFollowings(String username22){
            User user22 = Load.loadUser(username22);
            if (user22.followings.size() > 0) {
                for (int i = 0; i < user22.followings.size(); i++) {
                    Logic.output(user22.followings.get(i));
                }
            }
            else
                Logic.output("no followings yet!");
    }


    public void showBlockList(String username11){
            User user11 = Load.loadUser(username11);
         if (user11.blockList.size() > 0){
            for (int i = 0; i < user11.blockList.size(); i++) {
                Logic.output(user11.blockList.get(i));
            }
         }
         else
             Logic.output("no blockList yet!");

    }



    public void notifications(String username66){
            User user66 = Load.loadUser(username66);
            user66.updateLastTime();
            Logic.output("wanna see to whom have you sent request?\n "+
                    "go back to your Info");
            if (user66.Public == false){
        Logic.output("1.accept\n"+
                "2.reject and notify\n"+
                "3.reject silently\n" +
                "4.back\n");
        LinkedList<String> ans = user66.getRequests();
        if (ans.size() == 0){
            Logic.output("no requests yet!");
        }
        else if (ans.size() > 0) {
            while (true) {
                String s = Logic.input();
                for (int i = 0; i < ans.size(); i++) {
                    if (s.equals("1")) {
                        user66.followers.add(ans.get(i));
                        ans.remove(i);
                        user66.saveUser();
                    }
                    else if (s.equals("2")) {
                        User user54 = Load.loadUser(ans.get(i));
                        user54.rejectedFrom.add(user.getUsername());
                        ans.remove(i);
                        user54.saveUser();
                        user66.saveUser();
                    }
                    else if (s.equals("3")) {
                        ans.remove(i);
                        user66.saveUser();
                    }
                    else if (s.equals("4")) {
                        return;
                    }
                    else
                        Logic.output("enter a valid number!");
                }
            }
        }
        }
            else if (user66.Public == true){
                user66.updateLastTime();
              if (user66.followers.size() > 0){
                  Logic.output(user66.followers.get(user66.followers.size() - 1) + ":");
                  Logic.output("followed you");
              }
            }



    }

      }


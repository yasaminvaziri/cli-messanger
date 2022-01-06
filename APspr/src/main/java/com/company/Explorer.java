package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Random;

public class Explorer {
    private static final Logger logger = LogManager.getLogger(Explorer.class);

    User user;
    int o;

    public Explorer(User user) {
        this.user = user;
        o = 0;
        followingTweets = new LinkedList<>();
    }

    public void explorerOptions(){
        user.updateLastTime();
        addToList();
        while (true) {
            if (followingTweets.size() != 0){
                followingTweets.get(o).showT();
            }
            Logic.output("1.next tweet\n" +
                    "2.previous tweet\n" +
                    "3.like tweet\n" +
                    "4.add tweet to saved messages\n" +
                    "5.retweet\n" +
                    "6.forward\n" +
                    "7.block this user\n" +
                    "8.mute this user\n" +
                    "9.report tweet\n" +
                    "10.see user's profile\n" +
                    "11.comment\n" +
                    "12.see comments\n" +
                    "13.search user\n" +
                    "14.discover\n" +
                    "15.back\n");

            String ans = Logic.input();
            if (ans.equals("1")) {
                next();
            }
            else if (ans.equals("2")) {
                previous();
            }
            else if (ans.equals("3")) {
                like();
            }
            else if (ans.equals("4")) {
                Message message = new Message(user.getUsername(), followingTweets.get(o));
                user.savedMessages.add(message);
                user.saveUser();

            }
            else if (ans.equals("5")) {
                retweet(user.getUsername());
                user.saveUser();
            }
            else if (ans.equals("6")) {
                forwardTweet();
            }
            else if (ans.equals("7")) {
                othersPage othersPage = new othersPage(user);
                othersPage.blockSb(followingTweets.get(o).getUser().getUsername());
                user.saveUser();

            }
            else if (ans.equals("8")) {
                othersPage othersPage = new othersPage(user);
                othersPage.muteUser(followingTweets.get(o).getUser().getUsername());
                user.saveUser();
            }
            else if (ans.equals("9")) {
                user.reportedT.add(followingTweets.get(o));
                user.saveUser();

            }
            else if (ans.equals("10")) {
                othersPage othersPage = new othersPage(user);
                othersPage.showP(followingTweets.get(o).getUser().getUsername());
            }
            else if (ans.equals("11")) {
                addComment(user.getUsername());
            }
            else if (ans.equals("12")) {
                seeComments();
            }
            else if (ans.equals("13")) {
                user.searchUser();
            }
            else if (ans.equals("14")){
                discover(followingTweets.get(o).getUser().getUsername());
            }
            else if (ans.equals("15")){
                return;
            }
            else
                Logic.output("enter a valid number:");
        }

    }
    public  void like(){
        followingTweets.get(o).like++;
        followingTweets.get(o).saveTweet();
        followingTweets.get(o).getUser().saveUser();
    }


    LinkedList<Tweet> followingTweets;
    public void addToList() {
        for (String targetUser: user.followings) {
            User user = Load.loadUser(targetUser);
            followingTweets.addAll(user.getTweetList());
        }
    }

    public void next(){
        if (o == followingTweets.size() - 1) {
            Logic.output("Last Tweet");
        }
        else
            o++;
    }

    public void previous() {
        if (o == 0) {
            Logic.output("First Tweet");
        }
        else
            o++;
    }


    public void addComment(String username89){
        User user89 = Load.loadUser(username89);
        Logic.output("write a comment:");
        Tweet tweet = new Tweet(user89,Logic.input());
        followingTweets.get(o).commentList.add(tweet);
        followingTweets.get(o).getUser().saveUser();
        followingTweets.get(o).saveTweet();
        tweet.saveTweet();
        user89.saveUser();
        logger.info(user89.getUsername() + " added a comment ");
    }

    public void seeComments(){
        for(int i = 0; i < followingTweets.get(o).commentList.size(); i++){
            Logic.output(followingTweets.get(o).commentList.get(i).getT());
        }
    }

    public void retweet(String username3){
        User user3 = Load.loadUser(username3);
        Tweet tweet = new Tweet(user3,followingTweets.get(o).getT());
        user3.tweetList.add(tweet);
        tweet.setrTweet(followingTweets.get(o));
        tweet.saveTweet();
        user3.saveUser();
    }

    public void forwardTweet() {
        String username = user.getUsername();
        Logic.output("write the username that you want to send message:");
        String receiver = Logic.input();
        if (user.followings.contains(receiver) && !user.blockList.contains(receiver)){
            for (Chat chat : user.chats) {
                if (chat.user1.equals(user.getUsername())) {
                    if (chat.user2.equals(receiver)) {
                        Message message = new Message(username, followingTweets.get(o));
                        chat.messages.add(message);
                        Load.loadUser(receiver).addMessage(username, message);
                        user.saveUser();
                        return;
                    }

                }
                if (chat.user1.equals(receiver)) {
                    if (chat.user2.equals(user.getUsername())) {
                        Message message = new Message(username, followingTweets.get(o));
                        chat.messages.add(message);
                        Load.loadUser(receiver).addMessage(username, message);
                        user.saveUser();
                        return;
                    }

                }

            }
            Chat chat = new Chat(user.getUsername(),receiver);
            user.chats.add(chat);
            Message message = new Message(username, followingTweets.get(o));
            user.chats.get(user.chats.size() - 1).messages.add(message);
            user.saveUser();

        }
        else
            Logic.output("you need to follow the user first");
        return;
    }

    public void discover(String username11){
        User user11 = Load.loadUser(username11);
        Random rndm = new Random();
        int rand = rndm.nextInt(followingTweets.size());
        Tweet tt = followingTweets.get(rand);
        if (!user.blockList.contains(user11.followings) &&
                !user.muted.contains(user11.followings))
            followingTweets.get(rand).getT();

    }


}
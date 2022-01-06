package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedList;

public class Tweet {
    private static final Logger logger = LogManager.getLogger(Tweet.class);

    long user;
    String t;
    static LinkedList<Tweet> commentList = new LinkedList<Tweet>();
    LinkedList<String> liker = new LinkedList<String>();
    Tweet rTweet;
    int like = 0;
    long id;



    public Tweet(User user, String t) {
        this.id = Load.lastIdTweet() + 1;
        Load.tweetList.add(this);
        this.user = user.id;
        this.t = t;
        liker = new LinkedList<>();


    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like++;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser(){
        return Load.loadUser(user);
    }

    public Tweet getrTweet() {
        return rTweet;
    }

    public void setrTweet(Tweet rTweet) {
        this.rTweet = rTweet;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public LinkedList<Tweet> getCommentList(){
        return commentList;
    }

    public void setCommentList(Tweet tweet){
        commentList.add(tweet);
    }

    public void showT(){
        Logic.output(t);
    }


    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public  void  saveTweet()  {
        String s = gson.toJson(this);
        String path = "resources/saveTweet";
        File tmp = new File(path);
        path +="/" + id;
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
                logger.info("file created for tweet");
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(this,printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
            logger.error("could not create file");
        }

    }

}

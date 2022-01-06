package com.company;



public class Message {
    String sender;
    String message;
    Tweet tweet;
    boolean seen;

    public Message(String sender, String message){
        this.sender = sender;
        this.message = message;
        this.tweet = null;
        seen = false;

    }

    public Message(String sender, Tweet tweet) {
        this.sender = sender;
        this.tweet = tweet;
        this.message = null;
        seen = false;
    }

    public void show(String username){
        if (!sender.equals(username)){
            seen = true;
        }
        if (tweet == null) {
            Logic.output(sender + ":");
            Logic.output(message);
        }
        else
            tweet.showT();

    }


}

package com.company;

import java.util.LinkedList;

public class Chat {
    String user1;
    String user2;
    public LinkedList<Message>messages;


    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        messages = new LinkedList<>();
    }

    public int getUnread(String username){
        int cnt = 0;
        for (Message message : messages){
            if (!message.sender.equals(username) && message.seen == false){
                cnt++;
            }
        }
        return cnt;
    }

}

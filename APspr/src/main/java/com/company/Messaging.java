package com.company;


import java.util.LinkedList;


public class Messaging {
    User user;


    public Messaging(User user) {
        this.user = user;

    }

    public void messageOptions(){
        user.updateLastTime();
        while (true) {
            Logic.output("1.saved messages\n" +
                    "2.chats\n" +
                    "3.send  to group\n"+
                    "4.back\n");
            String s = Logic.input();
            if (s.equals("1")){
                user.sendMessage();
            }

            else if (s.equals("1")){
                while (1==1){
                    Logic.output("1.write a message to save\n"+
                            "2.show fave(Tweet/DM)\n"+
                            "3.back\n");
                    String q = Logic.input();
                    if (q.equals("1")){
                        String m = Logic.input();
                        String sender = Logic.input();
                        Message message = new Message(m,sender);
                        user.savedMessages.add(message);
                        user.saveUser();
                    }
                    else if (q.equals("2")){
                       for (int i = 0; i < user.savedMessages.size(); i++){
                           user.savedMessages.get(i);
                       }
                    }
                    else
                        return;
                }

            }
            else if (s.equals("2")){
                for (Chat chat : user.chats){
                  if (chat.user1.equals(user.getUsername()) && chat.getUnread(user.getUsername()) > 0){
                      Logic.output(chat.user2);
                      Logic.output(String.valueOf(chat.getUnread(user.getUsername())));
                  }
                  else if (chat.user2.equals(user.getUsername())&& chat.getUnread(user.getUsername()) > 0){
                      Logic.output(chat.user1);
                      Logic.output(String.valueOf(chat.getUnread(user.getUsername())));
                  }
                }
                for (Chat chat : user.chats){
                    if (chat.user1.equals(user.getUsername()) && chat.getUnread(user.getUsername()) == 0){
                        Logic.output(chat.user2);
                    }
                    else if (chat.user2.equals(user.getUsername()) && chat.getUnread(user.getUsername()) == 0){
                        Logic.output(chat.user1);
                    }
                }
                Logic.output("write a username to show messages with:");
                String username = Logic.input();
               User user33 = Load.loadUser(username);
                othersPage othersPage = new othersPage(user);
                othersPage.showP(user33.getUsername());
                for (Chat chat : user.chats){
                    Logic.output("!");
                    if (chat.user1.equals(user.getUsername())){
                        if (user.getUsername().equals(chat.user2)) {
                            showChat();
                            Logic.output("1.send message:\n" +
                                    "2.back\n");
                            String ans = Logic.input();
                            if (ans.equals("1")) {
                                Logic.output("write your message:");
                                String m = Logic.input();
                                Message message = new Message(chat.user1, m);
                                chat.messages.add(message);
                                user33.saveUser();
                            }
                            else if (ans.equals("2")){
                                return;
                            }
                        }
                    }
                    else if (chat.user2.equals(user.getUsername())){
                        if (user.getUsername().equals(chat.user1)){
                            showChat();
                            Logic.output("1.send message:\n" +
                                    "2.back\n");
                            String ans = Logic.input();
                            if (ans.equals("1")){
                                Logic.output("write your message:");
                                String m = Logic.input();
                                Message message = new Message(chat.user2,m);
                                chat.messages.add(message);
                                user33.saveUser();
                            }
                            else if (ans.equals("2")){
                                return;
                            }

                        }

                    }
                }

            }
            else if(s.equals("3")){
                groupSendMessage();
            }
            else if (s.equals("4")){
                return;
            }

        }
    }



    public void showChat(){
        int i = 0;
        for (Chat chat : user.chats){
           chat.messages.get(i).show(user.getUsername());
           i++;
        }

    }
    public void groupSendMessage(){
        LinkedList<String> usernames = new LinkedList<>();
        Logic.output("1.send to multiple users\n" +
                "2.send to groups\n" +
                "3.add member to a group\n" +
                "4.delete member from group\n" +
                "5.back\n");
        while (true) {
        String s = Logic.input();
            if (s.equals("1")) {
                Logic.output("the numbers of the users that you want to send message:");
                int n = Integer.parseInt(Logic.input());
                Logic.output("write the username/usernames:");
                for (int i = 0; i < n; i++){
                    String u = Logic.input();
                    User user83 = Load.loadUser(u);
                    if (!user.blockList.contains(user83.getUsername())
                            && user.followings.contains(user83.getUsername())) {
                        usernames.add(user83.getUsername());
                        user.sendMessage(usernames);
                    }
                }

            }
            else if (s.equals("2")) {
                Logic.output("write the numbers of a group that you want to send message:");
                int n = Integer.parseInt(Logic.input());
                Logic.output("write the group/groups name:");
                for (int i = 0; i < n; i++){
                    String name = Logic.input();
                    for (Group group : user.groups) {
                        if (user.groups.contains(name)) {
                            user.sendMessage(group.usernames);
                            user.saveUser();
                            return;
                        }
                    }
                    Group group = new Group(name,user.getUsername());
                    user.sendMessage(group.usernames);
                    user.saveUser();
                }

            }
            else if (s.equals("3")) {
              for (Group group : user.groups){
                  group.addToGroup();
              }
            }

            else if (s.equals("4")){
                for (Group group : user.groups){
                    group.deleteFromGroup();
                }

            }
            else if (s.equals("5")){
                return;
            }
            else
                Logic.output("enter a valid number");
        }

    }

}

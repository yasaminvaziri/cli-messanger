package com.company;

import java.util.LinkedList;

public class Group {
    String name;
    String admin;
    LinkedList<String> usernames;

    public Group(String name, String admin) {
        this.name = name;
        this.admin = admin;
        usernames = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public LinkedList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(LinkedList<String> usernames) {
        this.usernames = usernames;
    }


    public void addToGroup(){
        User user1 = Load.loadUser(admin);
        Logic.output("write the username that you want to add");
        String username = Logic.input();
        User user44 = Load.loadUser(username);
        if (!user1.blockList.contains(user44) && user1.followings.contains(user44.getUsername())){
            usernames.add(user44.getUsername());
            user1.saveUser();
        }
    }

    public void deleteFromGroup(){
        User user1 = Load.loadUser(admin);
        Logic.output("write the username that you want to remove");
        String username = Logic.input();
        User user44 = Load.loadUser(username);
        if (!user1.blockList.contains(user44) && user1.followings.contains(user44.getUsername())){
            usernames.remove(user44.getUsername());
            user1.saveUser();
        }
    }


}

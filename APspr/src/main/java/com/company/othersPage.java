package com.company;


public class othersPage {
    User user0;
    User user;


    public othersPage(User user) {
        this.user0 = user;
        this.user = user;

    }

    public void showP(String username10){
        User user10 = Load.loadUser(username10);
        user10.updateLastTime();
        if (user10.isActive) {
        Logic.output(" fullName:" + user10.getFullName());
        Logic.output("username:" + user10.getUsername());
        if (user10.permit == true){
           if (user10.Public == true) {
            Logic.output("email:" + user10.getEmail());
            Logic.output("phoneNumber:" + user10.getPhoneNumber());
            Logic.output("birthDay:" + user10.getBirthDay());
            Logic.output("bio:" + user10.getBiography());
        }
           else
               Logic.output("fullName and username is enough for you!");
            }
        }
        else
            Logic.output("unavailable!");

        Logic.output("1.see lastSeen\n"+
                "2.send message\n"+
                "3.report user\n"+
                "4.block user\n" +
                "5.unfollow\n" +
                "6.unblock\n"+
                "7.follow\n"+
                "8.back\n");
        while (true) {
            String s = Logic.input();
            if (s.equals("1")) {
                Logic.output(user10.showLast(user.getUsername()));
            }
            if (s.equals("2")) {
              user0.sendMessage();
            }
            else if (s.equals("3")) {
               reportUser(user10.getUsername());
            }
            else if (s.equals("4")) {
                blockSb(user10.getUsername());
            }
            else if (s.equals("5")){
                unfollowSb(user10.getUsername());

            }
            else if (s.equals("6")){
                unblockSb(user10.getUsername());

            }
            else if (s.equals("7")){
                followSb(user10.getUsername());

            }
            else if (s.equals("8")) {
                return;
            }
            else
                Logic.output("enter a valid number");
        }

    }


    public void followSb(String username14) {
        User user14 = Load.loadUser(username14);
        user14.updateLastTime();
        if (user0.followings.contains(user14.getUsername())) {
            Logic.output("you are already followed this");
        }
        else if (user14.Public == true) {
            if (!user0.blockList.contains(user14.getUsername())
                    && !user0.reportedU.contains(user14.getUsername())
                     && user14.isActive) {
                user0.followings.add(user14.getUsername());
                user14.followers.add(user0.getUsername());
                user14.saveUser();
                user0.saveUser();
                Logic.output("you just followed the user");
            }
        }
        else if (user14.Public == false) {
            if (!user0.blockList.contains(user14.getUsername())
                    && !user0.reportedU.contains(user14.getUsername()) &&
                      user14.isActive) {
                user14.requests.add(user0.getUsername());
                user0.haveSentRequest.add(user14.getUsername());
                user14.saveUser();
                user0.saveUser();
                Logic.output("added to user's requests");
            }
        }
    }

    public void unfollowSb(String username15){
        User user15 = Load.loadUser(username15);
        if (user0.followings.contains(user15.getUsername()) && user15.isActive){
            user0.followings.remove(user15.getUsername());
            user15.followers.remove(user0.getUsername());
            user15.saveUser();
            user0.saveUser();
            Logic.output("you unfollowed this user");
        }

    }

    public void blockSb(String username1){
        User user1 = Load.loadUser(username1);
        if (!user0.blockList.contains(user1.getUsername()) && user1.isActive) {
            user0.blockList.add(user1.getUsername());
            user0.saveUser();
            Logic.output("user blocked");

        }
        else{
            Logic.output("you already blocked this user");
        }

    }

    public void unblockSb(String username16){
        User user16 = Load.loadUser(username16);
        if (user0.blockList.contains(user16.getUsername()) && user16.isActive){
            user0.blockList.remove(user16.getUsername());
            Logic.output("you unblocked the user");
            user0.saveUser();
        }

    }

    public void reportUser(String username4){
        User user4 = Load.loadUser(username4);
        if(!user0.reportedU.contains(user4.getUsername()) && user4.isActive){
            user0.reportedU.add(user4.getUsername());
            user0.saveUser();
        }
    }

    public void muteUser(String username5){
        User user5 = Load.loadUser(username5);
        if (!user0.muted.contains(user5.getUsername()) && user5.isActive)
        user0.muted.add(user5.getUsername());
        user0.saveUser();
    }




}

package com.company;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class User  {
    private static final Logger logger = LogManager.getLogger(Main.class);

    LinkedList<String>followers = new LinkedList<>();
    LinkedList<String>followings = new LinkedList<>();
    LinkedList<String>blockList = new LinkedList<>();
    LinkedList<String>rejectedFrom = new LinkedList<>();
    LinkedList<Tweet> tweetList = new LinkedList<>();
    LinkedList<String>requests = new LinkedList<>();
    LinkedList<String>haveSentRequest = new LinkedList<>();
    LinkedList<String>muted = new LinkedList<>();
    LinkedList<Tweet>reportedT = new LinkedList<>();
    LinkedList<String>reportedU = new LinkedList<>();
    LinkedList<Chat>chats = new LinkedList<>();
    LinkedList<Message>savedMessages = new LinkedList<>();
    LinkedList<Group>groups =new LinkedList<>();
    String username;
    String password;
   public String fullName;
   protected String email;
   protected String birthDay;
   String phoneNumber;
   public String biography;
   long id;
   boolean Public;
   boolean isActive;
   boolean permit;
   boolean isOnline;
   int showLastSeen;
   LocalDateTime lastTime;




 public User( String username,String email, String password,String fullName,String birthDay
              ,String phoneNumber, String biography){
     Load.userList.add(this);
     this.id = Load.lastIdUser() + 1;
     this.username = username;
     this.password = password;
     this.email = email;
     this.fullName = fullName;
     this.birthDay = birthDay;
     this.phoneNumber = phoneNumber;
     this.biography = biography;
     followers = new LinkedList<>();
     followings = new LinkedList<>();
     blockList = new LinkedList<>();
     muted = new LinkedList<>();
     rejectedFrom = new LinkedList<>();
     reportedT = new LinkedList<>();
     reportedU = new LinkedList<>();
     chats = new LinkedList<>();
     savedMessages = new LinkedList<>();
     groups = new LinkedList<>();
     haveSentRequest = new LinkedList<>();
     showLastSeen = 2;
     isActive = true;
     Public = true;
     isOnline = true;
     permit = true;


}
   public String showLast(String username1){
     User user1 = Load.loadUser(username1);
     String t;
     if (showLastSeen == 0){
         Logic.output("last seen recently");
     }
     else if (this.followings.contains(user1.getUsername()) && !this.blockList.contains(user1.getUsername())){
         t = lastTime.getHour()+ " " + lastTime.getMinute() + " " + lastTime.getSecond();
         Logic.output(t);
     }
     else{
         t = lastTime.getHour()+ "" + lastTime.getMinute() + "" + lastTime.getSecond();
         Logic.output(t);
     }
      return username1;
   }

    public String getFullName() {
            return fullName;
    }

    public void setFullName(String fullName) {
            this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getBirthDay() {
        return birthDay;
    }

    protected void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public long getId() { return id;}

    public void setId(long id) { this.id = id;}

    public LinkedList<String> getRejectedFrom() {
        return rejectedFrom;
    }

    public void setRejectedFrom(LinkedList<String> rejectedFrom) {
        this.rejectedFrom = rejectedFrom;
    }

    public LinkedList<String> getRequests() {
        return requests;
    }

    public void setRequests(LinkedList<String> requests) {
        this.requests = requests;
    }

    public LinkedList<String> getHaveSentRequest() {
        return haveSentRequest;
    }

    public void setHaveSentRequest(LinkedList<String> haveSentRequest) {
        this.haveSentRequest = haveSentRequest;
    }

    public LinkedList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<String> followers) {
        this.followers = followers;
    }

    public LinkedList<String> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedList<String> followings) {
        this.followings = followings;
    }

    public LinkedList<String> getBlockList() {
        return blockList;
    }

    public void setBlockList(LinkedList<String> blockList) {
        this.blockList = blockList;
    }

    public LinkedList<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(LinkedList<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public LinkedList<String> getMuted() {
        return muted;
    }

    public void setMuted(LinkedList<String> muted) {
        this.muted = muted;
    }

    public LinkedList<Tweet> getReported() {
        return reportedT;
    }

    public void setReported(LinkedList<Tweet> reported) {
        this.reportedT = reported;
    }

    public void reportTweet(Tweet tweet1){
     this.reportedT.add(tweet1);
    }

    public LinkedList<String> getReportedU() {
        return reportedU;
    }

    public void setReportedU(LinkedList<String> reportedU) {
        this.reportedU = reportedU;
    }

    public LinkedList<Tweet> getReportedT() {
        return reportedT;
    }

    public void setReportedT(LinkedList<Tweet> reportedT) {
        this.reportedT = reportedT;
    }

    public LinkedList<Chat> getChats() {
        return chats;
    }

    public void setChats(LinkedList<Chat> chats) {
        this.chats = chats;
    }

    public LinkedList<Message> getSavedMessages() {
        return savedMessages;
    }

    public void setSavedMessages(LinkedList<Message> savedMessages) {
        this.savedMessages = savedMessages;
    }

    public LinkedList<Group> getGroups() {
        return groups;
    }

    public void setGroups(LinkedList<Group> groups) {
        this.groups = groups;
    }

    public boolean isPublic() {
        return Public;
    }

    public void setPublic(boolean aPublic) {
        Public = aPublic;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getShowLastSeen() {
        return showLastSeen;
    }

    public void setShowLastSeen(int showLastSeen) {
        this.showLastSeen = showLastSeen;
    }

    public boolean isPermit() {
        return permit;
    }

    public void setPermit(boolean permit) {
        this.permit = permit;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void updateLastTime(){
     lastTime = LocalDateTime.now();
    }



    public void searchUser(){
        Logic.output("write a username to search");
        String usrname = Logic.input();
        User user = Load.loadUser(usrname);
        if (user == null){
            Logic.output("user did not found");
        }
        else{
            othersPage othersPage = new othersPage(this);
            othersPage.showP(usrname);
        }
    }

    public void addMessage(String sender, Message message) {
        for (Chat chat: chats) {
            if (chat.user1.equals(sender) || chat.user2.equals(sender))
                chat.messages.add(message);
        }
    }

    public void sendMessage(LinkedList<String>usernames){
        Logic.output("write your message:");
        String me = Logic.input();
        Message message = new Message(getUsername(),me);
        for (String receiver : usernames) {

            if (this.followings.contains(receiver) && !this.blockList.contains(receiver)) {
                for (Chat chat : this.chats) {
                    if (chat.user1.equals(this.getUsername())) {
                        if (chat.user2.equals(receiver)) {
                            chat.messages.add(message);
                            Load.loadUser(receiver).addMessage(username, message);

                            this.saveUser();
                            return;
                        }

                    }
                    if (chat.user1.equals(receiver)) {
                        if (chat.user2.equals(this.getUsername())) {
                            chat.messages.add(message);
                            Load.loadUser(receiver).addMessage(username, message);
                            this.saveUser();
                            return;
                        }

                    }

                }
                Chat chat = new Chat(getUsername(), receiver);
                chats.add(chat);
                chats.get(chats.size() - 1).messages.add(message);
                this.saveUser();

            }
            return;
        }
    }


    public void sendMessage(){
        String username = getUsername();
        Logic.output("write the username that you want to send message:");
        String receiver = Logic.input();
        if (this.followings.contains(receiver) && !this.blockList.contains(receiver)){
            for (Chat chat : this.chats) {
                if (chat.user1.equals(this.getUsername())) {
                    if (chat.user2.equals(receiver)) {
                            Logic.output("write your message:");
                            String m = Logic.input();
                            Message message = new Message(username, m);
                            chat.messages.add(message);
                            Load.loadUser(receiver).addMessage(username, message);
                            this.saveUser();
                            return;
                    }

                }
                if (chat.user1.equals(receiver)) {
                    if (chat.user2.equals(this.getUsername())) {
                        Logic.output("write your message:");
                        String m = Logic.input();
                        Message message = new Message(username, m);
                        chat.messages.add(message);
                        Load.loadUser(receiver).addMessage(username, message);
                        this.saveUser();
                        return;
                    }

                }

            }
            Chat chat = new Chat(getUsername(),receiver);
            chats.add(chat);
            Logic.output("write your message:");
            String m = Logic.input();
            Message message = new Message(username, m);
            chats.get(chats.size() - 1).messages.add(message);
            this.saveUser();

        }
        else
            Logic.output("you need to follow the user first");
        return;
    }


    public  void profile() {
        Logic.output("fullName: " + this.getFullName());
        Logic.output("email:" + this.getEmail());
        Logic.output("phoneNumber:" + this.getPhoneNumber());
        Logic.output("username:" + this.getUsername());
        Logic.output("birthDate:" + this.getBirthDay());
        Logic.output("bio:" + this.getBiography());
        Logic.output("activate:" + this.isActive());
        Logic.output("permit:" + this.isPermit());
        Logic.output("is online:" + this.isOnline());
        Logic.output("public or private:\n" +
                "public: public == true\n"+
                "private: public == false\n"
                + this.isPublic());
        Logic.output("show last seen:" + this.showLastSeen);
        this.updateLastTime();
        if (this.followers.size() > 0) {
            for (int i = 0; i < this.followers.size(); i++) {
                Logic.output("followers:" + this.followers.get(i));
            }
        }
        if (this.followings.size() > 0) {
            for (int i = 0; i < this.followings.size(); i++) {
                Logic.output("followings:" + this.followings.get(i));
            }
        }
        if (this.blockList.size() > 0){
            for (int i = 0; i < this.blockList.size(); i++) {
                Logic.output("blockList:" + this.blockList.get(i));
            }
        }
        if (this.requests.size() > 0){
            for (int i = 0; i < this.requests.size(); i++) {
                Logic.output("requests:" + this.requests.get(i));
            }
        }
        if (this.haveSentRequest.size() > 0){
            for (int i = 0; i < this.haveSentRequest.size(); i++){
                Logic.output("have sent request:" + this.haveSentRequest.get(i));
            }
        }
        if (this.muted.size() > 0){
            for (int i = 0; i < this.muted.size(); i++){
                Logic.output("muted:" + this.muted.get(i));
            }
        }
       if (this.rejectedFrom.size() > 0){
           for (int i = 0; i < this.rejectedFrom.size(); i++){
               Logic.output("rejected from:" + this.rejectedFrom.get(i));
           }
       }
       if (this.tweetList.size() > 0){
           for (int i = 0; i < this.tweetList.size(); i++){
               this.tweetList.get(i).showT();
           }
       }
       if (this.reportedT.size() > 0){
           for (int i = 0; i < this.reportedT.size(); i++){
               Logic.output("reported tweets:" + this.reportedT.get(i));
           }
       }
       if (this.reportedU.size() > 0){
           for (int i = 0; i < this.reportedU.size(); i++){
               Logic.output("reported users:" + this.reportedU.get(i));
           }
       }
        if (this.groups.size() > 0){
            for (int i = 0; i < this.groups.size(); i++){
                Logic.output("groups:" + this.groups.get(i));
            }
        }
        if (this.chats.size() > 0){
            for (int i = 0; i < this.chats.size(); i++){
                Logic.output("chats:" + this.chats.get(i));
            }
        }
         if (this.savedMessages.size() > 0){
             for (int i = 0; i < this.savedMessages.size(); i++){
                 Logic.output("saved messages :" + this.savedMessages.get(i));
             }
         }
         return;
    }

    public  void  saveUser()  {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        String s = gson.toJson(this);
        String path = "resources/saveUser";
        path +="/" + (id);
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
                logger.info("file created for user");
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(this, printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
            logger.info("could not create file for user");
        }

    }

    public void updateLastSeen() {
        lastTime = LocalDateTime.now();
        saveUser();
    }

}

class LocalDateTimeSerializer implements JsonSerializer< LocalDateTime > {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}
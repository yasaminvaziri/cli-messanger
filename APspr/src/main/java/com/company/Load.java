package com.company;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;



public class Load {

   static LinkedList<User>userList = new LinkedList<>();
  static public void loadUser(){
      File file = new File("resources/saveUser");
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
      Gson gson = gsonBuilder.setPrettyPrinting().create();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                User user = gson.fromJson(fileReader,User.class);
                fileReader.close();
                userList.add(user);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     public static User loadUser(long id){
      for (User user : userList){
          if (user.id == id){
              return user;
          }
      }
      return null;
     }

     public static User loadUser(String username){
      for (User user : userList){
          if (user.username.equals(username)){
              return user;
          }
      }
      return null;
     }


    static long lastIdUser(){
      long max = 0;
      for (User user : userList){
          max = (long) Math.max(max, user.id);
      }
      return max;
    }

    static LinkedList<Tweet>tweetList = new LinkedList<>();
 static public void loadTweet(){
        File file = new File("resources/saveTweet");
        Gson gson = new Gson();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                Tweet tweet = gson.fromJson(fileReader,Tweet.class);
                fileReader.close();
                tweetList.add(tweet);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static long lastIdTweet(){
     long max = 0;
     for (Tweet tweet : tweetList){
         max = Math.max(max,tweet.id);
     }
     return max;
    }

}
class LocalDateTimeDeserializer implements JsonDeserializer< LocalDateTime > {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
    }
}

package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.dto.Comments;
import org.example.dto.Post;
import org.example.service.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task2 {
    public void getComments (int userID){
        int postID = getIDPost(userID);
        if (postID >=0) {
            //System.out.println("Max postID (UserID" + userID + ")=" + postID);

            String filename = "src/main/resources/user-"+userID+"-post-"+postID+"-comments.json";
            List<Comments> comments = listComments(postID);
            if (comments != null) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJson = gson.toJson(comments);

                try (FileWriter fileWriter = new FileWriter(Paths.get(filename).toFile())){
                    fileWriter.write(prettyJson);
                    System.out.println("JSON saved to " + filename);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                        System.out.println("Comments not found (UserID " + userID + " postID " + postID);
                    }
    }else{
            System.out.println("Posts not found (UserID " + userID+")");
        }
    }

    private static int getIDPost(int userID) {
        HttpResponse response;
        try {
            response = UserService.sendGetReqest("/users/"+userID+"/posts");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() >= 200 && response.statusCode() < 300){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type postsListType = new TypeToken<List<Post>>() {}.getType();
            ArrayList<Post> posts= gson.fromJson((String) response.body(), postsListType);
            return  posts.stream()
                    .max(Comparator.comparingInt(Post::getId))
                    .get().getId();
        }else {
            return -1;
        }
    }

    private static List<Comments> listComments(int postID) {
        HttpResponse response;
        try {
            response = UserService.sendGetReqest("/posts/"+postID+"/comments");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() >= 200 && response.statusCode() < 300){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type commentsListType = new TypeToken<List<Comments>>() {}.getType();
            List<Comments> comments = gson.fromJson((String) response.body(), commentsListType);
            return comments;
        }else {
            return null;
        }
    }
}

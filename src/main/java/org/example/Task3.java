package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.dto.Task;
import org.example.service.UserService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public List<Task> getListTask(int userID) {
        HttpResponse response;
        try {
            response = UserService.sendGetReqest("/users/"+userID+"/todos");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() >= 200 && response.statusCode() < 300){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type tasksListType = new TypeToken<List<Task>>() {}.getType();
            ArrayList<Task> tasks = gson.fromJson((String) response.body(), tasksListType);
            return  tasks.stream()
                    .filter(task ->(!task.isCompleted()))
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }
}

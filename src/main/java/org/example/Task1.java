package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.dto.User;
import org.example.service.UserService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class Task1 {
    public void createUserPOST() {
        int MAX_ID = 10;
        User user = createUser();
        HttpResponse response;
        UserService userService = new UserService();
        try {
            response = userService.createUser(user);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            User newUser = gson.fromJson((String) response.body(), User.class);
            if (newUser != null && newUser.equals(user) && newUser.getId() == MAX_ID + 1) {
                System.out.println("User successfully created " + newUser);
            } else {
                System.out.println("Error creating user");
            }
        } else System.out.println("Error creating user. Status code " + statusCode);
    }

    public void updateUserPUT(int userID) {
        User user = createUser();
        UserService userService = new UserService();
        HttpResponse response;
        try {
            response = userService.updateUser(user, userID);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            User newUser = gson.fromJson((String) response.body(), User.class);
            if (newUser != null && newUser.equals(user)) {
                System.out.println("User (ID=" + userID + ") successfully update " + newUser);
            } else {
                System.out.println("Error update user (ID=" + userID + ")");
            }
        } else System.out.println("Error update user. Status code " + statusCode);
    }

    public void deleteUser(int userID) {
        UserService userService = new UserService();
        HttpResponse response;
        try {
            response = userService.deleteUser(userID);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            System.out.println("User (ID=" + userID + ") successfully delete.");
        } else
            System.out.println("Error delete user. Status code " + statusCode);
        }

    public void getUserID(int userID) {
        UserService userService = new UserService();
        HttpResponse response;
        try {
            response = userService.getUser_ID(userID);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            User newUser = gson.fromJson((String) response.body(), User.class);
            if (newUser != null) {
                System.out.println("GET user (ID=" + userID + "): " + newUser);
            } else {
                System.out.println("User (ID=" + userID + ")  is not found");
            }
        } else System.out.println("Error GET user ID. Status code " + statusCode);
    }

    public void getUserUsername(String username) {
        UserService userService = new UserService();
        HttpResponse response;
        try {
            response = userService.getUser_username(username);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson((String) response.body(), userListType);
            if (users != null) {
                System.out.print("GET user (username=" + username + "): ");
                users.forEach(System.out::println);
            } else {
                System.out.println("User " + username + " is not found");
            }
        } else System.out.println("Error GET username. Status code " + statusCode);
    }

    public void getUserAll() {
        UserService userService = new UserService();
        HttpResponse response;
        try {
            response = userService.getAllUser();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson((String) response.body(), userListType);
            if (users != null) {
                System.out.print("GET all users: ");
                users.forEach(System.out::println);
            } else {
                System.out.println("Users are not found.");
            }
        } else System.out.println("Error GET all users. Status code " + statusCode);
    }

    private static User createUser() {
        return new User(
                17,
                "Iryna Serogina",
                "IrynaS",
                "Iryna@gmail.com",
                new User.Address(
                        "Pushkina",
                        "123",
                        "Kyiv",
                        "12345678",
                        new User.Geo("1234", "5678")),
                "05012345678",
                "www.serogina.com",
                new User.Company(
                        "SeroginaCo",
                        "Phrasa",
                        "bla-bla-bla")
        );
    }
}


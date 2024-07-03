package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class Task1 {
    public static void createUserPOST() {
        int MAX_ID = 10;
        User user = createUser();
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.POST, user, "");
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

    public static void updateUserPUT(int userID) {
        User user = createUser();
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.PUT, user, "" + userID);
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

    public static void deleteUser(int userID) {
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.DELETE, null, "" + userID);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            System.out.println("User (ID=" + userID + ") successfully delete.");
        } else
            System.out.println("Error delete user. Status code " + statusCode);
        }

    public static void getUserID(int userID) {
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.GET_ID, null, "" + userID);
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

    public static void getUserUsername(String username) {
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.GET_username, null, username);
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

    public static void getUserAll() {
        HttpResponse response;
        try {
            response = Request.sendUserRequest(Request.HttpMethod.GET_ALL, null, "");
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


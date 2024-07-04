package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.dto.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class UserService {
    private static final URI BASE_URI = URI.create("https://jsonplaceholder.typicode.com");
    public HttpResponse createUser(User user) throws IOException, InterruptedException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse updateUser(User user, int userID) throws IOException, InterruptedException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + userID))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse getUser_ID(int userID) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + userID))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse getUser_username(String username) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users?username=" + username))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }
    public HttpResponse getAllUser() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse deleteUser(int userID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + userID))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();
        HttpResponse <String> response =   client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }
    public static HttpResponse sendGetReqest(String uriMethod) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve(uriMethod))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
        HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }


}

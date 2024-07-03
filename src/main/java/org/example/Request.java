package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Request {
    private static final URI BASE_URI = URI.create("https://jsonplaceholder.typicode.com");
    public enum HttpMethod {
        POST, PUT, GET_ID, GET_username, GET_ALL, DELETE
    }
    public static HttpResponse sendUserRequest(HttpMethod httpMethod, User user, String searchCriterion) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = switch (httpMethod) {
            case POST -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();
            case PUT -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + searchCriterion))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();
            case GET_ID -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + searchCriterion))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            case GET_username -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users?username=" + searchCriterion))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            case GET_ALL -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            case DELETE -> HttpRequest.newBuilder()
                    .uri(BASE_URI.resolve("/users/" + searchCriterion))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();
        };
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

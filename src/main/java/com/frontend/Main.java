package com.frontend;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Auth auth = new Auth();
    }

    public static Map<Object, Object> postUserRequest(String path, String username, String password) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();

        // build entity object
        Map<String, Object> toJson = new HashMap<>();
        toJson.put("username", username);
        toJson.put("password", password);

        String json = new GsonBuilder().create().toJson(toJson, Map.class);

        HttpPost post = new HttpPost(path);
        post.setEntity(new StringEntity(json));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(post);

        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

    public static Map<Object, Object> postStorageRequest(String path, String title, String description, Integer count) throws IOException {
        Map<String, Object> toJson = new HashMap<>();
        CloseableHttpClient client = HttpClients.createDefault();

        toJson.put("title", title);
        toJson.put("description", description);
        toJson.put("count", count);

        String json = new GsonBuilder().create().toJson(toJson, Map.class);

        HttpPost post = new HttpPost(path);
        post.setEntity(new StringEntity(json));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(post);
        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

    public static Map<Object, Object> postShopRequest(String path, String title, String description, Integer count, String price) throws IOException {
        Map<String, Object> toJson = new HashMap<>();
        CloseableHttpClient client = HttpClients.createDefault();

        toJson.put("title", title);
        toJson.put("description", description);
        toJson.put("count", count);
        toJson.put("price", price);

        String json = new GsonBuilder().create().toJson(toJson, Map.class);

        HttpPost post = new HttpPost(path);
        post.setEntity(new StringEntity(json));
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(post);
        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

    public static Map<Object, Object> getUserRequest(String path, String pathVar) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(path + "/" + pathVar);

        HttpResponse response = client.execute(get);

        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

    public static JSONArray getStorageRequest(String path) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(path);

        HttpResponse response = client.execute(get);

        JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));

        JSONArray jsonArray = jsonObject.getJSONArray("storage");

        return jsonArray;
    }

    public static JSONArray getShopRequest(String path) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(path);

        HttpResponse response = client.execute(get);

        JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));

        JSONArray jsonArray = jsonObject.getJSONArray("shop");

        return jsonArray;
    }

    public static Map<Object, Object> deleteUserRequest(String path, String pathVar) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpDelete delete = new HttpDelete(path + "/" + pathVar);

        HttpResponse response = client.execute(delete);

        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

}

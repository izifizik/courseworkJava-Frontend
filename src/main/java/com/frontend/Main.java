package com.frontend;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Auth auth = new Auth();
//        Map<Object, Object> map = new HashMap<>();
//        map.put("username", "asd");
//        map.put("password", "123");
//        map.put("id", "123123");
//        try{
//            new App(map);
//        }catch (Exception e) {
//            return;
//        }
    }

    public static Map<Object, Object> postRequest(String path, String username, String password) throws IOException {

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

//        System.out.println(EntityUtils.toString(response.getEntity()));

//        System.out.println(new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class));

        return new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);
    }

    public static Map<Object, Object> getRequest(String path, String pathVar) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        Map<Object, Object> fromJson = new HashMap<>();

        HttpGet get = new HttpGet(path + "/" + pathVar);

        HttpResponse response = client.execute(get);

        fromJson = new GsonBuilder().create().fromJson(EntityUtils.toString(response.getEntity()), Map.class);

        System.out.println(fromJson);

        return fromJson;
    }

}

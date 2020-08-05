package com.simbirrsoft;

import static io.restassured.RestAssured.get;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class FirstTest {

    @Test(description = "GET")
    public void getRequestExampleTest() throws JSONException, JsonProcessingException {
        Response response = get("https://cat-fact.herokuapp.com/facts");
        JSONObject jsonResponse = new JSONObject(response.asString());
        JSONArray jsonArray = jsonResponse.getJSONArray("all");
        jsonArray.length();

        System.out.println(jsonArray.length());
        int x = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i=0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            ObjectMapper objectMapper = new ObjectMapper();
            CatFacts catFacts = objectMapper.readValue(obj.toString(), CatFacts.class);
            if (catFacts.user != null) {
                String stringName = catFacts.user.name.first + " " + catFacts.user.name.last;
                hashMap.put(stringName, 0);
            }
        }
        for(int i=0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            ObjectMapper objectMapper = new ObjectMapper();
            CatFacts catFacts = objectMapper.readValue(obj.toString(), CatFacts.class);
            if (catFacts.user != null) {
                String stringName = catFacts.user.name.first + " " + catFacts.user.name.last;
                hashMap.get(stringName);
                int y = hashMap.get(stringName);
                y++;
                hashMap.put(stringName, y);
            }
        }
        boolean flag = true;
        for (String author: hashMap.keySet()) {
            if (hashMap.get("Kasimir Schulz") < hashMap.get(author)) {
                flag = false;
            }
        }
        Assert.assertTrue("Kasimir Schulz not best", flag);
    }
}
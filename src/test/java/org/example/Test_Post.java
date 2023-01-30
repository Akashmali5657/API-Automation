package org.example;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Test_Post {

    @Test
    public void test_1_post() {

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", "Raghav");
//        map.put("job", "Teacher");
//        System.out.println(map);

        JSONObject request = new JSONObject();

        request.put("name", "Raghav");
        request.put("job", "Teacher");

//        System.out.println(request);
//        System.out.println(request.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201);

    }


    @Test
    public void test_2_put() {

        JSONObject request = new JSONObject();

        request.put("name", "Raghav");
        request.put("job", "Teacher");

//        System.out.println(request);
//        System.out.println(request.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200).contentType("application/json");

        Response response = put("https://reqres.in/api/users/2");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void test_3_patch() {

        JSONObject request = new JSONObject();

        request.put("name", "Raghav");
        request.put("job", "Teacher");

        System.out.println(request);
        System.out.println(request.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                statusCode(200).
                body("job", equalTo("Teacher")).
                log().all();



    }

    @Test
    public void test_4_delete() {


        when().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).
                log().all();
    }
}

package org.example;

import org.junit.Test;
import org.testng.Assert;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class Test_Get {

    @Test
    public void test_01() {

        Response response = given().get("https://reqres.in/api/users?page=2");

        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void test_02() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    @Test
    public void test_03() {
        given()
                .param("Key", "values")
                .header("key", "value")
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.first_name", hasItems("Michael", "Lindsay"));

    }

}

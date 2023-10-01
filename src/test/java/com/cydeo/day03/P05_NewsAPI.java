package com.cydeo.day03;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P05_NewsAPI {

    @Test
    public void getEverything(){

        given().accept(ContentType.JSON)
                .queryParam("q" , "bitcoin")
                .baseUri("https://newsapi.org/v2")
                .header("x-api-key" , "895b50e9e60744729f4308b9e8ff9aae")
                .when().get("/everything").prettyPeek()
                .then().statusCode(200) ;


    }
}

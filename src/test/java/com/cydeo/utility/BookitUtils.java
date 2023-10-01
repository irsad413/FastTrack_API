package com.cydeo.utility;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class BookitUtils {

    public static String getToken (String email , String password) {


        // Get Token
        String accessToken = given().accept(ContentType.JSON)
                .queryParams("email" , email)
                .queryParams("password" , password)
                .when().get("/sign").prettyPeek()
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getString("accessToken");

        System.out.println("accessToken = " + accessToken);



        return "Bearer " + accessToken;
    }
}

package com.cydeo.day03;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P03_BasicAuth extends SpartanAuthTestBase {

    @Test
    public void negativeTest(){

        given().accept(ContentType.JSON)
                .pathParam("id" , 5)
                .when().get("/spartans/{id}").prettyPeek()
                .then().statusCode(401) // Unauthorized User
                .body("error" , is("Unauthorized")) ;


    }


    @Test
    public void getSpartanAsUser(){

        given().log().all().accept(ContentType.JSON)
                .pathParam("id" , 5)
                .auth().basic("user" , "user")
                .when().get("/spartans/{id}").prettyPeek()
                .then().statusCode(200) ;


    }

    @Test
    public void deleteSpartan(){

        given().accept(ContentType.JSON)
                .pathParam("id" , 5)
                .auth().basic("editor" , "editor")
                .when().delete("/spartans/{id}").prettyPeek()
                .then().statusCode(403)
                .body("error" ,  is( "Forbidden")) ;






    }


}







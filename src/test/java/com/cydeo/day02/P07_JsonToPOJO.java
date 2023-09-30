package com.cydeo.day02;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P07_JsonToPOJO extends SpartanTestBase {

    @Test
    public void getSingleSpartan(){


        Response response = given().accept(ContentType.JSON)
                .pathParam("id" , 10)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();


        /*


        {
                "id": 10,
                "name": "Lorenza",
                "gender": "Female",
                "phone": 3312820936
        }
}
         */

        // First Approach --> Response
        System.out.println( " ----------- First Approach --> Response.path() ---------------- " );

        Spartan spartan = response.as(Spartan.class);

        System.out.println("spartan = " + spartan);

        System.out.println(spartan.getId());

        System.out.println(spartan.getName());


        // Second Approach --> JsonPath
        System.out.println( " ----------- Second Approach --> JsonPath ---------------- " );

        JsonPath jsonPath = response.jsonPath() ;
        Spartan sparta = jsonPath.getObject("", Spartan.class);

        System.out.println("sparta.getId() = " + sparta.getId());
        System.out.println("sparta.getName() = " + sparta.getName());




    }

    @Test
    public void searchSpartans(){

        Response response  = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .queryParam("nameContains", "f")
                .when().get("/spartans/search").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                        .extract().response() ;

        // First Approach --> Response
        System.out.println( " ----------- Get me first Spartan --> Response.as ---------------- " );
       // response.as() --> We are not gonna use response.as() method to ger partial of response as POJO class
        // it does not have path parameter to do it




        // Second Approach --> JsonPath
        System.out.println( " ----------- Get me first Spartan  --> JsonPath ---------------- " );
        JsonPath jsonPath = response.jsonPath() ;
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println(spartan);
        System.out.println(spartan.getName());


    }

    @Test
    public void  searchSpartanPOJO(){

        Response response  = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .queryParam("nameContains", "f")
                .when().get("/spartans/search").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response() ;

        JsonPath jsonPath = response.jsonPath() ;

        Search search = jsonPath.getObject("", Search.class);

        // print out total Element
        System.out.println("search.getTotalElement() = " + search.getTotalElement());

        // print how many spartan we have
             List<Spartan> allSpartans = search.getAllSpartans() ;
             for (Spartan eachSpartan : allSpartans) {
                 System.out.println(eachSpartan);
             }


        //print first spartan info
         System.out.println("allSpartans.get(0) = " + allSpartans.get(0));





    }
}

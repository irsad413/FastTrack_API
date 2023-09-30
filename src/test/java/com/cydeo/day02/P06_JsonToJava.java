package com.cydeo.day02;

import com.cydeo.utility.HrTestBase;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P06_JsonToJava extends SpartanTestBase {

    @Test
    public void getSingleSpartan(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();


        // First Approach --> Response
        System.out.println( " ----------- First Approach --> Response.path() ---------------- " );
        Map<String , Object> spartanMap = response.path("") ;

        System.out.println("spartanMap = " + spartanMap);


        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");

        System.out.println("id = " + id);
        System.out.println("name = " + name);


        // Second Approach --> JsonPath
        System.out.println( " ----------- Second Approach --> JsonPath ---------------- " );
        JsonPath jsonPath = response.jsonPath() ;

        Map<String , Object> spMap = jsonPath.getMap("") ;
        int id1 = (int) spMap.get("id");
        String name1 = (String) spMap.get("name");
        System.out.println("id1 = " + id1);
        System.out.println("name1 = " + name1);


    }


    @Test
    public void getAllSpartans(){


        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();


        // First Approach --> Response
        System.out.println( " ----------- First Approach --> Response.path() ---------------- " );

        List<Map<String , Object>> allSpartans = response.path("");

        // print out spartans

        for (Map<String, Object> eachSpartan : allSpartans) {

            System.out.println(eachSpartan);

        }

        // find me first spartan info
        System.out.println("First Spartan = " + allSpartans.get(0));

        // find me first spartan name
        Map<String, Object> firstSpartan = allSpartans.get(0);
        String name = (String) firstSpartan.get("name");
        System.out.println("name = " + name);

        System.out.println("First Spartan Name= " + allSpartans.get(0).get("name"));


        // Second Approach --> JsonPath
        System.out.println( " ----------- Second Approach --> JsonPath ---------------- " );

        JsonPath jsonPath = response.jsonPath() ;
        List<Map<String , Object>> spartans = jsonPath.getList("");


        // print out spartans

        for (Map<String, Object> eachSpartan : spartans) {

            System.out.println(eachSpartan);

        }

        // find me first spartan info
        System.out.println("First Spartan = " + spartans.get(0));


        // find me first spartan name
        System.out.println("First Spartan Name= " + spartans.get(0).get("name"));


    }
}

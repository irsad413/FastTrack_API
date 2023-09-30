package com.cydeo.day03;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P01_SerializationMap extends SpartanTestBase {

   static int spartanID ;

    @Order(1)
    @Test
    public void postSpartan(){

        // Verify success message is --> A Spartan is Born!
        //extract id info from response

        Map<String , Object>  spartanMap = new LinkedHashMap<>() ;
        spartanMap.put("name" , "Snoww");
        spartanMap.put("gender" , "Female");
        spartanMap.put("phone" , 202307010903l);



        System.out.println(spartanMap);

        spartanID = given().accept(ContentType.JSON) // hey API i wanna ger response as JSON
                .contentType(ContentType.JSON) // hey API i am sending data in JSON format
                .body(spartanMap)   // Serialization happens here to convert to JSOn
                .when().post("/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .extract()
                .jsonPath().getInt("data.id");

        System.out.println(spartanID + " is generated");

    }

    @Order(2)
    @Test
    public void putSpartan(){

        Map<String , Object> spartanMap = new LinkedHashMap<>() ;
        spartanMap.put("name" , "Akbash");
        spartanMap.put("gender" , "Female");
        spartanMap.put("phone" , "9876543210");


        given().contentType(ContentType.JSON)
                .pathParam("id" , spartanID)
                .body(spartanMap)
                .when()
                .put("/spartans/{id}")
                .then()
                .statusCode(204) ;

        System.out.println(spartanID + " is updated");


    }

    @Order(3)
    @Test
    public void patchSpartan(){

        Map<String , Object> spartanMap = new LinkedHashMap<>() ;
       spartanMap.put("name" , "Oneal") ;


        given().contentType(ContentType.JSON)
                .pathParam("id" , spartanID)
                .body(spartanMap)
                .when()
                .patch("/spartans/{id}")
                .then()
                .statusCode(204) ;

        System.out.println(spartanID + " is partially updated");


    }

    @Order(4)
    @Test
    public void deleteSpartan(){



        given()
                .pathParam("id" , spartanID)
                .when()
                .delete("/spartans/{id}")
                .then()
                .statusCode(204) ;

        System.out.println(spartanID + " is deleted");


    }
    @Order(5)
    // Negative Test
    @Test
    public void getSpartanAfterDelete(){



        given().accept(ContentType.JSON)
                .pathParam("id" , spartanID)
                .when()
                .get("/spartans/{id}")
                .prettyPeek()
                        .then()
                                .statusCode(404) ;

        System.out.println(spartanID + " is not found");


    }







}

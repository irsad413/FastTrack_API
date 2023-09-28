package com.cydeo.day01;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P04_ParametersTest extends SpartanTestBase {

    @DisplayName("Get /spartan{id} with Path Param example")
    @Test
    public void pathParam() {

        Response response = given().log().all().accept(ContentType.JSON)
                .pathParam("id", 5).
                when().get("/spartans/{id}");

        response.prettyPrint() ;

        // Status code should be 200
        assertEquals(200 , response.statusCode());

        //Content Type is application/json
      assertEquals(ContentType.JSON.toString() , response.contentType());

      
        // ID is 5
        int id = response.path("id");
        System.out.println("id = " + id);
        assertEquals(5 , id);


        // Name is "Blythe",
        String name = response.path("name") ;
        System.out.println("name = " + name);
        assertEquals("Blythe" , name);


        // gender is "Female"
        String gender = response.path("gender") ;
        System.out.println("gender = " + gender);
        assertEquals( "Female" , gender);



        // phone is 3677539542
        long phone = response.path("phone");
        System.out.println("phone = " + phone);
        assertEquals(3677539542l , phone);
    }


    @Test
    public void queryParam(){

        Map<String , String> queryMap = new HashMap<>() ;
        queryMap.put("nameContains" , "k") ;
        queryMap.put( "gender" , "Male") ;

        Response response = given().accept(ContentType.JSON)
                .queryParams(queryMap)
              //  .queryParam("nameContains", "k")
               // .queryParam("gender", "male")
                .when().get("/spartans/search").prettyPeek();

        // Total element number
        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        // Get me first spartan name
        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));

        // Get me second spartan id
        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));

        //Get me last spartan name
        System.out.println("response.path(\"content[-1].name\") = " + response.path("content[-1].name"));


        // Get me all spartan names
        List<String> allNames = response.path("content.name");
        System.out.println("allNames = " + allNames);


        // Verify followings
        // Status code should be 200
        assertEquals(200 , response.statusCode());

    }

    @Test
    public void negativeTest(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("/spartans/{id}").prettyPeek();

        assertEquals(404 , response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND , response.statusCode());


        assertEquals("application/json" , response.contentType());


        assertTrue(response.toString().contains("Not Found")); ;

        String  error = response.path("error");
        assertEquals("Not Found" , error);


    }



}

package com.cydeo.day01;


import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanTest  extends SpartanTestBase {


    @Test
    public  void  getAllSpartans() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .when().get("/spartans");

        // response
        response.prettyPrint() ;

        //Content-type
        assertEquals(ContentType.JSON.toString() , response.contentType());

        //Status Code
        assertEquals(HttpStatus.SC_OK , response.statusCode());

        //Get me first spartan gender
        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));

        //Get me first spartan name
        System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

        //Get me all spartan name
        List<String> allName = response.path("name");
        System.out.println("allName = " + allName);

        //Get me all spartan gender
        List<String> allGender = response.path("gender");
        System.out.println("allGender = " + allGender);

    }
}

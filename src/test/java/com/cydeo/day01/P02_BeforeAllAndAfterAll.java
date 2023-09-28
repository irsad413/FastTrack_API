package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P02_BeforeAllAndAfterAll {

    @BeforeAll
    public static void init(){
        baseURI= "http://34.202.158.24:1000" ;
        basePath = "/ords/hr" ;


    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }


    @Test
    public void simpleTest(){

        Response response = RestAssured.get("regions");

        //print with prettyPeak
        // response.prettyPeek() ;

        //print response
        response.prettyPrint() ;

        //Headers
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.headers() = " + response.headers());

        //Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());

        //Status Code
        System.out.println("response.statusCode() = " + response.statusCode());

        //Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify response has Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //Verify response body has "Europe"
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe"));
        assertTrue(response.asString().contains("Europe"));



    }


    @DisplayName("GET /employees.100")
    @Test
    public void getOneEmployee(){

        Response response = RestAssured.get("/employees/100").prettyPeek();

        //firstName
        String firstName = response.path("first_name");
        System.out.println("first_name = " + firstName);

        //Get first name by using firName key
        String firstname = response.path("firstname");
        System.out.println("first_name = " + firstname);


        //lastName

        String lastName =response.path("last_name") ;
        System.out.println("lastName = " + lastName);

        //Verify status code is 200
        assertEquals(200 , response.statusCode());
        assertEquals(HttpStatus.SC_OK , response.statusCode());


        // Verify first name is "Steven"
        assertEquals("Steven" , firstName);

        // Verify Content-Type is application/json
        assertEquals("application/json" , response.contentType());
        assertEquals(ContentType.JSON.toString() , response.contentType());


    }


}

package com.cydeo.day03;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P02_SerializationPOJO extends SpartanTestBase {

    @Test
    public void postSpartan(){

        Spartan spartan = new Spartan() ;

        spartan.setName("Post POJO");
        spartan.setGender("Male");
        spartan.setPhone(1236547890);

        System.out.println(spartan); // it will show id field as well

        given().log().all().accept(ContentType.JSON) // print body that we are sending to API
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/spartans")
                .then().statusCode(201) ;



    }
}

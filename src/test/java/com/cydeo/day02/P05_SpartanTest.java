package com.cydeo.day02;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P05_SpartanTest extends SpartanTestBase {

    @Test
    public void searchSpartans(){

        given().log().all()
                .queryParam("gender" , "Female")
                .queryParam("nameContains" , "f")
                .when().get("/spartans/search").prettyPeek()
                .then()
                .log().ifValidationFails() // if any validation fails in then() by using HamCrest it will log detail
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .body("totalElement" , is(4))
                .body("content" , hasSize(4))
                .body("content.name" , hasItem("Freida"))
                .body("content.gender" , everyItem(is("Female"))) ;




    }


}

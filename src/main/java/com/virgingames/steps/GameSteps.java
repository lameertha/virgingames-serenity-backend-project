package com.virgingames.steps;

import com.virgingames.constant.EndPoint;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/* Created
 * by Lamee */
public class GameSteps {
    @Step("Get all games information")
    public ValidatableResponse GetGameInfo() {

        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .when()
                .get(EndPoint.GET_BINGO)
                .then().log().ifValidationFails()
                .parser("text/plain;charset=UTF-8", Parser.JSON);

    }
}

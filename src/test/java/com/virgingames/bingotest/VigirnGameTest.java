package com.virgingames.bingotest;

import com.virgingames.steps.GameSteps;
import com.virgingames.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/* Created
 * by Lamee */
@RunWith(SerenityRunner.class)
public class VigirnGameTest extends TestBase {
    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;

    @Steps
    GameSteps gameSteps;

    @Title("This test will get all default game frequency = 300000")
    @Test

    public void test001() {
        ValidatableResponse response = gameSteps.GetGameInfo().statusCode(200);
        defaultGameFrequency = response.extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        System.out.println("Default game frequency is : " + defaultGameFrequency);

    }


    @Title("This test will verify that the startTime always future timestamp")
    @Test
    public void test002() {

        ValidatableResponse response = gameSteps.GetGameInfo().statusCode(200);
        timeStamp = response.extract().response().body().path("timestamp").toString();
        startTime = response.extract().response().body().path("bingoLobbyInfoResource.streams.startTime").toString();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Timestamp is : " + timeStamp);
        System.out.println("Start Times is :" + startTime);
        System.out.println("------------------End of Test---------------------------");
        assertThat(startTime, greaterThan(timeStamp));
    }

}

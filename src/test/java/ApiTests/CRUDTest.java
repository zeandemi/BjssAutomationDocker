package ApiTests;

import ApiUtility.PayLoadDoc;
import ApiUtility.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Instant;
import static io.restassured.RestAssured.given;
import static java.time.Instant.parse;

public class CRUDTest {
    static PayLoadDoc payLoadDoc;


    @BeforeTest
    public void getBaseUrl(){
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test(priority = 1)
    public void createApi() {
        Response rs = given().body(Payloads.createPayload).when().post("/api/users").then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON).extract().response();
        String rawToString = rs.asString();
        Payloads.jsonResponse(rawToString);
        System.out.println(payLoadDoc.responseId);
    }

    @Test(priority = 2)
    public void patchApi() {
        Response rs = given().body(Payloads.updatedPayload).when().patch("/api/users/" + payLoadDoc.responseId).then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON).extract().response();
        String rawToString1 = rs.asString();
        String timeStamp = Payloads.jsonPatchResponse(rawToString1, "updatedAt");
        String createTimeStamp = payLoadDoc.responseCreatedAt;
        Instant updateInstant = parse(timeStamp);
        Instant createInstant = parse(createTimeStamp);
        Assert.assertTrue(updateInstant.isAfter(createInstant));
        System.out.println(payLoadDoc.responseCreatedAt);
    }

    @Test(priority = 3)
    public void deleteApi(){
        given().delete("/api/users/" + payLoadDoc.responseId).then().assertThat().statusCode(204);
    }

    @Test
    public void getApi(){
        String id = "10";
        Response rs = given().get("/api/users/"+id).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .extract().response();
        String rawMessage = rs.asString();
        String userId = Payloads.stringToJsonResponse(rawMessage);
        Assert.assertEquals(id,userId);
        System.out.println(userId);
    }
}

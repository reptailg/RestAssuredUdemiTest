package base;

import client.TestClient;
import config.TestConfig;
import data.Admin;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BookingTestBase {
    protected static TestClient testClient;
    protected String token;
    protected Integer id;

//    static {
//        TestClient testClient = new TestClient();
//    }

    @BeforeTest
    public void getTokenTest(){
        token = "token=" + given().baseUri(TestConfig.URI.value)
                .contentType(ContentType.JSON)
                .body(new Admin())
                .when()
                .post("auth")
                .then()//.log().all()
                .extract().response().jsonPath().getString("token");
    }

    @AfterTest
    public void deleteGuest(){
        try {
            given().baseUri(TestConfig.URI.value)
                    .contentType(ContentType.JSON)
                    .header("Cookie",token)
                    .when()
                    .delete("booking/{id}", id);
            System.out.printf("Delete guest %d is completed ", id);
        }catch (Exception e){
            System.out.println("Can't find guest " + id);
        }
    }

}

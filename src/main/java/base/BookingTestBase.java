package base;

import client.TestClient;
import config.TestConfig;
import data.Admin;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BookingTestBase {
    public static final Logger log = LoggerFactory.getLogger(BookingTestBase.class.getName());
    protected static TestClient testClient;
    static {
        testClient = new TestClient();
    }

    protected String authToken;
    protected Integer guestId;
    @BeforeTest
    public void getTokenTest(){
        authToken = "token=" + given().baseUri(TestConfig.URI.value)
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
                    .header("Cookie", authToken)
                    .when()
                    .delete("booking/{id}", guestId);

            log.info("Delete guest {} is completed ", guestId);
        }catch (Exception e){
            log.info(String.format("%60s", "-").replace(' ', '-'));
            log.info(String.format("%60s", "-").replace(' ', '-'));
            log.error("Can't find guest {}", guestId);
        }
    }

}

package client;

import data.Admin;
import data.Guest;
import data.GuestValidatableResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import config.TestConfig;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class TestClient {
    private String baseUri;

    public TestClient() {
        this(TestConfig.URI.value);
        RestAssured.defaultParser = Parser.JSON;

    }

    public RequestSpecification getRequestSpec(){
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public RequestSpecification getRequestSpec(Object guest){
        return getRequestSpec()
                .body(guest);
    }


    public GuestValidatableResponse createGuest(Guest guest){
         Response response = getRequestSpec(guest)
                .when()
                .post("booking/");
         response.then().log().all();
         return new GuestValidatableResponse(response, "create");
    }

    public GuestValidatableResponse getGuest(Integer id){
        Response response = getRequestSpec()
                .when()
                .get("booking/{id}", id);
        response.then().log().all();
        return new GuestValidatableResponse(response, "get");
    }

    public String getToken(Admin admin){
        return  getRequestSpec(admin)
                .when()
                .post("auth")
                .then().log().all()
                .extract().response().jsonPath().getString("token");

    }

    public GuestValidatableResponse updateGuest(int id, Guest guest, String token){
        Response response = getRequestSpec(guest)
                .header("Cookie",token)
                .when()
                .put("booking/{id}", id);
        response.then().log().all();
        return new GuestValidatableResponse(response,"put");
    }
}

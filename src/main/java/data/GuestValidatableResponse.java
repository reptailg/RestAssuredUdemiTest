package data;

import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

public class GuestValidatableResponse {
    private GuestResponse guestResponse;
    private Guest guest;
    private Response response;

    public GuestValidatableResponse(Response response, String metod) {
        this.response = response;
        if(metod.equals("create")) {
            guestResponse = response.as(GuestResponse.class);
        }else {
            guest = response.as(Guest.class);
        }
    }

    public GuestValidatableResponse checkStatusCode(int statusCode){
        response.then().statusCode(statusCode);
        return this;
    }

    public GuestValidatableResponse checkIdNotNull(){
        response.then().body("bookingid", notNullValue());
        return this;
    }

    public GuestValidatableResponse checkBook(Guest expected){
        Assert.assertEquals(guestResponse.booking,expected);
        return this;
    }

    public GuestValidatableResponse checkFindBook(Guest expected){
        Assert.assertEquals(guest, expected);
        return this;
    }

    public Integer getId(){
        return response.jsonPath().getInt("bookingid");
    }

}

package test;

import base.BookingTestBase;
import client.TestClient;
import data.GuestValidatableResponse;
import data.Guest;
import base.GuestData;
import org.testng.annotations.Test;


public class CreateBookingTest extends BookingTestBase {

    @Test(dataProvider = "createBooksPositive", dataProviderClass = GuestData.class)
    public void testCreatedBook(Guest guest){
        testClient = new TestClient();

       GuestValidatableResponse response = testClient.createGuest(guest)
               .checkStatusCode(200)
               .checkIdNotNull()
               .checkBook(guest);

       id = response.getId();
       testClient.getGuest(response.getId())
               .checkStatusCode(200)
               .checkFindBook(guest);
    }



}



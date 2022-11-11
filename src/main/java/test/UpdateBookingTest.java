package test;

import base.BookingTestBase;
import base.GuestData;
import client.TestClient;
import data.Guest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UpdateBookingTest extends BookingTestBase {
    @BeforeClass
    public void setUp(){
        testClient = new TestClient();
        try {
            guestId = testClient.createGuest(Guest.defaultof())
                    .checkStatusCode(200).getId();
            log.info("Create guest # "+ guestId);
            log.info(String.format("%60s", "-").replace(' ', '-'));
            log.info(String.format("%60s", "-").replace(' ', '-'));
        }catch (Exception e){
            log.error("Guest not created");
            log.info(String.format("%60s", "-").replace(' ', '-'));
            log.info(String.format("%60s", "-").replace(' ', '-'));
        }
    }
    @Test(dataProvider = "createGuestPositive", dataProviderClass = GuestData.class)
    public void updateGuestTest(Guest guest){
        testClient.updateGuest(guestId, guest, authToken)
                .checkStatusCode(200)
                .checkFindGuest(guest);

        testClient.getGuest(guestId)
                .checkStatusCode(200)
                .checkFindGuest(guest);
    }
}

package base;

import data.Guest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class GuestData {

    private GuestData(){
    }

    @DataProvider
    public static Object[][] createGuestPositive(){
        return new Object[][]{
//                {Guest.defaultof()},
                {Guest.defaultof().setFirstname(RandomStringUtils.randomAlphabetic(3))},
                {Guest.defaultof().setLastname(RandomStringUtils.randomAlphabetic(3))},
//                {Guest.defaultof().setTotalprice(0)},
//                {Guest.defaultof().setDepositpaid(false)},
//                {Guest.defaultof().setBookingdates(date().toString(), date().toString())},
//                {Guest.defaultof().setAdditionalneeds(RandomStringUtils.randomAlphabetic(20))},
        };
    }

    public static LocalDate date() {
        int hundredYears = 100 * 365;
        return LocalDate.ofEpochDay(ThreadLocalRandom
                .current().nextInt(-hundredYears, hundredYears));
    }
}

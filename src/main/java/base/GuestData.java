package base;

import data.Guest;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class GuestData {
    @DataProvider
    public static Object[][] createBooksPositive(){
        return new Object[][]{
                {Guest.defaultof()},
//                {Guest.defaultof().setFirstname(RandomStringUtils.randomAlphabetic(3))},
//                {Guest.defaultof().setLastname(RandomStringUtils.randomAlphabetic(3))},
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

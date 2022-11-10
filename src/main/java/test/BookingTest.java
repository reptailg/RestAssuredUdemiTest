package test;

import data.Book;
import data.Bookingdates;
import org.testng.annotations.Test;
import spec.Specification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BookingTest {
    public static final String URL = "https://restful-booker.herokuapp.com";

    @Test
    public void testCreatedBook(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpec(200));
        Book book = new Book("Jim",
                "Brown",
                111,
                true,
                new Bookingdates("2018-01-01", "2019-01-01"),
                "Breakfast");
        given()
                .body(book)
                .when().post("/booking/")
                .then().log().all()
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"));
    }
}

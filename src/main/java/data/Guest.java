package data;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    public Guest(Guest guest) {
        this.firstname = guest.firstname;
        this.lastname = guest.lastname;
        this.totalprice = guest.totalprice;
        this.depositpaid = guest.depositpaid;
        this.bookingdates = guest.bookingdates;
        this.additionalneeds = guest.additionalneeds;
    }

    public static Guest defaultof(){
        return new Guest("Jim",
                "Brown",
                111,
                true,
                new Bookingdates("2018-01-01", "2019-01-01"),
                "Breakfast");
    }


    public Object setBookingdates(String s, String s1) {
        return new Bookingdates(s,s1);
    }
}
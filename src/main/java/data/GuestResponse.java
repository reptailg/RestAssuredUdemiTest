package data;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GuestResponse extends Object{
    public int bookingid;
    public Guest booking;
}

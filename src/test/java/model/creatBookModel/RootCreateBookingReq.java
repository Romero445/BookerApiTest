package model.creatBookModel;

import lombok.Data;


@Data
public class RootCreateBookingReq {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingdatesReq bookingdates;
    private String additionalneeds;
}

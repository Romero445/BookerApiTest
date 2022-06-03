package model.creatBookModel;

import lombok.Data;

@Data
public class NewBookRep {

    private String firstname;
    private String additionalneeds;
    private BookingdatesRep bookingdates;
    private Integer totalprice;
    private Boolean depositpaid;
    private String lastname;


}

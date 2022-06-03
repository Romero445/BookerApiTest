
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import model.autModel.AutBookStore;
import model.autModel.AutBookStoreResponse;
import model.creatBookModel.BookingdatesReq;
import model.creatBookModel.RootCreateBookingReq;
import model.creatBookModel.RootNewBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class BookApiTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void createBook() {

        AutBookStore autBookStore = new AutBookStore();
        RootCreateBookingReq createBookingReq = new RootCreateBookingReq();
        BookingdatesReq bookingdatesReq = new BookingdatesReq();

        autBookStore.setUsername("admin");
        autBookStore.setPassword("password123");

        bookingdatesReq.setCheckout("2013-02-23");
        bookingdatesReq.setCheckin("2014-10-23");

        createBookingReq.setFirstname("lorenso");
        createBookingReq.setLastname("Naveres");
        createBookingReq.setTotalprice(120);
        createBookingReq.setAdditionalneeds("Breakfast");
        createBookingReq.setBookingdates(bookingdatesReq);
        createBookingReq.setDepositpaid(true);


        step("Login book store", () -> {
            AutBookStoreResponse loginResponse =
                    given()
                            .contentType("application/json; charset=utf-8")
                            .body(autBookStore)
                            .log().all()
                            .when()
                            .post("/auth")
                            .then()
                            .statusCode(200)
                            .log().all()
                            .extract().as(AutBookStoreResponse.class);

            assertThat(loginResponse.getToken()).isNotEqualTo(null);

        });

        step("create booking", () -> {

            RootNewBook newBook =
                    given()
                            .contentType("application/json; charset=utf-8")
                            .body(createBookingReq)
                            .log().all()
                            .when()
                            .post("/booking")
                            .then()
                            .statusCode(200)
                            .log().all()
                            .extract().as(RootNewBook.class);

            assertThat(newBook.getBooking()).isNotEqualTo(null);
            assertThat(newBook.getBooking().getFirstname()).isEqualTo("lorenso");

        });


    }

    @Test
    void getBookList(){

    }


}

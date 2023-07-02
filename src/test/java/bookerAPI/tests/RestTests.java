package bookerAPI.tests;

import bookerAPI.manager.BaseTests;
import bookerAPI.manager.BookingData;
import bookerAPI.manager.Specifications;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestTests extends BaseTests {

    private final static String URL = "https://restful-booker.herokuapp.com";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void auth() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "password123");

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post(URL + "/auth")
                .then().log().all()
                .extract().response();

    }


    @Test
    public void getAllId() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());

        given()
                .when()
                .get("/booking")
                .then().log().all()
                .body("bookingid", notNullValue())
                .extract().response();
    }

    @Test
    public void getBookingFromId() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());

        given()
                .when()
                .get("/booking/11")
                .then().log().all()
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("totalprice", notNullValue())
                .body("depositpaid", notNullValue())
                .body("bookingdates.checkin", notNullValue())
                .body("bookingdates.checkout", notNullValue())
                //.body("additionalneeds", notNullValue()) //это поле, то есть, то нет!
                .extract().response();

        //BookingData actualBooking = gson.fromJson(response.asString(), BookingData.class);
        //BookingData expectedBooking = new BookingData(null, "Susan", "Brown", 825, false,
        //       new BookingData.Bookingdates("2018-12-18", "2019-04-14"), "Breakfast");
        // Assert.assertEquals(actualBooking, expectedBooking);

    }

    @Test()
    public void createNewBooking() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());

        BookingData expectedBooking = new BookingData(null, "Jim", "Brown", 111, true,
                new BookingData.Bookingdates("2018-01-01", "2019-01-01"), "Breakfast");

        given()
                .contentType(ContentType.JSON)
                .body(expectedBooking)
                .when()
                .post("/booking")
                .then().log().all()
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"))
                .extract().response();

    }

    @Test
    public void updateBookingFromId() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());

        BookingData expectedBooking = new BookingData(null, "James", "Brown", 111, true,
                new BookingData.Bookingdates("2018-01-01", "2019-01-01"), "Breakfast");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                //.cookie("token", "abc123")
                .auth().preemptive().basic("admin", "password123")
                .body(expectedBooking)
                .when()
                .put("/booking/4")
                .then().log().all()
                .extract().response();

        BookingData actualBooking = gson.fromJson(response.asString(), BookingData.class);
        Assert.assertEquals(actualBooking, expectedBooking);
    }

    @Test
    public void updateBookingPartial() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec200());

        HashMap<String, String> map = new HashMap<>();
        map.put("firstname", "James");
        map.put("lastname", "Brown");

        BookingData actualBooking = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                //.cookie("token", "abc123")
                .body(map)
                .when()
                .patch("/booking/3")
                .then().log().all()
                .extract().response().as(BookingData.class);

        assertThat(actualBooking.getFirstname(), equalTo(map.get("firstname")));
        assertThat(actualBooking.getLastname(), equalTo(map.get("lastname")));

    }

    @Test
    public void deleteBooking() {
        Specifications.installSpec(Specifications.requestSpec(URL), Specifications.responseSpec201());

        given()
                .contentType(ContentType.JSON)
                .cookie("token", "abc123")
                .auth().preemptive().basic("admin", "password123")
                .when()
                .delete("/booking/10")
                .then().log().all().extract();

    }
}

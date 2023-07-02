package bookerAPI.manager;

import java.util.Objects;

public class BookingData {

    public Integer bookingid;
    public String firstname;
    public String lastname;
    public Integer totalprice;
    public boolean depositpaid;
    public Bookingdates bookingdates;
    public String additionalneeds;

    public BookingData(Integer bookingid, String firstname, String lastname, Integer totalprice, boolean depositpaid, Bookingdates bookingdates, String additionalneeds) {
        this.bookingid = bookingid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public BookingData(Integer bookingid, String firstname, String lastname, Integer totalprice, boolean depositpaid, Bookingdates bookingdates) {
        this.bookingid = bookingid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }

    public BookingData(String firstname, String lastname, Integer totalprice, boolean depositpaid, Bookingdates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public BookingData() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingData that = (BookingData) o;
        return depositpaid == that.depositpaid && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(totalprice, that.totalprice) && Objects.equals(bookingdates, that.bookingdates) && Objects.equals(additionalneeds, that.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    @Override
    public String toString() {
        return "BookingData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public static class Bookingdates {
        public String checkin;
        public String checkout;

        public Bookingdates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bookingdates that = (Bookingdates) o;
            return Objects.equals(checkin, that.checkin) && Objects.equals(checkout, that.checkout);
        }

        @Override
        public int hashCode() {
            return Objects.hash(checkin, checkout);
        }

        @Override
        public String toString() {
            return "Bookingdates{" +
                    "checkin='" + checkin + '\'' +
                    ", checkout='" + checkout + '\'' +
                    '}';
        }
    }
}

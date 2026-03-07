/**
 * Joe Merrill
 * CMSC 345 - Week 6 Assignment 3 15FEB2026
 * Black-box unit tests for Reservation class using w6.jar and Assert library.
 *
 * Notes from Reservation specification:
 * - All argument values are assumed valid; no invalid input testing required.
 * - Reservation end date > start date is assumed valid; no invalid date-order testing required.
 * - Date strings use pattern: "MMM dd, yyyy" (e.g., "Jan 02, 2025")
 * - Room types: "RoomWBath" ($200), "RoomWView" ($175), "NormalRoom" ($120)
 */
public class TestReservation {

    public static void main(String[] args) throws Exception {

        // A) Constructor & Getters
        Reservation r1 = new Reservation(
                101,
                "RoomWBath",
                "Jan 02, 2025",
                "Jan 05, 2025"
        );

        Assert.assertEqualsInt(r1.getGuestID(), 101);
        Assert.assertEqualsString(r1.getRoomType(), "RoomWBath");
        Assert.assertEqualsString(r1.getReservationStartDate(), "Jan 02, 2025");
        Assert.assertEqualsString(r1.getReservationEndDate(), "Jan 05, 2025");


        // B) Setters & Getters

        r1.setGuestID(202);
        r1.setRoom("NormalRoom");
        r1.setReservationStartDate("Feb 10, 2025");
        r1.setReservationEndDate("Feb 12, 2025");

        Assert.assertEqualsInt(r1.getGuestID(), 202);
        Assert.assertEqualsString(r1.getRoomType(), "NormalRoom");
        Assert.assertEqualsString(r1.getReservationStartDate(), "Feb 10, 2025");
        Assert.assertEqualsString(r1.getReservationEndDate(), "Feb 12, 2025");


        // C) calculateReservationNumberOfDays()
        // Spec: includes start date, excludes end date
        // Example: Jan 02 to Jan 05 => 3

        Reservation r2 = new Reservation(
                303,
                "RoomWView",
                "Mar 01, 2025",
                "Mar 04, 2025"
        );
        Assert.assertEqualsLong(r2.calculateReservationNumberOfDays(), 3L);

        // Another valid case: 1 day stay
        Reservation r3 = new Reservation(
                404,
                "NormalRoom",
                "Apr 10, 2025",
                "Apr 11, 2025"
        );
        Assert.assertEqualsLong(r3.calculateReservationNumberOfDays(), 1L);

        // D) calculateReservationBillAmount()
        // Spec: bill = days * dailyRate(roomType)
        // Rates: RoomWBath=200, RoomWView=175, NormalRoom=120

        // r2: RoomWView, 3 days => 3 * 175 = 525.0
        Assert.assertEqualsDouble(r2.calculateReservationBillAmount(), 525.0);

        // r3: NormalRoom, 1 day => 1 * 120 = 120.0
        Assert.assertEqualsDouble(r3.calculateReservationBillAmount(), 120.0);

        System.out.println("All tests executed.");
    }
}

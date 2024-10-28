package models;

import java.util.Date;

public class Reservation {
    private int id;
    private int carId;
    private int userId;
    private Date reservationDate;
    private Date testDriveDate;

    public Reservation() {}

    public Reservation(int id, int carId, int userId, Date reservationDate, Date testDriveDate) {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.testDriveDate = testDriveDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getTestDriveDate() {
        return testDriveDate;
    }

    public void setTestDriveDate(Date testDriveDate) {
        this.testDriveDate = testDriveDate;
    }

    @Override
    public String toString() {
        return "Reservation: Car ID " + carId + ", User ID " + userId + ", Test Drive Date: " + testDriveDate;
    }
}

package controllers;

import models.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {
    private List<Reservation> reservations;

    public ReservationController() {
        this.reservations = new ArrayList<>();
    }

    public void createReservation(int carId, int userId, Date testDriveDate) {
        Reservation reservation = new Reservation(reservations.size() + 1, carId, userId, new Date(), testDriveDate);
        reservations.add(reservation);
        System.out.println("Reservation created: " + reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public boolean cancelReservation(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                reservations.remove(reservation);
                System.out.println("Reservation cancelled: " + reservation);
                return true;
            }
        }
        System.out.println("Reservation not found with ID: " + id);
        return false;
    }
}
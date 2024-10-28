package controllers;

import models.Car;
import java.util.ArrayList;
import java.util.List;

public class CarController {
    private List<Car> cars;

    public CarController() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Car added: " + car);
    }

    public boolean removeCar(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                cars.remove(car);
                System.out.println("Car removed: " + car);
                return true;
            }
        }
        System.out.println("Car not found with ID: " + id);
        return false;
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    public Car getCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }
}
public class Car {
    private String brand;
    private double fuelCapacity;
    private double currentFuel;
    private double fuelEfficiency; // km per liter

    public Car(String brand, double fuelCapacity, double fuelEfficiency) {
        this.brand = brand;
        this.fuelCapacity = fuelCapacity;
        this.fuelEfficiency = fuelEfficiency;
        this.currentFuel = 0;
    }

    public void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Invalid input! Refuel amount must be greater than zero.");
            return;
        }
        if (currentFuel + liters > fuelCapacity) {
            System.out.println("Tank overflow! Filling up to maximum capacity.");
            currentFuel = fuelCapacity;
        } else {
            currentFuel += liters;
            System.out.println("Refueled: " + liters + "L");
        }
        System.out.println("Current fuel: " + String.format("%.2f", currentFuel) + "L");
    }

    public void drive(double distance) {
        double fuelNeeded = distance / fuelEfficiency;
        if (fuelNeeded <= currentFuel) {
            currentFuel -= fuelNeeded;
            System.out.println(brand + " drove " + distance + " km using fuel.");
        } else {
            System.out.println("Not enough fuel to drive " + distance + " km.");
        }
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public boolean canDrive(double distance) {
        return (distance / fuelEfficiency) <= currentFuel;
    }

    public double estimateRange() {
        return currentFuel * fuelEfficiency;
    }

    public String getBrand() {
        return brand;
    }
}

public class ElectricCar extends Car {
    private double batteryLevel;
    private double batteryCapacity;
    private double energyConsumption; // km per kWh

    public ElectricCar(String brand, double batteryCapacity, double efficiencyPerKWh) {
        super(brand, 0, 0); // no fuel values
        this.batteryCapacity = batteryCapacity;
        this.energyConsumption = efficiencyPerKWh;
        this.batteryLevel = 0;
    }

    public void recharge(double kWh) {
        if (batteryLevel + kWh > batteryCapacity) {
            System.out.println("Battery full! Charging up to max capacity.");
            batteryLevel = batteryCapacity;
        } else {
            batteryLevel += kWh;
            System.out.println("Recharged: " + kWh + " kWh");
        }
        System.out.println("Current battery: " + String.format("%.2f", batteryLevel) + " kWh");
    }

    @Override
    public void refuel(double liters) {
        System.out.println("This car uses electricity, not fuel!");
    }

    @Override
    public void drive(double distance) {
        double energyNeeded = distance / energyConsumption;
        if (energyNeeded <= batteryLevel) {
            batteryLevel -= energyNeeded;
            System.out.println(getBrand() + " drove " + distance + " km using battery.");
        } else {
            System.out.println("Not enough battery to drive " + distance + " km.");
        }
    }

    @Override
    public double estimateRange() {
        return batteryLevel * energyConsumption;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }
}

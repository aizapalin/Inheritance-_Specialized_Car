public class HybridCar extends Car {
    private double batteryLevel;
    private double batteryCapacity;
    private double energyConsumption; // km per kWh

    public HybridCar(String brand, double fuelCapacity, double fuelEfficiency,
                     double batteryCapacity, double efficiencyPerKWh) {
        super(brand, fuelCapacity, fuelEfficiency);
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
    public void drive(double distance) {
        double fuelRange = super.estimateRange();
        if (distance <= fuelRange) {
            super.drive(distance); // fuel is enough
        } else {
            // use all fuel first
            if (fuelRange > 0) {
                super.drive(fuelRange);
                distance -= fuelRange;
            }
            // then use battery
            double energyNeeded = distance / energyConsumption;
            if (energyNeeded <= batteryLevel) {
                batteryLevel -= energyNeeded;
                System.out.println(getBrand() + " continued " + distance + " km on battery.");
            } else {
                System.out.println("Not enough battery to finish the trip.");
            }
        }
    }

    @Override
    public double estimateRange() {
        return super.estimateRange() + (batteryLevel * energyConsumption);
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Car Simulator ===");
            System.out.println("Choose a car type:");
            System.out.println("1. Fuel Car");
            System.out.println("2. Electric Car");
            System.out.println("3. Hybrid Car");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = 0;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine()); 
                    if (choice >= 1 && choice <= 4) break;
                    else System.out.print("Invalid choice. Please enter 1–4: ");
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number (1–4): ");
                }
            }

            if (choice == 4) {
                System.out.println("Exiting program...");
                running = false;
                break;
            }

            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();

            if (choice == 1) { // Fuel Car
                double capacity = readDouble(scanner, "Enter fuel capacity (L): ");
                double efficiency = readDouble(scanner, "Enter fuel efficiency (km/L): ");
                Car fuelCar = new Car(brand, capacity, efficiency);
                simulateFuelCar(fuelCar, scanner);
            } 
            else if (choice == 2) { // Electric Car
                double batteryCap = readDouble(scanner, "Enter battery capacity (kWh): ");
                double efficiency = readDouble(scanner, "Enter efficiency (km/kWh): ");
                ElectricCar eCar = new ElectricCar(brand, batteryCap, efficiency);
                simulateElectricCar(eCar, scanner);
            } 
            else if (choice == 3) { // Hybrid Car
                double fuelCap = readDouble(scanner, "Enter fuel capacity (L): ");
                double fuelEff = readDouble(scanner, "Enter fuel efficiency (km/L): ");
                double batteryCap = readDouble(scanner, "Enter battery capacity (kWh): ");
                double effPerKWh = readDouble(scanner, "Enter efficiency (km/kWh): ");
                HybridCar hCar = new HybridCar(brand, fuelCap, fuelEff, batteryCap, effPerKWh);
                simulateHybridCar(hCar, scanner);
            }
        }

        scanner.close(); // ✅ close only once at the end
    }

    // Helper method to safely read doubles
    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // --- Simulation methods ---
    private static void simulateFuelCar(Car car, Scanner scanner) {
        System.out.println("\nCreated Fuel Car: " + car.getBrand());
        car.refuel(readDouble(scanner, "Enter amount of fuel to add: "));
        car.drive(readDouble(scanner, "Enter distance to drive: "));
        System.out.println("Remaining fuel: " + car.getCurrentFuel() + " L");
        System.out.println("Estimated range: " + car.estimateRange() + " km");
    }

    private static void simulateElectricCar(ElectricCar car, Scanner scanner) {
        System.out.println("\nCreated Electric Car: " + car.getBrand());
        car.recharge(readDouble(scanner, "Enter amount of charge to add (kWh): "));
        car.drive(readDouble(scanner, "Enter distance to drive: "));
        System.out.println("Remaining battery: " + car.getBatteryLevel() + " kWh");
        System.out.println("Estimated range: " + car.estimateRange() + " km");
    }

    private static void simulateHybridCar(HybridCar car, Scanner scanner) {
        System.out.println("\nCreated Hybrid Car: " + car.getBrand());
        car.refuel(readDouble(scanner, "Enter amount of fuel to add: "));
        car.recharge(readDouble(scanner, "Enter amount of charge to add (kWh): "));
        car.drive(readDouble(scanner, "Enter distance to drive: "));
        System.out.println("Remaining fuel: " + car.getCurrentFuel() + " L");
        System.out.println("Remaining battery: " + car.getBatteryLevel() + " kWh");
        System.out.println("Estimated range: " + car.estimateRange() + " km");
    }
}

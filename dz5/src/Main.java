import java.util.Scanner;

interface IVehicle {
    void Drive();
    void Refuel();
}

class Car implements IVehicle {
    private String brand;
    private String model;
    private String fuelType;

    public Car(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public void Drive() {
        System.out.println("Водит " + brand + " " + model + ".");
    }

    @Override
    public void Refuel() {
        System.out.println("Заправляет " + fuelType + "бензином.");
    }
}

class Motorcycle implements IVehicle {
    private String type;
    private int engineCapacity;

    public Motorcycle(String type, int engineCapacity) {
        this.type = type;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void Drive() {
        System.out.println("Водит " + type + " мотоцикл с" + engineCapacity + "кубовым двигателем.");
    }

    @Override
    public void Refuel() {
        System.out.println("Заправка мотоцикла.");
    }
}

class Truck implements IVehicle {
    private int payloadCapacity;
    private int numberOfAxles;

    public Truck(int payloadCapacity, int numberOfAxles) {
        this.payloadCapacity = payloadCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    @Override
    public void Drive() {
        System.out.println("Вождение грузового автомобиля грузоподъемностью " + payloadCapacity + "тонн.");
    }

    @Override
    public void Refuel() {
        System.out.println("Заправка грузового автомобиля.");
    }
}

abstract class VehicleFactory {
    public abstract IVehicle CreateVehicle();
}

class CarFactory extends VehicleFactory {
    private String brand;
    private String model;
    private String fuelType;

    public CarFactory(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public IVehicle CreateVehicle() {
        return new Car(brand, model, fuelType);
    }
}

class MotorcycleFactory extends VehicleFactory {
    private String type;
    private int engineCapacity;

    public MotorcycleFactory(String type, int engineCapacity) {
        this.type = type;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public IVehicle CreateVehicle() {
        return new Motorcycle(type, engineCapacity);
    }
}

class TruckFactory extends VehicleFactory {
    private int payloadCapacity;
    private int numberOfAxles;

    public TruckFactory(int payloadCapacity, int numberOfAxles) {
        this.payloadCapacity = payloadCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    @Override
    public IVehicle CreateVehicle() {
        return new Truck(payloadCapacity, numberOfAxles);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип транспорта: 1 - Car, 2 - Motorcycle, 3 - Truck");
        int choice = scanner.nextInt();

        IVehicle vehicle = null;

        switch (choice) {
            case 1:
                System.out.println("Введите марку автомобиля:");
                String carBrand = scanner.next();
                System.out.println("Введите модель автомобиля:");
                String carModel = scanner.next();
                System.out.println("Введите тип топлива:");
                String carFuelType = scanner.next();

                CarFactory carFactory = new CarFactory(carBrand, carModel, carFuelType);
                vehicle = carFactory.CreateVehicle();
                break;

            case 2:
                System.out.println("Введите тип мотоцикла:");
                String motoType = scanner.next();
                System.out.println("Введите объем двигателя (в куб.см):");
                int motoEngineCapacity = scanner.nextInt();

                MotorcycleFactory motoFactory = new MotorcycleFactory(motoType, motoEngineCapacity);
                vehicle = motoFactory.CreateVehicle();
                break;

            case 3:
                System.out.println("Введите грузоподъемность (в тоннах):");
                int truckPayloadCapacity = scanner.nextInt();
                System.out.println("Введите количество осей:");
                int truckAxles = scanner.nextInt();

                TruckFactory truckFactory = new TruckFactory(truckPayloadCapacity, truckAxles);
                vehicle = truckFactory.CreateVehicle();
                break;

            default:
                System.out.println("Неверный выбор!");
                break;
        }

        if (vehicle != null) {
            vehicle.Drive();
            vehicle.Refuel();
        }

        scanner.close();
    }
}

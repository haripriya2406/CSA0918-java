interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    // Every started hour is charged in full.
    // Integer arithmetic is used to calculate the ceiling of minutes / 60.
    public double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative.");
        }

        int billableHours = (minutes + 59) / 60;
        return billableHours * ratePerHour();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Vehicle)) {
            return false;
        }

        Vehicle other = (Vehicle) object;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

class ElectricCar extends Car implements Chargeable {
    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {
        if (kWh < 0) {
            throw new IllegalArgumentException("kWh cannot be negative.");
        }

        return kWh * 12;
    }
}

public class FeeDemo {
    public static void main(String[] args) {
        Vehicle[] parked = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        for (Vehicle vehicle : parked) {
            double fee = vehicle.calculateFee(130);

            // Java 8-compatible interface check and cast.
            if (vehicle instanceof Chargeable) {
                Chargeable chargeable = (Chargeable) vehicle;
                fee += chargeable.chargingFee(10);
            }

            System.out.println(
                vehicle.getClass().getSimpleName()
                + " " + vehicle.getRegNo()
                + " -> Rs " + fee
            );
        }

        System.out.println(
            new Car("TN02B2").equals(new ElectricCar("TN02B2"))
        );
    }
}

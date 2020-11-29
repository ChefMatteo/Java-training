abstract class Vehicle {
    String plate;
    double price;
    private int tank = 100;
    int percentOfFuel = tank;
    Status status = Status.AVAILABLE;
    int percentConsumptionForMinutes;

    protected Vehicle() {
    }

    public void fuelConsumption(int minutes){
        percentOfFuel -= percentConsumptionForMinutes *minutes;
    }

    @Override
    public String toString() {
        return "Veicolo con targa "+ plate + " per minuti ";
    }
}

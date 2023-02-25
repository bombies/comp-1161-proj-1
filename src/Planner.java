import java.util.ArrayList;
import java.util.Arrays;

public class Planner {

    private String name;
    private double budget;
    private Ministry mny;
    private Bus[] buses;
    private ArrayList<Bus> possibleBuses;

    public Planner(String name, double budget, Ministry mny, Bus[] buses) {
        this.name = name;
        this.budget = budget;
        this.mny = mny;
        this.buses = buses;
        possibleBuses = new ArrayList<Bus>();
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public int planTrip(int numPassengers, String tripType, Date date, int comfortLevel) {
        int retval = -1;
        possibleBuses.clear();
        /*
         * //populate possible buses by adding busses that are suitable,
         * // can hold the passengers at the comfortlevel,and can be afforded
         */

        possibleBuses.addAll(Arrays.stream(buses)
            .filter(bus -> 
                bus.getEstimate(tripType, numPassengers, comfortLevel) <= this.budget 
                && bus.isSuitable(tripType) 
            )
            .toList()
        );

        // System.out.println(possibleBuses.size() +" affordable buses: " + possibleBuses.toString());
        if (possibleBuses.size() > 0) {
            /*
             * //find the suitable bus with minimum price and assign to minbus`
             */
            Bus minBus = possibleBuses.stream()
                .filter(bus -> bus.available(date) && bus.canHold(numPassengers, comfortLevel))    
                .reduce((cur, next) -> cur.getEstimate(tripType, numPassengers, comfortLevel) < next.getEstimate(tripType, numPassengers, comfortLevel) ?  cur : next)
                .orElse(null);
            
            if (minBus == null)
                System.out.println("No suitable bus is available");
            else {
                // create a trp with the type, number of passengers and the comfortlevel
                // attempt to get an approval id by calling the reserve method of the bus
                // if the id >=0
                Trip trip = new Trip(tripType, numPassengers, date, comfortLevel, this);
                int reserved = minBus.reserve(trip, mny);
                if (minBus.getId() >= 0 &&  reserved >= 0) {
                    trip.setBus(minBus);
                    payFor(minBus, tripType, numPassengers, comfortLevel);
                    retval = reserved;
                    System.out.println(name + " successfully reserved " + trip);
                }
            }
        } else System.out.println(name + " cannot afford to pay for any suitable buses");
        return retval;
    }

    public void payFor(Bus bus, String tripType, int numPassengers, int comfortLevel) {
        budget -= bus.getEstimate(tripType, numPassengers, comfortLevel);
    }
}

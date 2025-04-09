import java.util.concurrent.TimeUnit;

class TrafficLight {
    private String currentState;

    public TrafficLight() {
        currentState = "RED";  // Default state
    }

    // Method to change the state of the traffic light
    public void changeState() {
        if (currentState.equals("RED")) {
            currentState = "GREEN";
        } else if (currentState.equals("GREEN")) {
            currentState = "YELLOW";
        } else {
            currentState = "RED";
        }
    }

    // Method to get the current state of the traffic light
    public String getCurrentState() {
        return currentState;
    }
}

class Car {
    private String direction;

    public Car(String direction) {
        this.direction = direction;
    }

    // Method to simulate car approaching the intersection
    public void approachIntersection(TrafficLight light) {
        System.out.println("A car is approaching the intersection from the " + direction + ".");
        System.out.println("Current traffic light state: " + light.getCurrentState());
        
        // Simulate the car waiting for the light to turn green
        while (!light.getCurrentState().equals("GREEN")) {
            System.out.println("Car waiting for the green light...");
            try {
                TimeUnit.SECONDS.sleep(1);  // Wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The car can now pass through the intersection.");
    }
}

class TrafficManagementSystem {
    private TrafficLight trafficLight;

    public TrafficManagementSystem() {
        trafficLight = new TrafficLight();
    }

    // Method to simulate traffic management
    public void startManagingTraffic() {
        while (true) {
            System.out.println("\n--- Changing traffic light ---");
            trafficLight.changeState();
            
            // Display current traffic light status
            System.out.println("The traffic light is now: " + trafficLight.getCurrentState());
            
            // Simulate cars approaching the intersection
            Car car1 = new Car("North");
            Car car2 = new Car("East");
            
            car1.approachIntersection(trafficLight);
            car2.approachIntersection(trafficLight);
            
            // Wait for some time before changing the light again
            try {
                TimeUnit.SECONDS.sleep(5);  // Wait for 5 seconds before changing the light again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TrafficManagement {
    public static void main(String[] args) {
        TrafficManagementSystem trafficSystem = new TrafficManagementSystem();
        trafficSystem.startManagingTraffic();  // Start traffic management
    }
}

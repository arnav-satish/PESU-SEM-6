// Smart Home Automation - Single File Implementation

import java.util.*;

// Device Interface
interface Device {
    String getName();
    boolean getStatus();
}

// Abstract SmartDevice Class
abstract class SmartDevice implements Device {
    protected int Device_ID;
    protected String Device_Name;
    protected boolean Device_State;
    
    public SmartDevice(int Device_ID, String Device_Name) {
        this.Device_ID = Device_ID;
        this.Device_Name = Device_Name;
        this.Device_State = false; // Default off
    }
    
    public String getName() {
        return Device_Name;
    }
    
    public boolean getStatus() {
        return Device_State;
    }
    
    public void switchOn() {
        Device_State = true;
    }
    
    public void switchOff() {
        Device_State = false;
    }
}

// SmartLight Class
class SmartLight extends SmartDevice {
    private int Brightness_Level;
    
    public SmartLight(int Device_ID, String Device_Name) {
        super(Device_ID, Device_Name);
        this.Brightness_Level = 50; // Default brightness
    }
    
    public void setBrightness(int level) {
        this.Brightness_Level = level;
    }
}

// Thermostat Class
class Thermostat extends SmartDevice {
    private int Temperature_Level;
    
    public Thermostat(int Device_ID, String Device_Name) {
        super(Device_ID, Device_Name);
        this.Temperature_Level = 22; // Default temperature
    }
    
    public void setTemperature(int temp) {
        this.Temperature_Level = temp;
    }
}

// SecurityCamera Class
class SecurityCamera extends SmartDevice {
    private boolean Recording_Status;
    
    public SecurityCamera(int Device_ID, String Device_Name) {
        super(Device_ID, Device_Name);
        this.Recording_Status = false;
    }
    
    public void startRecording() {
        Recording_Status = true;
    }
    
    public void stopRecording() {
        Recording_Status = false;
    }
}

// Home Class
class Home {
    private List<SmartDevice> devices;
    
    public Home() {
        devices = new ArrayList<>();
    }
    
    public void addDevice(SmartDevice device) {
        devices.add(device);
    }
    
    public void removeDevice(int Device_ID) {
        devices.removeIf(device -> device.Device_ID == Device_ID);
    }
    
    public void switchAll(boolean state) {
        for (SmartDevice device : devices) {
            if (state) {
                device.switchOn();
            } else {
                device.switchOff();
            }
        }
    }
    
    public void displayDevicesSorted() {
        devices.sort((a, b) -> Boolean.compare(b.getStatus(), a.getStatus()));
        for (SmartDevice device : devices) {
            System.out.println(device.getName() + " - Status: " + (device.getStatus() ? "ON" : "OFF"));
        }
    }
}

// Main Class
public class SmartHomeSystem {
    public static void main(String[] args) {
        Home home = new Home();
        
        SmartLight light = new SmartLight(1, "Living Room Light");
        Thermostat thermostat = new Thermostat(2, "Home Thermostat");
        SecurityCamera camera = new SecurityCamera(3, "Front Door Camera");
        
        home.addDevice(light);
        home.addDevice(thermostat);
        home.addDevice(camera);
        
        System.out.println("Adding devices to home...");
        home.displayDevicesSorted();
        
        System.out.println("\nSwitching all devices ON...");
        home.switchAll(true); 
        home.displayDevicesSorted();
        
        System.out.println("\nChanging brightness level of light...");
        light.setBrightness(80);
        System.out.println("Living Room Light brightness set to 80");
        
        System.out.println("\nSetting thermostat temperature...");
        thermostat.setTemperature(25);
        System.out.println("Home Thermostat temperature set to 25");
        
        System.out.println("\nStarting security camera recording...");
        camera.startRecording();
        System.out.println("Front Door Camera is now recording");
        
        System.out.println("\nSwitching all devices OFF...");
        home.switchAll(false); 
        home.displayDevicesSorted();
        
        System.out.println("\nRemoving the thermostat from home...");
        home.removeDevice(2);
        home.displayDevicesSorted();
    }
}
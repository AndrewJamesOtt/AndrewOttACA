package com.andrewott.android.aca.marsrobot;

public class MarsRobot {
    String status;
    int speed;
    float temperature;
    int tireWear;
    int lights;

    void checkTemperature() {
        if (temperature < -80) {
            status = "returning home";
            speed = 5;
        }
    }
    void checkLights(){
        if (lights < 4){
            status = "changing the bulbs";
            speed = 4;
        }
    }
    void showAttributes() {
        System.out.println("Status: " + status);
        System.out.println("Speed: " + speed);
        System.out.println("Temperature: " + temperature);
        System.out.println("Tire Wear: " + tireWear);
        System.out.println("Lights Shining: " + lights);
    }
}





package com.example.prasad.firstweatyahoo.Model;

/**
 * Created by Prasad on 10/3/2017.
 */

public class Wind {
    private double speed;
    private  double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}

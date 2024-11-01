package com.excercise.tram;

import java.time.LocalTime;

public class Tram {

    private int tramId;
    private String tramNumber;
    private String direction;
    private LocalTime arrivalTime;
    private int delayMins;

    public Tram(int tramId,String tramNumber, String direction, LocalTime arrivalTime) {
        this.tramId = tramId;
        this.tramNumber = tramNumber;
        this.direction = direction;
        this.arrivalTime = arrivalTime;
    }

    public int getTramId() {
        return tramId;
    }

    public void setTramId(int tramId) {
        this.tramId = tramId;
    }

    public String getTramNumber() {
        return tramNumber;
    }

    public void setTramNumber(String tramNumber) {
        this.tramNumber = tramNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void applyDelay(int delayMins){
        this.delayMins = delayMins;
        this.arrivalTime = this.arrivalTime.plusMinutes(delayMins);
    }


    public String toString() {
        return "Tram id "+ tramId +" Tram: "+ tramNumber + " on route " + direction +
                " is arriving at " + getArrivalTime() +
                " (Delay: " + delayMins + " mins)";
    }
}

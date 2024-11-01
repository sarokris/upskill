package com.excercise.tram;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTable {
    private List<Tram> tramList ;
    private final ScheduledExecutorService scheduler;

    public TimeTable() {
        tramList = new ArrayList<>();
        scheduler = Executors.newScheduledThreadPool(1);
    }

    private void startTimetableUpdate() {
        scheduler.scheduleAtFixedRate(() -> displayTimeTable(), 0, 10, TimeUnit.SECONDS);
    }

    public void addTramWithFrequency(Tram tram, int frequencyInMin){
        LocalTime endTime = tram.getArrivalTime().plusHours(1);
        int tramId = tram.getTramId();
        for(LocalTime latestTime = tram.getArrivalTime(); latestTime.isBefore(endTime);latestTime = latestTime.plusMinutes(frequencyInMin)){
            this.tramList.add(new Tram(tramId,tram.getTramNumber(),tram.getDirection(),latestTime));
//            latestTime = latestTime.plusMinutes(frequencyInMin);
            tramId += 1;
        }
    }

    public static void main(String[] args) {
        Tram tram16 = new Tram(100,"16","DenHaag Central", LocalTime.of(8,0));
        Tram tram9 = new Tram(200,"9","Scheviningen", LocalTime.of(8,5));
        TimeTable timeTable = new TimeTable();
        timeTable.addTramWithFrequency(tram16,10);
        timeTable.addTramWithFrequency(tram9,5);
        timeTable.startTimetableUpdate();

        // Apply a delay to Tram 9 in a separate thread after 30 seconds
        new Thread(() -> {
            try {
                Thread.sleep(20000); // Wait for 30 seconds
                timeTable.applyDelayToTram(101, 2); // Delay Tram 9 by 2 minutes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Main thread continues to run for a while
        try {
            Thread.sleep(60000); // Run for 1  minutes to see updates
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeTable.shutdown(); // Shutdown the scheduler
        }

    }

    private void displayTimeTable() {
        System.out.println("Current Tram Timetable (Sorted by Frequency):");
        // Sort trams based on frequency (ascending order)
        tramList.stream()
                .sorted(Comparator.comparing(Tram::getArrivalTime))
                .limit(4)
                .forEach(tram -> System.out.println(tram));
        System.out.println("----------------------");

    }

    public void applyDelayToTram(int tramId, int delayMinutes) {
        // Using a separate thread to apply the delay
        new Thread(() -> {
            for (Tram tram : tramList) {
                if (tram.getTramId() == tramId) {
                    tram.applyDelay(delayMinutes);
                    System.out.println("Delay applied to Tram " + tramId);
                }
            }
        }).start();
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }


}

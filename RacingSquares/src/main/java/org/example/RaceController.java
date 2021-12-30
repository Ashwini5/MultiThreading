package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class RaceController {

    private ArrayList<Racer> racers;

    public RaceController(int numberOfRacers) {
        this.initializeRacers(numberOfRacers);
    }

    public int getNumberOfRacers(){
        return this.racers.size();
    }

    public void initializeRacers(int numberOfRacers) {
        this.racers = new ArrayList<>();
        for (int i=0; i < numberOfRacers; i++) {
            this.racers.add(new Racer());
        }
    }

    public ArrayList<Integer> getRacerPositions() {
        return (ArrayList<Integer>) this.racers.stream().map(Racer::getPosition).collect(Collectors.toList());
    }

    public ArrayList<Boolean> getRacersWinningStatus() {
        return (ArrayList<Boolean>) this.racers.stream().map(Racer::getIsWinner).collect(Collectors.toList());
    }

   public void startRace() {
       ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(racers.size());
       int numberOfRacers = racers.size();
       for (int i=0; i < numberOfRacers; i++) {
           threadPoolExecutor.execute(racers.get(i));
       }
   }
}

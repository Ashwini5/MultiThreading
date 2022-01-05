package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class RaceController {

    public class RacerPositions extends ArrayList<ImmutablePair<Integer, Integer>> {}
    private class RacerPositionsWithId extends ArrayList<ImmutablePair<Integer, ImmutablePair<Integer, Integer>>> {}

    private ArrayList<Racer> racers;
    private final Lock pauseRacersLock = new ReentrantLock();
    private final Condition condition = pauseRacersLock.newCondition();
    private AtomicBoolean pauseRacers;

    public RaceController(int numberOfRacers) {
        this.pauseRacers = new AtomicBoolean(false);
        this.initializeRacers(numberOfRacers);
    }

    public int getNumberOfRacers(){
        return this.racers.size();
    }

    public void initializeRacers(int numberOfRacers) {
        this.racers = new ArrayList<>();
        for (int i=0; i < numberOfRacers; i++) {
            this.racers.add(new Racer(this.pauseRacersLock, this.condition, this.pauseRacers));
        }
    }

    private RacerPositions computeRanks(RacerPositionsWithId racerPositionsWithId) {
        RacerPositionsWithId unfinishedRacers = racerPositionsWithId.stream().filter(r -> r.right.left == 0).collect(Collectors.toCollection(RacerPositionsWithId::new));
        RacerPositionsWithId finishedRacers = racerPositionsWithId.stream().filter(r -> r.right.left != 0).collect(Collectors.toCollection(RacerPositionsWithId::new));
        Integer maxFinishedRacerRank = racerPositionsWithId.stream().mapToInt(r -> r.right.left).max().orElse(0);

        unfinishedRacers.sort(Comparator.comparingInt(x -> x.right.right));
        Collections.reverse(unfinishedRacers);
        for (int i=0; i < unfinishedRacers.size(); i++) {
            Integer racerId = unfinishedRacers.get(i).left;
            Integer racerPosition = unfinishedRacers.get(i).right.right;
            unfinishedRacers.set(i, new ImmutablePair<>(racerId, new ImmutablePair<>(maxFinishedRacerRank + i + 1, racerPosition)));
        }

        return Stream.of(finishedRacers, unfinishedRacers)
            .flatMap(r -> r.stream())
            .sorted(Comparator.comparingInt(r -> r.left))
            .map(r -> r.right)
            .collect(Collectors.toCollection(RacerPositions::new));
    }

    public RacerPositions getRacerPositions() {
        RacerPositionsWithId racerPositionsWithId;
        this.pauseRace();
        racerPositionsWithId =
            this.racers
                .stream()
                .map(x -> new ImmutablePair<>(x.getId(), new ImmutablePair<>(x.getRank(), x.getPosition())))
                .collect(Collectors.toCollection(RacerPositionsWithId::new));
        this.unpauseRace();
        return computeRanks(racerPositionsWithId);
    }

//    public ArrayList<Boolean> getRacersWinningStatus() {
//        return (ArrayList<Boolean>) this.racers.stream().map(Racer::getIsWinner).collect(Collectors.toList());
//    }

   public void startRace() {
       ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(racers.size());

       int numberOfRacers = racers.size();
       for (int i=0; i < numberOfRacers; i++) {
           threadPoolExecutor.execute(racers.get(i));
       }
   }

   public void pauseRace() {
       while(!this.pauseRacers.compareAndSet(false, true));
   }

   public void unpauseRace() {
        this.pauseRacersLock.lock();
        try {
            if (this.pauseRacers.compareAndSet(true, false)) {
                condition.signalAll();
            }
       } finally {
            pauseRacersLock.unlock();
        }
   }
}

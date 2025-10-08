package dev.animals;

import java.util.*;

/**
 * The RaceManager class is a singleton used to manage the state of the race and its participants.
 */
public class RaceManager {

    private final int maxLaps = 3;

    private int currentLap = 0;

    private List<Animal> animals = new ArrayList<>();

    private boolean raceIsOver = false;

    private Animal lapWinner;

    private Map<String, Integer> raceWinners = new TreeMap<>();

    private static RaceManager instance;

    public static RaceManager getInstance() {
        if (instance == null) {
            instance = new RaceManager();
        }
        return instance;
    }

    /**
     * check if there is more laps to be played
     * @return true if there is, else false
     */
    public boolean noMoreLaps() {
        return this.currentLap >= this.maxLaps;
    }

    /**
     * Ends the lap and prints the results.
     */
    public void endLap() {
        System.out.println("Manche " + (currentLap + 1) + " terminée !");
        System.out.println("Vainqueur : " + this.lapWinner);
        System.out.println("Classement :");
        List<String> lapLeaderboard = getLapLeaderboard();
        for (String name : lapLeaderboard) {
            System.out.println("Numéro " + (lapLeaderboard.indexOf(name) + 1) + " : " + name);
        }
        System.out.println("État final de la manche :");
        for (Animal animal : this.animals) {
            System.out.println(animal.createPositionString());
        }
        System.out.println("======================================================================================================================================");
            resetAnimals();
            this.currentLap++;

    }

    /**
     * Ends the race and prints the results.
     */
    public void endRace() {
        System.out.println("Course terminée !");
        System.out.println("Nombre total de manches : " + maxLaps);
        System.out.println("Nombre de victoires :");
        for (Map.Entry<String, Integer> entry : getRaceWinnerSorted() ) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("Le gagnant est : " + getRaceWinner() + " !");
    }

    /**
     * reset all animals position
     */
    public void resetAnimals() {
        for (Animal animal : animals) {
            animal.setPosition(0);
        }
    }

    /**
     * Generate a list of String of the animals sorted by their position in the race.
     *
     * @return List of String
     */
    public List<String> getLapLeaderboard() {
        return animals.stream()
                .sorted((a, b) -> Double.compare(b.getPosition(), a.getPosition()))
                .map(Animal::toString)
                .toList();
    }

    public String getRaceWinner(){
        return raceWinners.keySet().stream().findFirst().get();
    }

    public List<Map.Entry<String, Integer>> getRaceWinnerSorted()
    {
        return raceWinners.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }
    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public boolean isRaceOver() {
        return raceIsOver;
    }

    public void setRaceIsOver(boolean raceIsOver) {
        this.raceIsOver = raceIsOver;
    }

    public Animal getLapWinner() {
        return lapWinner;
    }

    public void setLapWinner(Animal lapWinner) {
        if (this.lapWinner == null) {
            this.lapWinner = lapWinner;
        }
    }

    public Map<String, Integer> getRaceWinners() {
        return raceWinners;
    }

    public void addRaceWinner(Animal winner) {
        this.raceWinners.put(winner.toString(), this.raceWinners.getOrDefault(winner.toString(), 0) + 1);
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public int getMaxLaps() {
        return maxLaps;
    }
}

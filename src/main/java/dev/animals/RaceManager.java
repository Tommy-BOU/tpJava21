package dev.animals;

import java.util.ArrayList;
import java.util.List;

/**
 * The RaceManager class is a singleton used to manage the state of the race and its participants.
 */
public class RaceManager {
    private List<Animal> animals = new ArrayList<>();

    private boolean raceIsOver = false;

    private Animal winner;
    private List<Animal> winners = new ArrayList<>();

    private static RaceManager instance;

    public static RaceManager getInstance() {
        if (instance == null) {
            instance = new RaceManager();
        }
        return instance;
    }

    /**
     * Ends the race and prints the results.
     */
    public void endRace(){
        System.out.println("Course terminée !");
        System.out.println("Vainqueur : " + this.winner);
        System.out.println("Classement :");
        List<String> leaderboard = getLeaderboard();
        for (String name : leaderboard){
            System.out.println("Numéro " + (leaderboard.indexOf(name) + 1) + " : " + name);
        }
        System.out.println("État final de la course :");
        for (Animal animal : this.animals)
        {
            System.out.println(animal.createPositionString());
        }
    }

    /**
     * Generate a list of String of the animals sorted by their position in the race.
     * @return List of String
     */
    public List<String> getLeaderboard(){
        return animals.stream()
                .sorted((a, b) -> Double.compare(b.getPosition(), a.getPosition()))
                .map(Animal::toString)
                .toList();
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

    public Animal getWinner() {
        return winner;
    }

    public void setWinner(Animal winner) {
        if (this.winner == null) {
            this.winner = winner;
        }
    }

    public List<Animal> getWinners() {
        return winners;
    }

    public void setWinners(List<Animal> winners) {
        this.winners = winners;
    }

    public void addWinner(Animal winner) {
        this.winners.add(winner);
    }
}

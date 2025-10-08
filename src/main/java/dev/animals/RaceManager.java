package dev.animals;

import java.util.ArrayList;
import java.util.List;

public class RaceManager {
    private List<Animal> animals = new ArrayList<>();

    private boolean raceIsOver = false;

    private Animal winner;

    private static RaceManager instance;

    public static RaceManager getInstance() {
        if (instance == null) {
            instance = new RaceManager();
        }
        return instance;
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
}

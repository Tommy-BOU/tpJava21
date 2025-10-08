package dev;

import dev.animals.Animal;
import dev.animals.Horse;
import dev.animals.Rabbit;
import dev.animals.Turtle;

public class Main {
    public static void main(String[] args) {
        Turtle turtle = new Turtle();
        Rabbit rabbit = new Rabbit();
        Horse horse = new Horse();

        Thread t1 = new Thread(turtle::run);
        Thread t2 = new Thread(rabbit::run);
        Thread t3 = new Thread(horse::run);

        t1.start();
        t2.start();
        t3.start();

    }
}
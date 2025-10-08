package dev.animals;

import java.util.Random;

public final class Rabbit extends Animal{
    private final String name = "Lapin";
    private final String icon = "🐇";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

}

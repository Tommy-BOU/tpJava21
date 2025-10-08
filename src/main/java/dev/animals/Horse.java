package dev.animals;

import java.util.Random;

public final class Horse extends Animal{

    private final String name = "Cheval";
    private final String icon = "🐎";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

}

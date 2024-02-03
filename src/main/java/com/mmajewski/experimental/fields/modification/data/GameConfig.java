package com.mmajewski.experimental.fields.modification.data;

import java.util.Arrays;
import java.util.Objects;

public final class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;
    private String[] characterNames;

    public int releaseYear() {
        return releaseYear;
    }

    public String gameName() {
        return gameName;
    }

    public double price() {
        return price;
    }

    public String[] characterNames() {
        return characterNames;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GameConfig) obj;
        return this.releaseYear == that.releaseYear &&
                Objects.equals(this.gameName, that.gameName) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price) &&
                Arrays.equals(this.characterNames, that.characterNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseYear, gameName, price, Arrays.hashCode(characterNames));
    }

    @Override
    public String toString() {
        return "GameConfig[" +
                "releaseYear=" + releaseYear + ", " +
                "gameName=" + gameName + ", " +
                "price=" + price + ", " +
                "characterName=" + Arrays.toString(characterNames) + ']';
    }
}

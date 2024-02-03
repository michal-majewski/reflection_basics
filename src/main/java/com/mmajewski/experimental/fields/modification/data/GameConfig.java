package com.mmajewski.experimental.fields.modification.data;

import java.util.Objects;

public final class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;

    public int releaseYear() {
        return releaseYear;
    }

    public String gameName() {
        return gameName;
    }

    public double price() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GameConfig) obj;
        return this.releaseYear == that.releaseYear &&
                Objects.equals(this.gameName, that.gameName) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseYear, gameName, price);
    }

    @Override
    public String toString() {
        return "GameConfig[" +
                "releaseYear=" + releaseYear + ", " +
                "gameName=" + gameName + ", " +
                "price=" + price + ']';
    }

}

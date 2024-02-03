package com.mmajewski.experimental.fields.modification.data;

import java.util.Arrays;
import java.util.Objects;

public final class UserInterfaceConfig {
    private String titleText;
    private String[] titleFonts;
    private short[] titleTextSizes;

    public String titleText() {
        return titleText;
    }

    public String[] titleFonts() {
        return titleFonts;
    }

    public short[] titleTextSizes() {
        return titleTextSizes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserInterfaceConfig) obj;
        return Objects.equals(this.titleText, that.titleText) &&
                Arrays.equals(this.titleFonts, that.titleFonts) &&
                Arrays.equals(this.titleTextSizes, that.titleTextSizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleText, Arrays.hashCode(titleFonts), Arrays.hashCode(titleTextSizes));
    }

    @Override
    public String toString() {
        return "UserInterfaceConfig[" +
                "titleText=" + titleText + ", " +
                "titleFonts=" + Arrays.toString(titleFonts) + ", " +
                "titleTextSizes=" + Arrays.toString(titleTextSizes) + ']';
    }
}

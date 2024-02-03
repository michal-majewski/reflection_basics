package com.mmajewski.experimental.fields.modification.data;

import java.util.Objects;

public final class UserInterfaceConfig {
    private String titleColor;
    private String footerText;
    private short titleFontSize;
    private short footerFontSize;

    public String titleConfig() {
        return titleColor;
    }

    public String footerText() {
        return footerText;
    }

    public short titleFontSize() {
        return titleFontSize;
    }

    public short footerFontSize() {
        return footerFontSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserInterfaceConfig) obj;
        return Objects.equals(this.titleColor, that.titleColor) &&
                Objects.equals(this.footerText, that.footerText) &&
                this.titleFontSize == that.titleFontSize &&
                this.footerFontSize == that.footerFontSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleColor, footerText, titleFontSize, footerFontSize);
    }

    @Override
    public String toString() {
        return "UserInterfaceConfig[" +
                "titleConfig=" + titleColor + ", " +
                "footerText=" + footerText + ", " +
                "titleFontSize=" + titleFontSize + ", " +
                "footerFontSize=" + footerFontSize + ']';
    }

}

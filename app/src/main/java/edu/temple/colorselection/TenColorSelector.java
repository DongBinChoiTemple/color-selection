package edu.temple.colorselection;

import android.graphics.Color;

public class TenColorSelector implements ColorSelector {
    public int stringToColor(String s){
        switch (s.toLowerCase()) {
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
            case "green":
                return Color.GREEN;
            case "cyan":
                return Color.CYAN;
            case "blue":
                return Color.BLUE;
            case "magenta":
                return Color.MAGENTA;
            case "white":
                return Color.WHITE;
            case "light gray":
                return Color.LTGRAY;
            case "dark gray":
                return Color.DKGRAY;
            case "black":
                return Color.BLACK;
            default:
                return Color.TRANSPARENT;
        }
    }
}

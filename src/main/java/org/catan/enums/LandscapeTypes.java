package org.catan.enums;

import lombok.Getter;

import java.awt.Color;

@Getter
public enum LandscapeTypes {
    MOUNTAINS(new Color(128, 128, 128)),   // Grau
    FIELDS(new Color(255, 255, 102)),      // Hellgelb
    PASTURE(new Color(153, 255, 153)),     // Hellgrün
    FOREST(new Color(34, 139, 34)),        // Dunkelgrün
    HILLS(new Color(205, 133, 63)),        // Braun
    DESERT(new Color(237, 201, 175)),      // Sandfarben
    OCEAN(new Color(0, 119, 190));         // Blau

    private final Color colour;

    LandscapeTypes(Color color) {
        this.colour = color;
    }

}

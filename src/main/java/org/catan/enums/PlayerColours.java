package org.catan.enums;

import lombok.Getter;

import java.awt.*;

@Getter
public enum PlayerColours {
    WHITE(new Color(255,255,255)),
    ORANGE(new Color(255,127,0)),
    BLUE(new Color(0,0,200)),
    RED(new Color(200,0,0));

    private final Color colour;
    PlayerColours(Color colour) {
        this.colour = colour;
    }

}

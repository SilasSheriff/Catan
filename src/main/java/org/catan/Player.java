package org.catan;

import lombok.Data;
import org.catan.enums.Resources;
import org.catan.models.Pieces;

import java.awt.*;
import java.util.HashMap;

@Data
public class Player {
    private String playerName;
    private int points;
    private Color colour;
    private int remainingStreets = 15;
    private int remainingVillages = 5;
    private int remainingTowns = 4;


    private HashMap<Resources,Integer> numberOfResources = new HashMap<>();

    Player(){
        numberOfResources.put(Resources.WHEAT,0);
        numberOfResources.put(Resources.SHEEP,0);
        numberOfResources.put(Resources.CLAY,0);
        numberOfResources.put(Resources.WOOD,0);
        numberOfResources.put(Resources.ORE,0);
    }



}

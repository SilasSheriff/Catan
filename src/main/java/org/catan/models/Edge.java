package org.catan.models;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Edge extends Place {


    public Edge(Point coordinate, ArrayList<ResourceTile> resourceTileArrayList){
        super(coordinate,resourceTileArrayList);
    }
}

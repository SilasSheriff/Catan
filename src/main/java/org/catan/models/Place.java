package org.catan.models;


import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@Getter
@Setter
public class Place {


    final Point coordinate;
    Point2D.Double exactLocation = new Point2D.Double();
    Pieces piece = null;
    final ArrayList<ResourceTile> resourceTileArrayList;

    Place(Point coordinate,ArrayList<ResourceTile> resourceTileArrayList){
        this.coordinate = coordinate;
        this.resourceTileArrayList = resourceTileArrayList;
    }
}

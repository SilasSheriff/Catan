package org.catan.models;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.catan.enums.LandscapeTypes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

@Setter
@Getter
@Data
public class ResourceTile {

    private final HashMap<Integer, Point2D.Double> corners = new HashMap<>();
    private final Point2D.Double center;
    private final double sidelength;
    private int resourceNumber = 0;
    private LandscapeTypes type;
    private final Point coord;



    private final ArrayList<Vertex> vertices = new ArrayList<>();

    public ResourceTile(Point2D.Double center, double sidelength, Point coord) {
        this.center = center;
        this.sidelength = sidelength;
        this.coord = coord;
        defineCorners();
    }

    private void defineCorners(){
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = center.x + sidelength * Math.cos(angle);
            double y = center.y + sidelength * Math.sin(angle);
            corners.put(i+1,new Point2D.Double(x,y));
        }
    }
}

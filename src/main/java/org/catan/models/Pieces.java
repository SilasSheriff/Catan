package org.catan.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.catan.enums.PlayerColours;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@Data
@Getter
@Setter



public class Pieces {
    Point2D.Double center;
    PlayerColours colour;
    double size = 4;

    public Pieces(Point2D.Double center, PlayerColours colour){
        this.center = center;
        this.colour = colour;
    }

    public void drawPiece(Graphics2D graph){

    }


}

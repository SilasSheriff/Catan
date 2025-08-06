package org.catan.models;

import lombok.Getter;
import lombok.Setter;
import org.catan.enums.PlayerColours;
import org.catan.enums.Resources;
import org.catan.utils.BoardUtils;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class Village extends Pieces{
    private final int value = 2;
    private final Point2D.Double center;
    private final ArrayList<Point2D.Double> corners = new ArrayList<>();
    private final HashMap<Resources,Integer> price = new HashMap<>();
    double size = super.size;

    public Village(Point2D.Double center, PlayerColours colour) {
        super(center, colour);
        this.center = center;
        calculateTownCorners();

        price.put(Resources.CLAY,1);
        price.put(Resources.WOOD,1);
        price.put(Resources.SHEEP,1);
        price.put(Resources.WHEAT,1);
    }

    private void calculateTownCorners(){
        corners.add(new Point2D.Double(center.x, center.y - 2.5 * size));
        corners.add(new Point2D.Double(center.x + 1.25 * size, center.y - size ));
        corners.add(new Point2D.Double(center.x + 1.25 * size, center.y + size ));
        corners.add(new Point2D.Double(center.x - 1.25 * size, center.y + size ));
        corners.add(new Point2D.Double(center.x - 1.25 * size, center.y - size ));
    }

    @Override
    public void drawPiece(Graphics2D graph){
        Path2D town = new Path2D.Double();
        town.moveTo(corners.getFirst().x,corners.getFirst().y);
        for (Point2D.Double corner : corners) {

            double x = corner.x;
            double y = corner.y;

            town.lineTo(x,y);
        }
        graph.setColor(colour.getColour());
        town.closePath();
        graph.draw(town);
        BoardUtils.fillForm(town,graph,colour.getColour());
    }
}

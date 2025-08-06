package org.catan.utils;

import org.catan.enums.LandscapeTypes;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class BoardUtils {
    public static Path2D createHexagon(Point2D.Double center,
                                       double sidelength) {
        Path2D hex = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = center.x + sidelength * Math.cos(angle);
            double y = center.y + sidelength * Math.sin(angle);
            if (i == 0) hex.moveTo(x, y);
            else hex.lineTo(x, y);
        }
        hex.closePath();
        return hex;
    }

    public static void fillForm(Path2D hex, Graphics2D hexGraphic, Color colour){
        hexGraphic.setColor(colour);
        hexGraphic.fill(hex);
    }

    public static void drawHexagon(Graphics2D g2,
                                   Point2D.Double center,
                                   double sidelength){

        Path2D hex = new Path2D.Double();
        for (int i = 0; i < 6; i++){

            double angle = Math.toRadians(60 * i);
            double x = center.x + sidelength * Math.cos(angle);
            double y = center.y + sidelength * Math.sin(angle);

            if(i == 0){
                hex.moveTo(x,y);
            }
            else {
                hex.lineTo(x,y);
            }

        }
        g2.setColor(Color.BLACK);
        hex.closePath();
        g2.draw(hex);
    }

    public static ArrayList<LandscapeTypes> landscapeTypesDistribution(){
        ArrayList<LandscapeTypes> landscapeTypes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            landscapeTypes.add(LandscapeTypes.PASTURE);
            landscapeTypes.add(LandscapeTypes.FIELDS);
            landscapeTypes.add(LandscapeTypes.FOREST);
            landscapeTypes.add(LandscapeTypes.HILLS);
            landscapeTypes.add(LandscapeTypes.MOUNTAINS);
        }
        landscapeTypes.add(LandscapeTypes.DESERT);
        landscapeTypes.add(LandscapeTypes.PASTURE);
        landscapeTypes.add(LandscapeTypes.FIELDS);
        landscapeTypes.add(LandscapeTypes.FOREST);

        // Landschaften mischen
        Collections.shuffle(landscapeTypes);
        return landscapeTypes;
    }

    public static ArrayList<Integer> resourceNumberDistribution(){
        ArrayList<Integer> resourceNumbers = new ArrayList<>();
        for (int i = 3; i < 12; i++){
            if (i == 7) continue;
            resourceNumbers.add(i);
            resourceNumbers.add(i);
        }
        resourceNumbers.add(2);
        resourceNumbers.add(12);

        // Landschaften mischen
        Collections.shuffle(resourceNumbers);
        return resourceNumbers;
    }


}

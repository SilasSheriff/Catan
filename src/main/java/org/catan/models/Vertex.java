package org.catan.models;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;


@Getter
@Setter
public class Vertex extends Place {
    private Point neighbouringResourceTile;
    private Point neighbouringResourceTile2;
    private Point neighbouringResourceTile3;



    private Pieces piece = null;


    public Vertex(Point coordinate, ArrayList<ResourceTile> resourceTileArrayList){
        super(coordinate,resourceTileArrayList);

        neighbourTileHandler();
        assignExactPosition();
    }

    private void neighbourTileHandler(){
        neighbouringResourceTile = new Point();
        neighbouringResourceTile2 = new Point();
        neighbouringResourceTile3 = new Point();

        if(coordinate.x % 2== 0){

            neighbouringResourceTile = coordinate;

            neighbouringResourceTile2.x = coordinate.x;
            neighbouringResourceTile2.y = coordinate.y + 2;

            neighbouringResourceTile3.x = coordinate.x - 2;
            neighbouringResourceTile3.y = coordinate.y + 2;
        }
        if(Math.abs(coordinate.x % 2) == 1){
            neighbouringResourceTile.x = coordinate.x - 1;
            neighbouringResourceTile.y = coordinate.y;

            neighbouringResourceTile2.x = coordinate.x + 1;
            neighbouringResourceTile2.y = coordinate.y;

            neighbouringResourceTile3.x = coordinate.x - 1;
            neighbouringResourceTile3.y = coordinate.y + 2;
        }
    }

    private void assignExactPosition(){
        for (ResourceTile r : resourceTileArrayList){
            if (r.getCoord().equals(neighbouringResourceTile)){
                if (coordinate.x % 2 == 0){
                    exactLocation = r.getCorners().get(3);
                }
                else {
                    exactLocation = r.getCorners().get(2);
                }
            }
        }
    }

    public void assignPieceToVertex(Pieces piece){
        piece.setCenter(exactLocation);
        this.piece = piece;
    }

}

package org.catan.models;

import lombok.Getter;
import lombok.Setter;
import org.catan.enums.LandscapeTypes;
import org.catan.enums.PlayerColours;
import org.catan.utils.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class HexagonGrid extends JPanel {
    private final int boardSize = 3;
    private ArrayList<LandscapeTypes> landscapeTypes = BoardUtils.landscapeTypesDistribution();
    private ArrayList<ResourceTile> resourceTiles = new ArrayList<>();
    ArrayList<Integer> resourceNumbers = BoardUtils.resourceNumberDistribution();
    HashMap<Vertex,ResourceTile> vertexResourceTileHashMap = new HashMap<>();
    boolean painted = false;
    private final double sidelength = 50;
    private int numberOfNotOceanTiles = 0;
    private int numberOfUsedNumberTiles = 0;

    public HexagonGrid(){
        resourceTiles = createResourceTiles();
        initializeBoard();
    }

    public void initializeBoard(){
        for (ResourceTile resourceTile : resourceTiles){
            Point coord = resourceTile.getCoord();
            if(Math.abs(coord.x) > 2 * boardSize - 2 ||
                    Math.abs(coord.y) > 2 * boardSize - 2 ||
                    Math.abs( coord.x + coord.y) > 2 * boardSize - 2) {
                resourceTile.setType(LandscapeTypes.OCEAN);
            }
            else{
                // entnimmt die erste Landschaft aus der Landschaftenliste
                resourceTile.setType(landscapeTypes.get(numberOfNotOceanTiles));
                numberOfNotOceanTiles++;
            }
            defineVertices();
            LandscapeTypes type = resourceTile.getType();
            if(!type.equals(LandscapeTypes.DESERT) && ! type.equals(LandscapeTypes.OCEAN)){
                defineResourceTileNumbers(resourceTile);
                numberOfUsedNumberTiles++;
            }

        }
        numberOfUsedNumberTiles = 0;
        numberOfNotOceanTiles = 0;
    }

    public void drawBoard(Graphics2D hexGraphic){
        for(ResourceTile resourceTile : resourceTiles){

            BoardUtils.drawHexagon(hexGraphic,resourceTile.getCenter(),sidelength);
            // Hexagonform für das Feld erzeugen
            Path2D hex = BoardUtils.createHexagon(resourceTile.getCenter(), sidelength);

            // Den Landschaftstyp setzen (inkl. farblicher Füllung)
            fillResourceTiles(resourceTile, hex, hexGraphic);
            // Ressourcenwürfelzahl einzeichnen
            insertResourceRollNumbers(hexGraphic,resourceTile);
        }


        for(Map.Entry<Vertex,ResourceTile> entry : vertexResourceTileHashMap.entrySet()){
            Vertex v = entry.getKey();
            drawVertexCoords(v,hexGraphic);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D hexGraphic = (Graphics2D) g;
        hexGraphic.setColor(Color.WHITE);
        hexGraphic.fillRect(0, 0, getWidth(), getHeight());
        hexGraphic.setColor(Color.BLACK);
        // Ressourcenplättchen erstellen

        drawBoard(hexGraphic);
    }

    /**
     * Erstellt die Ressourcenfelder (Hexagone) für das Spielbrett.
     * Dabei werden Position, Landschaftstyp und Form jedes einzelnen Feldes bestimmt.
     * Ozean-Tiles werden an den Rändern platziert und nicht mit Zahlen versehen.
     *
     * @return Eine Liste aller erstellten ResourceTile-Objekte (Hex-Felder)
     */
    public ArrayList<ResourceTile> createResourceTiles() {
        // Liste zur Speicherung aller generierten Ressourcenfelder
        ArrayList<ResourceTile> resourceTiles = new ArrayList<>();

        // Doppelte Schleife zur Platzierung der Hexagone im Koordinatengitter
        for (int row = -2 * boardSize; row <= 2 * boardSize; row += 2) {
            for (int col = -2 * boardSize; col <= 2 * boardSize; col += 2) {

                // Raster-Koordinaten des aktuellen Hexagons im Spielbrett-Gitter
                Point coordPos = new Point(col, row);

                // Berechnung der tatsächlichen Pixelposition für das Zentrum des Hexagons
                ResourceTile activeResourceTile = determineHexCenter(col, row, coordPos);

                // Ungültige Felder außerhalb des hexagonalen Spielfeldbereichs aussortieren
                if (Math.abs(row + col) > 2 * boardSize) {
                    continue; // überspringen
                }

                // Feld zur Rückgabeliste hinzufügen

                resourceTiles.add(activeResourceTile);
            }
        }

        // Gibt die komplette Liste an ResourceTiles zurück
        return resourceTiles;
    }

    private ResourceTile determineHexCenter(int col, int row, Point coordPos) {
        double x = 0.5 * col * 1.5 * sidelength + 400;
        double y = 0.5 * row * Math.sqrt(3) * sidelength
                + (0.5 * col * Math.sqrt(3) / 2 * sidelength) + 400;


        return new ResourceTile(
                new Point2D.Double(x, y),  // Mittelpunkt in Pixelkoordinaten
                sidelength,                // Seitenlänge des Hexagons
                coordPos                   // Rasterposition (logische Koordinaten)
        );
    }

    /**
     * Zeichnet die Würfelzahl (Zahl zwischen 2 und 12, außer 7) auf das übergebene Ressourcenfeld.
     * Felder vom Typ DESERT oder OCEAN erhalten keine Zahl.
     */
    private void insertResourceRollNumbers(Graphics2D hexGraphic, ResourceTile resourceTile){

        int resourceNumber = resourceTile.getResourceNumber();
        if (resourceNumber == 0) return;
        // Berechnet die Position in Pixeln, an der die Zahl gezeichnet werden soll (Zentrum des Hexagons)
        Point2D.Double center = resourceTile.getCenter();
        // Setzt Schriftart und -größe für die Zahl
        hexGraphic.setFont(new Font("SansSerif", Font.BOLD, 14));
        FontMetrics fm = hexGraphic.getFontMetrics();
        // Berechnet Breite und Höhe der Zeichenkette (zur Zentrierung)
        int textWidth = fm.stringWidth("" + resourceNumber);
        int textHeight = fm.getAscent();
        // Setzt die Textfarbe auf Schwarz
        hexGraphic.setColor(Color.BLACK);
        // Zeichnet die Zahl zentriert im Hexagon
        hexGraphic.drawString("" + resourceNumber, (float) (center.x - textWidth / 2.0), (float) (center.y + textHeight / 2.5));
        // Erhöht den Zähler für verwendete Würfelzahlen
    }

    private void defineResourceTileNumbers(ResourceTile resourceTile){
        // Holt die nächste verfügbare Würfelzahl aus der Liste, basierend auf dem aktuellen Zähler
        int resourceNumber = resourceNumbers.get(numberOfUsedNumberTiles);
        // Setzt die Würfelzahl im ResourceTile-Objekt (für spätere Verwendung)
        resourceTile.setResourceNumber(resourceNumber);
    }

    private void fillResourceTiles(ResourceTile actualResourceTile,
                                   Path2D hex,
                                   Graphics2D hexGraphic){


        // Rand blau färben und Mitte anders färben

        BoardUtils.fillForm(hex,hexGraphic,actualResourceTile.getType().getColour());
        hexGraphic.fill(hex);
    }

    private void defineVertices(){
        for(ResourceTile r : resourceTiles){
            int x = r.getCoord().x;
            int y = r.getCoord().y;

            if(y < - 6 || y > 4 || x + y > 4) continue;
            Vertex vertexLeft = new Vertex(new Point(x,y),resourceTiles);
            Vertex vertexRight = new Vertex(new Point(x + 1,y),resourceTiles);

            if (vertexRight.getCoordinate().x < 6){
                vertexResourceTileHashMap.put(vertexRight,r);
            }

            if (vertexLeft.getCoordinate().x > -5){
                vertexResourceTileHashMap.put(vertexLeft,r);
            }
        }
    }

    public void drawVertexCoords(Vertex v, Graphics2D hexGraphic){
        Point coordinate = v.getCoordinate();
        if (coordinate.equals(new Point(0,0))){
            Town t = new Town(v.getExactLocation(), PlayerColours.RED);
            v.assignPieceToVertex(t);
        }
        if (coordinate.equals(new Point(1,2))){
            Village vil = new Village(v.getExactLocation(), PlayerColours.WHITE);
            v.assignPieceToVertex(vil);
        }

        Pieces piece = v.getPiece();
        if (piece != null) {
                piece.drawPiece(hexGraphic);
                return;
        }
        Point2D.Double exactLoc = v.getExactLocation();
        FontMetrics fm = hexGraphic.getFontMetrics();
        int textWidth = fm.stringWidth(coordinate.x + "," + coordinate.y);
        int textHeight = fm.getAscent();
        hexGraphic.setColor(Color.BLACK);
        hexGraphic.drawString( coordinate.x + "," + coordinate.y, (float) (exactLoc.x - textWidth / 2.0), (float) (exactLoc.y + textHeight / 2.5));
    }
}

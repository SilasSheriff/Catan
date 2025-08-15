package org.catan;

import org.catan.enums.PlayerColours;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) {

        Player asmaa = new Player("Asmaa", PlayerColours.BLUE);
        Player silas = new Player("Silas", PlayerColours.RED);
        Player mai = new Player("Mai",PlayerColours.WHITE);
        Player thomas = new Player("Thomas",PlayerColours.ORANGE);

        ArrayList<Player> players = new ArrayList<>();

        players.add(asmaa);
        players.add(silas);
        players.add(mai);
        players.add(thomas);

        Game game = new Game(players);
        game.playGame();

    }

}




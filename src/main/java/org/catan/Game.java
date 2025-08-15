package org.catan;

import lombok.Getter;
import lombok.Setter;
import org.catan.models.HexagonGrid;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Setter
@Getter
public class Game {
    HexagonGrid board = new HexagonGrid();
    ArrayList<Player> players;

    Game(ArrayList<Player> players){
        this.players = players;
    }



    public void playGame(){
        Scanner scanner = new Scanner(System.in);
        JFrame frame = new JFrame("Catan");
        boolean end = false;
        while(!end){



            frame.setSize(800, 800);
            frame.add(new HexagonGrid());
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String endGame = scanner.nextLine().toLowerCase();

            for (Player p: players){
                int diceRoll = rollDice();
            }

            if (endGame.equals("j")){
                end = true;
            }

        }

    }

    private int rollDice(){
        Random random = new Random();
        return random.nextInt(1,7) + random.nextInt(1,7);
    }
}

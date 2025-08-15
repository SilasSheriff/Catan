package org.catan;

import lombok.Getter;
import lombok.Setter;
import org.catan.models.HexagonGrid;
import javax.swing.*;
import java.awt.*;
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



    public void playGame() {
        JFrame frame = new JFrame("Catan");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(board, BorderLayout.CENTER);
        JButton nextTurnButton = new JButton("Nächster Zug");
        for (Player p : players) {
        nextTurnButton.addActionListener(e -> {
                int diceRoll = rollDice();
                System.out.println(p.getPlayerName() + " rolled: " + diceRoll);

        });
        }
        JButton endGameButton = new JButton("Spiel beenden");
        endGameButton.addActionListener(e -> frame.dispose());

        JPanel controlPanel = new JPanel();
        controlPanel.add(nextTurnButton);
        controlPanel.add(endGameButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    private int rollDice(){
        Random random = new Random();
        return random.nextInt(1,7) + random.nextInt(1,7);
    }
}

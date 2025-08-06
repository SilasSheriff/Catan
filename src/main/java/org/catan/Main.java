package org.catan;

import org.catan.models.HexagonGrid;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Catan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new HexagonGrid());
        frame.setVisible(true);
    }

}




package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class Obstacle {

    private ArrayList<Rectangle> cells;
    private int cellHeigth;
    private int cellWidth;
    private static final int SIZE = 10;
    private int middleGap;


    public Obstacle(int fieldSize) {
        this.cellWidth = 100;
        this.cellHeigth = fieldSize / SIZE;


        for (int i = 0; i < SIZE; i++) {
            cells.add()
        }

    }


    /**
     * moves all object right
     */
    public void move() {

        for (Rectangle cell : cells) {
            cell.translate(-1, 0);
        }

    }


    public int getMiddleGap() {

        return middleGap;
    }


}



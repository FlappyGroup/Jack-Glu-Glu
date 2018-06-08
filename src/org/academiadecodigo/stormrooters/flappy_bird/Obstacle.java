package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class Obstacle {

    private ArrayList<Rectangle> cells;
    private int cellHeigth;
    private int cellWidth;
    private static final int SIZE = 10;
    private int middleGap;


    public Obstacle(int gap) {

        this.middleGap = gap;
        cells = new ArrayList<>();
        this.cellWidth = 100;
        this.cellHeigth = Game.FIELD_HEIGHT / SIZE;


        cells.add(new Rectangle(Game.FIELD_WIGHT - cellWidth, Game.PADDING, cellWidth, cellHeigth));
        cells.get(0).fill();

        for (int i = 1; i < SIZE; i++) {
            cells.add(new Rectangle(Game.FIELD_WIGHT - cellWidth, Game.PADDING +
                    (cellHeigth * i), cellWidth, cellHeigth));
            cells.get(i).fill();
        }

        for (int i = gap - 1; i <= gap + 1; i++) {

            cells.get(i).delete();
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



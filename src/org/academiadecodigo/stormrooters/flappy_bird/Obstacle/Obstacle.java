package org.academiadecodigo.stormrooters.flappy_bird.Obstacle;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.stormrooters.flappy_bird.Game;

import java.util.ArrayList;

public class Obstacle {

    private ArrayList<Cell> cells;
    private int cellHeight;
    private int cellWidth;
    private static final int SIZE = 11;
    private int middleGap;


    public Obstacle(int gap) {

        this.middleGap = gap;
        cells = new ArrayList<>();
        this.cellWidth = 100;
        this.cellHeight = Game.FIELD_HEIGHT / SIZE;


    }

    public void objectInit() {

        cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING, cellWidth, cellHeight));
        cells.get(0).fill();

        for (int i = 1; i < SIZE; i++) {
            cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING +
                    (cellHeight * i), cellWidth, cellHeight));
            cells.get(i).fill();
        }

        for (int i = this.middleGap - 1; i <= this.middleGap + 1; i++) {

            cells.get(i).delete();
            cells.get(i).setOff();
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

    public Cell getTopObstacle() {

        for (int i = 0; i < cells.size(); i++) {

            if (!cells.get(i).isStatus()) {
                return cells.get(i - 1);
            }
        }
        return null;
    }

    public Cell getBottomObstacle() {

        for (int i = cells.size() - 1; i >= 0; i--) {

            if (!cells.get(i).isStatus()) {
                return cells.get(i + 1);
            }
        }
        return null;
    }


    public int getMiddleGap() {

        return middleGap;
    }


}


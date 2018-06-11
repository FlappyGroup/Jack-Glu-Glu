package org.academiadecodigo.stormrooters.flappy_bird.Obstacle;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.stormrooters.flappy_bird.game.Game;

import java.util.ArrayList;

public class Obstacle {

    private static final int SIZE = 11;

    private ArrayList<Cell> cells;
    private int cellHeight;
    private int cellWidth;
    private int middleGap;
    private boolean used;


    public Obstacle() {


        cells = new ArrayList<>();
        this.cellWidth = 100;
        this.cellHeight = 45;
        used = true;
    }


    /**
     * create all the cells of obstacle
     */
    public void objectInit() {

        cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING, cellWidth, cellHeight));
        cells.get(0).fill();

        for (int i = 1; i < SIZE; i++) {
            cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING +
                    (cellHeight * i), cellWidth, cellHeight));
            cells.get(i).fill();
        }


    }

    /**
     * delete all cells from canvas and set all cells status to false
     */
    public void deleteCell() {

        for (int i = 0; i < cells.size(); i++) {

            cells.get(i).delete();
            cells.get(i).setOff();
        }


    }

    /**
     * Delete cells 3 cells withdraw  numberGap as middle
     *
     * @param numberGap middle of the gap received from generateGap
     */

    public void deleteGap(int numberGap) {

        middleGap = numberGap;

        for (int i = this.middleGap - 1; i <= this.middleGap + 1; i++) {

            cells.get(i).delete();
            cells.get(i).setOff();
        }
    }

    /**
     * moves all used object right
     */
    public void move() {

        if (!used) {
            return;
        }

        for (Rectangle cell : cells) {

            cell.translate(-1, 0);
        }


    }

    /**
     * moves obstacle to the begin (all cells) and set as used
     */
    public void translateCells() {

        for (int i = 0; i < cells.size(); i++) {

            cells.get(i).translate(Game.FIELD_WIGHT - cellWidth, 0);
            cells.get(i).fill();
            cells.get(i).setOn();
        }
    }

    /**
     * get the last filled cell before the gap
     *
     * @return Cell
     */
    public Cell getTopObstacle() {

        for (int i = 0; i < cells.size(); i++) {

            if (!cells.get(i).isStatus()) {
                return cells.get(i - 1);
            }
        }
        return null;
    }

    /**
     * get the first cell after the gap
     *
     * @return Cell
     */
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

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean getUsed() {
        return used;
    }
}



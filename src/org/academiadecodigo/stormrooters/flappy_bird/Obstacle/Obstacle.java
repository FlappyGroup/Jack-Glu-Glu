package org.academiadecodigo.stormrooters.flappy_bird.Obstacle;


import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.game.Game;

import java.util.ArrayList;

public class Obstacle {

    private static final int SIZE = 11;

    private ArrayList<Cell> cells;
    private int cellHeight;
    private int cellWidth;
    private int middleGap;
    private boolean used;

    private final String pathNormalSprite = "obstacle/normal.png";
    private final String pathTopSprite = "obstacle/top.png";
    private final String pathBottomSpite = "obstacle/bottom.png";

    public Obstacle() {


        cells = new ArrayList<>();
        this.cellWidth = 100;
        this.cellHeight = 45;
        used = false;
    }


    /**
     * create all the cells of obstacle
     */
    public void init() {

        cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING, pathBottomSpite));

        for (int i = 1; i < SIZE; i++) {
            cells.add(new Cell(Game.FIELD_WIGHT - cellWidth, Game.PADDING +
                    (cellHeight * i), pathNormalSprite));
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

    public void configObstacle(int numberGap) {

        middleGap = numberGap;

        for (int i = this.middleGap - 1; i <= this.middleGap + 1; i++) {

            cells.get(i).delete();
            cells.get(i).setOff();
        }
        getBottomCell().load(pathBottomSpite);
        getTopCell().load(pathTopSprite);
    }

    public void drawCells() {
        for (Cell cell : cells) {
            if (cell.isStatus()) {
                cell.draw();
            }
        }
    }

    /**
     * moves all used object right
     */
    public void move() {

        if (!used) {
            return;
        }

        for (Picture cell : cells) {

            cell.translate(-3, 0);
        }


    }

    /**
     * moves obstacle to the begin (all cells) and set as used
     */
    public void reUseObstacle() {

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            cell.translate(Game.FIELD_WIGHT - cellWidth - cell.getX(), 0);
            cell.setOn();
            cell.load(pathNormalSprite);
        }
    }

    /**
     * get the last filled cell before the gap
     *
     * @return Cell
     */
    public Cell getTopCell() {

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
    public Cell getBottomCell() {

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



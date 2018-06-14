package org.academiadecodigo.stormrooters.jackgluglu.Obstacle;


import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class Obstacle {

    private static final int SIZE = 11;

    private ArrayList<Cell> cells;
    private final int cellHeight = 45;
    private final int cellWidth = 100;
    private int middleGap;
    private boolean used;
    private int cellsDrawn;

    private final String pathNormalSprite = "obstacle/normal.png";
    private final String pathTopSprite = "obstacle/top.png";
    private final String pathBottomSpite = "obstacle/bottom.png";

    public Obstacle() {


        cells = new ArrayList<>();
        used = false;
    }


    /**
     * create all the cells of obstacle
     */
    public void init() {

        cells.add(new Cell(org.academiadecodigo.stormrooters.jackgluglu.game.Game.FIELD_WIGHT - cellWidth, org.academiadecodigo.stormrooters.jackgluglu.game.Game.PADDING, pathBottomSpite));

        for (int i = 1; i < SIZE; i++) {
            cells.add(new Cell(org.academiadecodigo.stormrooters.jackgluglu.game.Game.FIELD_WIGHT - cellWidth, org.academiadecodigo.stormrooters.jackgluglu.game.Game.PADDING +
                    (cellHeight * i), pathNormalSprite));
        }
        cellsDrawn = SIZE;

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
        cellsDrawn = 0;
        for (int i = this.middleGap - 1; i <= this.middleGap + 1; i++) {

            cells.get(i).delete();
            cells.get(i).setOff();
        }
        getBottomCell().load(pathBottomSpite);
        getTopCell().load(pathTopSprite);
    }

    public void drawCells() {

        if (cellsDrawn == SIZE) {
            return;
        }

        int x = SIZE / 2;
        if (cellsDrawn == x) {

            for (int i = SIZE / 2; i < SIZE; i++) {
                if (cells.get(i).isStatus()) {
                    cells.get(i).draw();
                    cellsDrawn = SIZE;
                }
            }
            return;
        }

        for (int i = 0; i < SIZE / 2; i++) {
            if (cells.get(i).isStatus()) {
                cells.get(i).draw();
                cellsDrawn = SIZE / 2;
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
            cell.translate(org.academiadecodigo.stormrooters.jackgluglu.game.Game.FIELD_WIGHT - cellWidth - cell.getX(), 0);
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



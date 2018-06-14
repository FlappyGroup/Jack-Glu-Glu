package org.academiadecodigo.stormrooters.flappy_bird.Obstacle;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Cell extends Picture {
    private boolean status;


    public Cell(double v, double v1, String s) {
        super(v, v1, s);
        this.status = true;
    }

    public void setOn() {
        status = true;
    }

    public void setOff() {
        status = false;
    }

    public boolean isStatus() {
        return status;
    }
}

package org.academiadecodigo.stormrooters.flappy_bird.Obstacle;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell extends Rectangle {
    private boolean status;

    public Cell(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
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

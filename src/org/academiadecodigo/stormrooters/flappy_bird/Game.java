package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    public final int PADDING = 10;
    private Bird bird;
    private Rectangle field;
    // need a container like a queue for the obstacles
    //

    /**
     * prepare the game to start: - Draw field
     * fill the container with the first obstacles(or create a class field to do it)
     */
    public void init() {
        field = new Rectangle(PADDING, PADDING, 1500, 500);
        this.field.draw();
    }

    /**
     * check of collisions and
     * move all Movables
     */
    public void runGame() {

    }

    /**
     * Check collision of bird with top, ground and obstacles
     * and obstacles with the right wall
     */
    public void collisionDetecter() {

    }

    /**
     * delete first obstacle on the queue
     */
    public void deleteObstacles() {

    }

    /**
     * create a obstacle on the last position of the queue
     */
    private void createObstacles() {

    }
}

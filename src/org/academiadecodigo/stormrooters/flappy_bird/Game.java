package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private final int DELAY = 5;
    private final int PADDING = 10;
    private final int numberOfObstacles = 3;

    private Bird bird;
    private Rectangle field;
    private Queue<Obstacle> obstacles;


    /**
     * prepare the game to start: - Draw field
     * create and add the container with the first obstacles
     */
    public void init() {

        //creating the field                     W      H
        field = new Rectangle(PADDING, PADDING, 1500, 500);
        this.field.draw();

        // creating first obstacle
        this.obstacles = new LinkedList<>();
        this.obstacles.add(createObstacles());

        // creating bird
        this.bird = new Bird();
    }

    /**
     * check of collisions and
     * move all Movables
     */
    public void runGame() throws InterruptedException {

        while (true) {

            //delay between cycles
            Thread.sleep(DELAY);

            //moving all obstacles
            for (Obstacle obstacle : obstacles) {

                obstacle.move();
            }

            bird.move();
        }
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
    private Obstacle createObstacles() {
        return null;
    }
}

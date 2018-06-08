package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private final int DELAY = 5;
    private final int PADDING = 10;

    private Bird bird;
    private Rectangle field;
    private LinkedList<Obstacle> obstacles;


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
        this.bird = new Bird(PADDING + 100, PADDING + 200, 50, 50);
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
           // for (Obstacle obstacle : obstacles) {

             //   obstacle.move();
            //}

            //bird.move();
            collisionChecker();
        }
    }

    /**
     * Check collision of bird with top, ground and obstacles
     * and obstacles with the right wall
     */
    public void collisionChecker() {

        Obstacle top = obstacles.peek();

        int topObstacleY = top.getY();
        int topObstacleX = top.getX();
        int topObstacleWidth = top.getWidth();
        int topObstacleHeight = top.getHeight();

        int birdY = bird.getY();
        int birdX = bird.getX();
        int birdWidth = bird.getWidth();
        int birdHeight = bird.getHeight();

        int bottomObstacleY = obstacles.get(1).getY();

        // checking if obstacles it the edge of the field and delete NOT TESTED
        if (topObstacleX + topObstacleWidth <= field.getX()) {
            deleteObstacles();
            createObstacles();
        }

        //checking collision with ground/roof

        if (birdY <= field.getY() ||
                (birdY + birdHeight) >= (field.getY() + field.getHeight())) {

            bird.die();
            return;
        }

        // checking collision with obstacles NOT TESTED
        if (birdX + birdWidth < topObstacleX || birdX > topObstacleX + topObstacleWidth) {
            return;
        }


        // checking collision with top obstacles
        if (birdY <= topObstacleY + topObstacleHeight) {
            bird.die();
            return;
        }


        if (birdY + birdHeight >= bottomObstacleY) {
            bird.die();
            return;
        }
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

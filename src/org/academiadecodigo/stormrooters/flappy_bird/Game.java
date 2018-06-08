package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Game {

    private final int TRIGGER = 1300; // X pixel who triggers a new obstacle;
    private final int DELAY = 5;
    private final int PADDING = 10;

    private Bird bird;
    private Rectangle field;
    private LinkedList<Obstacle> topObstacles;
    private LinkedList<Obstacle> bottomObstacles;


    /**
     * prepare the game to start: - Draw field
     * create and add the container with the first topObstacles
     */
    public void init() {

        //creating the field                     W      H
        field = new Rectangle(PADDING, PADDING, 1500, 500);
        this.field.draw();

        // creating obstacle lists
        this.topObstacles = new LinkedList<>();
        this.bottomObstacles = new LinkedList<>();

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

            //moving all topObstacles
            // for (Obstacle obstacle : topObstacles) {

            //   obstacle.move();
            //}

            bird.move();

            collisionChecker();

        }
    }

    /**
     * Check collision of bird with top, ground and topObstacles
     * and topObstacles with the right wall
     */
    public void collisionChecker() {

        /*Obstacle top = topObstacles.peek();
        Obstacle bottom = bottomObstacles.peek();

        int topObstacleY = top.getY();
        int topObstacleX = top.getX();
        int topObstacleWidth = top.getWidth();
        int topObstacleHeight = top.getHeight();

        int bottomObstacleY = bottom.getY();

        int birdY = bird.getY();
        int birdX = bird.getX();
        int birdWidth = bird.getWidth();
        int birdHeight = bird.getHeight();


        // checking if topObstacles it the edge of the field and delete NOT TESTED
        if (topObstacleX + topObstacleWidth <= field.getX()) {
            deleteObstacles();
            createObstacle();
        }

        //checking collision with ground/roof

        if (birdY <= field.getY() ||
                (birdY + birdHeight) >= (field.getY() + field.getHeight())) {

            bird.die();
            return;
        }

        // checking collision with topObstacles NOT TESTED
        if (birdX + birdWidth < topObstacleX || birdX > topObstacleX + topObstacleWidth) {
            return;
        }


        // checking collision with top topObstacles
        if (birdY <= topObstacleY + topObstacleHeight) {
            bird.die();
            return;
        }


        if (birdY + birdHeight >= bottomObstacleY) {
            bird.die();
            return;
        }*/
    }

    /**
     * delete first obstacle on the queue
     */
    public void deleteObstacles() {


    }

    /**
     * create a obstacle on the last position of the queue
     */
    private void createObstacle() {
        return;
    }

}

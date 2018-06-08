package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Game {

    private final int TRIGGER = 1300; // X pixel who triggers a new obstacle;
    private final int DELAY = 10;
    public static final int PADDING = 10;

    public static final int FIELD_HEIGHT = 500;
    public static final int FIELD_WIGHT = 1500;

    private Bird bird;
    private Rectangle field;
    private LinkedList<Obstacle> obstacles;


    /**
     * prepare the game to start: - Draw field
     * create and add the container with the first obstacles
     */
    public void init() {

        //creating the field                     W                 H
        field = new Rectangle(PADDING, PADDING, FIELD_WIGHT, FIELD_HEIGHT);
        this.field.draw();

        // creating obstacle lists
        this.obstacles = new LinkedList<>();

        // creating bird
        this.bird = new Bird(PADDING + 100, PADDING + 200, 50, 50);
    }

    /**
     * check of collisions and
     * move all Movables
     */
    public void runGame() throws InterruptedException {

        createObstacle();

        while (true) {

            //delay between cycles
            Thread.sleep(DELAY);

            //moving all obstacles
            for (Obstacle obstacle : obstacles) {

                obstacle.move();
            }

            bird.move();

            collisionChecker();

        }
    }

    /**
     * Check collision of bird with top, ground and obstacles
     * and obstacles with the right wall
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


        // checking if obstacles it the edge of the field and delete NOT TESTED
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
        */
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
        obstacles.add(new Obstacle());
    }

}

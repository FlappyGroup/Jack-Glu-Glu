package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Cell;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Obstacle;

import java.util.LinkedList;

public class Game {

    private final int DELAY = 7;
    public static final int PADDING = 10;

    public static final int FIELD_HEIGHT = 500;
    public static final int FIELD_WIGHT = 1500;

    private Bird bird;
    private Rectangle field;
    private LinkedList<Obstacle> obstacles;

    private int spacer;


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
        spacer = 0;
    }

    /**
     * check of collisions and
     * move all Movables
     */
    public void runGame() throws InterruptedException {


        while (!bird.isDead()) {

            createObstacle();
            //delay between cycles
            Thread.sleep(DELAY);

            //moving all obstacles
            for (Obstacle obstacle : obstacles) {

                obstacle.move();
            }

            bird.move();

            collisionChecker();

        }

        System.out.println("gameover");
    }

    /**
     * Check collision of bird with top, ground and obstacles
     * and obstacles with the right wall
     */
    public void collisionChecker() {

        Obstacle obstacle = obstacles.peek();
        Cell topCell = obstacle.getTopObstacle();

        int topCellY = topCell.getY();
        int topCellX = topCell.getX();
        int topCellWidth = topCell.getWidth();
        int topCellHeight = topCell.getHeight();

        int bottomCellY = obstacle.getBottomObstacle().getY();

        int birdY = bird.getY();
        int birdX = bird.getX();
        int birdWidth = bird.getWidth();
        int birdHeight = bird.getHeight();


        // checking if obstacles it the edge of the field and delete
        if (topCellX + topCellWidth <= field.getX()) {
            //deleteObstacles();
            //createObstacle();
        }

        //checking collision with ground/roof

        if (birdY <= field.getY() ||
                (birdY + birdHeight) >= (field.getY() + field.getHeight())) {

            bird.die();
            return;
        }

        // checking collision with obstacles
        if (birdX + birdWidth < topCellX || birdX > topCellX + topCellWidth) {
            return;
        }


        // checking collision with top obstacles
        if (birdY <= topCellY + topCellHeight) {
            bird.die();
            return;
        }


        if (birdY + birdHeight >= bottomCellY) {
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
    private void createObstacle() {


        if (obstacles.size() <= 4) {

            spacer--;

            if (spacer <= 0) {

                Obstacle obst = new Obstacle(numberGap());
                spacer = 350;
                obst.objectInit();
                obstacles.add(obst);
            }

        }
    }

    private int numberGap() {

        if (obstacles.isEmpty()) {

            return 4;
        }
        int number = obstacles.getLast().getMiddleGap();

        if (number == 2) {

            return number + 2;
        }

        if (number == 8) {

            return number - 2;
        }

        double random = Math.random();

        if (random > 0.5) {

            return number + 2;
        }
        return number - 2;


    }

}

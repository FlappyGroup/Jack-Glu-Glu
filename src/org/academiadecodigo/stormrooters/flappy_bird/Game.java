package org.academiadecodigo.stormrooters.flappy_bird;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Cell;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Obstacle;
import org.academiadecodigo.stormrooters.flappy_bird.swimmer.Swimmer;

import java.util.LinkedList;

public class Game {

    private final int DELAY = 7;
    public static final int PADDING = 10;

    public static final int FIELD_HEIGHT = 495;
    public static final int FIELD_WIGHT = 1500;

    private Swimmer swimmer;
    private Rectangle field;
    private LinkedList<Obstacle> obstacles;

    private int spacer;


    /**
     * prepare the game to start: - Draw field
     * create the container for the obstacles
     * create the swimmer
     */
    public void init() {


        //creating the field                     W                 H
        field = new Rectangle(PADDING, PADDING, FIELD_WIGHT, FIELD_HEIGHT);
        this.field.draw();
        Picture background = new Picture(PADDING, PADDING, "resources/bg.jpg");
        background.draw();


        // creating obstacle lists
        this.obstacles = new LinkedList<>();

        // creating swimmer
        this.swimmer = new Swimmer(PADDING + 100, PADDING + 200);
        spacer = 0;
    }

    /**
     * check of collisions and
     * move all Movables
     */
    public void runGame() throws InterruptedException {

        int delayAnimation = 30;

        while (!swimmer.isDead()) {
            delayAnimation--;
            createObstacle();

            //delay between cycles
            Thread.sleep(DELAY);

            //moving all obstacles
            for (Obstacle obstacle : obstacles) {

                obstacle.move();
            }

            swimmer.move();

            collisionChecker();

            if (delayAnimation <= 0) {
                swimmer.nextSprit();
                delayAnimation = 30;
            }

        }

        System.out.println("gameover");
    }

    /**
     * Check collision of swimmer with top, ground and obstacles
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

        int birdY = swimmer.getY();
        int birdX = swimmer.getX();
        int birdWidth = swimmer.getWidth();
        int birdHeight = swimmer.getHeight();


        // checking if obstacles hit the edge of the field and delete
        if (topCellX <= field.getX()) {
            recycleObstacle();
        }

        //checking collision with ground/roof

        if (birdY <= field.getY() ||
                (birdY + birdHeight) >= field.getHeight()) {

            swimmer.die();
            return;
        }

        // checking if swimmer is between obstacles
        if (birdX + birdWidth < topCellX || birdX > topCellX + topCellWidth) {
            return;
        }


        // checking collision with top obstacles
        if (birdY <= topCellY + topCellHeight) {
            swimmer.die();
            return;
        }


        if (birdY + birdHeight >= bottomCellY) {
            swimmer.die();
            return;
        }

    }

    /**
     * delete first obstacle on the queue
     */
    public void recycleObstacle() {

        Obstacle obstacle = obstacles.poll();
        obstacle.deleteCell();
        obstacle.setUsed(false);
        obstacles.addLast(obstacle);
    }


    /**
     * create a obstacle on the last position of the queue
     */
    private void createObstacle() {

        spacer--;


        if (spacer <= 0) {

            if (obstacles.size() < 5) {

                Obstacle obst = new Obstacle();
                spacer = 300;
                obst.objectInit();
                obst.deleteGap(generateGap());
                obstacles.add(obst);
                return;

            }

            for (int i = 0; i < obstacles.size(); i++) {

                if (!obstacles.get(i).getUsed()) {
                    spacer = 300;
                    obstacles.get(i).translateCells();
                    obstacles.get(i).deleteGap(generateGap());
                    obstacles.get(i).setUsed(true);
                    break;

                }
            }
        }
    }


    /**
     * generate the number of next gap
     *
     * @return int where the center of the next gap will be
     */
    private int generateGap() {

        if (obstacles.isEmpty()) {

            return 4;
        }
        int number = getLastUsedObstacle().getMiddleGap();

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

    /**
     * Gets last obstacle with property used=true
     *
     * @return a Obstacles
     */

    public Obstacle getLastUsedObstacle() {

        for (int i = obstacles.size() - 1; i >= 0; i--) {

            if (obstacles.get(i).getUsed()) {
                return obstacles.get(i);
            }
        }

        return null;
    }
}


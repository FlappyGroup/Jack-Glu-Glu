package org.academiadecodigo.stormrooters.flappy_bird.game;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Cell;
import org.academiadecodigo.stormrooters.flappy_bird.Obstacle.Obstacle;
import org.academiadecodigo.stormrooters.flappy_bird.swimmer.Swimmer;

import java.util.LinkedList;

public class CollisionDetector {

    private Rectangle field;
    private Swimmer swimmer;
    private LinkedList<Obstacle> obstacles;

    public CollisionDetector(Rectangle field, Swimmer swimmer, LinkedList<Obstacle> obstacles) {
        this.swimmer = swimmer;
        this.obstacles = obstacles;
        this.field = field;
    }

    /**
     * Check collision of swimmer with top, ground and obstacles
     * and obstacles with the right wall
     */
    public void check() {

        Obstacle obstacle = obstacles.peek();
        Cell topCell = obstacle.getTopCell();

        int topCellY = topCell.getY();
        int topCellX = topCell.getX();
        int topCellWidth = topCell.getWidth();
        int topCellHeight = topCell.getHeight();

        int bottomCellY = obstacle.getBottomCell().getY();


        // checking if obstacles hit the edge of the field and delete
        if (topCellX <= field.getX()) {
            recycleObstacle();
        }
        Rectangle[] hitBoxArray = swimmer.getHitBoxes().get(swimmer.getAtSprite());
        for (Rectangle hitBox : hitBoxArray) {

            int hitBoxY = hitBox.getY();
            int hitBoxX = hitBox.getX();
            int hitBoxWidth = hitBox.getWidth();
            int hitBoxHeight = hitBox.getHeight();

            //checking collision with ground/roof
            if (hitBoxY <= field.getY() ||
                    (hitBoxY + hitBoxHeight) >= field.getHeight()) {

                swimmer.die();
                continue;
            }

            // checking if hitbox is between obstacles
            if (hitBoxX + hitBoxWidth < topCellX || hitBoxX > topCellX + topCellWidth) {
                continue;
            }


            // checking collision with top obstacles
            if (hitBoxY <= topCellY + topCellHeight - 3) {
                swimmer.die();
                continue;
            }

            // checking collision with bottom obstacles
            if (hitBoxY + hitBoxHeight >= bottomCellY + 3) {
                swimmer.die();
                continue;
            }
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


}

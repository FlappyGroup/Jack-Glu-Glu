package org.academiadecodigo.stormrooters.flappy_bird.swimmer;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class Swimmer implements KeyboardHandler {

    private boolean isDead;
    private ArrayList<String> spritesPath;
    private Picture picture;
    private HashMap<Integer, Rectangle[]> hitBoxes;
    private int atSprite;
    private int cyclesRising;

    public Swimmer(int x, int y) {

        this.atSprite = 1;
        this.spritesPath = new ArrayList<>();
        this.hitBoxes = new HashMap<>();

        for (int i = 1; i <= 7; i++) {
            spritesPath.add("resources/swimmer/swimmer" + i + ".png");
        }

        picture = new Picture(x, y, spritesPath.get(atSprite - 1));
    }

    public void init() {

        Picture picture2 = new Picture(getX(),getY(),"resources/swimmer/swimmer-death0.png" );
        picture2.draw();
        this.picture.draw();
        addEventsToKeyboard();
        for (int i = 1; i <= 7; i++) {
            hitBoxes.put(i, generateHitBox(i));
        }
    }

    public void drawHitBoxes() {

        Rectangle[] hitBoxes = this.hitBoxes.get(atSprite);
        for (Rectangle rectangle : hitBoxes) {
            rectangle.draw();
        }
    }

    private Rectangle[] generateHitBox(int number) {
        switch (number) {
            case 1:
                return new Rectangle[]{
                        new Rectangle(getX(), getY(), 15, 42),
                        new Rectangle(getX() + 15, getY() + 10, 56, 20),
                        new Rectangle(getX() + 73, getY(), 15, 17)

                };

            case 2:
                return new Rectangle[]{
                        new Rectangle(getX() + 3, getY() + 13, 61, 21),
                        new Rectangle(getX() + 71, getY(), 19, 20)
                };
            case 3:
                return new Rectangle[]{
                        new Rectangle(getX() + 2, getY() + 16, 36, 15),
                        new Rectangle(getX() + 39, getY() + 11, 30, 17),
                        new Rectangle(getX() + 72, getY() + 2, 18, 17)
                };

            case 4:
                return new Rectangle[]{
                        new Rectangle(getX() + 3, getY() + 11, 61, 21),
                        new Rectangle(getX() + 71, getY(), 19, 20)
                };

            case 5:
                return new Rectangle[]{
                        new Rectangle(getX(), getY(), 15, 42),
                        new Rectangle(getX() + 15, getY() + 10, 56, 22),
                        new Rectangle(getX() + 73, getY(), 15, 17)

                };
            case 6:
                return new Rectangle[]{
                        new Rectangle(getX() + 3, getY() + 11, 61, 17),
                        new Rectangle(getX() + 71, getY(), 19, 20)
                };

            case 7:
                return new Rectangle[]{
                        new Rectangle(getX() + 2, getY() + 18, 36, 13),
                        new Rectangle(getX() + 39, getY() + 11, 30, 15),
                        new Rectangle(getX() + 72, getY() + 2, 18, 17)
                };

            default:
                System.out.println(" boken @ Swimer generateHitBoxes");
                return null;
        }
    }

    /**
     * move the bird up and down (if cycles rising > 0 move up)
     */

    public void move(Direction direction) {

        int newYIncrement = 0;

        switch (direction) {

            case UP:
                newYIncrement = -2;
                break;

            case DOWN:
                newYIncrement = 2;
                break;

        }

        this.picture.translate(0, newYIncrement);
        for (Rectangle[] hitBoxes : hitBoxes.values()) {

            for (Rectangle hitBox : hitBoxes) {

                hitBox.translate(0, newYIncrement);
            }
        }

    }

    public void nextSprite() {

        atSprite++;
        if (atSprite >= spritesPath.size()) {

            atSprite = 1;
        }
        picture.load(spritesPath.get(atSprite - 1));
        picture.draw();
    }

    /**
     * Move swimmer up if cyclesRising > 0
     */
    public void move() {

        int movement = 1;

        if (cyclesRising > 0) {

            movement = -1;
            cyclesRising--;
        }

        this.picture.translate(0, movement);

        for (Rectangle[] hitBoxes : hitBoxes.values()) {

            for (Rectangle hitBox : hitBoxes) {

                hitBox.translate(0, movement);
            }
        }

    }

    /**
     * set property isDead to true
     */
    public void die() {

        System.out.println(atSprite + " " + spritesPath.get(atSprite - 1));
        isDead = true;
        drawHitBoxes();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                move(Direction.UP);
                break;

            case KeyboardEvent.KEY_DOWN:
                move(Direction.DOWN);
                break;
            case KeyboardEvent.KEY_SPACE:
                cyclesRising = 40;
                break;

        }

    }

    public void addEventsToKeyboard() {

        Keyboard keyboard = new Keyboard(this);


        KeyboardEvent pressUp = new KeyboardEvent();
        pressUp.setKey(KeyboardEvent.KEY_UP);
        pressUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressDown = new KeyboardEvent();
        pressDown.setKey(KeyboardEvent.KEY_DOWN);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressSpace = new KeyboardEvent();
        pressSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(pressUp);
        keyboard.addEventListener(pressDown);
        keyboard.addEventListener(pressSpace);


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public boolean isDead() {
        return isDead;
    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }

    public HashMap<Integer, Rectangle[]> getHitBoxes() {
        return hitBoxes;
    }

    public int getAtSprite() {
        return atSprite;
    }
}


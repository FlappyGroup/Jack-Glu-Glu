package org.academiadecodigo.stormrooters.flappy_bird.swimmer;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.Direction;
import org.academiadecodigo.stormrooters.flappy_bird.game.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class Swimmer implements KeyboardHandler {

    private boolean isDead;
    private ArrayList<String> spritesPath;
    private ArrayList<String> deathSpritesPath;
    private Picture picture;
    private HashMap<Integer, Rectangle[]> hitBoxes;
    private int atSprite;
    private int cyclesRising;

    public Swimmer() {

        this.atSprite = 1;
        this.spritesPath = new ArrayList<>();
        this.deathSpritesPath = new ArrayList<>();
        this.hitBoxes = new HashMap<>();


    }

    /**
     * generate and load all data needed for the swimmer
     */
    public void init() {

        for (int i = 0; i <= 6; i++) {
            spritesPath.add("resources/swimmer/swimmer" + i + ".png");
        }

        for (int i = 0; i <= 3; i++) {
            deathSpritesPath.add("resources/swimmer/swimmer-death" + i + ".png");
        }

        picture = new Picture(Game.PADDING + 100, Game.PADDING + 200, spritesPath.get(atSprite));


        addEventsToKeyboard();
        for (int i = 1; i <= 7; i++) {
            hitBoxes.put(i, generateHitBox(i));
        }
    }

    /**
     * show hitboxes for current sprite
     */
    private void drawHitBoxes() {

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
     * listener the bird up and down (if cycles rising > 0 listener up)
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

    /**
     * load next sprite if it is the after the last loads the first again
     * if swimmer is dead load different spites
     */
    public void nextSprite() {

        if (!isDead) {
            atSprite++;
            if (atSprite >= spritesPath.size()) {

                atSprite = 1;
            }

            picture.load(spritesPath.get(atSprite));
            picture.draw();
            return;
        }

        if (atSprite >= deathSpritesPath.size()) {

            atSprite = 1;
        }

        picture.load(deathSpritesPath.get(atSprite));
        picture.draw();
        atSprite++;


    }

    /**
     * Move swimmer up if cyclesRising > 0
     */
    public void move() {

        int movement = 1;
        if (isDead) {
            cyclesRising = 0;
        }
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

        isDead = true;
        cyclesRising = 0;
        atSprite = 1;
    }

    public void reset() {
        picture.delete();
        isDead = false;
        atSprite = 1;
        picture.translate(0, -250);

        for (Rectangle[] hitBoxes : hitBoxes.values()) {

            for (Rectangle hitBox : hitBoxes) {

                hitBox.translate(0, -250);
            }
        }
    }

    public void draw() {
        picture.draw();
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

    public int getHeight() {
        return this.picture.getHeight();
    }

    public int getAtSprite() {
        return atSprite;
    }
}


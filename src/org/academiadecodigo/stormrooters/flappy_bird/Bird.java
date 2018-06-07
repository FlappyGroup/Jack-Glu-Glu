package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.awt.*;

public class Bird implements KeyboardHandler {

    private boolean isDead;
    private Rectangle hitBox;
    private int ciclesRising;


    public Bird(int x, int y, int width, int height) {

        this.hitBox = new Rectangle(x, y, width, height);
        this.hitBox.fill();
        addEventsToKeyboard();

    }

    /**
     * move the bird up and down (if cycles rising > 0 move up)
     */

    public void move(Direction direction) {

        int newYIncrement = 0;

        switch (direction) {

            case UP:
                newYIncrement = -1;
                break;

            case DOWN:
                newYIncrement = +1;

                break;

        }

        this.hitBox.translate(0, newYIncrement);


    }

    public void die() {
        isDead = true;
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

        keyboard.addEventListener(pressUp);
        keyboard.addEventListener(pressDown);


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    /**
     * set the cyclesRising to the number of cycles  where the bird is rising
     */
    public void fly() {


    }

    public int getHeight() {
        return hitBox.getHeight();
    }

    public int getWidth() {
        return hitBox.getWidth();
    }

    public int getX() {
        return hitBox.getX();
    }

    public int getY() {
        return hitBox.getY();
    }
}


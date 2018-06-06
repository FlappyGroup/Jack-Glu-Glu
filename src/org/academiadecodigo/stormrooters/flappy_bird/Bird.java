package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.awt.*;

public class Bird implements Movable, KeyboardHandler {

    private boolean isDead;
    private Rectangle hitBox;
    private int ciclesRising;
    private int y;
    private int x;


    public Bird(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;

        this.hitBox = new Rectangle(this.x, this.y, width, height);
        this.hitBox.draw();
        addEventsToKeyboard();

    }

    /**
     * move the bird up and down (if cycles rising > 0 move up)
     */
    @Override
    public void move(Direction direction) {

        switch (direction) {

            case UP:
                this.y--;
                break;

            case DOWN:
                this.y++;
                break;

        }
        System.out.println("x=" + x);
        System.out.println("y=" + y);
        this.hitBox.translate(x,y);


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                System.out.println("UP");
                move(Direction.UP);
                break;
            case KeyboardEvent.KEY_DOWN:
                System.out.println("DOWN");
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
}

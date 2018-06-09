package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Bird implements KeyboardHandler {

    private boolean isDead;
    //private Rectangle hitBox;
    private Picture hitBox1;
    private int ciclesRising;


    public Bird(int x, int y, int width, int height) {

       // this.hitBox = new Rectangle(x, y, width, height);
        this.hitBox1 = new Picture(x, y, "C:\\Users\\Papi Litron\\Desktop\\flappy_bird\\PNG\\enemies\\mine.png");
        this.hitBox1.draw();
       // this.hitBox.fill();
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
                newYIncrement = 1;

                break;

        }

        //this.hitBox.translate(0, newYIncrement);
        this.hitBox1.translate(0, newYIncrement);


    }


    public void move() {


        if (ciclesRising > 0) {

           // this.hitBox.translate(0, -1);
            this.hitBox1.translate(0, -1);
            ciclesRising--;
            return;
        }


       // this.hitBox.translate(0, 1);
        this.hitBox1.translate(0,1);


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

            case KeyboardEvent.KEY_SPACE:
                ciclesRising = 40;
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
        pressDown.setKey(KeyboardEvent.KEY_SPACE);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(pressUp);
        keyboard.addEventListener(pressDown);
        keyboard.addEventListener(pressSpace);


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    /**
     * set the cyclesRising to the number of cycles  where the bird is rising
     */
    public void fly() {


    }

    public boolean isDead() {
        return isDead;
    }

    public int getHeight() {
        return hitBox1.getHeight();
    }

    public int getWidth() {
        return hitBox1.getWidth();
    }

    public int getX() {
        return hitBox1.getX();
    }

    public int getY() {
        return hitBox1.getY();
    }
}


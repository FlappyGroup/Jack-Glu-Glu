package org.academiadecodigo.stormrooters.flappy_bird.swimmer;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.Direction;

import java.util.ArrayList;

public class Swimmer implements KeyboardHandler {

    private boolean isDead;
    private ArrayList<String> spritsPath;
    private Picture picture;
    private int atSprite;
    private int cyclesRising;

    public Swimmer(int x, int y) {

        this.atSprite = 1;
        this.spritsPath = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            spritsPath.add("resources/swimmer/swimmer" + i + ".png");
        }

        picture = new Picture(x, y, spritsPath.get(atSprite));


        this.picture.draw();
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

        this.picture.translate(0, newYIncrement);


    }

    public void nextSprit() {

        atSprite++;
        if (atSprite >= spritsPath.size()){
            atSprite = 1;
        }
        System.out.println(atSprite);
        picture.load(spritsPath.get(atSprite));
        picture.draw();
    }

    /**
     * Move swimmer up if cyclesRising > 0
     */
    public void move() {


        if (cyclesRising > 0) {

            // this.hitBox.translate(0, -1);
            this.picture.translate(0, -1);
            cyclesRising--;
            return;
        }


        // this.hitBox.translate(0, 1);
        this.picture.translate(0, 1);


    }

    /**
     * set property isDead to true
     */
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
        pressDown.setKey(KeyboardEvent.KEY_SPACE);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

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

    public int getHeight() {
        return picture.getHeight();
    }

    public int getWidth() {
        return picture.getWidth();
    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }
}


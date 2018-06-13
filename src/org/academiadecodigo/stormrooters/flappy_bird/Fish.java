package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Fish {

    private Picture picture;
    private int atSprite;
    private String[] spritesPath;
    private boolean status;

    public Fish() {
        atSprite = 1;
        spritesPath = new String[4];
    }

    public void init() {

        int random = (int) (Math.random() * 3);
        System.out.println(random);
        for (int i = 0; i < 4; i++) {
            spritesPath[i] = "fish/fish" + random + "/fish" + i + ".png";
        }

        picture = new Picture(1300, 200, spritesPath[atSprite]);
    }

    public void nextSprite() {

        atSprite++;
        if (atSprite >= spritesPath.length) {

            atSprite = 1;
        }

        picture.load(spritesPath[atSprite]);
        picture.draw();
    }

    public void move() {
        picture.translate(-1, 0);
    }
}

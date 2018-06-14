package org.academiadecodigo.stormrooters.flappy_bird.game;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.flappy_bird.Sound;

public class GameHandler implements MouseHandler {

    private Game game;
    private boolean next = false;
    private Picture menuPlay;
    private Picture menuExit;
    private boolean exit = false;
    private Picture title;

    public GameHandler() {

        this.game = new Game();

    }

    public void gameStart() throws InterruptedException {

        game.init();

        menuPlay = new Picture(580, 250, "resources/Play.png");


        listener();
        menuExit = new Picture(580, 350, "resources/EXIT.png");
        title = new Picture(290, 50, "resources/title.png");

        Sound sound = new Sound("/resources/sound.wav");
        sound.loopIndef();
        sound.play(true);

        while (!exit) {

            // missing condition to leave menu

            menuPlay.draw();
            menuExit.draw();
            title.draw();
            while (!next) {

                Thread.sleep(500);


            }

            menuPlay.delete();
            menuExit.delete();
            title.delete();

            if (exit) {
                break;
            }


            game.runGame();

            next = false;
/*

            // clean all obsjects and swimmer
            next = false;

            // missing condition to leave pos-death scream
            while (!next) {

                //pos-death scream
                next = true;
                Thread.sleep(100);
            }

            // cycles continue until someone press click
*/


        }

        System.exit(0);

    }

    public void listener() {
        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        double mousex = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        if (mousex > menuPlay.getX() && mousex < menuPlay.getX() + menuPlay.getWidth()
                && mouseY > menuPlay.getY() && mouseY < menuPlay.getY() + menuPlay.getHeight()) {
            next = true;


        }

        if (mousex > menuExit.getX() && mousex < menuExit.getX() + menuExit.getWidth()
                && mouseY > menuExit.getY() && mouseY < menuExit.getY() + menuExit.getHeight()) {
            exit = true;
            next = true;
        }

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}

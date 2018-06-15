package org.academiadecodigo.stormrooters.jackgluglu.game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameHandler implements MouseHandler {

    private Game game;
    private boolean next = false;
    private Picture menuPlay;
    private Picture menuExit;
    private boolean exit = false;
    private Picture title;
    private int bestScore;
    private Text highScore;

    public GameHandler() {

        this.game = new Game();
        bestScore = 0;

    }

    public void gameStart() throws InterruptedException {

        game.init();

        menuPlay = new Picture(580, 250, "resources/Play.png");
        listener();
        highScore = new Text(1300, 450, bestScore + "");
        highScore.grow(70,25);
        highScore.setColor(Color.BLACK);
        menuExit = new Picture(580, 350, "resources/EXIT.png");
        title = new Picture(290, 50, "resources/title.png");


        while (!exit) {
            highScore.setText("High Score: " +bestScore + "");
            highScore.draw();
            // missing condition to leave menu

            menuPlay.draw();
            menuExit.draw();
            title.draw();
            while (!next) {

                Thread.sleep(500);


            }
            highScore.delete();
            menuPlay.delete();
            menuExit.delete();
            title.delete();

            if (exit) {
                break;
            }


            int score = game.runGame();
            if (score > bestScore) {
                bestScore = score;
            }
            next = false;
        }

        System.exit(0);

    }

    public void listener() {
        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY() - 25 - Game.PADDING;

        if (mouseX > menuPlay.getX() && mouseX < menuPlay.getX() + menuPlay.getWidth()
                && mouseY > menuPlay.getY() && mouseY < menuPlay.getY() + menuPlay.getHeight()) {
            next = true;


        }

        if (mouseX > menuExit.getX() && mouseX < menuExit.getX() + menuExit.getWidth()
                && mouseY > menuExit.getY() && mouseY < menuExit.getY() + menuExit.getHeight()) {
            exit = true;
            next = true;
        }
        System.out.println();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}

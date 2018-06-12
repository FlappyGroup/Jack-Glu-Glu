package org.academiadecodigo.stormrooters.flappy_bird.game;

public class GameHandler {

    private Game game;

    public GameHandler() {

        this.game = new Game();

    }

    public void gameStart() throws InterruptedException {

        game.init();

        while (true) {

            boolean next = false;
            // missing condition to leave menu

            while (!next) {
                // menus
                System.out.println("fuck");
                //next = true;
                Thread.sleep(100);
            }

            game.runGame();


            // clean all obsjects and swimmer
            next = false;

            // missing condition to leave pos-death scream
            while (!next) {

                //pos-death scream
                next = true;
                Thread.sleep(100);
            }

            // cycles continue until someone press click
        }


    }

}

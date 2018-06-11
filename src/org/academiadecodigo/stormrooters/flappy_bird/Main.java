package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.stormrooters.flappy_bird.game.Game;

public class Main {
    public static void main(String[] args ) throws InterruptedException{


        Game game = new Game();
        game.init();
        game.runGame();
    }
}

package org.academiadecodigo.stormrooters.flappy_bird;

import org.academiadecodigo.stormrooters.flappy_bird.game.Game;
import org.academiadecodigo.stormrooters.flappy_bird.game.GameHandler;

public class Main {
    public static void main(String[] args ) throws InterruptedException{


        GameHandler game = new GameHandler();
        game.gameStart();
    }
}

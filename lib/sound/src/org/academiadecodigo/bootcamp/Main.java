package org.academiadecodigo.bootcamp;

/**
 * Created by falcao on 01/02/2017.
 */
public class Main {

    public static void main(String[] args) {

        Sound sound = new Sound("/resources/puddipuddimusic.wav");

        sound.loopIndef();
        sound.play(true);

        while (true) {

            try {
                Thread.sleep(1000);
                System.out.println("Game loop in here");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

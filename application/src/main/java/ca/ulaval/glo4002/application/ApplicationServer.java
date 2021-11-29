package ca.ulaval.glo4002.application;

import ca.ulaval.glo4002.game.GameServer;
import ca.ulaval.glo4002.vet.VetServer;

public class ApplicationServer {
    public static void main(String[] args) throws InterruptedException {
        Thread vet = new Thread(new VetServer(args));
        Thread game = new Thread(new GameServer());

        game.start();
        vet.start();

        game.join();
        vet.join();
    }
}

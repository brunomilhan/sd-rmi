package model;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Responsavel por criar um timertask que controlará as multas
 * Created by Bruno on 07/10/2016.
 */
class LibraryFines {
    private Timer timer;

    LibraryFines() {
        timer = new Timer();
    }

    void applyFine(Client client) {
        FineTimerTask fineTimerTask = new FineTimerTask(client);
        timer.schedule(fineTimerTask, LibraryRules.TIME_PENALTY);
    }

    private class FineTimerTask extends TimerTask {
        Client client;

        FineTimerTask(Client client) {
            this.client = client;
        }

        public void run() {
            client.setStatus(Client.OK);
            System.out.println("Multa  expirou para o cliente: " + client.getName());
        }
    }

}

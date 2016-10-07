package model;

import rmi_class.Client;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bruno on 07/10/2016.
 */
public class LibraryFines {
    private Timer timer;
    private FineTimerTask fineTimerTask;

    public LibraryFines(){
        timer = new Timer();
    }

    public void applyFine(Client client){
        fineTimerTask = new FineTimerTask(client);
        System.out.println(LibaryRules.TIME_PENALTY);
        timer.schedule(fineTimerTask, LibaryRules.TIME_PENALTY);
    }

    private class FineTimerTask extends TimerTask{
        Client client;

        FineTimerTask(Client client){
            this.client = client;
        }

        public void run() {
            client.setStatus(Client.OK);
        }
    }

}

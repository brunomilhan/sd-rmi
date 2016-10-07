package main_test;

import config.NamesService;
import model.Client;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bruno on 05/10/16.
 */
public class MainTestClient {
    public static void main(String []args){
        ServerInterface serverInterface;
        NamesService namesService = new NamesService();
        serverInterface = namesService.getServerRegistry();
        try {
            boolean bol = serverInterface.lend("bruno", "Teste1");
            bol = serverInterface.lend("bruno", "Teste1");
            for (String s : serverInterface.listBooks()){
                System.out.println(s);
            }
            System.out.println(bol);
            for (String s : serverInterface.listBooks()){
                System.out.println(s);
            }
            boolean test2 = serverInterface.renew("bruno", "Teste1");
            System.out.println(test2);

            Client client = new Client();
            serverInterface.reserve("bruno", "Teste1", client,
                    new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)));

            boolean testReturn = serverInterface.returnBook("bruno", "Teste1");
            System.out.println("retorno"+testReturn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

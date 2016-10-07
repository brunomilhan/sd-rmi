package main_test;

import config.NamesService;
import rmi_class.Book;
import rmi_class.BookInterface;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;

/**
 * Created by bruno on 05/10/16.
 */
public class MainTestClient {
    public static void main(String []args){
        ServerInterface serverInterface;
        NamesService namesService = new NamesService();
        serverInterface = namesService.getServerRegistry();
        try {
            for (Book b : serverInterface.listBooks()){
                System.out.println( b);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

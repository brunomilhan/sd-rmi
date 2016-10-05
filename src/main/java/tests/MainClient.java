package tests;

import cliente.Cliente;

import java.rmi.RemoteException;

/**
 * Created by bruno on 28/09/16.
 */
public class MainClient {
    public static void main(String args[]){
        Cliente cliente = null;
        try {
            cliente = new Cliente("bruno");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        cliente.callChamar();

    }
}

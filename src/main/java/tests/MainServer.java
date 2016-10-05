package tests;

import config.NamesService;
import servidor.Servidor;

/**
 * Created by bruno on 28/09/16.
 */
public class MainServer {

    public static void main(String []args){
        NamesService namesService = new NamesService();
        namesService.setRegistry();
        namesService.bindReference();
        while (true){

        }
    }
}

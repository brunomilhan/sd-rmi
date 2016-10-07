package model;

import rmi_class.Book;
import rmi_class.Client;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
    private List<Book> books = null;

    public Server() throws RemoteException {
        super();
        books = new ArrayList<Book>();
        //for tests
        books.add(new Book("Teste1"));
        books.add(new Book("Teste2"));
        books.add(new Book("Teste3"));
    }

    public List<String> listBooks() throws RemoteException {
        List<String> booksAvailable = new ArrayList<String>();
        for (Book b : books)
            if (b.isAvaiable())
                booksAvailable.add(b.getName());

        return booksAvailable;
    }

    public boolean lend(String clientName, Book book) throws RemoteException {
        List<Client> clients = new ArrayList<Client>();

        // verifica se cliente está disponivel para emprestar
        boolean cliAvailable = false;
        Client borrow2cli = null;

        // verifica se cliente já existe
        for (Client c : clients)
            if (c.equals(clientName))
                borrow2cli = c;

        if (borrow2cli != null) {
            if (borrow2cli.getLoans() <= LibaryRules.LOANS_LIMIT)
                cliAvailable = true;
        } else {
            borrow2cli = new Client(clientName);
            clients.add(borrow2cli);
            cliAvailable = true;
        }

        // empresta o livro se estiver disponivel no acervo
        if (cliAvailable)
            for (Book b : books)
                if (b.equals(book) && b.getStatus().equals(Book.AVAILABLE)){
                    registerBookLend(b, borrow2cli);
                    return true;
                }

        return false;
    }

    private void registerBookLend(Book book, Client client){
        client.setLoans(1);
        book.setStatus(Book.UNAVAILABLE);
        book.setClient(client);
        book.setInitDate(new Date(System.currentTimeMillis()));
        book.setEndDate(new Date(System.currentTimeMillis() +  LibaryRules.LOAN_PERIOD));
        book.setRenew(false);
    }

    /*public List<Book> find(Book book) throws RemoteException {
        for (Book b : books){
            if (book.equals(b));
        }
        return null;
    }*/
}

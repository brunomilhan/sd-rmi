package model;

import rmi_class.Book;
import rmi_class.BookInterface;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
    private List<BookInterface> books;

    public Server() throws RemoteException {
        super();
        books = new ArrayList<BookInterface>();
        //for tests
        books.add(new Book("Teste1"));
        books.add(new Book("Teste2"));
        books.add(new Book("Teste3"));
    }

    public List<BookInterface> listBooks() throws RemoteException {
        return books;
    }

    /*public List<Book> find(Book book) throws RemoteException {
        for (Book b : books){
            if (book.equals(b));
        }
        return null;
    }*/
}

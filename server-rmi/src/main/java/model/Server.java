package model;

import rmi_interfaces.ClientInterface;
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
    private List<Client> clients = new ArrayList<Client>();
    private LibraryFines libraryFines = new LibraryFines();

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

    public boolean lend(String clientName, String bookName) throws RemoteException {
        // verifica se cliente está disponivel para emprestar
        boolean cliAvailable = false;
        Client borrow2cli = null;

        // verifica se cliente já existe
        for (Client c : clients)
            if (c.equalsName(clientName))
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
                if (b.equalsName(bookName) && b.getStatus().equals(Book.AVAILABLE)){
                    registerBookLend(b, borrow2cli);
                    return true;
                }

        return false;
    }

    public boolean renew(String clientName, String bookName) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if(book != null)
            if (client != null)
                if (book.isEmptyReserveList())
                    if (checkClientOverdue(client))
                        if (client.isAvailable()){
                            registerBookRenew(book, client);
                            return true;
                        }
        return false;
    }

    public boolean returnBook(String clientName, String bookName) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if(book != null)
            if (client != null) {
                long time = book.getEndDate().getTime() - System.currentTimeMillis();
                if (time < 0)
                    applyFine(client);
                registerBookReturn(client, book);
                return true;
            }
        return false;
    }

    public boolean reserve(String clientName, String bookName, ClientInterface clientInterface,
    Date date2Expire) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if(book != null)
            if (client != null){
                if (!book.isAvaiable()){
                    Reserve reserve = new Reserve(clientInterface, date2Expire);
                    book.addClientInReserveList(reserve);
                }
            }
        return false;
    }

    private void registerBookReturn(Client client, Book book) {
        client.removeLoanBook(book);
        client.setLoans(-1);
        book.cleanLoan();
        checkReserveList(book);
    }

    private void checkReserveList(Book book){
        if (!book.isEmptyReserveList()){
            Reserve reserve = book.getReserveList().get(0);
            if (reserve.isExpired())
                try {
                    reserve.getClientInterface().notifyBookAvailable(book.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        }
    }

    private void applyFine(Client client) {
        client.setStatus(Client.UNAVAILABLE);
        libraryFines.applyFine(client);
    }

    private boolean checkClientOverdue(Client client){
        for (Book b : client.getLoansBooks())
            if (b.isOverdue())
                return false;
        return true;
    }

    private Client findClient(String name){
        for (Client client : clients)
            if (client.equalsName(name))
                return client;
        return null;
    }

    private Book findBook(String name){
        for (Book book : books)
            if (book.equalsName(name))
                return book;
        return null;
    }

    private void registerBookRenew(Book book, Client client){
        client.removeLoanBook(book);
        book.setInitDate(new Date(System.currentTimeMillis()));
        book.setEndDate(new Date(System.currentTimeMillis() +  LibaryRules.LOAN_PERIOD));
        book.setRenew(false);
    }

    private void registerBookLend(Book book, Client client){
        client.addLoanBook(book);
        book.setStatus(Book.UNAVAILABLE);
        book.setClient(client);
        book.setInitDate(new Date(System.currentTimeMillis()));
        book.setEndDate(new Date(System.currentTimeMillis() +  LibaryRules.LOAN_PERIOD));
        book.setRenew(true);
    }
}

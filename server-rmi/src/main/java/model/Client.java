package model;

import rmi_interfaces.ClientInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 07/10/2016.
 */
public class Client {
    public static final String OK = "OK";
    public static final String UNAVAILABLE = "UNAVAILABLE";

    private String name;
    private int loans;
    private List<Book> loansBooks;
    private String status;


    public Client(String name) {
        this.name = name;
        this.status = OK;
        loansBooks = new ArrayList<Book>();
    }

    public boolean isAvailable(){
        return status.equals(OK);
    }

    public void addLoanBook(Book book){
        loansBooks.add(book);
        loans +=1;
    }

    public void removeLoanBook(Book book){
        Book b2remove = null;
        for (Book b : loansBooks)
            if (b.getName().equals(book.getName()))
                b2remove = b;
        loansBooks.remove(b2remove);
    }

    public List<Book> getLoansBooks() {
        return loansBooks;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans += loans;
    }

    public boolean equalsName(String clientName) {
        return this.name.equals(clientName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

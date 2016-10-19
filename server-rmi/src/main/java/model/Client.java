package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um cliente. Apenas utilizado no Servidor.
 * Created by Bruno on 07/10/2016.
 */
class Client {
    static final String OK = "OK";
    static final String UNAVAILABLE = "UNAVAILABLE";

    private String name;
    private int loans;
    private List<Book> loansBooks;
    private String status;


    Client(String name) {
        this.name = name;
        this.status = OK;
        loansBooks = new ArrayList<Book>();
    }

    boolean isAvailable() {
        return status.equals(OK);
    }

    void addLoanBook(Book book) {
        loansBooks.add(book);
        loans += 1;
    }

    void removeLoanBook(Book book) {
        Book b2remove = null;
        for (Book b : loansBooks)
            if (b.getName().equals(book.getName()))
                b2remove = b;
        loansBooks.remove(b2remove);
    }

    List<Book> getLoansBooks() {
        updateBookStatus();
        return loansBooks;
    }

    void setStatus(String status) {
        this.status = status;
    }

    int getLoans() {
        return loans;
    }

    void setLoans(int loans) {
        this.loans += loans;
    }

    boolean equalsName(String clientName) {
        return this.name.equals(clientName);
    }

    private void updateBookStatus(){
        for (Book b : loansBooks)
            if (b.getEndDate().getTime() < System.currentTimeMillis())
                b.setStatus(Book.OVERDUE);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

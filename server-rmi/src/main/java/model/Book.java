package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representação de um livro, apenas utilizado no servidor.
 * Created by bruno on 05/10/16.
 */
class Book {
    static final String AVAILABLE = "AVAILABLE";
    static final String UNAVAILABLE = "UNAVAILABLE";
    static final String OVERDUE = "OVERDUE";
    private String name;
    private String status;
    private Date endDate;
    private List<Reserve> reserveList;

    Book(String name) {
        reserveList = new ArrayList<Reserve>();
        this.name = name;
        this.status = AVAILABLE;
    }

    void cleanLoan() {
        status = AVAILABLE;
        setEndDate(null);
    }

    boolean isAvaiable() {
        return status.equals(AVAILABLE);
    }

    boolean isOverdue() {
        return status.equals(OVERDUE);
    }

    boolean isEmptyReserveList() {
        return reserveList.isEmpty();
    }

    void addClientInReserveList(Reserve reserve) {
        reserveList.add(reserve);
    }

    String getName() {
        return name;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }


    boolean equalsName(String bookName) {
        return this.name.equals(bookName);
    }

    Date getEndDate() {
        return endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    List<Reserve> getReserveList() {
        return reserveList;
    }

}

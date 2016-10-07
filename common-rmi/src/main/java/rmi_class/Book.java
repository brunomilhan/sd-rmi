package rmi_class;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 */
public class Book implements Serializable {
    public static final String AVAILABLE = "AVAILABLE";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    private String name;
    private String status;

    private int amount;
    private Date initDate;
    private Date endDate;
    private List<Client> clientReserveList;
    private Client client;
    private boolean isRenew;

    public Book(String name) {
        this.name = name;
        this.status = AVAILABLE;
    }

    public boolean isAvaiable() {
        return status.equals(AVAILABLE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object book) {
        return this.name.equals(book.toString());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Client> getClientReserveList() {
        return clientReserveList;
    }

    public void setClientReserveList(List<Client> clientReserveList) {
        this.clientReserveList = clientReserveList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isRenew() {
        return isRenew;
    }

    public void setRenew(boolean renew) {
        isRenew = renew;
    }
}

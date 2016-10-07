package rmi_class;

import java.util.Date;
import java.util.List;

/**
 * Created by Bruno on 07/10/2016.
 */
public class BookServer extends Book{
    private int amount;
    private Date initDate;
    private Date endDate;
    private List<Client> clientReserveList;
    private Client client;
    private boolean isRenew;

    public BookServer(String name) {
        super(name);
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

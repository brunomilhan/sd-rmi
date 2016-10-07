package model;

import rmi_interfaces.ClientInterface;

import java.util.Date;

/**
 * Created by Bruno on 07/10/2016.
 */
public class Reserve {
    private Date date2Expire;
    private ClientInterface clientInterface;

    public Reserve(ClientInterface clientInterface, Date date2Expire) {
        this.date2Expire = date2Expire;
        this.clientInterface = clientInterface;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() < date2Expire.getTime();
    }

    public ClientInterface getClientInterface() {
        return clientInterface;
    }
}

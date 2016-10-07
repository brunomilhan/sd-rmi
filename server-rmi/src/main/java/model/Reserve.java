package model;

import rmi_interfaces.ClientInterface;

import java.util.Date;

/**
 * Created by Bruno on 07/10/2016.
 */
class Reserve {
    private Date date2Expire;
    private ClientInterface clientInterface;

    Reserve(ClientInterface clientInterface, Date date2Expire) {
        this.date2Expire = date2Expire;
        this.clientInterface = clientInterface;
    }

    boolean isExpired() {
        return System.currentTimeMillis() < date2Expire.getTime();
    }

    ClientInterface getClientInterface() {
        return clientInterface;
    }
}

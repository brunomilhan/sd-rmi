package model;

import rmi_interfaces.ClientInterface;

import java.util.Date;

/**
 * Representa as reservas (Registros de interesse).
 * Created by Bruno on 07/10/2016.
 */
class Reserve {
    private Date date2Expire;
    private ClientInterface clientInterface;
    private String clientName;

    Reserve(String clientName, ClientInterface clientInterface, Date date2Expire) {
        this.clientName = clientName;
        this.date2Expire = date2Expire;
        this.clientInterface = clientInterface;
    }

    boolean isExpired() {
        return System.currentTimeMillis() < date2Expire.getTime();
    }

    ClientInterface getClientInterface() {
        return clientInterface;
    }

    public String getClientName() {
        return clientName;
    }
}

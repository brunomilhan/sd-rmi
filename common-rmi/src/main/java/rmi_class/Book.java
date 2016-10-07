package rmi_class;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 05/10/16.
 */
public class Book implements Serializable {
    private String name;
    private String status;

    public Book(String name) {
        this.name = name;
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
}

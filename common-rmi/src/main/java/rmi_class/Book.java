package rmi_class;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by bruno on 05/10/16.
 */
public class Book implements Serializable {
    private String name;

    public Book(String name) throws RemoteException {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

package rmi_class;

import java.io.Serializable;

/**
 * Created by Bruno on 07/10/2016.
 */
public class Client implements Serializable {
    private String name;
    private int loans;

    public Client(String name) {
        this.name = name;
    }

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans += loans;
    }

    @Override
    public boolean equals(Object clientName) {
        return this.name.equals(clientName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

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
    // lista de clientes (onde fica a classe cliente?)
    // cliente emprestado
    private boolean isRenew;

    public BookServer(String name) {
        super(name);
    }

}

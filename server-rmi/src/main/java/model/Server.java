package model;

import rmi_interfaces.ClientInterface;
import rmi_interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 05/10/16.
 *  Classe que implementa os métodos da Interface do servidor, que é utilizado pelo RMI.
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {

    // Essas varriaveis existem apenas no contexto do servidor, para controle e não
    // são disponibilizadas para o cliente.
    private List<Book> books = null;
    private List<Client> clients = new ArrayList<Client>();
    private LibraryFines libraryFines = new LibraryFines();

    public Server() throws RemoteException {
        super();
        books = new ArrayList<Book>();
        //for tests
        books.add(new Book("Teste1"));
        books.add(new Book("Teste2"));
        books.add(new Book("Teste3"));
    }

    /**
     * Requisito 1 - Listar os livros disponiveis no acervo
     * @return lista dos livros disponiveis
     *
     */
    public List<String> listBooks() throws RemoteException {
        List<String> booksAvailable = new ArrayList<String>();
        for (Book b : books)
            if (b.isAvaiable())
                booksAvailable.add(b.getName());

        return booksAvailable;
    }

    /**
     * Requisito 2 - Emprestar livro
     *  O livro apenas será emprestado se o usuário estiver dentro do limite de livros emprestados
     *  e o livro estiver disponivel no acervo.
     * @see LibraryRules
     *
     * O nome do cliente é adicionado na lista de clientes do servidor apenas para controle.
     * @return boolen sinalizando o resutado da operação.
     */
    public synchronized boolean lend(String clientName, String bookName) throws RemoteException {
        // verifica se cliente está disponivel para emprestar
        boolean cliAvailable = false;
        Client borrow2cli = null;

        // verifica se cliente já existe
        for (Client c : clients)
            if (c.equalsName(clientName))
                borrow2cli = c;

        if (borrow2cli != null) {
            if (borrow2cli.getLoans() <= LibraryRules.LOANS_LIMIT)
                cliAvailable = true;
        } else {
            borrow2cli = new Client(clientName);
            clients.add(borrow2cli);
            cliAvailable = true;
        }

        // empresta o livro se estiver disponivel no acervo
        if (cliAvailable)
            for (Book b : books)
                if (b.equalsName(bookName) && b.getStatus().equals(Book.AVAILABLE)) {
                    registerBookLend(b, borrow2cli);
                    return true;
                }

        return false;
    }

    /**
     * Requisito 3 - Renovar livro
     * É realizado as 3 validações descritas no requisito.
     */
    public boolean renew(String clientName, String bookName) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if (book != null)
            if (client != null)
                if (book.isEmptyReserveList())
                    if (checkClientOverdue(client))
                        if (client.isAvailable()) {
                            registerBookRenew(book, client);
                            return true;
                        }
        return false;
    }

    /**
     * Requisito 4 - Devolução
     * É verificado se está dentro do prazo, caso não é aplicado uma multa.
     * Veja a  classe Library fines que implementa um TimerTask para gerar
     * as multas por um determinado tempo
     *
     * @see LibraryFines
     */
    public boolean returnBook(String clientName, String bookName) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if (book != null)
            if (client != null) {
                long time = book.getEndDate().getTime() - System.currentTimeMillis();
                if (time < 0)
                    applyFine(client);
                registerBookReturn(client, book);
                return true;
            }
        return false;
    }

    /**
     * Requisito 5 - Reserva
     *  Apenas é reservado os livros que estão indisponiveis.
     *  É criado um objeto reserva contendo as informações necessárias para realizar o callback,
     *  e esse objeto é adicionado em uma lista.
     *
     *  @see Reserve
     * @param clientInterface instância da interface do cliente para posteriormente notifica-lo.
     */
    public boolean reserve(String clientName, String bookName, ClientInterface clientInterface,
                           Date date2Expire) throws RemoteException {
        Book book = findBook(bookName);
        Client client = findClient(clientName);
        if (client == null)
            clients.add(new Client(clientName));

        if (book != null) {
            if (!book.isAvaiable()) {
                Reserve reserve = new Reserve(clientName, clientInterface, date2Expire);
                book.addClientInReserveList(reserve);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista os livros indisponiveis.
     * Este método não está explicito nos requisitos, embora é necessário para o usuário
     * saber os livros que estão indisponiveis e realizar uma reserva.
     */
    public List<String> listUnavailableBooks() throws RemoteException {
        List<String> booksUnavailable = new ArrayList<String>();
        for (Book b : books)
            if (!b.isAvaiable())
                booksUnavailable.add(b.getName());

        return booksUnavailable;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * É removido o livro na lista de livros emprestados para o cliente.
     * Os dados de empréstimo do livro são limpos
     * E é verificado se o livro tem registro de interesse, caso sim o método @checkReserveList
     * é chamado para realizar a lógica.
     */
    private void registerBookReturn(Client client, Book book) {
        client.removeLoanBook(book);
        client.setLoans(-1);
        book.cleanLoan();
        checkReserveList(book);
    }

    /**
     * Verifica se o livro tem lista de reserva, caso sim é notificado o cliente interessado,
     * por ordem.
     * É verificado se a reserva não expirou.
     * Por meio da instância da interface do usuário é realizado a notificação, essa
     * interface foi guardada quando o usuário registrou sua reserva.
     */
    private void checkReserveList(Book book) {
        if (!book.isEmptyReserveList()) {
            Reserve reserve = book.getReserveList().get(0);
            if (reserve.isExpired())
                try {
                    reserve.getClientInterface().notifyBookAvailable(book.getName());
                    // not lend just notify client
                    //registerBookLend(book, new Client(reserve.getClientName()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Deixa o cliente indisponivel.
     * Aplica a multa @{@link LibraryFines}
     */
    private void applyFine(Client client) {
        client.setStatus(Client.UNAVAILABLE);
        libraryFines.applyFine(client);
    }

    private boolean checkClientOverdue(Client client) {
        for (Book b : client.getLoansBooks())
            if (b.isOverdue())
                return false;
        return true;
    }

    /**
     * utilizado para auxiliar os métodos do servidor
     */
    private Client findClient(String name) {
        for (Client client : clients)
            if (client.equalsName(name))
                return client;
        return null;
    }

    /**
     * utilizado para auxiliar os métodos do servidor
     */
    private Book findBook(String name) {
        for (Book book : books)
            if (book.equalsName(name))
                return book;
        return null;
    }

    private void registerBookRenew(Book book, Client client) {
        client.removeLoanBook(book);
        book.setEndDate(new Date(System.currentTimeMillis() + LibraryRules.LOAN_PERIOD));
        client.addLoanBook(book);
    }

    private void registerBookLend(Book book, Client client) {
        client.addLoanBook(book);
        book.setStatus(Book.UNAVAILABLE);
        book.setEndDate(new Date(System.currentTimeMillis() + LibraryRules.LOAN_PERIOD));
    }
}

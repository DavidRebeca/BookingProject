package bll;

import dao.AccountDao;
import model.Account;
import model.RentUnit;
import model.Reservation;
import model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountBll {
    private AccountDao accountDao;

    public AccountBll(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountBll() {

        accountDao = new AccountDao();
    }

    public void insertAccount(Account account) {
        accountDao.insert(account);
    }
    public Account findAccountById(String id) {
        List<Account> accounts = accountDao.readAll();
        for(Account account1 : accounts) {
            if( account1.getAccountId().equals(id)) {
                return account1;
            }
        }
        return null;
    }
    public Account verifyAccount(String username, String password) {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts = realAllAccounts();
        System.out.println(accounts.get(0).getUsername());
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password))
                return account;
        }
        return null;
    }

    public ArrayList<Account> realAllAccounts() {
        return (ArrayList<Account>) accountDao.readAll();
    }

    public Set<Reservation> getReservationsForThisAccount(Account account) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Account account1 = session.get(Account.class, account.getAccountId());
        Set<Reservation> reservations = account1.getReservations();
        return reservations;
    }

    public void edit(Account account) {
        accountDao.update(account);
    }


}

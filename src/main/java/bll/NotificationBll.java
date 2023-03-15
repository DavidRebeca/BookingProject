package bll;

import dao.AccountDao;
import dao.NotificationDao;
import model.Account;
import model.Notification;
import model.RentUnit;
import model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Set;

public class NotificationBll {
    private NotificationDao notificationDao;

    public NotificationBll() {

        notificationDao = new NotificationDao();
    }
    public void insertNotification(Notification n) {

        notificationDao.insert(n);
    }
    public void updateNotification(Notification n) {

        notificationDao.update(n);
    }
    public void deleteNotification(Notification n) {

        notificationDao.delete(n);
    }
    public ArrayList<Notification> readAllNotification() {
        return (ArrayList<Notification>) notificationDao.readAll();
    }

    public Notification findNotificationById(String id){
        ArrayList<Notification> notifications = (ArrayList<Notification>) notificationDao.readAll();
        for(Notification notification : notifications) {
            if( notification.getNotificationnId().equals(id)) {
                return notification;
            }
        }
        return null;
    }
    public Set<Notification> getNotificationForThisAccount(String id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Account account = (Account) session.get(Account.class,id);
        Set<Notification> notifications = account.getNotifications();
        return notifications;
    }
}

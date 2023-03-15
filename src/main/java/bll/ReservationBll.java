package bll;

import dao.ReservationDao;
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
import utils.ReservationFields;

import java.util.ArrayList;
import java.util.Set;

public class ReservationBll {

    ReservationDao reservationDao = new ReservationDao();
    public Set<Reservation> getReservationsForThisRoom(Room room) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Room room1 = session.get(Room.class,room.getRoomId());
        Set<Reservation> reservations = room1.getReservations();
        return reservations;
    }

    public void insertReservation(Reservation reservation) {
        reservationDao.insert(reservation);
    }
    public void deleteReservation(Reservation reservation) {
        reservationDao.delete(reservation);
    }

    public Reservation findReservationById(String id) {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationDao.readAll();
        for(Reservation reservation : reservations) {
            if(reservation.getReservationId().equals(id)) {
                return reservation;
            }
        }
        return null;
    }

}

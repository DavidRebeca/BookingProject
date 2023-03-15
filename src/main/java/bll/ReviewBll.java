package bll;

import dao.ReviewDao;
import model.RentUnit;
import model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Set;

public class ReviewBll {

    ReviewDao reviewDao = new ReviewDao();

    public Set<Review> getReviewForThisRentUnit(RentUnit rentUnit) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        RentUnit rentUnit1 = session.get(RentUnit.class,rentUnit.getUnitId());
        Set<Review> reviews = rentUnit1.getReviews();
        return reviews;
    }
    public Set<Review> getReviewForThisRentUnitbyId(String id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        RentUnit rentUnit1 = session.get(RentUnit.class,id);
        Set<Review> reviews = rentUnit1.getReviews();
        return reviews;
    }

    public void insertReview(Review review) {
        reviewDao.insert(review);
    }

}

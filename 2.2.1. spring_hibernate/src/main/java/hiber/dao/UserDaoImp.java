package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUser(String model, int series) {
//      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model = :paramName and series = :paramName2");
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User  inner join fetch User.userCar as car with car.model = :paramName and car.series = :paramName2");
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                " from User where userCar.model = :paramName and userCar.series = :paramName2");
        query.setParameter("paramName", model);
        query.setParameter("paramName2", series);
        return query.getResultList().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}

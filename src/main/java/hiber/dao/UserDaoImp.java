package hiber.dao;

import hiber.model.User;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;
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
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> userFromCar(String model, int series) {
       @Deprecated
       Query query = sessionFactory.getCurrentSession()
               .createQuery("from User as user where empCar.model = :model and empCar.series = :series");
       query.setParameter("model", model);
       query.setParameter("series", series);
      return query.getResultList();
   }


}

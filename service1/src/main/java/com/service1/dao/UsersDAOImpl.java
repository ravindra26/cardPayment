package com.service1.dao;

import com.service1.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ravindra
 */
@Repository
public class UsersDAOImpl {

    @Autowired
    SessionFactory sf;

    public void setSeesionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    public Integer addUser(Customer customer) {
        return (Integer) this.sf.getCurrentSession().save(customer);
    }

    public Customer getUserByUsername(String username) {
        Criteria crit = sf.getCurrentSession().createCriteria(Customer.class);
        crit.add(Restrictions.eq("username", username));
        return (Customer) crit.uniqueResult();
    }
    
    public void updateUser(Customer cus){
        this.sf.getCurrentSession().update(cus);
    }
}

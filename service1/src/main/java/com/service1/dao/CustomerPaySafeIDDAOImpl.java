package com.service1.dao;

import com.service1.model.Customer;
import com.service1.model.CustomerPaysafeID;
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
public class CustomerPaySafeIDDAOImpl {

    @Autowired
    SessionFactory sf;

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    public Integer addID(Customer customer, String id) {
        CustomerPaysafeID obj = new CustomerPaysafeID();
        obj.setCustomer(customer);
        obj.setPaysafeCustomerID(id);
        return (Integer) this.sf.getCurrentSession().save(obj);
    }

    public CustomerPaysafeID getPaymentIDByCustomer(Customer customer) {
        Criteria crit = this.sf.getCurrentSession().createCriteria(CustomerPaysafeID.class);
        crit.add(Restrictions.eq("customer", customer));
        return (CustomerPaysafeID) crit.uniqueResult();
    }
}

package com.service1.dao;

import com.service1.model.Configs;
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
public class ConfigsDAOImpl {

    @Autowired
    SessionFactory sf;

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    public Configs getConfigByKey(String key) {
        Criteria crit = this.sf.getCurrentSession().createCriteria(Configs.class);
        crit.add(Restrictions.eq("key", key));
        return (Configs) crit.uniqueResult();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.hbm.bean.Tallyman;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @gnv solution
 */
@Service("tallymanDao")
@Transactional
public class TallymanDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Tallyman> getTallymans(String corpId, String whCode) {
        return sessionFactory.getCurrentSession().createCriteria(Tallyman.class)
                .add(Restrictions.eq("corpId", corpId))
                .add(Restrictions.eq("whCode", whCode))
                .list();
    }

    public void save(Tallyman tallyman) {
        sessionFactory.getCurrentSession().save(tallyman);
    }
    
    public void update(Tallyman tallyman) {
        sessionFactory.getCurrentSession().update(tallyman);
    }

    public List<Tallyman> get(String tallyId) {
        return sessionFactory.getCurrentSession().createCriteria(Tallyman.class).add(Restrictions.like("tallyId", "%" + tallyId + "%")).list();
    }
    
    public Tallyman get(Integer id) {
        return (Tallyman) sessionFactory.getCurrentSession().get(Tallyman.class, id);
    }
}

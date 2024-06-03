package org.mpp2024.Repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Repository.Utils.HibernateUtils;

import java.util.Optional;

public class RepoAdmin implements Repo_Admin_Interface {



    /**
     * @param id 
     * @return
     */
    @Override
    public Optional<Admin> findById(Long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
           Admin admin = session.find(Admin.class, id);
            transaction.commit();
            return Optional.ofNullable(admin);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
            HibernateUtils.closeSessionFactory();
        }



    }

    @Override
    public Admin findByUsername(String username) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<Admin> query = session.createQuery("from Admin p where p.username = :username", Admin.class);
            query.setParameter("username", username);
            Admin admin= query.uniqueResult();
            transaction.commit();
            return admin;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateUtils.closeSessionFactory();
        }
    }
}

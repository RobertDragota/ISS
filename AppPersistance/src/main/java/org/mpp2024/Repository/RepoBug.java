package org.mpp2024.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mpp2024.Domain.Bug;
import org.mpp2024.Repository.Utils.HibernateUtils;


import java.util.Optional;

public class RepoBug implements Repo_Bug_Interface{
    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Bug> save(Bug entity) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            session.persist(entity);
            transaction.commit();

            return Optional.of(entity);
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

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Bug> delete(Bug entity) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
            return Optional.of(entity);
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

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Bug> update(Bug entity) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return Optional.of(entity);
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



    /**
     * @param Id
     * @return
     */
    @Override
    public Optional<Bug> findById(Long Id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Bug bug = session.find(Bug.class, Id);
            transaction.commit();
            return Optional.ofNullable(bug);
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

    /**
     * @return 
     */
    @Override
    public Iterable<Bug> findAll() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Iterable<Bug> bugs = session.createQuery("from Bug ", Bug.class).getResultList();
            transaction.commit();
            return bugs;
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

    /**
     * @return 
     */
    @Override
    public Long size() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<Long> query = session.createQuery("SELECT COUNT(b) FROM Bug b", Long.class);
            transaction.commit();
            return query.uniqueResult();
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

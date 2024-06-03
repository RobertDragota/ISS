package org.mpp2024.Repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Repository.Utils.HibernateUtils;

import java.util.Optional;

public class RepoProgrammer implements Repo_Programmer_Interface {


    /**
     * @param entity
     * @return
     */
    @Override
    public Optional<Programmer> save(Programmer entity) {

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
    public Optional<Programmer> delete(Programmer entity) {
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
    public Optional<Programmer> update(Programmer entity) {
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
     * @param
     * @return
     */
    @Override
    public Optional<Programmer> findById(Long Id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Programmer programmer = session.find(Programmer.class, Id);
            transaction.commit();
            return Optional.ofNullable(programmer);
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
    public Iterable<Programmer> findAll() {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Iterable<Programmer> programmers = session.createQuery("from Programmer ", Programmer.class).getResultList();
            transaction.commit();
            return programmers;
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
            Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Programmer p", Long.class);
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

    @Override
    public Programmer findByUsername(String username) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<Programmer> query = session.createQuery("from Programmer p where p.username = :username", Programmer.class);
            query.setParameter("username", username);
            Programmer programmer = query.uniqueResult();
            transaction.commit();
            return programmer;
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


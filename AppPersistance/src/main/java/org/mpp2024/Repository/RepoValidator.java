package org.mpp2024.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Repository.Utils.HibernateUtils;


import java.util.Optional;

public class RepoValidator implements Repo_Validator_Interface{




    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Validator> save(Validator entity) {
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
    public Optional<Validator> delete(Validator entity) {

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
    public Optional<Validator> update(Validator entity) {
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
    public Optional<Validator> findById(Long Id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Validator validator = session.find(Validator.class, Id);
            transaction.commit();
            return Optional.ofNullable(validator);
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
    public Iterable<Validator> findAll() {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Iterable<Validator> validators = session.createQuery("from Validator ", Validator.class).getResultList();
            transaction.commit();
            return validators;
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
            Query<Long> query = session.createQuery("SELECT COUNT(v) FROM Validator v", Long.class);
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
    public Validator findByUsername(String username) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<Validator> query = session.createQuery("from Validator v where v.username = :username", Validator.class);
            query.setParameter("username", username);
            Validator validator = query.uniqueResult();
            transaction.commit();
            return validator;
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

package org.mpp2024.Repository.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mpp2024.Domain.Admin;
import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;


public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactory(Admin.class, Bug.class, Programmer.class, Validator.class);
        return sessionFactory;
    }

    private static SessionFactory createNewSessionFactory(Class<?>... annotatedClasses){
        Configuration configuration = new Configuration();
        for (Class<?> annotatedClass : annotatedClasses) {
            configuration.addAnnotatedClass(annotatedClass);
        }
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public static void closeSessionFactory(){
        if (sessionFactory!=null)
            sessionFactory.close();
    }
}

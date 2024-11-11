package com.adoptmate.tools;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * 
     * @return Una fábrica de sessiones de Hibernate, de la que podremos abrir una sesión
     * @throws HibernateException 
     */
    private static SessionFactory buildSessionFactory() throws HibernateException {
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Cerramos la fábrica de sesiones
     */
    public static void shutDown(){
        sessionFactory.close();
    }

}
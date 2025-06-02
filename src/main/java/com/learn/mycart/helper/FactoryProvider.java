package com.learn.mycart.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
    private static SessionFactory factory;

    // Synchronized to ensure thread-safety
    public static synchronized SessionFactory getFactory() {
        try {
            if (factory == null) {
                factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed." + e);
        }
        return factory;
    }

    // Close the factory when the application is stopped (useful if manually handling shutdown)
    public static void closeFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}

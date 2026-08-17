package com.makeinfo_web.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Leandro Gregory
 */
public class MakeInfoUtil {

    private static final String PERSISTENCE_UNIT = "MakeInfo-PU";


    private static EntityManager manager;
    private static EntityManagerFactory factory;

    /* Método que faz a conexão com o banco de dados */
    public static EntityManager conectar() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }

        if (manager == null || !manager.isOpen()) {
            manager = factory.createEntityManager();
        }

        return manager;
    }

    /* Método que faz a desconexão com o banco de dados */
    public static void desconectar() {
        if (manager != null && manager.isOpen()) {
            manager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }

}

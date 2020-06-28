package br.com.adriano.desafio.core.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author imssbora
 */
public class HibernateUtil {

    //Hibernate config
    private static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    private static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    private static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    private static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_HBM_2_DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String TRUE = "true";
    private static final String VALIDATE = "validate";
    private static final String POSTGRES_URL_JDBC = "jdbc:postgresql://" +
            getEnv("POSTGRES_DATABASE", "localhost") + ":5432/" +
            getEnv("POSTGRES_DB", "db");
    private static final String POSTGRES_USER = getEnv("POSTGRES_USER", "postgres");
    private static final String POSTGRES_PASSWORD = getEnv("POSTGRES_PASSWORD", "123456");
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(Class<?>... clss) {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put(HIBERNATE_CONNECTION_DRIVER_CLASS, POSTGRESQL_DRIVER);
                settings.put(HIBERNATE_CONNECTION_URL, POSTGRES_URL_JDBC);
                settings.put(HIBERNATE_CONNECTION_USERNAME, POSTGRES_USER);
                settings.put(HIBERNATE_CONNECTION_PASSWORD, POSTGRES_PASSWORD);
                settings.put(HIBERNATE_SHOW_SQL, TRUE);
                settings.put(HIBERNATE_HBM_2_DDL_AUTO, VALIDATE);

                registryBuilder.applySettings(settings);

                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                for (Class<?> cls : clss) {
                    sources.addAnnotatedClass(cls);
                }
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    private static String getEnv(String key, String defaultValue) {
        final String result = System.getenv(key);
        return result != null ? result : defaultValue;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

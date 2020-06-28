package br.com.adriano.desafio.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    //Properties
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String POSTGRES_SQL_DRIVER = "org.postgresql.Driver";
    private static final String SPRING_DATASOURCE_URL_PROP = "spring.datasource.url";
    private static final String SPRING_DATASOURCE_USERNAME_PROP = "spring.datasource.username";
    private static final String SPRING_DATASOURCE_PASSWORD_PROP = "spring.datasource.password";


    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(Class<?>... clss) {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                final Properties properties = ReadPropertiesFileUtil.readPropertiesFile(APPLICATION_PROPERTIES);
                settings.put(HIBERNATE_CONNECTION_DRIVER_CLASS, POSTGRES_SQL_DRIVER);
                settings.put(HIBERNATE_CONNECTION_URL, properties.getProperty(SPRING_DATASOURCE_URL_PROP));
                settings.put(HIBERNATE_CONNECTION_USERNAME, properties.getProperty(SPRING_DATASOURCE_USERNAME_PROP));
                settings.put(HIBERNATE_CONNECTION_PASSWORD, properties.getProperty(SPRING_DATASOURCE_PASSWORD_PROP));
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

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

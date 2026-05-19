package com.example.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAUtil {
    private static EntityManagerFactory emf;

    public static synchronized EntityManagerFactory getFactory(Env env) {
        if (emf == null) {
            Map<String, String> props = new HashMap<>();
            String url = env.get("JDBC_URL", "jdbc:mysql://localhost:3306/crud_db?useSSL=false&serverTimezone=UTC");
            String user = env.get("JDBC_USER", "root");
            String pass = env.get("JDBC_PASS", "");
            String driver = env.get("JDBC_DRIVER", "com.mysql.cj.jdbc.Driver");
            String dialect = env.get("HIBERNATE_DIALECT", "org.hibernate.dialect.MySQL8Dialect");
            String ddl = env.get("HIBERNATE_HBM2DDL", "update");
            String showSql = env.get("HIBERNATE_SHOW_SQL", "false");

            props.put("javax.persistence.jdbc.url", url);
            props.put("javax.persistence.jdbc.user", user);
            props.put("javax.persistence.jdbc.password", pass);
            props.put("javax.persistence.jdbc.driver", driver);
            props.put("hibernate.dialect", dialect);
            props.put("hibernate.hbm2ddl.auto", ddl);
            props.put("hibernate.show_sql", showSql);

            emf = Persistence.createEntityManagerFactory("default", props);
        }
        return emf;
    }
}

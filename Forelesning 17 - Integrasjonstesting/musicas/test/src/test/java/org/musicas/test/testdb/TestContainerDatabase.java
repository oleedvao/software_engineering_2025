package org.musicas.test.testdb;

import org.testcontainers.containers.MySQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;

/*
Denne TestDatabase-implementasjonen er basert på Testcontainers, og vil opprette en ekte MySQL-database i en
Docker Container.
 */
public class TestContainerDatabase extends TestDatabase {

    public final static String DB_NAME = "testdb";
    public final static String USERNAME = "user";
    public final static String PASSWORD = "password";

    /*
    Konfigurerer Docker Containeren
     */
    private final static MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName(DB_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD);

    @Override
    public Connection startDB() throws Exception {
        /*
        Starter containeren.
        Merk at dette krever at Docker Desktop er installert på systemet.
         */
        container.start();
        connection = DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );
        return connection;
    }

    @Override
    public void stopDB() throws Exception {
        connection.close();
        container.stop();
    }
}

package org.musicas.test.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

/*
En in-memory H2-implementasjon som håndterer koblingen til en database av denne typen.
 */
public class H2TestDatabase extends TestDatabase{

    public final static String DB_NAME = "testdb";
    /*
    "jdbc:h2:mem:.. betyr at databasen skal være en in-memory h2 database
    ...MODE=MySQL;DATABASE_TO_LOWER=TRUE; betyr at h2-databasen skal etterligne MySQL
     */
    public final static String URL = "jdbc:h2:mem:" + DB_NAME + ";MODE=MySQL;DATABASE_TO_LOWER=TRUE;";
    public final static String USERNAME = "user";
    public final static String PASSWORD = "password";

    public Connection startDB() throws Exception{
        Class.forName("org.h2.Driver"); // Spesifiserer at h2-driver skal benyttes.
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public void stopDB() throws Exception{
        connection.close();
    }
}

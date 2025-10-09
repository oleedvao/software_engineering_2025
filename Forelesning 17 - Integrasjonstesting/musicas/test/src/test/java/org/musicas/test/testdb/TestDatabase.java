package org.musicas.test.testdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
Representerer en generell Test Database. Definerer de grunnleggende egenskaper som alle test-databasene skal ha,
men krever implementasjon av slikt som startDB() og stopDB(), ettersom disse vil variere basert på konkret
databaseløsning (f.eks. H2-database eller ekte database gjennom Testcontainers.)
 */
public abstract class TestDatabase {

    protected Connection connection;

    public abstract Connection startDB() throws Exception;

    public abstract void stopDB() throws Exception;

    public void createTables() throws Exception{
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE artists (id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255))");

            statement.execute("CREATE TABLE songs (id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255)," +
                    "artistId INT," +
                    "lengthInSeconds INT," +
                    "albumId INT)");
        }
    }

    public void createDummyData() throws Exception{
        try (Statement statement = connection.createStatement()) {
            insertIntoArtists("Radiohead");

            insertIntoSongs("Reckoner", 1, 250, 5);
            insertIntoSongs("Weird Fishes", 1, 300, 5);
            insertIntoSongs("Faust Arp", 1, 150, 5);
        }
    }

    public void insertIntoSongs(String title, int artistId, int lengthInSeconds, int albumId)
            throws Exception{
        String sql = "INSERT INTO songs (title, artistId, lengthInSeconds, albumId) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, artistId);
            preparedStatement.setInt(3, lengthInSeconds);
            preparedStatement.setInt(4, albumId);
            preparedStatement.executeUpdate();
        }
    }

    public void insertIntoArtists(String name) throws Exception{
        String sql = "INSERT INTO artists (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public int countRowsInTable(String tableName) throws Exception{
        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public String getArtistName(int artistId) throws Exception{
        String sql = "SELECT name FROM artists WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        }
    }
}

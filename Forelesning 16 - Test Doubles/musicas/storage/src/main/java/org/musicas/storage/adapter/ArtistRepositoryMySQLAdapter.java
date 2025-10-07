package org.musicas.storage.adapter;

import org.musicas.core.domain.Artist;
import org.musicas.core.domain.Song;
import org.musicas.core.exception.ArtistRepositoryException;
import org.musicas.core.port.ArtistRepositoryPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistRepositoryMySQLAdapter implements ArtistRepositoryPort {

    private Connection connection;

    public ArtistRepositoryMySQLAdapter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createArtist(Artist artist) throws ArtistRepositoryException {
        String sql = "INSERT INTO artists (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, artist.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ArtistRepositoryException("Could not create artist in database", e);
        }
    }

    @Override
    public ArrayList<Song> getArtistSongs(int artistId) throws ArtistRepositoryException{
        String sql = "SELECT id, title, lengthInSeconds, albumId " +
                "FROM songs " +
                "WHERE artistId=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, artistId);

            ArrayList<Song> artistSongs = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int songId = resultSet.getInt("id");
                String songTitle = resultSet.getString("title");
                int lengthInSeconds = resultSet.getInt("lengthInSeconds");
                int albumId = resultSet.getInt("albumId");

                Song song = new Song(songId, songTitle, lengthInSeconds, artistId, albumId);
                artistSongs.add(song);
            }

            return artistSongs;

        } catch (SQLException e) {
            throw new ArtistRepositoryException("Could not retrieve songs for artist with id " + artistId, e);
        }
    }
}

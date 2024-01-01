package com.example.musicplayerfx.repositories;

import com.example.musicplayerfx.enums.FileFormat;
import com.example.musicplayerfx.enums.Genre;
import com.example.musicplayerfx.enums.Mood;
import com.example.musicplayerfx.infrastructures.CrudRepository;
import com.example.musicplayerfx.infrastructures.DbHelper;
import com.example.musicplayerfx.models.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements CrudRepository<Song, Integer> {
    @Override
    public boolean add(Song entity) {
        String query = """
                INSERT INTO dbo.Song(Title,Artist,DurationInSeconds,ReleaseDate,
                Language,Producer,Songwriter,Feature,Genre,Mood,FileFormat) 
                VALUES (?,?,?,?,?,?,?,?,?,?,?);
                """;
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getArtist());
            statement.setInt(3, entity.getDurationInSeconds());
            statement.setDate(4, Date.valueOf(entity.getReleaseDate()));
            statement.setString(5, entity.getLanguage());
            statement.setString(6, entity.getProducer());
            statement.setString(7, entity.getSongwriter());
            statement.setString(8, entity.getFeature());
            statement.setString(9, String.valueOf(entity.getGenre()));
            statement.setString(10, String.valueOf(entity.getMood()));
            statement.setString(11, String.valueOf(entity.getFileFormat()));

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean modify(Integer id, Song entity) {
        String query = """
                UPDATE dbo.Song
                SET Title = ?,
                    Artist = ?,
                    DurationInSeconds = ?,
                    ReleaseDate = ?,
                    Language = ?,
                    Producer = ?,
                    Songwriter = ?,
                    Feature = ?,
                    Genre = ?,
                    Mood = ?,
                    FileFormat = ?
                WHERE Id = ?
                """;
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getArtist());
            statement.setInt(3, entity.getDurationInSeconds());
            statement.setDate(4, Date.valueOf(entity.getReleaseDate()));
            statement.setString(5, entity.getLanguage());
            statement.setString(6, entity.getProducer());
            statement.setString(7, entity.getSongwriter());
            statement.setString(8, entity.getFeature());
            statement.setString(9, String.valueOf(entity.getGenre()));
            statement.setString(10, String.valueOf(entity.getMood()));
            statement.setString(11, String.valueOf(entity.getFileFormat()));
            statement.setInt(12, id);

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Integer id) {
        String deleteLyricsQuery = "DELETE FROM dbo.Lyrics WHERE SongId = ?";
        String deleteSongQuery = "DELETE FROM dbo.Song WHERE Id = ?";

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement deleteLyricsStatement = connection.prepareStatement(deleteLyricsQuery);
             PreparedStatement deleteSongStatement = connection.prepareStatement(deleteSongQuery);) {


            deleteLyricsStatement.setInt(1, id);
            deleteLyricsStatement.executeUpdate();


            deleteSongStatement.setInt(1, id);
            int rowsDeleted = deleteSongStatement.executeUpdate();


            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Song> findAll() {
        String query = "SELECT * FROM dbo.Song";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            List<Song> songs = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                songs.add(toObject(resultSet));
            }
            return songs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song findById(Integer id) {
        String query = "SELECT * FROM dbo.Song WHERE Id = ?";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            Song song = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                song = toObject(resultSet);
            }
            return song;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song toObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String title = resultSet.getString("Title");
        String artist = resultSet.getString("Artist");
        int durationInSeconds = resultSet.getInt("DurationInSeconds");
        LocalDate releaseDate = resultSet.getDate("ReleaseDate").toLocalDate();
        String language = resultSet.getString("Language");
        String producer = resultSet.getString("Producer");
        String songwriter = resultSet.getString("Songwriter");
        String feature = null;
        if (resultSet.getString("Feature") != null) {
            feature = resultSet.getString("Feature");
        }
//        Genre genre = Enum.valueOf(Genre.class, resultSet.getString("Genre"));
//        Mood mood = Enum.valueOf(Mood.class, resultSet.getString("Mood"));
//        FileFormat fileFormat = Enum.valueOf(FileFormat.class, resultSet.getString("FileFormat"));
        String genreString = resultSet.getString("Genre");
        Genre genre = Genre.valueOf(genreString);

        String moodString = resultSet.getString("Mood");
        Mood mood = Mood.valueOf(moodString);

        String fileFormatString = resultSet.getString("FileFormat");
        FileFormat fileFormat = FileFormat.valueOf(fileFormatString);

        return new Song(id, title, artist, durationInSeconds, releaseDate, language,
                producer, songwriter, feature, genre, mood, fileFormat);
    }
}


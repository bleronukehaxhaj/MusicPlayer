package com.example.musicplayerfx.repositories;

import com.example.musicplayerfx.infrastructures.DbHelper;
import com.example.musicplayerfx.infrastructures.JoinRepository;
import com.example.musicplayerfx.models.JoinedData;
import com.example.musicplayerfx.models.Lyrics;
import com.example.musicplayerfx.models.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository implements JoinRepository<JoinedData> {

    @Override
    public List<JoinedData> innerJoinWithWhere(int id) {
        String query = "SELECT Lyrics.Content, Song.Title FROM Lyrics INNER JOIN Song ON Lyrics.SongId = Song.Id WHERE Lyrics.SongId = ?";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            List<JoinedData> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String songTitle = resultSet.getString("Title");
                String lyricsContent = resultSet.getString("Content");

                JoinedData joinedData = new JoinedData(songTitle, lyricsContent);
                result.add(joinedData);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


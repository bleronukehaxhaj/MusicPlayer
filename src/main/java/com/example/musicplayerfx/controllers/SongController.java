package com.example.musicplayerfx.controllers;

import com.example.musicplayerfx.enums.FileFormat;
import com.example.musicplayerfx.enums.Genre;
import com.example.musicplayerfx.enums.Mood;
import com.example.musicplayerfx.infrastructures.JoinRepository;
import com.example.musicplayerfx.models.JoinedData;
import com.example.musicplayerfx.models.Song;
import com.example.musicplayerfx.repositories.ReportRepository;
import com.example.musicplayerfx.repositories.SongRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.*;

public class SongController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField durationField;
    @FXML
    private DatePicker releaseDateField;
    @FXML
    private TextField languageField;
    @FXML
    private TextField producerField;
    @FXML
    private TextField songwriterField;
    @FXML
    private TextField featureField;

    @FXML
    private ComboBox<Genre> genreComboBox;
    @FXML
    private ComboBox<Mood> moodComboBox;
    @FXML
    private ComboBox<FileFormat> fileFormatComboBox;
    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private ListView<Song> listSongs;
    @FXML
    private TableView<Song> tableSongs;

    private List<Song> songList = null;
    private ObservableList<Song> observableSong = null;
    private SongRepository repository = null;
    private Song selectedSong = null;


    public void initialize() {

        genreComboBox.setItems(FXCollections.observableArrayList(Genre.values()));
        moodComboBox.setItems(FXCollections.observableArrayList(Mood.values()));
        fileFormatComboBox.setItems(FXCollections.observableArrayList(FileFormat.values()));

        genreComboBox.setValue(Genre.POP);
        moodComboBox.setValue(Mood.HAPPY);
        fileFormatComboBox.setValue(FileFormat.MP3);

        releaseDateField.setValue(LocalDate.now());

        repository = new SongRepository();
        observableSong = FXCollections.observableArrayList();
        initializeTableView();
        onRefresh();
    }

    public void initializeTableView() {
        TableColumn<Song, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Song, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Song, String> colArtist = new TableColumn<>("Artist");
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<Song, Integer> colDurationInSeconds = new TableColumn<>("DurationInSeconds");
        colDurationInSeconds.setCellValueFactory(new PropertyValueFactory<>("durationInSeconds"));

        TableColumn<Song, LocalDate> colReleaseDate = new TableColumn<>("ReleaseDate");
        colReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Song, String> colLanguage = new TableColumn<>("Language");
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));

        TableColumn<Song, String> colProducer = new TableColumn<>("Producer");
        colProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));

        TableColumn<Song, String> colSongwriter = new TableColumn<>("Songwriter");
        colSongwriter.setCellValueFactory(new PropertyValueFactory<>("songwriter"));

        TableColumn<Song, String> colFeature = new TableColumn<>("Feature");
        colFeature.setCellValueFactory(new PropertyValueFactory<>("feature"));

        TableColumn<Song, String> colGenre = new TableColumn<>("Genre");
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Song, String> colMood = new TableColumn<>("Mood");
        colMood.setCellValueFactory(new PropertyValueFactory<>("mood"));

        TableColumn<Song, String> colFileFormat = new TableColumn<>("FileFormat");
        colFileFormat.setCellValueFactory(new PropertyValueFactory<>("fileFormat"));


        tableSongs.getColumns().addAll(colId, colTitle, colArtist, colDurationInSeconds,
                colReleaseDate, colLanguage, colProducer, colSongwriter, colFeature, colGenre, colMood, colFileFormat);

        tableSongs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    Song song = tableSongs.getSelectionModel().getSelectedItem();
                    if (song != null) {
                        titleField.setText(song.getTitle());
                        artistField.setText(song.getArtist());
                        durationField.setText(String.valueOf(song.getDurationInSeconds()));
                        releaseDateField.setValue(song.getReleaseDate());
                        languageField.setText(song.getLanguage());
                        producerField.setText(song.getProducer());
                        songwriterField.setText(song.getSongwriter());
                        featureField.setText(song.getFeature());
                        genreComboBox.setValue(song.getGenre());
                        moodComboBox.setValue(song.getMood());
                        fileFormatComboBox.setValue(song.getFileFormat());

                        selectedSong = song;
                    }
                }
            }
        });


        listSongs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    Song song = listSongs.getSelectionModel().getSelectedItem();
                    if (song != null) {
                        titleField.setText(song.getTitle());
                        artistField.setText(song.getArtist());
                        durationField.setText(String.valueOf(song.getDurationInSeconds()));
                        releaseDateField.setValue(song.getReleaseDate());
                        languageField.setText(song.getLanguage());
                        producerField.setText(song.getProducer());
                        songwriterField.setText(song.getSongwriter());
                        featureField.setText(song.getFeature());
                        genreComboBox.setValue(song.getGenre());
                        moodComboBox.setValue(song.getMood());
                        fileFormatComboBox.setValue(song.getFileFormat());

                        selectedSong = song;
                    }
                }
            }
        });

    }

    public void onRefresh() {
        songList = repository.findAll();
        observableSong.clear();
        observableSong.addAll(songList);
        listSongs.setItems(observableSong);
        tableSongs.setItems(observableSong);

    }

    public void onSubmit() {

//        Logger logger = Logger.getLogger(SongController.class.getName());
//
//        try {
////            FileHandler fileHandler = new FileHandler("C:\\Users\\Lenovo\\Desktop\\MusicPlayerFx\\src\\main\\resources\\com\\example\\musicplayerfx\\log\\log.txt", true);
//
//            fileHandler.setFormatter(new Formatter() {
//                @Override
//                public String format(LogRecord record) {
//                    return String.format("[%s] %s (%s.%s)%n",
//                            record.getLevel().getName(),
//                            formatMessage(record),
//                            record.getSourceClassName(),
//                            record.getSourceMethodName());
//                }
//            });
//            // Add the file handler to the logger
//            logger.addHandler(fileHandler);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }

//        logger.log(Level.INFO, "onSubmit() method started");

        String title = titleField.getText();
        String artist = artistField.getText();
        String durationText = durationField.getText();
        LocalDate releaseDate = releaseDateField.getValue();
        String language = languageField.getText();
        String producer = producerField.getText();
        String songwriter = songwriterField.getText();
        String feature = featureField.getText();
        Genre genre = genreComboBox.getValue();
        Mood mood = moodComboBox.getValue();
        FileFormat fileFormat = fileFormatComboBox.getValue();

        if (!validateFields()) {
//            logger.log(Level.WARNING, "Input field validation failed");
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationText);
        } catch (NumberFormatException e) {
//            logger.log(Level.WARNING, "Invalid duration format");

            showAlert(Alert.AlertType.ERROR, "Invalid input", null,
                    "Please enter a valid integer for duration");
            return;
        }

        Song song = new Song(title, artist, duration, releaseDate, language, producer,
                songwriter, feature, genre, mood, fileFormat);

        if (selectedSong == null)
            repository.add(song);
        else
            repository.modify(selectedSong.getId(), song);

        onRefresh();
        reset();

//        logger.log(Level.INFO, "onSubmit() method finished");
//
//        logger.getHandlers()[0].close();

    }

    private boolean validateFields() {
        String title = titleField.getText();
        String artist = artistField.getText();
        String durationText = durationField.getText();
        LocalDate releaseDate = releaseDateField.getValue();
        String language = languageField.getText();
        String producer = producerField.getText();
        String songwriter = songwriterField.getText();
        Genre genre = genreComboBox.getValue();
        Mood mood = moodComboBox.getValue();
        FileFormat fileFormat = fileFormatComboBox.getValue();

        if (title.isEmpty() || artist.isEmpty() || durationText.isEmpty() || releaseDate == null ||
                language.isEmpty() || producer.isEmpty() || songwriter.isEmpty() ||
                genre == null || mood == null || fileFormat == null) {

            showAlert(Alert.AlertType.ERROR, "Invalid input", null,
                    "Please fill in all the required fields");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void reset() {
        titleField.clear();
        artistField.setText("");
        durationField.setText("");
        releaseDateField.setValue(LocalDate.now());
        languageField.setText("");
        producerField.setText("");
        songwriterField.setText("");
        featureField.setText("");
        genreComboBox.setValue(Genre.POP);
        moodComboBox.setValue(Mood.HAPPY);
        fileFormatComboBox.setValue(FileFormat.MP3);
        selectedSong = null;
    }

    public void delete() {
        if (selectedSong != null) {
            repository.remove(selectedSong.getId());
            onRefresh();
            reset();
        }
    }

    @FXML
    public void exitMenuItemClicked() {
        Stage stage = (Stage) exitMenuItem.getParentPopup().getOwnerWindow();
        stage.close();
    }

    @FXML
    public void showAboutUs(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "About Us", "Welcome to Our Music Player Application",
                "Erolinda Kajtazi: Team Leader\n"
                        + "Bleron Ukehaxhaj: Java Developer\n"
                        + "Gazmend Rashiti: Java Fx Designer\n"
                        + "Dion Abdullahu: Front End Developer\n"
                        + "Petrit Reshani: Front End Developer\n"
                        + "Leonart Asllani: Tech Support\n\n"
                        + "Version: 1.0");
    }


    private void showReport(Song selectedSong) {
        ReportRepository joinRepository = new ReportRepository();

        List<JoinedData> joinedDataList = joinRepository.innerJoinWithWhere(selectedSong.getId());
        if (!joinedDataList.isEmpty()) {
            JoinedData joinedData = joinedDataList.get(0);

            String songTitle = (String) joinedData.getData1();
            String lyricsContent = (String) joinedData.getData2();

            showAlert(Alert.AlertType.INFORMATION, "Report", songTitle, lyricsContent);
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Report", null,
                    "No report available for this song.");
        }
    }

    @FXML
    private void handleReportButtonAction(ActionEvent event) {
        if (selectedSong != null) {
            showReport(selectedSong);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "No song selected",
                    "Please select a song before generating the report.");
        }
    }


}

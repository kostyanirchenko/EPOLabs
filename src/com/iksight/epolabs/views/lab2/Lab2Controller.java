package com.iksight.epolabs.views.lab2;

import com.iksight.epolabs.Main;
import com.iksight.epolabs.views.lab1.FileWorker;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Adler32;

/**
 * Created by NKostya on 11.04.2017.
 */
public class Lab2Controller {

    public static final String FILENAME = "CHECKSUM.txt";

    String destChecksum;

    private Stage stage;

    @FXML
    private Label oldCheckSum;

    @FXML
    private Label newCheckSum;

    private File file;

    private File checksumFile;

    private byte[] array;

    private Adler32 adler32;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    @FXML
    private void initialize() {

        checksumFile = new File(FILENAME);

        adler32 = new Adler32();

    }

    public void run() throws IOException {

        if (!checksumFile.exists()) {
            array = FileWorker.getBytesFromFile(file);
            adler32.update(array);
            FileWorker.write(checksumFile, adler32.getValue());
            destChecksum = String.valueOf(adler32.getValue());
            oldCheckSum.setText(destChecksum);
        } else {

            oldCheckSum.setText(FileWorker.read(checksumFile.getPath()));
            array = FileWorker.getBytesFromFile(file);
            adler32.update(array);
            newCheckSum.setText(String.valueOf(adler32.getValue()));

            if (!FileWorker.read(checksumFile.getPath()).trim().equals(String.valueOf(adler32.getValue()))) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Чексуммы разные");
                alert.setHeaderText("Запрос на перезапись");
                alert.setContentText("Вы действительно хотите перезаписать чексумму?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    FileWorker.write(checksumFile, adler32.getValue());
                    oldCheckSum.setText(FileWorker.read(checksumFile.getPath()));
                }
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

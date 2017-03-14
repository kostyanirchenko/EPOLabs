package com.iksight.epolabs.views.lab1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * Created by DMITRY on 07.03.2017.
 */
public class Lab1Controller {

    private long[] measures = new long[4];

    @FXML
    public Label oldWTimeLabel;

    @FXML
    public Label newWTimeLabel;

    @FXML
    public Label rTimeLabel;

    @FXML
    public Label percentWTimeLabel;

    public void initialize() {
        FileWorker.createFile("withoutBuff.txt");

        measure(1);

        for (int i = 0; i < 12800; i++) {
            try {
                FileWorker.update(FileWorker.getFile(), i);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
        }

        measure(2);

        FileWorker.createFile("withBuff.txt");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 12800; i++) {
            builder.append(i).append(" ");
        }

        try {
            FileWorker.write(FileWorker.getFile(), builder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        measure(3);

        FileWorker.read(FileWorker.getFilename());

        measure(4);

        showTimes();
    }

    private void measure(int i) {
        measures[i-1] = System.currentTimeMillis();
    }

    private void showTimes() {
        float oldWTime = measures[1] - measures[0];
        float newWTime = measures[2] - measures[1];
        float rTime = measures[3] - measures[2];

        float oldWTimePercent = (oldWTime * 100)/ (oldWTime + newWTime);
        float newWTimePercent = (newWTime * 100) / (oldWTime + newWTime);

        oldWTimeLabel.setText("Duration of write method without using buffer: " + customFormat(oldWTime));
        newWTimeLabel.setText("Duration of write method using buffer: " + customFormat(newWTime));
        rTimeLabel.setText("Duration of read method: " + customFormat(rTime));
        percentWTimeLabel.setText("Percentage (write without buff/write with buff): " +
                customFormat(oldWTimePercent) + "% /" + customFormat(newWTimePercent) + "%");
    }

   private String customFormat(float value) {
        DecimalFormat myFormatter = new DecimalFormat("0.###");
        return myFormatter.format(value);
    }
}

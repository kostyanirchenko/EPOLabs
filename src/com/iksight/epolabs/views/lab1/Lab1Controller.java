package com.iksight.epolabs.views.lab1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by DMITRY on 07.03.2017.
 */
public class Lab1Controller {

    public static long[] measures = new long[3];

    @FXML
    private Label oldWTimeLabel;

    @FXML
    private Label newWTimeLabel;

    @FXML
    private Label rTimeLabel;

    @FXML
    private Label percentWTimeLabel;

    public Lab1Controller() {

    }
}

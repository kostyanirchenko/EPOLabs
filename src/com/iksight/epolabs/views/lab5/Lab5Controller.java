package com.iksight.epolabs.views.lab5;


import com.iksight.epolabs.views.lab1.FileWorker;
import javafx.scene.control.Label;

import java.io.File;

/**
 * Created by NKOSTYA on 05.06.2017.
 */

public class Lab5Controller {

    public Label chepinLabel;
    private File file;

    private String strFile;

    private int p = 0;
    private int m = 0;
    private int c = 0;
    private int t = 0;

    public void run() {
        strFile = FileWorker.read(file.getPath());

        String tmp = "";


        int index = strFile.indexOf("{");
        index++;

        int i = 0;

        while (index < strFile.length()) {
            /*if (strFile.charAt(index) == '\"' || strFile.charAt(index) == '@' || strFile.charAt(index) == '/') {
                int j = index + 2;
                while (j < strFile.length()) {
                    if (strFile.charAt(j) == '\"' || strFile.charAt(j) == '/') {
                        index = j + 1;
                        break;
                    }
                    j++;
                }
            }*/
            try {
                if (strFile.substring(index).contains("for")) {
                    p++;
                }
            } catch (StringIndexOutOfBoundsException e) {

            }


            System.out.print(strFile.charAt(index));
            index++;
        }
        System.out.println();
        setResult(p, m, c, t);
    }

    private void setResult(int p, int m, int c, int t) {
        chepinLabel.setText("" + p + 2 * m + 3 * c + 0.5 * t);
        System.out.println(p);
        System.out.println(m);
    }

    public void setFile(File file) {
        this.file = file;
    }
}

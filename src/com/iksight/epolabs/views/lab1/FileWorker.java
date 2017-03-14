package com.iksight.epolabs.views.lab1;

import java.io.*;

/**
 * Класс для работы с текстовыми файлами. Имеет возможность читать указаный файл, проверять на существование
 * указаного пользователем файла, записывать данные в файл и перезаписывать файл при необходимости.
 * @author Kostya Nirchenko
 */
public class FileWorker {

    public static File file;
    public static String filename;

    /**
     * Чтение указаного пользователем файла. Если файла не существует - создается новый файл, и отправляется
     * на запись. Иначе - выводит содержимое файла на экран.
     *
     * @param filename - имя файла
     * @return String
     */
    public static String read(String filename) {
        file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();
        if (!exists(file)) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));
                file.createNewFile();
                System.out.println("Введите текст для записи");
                String text = in.readLine();
                write(file, text);
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append(" ");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Запись указаного пользователем файла. Вызывается в случае, если указаного файла не было найдено.
     * Смотри {@link #read(java.lang.String)}
     *
     * @param file - файл для записи
     * @param text - текст для записи
     * @throws FileNotFoundException
     */
    public static void write(File file, String text) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        try {
            pw.write(text);
            read(filename);
        } catch (Exception ex) {

        } finally {
            pw.close();
        }
    }

    public static void write(File file, int i) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        try {
            pw.write(i);
            read(filename);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * Обновление указаного пользователем файла. Считывает файл в StringBuilder и отправляет на перезапись.
     * Смотри {@link #write(java.io.File, java.lang.String) }
     *
     * @param file - файл, который необходимо обновить
     * @throws FileNotFoundException
     */
    public static void update(File file) throws FileNotFoundException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));
//            System.out.println("Введите текст, который вы хотите добавить в файл");
            StringBuilder sb = new StringBuilder();
            String oldText = fileReader.readLine();
            String tmp = reader.readLine();
            String newText = sb.append(oldText).append(" ").append(tmp).toString();
            write(file, newText);
        } catch (IOException e) {

        }
    }

    public static void update(File file, int i) throws FileNotFoundException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));
//            System.out.println("Введите текст, который вы хотите добавить в файл");
            StringBuilder sb = new StringBuilder();
            String oldText = fileReader.readLine();
            String tmp = Integer.toString(i);
            String newText = sb.append(oldText).append(" ").append(tmp).toString();
            write(file, newText);
        } catch (IOException e) {

        }
    }

    /**
     * Проверяет, существует ли указаный пользователем файл.
     *
     * @param file - указаный пользователем файл.
     * @return boolean
     */
    public static boolean exists(File file) {
        return file.exists();
    }

    public static void createFile(String fname) {
        filename = fname;
        file = new File(fname);
    }

    public static File getFile() {
        return file;
    }

    public static String getFilename() {
        return filename;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Укажите имя файла.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        filename = reader.readLine();
        File file = new File(filename);
        System.out.println(read(filename));
        System.out.println("Вы хотите обновить файл? (y/n)");
        if (reader.readLine().equals("y")) {
            update(file);
        } else {
            return;
        }
    }
}

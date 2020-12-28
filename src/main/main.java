package main;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    static String pathOfPathOfNum = "C:\\Users\\Neo\\IdeaProjects\\nhentaiParser\\src\\main\\lastMangaNumber.txt";
    static String pathOfPath = "C:\\Users\\Neo\\IdeaProjects\\nhentaiParser\\src\\main\\pathConfig.txt";

    public static void main(String[] args) throws IOException {
        String path = getPath();
        int lastMangaNum = getLastNumberFromFile();

        while (true) {
                WebParser.parse(lastMangaNum, path, pathOfPathOfNum);
                lastMangaNum++;
            }
    }
    public static int getLastNumberFromFile() throws IOException {
        Path fileName = Path.of(pathOfPathOfNum);
        return Integer.parseInt(Files.readString(fileName));
    }
    public static String getPath() throws IOException {
        Path fileName = Path.of(pathOfPath);
        return Files.readString(fileName);
    }
}

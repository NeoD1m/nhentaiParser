package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    static String pathLastMangaNumber = "src/main/lastMangaNumber.txt";
    static String pathDownloadFolder = "src/main/pathConfig.txt";

    public static void main(String[] args) throws IOException {
        String path = getPath();
        int lastMangaNum = getLastMangaNumberFromFile();
        visual.main(args, path, pathLastMangaNumber);
        /*while (true) {
                WebParser.parse(lastMangaNum, path, pathLastMangaNumber);
                lastMangaNum++;
            }*/

    }
    public static int getLastMangaNumberFromFile() throws IOException {
        Path fileName = Path.of(pathLastMangaNumber);
        return Integer.parseInt(Files.readString(fileName));
    }
    public static String getPath() throws IOException {
        Path fileName = Path.of(pathDownloadFolder);
        return Files.readString(fileName);
    }
}

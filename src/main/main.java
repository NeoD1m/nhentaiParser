package main;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    static String pathOfPathOfNum = "C:\\Users\\Dima\\IdeaProjects\\NhentaiParser\\src\\main\\lastMangaNumber.txt";
    static String pathOfPath = "C:\\Users\\Dima\\IdeaProjects\\NhentaiParser\\src\\main\\pathConfig.txt";

    public static void main(String[] args) throws IOException {
        int lol = 200000;
        String path = getPath();
        int lastMangaNum = getLastNumberFromFile();
        for (int i=lastMangaNum;i<lol;i++)
            WebParser.parse(i,path,pathOfPathOfNum);
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

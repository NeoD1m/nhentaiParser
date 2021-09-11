package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static String pathLastMangaNumber = "src/main/lastMangaNumber.txt";
    static String pathDownloadFolder = "src/main/pathConfig.txt";
    static int mangaNumber = 0;
    static UIContainer ui;

    public static void main(String[] args){
        ui = new UIContainer(640, 500);

        System.out.println("End");
    }

    public static void startDownload() throws InterruptedException {
        DownloadHandler download = new DownloadHandler();
        download.start();
    }

    public static String getLastMangaNumberFromFile() throws IOException {
        Path fileName = Path.of(pathLastMangaNumber);
        return Files.readString(fileName);
    }

    public static String getPath() throws IOException {
        Path fileName = Path.of(pathDownloadFolder);
        return Files.readString(fileName);
    }

    public static UIContainer getUi() {
        return ui;
    }
    public static int getMangaNumber (){
        mangaNumber = Integer.parseInt(ui.getTextField().getText());
        return mangaNumber;
    }
}


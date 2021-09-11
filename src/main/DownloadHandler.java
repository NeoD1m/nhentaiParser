package main;

import java.io.IOException;

public class DownloadHandler extends Thread {
    @Override
    public void run() {
        String path;
        String lastMangaNum;
        try {
            path = Main.getPath();
            lastMangaNum = Main.getLastMangaNumberFromFile();
            WebParser.parse(Main.getMangaNumber(), path, lastMangaNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        if (WebParser.IsDone()) {
            UIContainer.getButton().setEnabled(true);
            UIContainer.getButton().setText("Download");
        }
    }
}

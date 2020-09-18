package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;

public class WebParser {

    public static void parse(int mangaNumber,String path,String pathOfPath) throws IOException {

        setLastNumberFromFile(mangaNumber,pathOfPath);
        Element doc = getPageBody("https://nhentai.net/g/" + mangaNumber + "/"); // Full page URL here

        if (doc != null) {
            Elements mangaName = doc.select("h1.title");
            System.out.print("\n" + "["+mangaNumber+"] "+ mangaName.text() + "\n");
            Elements pageAmountElement = doc.select("span.name");

            int pageAmount = Integer.parseInt(String.valueOf(pageAmountElement.last().text()));
            System.out.println(pageAmount);

            for (int i = 1; i < pageAmount + 1; i++) {
                Element page = getPageBody("https://nhentai.net/g/" + mangaNumber + "/" + i + "/");

                Document doc1 = Jsoup.connect("https://nhentai.net/g/" + mangaNumber + "/" + i + "/").get();
                Elements lol31 = doc1.select("img");
                Elements lol = doc1.getElementsContainingText("https://i.nhentai.net/galleries/");
                if (lol == null) System.out.println("ERROR no picture!!!");

                createFolder(path + "["+ mangaNumber+"]"+  mangaName.text());
                Element lol2 = lol31.last();
                String absoluteUrl = lol2.attr("src");
                System.out.print("=");
                DownloadFile(absoluteUrl, path + "["+mangaNumber+"]" + mangaName.text() + "\\" + i + ".jpg"); // Images to download
            }
        }
    }

    public static void setLastNumberFromFile(int num,String path) throws IOException {
        Path fileName = Path.of(path);
        String content = num+"";
        Files.writeString(fileName,content);
    }
    public static Element getPageBody(String url){
        Element doc = null;
        try {
            doc = Jsoup.connect(url).get().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void DownloadFile(String url, String file_name){
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file_name)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(String name){
        File folder = new File(name);
        if (!folder.exists()) {
            folder.mkdir();

        }
    }
}
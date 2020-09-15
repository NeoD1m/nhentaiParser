package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebParser {

    public static void parse(int mangaNumber) throws IOException {
        Element doc = getPageBody("https://nhentai.net/g/" + mangaNumber + "/");     // Full page URL here

        if( doc != null) {
            Elements mangaName = doc.select("h1.title");
            System.out.print(mangaName.text());
            System.out.print("\n");
            Elements pageAmountElement = doc.select("span.name");

            int pageAmount = Integer.parseInt(String.valueOf(pageAmountElement.last().text()));
            System.out.println(pageAmount);

            for (int i=1;i<pageAmount+1;i++){
                Element page = getPageBody("https://nhentai.net/g/" + mangaNumber + "/" + i + "/");

                Document doc1 = Jsoup.connect("https://nhentai.net/g/" + mangaNumber + "/" + i + "/").get();
                Elements lol31 = doc1.select("img");
                Elements lol = doc1.getElementsContainingText("https://i.nhentai.net/galleries/");
                if (lol == null) System.out.println("ERROR no picture!!!");


                Element lol2 = lol31.last();
                String absoluteUrl = lol2.attr("src");
                System.out.println(absoluteUrl);

                /*
                Elements photos = page.select("img.img-fluid");
                Element neededPhoto = photos.last();
                */

                //neededPhoto
                //System.out.println(neededPhoto.text().toString());
                //DownloadFile(); // Images to download
            }
        }
    }
    /*
                Elements linksToPages = doc.select("a.u-link-v2");
            System.out.println("Found "+linksToPages.size()+" pages\n");

            Elements namesEl = doc.select("h1.g-font-weight-500");
            String[] names = getNames(namesEl);

            int i=0;
            for (Element el: linksToPages) {
                createFolder(names[i]); ++i;

                Element page = getPageBody("https://student.mirea.ru"+el.attr("href")); //Pages to parse

                if (page != null) {
                    Elements photos = page.select("img.img-fluid");
                    System.out.println("Found " + photos.size() + " photos. On page "+names[i-1]);

                    for (Element pic : photos) {
                        if (isAllowed(getName(pic.attr("src"))))
                            DownloadFile("https://student.mirea.ru"+pic.attr("src"),
                                    names[i-1]+"/"+getName(pic.attr("src"))); // Images to download
                        System.out.print("=");
                    }
                    System.out.print("\n");
                }
            }
     */
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

    public static String getName(String raw){
        Pattern p = Pattern.compile("[а-яА-Яa-zA-Z 0-9]+\\.[jpgn]+");
        Matcher matcher = p.matcher(raw);
        if (matcher.find())
            return matcher.group(0);
        else{
            System.out.println("Can't resolve "+raw);
            return "ERROR.jpg";
        }
    }

    public static boolean isAllowed(String name){
        List<String> denied = Arrays.asList("logo.png");
        return !denied.contains(name);
    }

    public static String[] getNames(Elements names){
        String[] result = new String[names.size()];
        int k=0;
        for (Element el:names) {
            result[k]=el.text();
            ++k;
        }
        return result;
    }

    public static void createFolder(String name){
        File folder = new File(name);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
package ru.job4j.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget2 implements Runnable {
    private final String url;
    private final int speed;
    private final String filename;

    public Wget2(String url, int speed, String filename) {
        this.url = url;
        this.speed = speed;
        this.filename = filename;
    }

    @Override
    public void run() {
        var file = new File(filename);
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            var dataBuffer = new byte[512];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long elapsedTime;
            long totalBytesRead = 0;

            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                if (totalBytesRead >= speed) {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    totalBytesRead = 0;
                    startTime = System.currentTimeMillis();
                    if (elapsedTime < 1000) {
                        Thread.sleep(elapsedTime / speed);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Usage: java Wget2 <URL> <speed> <filename>");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String filename = args[2];
        if (isValidURL(url) && speed > 0) {
            Thread wget = new Thread(new Wget2(url, speed, filename));
            wget.start();
            wget.join();
        }
    }
}

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

    public Wget2(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var file = new File("tmp.xml");
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
                elapsedTime = System.currentTimeMillis() - startTime;
                double downloadSpeedKbps = (totalBytesRead * 8.0) / (elapsedTime * speed);
                if (downloadSpeedKbps > speed) {
                    long sleepTime = (long) ((totalBytesRead * 8.0) / (speed * 128)) - elapsedTime;
                    if (sleepTime > 0) {
                        Thread.sleep(sleepTime);
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
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        if (isValidURL(url) && speed > 0) {
            Thread wget = new Thread(new Wget2(url, speed));
            wget.start();
            wget.join();
        }
    }
}

package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class InstallFile {
    public static boolean install(String file, String url) {
        try {
            URL fileURL = new URL(url);
            URLConnection conn = fileURL.openConnection();
            if(conn instanceof HttpURLConnection) {((HttpURLConnection)conn).setRequestMethod("HEAD");}
            conn.getInputStream();
            Console.print(Colors.YELLOW + "Start installation '" + file + "' from '" + url + "'");
            InputStream in = fileURL.openStream();
            Console.print(Colors.YELLOW + "Downloading of " + (conn.getContentLength() /1000) + "MB");
            Files.copy(in, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
            Console.print(Colors.YELLOW + "Installation of '" + file + "' finished");
            return true;
        }catch (IOException e){
            Console.print(Colors.RED_BACKGROUND + "Unable to download these files");
            return false;
        }
    }
}

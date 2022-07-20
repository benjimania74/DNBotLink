package fr.benjimania74.dnapitest.utils;

import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.Main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilesManager {
    private static FilesManager instance;
    public static FilesManager getInstance(){return instance;}

    private String[] configFiles = {"token", "services"};
    protected String jar = FilesManager.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
    public Path addonPath = Paths.get(jar.replace("/" + jar.substring(jar.lastIndexOf("/") + 1), "") + "/" + Main.addonName);

    public FilesManager(){
        if(!Files.exists(addonPath)){ try{ Files.createDirectory(addonPath); }catch (Exception e){ e.printStackTrace(); } }
        for(String f : configFiles){
            File file = new File(addonPath + "/" + f);
            if(!file.exists()){
                try{
                    file.createNewFile();
                    Console.print(Colors.YELLOW + "[" + Colors.GREEN + Main.addonName + Colors.YELLOW + "] " + Colors.CYAN + f + " created");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        Console.print(Colors.YELLOW + "[" + Colors.GREEN + Main.addonName + Colors.YELLOW + "] " + Colors.CYAN + "Plugin's Files are loaded");
        instance = this;
    }

    public String read(String fileName){
        String returnedString = "";
        File file = new File(addonPath + "/" + fileName);
        if(!file.exists()){return returnedString;}
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                return scan.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnedString;
    }
}

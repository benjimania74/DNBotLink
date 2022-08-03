package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnbotlink.Main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateService {
    public static String[] files = {"network.yml", "server.jar", "plugins/DreamNetwork-Plugin.jar", "plugins/" + Main.addonName + "-" + Main.addonVersion + ".jar"};
    public static String create(IContainer.JVMType type, String name, String version, String dynSta, String xms, String xmx){
        // JVMType#toString = PROXY || SERVER
        Path p = Paths.get("./template/" + type.toString().toLowerCase() + "/" + name);

        if(Services.getType(name) == null || Services.getType(name).equals(type)){return StatusMessages.EXIST.replace("SERVICE", name);}

        FilesManager.getInstance().createDirectory(p.toString());
        FilesManager.getInstance().createDirectory("./template" + type.toString().toLowerCase() + "/plugins");
        for(String s : files){FilesManager.getInstance().createFile(p + "/" + s);}
        try {
            FilesManager.getInstance().write(
                    p + "/network.yml",
                    "# " + name + "'s configuration of the startup -|- DreamNetwork™\n" +
                    "type: " + dynSta + "\n" +
                    "xms: " + xmx + "\n" +
                    "xmx: " + xms + "\n" +
                    "exec: server.jar\n" +
                    "proxy: " + (type.equals(IContainer.JVMType.PROXY))
            );

            InstallFile.installDNPlugin(files[2]);

            if(type.equals(IContainer.JVMType.SERVER)){
                FilesManager.getInstance().createFile(p + "/eula.txt");
                FilesManager.getInstance().write(p + "/eula.txt", "eula=true");
                InstallFile.copyLocalFile(
                        Main.class.getProtectionDomain()
                                .getCodeSource()
                                .getLocation()
                                .toURI()
                                .getPath(),
                        p + "/" + Main.addonName + "-" + Main.addonVersion + ".jar"
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }
        if(type.equals(IContainer.JVMType.PROXY)){
            if(!InstallFile.installBungee(p + "/server.jar")){
                FilesManager.getInstance().deleteDirectory(p);
                return "CAN'T DOWNLOAD BUNGEECORD";
            }
            return "proxy created";
        }
        if(type.equals(IContainer.JVMType.SERVER)){
            if(!InstallFile.installPaper(p + "/server.jar", version)){
                FilesManager.getInstance().deleteDirectory(p);
                return "INNEXISTANT VERSION";
            }
            return "server created";
        }
        return "ERROR";
    }
}

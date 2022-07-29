package fr.benjimania74.dnbotlink;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import fr.benjimania74.dnbotlink.utils.CustomResponse;
import fr.benjimania74.dnbotlink.registers.CommandsRegister;
import fr.benjimania74.dnbotlink.registers.ListenerRegister;
import fr.benjimania74.dnbotlink.utils.FilesManager;
import fr.benjimania74.dnbotlink.utils.InstallFile;
import fr.benjimania74.dnbotlink.utils.ServiceAutoStarter;

public class Main extends DreamExtension {
    public static DNClientAPI clientAPI;
    public static String addonName;

    @Override
    public void onLoad() {
        super.onLoad();

        registerPluginToServers(this);

        addonName = getAddon().getDreamyName();
        new FilesManager();
        if(!new BotMain().create()){
            //stop();
        }
        Console.print(Colors.YELLOW + "[" + Colors.GREEN + addonName + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Loaded");
    }

    @Override
    public void start() {
        super.start();
        clientAPI = DNClientAPI.getInstance();
        if(!CommandsRegister.register(clientAPI)){
            Console.print(Colors.RED + "The Plugin is stopping");
            //this.stop();
        }
        if(!ListenerRegister.register(clientAPI)){
            Console.print(Colors.RED + "The Plugin is stopping");
            //this.stop();
        }

        Console.print(Colors.YELLOW + "[" + Colors.GREEN + addonName + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Started");
        new ServiceAutoStarter();

        // InstallFile.install("./paper-1.8.8.jar", "https://api.papermc.io/v2/projects/paper/versions/1.8.8/builds/443/downloads/paper-1.8.8-443.jar"); <- INSTALL PAPER SPIGOT 1.8.8
        clientAPI.getGlobalResponses().add(new CustomResponse());
    }

    @Override
    public void stop() {
        super.stop();
        BotConfig.getInstance().save();
        Console.print(Colors.YELLOW + "[" + Colors.GREEN + addonName + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Stopped");
    }

    public Main(Addon addon){
        super(addon);
    }
}

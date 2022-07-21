package fr.benjimania74.dnapitest;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.bot.BotMain;
import fr.benjimania74.dnapitest.registers.CommandsRegister;
import fr.benjimania74.dnapitest.registers.ListenerRegister;
import fr.benjimania74.dnapitest.utils.FilesManager;

import java.util.ArrayList;
import java.util.List;

public class Main extends DreamExtension {
    public static DNClientAPI clientAPI;
    public static String addonName;
    public static List<IClient> clients;

    @Override
    public void onLoad() {
        super.onLoad();
        addonName = getAddon().getDreamyName();
        clients = new ArrayList<>();
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
    }

    @Override
    public void stop() {
        super.stop();
        Console.print(Colors.YELLOW + "[" + Colors.GREEN + addonName + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Stopped");
    }

    public Main(Addon addon){
        super(addon);
    }
}

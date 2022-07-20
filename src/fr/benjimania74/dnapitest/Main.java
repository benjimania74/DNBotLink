package fr.benjimania74.dnapitest;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.console.colors.Colors;
import fr.benjimania74.dnapitest.registers.CommandsRegister;
import fr.benjimania74.dnapitest.registers.ListenerRegister;

public class Main extends DreamExtension {
    public static DNClientAPI clientAPI;

    @Override
    public void onLoad() {
        super.onLoad();
        Console.print(Colors.YELLOW + "[" + Colors.GREEN + getAddon().getDreamyName() + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Loaded");
    }

    @Override
    public void start() {
        super.start();
        clientAPI = DNClientAPI.getInstance();
        if(!CommandsRegister.register(clientAPI)){
            Console.print(Colors.RED + "The Plugin is stopping");
            this.stop();
        }
        if(!ListenerRegister.register(clientAPI)){
            Console.print(Colors.RED + "The Plugin is stopping");
            this.stop();
        }

        Console.print(Colors.YELLOW + "[" + Colors.GREEN + getAddon().getDreamyName() + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Started");
    }

    @Override
    public void stop() {
        super.stop();
        Console.print(Colors.YELLOW + "[" + Colors.GREEN + getAddon().getDreamyName() + Colors.YELLOW + "] " + Colors.CYAN + "The Plugin is Stopped");
    }

    public Main(Addon addon){
        super(addon);
    }
}

package fr.benjimania74.dnapitest;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import fr.benjimania74.dnapitest.cmd.HelloCmd;
import fr.benjimania74.dnapitest.cmd.StartCmd;

public class Main extends DreamExtension {
    public static DNClientAPI clientAPI;

    @Override
    public void onLoad() {
        super.onLoad();
        System.out.println("[" + getAddon().getDreamyName() + "] The Plugin is Loaded");
    }

    @Override
    public void start() {
        super.start();
        clientAPI = DNClientAPI.getInstance();
        clientAPI.getCommandReader().getCommands().addCommands(new HelloCmd("hello"));
        clientAPI.getCommandReader().getCommands().addCommands(new StartCmd("server"));

        System.out.println("[" + getAddon().getDreamyName() + "] The Plugin is Started");
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("[" + getAddon().getDreamyName() + "] The Plugin is Stopped");
    }

    public Main(Addon addon){
        super(addon);
    }
}

package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;
import fr.benjimania74.dnapitest.Main;

public class ClientsCmd extends Command {
    public ClientsCmd(String name) {
        super(name);

        addSubCommand("see", args -> {
            System.out.println(Main.clientAPI.getClientManager().getClients());
            return true;
        });

    }
}

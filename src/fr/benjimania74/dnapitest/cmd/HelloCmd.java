package fr.benjimania74.dnapitest.cmd;

import be.alexandre01.dreamnetwork.api.commands.Command;

public class HelloCmd extends Command {

    public HelloCmd(String name) {
        super(name);

        addSubCommand("world", strings -> {
            System.out.println("Hello World");
            return true;
        });

        getHelpBuilder().setTitleUsage("Salut moi c'est Hello !");
        getHelpBuilder().setCmdUsage("Dire \"Hello World\"","world");
    }
}

package fr.benjimania74.dnapitest.bot;

import fr.benjimania74.dnapitest.bot.cmd.services.StartCmd;
import fr.benjimania74.dnapitest.bot.cmd.services.StopCmd;

public class CommandsRegister {
    public void register(){
        BotMain.instance.registerCommand(new StartCmd("start", "Start services command"),
                new StopCmd("stop", "Strop services command"));
    }
}

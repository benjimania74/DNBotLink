package fr.benjimania74.dnapitest.bot;

import fr.benjimania74.dnapitest.bot.cmd.utils.LinkCmd;
import fr.benjimania74.dnapitest.bot.cmd.services.StartCmd;
import fr.benjimania74.dnapitest.bot.cmd.services.StopCmd;
import fr.benjimania74.dnapitest.bot.cmd.utils.HelpCmd;

public class CommandsRegister {
    public void register(){
        BotMain.instance.registerCommand(new StartCmd("start", "Start services command"),
                new StopCmd("stop", "Strop services command"),
                new HelpCmd("help", "See all commands"),
                new LinkCmd("link", "Link a console to Discord")
        );
    }
}

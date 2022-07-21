package fr.benjimania74.dnapitest.bot.cmd;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import fr.benjimania74.dnapitest.Main;
import fr.benjimania74.dnapitest.bot.BotConfig;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public abstract class Command {
    String name;
    String description;

    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrefix(){
        return BotConfig.getInstance().getPrefix();
    }
    public String getAddonName(){
        return Main.addonName;
    }

    public abstract void execute(TextChannel channel, DNClientAPI clientAPI, Message message);
}

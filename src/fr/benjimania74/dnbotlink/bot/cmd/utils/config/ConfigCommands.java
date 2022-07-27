package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import net.dv8tion.jda.api.entities.TextChannel;

public abstract class ConfigCommands {
    String name;
    String description;

    public ConfigCommands(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}

    public abstract void execute(TextChannel channel, String[] args);
}

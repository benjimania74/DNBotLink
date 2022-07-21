package fr.benjimania74.dnapitest.bot.cmd.services;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import fr.benjimania74.dnapitest.bot.BotConfig;
import fr.benjimania74.dnapitest.bot.cmd.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class StopCmd extends Command {
    public StopCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, DNClientAPI clientAPI, Message message) {
        String[] args = message.getContentRaw().substring(BotConfig.getInstance().getPrefix().length() + getName().length()).split(" ");
    }
}

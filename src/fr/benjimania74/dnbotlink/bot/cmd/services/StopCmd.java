package fr.benjimania74.dnbotlink.bot.cmd.services;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import fr.benjimania74.dnbotlink.bot.BotMain;
import fr.benjimania74.dnbotlink.bot.cmd.Command;
import fr.benjimania74.dnbotlink.utils.ServicesStopper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class StopCmd extends Command {
    public StopCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, DNClientAPI clientAPI, Message message) {
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Invalid Command")
                .setDescription("Usage: " + getPrefix() + getName() + " <service name to stop> [<server | proxy>]")
                .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl());

        String args[];

        try{
            args = message.getContentRaw().substring(getPrefix().length() + getName().length() + 1).split(" ");
        }catch (Exception e){
            channel.sendMessageEmbeds(eb.build()).queue();
            return;
        }

        if(args.length < 1){
            channel.sendMessageEmbeds(eb.build()).queue();
            return;
        }

        message.getChannel().sendMessageEmbeds(
                new ServicesStopper().stopB(args).setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl()).build()
        ).queue();
    }
}

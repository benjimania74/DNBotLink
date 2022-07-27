package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatusConfigCmd extends ConfigCommands {
    public StatusConfigCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, String[] args) {
        MessageEmbed me = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Incorrect Format")
                .setDescription("Format: " + BotConfig.getInstance().getPrefix() + "config " + getName() + " <online|idle|dnd|invisible|offline>")
                .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                .build();
        List<String> possibility = new ArrayList<>();
        possibility.add("online");
        possibility.add("idle");
        possibility.add("dnd");
        possibility.add("invisible");
        possibility.add("offline");

        if(args.length == 1 || !possibility.contains(args[1])){
            channel.sendMessageEmbeds(me).queue();
            return;
        }

        BotConfig.getInstance().setStatus(args[1]);
        BotConfig.getInstance().save();
        channel.sendMessageEmbeds(
                new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("Status Changed")
                        .setDescription("The Status has been changed to '" + OnlineStatus.fromKey(BotConfig.getInstance().getStatus()) + "'")
                        .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
        ).queue();
    }
}

package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class PrefixConfigCmd extends ConfigCommands {
    public PrefixConfigCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, String[] args) {
        if(args.length != 2){
            channel.sendMessageEmbeds(
                    new EmbedBuilder()
                            .setColor(Color.RED)
                            .setTitle("Incorrect Format")
                            .setDescription("Format: " + BotConfig.getInstance().getPrefix() + "config " + getName() + " <new value>")
                            .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                            .build()
            ).queue();
            return;
        }

        BotConfig.getInstance().setPrefix(args[1]);
        BotConfig.getInstance().save();
        channel.sendMessageEmbeds(
                new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("Prefix Changed")
                        .setDescription("The prefix has been changed to '" + args[1] + "'")
                        .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
        ).queue();
    }
}

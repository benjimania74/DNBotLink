package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class ActivityConfigCmd extends ConfigCommands {
    public ActivityConfigCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, String[] args) {
        if(args.length == 1){
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

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i != args.length; i++){sb.append(args[i]).append(" ");}
        BotConfig.getInstance().setActivity(sb.toString());
        BotConfig.getInstance().save();
        channel.sendMessageEmbeds(
                new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("Activity Changed")
                        .setDescription("The activity has been changed to '" + sb.toString() + "'")
                        .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
        ).queue();
    }
}

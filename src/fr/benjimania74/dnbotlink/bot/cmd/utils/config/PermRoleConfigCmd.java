package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class PermRoleConfigCmd extends ConfigCommands {
    public PermRoleConfigCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, String[] args) {
        if(args.length != 2){
            channel.sendMessageEmbeds(
                    new EmbedBuilder()
                            .setColor(Color.RED)
                            .setTitle("Incorrect Format")
                            .setDescription("Format: " + BotConfig.getInstance().getPrefix() + "config " + getName() + " <role id | role mention | everyone>")
                            .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                            .build()
            ).queue();
            return;
        }

        String roleID = args[1].replace("<@&", "").replace(">", "");
        boolean exist = false;
        if(!roleID.equals("everyone")){for(Role r : channel.getGuild().getRoles()){if(r.getId().equals(roleID)){exist = true;}}}
        if(!exist && !roleID.equals("everyone")){
            channel.sendMessageEmbeds(
                    new EmbedBuilder()
                            .setColor(Color.RED)
                            .setTitle("Incorrect Format")
                            .setDescription("Format: " + BotConfig.getInstance().getPrefix() + "config " + getName() + " <role id | role mention | everyone>")
                            .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                            .build()
            ).queue();
            return;
        }

        BotConfig.getInstance().setPermRole(roleID);
        BotConfig.getInstance().save();
        channel.sendMessageEmbeds(
                new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("PermRole Changed")
                        .setDescription("The PermRole has been changed to '" + args[1] + "'")
                        .setFooter(Main.addonName + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
        ).queue();
    }
}

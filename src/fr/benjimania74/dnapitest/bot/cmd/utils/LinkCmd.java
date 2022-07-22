package fr.benjimania74.dnapitest.bot.cmd.utils;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnapitest.Main;
import fr.benjimania74.dnapitest.bot.BotConfig;
import fr.benjimania74.dnapitest.bot.BotMain;
import fr.benjimania74.dnapitest.bot.cmd.Command;
import fr.benjimania74.dnapitest.utils.Services;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class LinkCmd extends Command {
    private final MessageEmbed successEmbed = new EmbedBuilder()
            .setColor(Color.GREEN)
            .setTitle("Service Linked")
            .setDescription("The Service has been successful link with this channel")
            .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
            .build();

    public LinkCmd(String name, String description) {super(name, description);}

    @Override
    public void execute(TextChannel channel, DNClientAPI clientAPI, Message message) {
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Invalid Command")
                .setDescription("Usage: " + getPrefix() + getName() + " <service name to link> [<server | proxy>]")
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

        if(args.length == 1){
            if(Services.isBoth(args[0])){
                channel.sendMessageEmbeds(new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Double Service")
                        .setDescription(args[0] + " is a Server and a Proxy ! Type '" + args[0] + " server' for server and '" + args[0] + " proxy' for proxy")
                        .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
                ).queue();
                return;
            }

            if(Services.getType(args[0]) == null){
                channel.sendMessageEmbeds(new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Inexistant Service")
                        .setDescription(args[0] + " is not a Service")
                        .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                        .build()
                ).queue();
                return;
            }

            BotConfig.getInstance().addLink(args[0], channel.getId());
            channel.sendMessageEmbeds(successEmbed).queue();
            return;
        }

        IContainer container = Main.clientAPI.getContainer();

        if(args[1].equalsIgnoreCase("server")){
            if(container.getJVMExecutorsServers().containsKey(args[0])){
                BotConfig.getInstance().addLink(args[0] + "<&>server", channel.getId());
                channel.sendMessageEmbeds(successEmbed).queue();
                return;
            }

            channel.sendMessageEmbeds(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(args[0] + " is not a Server")
                    .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                    .build()
            ).queue();
            return;
        }

        if(args[1].equalsIgnoreCase("proxy")){
            if(container.getJVMExecutorsProxy().containsKey(args[0])){
                BotConfig.getInstance().addLink(args[0] + "<&>proxy", channel.getId());
                channel.sendMessageEmbeds(successEmbed).queue();
                return;
            }
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Incorrect Service's Type")
                    .setDescription(args[0] + " is not a Server")
                    .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                    .build()
            ).queue();
            return;
        }

        channel.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Inexistant Service")
                .setDescription(args[0] + " is not a Service")
                .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl())
                .build()
        ).queue();
    }
}

package fr.benjimania74.dnbotlink.bot.cmd.utils;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnbotlink.bot.BotMain;
import fr.benjimania74.dnbotlink.bot.cmd.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class ListCmd extends Command {
    private EmbedBuilder eb;
    private DNClientAPI clientAPI;

    public ListCmd(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(TextChannel channel, DNClientAPI clientAPI, Message message) {
        this.clientAPI = clientAPI;
        eb = new EmbedBuilder()
                .setColor(new Color(0, 128, 255))
                .setTitle("Services List")
                .addField("Servers", "\u200b", false)
                .setFooter(getAddonName() + " by benjimania74", BotMain.instance.jda.getSelfUser().getAvatarUrl());

        clientAPI.getContainer().getJVMExecutorsServers().forEach((s, ijvmExecutor) -> eb.addField(s, value(s, IContainer.JVMType.SERVER), true));

        eb.addField("Proxy", "\u200b", false);

        clientAPI.getContainer().getJVMExecutorsProxy().forEach((s, ijvmExecutor) -> eb.addField(s, value(s, IContainer.JVMType.PROXY), true));

        channel.sendMessageEmbeds(eb.build()).queue();
    }

    private String value(String serviceName, IContainer.JVMType type){
        for(IClient client : clientAPI.getClientManager().getClients().values()){
            if(client.getJvmService().getJvmExecutor().getName().equals(serviceName) && client.getJvmType().equals(type)){
                return "Online :green_circle:";
            }
        }
        return "Offline :red_circle:";
    }
}

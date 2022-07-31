package fr.benjimania74.dnbotlink.bot.utils;

import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;

public class SendMessage {
    public static void send(String msg, String channelID){
        if(!channelExist(channelID)){return;}
        send(msg, Objects.requireNonNull(BotMain.instance.jda.getTextChannelById(channelID)));
    }

    public static void send(String msg, TextChannel channel){channel.sendMessage(msg).queue(); }

    public static void send(MessageEmbed embed, String channelID){
        if(!channelExist(channelID)){return;}
        send(embed, Objects.requireNonNull(BotMain.instance.jda.getTextChannelById(channelID)));
    }

    public static void send(MessageEmbed embed, TextChannel channel) {
        channel.sendMessageEmbeds(embed).queue();
    }

    private static boolean channelExist(String channelID){
        for(TextChannel channel : BotMain.instance.jda.getTextChannels()){if(channel.getId().equals(channelID)){return true;}}
        return false;
    }
}

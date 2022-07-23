package fr.benjimania74.dnbotlink.bot.utils;

import fr.benjimania74.dnbotlink.bot.BotMain;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class SendMessage {
    public void send(String msg, String channelID){
        if(!channelExist(channelID)){return;}
        send(msg, BotMain.instance.jda.getTextChannelById(channelID));
    }

    public void send(String msg, TextChannel channel){channel.sendMessage(msg).queue(); }

    public void send(MessageEmbed embed, String channelID){
        if(!channelExist(channelID)){return;}
        send(embed, BotMain.instance.jda.getTextChannelById(channelID));
    }

    public void send(MessageEmbed embed, TextChannel channel) {
        channel.sendMessageEmbeds(embed).queue();
    }

    private boolean channelExist(String channelID){
        for(TextChannel channel : BotMain.instance.jda.getTextChannels()){if(channel.getId().equals(channelID)){return true;}}
        return false;
    }
}

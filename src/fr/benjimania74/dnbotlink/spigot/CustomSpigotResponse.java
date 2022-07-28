package fr.benjimania74.dnbotlink.spigot;

import be.alexandre01.dnplugin.api.request.communication.ClientResponse;
import be.alexandre01.dnplugin.netty.channel.ChannelHandlerContext;
import be.alexandre01.dnplugin.utils.messages.Message;
import org.bukkit.Bukkit;

public class CustomSpigotResponse extends ClientResponse {
    @Override
    protected void onResponse(Message message, ChannelHandlerContext ctx) {
        if (message.getHeader().equals("DNBotLink-Chat")) {
            String msg = message.getString("Chat");
            Bukkit.getServer().broadcastMessage(msg);
        }
    }
}

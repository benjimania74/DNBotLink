package fr.benjimania74.dnbotlink.utils;

import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.client.utils.messages.Message;
import io.netty.channel.ChannelHandlerContext;

public class CustomResponse extends be.alexandre01.dreamnetwork.api.connection.core.communication.CoreResponse {
    public CustomResponse(){}

    @Override
    protected void onResponse(Message message, ChannelHandlerContext ctx, IClient client) {
        if(message.getHeader().equals("DNBotLink-Chat")){
            String msg = message.getString("Chat");
            System.out.println(msg);
        }
    }
}

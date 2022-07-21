package fr.benjimania74.dnapitest.bot.listeners;

import fr.benjimania74.dnapitest.Main;
import fr.benjimania74.dnapitest.bot.BotConfig;
import fr.benjimania74.dnapitest.bot.BotMain;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){return;}
        if(event.getMessage().getContentRaw().startsWith(BotConfig.getInstance().getPrefix())){
            BotMain.commandsList.forEach((cmd) -> {
                if(cmd.getName().equalsIgnoreCase(event.getMessage().getContentRaw().split(" ")[0].substring(BotConfig.getInstance().getPrefix().length()))){
                    cmd.execute((TextChannel) event.getChannel(), Main.clientAPI, event.getMessage());
                }
            });
        }
    }
}

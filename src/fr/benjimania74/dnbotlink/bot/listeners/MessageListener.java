package fr.benjimania74.dnbotlink.bot.listeners;

import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import fr.benjimania74.dnbotlink.bot.utils.ExecuteServerCmd;
import fr.benjimania74.dnbotlink.utils.Services;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){return;}

        if(event.getMessage().getContentRaw().equalsIgnoreCase("<@" + event.getJDA().getSelfUser().getId() + ">")){
            event.getChannel().sendMessage("My prefix is " + BotConfig.getInstance().getPrefix()).queue();
            return;
        }

        if(event.getMessage().getContentRaw().startsWith(BotConfig.getInstance().getPrefix())){
            String cmd = event.getMessage().getContentRaw().split(" ")[0].substring(BotConfig.getInstance().getPrefix().length());
            if(BotMain.commandsList.containsKey(cmd)){BotMain.commandsList.get(cmd).execute((TextChannel) event.getChannel(), Main.clientAPI, event.getMessage());}
        }

        BotConfig.getInstance().getLinks().forEach((service, id) -> {
            String name = service;

            if(service.split("<&>").length == 2){
                String[] args = service.split("<&>");
                name = args[0];
                if(args[1].equalsIgnoreCase("proxy")){ return; }
            }

            if(id.equals(event.getChannel().getId())){
                new ExecuteServerCmd().execute(event.getMessage().getContentRaw(), name);
            }
        });
    }
}

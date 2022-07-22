package fr.benjimania74.dnbotlink.bot.listeners;

import be.alexandre01.dreamnetwork.api.service.IContainer;
import fr.benjimania74.dnbotlink.Main;
import fr.benjimania74.dnbotlink.bot.BotConfig;
import fr.benjimania74.dnbotlink.bot.BotMain;
import fr.benjimania74.dnbotlink.bot.ExecuteServerCmd;
import fr.benjimania74.dnbotlink.utils.Services;
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

        BotConfig.getInstance().getLinks().forEach((service, id) -> {
            String name = service;
            IContainer.JVMType type = Services.getType(service);

            if(service.split("<&>").length == 2){
                String[] args = service.split("<&>");
                name = args[0];
                if(args[1].equalsIgnoreCase("server")){ type = IContainer.JVMType.SERVER; }
                if(args[1].equalsIgnoreCase("proxy")){ type = IContainer.JVMType.PROXY; }
            }

            if(id.equals(event.getChannel().getId())){
                new ExecuteServerCmd().execute(event.getMessage().getContentRaw(), name, type);
            }
        });

        if(event.getMessage().getContentRaw().equalsIgnoreCase("<@" + event.getJDA().getSelfUser().getId() + ">")){
            event.getChannel().sendMessage("My prefix is " + BotConfig.getInstance().getPrefix()).queue();
        }
    }
}